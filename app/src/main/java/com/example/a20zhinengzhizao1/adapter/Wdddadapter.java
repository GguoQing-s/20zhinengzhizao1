package com.example.a20zhinengzhizao1.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.a20zhinengzhizao1.R;
import com.example.a20zhinengzhizao1.bean.Wddd;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Wdddadapter extends BaseAdapter {
    private List<Wddd> myzf;
    private Context context;

    public Wdddadapter(Context context, List<Wddd> myzf) {
        this.context = context;
        this.myzf = myzf;
    }

    @Override
    public int getCount() {
        return myzf.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Wddd dzf = myzf.get(position);
        View view = View.inflate(context, R.layout.wddd_item, null);
        ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.clmc.setText("    车辆名称："+dzf.getName());
        viewHolder.je.setText("    金额："+dzf.getJine());
        viewHolder.sj.setText("    时间："+dzf.getTine());
        return view;
    }

    static
    class ViewHolder {
        @BindView(R.id.clmc)
        TextView clmc;
        @BindView(R.id.je)
        TextView je;
        @BindView(R.id.sj)
        TextView sj;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
