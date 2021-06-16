package com.example.myapplicationt.repo;


import com.example.myapplicationt.bean.LoginResult;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

/**
 * 创建者 ：  huyangyang962
 * 创建日期： 2021/4/1 5:47 PM
 * 描述：
 */
public class LoginModel {
    public Observable<LoginResult> login(String acc, String psw) {
        return Observable
                .timer(2, TimeUnit.SECONDS)
                .flatMap(new Function<Long, ObservableSource<LoginResult>>() {
                    @Override
                    public ObservableSource<LoginResult> apply(@NonNull Long aLong) throws Exception {
                        int status = new Random().nextInt() % 2 == 0 ? 0 : 404;
                        LoginResult loginResult = new LoginResult(0);
                        loginResult.setToken(new Random().nextLong() + "");
                        return Observable.just(loginResult);
                    }
                });

    }
}
