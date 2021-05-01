package com.android.base.view;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.android.loadlayout.view.StateLoadView;
import com.android.base.R;

/**
 * @ClassName: com.android.base.view
 * @Description: 空界面视图
 * @Author: yyw
 * @Date: 2021/1/5
 * @Time: 14:10
 */
public class EmptyView extends StateLoadView {
    public EmptyView(Context context) {
        super(context);
    }

    @Override
    protected void onCreatView(Context context, View rootView) {
        TextView textView = rootView.findViewById(R.id.tv_empty_view);
        textView.setText("你好，我是空界面");
    }

    @Override
    protected int getLayoutViewId() {
        return R.layout.layout_empty_view;
    }
}
