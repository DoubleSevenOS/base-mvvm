package com.android.loadlayout.view;

import android.content.Context;
import android.view.View;


/**
 * 创建者 ：  huyangyang962
 * 创建日期： 2021/3/18 2:46 PM
 * 描述：
 */
public class StateOriginalView extends StateLoadView {
    public StateOriginalView(View rootView, Context context) {
        super(rootView, context);
    }

    @Override
    protected void onCreatView(Context context, View rootView) {

    }

    @Override
    protected int getLayoutViewId() {
        return 0;
    }
}
