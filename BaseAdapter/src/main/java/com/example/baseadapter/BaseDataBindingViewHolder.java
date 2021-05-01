package com.example.baseadapter;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

/**
 * 创建者 ：  huyangyang962
 * 创建日期： 2021/3/25 6:14 PM
 * 描述：
 */
public class BaseDataBindingViewHolder<DB extends ViewDataBinding> extends BaseViewHolder {
    private DB db;

    public BaseDataBindingViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    public DB getBinding() {
        if (db == null) {
            db = DataBindingUtil.bind(mRootView);
        }
        return db;
    }
}
