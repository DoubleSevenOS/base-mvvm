package com.example.baseadapter;

import android.util.SparseArray;
import android.view.ViewGroup;

import com.example.baseadapter.bean.MultiItemBean;

import java.util.List;

/**
 * 创建者 ：  huyangyang962
 * 创建日期： 2021/3/26 9:52 AM
 * 描述： 多种类型的Item适配器。注意，数据源 bean 需要继承 MultiItemBean，
 * 需要注意的点添加 ItemType时候 。 multiType需要和layouId 记得对应起来。
 */
public abstract class BaseMultiAdapter<D extends MultiItemBean, VH extends BaseViewHolder> extends BaseRecyclerViewAdapter<D, VH> {

    private SparseArray<Integer> mLayouIds;

    public BaseMultiAdapter(List<D> itemDatas) {
        super(0, itemDatas);
        mLayouIds = new SparseArray();
    }


    @Override
    public int getItemViewType(int position) {
        return getItemBean(position).getItemMultiType();
    }

    /**
     * 添加某种类型item
     *
     * @param type
     * @param layouId
     */
    protected void addItemType(int type, int layouId) {
        mLayouIds.put(type, layouId);
    }

    @Override
    protected VH createDefViewHolder(ViewGroup parent, int viewType) {
        return createBaseViewHolder(parent, getLayoutId(viewType));
    }

    private int getLayoutId(int type) {
        return mLayouIds.get(type);
    }


}
