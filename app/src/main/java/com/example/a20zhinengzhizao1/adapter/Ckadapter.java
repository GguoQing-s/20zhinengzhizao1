package com.example.a20zhinengzhizao1.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.a20zhinengzhizao1.R;
import com.example.a20zhinengzhizao1.bean.Ck;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Ckadapter extends BaseAdapter {
    private List<Ck> mck;
    private Context context;

    public interface SetData{
        void setdata(String name,String xh,String path);
    }
    public SetData data;
    public void SetData(SetData data)
    {
        this.data=data;
    }
    public Ckadapter(Context context, List<Ck> mck) {
        this.context = context;
        this.mck = mck;
    }

    @Override
    public int getCount() {
        return mck.size();
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
        final Ck ck = mck.get(position);
        View view = View.inflate(context, R.layout.ck_item, null);
        ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.ylmc.setText("原料名称：" + ck.getName());
        viewHolder.xh.setText("型号：" + ck.getXh());
        viewHolder.kcl.setText("库存量：" + ck.getShuliang());
        viewHolder.wz.setText("位置：" + ck.getWeizhi());
        viewHolder.ck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data.setdata(ck.getName(),ck.getXh(),ck.getPath());
            }
        });
        return view;
    }

    static
    class ViewHolder {
        @BindView(R.id.ylmc)
        TextView ylmc;
        @BindView(R.id.xh)
        TextView xh;
        @BindView(R.id.kcl)
        TextView kcl;
        @BindView(R.id.wz)
        TextView wz;
        @BindView(R.id.ck)
        TextView ck;
        @BindView(R.id.beijing)
        LinearLayout beijing;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
