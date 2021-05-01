package com.android.base.base;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;

import com.android.base.common.Constants;
import com.android.base.viewmodel.BaseViewModel;

/**
 * @ClassName: com.android.base.ui
 * @Description: bindingview基类，公共事件处理类
 * @Author: yyw
 * @Date: 2021/1/5
 * @Time: 15:50
 */
public class BaseViewEventProcessor<T extends LifecycleOwner> {
    public BaseViewEventProcessor(@NonNull T t, @NonNull BaseViewModel baseViewModel, @NonNull IBaseView iBaseView) {
        registEvent(t, baseViewModel, iBaseView);
    }

    /**
     * 注册IBaseView 事件
     *
     * @param viewModel
     * @param iBaseView
     */
    private void registEvent(T t, BaseViewModel viewModel, final IBaseView iBaseView) {
        if (viewModel != null) {
            /**
             * 通知IBaseView loading
             */
            viewModel.getBaseViewEventLiveData().getShowLoadingEvent().observe(t, new Observer<Boolean>() {
                @Override
                public void onChanged(Boolean aBoolean) {
                    if (aBoolean) {
                        iBaseView.showLoadingView();
                    }
                }
            });

            /**
             * 通知IBaseView 消息loading
             */
            viewModel.getBaseViewEventLiveData().getDismissViewEvent().observe(t, new Observer<Boolean>() {
                @Override
                public void onChanged(Boolean aBoolean) {
                    Log.i("LiveData", "DismissViewEvent: onChanged" + aBoolean);
                    if (aBoolean) {
                        iBaseView.dismissView();
                    }
                }
            });
            /**
             * 通知IBaseView  显示空界面
             */
            viewModel.getBaseViewEventLiveData().getEmptyViewEvent().observe(t, new Observer<Boolean>() {
                @Override
                public void onChanged(Boolean aBoolean) {
                    if (aBoolean) {
                        iBaseView.showEmptyView();
                    }
                }
            });
            /**
             * 通知IBaseView  显示重新加载界面
             */
            viewModel.getBaseViewEventLiveData().getReloadViewEvent().observe(t, new Observer<Boolean>() {
                @Override
                public void onChanged(Boolean aBoolean) {
                    if (aBoolean) {
                        iBaseView.showReloadView();
                    }
                }
            });

            /**
             * 通知IBaseView  跳转界面
             */
            viewModel.getBaseViewEventLiveData().getJumpPagePathEvent().observe(t, new Observer<String>() {
                @Override
                public void onChanged(String path) {
                    iBaseView.jumpPage(path);
                }
            });
            /**
             * 通知IBaseView  跳转界面
             */
            viewModel.getBaseViewEventLiveData().getJumpPageParamsEvent().observe(t, new Observer<Bundle>() {
                @Override
                public void onChanged(Bundle paramsMap) {
                    if (paramsMap.containsKey(Constants.JUMP_PAGE_PATH_KEY)) {
                        String path = paramsMap.getString(Constants.JUMP_PAGE_PATH_KEY);
                        if (!TextUtils.isEmpty(path)) {
                            paramsMap.remove(Constants.JUMP_PAGE_PATH_KEY);
                            iBaseView.jumpPage(path, paramsMap);
                        }
                    }
                }
            });

        }
    }
}
