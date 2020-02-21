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
import com.example.a20zhinengzhizao1.fragment.S_Fragment_tjxx;
import com.example.a20zhinengzhizao1.fragment.S_Fragment_xscp;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class S_SCXXXActivity extends AppCompatActivity {
    @BindView(R.id.change)
    ImageView change;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.title1)
    TextView title1;
    @BindView(R.id.ztxx)
    TextView ztxx;
    @BindView(R.id.lin)
    LinearLayout lin;
    @BindView(R.id.xscp)
    TextView xscp;
    @BindView(R.id.tjcp)
    TextView tjcp;
    private FragmentTransaction ft;
    private String cjh,scxh,zt;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.s_scxxxactivity);
        ButterKnife.bind(this);
        cjh = getIntent().getStringExtra("cjh");
        scxh = getIntent().getStringExtra("scxh");
        zt = getIntent().getStringExtra("zt");
        ztxx.setText(cjh+"车间"+scxh+"生产线状态："+zt);
        title.setText("生产线信息");
        ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.lin,new S_Fragment_xscp(cjh,scxh));
        ft.commit();
    }

    @OnClick({R.id.change, R.id.xscp, R.id.tjcp})
    public void onViewClicked(View view) {
        ft = getSupportFragmentManager().beginTransaction();
        switch (view.getId()) {
            case R.id.change:
                finish();
                break;
            case R.id.xscp:
                ztxx.setVisibility(View.VISIBLE);
                ft.replace(R.id.lin,new S_Fragment_xscp(cjh,scxh));
                xscp.setBackgroundResource(R.drawable.cjbk2);
                tjcp.setBackgroundResource(R.drawable.cjbk1);
                break;
            case R.id.tjcp:
                ztxx.setVisibility(View.INVISIBLE);
                ft.replace(R.id.lin,new S_Fragment_tjxx());
                tjcp.setBackgroundResource(R.drawable.cjbk2);
                xscp.setBackgroundResource(R.drawable.cjbk1);
                break;
        }
        ft.commit();
    }
}
