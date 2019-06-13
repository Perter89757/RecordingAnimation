package com.yiguo.recordinganimation.UI.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;


import java.util.List;

public class SortAdapter extends BaseQuickAdapter<String, ItemViewHolder> {


    public SortAdapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(ItemViewHolder helper, String name) {
        helper.tvName.setText(name);
    }
}
