package com.android.base.base;

import android.content.Context;

/**
 * @ClassName: com.android.base.base
 * @Description:IBaseView 代理处理类，自己项目中，可以自定义去实现相关操作。
 * @Author: hyy
 * @Date: 2021/6/16
 * @Time: 5:48 PM
 */
public abstract class BaseViewProxy implements IBaseView {
    protected Context mContext;

    public BaseViewProxy(Context context) {
        this.mContext = context;
    }

    /**
     * init 操作代理类，不做处理
     */
    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }
}
