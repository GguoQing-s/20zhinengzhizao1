package com.example.a20zhinengzhizao1.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


import com.example.a20zhinengzhizao1.AppClient;
import com.example.a20zhinengzhizao1.R;
import com.example.a20zhinengzhizao1.adapter.Scadapter;
import com.example.a20zhinengzhizao1.bean.Sc;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class S_SCActivity extends AppCompatActivity {
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.change)
    ImageView change;
    @BindView(R.id.change1)
    ImageView change1;
    @BindView(R.id.listView)
    ListView listView;
    private AppClient mApp;
    private List<Sc> msc;
    private Scadapter mscadapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.s_zpscactivity);
        ButterKnife.bind(this);
        change.setVisibility(View.GONE);
        change1.setVisibility(View.GONE);
        inview();
        setadapter();
    }

    private void setadapter() {
        mscadapter = new Scadapter(this,msc);
        listView.setAdapter(mscadapter);
    }
    private void inview() {
        title.setText("招聘信息收藏");
        mApp = (AppClient) getApplication();
        msc = mApp.getMsc();
    }
}
