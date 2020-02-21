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
import com.example.a20zhinengzhizao1.bean.TzItem;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MsgListAdapter extends ArrayAdapter<TzItem> {
    public MsgListAdapter(@NonNull Context context, int resource, @NonNull List objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(getContext()).inflate(R.layout.notifyitem, null);
        ViewHolder holder=new ViewHolder(convertView);
        TzItem item = getItem(position);
        holder.idnumber.setText(item.getId()+"");
        holder.info.setText(item.getMessages().get(item.getMessages().size()-1).getContent());
        holder.time.setText(item.getTime());
        TextView icon=convertView.findViewById(R.id.icon);
        if (item.isRead()){
          icon.setVisibility(View.INVISIBLE);
        }
        return convertView;
    }

    static
    class ViewHolder {
        @BindView(R.id.idnumber)
        TextView idnumber;
        @BindView(R.id.info)
        TextView info;
        @BindView(R.id.time)
        TextView time;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
