package com.example.a20zhinengzhizao1.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;


import com.example.a20zhinengzhizao1.R;
import com.example.a20zhinengzhizao1.fragment.Fragment_Jbxx;
import com.example.a20zhinengzhizao1.fragment.Fragment_jllb;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WdjlActivity extends AppCompatActivity {
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.content2)
    TextView content2;
    @BindView(R.id.content3)
    TextView content3;
    @BindView(R.id.fragment)
    FrameLayout fragment;
    @BindView(R.id.jbxx)
    TextView jbxx;
    @BindView(R.id.jllb)
    TextView jllb;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wdjl);
        ButterKnife.bind(this);
        title.setText("人才市场——我的简历");
        replace(new Fragment_Jbxx());
        jbxx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replace(new Fragment_Jbxx());
            }
        });
        jllb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replace(new Fragment_jllb());
            }
        });
    }

    public void replace(Fragment fragment){
        FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment,fragment);
        transaction.commit();
    }


}
