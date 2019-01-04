package com.yiguo.recordinganimation.GlideLoad;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.yiguo.recordinganimation.R;

/**
 * 原文地址: http://blog.csdn.net/guolin_blog/article/details/45586553
 * @author guolin
 */
public class ImageAdapter extends ArrayAdapter<String> {


    private final Context context1;

    public ImageAdapter(Context context, int resource, String[] objects) {
        super(context, resource, objects);
        context1 = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String url = getItem(position);
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_image_item, null);
            ImageView image = (ImageView) convertView.findViewById(R.id.image);
            CheckBox cb = (CheckBox) convertView.findViewById(R.id.cb);
            holder = new ViewHolder( );
            holder.imageView = image;
            holder.cb = cb;
            convertView.setTag(holder);
        } else {
              holder = (ViewHolder) convertView.getTag();
         }
        Glide.with(context1).load(url).into(holder.imageView);

        return convertView;
    }

    class ViewHolder {
        ImageView imageView;
        CheckBox cb;
    }





}
