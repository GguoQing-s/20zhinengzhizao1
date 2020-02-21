package com.example.a20zhinengzhizao1.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.a20zhinengzhizao1.R;

import java.util.List;
import java.util.Map;

public class Ejldadapter1 extends BaseExpandableListAdapter {
    private List<String> fu;
    private Map<String,List<String>> zi;
    public Ejldadapter1(List<String> fu,Map<String,List<String>> zi)
    {
        this.fu=fu;
        this.zi=zi;
    }
    @Override
    public int getGroupCount() {
        return fu.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        List<String> z = zi.get(fu.get(groupPosition));
        return z.size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return null;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return null;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.ejldfu1,null,false);
        ImageView imageView = convertView.findViewById(R.id.jt1);
        TextView textView = convertView.findViewById(R.id.fu1);
        textView.setText(fu.get(groupPosition));
        if (isExpanded)
        {
            imageView.setImageResource(R.drawable.j2);
        }else {
            imageView.setImageResource(R.drawable.j3);
        }
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        List<String> z = zi.get(fu.get(groupPosition));
        String zz  =z.get(childPosition);
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.ejldzi1,null,false);
        TextView textView = convertView.findViewById(R.id.zi1);
        textView.setText("供应商："+zz);
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
