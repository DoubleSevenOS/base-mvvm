package com.android.base.base;

import android.os.Bundle;

import java.util.Map;

/**
 * @ClassName: com.android.base.ui
 * @Description: 基类，基础功能
 * @Author: yyw
 * @Date: 2021/1/4
 * @Time: 16:46
 */
public interface IBaseView {

    void initView();

    void initData();

    /**
     * 显示loading
     */
    void showLoadingView();

    /**
     * 取消状态界面
     */
    void dismissView();

    /**
     * 显示空界面
     */
    void showEmptyView();

    /**
     * 显示重新刷新界面
     */
    void showReloadView();


    /**
     * 跳转界面
     *
     * @param path
     */
    void jumpPage(String path);

    /**
     * 跳转界面
     *
     * @param path
     * @param bundle
     */
    void jumpPage(String path, Bundle bundle);
}
