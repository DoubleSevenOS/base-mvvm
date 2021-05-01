package com.android.loadlayout.view;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;


/**
 * 创建者 ：  huyangyang962
 * 创建日期： 2021/3/18 2:46 PM
 * 描述：
 */
public class StateLoadLayout extends FrameLayout {

    private StateOriginalView originalView;

    private StateLoadView mCurShowView;

    public StateLoadLayout(Context context) {
        super(context);
    }

    public void setOriginalView(StateOriginalView originalView) {
        this.originalView = originalView;
        View contentView = originalView.getRootView();
        //先把原始View添加进来
        addView(contentView);
    }

    public void showLoadView(StateLoadView view) {
        if (view == mCurShowView) {
            return;
        }
        if (getChildCount() > 1) {
            removeViews(1, getChildCount() - 1);
        }
        View rootView = view.getRootView();
        if (rootView != null) {
            mCurShowView = view;
            addView(rootView);
        }
    }

    public void showtOriginalView() {
        // TODO: 2021/3/26
        Log.i("LoadView", "OriginalView: " + getChildCount());
        mCurShowView = null;
        if (getChildCount() > 1) {
            removeViews(1, getChildCount() - 1);
        }
    }
}
