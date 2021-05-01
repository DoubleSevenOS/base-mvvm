package com.android.loadlayout.core;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.android.loadlayout.view.StateLoadLayout;
import com.android.loadlayout.view.StateLoadView;
import com.android.loadlayout.view.StateOriginalView;

/**
 * 创建者 ：  huyangyang962
 * 创建日期： 2021/3/18 2:46 PM
 * 描述：
 */
public class StateLoadService {
    private StateLoadLayout paklLoadLayout;

    public StateLoadLayout getPaklLoadLayout() {
        return paklLoadLayout;
    }

    private StateLoadService() {

    }

    public StateLoadService(StateTargetContent targetContent) {
        Context context = targetContent.getContext();
        View oriView = targetContent.getOriView();
        int oriIndex = targetContent.getOriIndex();
        //初始化LoadLayout===>这个布局文件，会把原来的view 加入进来。同时后面需要显示的状态、空布局等也会添加这里
        this.paklLoadLayout = new StateLoadLayout(context);
        StateOriginalView originalView = new StateOriginalView(oriView, context);
        //把原始View添加到容器中
        paklLoadLayout.setOriginalView(originalView);
        ViewGroup.LayoutParams oriParams = oriView.getLayoutParams();
        //原始的parent 还是原来的位置，原来的属性，添加 LoadLayout===>狸猫换太子成功
        if (targetContent.getParentView() != null) {
            targetContent.getParentView().addView(paklLoadLayout, oriIndex, oriParams);
        }
    }

    /**
     * 显示原始view
     */
    public void showOriginalView() {
        paklLoadLayout.showtOriginalView();
    }

    public void showLoadView(StateLoadView view) {
        if (view != null) {
            paklLoadLayout.showLoadView(view);
        }
    }

    public static StateLoadService register(Object target) {
        StateTargetContent targetContext = StateLoadUtil.getTargetContent(target);
        return new StateLoadService(targetContext);
    }

}
