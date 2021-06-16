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
