package com.android.loadlayout.core;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

/**
 * 创建者 ：  huyangyang962
 * 创建日期： 2021/3/18 2:46 PM
 * 描述：
 */
public class StateLoadUtil {

    public static StateTargetContent getTargetContent(Object target) {
        Context context = null;
        View oldView = null;
        ViewGroup contentParent = null;
        int contentIndex = 0;

        if (target instanceof Activity) {
            Activity activity = (Activity) target;
            context = activity;
            contentParent = activity.findViewById(android.R.id.content);
        } else if (target instanceof View) {
            context = ((View) target).getContext();
            oldView = (View) target;
            contentParent = (ViewGroup) ((View) target).getParent();
        } else {
            throw new IllegalArgumentException("The target must be within Activity or View");
        }

        if (target instanceof View) {
            int childCount = contentParent == null ? 0 : contentParent.getChildCount();
            for (int i = 0; i < childCount; i++) {
                if (contentParent.getChildAt(i) == oldView) {
                    contentIndex = i;
                    break;
                }
            }
        } else {
            oldView = contentParent != null ? contentParent.getChildAt(0) : null;
        }

        if (oldView == null) {
            throw new IllegalArgumentException("The target must be within Activity, View");
        }

        if (contentParent != null) {
            //移除掉targView
            contentParent.removeView(oldView);
        }
        return new StateTargetContent(context, oldView, contentParent, contentIndex);
    }
}
