package com.example.a20zhinengzhizao1.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;


import com.example.a20zhinengzhizao1.R;
import com.example.a20zhinengzhizao1.bean.ShxxInfo;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShxxAdapter extends ArrayAdapter<ShxxInfo> {
    private OnCheckChangeListener listener;

    public void setListener(OnCheckChangeListener listener) {
        this.listener = listener;
    }

    public ShxxAdapter(@NonNull Context context, int resource, @NonNull List objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(getContext()).inflate(R.layout.shxxitem, null);
        ShxxInfo info = getItem(position);
        final ViewHolder holder=new ViewHolder(convertView);
        Button sh=convertView.findViewById(R.id.sh);
        holder.name.setText(info.getBh());
        holder.address.setText(info.getCom_address());
        holder.xz.setText(info.getGw());
        sh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.setData(1,position,true);
            }
        });
        holder.check.setChecked(info.isCheck());
        holder.check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.setData(2,position,holder.check.isChecked());
            }
        });
        return convertView;
    }

    static
    class ViewHolder {
        @BindView(R.id.name)
        TextView name;
        @BindView(R.id.address)
        TextView address;
        @BindView(R.id.xz)
        TextView xz;
        @BindView(R.id.check)
        CheckBox check;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    public interface OnCheckChangeListener{
        void setData(int lx, int position, boolean check);
    }
}
