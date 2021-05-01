package com.android.base.view;

import android.content.Context;
import android.view.View;

import com.android.loadlayout.view.StateLoadView;
import com.android.base.R;

/**
 * @ClassName: com.android.base.view
 * @Description: 加载动画
 * @Author: yyw
 * @Date: 2021/1/5
 * @Time: 14:10
 */
public class LoadingView extends StateLoadView {
    public LoadingView(Context context) {
        super(context);
    }

    @Override
    protected void onCreatView(Context context, View rootView) {

    }


    @Override
    protected int getLayoutViewId() {
        return R.layout.layout_loadingview;
    }
}
