package com.example.myapplicationt.repo;


import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

/**
 * 创建者 ：  huyangyang962
 * 创建日期： 2021/3/23 1:51 PM
 * 描述：
 */
public class MainModel {

    public Observable<String> getData() {
        return Observable.timer(10, TimeUnit.SECONDS)
                .flatMap(new Function<Long, ObservableSource<String>>() {
                    @Override
                    public ObservableSource<String> apply(@NonNull Long aLong) throws Exception {
                        return Observable.just("我是最新的网络数据");
                    }
                });
    }

    public Observable<String> getListData() {
        return Observable.timer(1, TimeUnit.SECONDS)
                .flatMap(new Function<Long, ObservableSource<String>>() {
                    @Override
                    public ObservableSource<String> apply(@NonNull Long aLong) throws Exception {
                        return Observable.just("我是列表的网络数据");
                    }
                });
    }


}
