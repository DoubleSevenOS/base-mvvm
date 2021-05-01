package com.example.myapplicationt.ui.login;

import android.app.Application;
import android.text.TextUtils;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.android.base.viewmodel.BaseViewModel;

import io.reactivex.functions.Consumer;

public class LoginViewModel extends BaseViewModel {

    public MutableLiveData<String> inputName = new MutableLiveData<>();
    public MutableLiveData<String> inputPsw = new MutableLiveData<>();

    private LoginModel loginMode = new LoginModel();

    public LoginViewModel(@NonNull Application application) {
        super(application);
    }

    public boolean isLoginEnable(String name, String psw) {
        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(psw)) {
            return false;
        }
        return true;
    }

    public void login() {
        showLoading();
        addSubscribe(loginMode.login("", ""), new Consumer<LoginResult>() {
            @Override
            public void accept(LoginResult loginResult) throws Exception {
                dismissView();
                if (loginResult.getError() == 0) {
                    jumpPage("com.example.myapplicationt.MainActivity");
                } else {
                    Toast.makeText(getApplication().getBaseContext(), "密码错误", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}