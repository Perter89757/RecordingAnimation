package com.yiguo.recordinganimation.UI.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.yiguo.recordinganimation.R;

public class TwoItemViewHolder extends BaseViewHolder {
    TextView tvCity;
    ImageView avatar;
    TextView tvTitle;

    public TwoItemViewHolder(View itemView) {
        super(itemView);
        tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
        tvCity = (TextView) itemView.findViewById(R.id.tvCity);
        avatar = (ImageView) itemView.findViewById(R.id.ivAvatar);
    }
}
