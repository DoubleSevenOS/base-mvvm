package com.example.myapplicationt.ui.login;

import androidx.annotation.NonNull;

import com.example.myapplicationt.BR;
import com.example.myapplicationt.R;
import com.example.myapplicationt.databinding.ActivityLoginBinding;
import com.android.base.activity.BaseDataBindingActivity;
import com.android.base.base.BaseDataBindingConfig;

public class LoginActivity extends BaseDataBindingActivity<ActivityLoginBinding, LoginViewModel> {


    @NonNull
    @Override
    public BaseDataBindingConfig getDataBindingConfig() {
        return new BaseDataBindingConfig(R.layout.activity_login)
                .addBindingParam(BR.vm, mViewModel)
                ;
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