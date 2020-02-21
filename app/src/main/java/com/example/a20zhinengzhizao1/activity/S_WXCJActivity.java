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
import com.example.a20zhinengzhizao1.fragment.S_Fragment_bx;
import com.example.a20zhinengzhizao1.fragment.S_Fragment_rz;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class S_WXCJActivity extends AppCompatActivity {
    @BindView(R.id.change)
    ImageView change;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.title1)
    TextView title1;
    @BindView(R.id.lin)
    LinearLayout lin;
    @BindView(R.id.baixiu)
    TextView baixiu;
    @BindView(R.id.rizhi)
    TextView rizhi;
    private FragmentTransaction ft;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.s_wxcjactivity);
        ButterKnife.bind(this);
        title.setText("维修车间");
        ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.lin,new S_Fragment_bx());
        ft.commit();
    }
    @OnClick({R.id.change, R.id.baixiu, R.id.rizhi})
    public void onViewClicked(View view) {
        ft = getSupportFragmentManager().beginTransaction();
        switch (view.getId()) {
            case R.id.change:
                finish();
                break;
            case R.id.baixiu:
                ft.replace(R.id.lin,new S_Fragment_bx());
                baixiu.setBackgroundResource(R.drawable.cjbk2);
                rizhi.setBackgroundResource(R.drawable.cjbk1);
                break;
            case R.id.rizhi:
                ft.replace(R.id.lin,new S_Fragment_rz());
                rizhi.setBackgroundResource(R.drawable.cjbk2);
                baixiu.setBackgroundResource(R.drawable.cjbk1);
                break;
        }
        ft.commit();
    }
}
