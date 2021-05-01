package com.android.base.base;

import androidx.annotation.NonNull;

/**
 * @ClassName: com.android.base.ui
 * @Description: 基类bingding , 基础功能
 * @Author: yyw
 * @Date: 2021/1/5
 * @Time: 14:52
 */
public interface IBaseBindingView<VM> {
    /**
     * 获取binding 配置
     *
     * @return
     */
    @NonNull
    BaseDataBindingConfig getDataBindingConfig();

    /**
     * 获取viewmodel Clazz
     *
     * @return
     */
    @NonNull
    Class<VM> initViewModelClazz();
}
