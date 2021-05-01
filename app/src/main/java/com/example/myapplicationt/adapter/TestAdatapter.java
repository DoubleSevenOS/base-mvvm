package com.example.myapplicationt.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.baseadapter.BaseRecyclerViewAdapter;
import com.example.baseadapter.BaseViewHolder;
import com.example.myapplicationt.R;

import java.util.List;

/**
 * 创建者 ：  huyangyang962
 * 创建日期： 2021/3/25 11:51 AM
 * 描述：
 */
public class TestAdatapter extends BaseRecyclerViewAdapter<String, BaseViewHolder> {
    public TestAdatapter(List<String> itemDatas) {
        super(R.layout.fragment_act_item, itemDatas);
    }

    @Override
    protected void onBindViewHolder(BaseViewHolder holder, String itemData,int position) {
        TextView content = holder.getView(R.id.content);
        content.setText(itemData);
    }

}
