package com.android.loadlayout.view;

import android.content.Context;
import android.view.View;

/**
 * 创建者 ：  huyangyang962
 * 创建日期： 2021/3/18 2:46 PM
 * 描述：
 */
public abstract class StateLoadView {
    public View rootView;
    public Context context;

    public StateLoadView(Context context) {
        this.context = context;
    }
    public StateLoadView(View rootView, Context context) {
        this.rootView = rootView;
        this.context = context;
    }

    public View getRootView() {
        if (rootView != null) {
            return rootView;
        }
        if (getLayoutViewId() != 0) {
            rootView = View.inflate(context, getLayoutViewId(), null);
            onCreatView(context, rootView);
            rootView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
        return rootView;
    }

    protected abstract void onCreatView(Context context, View rootView);

    protected abstract int getLayoutViewId();

}
