package com.example.myapplicationt.ui.login;

import com.android.base.base.BaseDataBindingConfig;
import com.example.myapplicationt.BR;
import com.example.myapplicationt.R;
import com.example.myapplicationt.base.BaseBindingActivity;
import com.example.myapplicationt.databinding.ActivityLoginBinding;

import androidx.annotation.NonNull;

public class LoginActivity extends BaseBindingActivity<ActivityLoginBinding, LoginViewModel> {

    @NonNull
    @Override
    public BaseDataBindingConfig getDataBindingConfig() {
        return new BaseDataBindingConfig(R.layout.activity_login)
                .addBindingParam(BR.vm, mViewModel);
    }

    @NonNull
    @Override
    public Class<LoginViewModel> initViewModelClazz() {
        return LoginViewModel.class;
    }

    @Override
    public void initView() {
    }


    @Override
    public void initData() {

    }
}