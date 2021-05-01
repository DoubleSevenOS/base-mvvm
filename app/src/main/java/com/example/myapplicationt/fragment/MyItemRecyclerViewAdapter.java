package com.example.myapplicationt.fragment;

import android.widget.TextView;

import com.example.baseadapter.BaseMultiAdapter;
import com.example.baseadapter.BaseViewHolder;
import com.example.baseadapter.bean.MultiItemBean;
import com.example.myapplicationt.R;
import com.example.myapplicationt.bean.PicItemBean;
import com.example.myapplicationt.fragment.dummy.DummyItem;

import java.util.List;

public class MyItemRecyclerViewAdapter extends BaseMultiAdapter<MultiItemBean, BaseViewHolder> {

    public MyItemRecyclerViewAdapter(List<MultiItemBean> itemDatas) {
        super(itemDatas);
        addItemType(DummyItem.TEXT_TYPE, R.layout.fragment_act_item);
        addItemType(PicItemBean.IMG_TYPE, R.layout.fragment_act_img_item);
    }

    @Override
    protected void onBindViewHolder(BaseViewHolder holder, MultiItemBean itemData, int position) {
        if (itemData instanceof DummyItem) {
            DummyItem dummyItem = (DummyItem) itemData;
            TextView item_number = holder.getView(R.id.item_number);
            TextView content = holder.getView(R.id.content);
            content.setText(dummyItem.details);
            item_number.setText("position=>" + position + "," + dummyItem.id + "," + dummyItem.content);
        }
    }


}