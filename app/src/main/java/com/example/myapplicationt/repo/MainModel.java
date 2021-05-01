package com.example.myapplicationt.repo;

import com.example.baseadapter.bean.MultiItemBean;
import com.example.myapplicationt.bean.PicItemBean;
import com.example.myapplicationt.fragment.dummy.DummyItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
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


    /**
     * Model只提供获取的数据，具体数据方式，根据业务实现
     * <p>
     * 获取首页列表数据
     *
     * @return
     */
    public Observable<List<MultiItemBean>> getItemDatas() {
        return Observable.timer(2, TimeUnit.SECONDS)
                .flatMap(new Function<Long, ObservableSource<List<MultiItemBean>>>() {
                    @Override
                    public ObservableSource<List<MultiItemBean>> apply(@NonNull Long aLong) throws Exception {
                        List<MultiItemBean> itemDatas = new ArrayList<>();
                        for (int i = 0; i < new Random().nextInt(10); i++) {
                            if (new Random().nextInt() % 2 == 0) {
                                itemDatas.add(new PicItemBean());
                            } else {
                                itemDatas.add(new DummyItem(new Random().nextInt(10) + "", "我是老司机，" + new Random().nextInt() + "号", "开拖拉机的司机"));
                            }
                        }
                        return Observable.just(itemDatas);
                    }
                });
    }

}
