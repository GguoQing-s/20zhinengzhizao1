package com.example.a20zhinengzhizao1.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.a20zhinengzhizao1.R;
import com.example.a20zhinengzhizao1.bean.Wxcjqb;
import com.example.a20zhinengzhizao1.bean.Wxcjywwx;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Ywxadapter extends BaseAdapter {
    private List<Wxcjywwx> mwxcjywwx;
    private Context context;

    public Ywxadapter(Context context, List<Wxcjywwx> mwxcjywwx) {
        this.context = context;
        this.mwxcjywwx = mwxcjywwx;
    }

    @Override
    public int getCount() {
        return mwxcjywwx.size();
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
        Wxcjywwx wxcjqb = mwxcjywwx.get(position);
        View view = View.inflate(context, R.layout.wxqb_item, null);
        ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.bh.setText("         车辆编号："+wxcjqb.getClbh());
        viewHolder.xh.setText("车辆型号："+wxcjqb.getClxh());
        viewHolder.wt.setText("车辆问题："+wxcjqb.getClwt());
        viewHolder.bx.setText("         报修时间："+wxcjqb.getBxsj());
        viewHolder.wx.setText("维修时间："+wxcjqb.getWxsj());
        viewHolder.zt.setText("是否维修："+wxcjqb.getZt());
        return view;
    }

    static
    class ViewHolder {
        @BindView(R.id.bh)
        TextView bh;
        @BindView(R.id.xh)
        TextView xh;
        @BindView(R.id.wt)
        TextView wt;
        @BindView(R.id.bx)
        TextView bx;
        @BindView(R.id.wx)
        TextView wx;
        @BindView(R.id.zt)
        TextView zt;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
