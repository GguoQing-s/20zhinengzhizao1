package com.example.a20zhinengzhizao1.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.example.a20zhinengzhizao1.R;
import com.example.a20zhinengzhizao1.entity.KeyItem;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProviderSelectAdapter extends ArrayAdapter<KeyItem> {
    public ProviderSelectAdapter(@NonNull Context context, int resource, @NonNull List objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(getContext()).inflate(R.layout.select_item, null);
        ViewHolder viewHolder=new ViewHolder(convertView);
        KeyItem item = getItem(position);
        if (item.isCheck()){
            viewHolder.item.setBackgroundColor(Color.RED);
        }else {
            viewHolder.item.setBackgroundColor(Color.WHITE);
        }
        viewHolder.key.setText(item.getName());
        return convertView;
    }

    static
    class ViewHolder {
        @BindView(R.id.key)
        TextView key;
        @BindView(R.id.item)
        LinearLayout item;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
