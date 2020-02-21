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
import com.example.a20zhinengzhizao1.sql.YlInfor;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AlarmAdapter extends ArrayAdapter<YlInfor> {
    public AlarmAdapter(@NonNull Context context, int resource, @NonNull List objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(getContext()).inflate(R.layout.alarm_item, null);
        ViewHolder viewHolder=new ViewHolder(convertView);
        YlInfor infor = getItem(position);
        viewHolder.name.setText(infor.getName());
        viewHolder.count.setText(infor.getCount()+"");
        viewHolder.xh.setText(infor.getXh()+"");
        return convertView;
    }

    static
    class ViewHolder {
        @BindView(R.id.name)
        TextView name;
        @BindView(R.id.xh)
        TextView xh;
        @BindView(R.id.count)
        TextView count;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
