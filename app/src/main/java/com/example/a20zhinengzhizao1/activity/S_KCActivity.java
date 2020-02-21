package com.example.a20zhinengzhizao1.activity;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.a20zhinengzhizao1.R;
import com.example.a20zhinengzhizao1.fragment.S_Fragment_ck;
import com.example.a20zhinengzhizao1.fragment.S_Fragment_rk;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class S_KCActivity extends AppCompatActivity {


    @BindView(R.id.change)
    ImageView change;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.kc)
    TextView kc;
    @BindView(R.id.yj)
    TextView yj;
    @BindView(R.id.rk)
    TextView rk;
    @BindView(R.id.ck)
    TextView ck;
    @BindView(R.id.lin)
    LinearLayout lin;
    private FragmentTransaction ft;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.s_kcactivity);
        ButterKnife.bind(this);

        inview();
        ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.lin,new KcActivity());
        ft.commit();
    }

    private void inview() {
        title.setText("原料库存管理");
        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @OnClick({R.id.kc, R.id.yj, R.id.rk, R.id.ck})
    public void onViewClicked(View view) {
        ft = getSupportFragmentManager().beginTransaction();
        switch (view.getId()) {
            case R.id.kc:
                ft.replace(R.id.lin,new KcActivity());
                break;
            case R.id.yj:
                ft.replace(R.id.lin,new AlarmActivity());
                break;
            case R.id.rk:
                ft.replace(R.id.lin,new S_Fragment_rk());
                break;
            case R.id.ck:
                ft.replace(R.id.lin,new S_Fragment_ck());
                break;
        }
        ft.commit();
    }
}
