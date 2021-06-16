package com.android.base.fragment;

import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.base.base.BaseDataBindingConfig;
import com.android.base.base.BaseViewEventProcessor;
import com.android.base.base.IBaseBindingView;
import com.android.base.base.IBaseView;
import com.android.base.view.LoadingView;
import com.android.base.viewmodel.BaseViewModel;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
public abstract class BaseDataBindFragment<DB extends ViewDataBinding, VM extends BaseViewModel> extends Fragment implements IBaseView, IBaseBindingView<VM> {
    public DB mBinding;
    public VM mViewModel;
    private LoadingView mLoadingView;

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
    }

    /**
     * 显示空界面
     */
    @Override
    public void showEmptyView() {
        Log.i("LoadService", "showEmptyView: ");
    }

    /**
     * 取消状态界面
     */
    @Override
    public void dismissView() {
        Log.i("LoadService", "dismissView: ");
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
        Log.i("PagePage", "=====跳转界面====" + path);
    }

    /**
     * 跳转界面
     *
     * @param path
     * @param bundle
     */
    @Override
    public void jumpPage(String path, Bundle bundle) {
        Log.i("PagePage", "=====跳转界面====" + path + "，exrParams" + bundle.toString());
    }


    /**
     * 是否允许把fragment根布局，替换成 LoadLayout
     *
     * @return
     */
    protected boolean isRegistLoadService() {
        return false;
    }

    /**
     * 显示的状态公共视图的容器（targetView）
     *
     * @return
     */
    protected View targetView() {
        return null;
    }
}
