package com.android.base.viewmodel;

import android.app.Application;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModel;

import com.android.base.LifecycleObseverImp;
import com.android.base.base.BaseViewEventLiveData;
import com.android.base.common.Constants;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * @ClassName: com.android.base.viewmodel
 * @Description:
 * @Author: yyw
 * @Date: 2021/1/4
 * @Time: 17:20
 */
public class BaseViewModel extends AndroidViewModel implements LifecycleObseverImp {

    private BaseViewEventLiveData mUiStatusChangeLiveData;

    private CompositeDisposable mCompositeDisposable;

    private static final String TAG = "BaseViewModel";

    public BaseViewModel(@NonNull Application application) {
        super(application);
    }


    @Override
    protected void onCleared() {
        super.onCleared();
    }

    /**
     * 添加订阅
     *
     * @param observable 已经添加订阅的集合
     */
    public void addDisposable(Disposable observable) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(observable);
    }

    private void cancelAllDisposable() {
        if (mCompositeDisposable != null) {
            mCompositeDisposable.clear();
        }
    }

    /**
     * getBaseErrorConsume 不为null的情况下，默认方法addSubscribe（）可以自动提示业务报错信息
     */
    public <T> void addSubscribe(@NonNull Observable<T> observable, @NonNull Consumer<T> consumer) {
        addSubscribe(observable, consumer, true);
    }

    public <T> void addSubscribe(@NonNull Observable<T> observable, @NonNull Consumer<T> consumer, boolean isShowLoading) {
        if (getBaseErrorConsume() != null) {
            addSubscribe(observable, consumer, getBaseErrorConsume());
        } else {
            addDisposable(observable
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(consumer));
        }
    }

    public <T> void addSubscribe(@NonNull Observable<T> observable, @NonNull Consumer<? super T> onNext, @NonNull Consumer<? super Throwable> onError) {
        addDisposable(observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(onNext, onError));
    }

    @Override
    public void onCreat() {

    }

    @Override
    public void onResume() {
        Log.i(TAG, "onResume:==>");
    }

    @Override
    public void onPause() {
        Log.i(TAG, "onPause:===> ");
    }

    @Override
    public void onFinish() {
        Log.i(TAG, "onFinish:===>");
        cancelAllDisposable();
    }

    /**
     * @return 获取baseview 事件LiveData
     */
    public BaseViewEventLiveData getBaseViewEventLiveData() {
        if (mUiStatusChangeLiveData == null) {
            mUiStatusChangeLiveData = new BaseViewEventLiveData();
        }
        return mUiStatusChangeLiveData;
    }

    /**
     * 注册事件者，显示loading
     */
    public void showLoading() {
        Log.i("LiveData", "showLoading: ");
        mUiStatusChangeLiveData.getShowLoadingEvent().setValue(true);
    }

    /**
     * 注册事件者，显示loading
     */
    public void showEmptyView() {
        mUiStatusChangeLiveData.getEmptyViewEvent().setValue(true);
    }


    /**
     * 注册事件者，取消所有状态view,显示原始视图
     */
    public void dismissView() {
        Log.i("LiveData", "dismissView: ");
        mUiStatusChangeLiveData.getDismissViewEvent().setValue(true);
    }


    /**
     * 跳转界面
     *
     * @param path 界面地址
     */
    public void jumpPage(@NonNull String path) {
        if (!TextUtils.isEmpty(path)) {
            mUiStatusChangeLiveData.getJumpPagePathEvent().setValue(path);
        }
    }

    /**
     * 跳转界面
     *
     * @param path   界面地址
     * @param bundle 跳转界面参数
     */
    public void jumpPage(@NonNull String path, @NonNull Bundle bundle) {
        if (!TextUtils.isEmpty(path)) {
            bundle.putString(Constants.JUMP_PAGE_PATH_KEY, path);
            mUiStatusChangeLiveData.getJumpPageParamsEvent().setValue(bundle);
        }
    }


    /**
     * 不为null的情况下，默认方法addSubscribe（）可以自动提示业务报错信息
     *
     * @return 是否返回公共错误处理Consumer
     */
    public Consumer<? super Throwable> getBaseErrorConsume() {
        return null;
    }
}
