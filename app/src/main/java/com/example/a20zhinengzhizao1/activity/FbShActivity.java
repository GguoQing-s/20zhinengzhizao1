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

public class FbShActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.content2)
    TextView content2;
    @BindView(R.id.content3)
    TextView content3;
    @BindView(R.id.iv_fb)
    ImageView ivFb;
    @BindView(R.id.sh)
    ImageView sh;
    @BindView(R.id.history)
    ImageView history;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fbsh);
        ButterKnife.bind(this);
            ivFb.setOnClickListener(this);
            sh.setOnClickListener(this);
            history.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_fb:
                startActivity(new Intent(this,FbzpActivity.class));
                break;
            case R.id.sh:
                startActivity(new Intent(this,ShActivity.class));
                break;
            case R.id.history:
                startActivity(new Intent(this,HistoryActivity.class));
                break;
        }
    }
}
