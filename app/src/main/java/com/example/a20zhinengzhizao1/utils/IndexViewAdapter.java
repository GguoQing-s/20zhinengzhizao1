package com.example.a20zhinengzhizao1.utils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import com.example.a20zhinengzhizao1.R;
import com.example.a20zhinengzhizao1.sql.ProviderInfor;

import java.util.List;

public class IndexViewAdapter extends ArrayAdapter<ProviderInfor> {
    public IndexViewAdapter(@NonNull Context context, int resource, @NonNull List objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView= LayoutInflater.from(getContext()).inflate(R.layout.item,null);
        ProviderInfor infor = getItem(position);
        Log.d("kkkkkkkkkkkkkkk", "getView: "+infor.getPy_name());
        String word=infor.getPy_name().substring(0,1);
        String name=infor.getName();
        TextView tv_word=convertView.findViewById(R.id.tv_word);
        TextView tv_name=convertView.findViewById(R.id.tv_name);
        tv_name.setText(name);
        tv_word.setText(word);
        if (position==0){
            tv_word.setVisibility(View.VISIBLE);
        }else {
            if (infor.getPy_name().substring(0,1).equals(getItem(position-1).getPy_name().substring(0,1))){
                tv_word.setVisibility(View.GONE);
            }
        }
        return convertView;
    }
}
