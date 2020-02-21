package com.example.a20zhinengzhizao1.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;


import com.example.a20zhinengzhizao1.R;
import com.example.a20zhinengzhizao1.entity.ContactInfor;

import java.util.List;

public class EmployerAdapter extends ArrayAdapter<ContactInfor> {
    private CheckListener listener;

    public void setListener(CheckListener listener) {
        this.listener = listener;
    }

    public EmployerAdapter(@NonNull Context context, int resource, @NonNull List objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView= LayoutInflater.from(getContext()).inflate(R.layout.contact_item,null);
        ContactInfor item = getItem(position);


        String name=item.getName();
        TextView tv_word=convertView.findViewById(R.id.tv_word);
        TextView tv_name=convertView.findViewById(R.id.tv_name);
        final CheckBox check=convertView.findViewById(R.id.check);
        String word=item.getPy().substring(0,1);
        tv_word.setText(word);
        Log.d("qqqqqqqqqqqqq", "getView: "+item.isVisiable());
        if (item.isVisiable()){
            check.setVisibility(View.VISIBLE);
        }
        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.checkChange(position);
            }
        });
        tv_name.setText(name);

        if (position==0){
            tv_word.setVisibility(View.VISIBLE);
        }else {

                if (item.getPy().substring(0,1).equals(getItem(position-1).getPy().substring(0,1))){
                    tv_word.setVisibility(View.GONE);

            }

        }
        return convertView;
    }
    public interface CheckListener{
        void checkChange(int position);
    }
}
