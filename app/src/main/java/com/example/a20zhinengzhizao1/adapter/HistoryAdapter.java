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
import com.example.a20zhinengzhizao1.sql.HistoryInfor;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HistoryAdapter extends ArrayAdapter<HistoryInfor> {
    public HistoryAdapter(@NonNull Context context, int resource, @NonNull List objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(getContext()).inflate(R.layout.histotyitem, null);
        ViewHolder viewHolder=new ViewHolder(convertView);
        HistoryInfor infor = getItem(position);
        viewHolder.bz.setText(infor.getBz()+"");
        viewHolder.count.setText(infor.getCount()+"");
        viewHolder.name.setText(infor.getName());
        viewHolder.price.setText(infor.getPrice()+"");
        viewHolder.provider.setText(infor.getProvider());
        viewHolder.sy.setText(infor.getSurplus()+"");
        viewHolder.time.setText(infor.getTime());
        return convertView;
    }

    static
    class ViewHolder {
        @BindView(R.id.time)
        TextView time;
        @BindView(R.id.provider)
        TextView provider;
        @BindView(R.id.count)
        TextView count;
        @BindView(R.id.price)
        TextView price;
        @BindView(R.id.total)
        TextView total;
        @BindView(R.id.name)
        TextView name;
        @BindView(R.id.bz)
        TextView bz;
        @BindView(R.id.sy)
        TextView sy;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
