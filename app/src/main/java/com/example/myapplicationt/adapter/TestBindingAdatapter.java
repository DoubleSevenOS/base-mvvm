package com.example.myapplicationt.adapter;

import com.example.baseadapter.BaseDataBindingViewHolder;
import com.example.baseadapter.BaseRecyclerViewAdapter;
import com.example.myapplicationt.databinding.FragmentItemBinding;

import java.util.List;

/**
 * 创建者 ：  huyangyang962
 * 创建日期： 2021/3/25 11:51 AM
 * 描述：
 */
public class TestBindingAdatapter extends BaseRecyclerViewAdapter<String, BaseDataBindingViewHolder<FragmentItemBinding>> {

    public TestBindingAdatapter(int layoutId, List<String> itemDatas) {
        super(layoutId, itemDatas);
    }

    @Override
    protected void onBindViewHolder(BaseDataBindingViewHolder<FragmentItemBinding> holder, String itemData,int position) {
    }

//    @Override
//    protected void onBindViewHolder(BaseViewHolder holder, String itemData) {
//        FragmentItemBinding fragmentItemBinding = holder.getBinding();
////        TextView content = holder.getView(R.id.content);
////        content.setText(itemData);
//    }

}
