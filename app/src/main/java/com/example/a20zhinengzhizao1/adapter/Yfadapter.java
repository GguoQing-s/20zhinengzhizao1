package com.example.a20zhinengzhizao1.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.a20zhinengzhizao1.R;
import com.example.a20zhinengzhizao1.bean.Yf;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Yfadapter extends BaseAdapter {
    private List<Yf> myf;
    private Context context;

    public Yfadapter(Context context, List<Yf> myf) {
        this.context = context;
        this.myf = myf;
    }

    @Override
    public int getCount() {
        return myf.size();
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
        Yf sc = myf.get(position);
        View view = View.inflate(context, R.layout.sc_item, null);
        final ViewHolder viewHolder = new ViewHolder(view);
        String[] aa= sc.getName().split("-");
        String[] bb= sc.getChengshi().split("-");
        String[] cc= sc.getName1().split("-");
        viewHolder.name.setText(aa[0]);
        viewHolder.type.setText(bb[1]);
        viewHolder.name1.setText(cc[0]);
        viewHolder.chengshi.setText(bb[0]);
        viewHolder.yaoqiu.setText(sc.getYaoqiu());
        viewHolder.xiuli.setText(aa[1]);
        viewHolder.xinzi.setText(sc.getXinzi() + "元");
        viewHolder.email.setText(sc.getEmail());
        viewHolder.time.setText(sc.getTime());
        viewHolder.scsj.setText(sc.getTime1());
        return view;
    }

    static
    class ViewHolder {
        @BindView(R.id.name)
        TextView name;
        @BindView(R.id.type)
        TextView type;
        @BindView(R.id.name1)
        TextView name1;
        @BindView(R.id.yaoqiu)
        TextView yaoqiu;
        @BindView(R.id.chengshi)
        TextView chengshi;
        @BindView(R.id.xiuli)
        TextView xiuli;
        @BindView(R.id.xinzi)
        TextView xinzi;
        @BindView(R.id.email)
        TextView email;
        @BindView(R.id.time)
        TextView time;
        @BindView(R.id.scsj)
        TextView scsj;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
