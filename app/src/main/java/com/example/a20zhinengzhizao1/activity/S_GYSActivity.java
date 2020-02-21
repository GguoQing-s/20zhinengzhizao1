package com.example.a20zhinengzhizao1.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.a20zhinengzhizao1.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class S_GYSActivity extends AppCompatActivity {
    @BindView(R.id.jia1)
    TextView jia1;
    @BindView(R.id.jia2)
    TextView jia2;
    @BindView(R.id.ghywcx)
    TextView ghywcx;
    @BindView(R.id.change)
    ImageView change;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.gystj)
    TextView gystj;
    @BindView(R.id.title1)
    TextView title1;
    @BindView(R.id.gyslb)
    TextView gyslb;
    @BindView(R.id.gyscx)
    TextView gyscx;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.s_gysactivity);
        ButterKnife.bind(this);
        title.setText("供应商");
        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @OnClick({R.id.jia1, R.id.jia2, R.id.ghywcx, R.id.gystj,R.id.gyslb, R.id.gyscx})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.gystj:
                startActivity(new Intent(S_GYSActivity.this, S_GYSTJActivity.class));
                break;
            case R.id.jia1:
                startActivity(new Intent(S_GYSActivity.this, S_JCLActivity.class));
                break;
            case R.id.jia2:
                startActivity(new Intent(S_GYSActivity.this, S_JYActivity.class));
                break;
            case R.id.ghywcx:
                startActivity(new Intent(S_GYSActivity.this, S_GHCXActivity.class));
                break;
            case R.id.gyslb:
                startActivity(new Intent(S_GYSActivity.this, ProviderListActivity.class));
                break;
            case R.id.gyscx:
                startActivity(new Intent(S_GYSActivity.this, ProviderSelectActivity.class));
                break;
        }
    }
}
