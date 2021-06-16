package com.android.base.fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

/**
 * @ClassName BaseDataBindFragment
 * @Author hyy
 * @Description 本身不对IBaseView做任何处理，我是中间商，只对相应的事件做事件回调处理，不具体实现对应的事件。
 * 需要注册代理类，处理实现对应的View操作
 * @Date 2021/6/16
 * @Time 7:17 PM
 */
public abstract class BaseDataBindFragment<DB extends ViewDataBinding, VM extends BaseViewModel> extends Fragment implements IBaseView, IBaseBindingView<VM> {
    public DB mBinding;
    public VM mViewModel;
    /**
     * IBaseView 具体的处理代理类
     * 为空的话，走默认BaseDataBindingActivity 实现方法
     */
    public BaseViewProxy baseViewProxy;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //获取ViewModel
        initViewModel();
        initBinding(inflater, container);
        // 注册viewmodel 公共事件
        registViewModelEvent();
        initView();
        initData();
        return mBinding.getRoot();
    }

    private void initViewModel() {
        mViewModel = getViewModel(initViewModelClazz());
        if (mViewModel != null) {
            getLifecycle().addObserver(mViewModel);
        }
    }

    private void initBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        BaseDataBindingConfig baseDataBindingConfig = getDataBindingConfig();
        mBinding = DataBindingUtil.inflate(inflater, baseDataBindingConfig.getLayoutId(), container, false);
        //binding 绑定 lifecycle
        mBinding.setLifecycleOwner(this);
        // binding 添加 xml 配置参数
        SparseArray bindingParams = baseDataBindingConfig.getBindingParams();
        for (int i = 0; i < bindingParams.size(); i++) {
            mBinding.setVariable(bindingParams.keyAt(i), bindingParams.valueAt(i));
        }
    }

    /**
     * 注册IBaseView公共事件处理
     */
    private void registViewModelEvent() {
        new BaseViewEventProcessor(this, mViewModel, this);
    }

    private VM getViewModel(Class<VM> modelClass) {
        return new ViewModelProvider(this).get(modelClass);
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
            getActivity().finish();
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
                return constructor.newInstance(getContext());
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
