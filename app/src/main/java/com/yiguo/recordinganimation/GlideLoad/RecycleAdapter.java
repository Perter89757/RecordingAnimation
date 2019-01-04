package com.yiguo.recordinganimation.GlideLoad;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;

import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.squareup.picasso.Picasso;
import com.yiguo.recordinganimation.R;

/**
 * Created by huang_yanhui on 2018/12/5.
 */
public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.ViewHolder> {

    private final Context context1;
    String[] urlList;

    public RecycleAdapter(Context context, String[] urlList) {
        context1 = context;
        this.urlList = urlList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View convertView = LayoutInflater.from(context1).inflate(R.layout.item_image_item,parent, false);
        ViewHolder viewHolder = new ViewHolder(convertView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //Glide.with(context1).load(urlList[position]).into(holder.imageView);
        Picasso.with(context1).load(urlList[position]).into(holder.imageView);
        SimpleTarget simpleTarget = new SimpleTarget() {

            @Override
            public void onResourceReady(Object resource, GlideAnimation glideAnimation) {

            }

        };
    }


    @Override
    public int getItemCount() {
        return urlList.length;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public final ImageView imageView;
        public final CheckBox cb;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.image);
            cb = (CheckBox) itemView.findViewById(R.id.cb);
        }
    }
}
