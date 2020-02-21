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
import com.example.a20zhinengzhizao1.entity.ProviderStoreInfo;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProviderAdapter extends ArrayAdapter<ProviderStoreInfo> {
    public ProviderAdapter(@NonNull Context context, int resource, @NonNull List objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(getContext()).inflate(R.layout.provider_item, null);
        ViewHolder viewHolder=new ViewHolder(convertView);
        ProviderStoreInfo info = getItem(position);
        viewHolder.name.setText("名称："+info.getProviderName());
        viewHolder.address.setText("地址："+info.getAddress());
        viewHolder.product.setText("产品："+info.getProviderName());

        return convertView;
    }

    static
    class ViewHolder {
        @BindView(R.id.name)
        TextView name;
        @BindView(R.id.address)
        TextView address;
        @BindView(R.id.product)
        TextView product;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
