package com.android.base.base;

import android.os.Bundle;
import android.text.method.SingleLineTransformationMethod;

import androidx.lifecycle.MutableLiveData;

/**
 * 创建者 ：  huyangyang962
 * 创建日期： 2021/3/22 10:36 AM
 * 描述：
 */
public class BaseViewEventLiveData {
    private MutableLiveData<Boolean> mIsShowLoadingEvent;
    private MutableLiveData<Boolean> mIsEmptyViewEvent;
    private MutableLiveData<Boolean> mIsDismissViewEvent;
    private MutableLiveData<Boolean> mIsReloadViewEvent;
    private MutableLiveData<String> mJumpPathEvent;
    private MutableLiveData<Bundle> mJumpPathParamsEvent;

    private <T> MutableLiveData<T> getLiveData(MutableLiveData<T> mutableLiveData) {
        if (mutableLiveData == null) {
            mutableLiveData = new MutableLiveData();
        }
        return mutableLiveData;
    }

    /**
     * @return 获取loading 加载view
     */
    public MutableLiveData<Boolean> getShowLoadingEvent() {
        return mIsShowLoadingEvent = getLiveData(mIsShowLoadingEvent);
    }

    /**
     * @return 获取空界面view
     */
    public MutableLiveData<Boolean> getEmptyViewEvent() {
        return mIsEmptyViewEvent = getLiveData(mIsEmptyViewEvent);
    }

    /**
     * @return 取消所有状态view
     */
    public MutableLiveData<Boolean> getDismissViewEvent() {
        return mIsDismissViewEvent = getLiveData(mIsDismissViewEvent);
    }

    /**
     * @return 获取重新加载view
     */
    public MutableLiveData<Boolean> getReloadViewEvent() {
        return mIsReloadViewEvent = getLiveData(mIsReloadViewEvent);
    }

    /**
     * @return 获取跳转界面事件
     */
    public MutableLiveData<String> getJumpPagePathEvent() {
        return mJumpPathEvent = getLiveData(mJumpPathEvent);
    }

    /**
     * @return 获取跳转界面事件
     */
    public <T extends Bundle> MutableLiveData<Bundle> getJumpPageParamsEvent() {
        return mJumpPathParamsEvent = getLiveData(mJumpPathParamsEvent);
    }

}
