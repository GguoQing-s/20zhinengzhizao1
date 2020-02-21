package com.example.a20zhinengzhizao1.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.a20zhinengzhizao1.AppClient;
import com.example.a20zhinengzhizao1.R;
import com.example.a20zhinengzhizao1.adapter.FragmentAdapter;
import com.example.a20zhinengzhizao1.bean.Count;
import com.example.a20zhinengzhizao1.bean.Gwc;
import com.example.a20zhinengzhizao1.fragment.S_Fragment_image;
import com.example.a20zhinengzhizao1.fragment.S_Fragment_video;
import com.example.a20zhinengzhizao1.net.VolleyLo;
import com.example.a20zhinengzhizao1.net.VolleyTo;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class S_XQYActivity extends AppCompatActivity {
    @BindView(R.id.change)
    ImageView change;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.mc)
    TextView mc;
    @BindView(R.id.jg)
    TextView jg;
    @BindView(R.id.kcl)
    TextView kcl;
    @BindView(R.id.sms)
    TextView sms;
    @BindView(R.id.cs)
    TextView cs;
    @BindView(R.id.jrgwc)
    TextView jrgwc;
    @BindView(R.id.dg)
    TextView dg;
    @BindView(R.id.title1)
    TextView title1;
    private List<Fragment> fragments;
    private String cm1, sms1, jg1,xh1;
    private String tp;
    private AppClient mApp;
    private List<Gwc> mgwc;
    private boolean is=true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.s_xqyactivity);
        ButterKnife.bind(this);
        inview();
        jiesho();
        addFragment();
    }
    private void huoqu() {
        VolleyTo volleyTo  =new VolleyTo();
        volleyTo.setUrl("get_vehiclea")
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        JSONArray jsonArray = jsonObject.optJSONArray("ROWS_DETAIL");
                        for (int i=0;i<jsonArray.length();i++)
                        {
                            JSONObject jsonObject1 = jsonArray.optJSONObject(i);
                            if (jsonObject1.optString("name").equals(cm1)&&
                                    jsonObject1.optString("clxh").equals(xh1))
                            {
                                kcl.setText("库存量：" + jsonObject1.optString("sl"));
                            }
                        }
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();
    }

    private void jiesho() {
        xh1 = getIntent().getStringExtra("clxh");
        cm1 = getIntent().getStringExtra("name");
        sms1 = getIntent().getStringExtra("sms");
        jg1 = getIntent().getStringExtra("jg");
        tp = getIntent().getStringExtra("image");

        mc.setText("名称：" + getIntent().getStringExtra("name"));
        jg.setText("价格：" + getIntent().getStringExtra("jg"));
        kcl.setText("库存量：" + getIntent().getStringExtra("sl"));
        sms.setText("说明书" + "\n" + "       " + getIntent().getStringExtra("sms"));
        cs.setText(getIntent().getStringExtra("cspz"));
    }

    private void addFragment() {
        fragments.add(new S_Fragment_image(getIntent().getStringExtra("image")));
        fragments.add(new S_Fragment_video(getIntent().getStringExtra("video")));
        viewPager.setAdapter(new FragmentAdapter(getSupportFragmentManager(), fragments));
        viewPager.setCurrentItem(0);
        viewPager.setOffscreenPageLimit(fragments.size());
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    private void inview() {
        title.setText("详情页");
        fragments = new ArrayList<>();
        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mApp = (AppClient) getApplication();
        mgwc = mApp.getMgwc();
        title1.setText("我的订单");
        title1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(S_XQYActivity.this,S_WDDDActivity.class));
            }
        });
    }

    @OnClick({R.id.jrgwc, R.id.dg})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.jrgwc:
                break;
            case R.id.dg:
                if (mgwc.size()>0)
                {

                    for (int i=0;i<mgwc.size();i++)
                    {
                        Gwc gwc = mgwc.get(i);
                        if (gwc.getName().equals(cm1))
                        {
                            gwc.setCount(gwc.getCount()+1);
                            is=false;
                        }
                    }
                    if (is)
                    {
                        mgwc.add(new Gwc(cm1,sms1,jg1,xh1,tp,1));
                    }
                }else {
                    mgwc.add(new Gwc(cm1,sms1,jg1,xh1,tp,1));
                }
                startActivity(new Intent(S_XQYActivity.this,S_GWCActivity.class));
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        huoqu();
    }
}
