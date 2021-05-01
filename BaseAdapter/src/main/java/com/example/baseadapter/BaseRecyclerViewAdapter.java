package com.example.baseadapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Constructor;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;

/**
 * 创建者 ：  huyangyang962
 * 创建日期： 2021/3/25 11:19 AM
 * 描述：
 */
public abstract class BaseRecyclerViewAdapter<D, VH extends BaseViewHolder> extends RecyclerView.Adapter<VH> {

    private List<D> mItemDatas;
    private int layoutId;

    public BaseRecyclerViewAdapter(int layoutId, List<D> itemDatas) {
        mItemDatas = itemDatas;
        this.layoutId = layoutId;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        VH vh = createDefViewHolder(parent, viewType);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        onBindViewHolder(holder, mItemDatas.get(position), position);
    }


    @Override
    public int getItemCount() {
        return mItemDatas.size();
    }

    /**
     * 创建默认的ViewHolder
     *
     * @param parent
     * @param viewType
     * @return 返回一个BaseViewHolder
     */
    protected VH createDefViewHolder(ViewGroup parent, int viewType) {
        return createBaseViewHolder(parent, layoutId);
    }

    /**
     * 创建BaseViewHolder
     *
     * @param parent
     * @param layoutId
     * @return
     */
    protected VH createBaseViewHolder(ViewGroup parent, int layoutId) {
        View itemView = getItemView(parent, layoutId);
        return creatBaseViewHolder(itemView);
    }

    /**
     * 创建ViewHolder
     *
     * @param view
     * @return
     */
    private VH creatBaseViewHolder(View view) {
        Class temp = getClass();
        //获取子类的，ViewHolder的泛型Class
        Class vhClz = getInstancedGenericVHClass(temp);
        VH vh;
        if (vhClz != null) {
            vh = creatGenericVHClass(vhClz, view);
        } else {
            vh = (VH) new BaseViewHolder(view);
        }
        return vh != null ? vh : (VH) new BaseViewHolder(view);
    }

    private Class getInstancedGenericVHClass(Class temp) {
        //获得带有泛型的父类
        Type adapterType = temp.getGenericSuperclass();
        // 如果当前是参数化泛型
        if (adapterType instanceof ParameterizedType) {
            //获取泛型数组
            Type[] types = ((ParameterizedType) adapterType).getActualTypeArguments();
            for (Type type : types) {
                //如果当前泛型是Class
                if (type instanceof Class) {
                    Class vhClass = (Class) type;
                    if (BaseViewHolder.class.isAssignableFrom(vhClass)) {
                        return vhClass;
                    }
                }
                //如果当前泛型是，是参数化类型
                else if (type instanceof ParameterizedType) {
                    Type rawType = ((ParameterizedType) type).getRawType();
                    if (rawType instanceof Class && BaseViewHolder.class.isAssignableFrom((Class) rawType)) {
                        return (Class) rawType;
                    }
                }
            }
        }
        return null;
    }

    private VH creatGenericVHClass(Class z, View view) {
        try {
            Constructor constructor = null;
            constructor = z.getDeclaredConstructor(View.class);
            constructor.setAccessible(true);
            return (VH) constructor.newInstance(view);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException("请不要修改BaseViewHolder默认构造函数");
        } catch (Exception e) {
        }
        return null;
    }


    protected abstract void onBindViewHolder(VH holder, D itemData, int position);


    private View getItemView(ViewGroup parent, int layoutIdRes) {
        return LayoutInflater.from(parent.getContext()).inflate(layoutIdRes, parent, false);
    }


    public List<D> getDatas() {
        return mItemDatas;
    }

    public D getItemBean(int position) {
        return getDatas().get(position);
    }

    /**
     * 添加数据源集合
     *
     * @param datas 数据源集合
     */
    public void addAll(Collection<? extends D> datas) {
        mItemDatas.addAll(datas);
        notifyItemRangeInserted(mItemDatas.size() - datas.size(), mItemDatas.size());
    }


    public void addHeaderView() {

    }
}
