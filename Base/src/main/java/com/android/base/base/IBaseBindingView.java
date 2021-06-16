package com.android.base.base;

import androidx.annotation.NonNull;

public interface IBaseBindingView<VM> {
    /**
     * 获取binding相关配置
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
