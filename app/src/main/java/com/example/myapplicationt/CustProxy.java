package com.example.myapplicationt;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.android.base.base.BaseViewProxy;

/**
 * @ClassName: com.example.myapplicationt
 * @Description:
 * @Author: hyy
 * @Date: 2021/6/16
 * @Time: 6:07 PM
 */
public class CustProxy extends BaseViewProxy {
    public CustProxy(Context context) {
        super(context);
    }


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
        //TODO log 调式
        Log.i("CustProxy", "showLoadingView==啥也不干就是玩=>");
    }

    /**
     * 取消状态界面
     */
    @Override
    public void dismissView() {
        //TODO log 调式
        Log.i("CustProxy", "dismissView==啥也不干就是玩=>");
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
        //TODO log 调式
        Log.i("CustProxy", "jumpPage==啥也不干就是玩=>");
    }

    /**
     * 跳转界面
     *
     * @param path
     * @param bundle
     */
    @Override
    public void jumpPage(String path, Bundle bundle) {
        //TODO log 调式
        Log.i("CustProxy", "jumpPage==啥也不干就是玩=>");
    }

    @Override
    public void finishActivity() {

    }
}
