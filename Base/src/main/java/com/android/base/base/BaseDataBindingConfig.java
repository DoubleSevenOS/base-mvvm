package com.android.base.base;

import android.util.SparseArray;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;

public class BaseDataBindingConfig {
    private int mLayoutId;
    private SparseArray mBindingParams = new SparseArray();

    public BaseDataBindingConfig(@LayoutRes int layoutId) {
        this.mLayoutId = layoutId;
    }

    public BaseDataBindingConfig addBindingParam(@NonNull Integer variableId, @NonNull Object object) {
        if (variableId > 0 && object != null) {
            mBindingParams.put(variableId, object);
        }
        return this;
    }

    @LayoutRes
    public int getLayoutId() {
        return mLayoutId;
    }

    /**
     * 获取bingding参数
     *
     * @return
     */
    public SparseArray getBindingParams() {
        return mBindingParams;
    }
}
