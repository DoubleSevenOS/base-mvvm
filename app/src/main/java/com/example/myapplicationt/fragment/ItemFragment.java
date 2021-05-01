package com.example.myapplicationt.fragment;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;

import com.example.baseadapter.bean.MultiItemBean;
import com.example.myapplicationt.BR;
import com.example.myapplicationt.R;
import com.example.myapplicationt.databinding.FragmentItemListBinding;
import com.example.myapplicationt.viewmodel.ItemViewModel;
import com.android.base.base.BaseDataBindingConfig;
import com.android.base.fragment.BaseDataBindFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * A fragment representing a list of Items.
 */
public class ItemFragment extends BaseDataBindFragment<FragmentItemListBinding, ItemViewModel> {

    private MyItemRecyclerViewAdapter myItemRecyclerViewAdapter;

    public ItemFragment() {

    }

    @NonNull
    @Override
    public BaseDataBindingConfig getDataBindingConfig() {
        myItemRecyclerViewAdapter = new MyItemRecyclerViewAdapter(new ArrayList<>());
        return new BaseDataBindingConfig(R.layout.fragment_item_list)
                .addBindingParam(BR.vm, mViewModel)
                .addBindingParam(BR.adapter, myItemRecyclerViewAdapter);
    }

    @NonNull
    @Override
    public Class<ItemViewModel> initViewModelClazz() {
        return ItemViewModel.class;
    }

    @Override
    public void initView() {
        mViewModel.getListDatas().observe(this, new Observer<List<MultiItemBean>>() {
            @Override
            public void onChanged(List<MultiItemBean> multiItemBeans) {
                myItemRecyclerViewAdapter.addAll(multiItemBeans);
            }
        });
    }

    @Override
    public void initData() {
        mViewModel.getItemDatas();
    }

    @Override
    protected boolean isRegistLoadService() {
        return true;
    }
}
