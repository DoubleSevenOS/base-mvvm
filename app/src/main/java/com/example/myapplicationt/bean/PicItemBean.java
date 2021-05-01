package com.example.myapplicationt.bean;

import com.example.baseadapter.bean.MultiItemBean;

/**
 * 创建者 ：  huyangyang962
 * 创建日期： 2021/3/26 2:12 PM
 * 描述：
 */
public class PicItemBean implements MultiItemBean {
    public static final int IMG_TYPE = 976;

    @Override
    public int getItemMultiType() {
        return IMG_TYPE;
    }
}
