package com.example.a20zhinengzhizao1.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.a20zhinengzhizao1.R;
import com.example.a20zhinengzhizao1.bean.Zhaopin1;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Zhaopinadapter1 extends BaseAdapter {
    private List<Zhaopin1> mzhaopin1;
    private Context context;

    public Zhaopinadapter1(Context context, List<Zhaopin1> mzhaopin1) {
        this.context = context;
        this.mzhaopin1 = mzhaopin1;
    }

    @Override
    public int getCount() {
        return mzhaopin1.size();
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
        Zhaopin1 zhaopin = mzhaopin1.get(position);
        View view = View.inflate(context, R.layout.zhaopin_item, null);
        ViewHolder viewHolder  =new ViewHolder(view);
        String[] aa= zhaopin.getName().split("-");
        String[] bb= zhaopin.getChengshi().split("-");
        String[] cc= zhaopin.getName1().split("-");
        viewHolder.name.setText(aa[0]);
        viewHolder.type.setText(bb[1]);
        viewHolder.name1.setText(cc[0]);
        viewHolder.chengshi.setText(bb[0]);
        viewHolder.yaoqiu.setText(zhaopin.getYaoqiu());
        viewHolder.xiuli.setText(aa[1]);
        viewHolder.xinzi.setText(zhaopin.getXinzi()+"元");
        viewHolder.email.setText(zhaopin.getEmail());
        viewHolder.time.setText(zhaopin.getTime());
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

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
