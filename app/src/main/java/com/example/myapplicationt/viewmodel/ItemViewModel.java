package com.example.myapplicationt.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import io.reactivex.functions.Consumer;

import com.example.baseadapter.bean.MultiItemBean;
import com.example.myapplicationt.repo.MainModel;
import com.android.base.viewmodel.BaseViewModel;

import java.util.List;


/**
 * 创建者 ：  huyangyang962
 * 创建日期： 2021/3/24 1:45 PM
 * 描述：
 */
public class ItemViewModel extends BaseViewModel {

    private MainModel mainModel;
    private MutableLiveData<List<MultiItemBean>> itemDatas = new MutableLiveData<>();

    public ItemViewModel(@NonNull Application application) {
        super(application);
        this.mainModel = new MainModel();
    }


    public void getItemDatas() {
        showLoading();
        addSubscribe(mainModel.getItemDatas(), new Consumer<List<MultiItemBean>>() {
            @Override
            public void accept(List<MultiItemBean> multiItemBeans) throws Exception {
                dismissView();
                itemDatas.setValue(multiItemBeans);
            }
        });
    }


    public MutableLiveData<List<MultiItemBean>> getListDatas() {
        return itemDatas;
    }

}
