package com.yiguo.recordinganimation.UI.adapter;

import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.yiguo.recordinganimation.R;

public class ItemViewHolder extends BaseViewHolder {
    public TextView tvName;
    public ItemViewHolder(View view) {
        super(view);
        tvName = (TextView) view.findViewById(R.id.tv_sort);
    }
}
