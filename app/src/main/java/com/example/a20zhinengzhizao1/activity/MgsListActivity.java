package com.example.a20zhinengzhizao1.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;


import com.example.a20zhinengzhizao1.AppClient;
import com.example.a20zhinengzhizao1.R;
import com.example.a20zhinengzhizao1.adapter.MsgListAdapter;
import com.example.a20zhinengzhizao1.bean.TzItem;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MgsListActivity extends AppCompatActivity {
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.content2)
    TextView content2;
    @BindView(R.id.content3)
    TextView content3;
    @BindView(R.id.lv)
    ListView lv;
    private List<TzItem> mlist;
    private AppClient appclient;
    private MsgListAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notice);
        ButterKnife.bind(this);
        appclient= (AppClient) getApplication();

        mlist=appclient.getTzItems();
        adapter=new MsgListAdapter(this,0,mlist);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mlist.get(position).setRead(true);
                Intent intent=new Intent(MgsListActivity.this, TalkActivity.class);
                intent.putExtra("id",position);
                startActivity(intent);
                adapter.notifyDataSetChanged();
            }
        });

    }
}
