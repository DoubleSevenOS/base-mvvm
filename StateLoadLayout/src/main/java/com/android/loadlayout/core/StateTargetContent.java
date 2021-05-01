package com.android.loadlayout.core;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

/**
 * 创建者 ：  huyangyang962
 * 创建日期： 2021/3/18 2:46 PM
 * 描述：
 */
public class StateTargetContent {
    private Context context;
    private View oriView;
    private ViewGroup parentView;
    private int oriIndex;

    public StateTargetContent(Context context, View oriView, ViewGroup oriParentView, int oriIndex) {
        this.context = context;
        this.oriView = oriView;
        this.parentView = oriParentView;
        this.oriIndex = oriIndex;
    }

    public Context getContext() {
        return context;
    }

    public View getOriView() {
        return oriView;
    }

    public ViewGroup getParentView() {
        return parentView;
    }

    public int getOriIndex() {
        return oriIndex;
    }
}
