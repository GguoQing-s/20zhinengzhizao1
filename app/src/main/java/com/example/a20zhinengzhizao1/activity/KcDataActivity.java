package com.example.a20zhinengzhizao1.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


import com.example.a20zhinengzhizao1.R;
import com.example.a20zhinengzhizao1.adapter.HistoryAdapter;
import com.example.a20zhinengzhizao1.sql.HistoryInfor;
import com.example.a20zhinengzhizao1.sql.YlInfor;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class KcDataActivity extends AppCompatActivity {
    @BindView(R.id.image)
    ImageView image;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.image2)
    ImageView image2;
    @BindView(R.id.ylname)
    TextView ylname;
    @BindView(R.id.bh)
    TextView bh;
    @BindView(R.id.xh)
    TextView xh;
    @BindView(R.id.count)
    TextView count;
    @BindView(R.id.location)
    TextView location;
    @BindView(R.id.lv)
    ListView lv;
    private List<HistoryInfor> mlist;
    private HistoryAdapter adapter;
    private YlInfor infor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);
        ButterKnife.bind(this);
        final String name=getIntent().getStringExtra("name");
        List<YlInfor> list = LitePal.findAll(YlInfor.class);
        image2.setImageResource(R.drawable.setting_icon);
        for (int i=0;i<list.size();i++){
            if (name.equals(list.get(i).getName())){
                infor=list.get(i);
                Log.d("11111111111111", "onCreate: "+list.size()+"------------"+name);
                break;
            }
        }

        bh.setText(infor.getBh()+"");
        xh.setText(infor.getXh()+"");
        count.setText(infor.getCount()+"");
        location.setText(infor.getLocation());
        ylname.setText(infor.getName());


        mlist = new ArrayList<>();
        List<HistoryInfor> infors = LitePal.findAll(HistoryInfor.class);
        for (int i = 0; i < infors.size(); i++) {
            HistoryInfor infor = infors.get(i);
            if (infor.getCount() != 0) {
                mlist.add(infor);
            }
        }
        adapter = new HistoryAdapter(this, 0, mlist);
        lv.setAdapter(adapter);

        image2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(KcDataActivity.this,YzActivity.class);
                intent.putExtra("name",name);
                startActivity(intent);
            }
        });

    }



}
