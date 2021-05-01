package com.example.myapplicationt.fragment.dummy;

import com.example.baseadapter.bean.MultiItemBean;

/**
 * A dummy item representing a piece of content.
 */
public class DummyItem implements MultiItemBean {
    public static final int TEXT_TYPE = 738;
    public final String id;
    public final String content;
    public final String details;

    public DummyItem(String id, String content, String details) {
        this.id = id;
        this.content = content;
        this.details = details;
    }

    @Override
    public String toString() {
        return content;
    }

    @Override
    public int getItemMultiType() {
        return TEXT_TYPE;
    }
}