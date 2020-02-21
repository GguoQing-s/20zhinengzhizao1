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
import com.example.a20zhinengzhizao1.fragment.S_Fragment_dzf;
import com.example.a20zhinengzhizao1.fragment.S_Fragment_yzf;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class S_WDDDActivity extends AppCompatActivity {
    @BindView(R.id.change)
    ImageView change;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.title1)
    TextView title1;
    @BindView(R.id.dzf)
    TextView dzf;
    @BindView(R.id.v1)
    View v1;
    @BindView(R.id.v2)
    View v2;
    @BindView(R.id.yzf)
    LinearLayout yzf;
    @BindView(R.id.lin)
    LinearLayout lin;
    private FragmentTransaction ft;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.s_wdddactivity);
        ButterKnife.bind(this);
        title.setText("我的订单");
        ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.lin,new S_Fragment_dzf());
        ft.commit();
        v1.setVisibility(View.VISIBLE);
        v2.setVisibility(View.INVISIBLE);
    }

    @OnClick({R.id.change, R.id.dzf, R.id.yzf})
    public void onViewClicked(View view) {
        ft = getSupportFragmentManager().beginTransaction();
        switch (view.getId()) {
            case R.id.change:
                finish();
                break;
            case R.id.dzf:
                ft.replace(R.id.lin,new S_Fragment_dzf());
                v1.setVisibility(View.VISIBLE);
                v2.setVisibility(View.INVISIBLE);
                break;
            case R.id.yzf:
                ft.replace(R.id.lin,new S_Fragment_yzf());
                v2.setVisibility(View.VISIBLE);
                v1.setVisibility(View.INVISIBLE);
                break;
        }
        ft.commit();
    }
}
