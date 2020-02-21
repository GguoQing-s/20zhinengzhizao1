package com.example.a20zhinengzhizao1.adapter;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.example.a20zhinengzhizao1.R;
import com.example.a20zhinengzhizao1.bean.Jbxx;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Jbxxadapter extends BaseAdapter {
    private List<Jbxx> mjbxx;
    private Context context;

    public interface SetData {
        void setdata(int position, String name, int lx, String path);
    }

    public SetData data;

    public void SetData(SetData data) {
        this.data = data;
    }

    public Jbxxadapter(Context context, List<Jbxx> mjbxx) {
        this.context = context;
        this.mjbxx = mjbxx;
    }

    @Override
    public int getCount() {
        return mjbxx.size();
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
        final Jbxx jbxx = mjbxx.get(position);
        View view = View.inflate(context, R.layout.jbxx_item, null);
        ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.xz.setTextColor(Color.BLUE);
        //  .setImageResource(Integer.parseInt(jbxx.getImage()));
        Log.d("------", "getView: -----"+jbxx.getImage());
        viewHolder.tx.setImageBitmap(BitmapFactory.decodeFile(jbxx.getImage()));
        viewHolder.name.setText("姓名：" + jbxx.getName());
        viewHolder.csrq.setText("出生年月：" + jbxx.getShengqi());
        viewHolder.yl.setText("学历：" + jbxx.getXl());
        viewHolder.jl.setText("有无工作经历：" + jbxx.getJl());
        viewHolder.beijing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data.setdata(position,jbxx.getName(),1,jbxx.getImage());

            }
        });
        if (jbxx.isXz())
        {
            viewHolder.xz.setTextColor(Color.RED);
        }else {
            viewHolder.xz.setTextColor(Color.BLUE);
        }
        viewHolder.xz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data.setdata(position,jbxx.getName(),2,jbxx.getImage());
            }
        });
        return view;
    }

    static
    class ViewHolder {
        @BindView(R.id.tx)
        ImageView tx;
        @BindView(R.id.name)
        TextView name;
        @BindView(R.id.csrq)
        TextView csrq;
        @BindView(R.id.yl)
        TextView yl;
        @BindView(R.id.jl)
        TextView jl;
        @BindView(R.id.xz)
        TextView xz;
        @BindView(R.id.beijing)
        RelativeLayout beijing;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
