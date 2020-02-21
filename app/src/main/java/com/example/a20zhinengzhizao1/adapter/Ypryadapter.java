package com.example.a20zhinengzhizao1.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.a20zhinengzhizao1.R;
import com.example.a20zhinengzhizao1.bean.Ypry;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Ypryadapter extends BaseAdapter {
    private List<Ypry> mypry;
    private Context context;

    public interface  SetData{
        void setdata(int position, String name);
    }
    public SetData data;
    public void SetData(SetData data)
    {
        this.data=data;
    }
    public Ypryadapter(Context context, List<Ypry> mypry) {
        this.context = context;
        this.mypry = mypry;
    }

    @Override
    public int getCount() {
        return mypry.size();
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
        final Ypry ypry = mypry.get(position);
        View view = View.inflate(context, R.layout.ypry_item1, null);
        ViewHolder viewHolder = new ViewHolder(view);
        String[] aa= ypry.getGw().split("-");
        viewHolder.bianhao.setText(ypry.getBianhao());
        viewHolder.name1.setText(ypry.getName());
        viewHolder.name2.setText(aa[0]);
        viewHolder.beijing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data.setdata(position,ypry.getName());
            }
        });
        return view;
    }

    static
    class ViewHolder {
        @BindView(R.id.bianhao)
        TextView bianhao;
        @BindView(R.id.name1)
        TextView name1;
        @BindView(R.id.name2)
        TextView name2;
        @BindView(R.id.beijing)
        LinearLayout beijing;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
