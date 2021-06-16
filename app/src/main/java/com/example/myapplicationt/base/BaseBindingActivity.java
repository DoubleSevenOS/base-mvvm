package com.example.myapplicationt.base;

import android.os.Bundle;

import com.android.base.activity.BaseDataBindingActivity;
import com.android.base.viewmodel.BaseViewModel;

import androidx.databinding.ViewDataBinding;

/**
 * @ClassName: com.example.myapplicationt.base
 * @Description:
 * @Author: hyy
 * @Date: 2021/6/16
 * @Time: 4:01 PM
 */
public abstract class BaseBindingActivity<DB extends ViewDataBinding, VM extends BaseViewModel> extends BaseDataBindingActivity<DB, VM> {

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    /**
     * 显示loading
     */
    @Override
    public void showLoadingView() {

    }

    /**
     * 取消状态界面
     */
    @Override
    public void dismissView() {

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

    }

    @Override
    public void jumpPage(String path, Bundle bundle) {

    }
}
