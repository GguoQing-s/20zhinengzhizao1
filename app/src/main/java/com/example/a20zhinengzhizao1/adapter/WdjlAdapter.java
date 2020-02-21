package com.example.a20zhinengzhizao1.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import com.example.a20zhinengzhizao1.R;
import com.example.a20zhinengzhizao1.bean.WdjlInfo;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WdjlAdapter extends ArrayAdapter<WdjlInfo> {


    public WdjlAdapter(@NonNull Context context, int resource, @NonNull List<WdjlInfo> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(getContext()).inflate(R.layout.jl_item, null);
        ViewHolder holder=new ViewHolder(convertView);
        WdjlInfo info = getItem(position);
        holder.name.setText(info.getName());
        holder.bz.setText(info.getBz());
        holder.time.setText(info.getTime());
        return convertView;
    }

    static
    class ViewHolder {
        @BindView(R.id.name)
        TextView name;
        @BindView(R.id.bz)
        TextView bz;
        @BindView(R.id.time)
        TextView time;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
