package com.example.a20zhinengzhizao1.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.a20zhinengzhizao1.R;
import com.example.a20zhinengzhizao1.bean.Noyw;

import java.util.List;
import java.util.Map;

public class Ejldadapter3 extends BaseExpandableListAdapter {
    private Map<String,List<Noyw>> listtype;
    private String[] fu={"存在业务","不存在业务"};
    public Ejldadapter3(Map<String,List<Noyw>> listtype)
    {
        this.listtype=listtype;
    }
    @Override
    public int getGroupCount() {
        return fu.length;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return fu.length;
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
        textView.setText(fu[groupPosition]);
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
        String f = fu[groupPosition];
        List<Noyw> gj = listtype.get(f);
        Noyw g = gj.get(childPosition);
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.ejldzi1,null,false);
        TextView textView = convertView.findViewById(R.id.zi1);
        textView.setText("供应商："+g.getName());
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
