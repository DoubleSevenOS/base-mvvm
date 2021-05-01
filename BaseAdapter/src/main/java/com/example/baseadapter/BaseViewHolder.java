package com.example.baseadapter;

import android.util.SparseArray;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


/**
 * 创建者 ：  huyangyang962
 * 创建日期： 2021/3/25 11:19 AM
 * 描述： 父类 BaseViewHolder
 */
public class BaseViewHolder extends RecyclerView.ViewHolder {

    public View mRootView;

    private SparseArray<View> mViews;

    public BaseViewHolder(@NonNull View itemView) {
        super(itemView);
        mRootView = itemView;
        mViews = new SparseArray<>();
    }

    public View getRootView() {
        return mRootView;
    }

    public <T extends View> T getView(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = mRootView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        if (view == null) {
            throw new RuntimeException("No view found with id " + viewId);
        }
        return (T) view;
    }

}
