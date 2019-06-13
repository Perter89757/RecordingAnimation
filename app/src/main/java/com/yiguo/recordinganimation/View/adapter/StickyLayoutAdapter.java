package com.yiguo.recordinganimation.View.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.yiguo.recordinganimation.R;

/**
 * Created by Linsa on 2018/1/2:11:21.
 * des: 创建相关LayoutHelper的使用
 */

public class StickyLayoutAdapter extends DelegateAdapter.Adapter<RecyclerView.ViewHolder> {

    private LayoutHelper mHelper;

    public StickyLayoutAdapter(LayoutHelper helper) {
        this.mHelper=helper;
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return mHelper;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_sticky_layout, parent, false);
        return new RecyclerViewItemHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {


    }

    @Override
    public int getItemCount() {
        return 1;
    }

    /**
     * 正常条目的item的ViewHolder
     */
    private class RecyclerViewItemHolder extends RecyclerView.ViewHolder {

        public ImageView iv_title;

        public RecyclerViewItemHolder(View itemView) {
            super(itemView);
            iv_title = (ImageView) itemView.findViewById(R.id.iv_title);
        }
    }
}
