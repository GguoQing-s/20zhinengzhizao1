package com.example.a20zhinengzhizao1.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.a20zhinengzhizao1.R;
import com.example.a20zhinengzhizao1.sql.YlInfo;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class YlAdapter extends ArrayAdapter<YlInfo> {
    public YlAdapter(@NonNull Context context, int resource, @NonNull List<YlInfo> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(getContext()).inflate(R.layout.yl_item, null);
        ViewHolder viewHolder=new ViewHolder(convertView);
        YlInfo item = getItem(position);
        byte[] photo = item.getYlPhoto();
        Bitmap bitmap= BitmapFactory.decodeByteArray(photo,0,photo.length);
        viewHolder.image.setImageBitmap(bitmap);
        viewHolder.name.setText("姓名："+item.getYlName());
        viewHolder.price.setText("单价："+item.getPrice());
        return convertView;
    }

    static
    class ViewHolder {
        @BindView(R.id.name)
        TextView name;
        @BindView(R.id.image)
        ImageView image;
        @BindView(R.id.price)
        TextView price;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
