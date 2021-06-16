package com.android.base.activity;

import android.content.Context;
import android.os.Bundle;
import android.util.SparseArray;

import com.android.base.BaseApplication;
import com.android.base.base.BaseDataBindingConfig;
import com.android.base.base.BaseViewEventProcessor;
import com.android.base.base.BaseViewProxy;
import com.android.base.base.IBaseBindingView;
import com.android.base.base.IBaseView;
import com.android.base.viewmodel.BaseViewModel;

import java.lang.reflect.Constructor;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.ViewModelProvider;

/**
 * @ClassName BaseDataBindingActivity
 * @Author hyy
 * @Description 本身不对IBaseView做任何处理，我是中间商，只对相应的事件做事件回调处理，不具体实现对应的事件。
 * 需要注册代理类，处理实现对应的View操作
 * @Date 2021/6/16
 * @Time 7:17 PM
 */
public abstract class BaseDataBindingActivity<DB extends ViewDataBinding, VM extends BaseViewModel> extends AppCompatActivity implements IBaseView, IBaseBindingView<VM> {
    public DB mBinding;
    public VM mViewModel;
    /**
     * IBaseView 具体的处理代理类
     * 为空的话，走默认BaseDataBindingActivity 实现方法
     */
    public BaseViewProxy baseViewProxy;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        baseViewProxy = getBaseViewProxy();
        //初始化viewmodel
        initViewModel();
        //初始化dataBinging
        initBinding();
        //注册livedata公共事件
        registViewModelEvent();
        initView();
        initData();
    }

    private void initViewModel() {
        mViewModel = getViewModel(initViewModelClazz());
        if (mViewModel != null) {
            getLifecycle().addObserver(mViewModel);
        }
    }

    /**
     * 初始化binding
     */
    private void initBinding() {
        BaseDataBindingConfig baseDataBindingConfig = getDataBindingConfig();
        if (baseDataBindingConfig == null) {
            throw new RuntimeException("BaseDataBindingConfig 不能为空");
        }
        mBinding = DataBindingUtil.setContentView(this, baseDataBindingConfig.getLayoutId());
        mBinding.setLifecycleOwner(this);
        SparseArray bindingParams = baseDataBindingConfig.getBindingParams();
        for (int i = 0; i < bindingParams.size(); i++) {
            mBinding.setVariable(bindingParams.keyAt(i), bindingParams.valueAt(i));
        }
    }

    private VM getViewModel(@NonNull Class<VM> modelClass) {
        return new ViewModelProvider(this).get(modelClass);
    }

    /**
     * 注册BaseViewModelEvent
     */
    private void registViewModelEvent() {
        new BaseViewEventProcessor(this, mViewModel, this);
    }


    /**
     * 显示loading
     */
    @Override
    public void showLoadingView() {
        if (baseViewProxy != null) {
            baseViewProxy.showLoadingView();
        }
    }

    /**
     * 取消状态界面
     */
    @Override
    public void dismissView() {
        if (baseViewProxy != null) {
            baseViewProxy.dismissView();
        }
    }

    /**
     * 显示空界面
     */
    @Override
    public void showEmptyView() {
        if (baseViewProxy != null) {
            baseViewProxy.showEmptyView();
        }
    }

    /**
     * 显示重新刷新界面
     */
    @Override
    public void showReloadView() {
        if (baseViewProxy != null) {
            baseViewProxy.showReloadView();
        }
    }

    /**
     * 跳转界面
     *
     * @param path
     */
    @Override
    public void jumpPage(String path) {
        if (baseViewProxy != null) {
            baseViewProxy.jumpPage(path);
        }
    }

    /**
     * 跳转界面
     *
     * @param path
     * @param bundle
     */
    @Override
    public void jumpPage(String path, Bundle bundle) {
        if (baseViewProxy != null) {
            baseViewProxy.jumpPage(path, bundle);
        }
    }

    @Override
    public void finishActivity() {
        if (baseViewProxy != null) {
            baseViewProxy.finishActivity();
        } else {
            finish();
        }
    }

    /**
     * 通过反射拿到，IBaseView 处理代理类
     *
     * @return
     */
    public BaseViewProxy getBaseViewProxy() {
        BaseViewProxy baseViewProxy = null;
        Class<BaseViewProxy> clazz = BaseApplication.getBaseViewProxyClass();
        if (clazz != null) {
            try {
                Constructor<BaseViewProxy> constructor = clazz.getConstructor(Context.class);
                return constructor.newInstance(getBaseContext());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return baseViewProxy;
    }

    /**
     * 手动调用设置代理类
     *
     * @param baseViewProxy
     */
    public void setBaseViewProxy(BaseViewProxy baseViewProxy) {
        this.baseViewProxy = baseViewProxy;
    }
}
