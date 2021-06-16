package com.example.myapplicationt;

import android.app.Application;

import com.android.base.BaseApplication;

/**
 * @ClassName: com.example.myapplicationt
 * @Description:
 * @Author: hyy
 * @Date: 2021/6/16
 * @Time: 6:08 PM
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        BaseApplication.registBaseViewProxy(CustProxy.class);
    }
}
