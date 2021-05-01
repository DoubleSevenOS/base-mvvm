package com.example.myapplicationt;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.myapplicationt.repo.MainModel;
import com.android.base.viewmodel.BaseViewModel;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * 创建者 ：  huyangyang962
 * 创建日期： 2021/3/22 11:21 AM
 * 描述：
 */
public class MainViewModel extends BaseViewModel {
    String TAG = "checkBox";

    public MutableLiveData<Boolean> isCheckout = new MutableLiveData(true);

    public MutableLiveData<Integer> progressNum = new MutableLiveData();

    public MutableLiveData<String> data = new MutableLiveData();

    public MainModel mainModel;

    public MainViewModel(@NonNull Application application) {
        super(application);
        mainModel = new MainModel();
    }


    public void checkListener() {
        Log.i(TAG, "checkListener: ");
        jumpPage("com.example.myapplicationt.MainActivity2");
    }


    public void getData() {
        showLoading();
        Log.i("getData", "accept: ===getData==>");

        Observable<String> observable = mainModel.getData();
        observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        Log.i("getData", "getData =====>" + s);
                        data.setValue(s);
                    }
                });

//
//        addSubscribe(mainModel.getData(), new Consumer<String>() {
//            @Override
//            public void accept(String s) throws Exception {
//                Log.i("getData", "getData: s==>" + s);
//                dismissView();
//                data.setValue(s);
//            }
//        }, new Consumer<Throwable>() {
//            @Override
//            public void accept(Throwable throwable) throws Exception {
//                showEmptyView();
//            }
//        });

    }

    public void getListDatas() {
//        showLoading();
        addSubscribe(mainModel.getListData(), new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Log.i("getData", "getListDatas: =====>" + s);
                data.setValue(s);
                dismissView();
            }
        });
    }
}
