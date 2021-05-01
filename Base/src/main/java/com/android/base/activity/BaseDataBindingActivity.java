package com.android.base.activity;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.ViewModelProvider;

import com.android.base.base.BaseDataBindingConfig;
import com.android.base.base.BaseViewEventProcessor;
import com.android.base.base.IBaseBindingView;
import com.android.base.base.IBaseView;
import com.android.base.view.EmptyView;
import com.android.base.view.LoadingView;
import com.android.base.viewmodel.BaseViewModel;

/**
 * @ClassName: com.android.base.ui
 * @Description:
 * @Author: yyw
 * @Date: 2021/1/4
 * @Time: 16:47
 */
public abstract class BaseDataBindingActivity<DB extends ViewDataBinding, VM extends BaseViewModel> extends AppCompatActivity implements IBaseView, IBaseBindingView<VM> {
    private static final String TAG = "BaseDataBindingActivity";
    public DB mBinding;
    public VM mViewModel;

    /**
     * 加载动画
     */
    private LoadingView mLoadingView;
    /**
     * 加载空界面
     */
    private EmptyView mEmptyView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        Log.i(TAG, "showLoadingView: ");
    }

    /**
     * 取消状态界面
     */
    @Override
    public void dismissView() {
        Log.i(TAG, "dismissView: ");
    }

    /**
     * 显示空界面
     */
    @Override
    public void showEmptyView() {
    }

    /**
     * 显示重新刷新界面
     */
    @Override
    public void showReloadView() {

    }

    /**
     * 跳转界面
     *
     * @param path
     */
    @Override
    public void jumpPage(String path) {
        Log.i(TAG, "jumpPage: " + path);
        //  跳转界面 路由等方式
        try {
            Intent intent = new Intent();
            intent.setComponent(new ComponentName(this, path));
            startActivity(intent);
        } catch (Exception e) {
            Log.i(TAG, "jumpPage: Exception" + e.getMessage());
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
        //  跳转界面  路由等方式
        try {
            Intent intent = new Intent();
            intent.setComponent(new ComponentName(this, path));
            intent.putExtras(bundle);
            startActivity(intent);
        } catch (Exception e) {
        }
    }
}
