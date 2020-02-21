package com.example.a20zhinengzhizao1.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.a20zhinengzhizao1.R;
import com.example.a20zhinengzhizao1.bean.Clkc;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Jcadapter extends BaseAdapter {
    private List<Clkc> mjc;
    private Context context;

    public interface SetData {
        void setdata(int position, String name, String clxh, String jb, String cs, String lx,
                     String hbbz, String sssj, String jg, String sl, String sms, String cspz, String video, String image);
    }

    public SetData data;

    public void SetData(SetData data) {
        this.data = data;
    }

    public Jcadapter(Context context, List<Clkc> mjc) {
        this.context = context;
        this.mjc = mjc;
    }

    @Override
    public int getCount() {
        return mjc.size();
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        final Clkc jc = mjc.get(position);
        View view = View.inflate(context, R.layout.jc_item, null);
        ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.clxh.setText("车辆型号：" + jc.getClxh());
        viewHolder.jb.setText("级别：" + jc.getJb());
        viewHolder.cs.setText("厂商：" + jc.getCs());
        viewHolder.lx.setText("能源类型：" + jc.getLx());
        viewHolder.zb.setText("环保标准：" + jc.getHbbz());
        viewHolder.sj.setText("上市时间：" + jc.getSssj());
        viewHolder.jg.setText("价格：" + jc.getJg());
        viewHolder.sl.setText("数量：" + jc.getSl());
        viewHolder.beijing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data.setdata(position,jc.getName(),jc.getClxh(),jc.getJb(),jc.getCs(),jc.getLx()
                ,jc.getHbbz(),jc.getSssj(),jc.getJg(),jc.getSl(),jc.getSms(),jc.getCspz(),
                        jc.getVideo(),jc.getImage());
            }
        });
        return view;
    }

    static
    class ViewHolder {
        @BindView(R.id.clxh)
        TextView clxh;
        @BindView(R.id.jb)
        TextView jb;
        @BindView(R.id.cs)
        TextView cs;
        @BindView(R.id.lx)
        TextView lx;
        @BindView(R.id.zb)
        TextView zb;
        @BindView(R.id.sj)
        TextView sj;
        @BindView(R.id.jg)
        TextView jg;
        @BindView(R.id.sl)
        TextView sl;
        @BindView(R.id.beijing)
        LinearLayout beijing;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
