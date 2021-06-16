package com.android.base.activity;

import android.os.Bundle;
import android.util.SparseArray;

import com.android.base.base.BaseDataBindingConfig;
import com.android.base.base.BaseViewEventProcessor;
import com.android.base.base.IBaseBindingView;
import com.android.base.base.IBaseView;
import com.android.base.viewmodel.BaseViewModel;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.ViewModelProvider;

public abstract class BaseDataBindingActivity<DB extends ViewDataBinding, VM extends BaseViewModel> extends AppCompatActivity implements IBaseView, IBaseBindingView<VM> {
    public DB mBinding;
    public VM mViewModel;

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


    @Override
    public void finishActivity() {
        finish();
    }
}
