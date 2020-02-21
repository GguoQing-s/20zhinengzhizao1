package com.example.a20zhinengzhizao1.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.example.a20zhinengzhizao1.R;
import com.example.a20zhinengzhizao1.bean.Zhaopin;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Zhaopinadapter extends BaseAdapter {
    private List<Zhaopin> mzhaopin;
    private Context context;

    public interface SetData {
        void setdata(boolean sc, int lx, int position, String name, String type, String name1, String yaoqiu, String chengshi, String xiuli, String xinzi, String email, String time);
    }

    public SetData data;

    public void SetData(SetData data) {
        this.data = data;
    }

    public Zhaopinadapter(Context context, List<Zhaopin> mzhaopins) {
        this.context = context;
        this.mzhaopin = mzhaopins;
    }

    @Override
    public int getCount() {
        return mzhaopin.size();
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
        final Zhaopin zhaopin = mzhaopin.get(position);
        View view = View.inflate(context, R.layout.zhaopin_item, null);
        final ViewHolder viewHolder = new ViewHolder(view);
        String[] aa= zhaopin.getName().split("-");
        String[] bb= zhaopin.getChengshi().split("-");
        String[] cc= zhaopin.getName1().split("-");
        viewHolder.name.setText(aa[0]);
        viewHolder.type.setText(bb[1]);
        viewHolder.name1.setText(cc[0]);
        viewHolder.chengshi.setText(bb[0]);
        viewHolder.yaoqiu.setText(zhaopin.getYaoqiu());
        viewHolder.xiuli.setText(aa[1]);
        viewHolder.xinzi.setText(zhaopin.getXinzi() + "元");
        viewHolder.email.setText(zhaopin.getEmail());
        viewHolder.time.setText(zhaopin.getTime());

        if (zhaopin.isSc())
        {
            viewHolder.sc.setText("已收藏");
        }else {
            viewHolder.sc.setText("收藏");
        }
        viewHolder.sc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if (zhaopin.isSc())
               {
                   data.setdata(false,1,position,zhaopin.getName(),zhaopin.getType(),zhaopin.getName1(),zhaopin.getYaoqiu(),zhaopin.getChengshi()
                           ,zhaopin.getXiuli(),zhaopin.getXinzi(),zhaopin.getEmail(),zhaopin.getTime());
               }else {
                   data.setdata(true,1,position,zhaopin.getName(),zhaopin.getType(),zhaopin.getName1(),zhaopin.getYaoqiu(),zhaopin.getChengshi()
                           ,zhaopin.getXiuli(),zhaopin.getXinzi(),zhaopin.getEmail(),zhaopin.getTime());
               }
            }
        });
        viewHolder.yp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data.setdata(true,2,position,zhaopin.getName(),zhaopin.getType(),zhaopin.getName1(),zhaopin.getYaoqiu(),zhaopin.getChengshi()
                        ,zhaopin.getXiuli(),zhaopin.getXinzi(),zhaopin.getEmail(),zhaopin.getTime());
            }
        });
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
        @BindView(R.id.sc)
        TextView sc;
        @BindView(R.id.yp)
        TextView yp;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
