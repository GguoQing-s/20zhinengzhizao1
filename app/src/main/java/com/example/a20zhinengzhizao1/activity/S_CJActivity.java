package com.example.a20zhinengzhizao1.activity;

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
import com.example.a20zhinengzhizao1.bean.Scxsj;
import com.example.a20zhinengzhizao1.fragment.S_Fragment_cj1;
import com.example.a20zhinengzhizao1.fragment.S_Fragment_cj2;
import com.example.a20zhinengzhizao1.fragment.S_Fragment_cj3;
import com.example.a20zhinengzhizao1.fragment.S_Fragment_cj4;
import com.example.a20zhinengzhizao1.net.VolleyLo;
import com.example.a20zhinengzhizao1.net.VolleyTo;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class S_CJActivity extends AppCompatActivity {
    @BindView(R.id.change)
    ImageView change;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.title1)
    TextView title1;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.yi)
    TextView yi;
    @BindView(R.id.er)
    TextView er;
    @BindView(R.id.san)
    TextView san;
    @BindView(R.id.si)
    TextView si;
    private List<Fragment> fragments;
    private List<Scxsj> mscxsj;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.s_cjactivity);
        ButterKnife.bind(this);
        inview();
        mscxsj.clear();
        huoqu();

    }
    private void huoqu() {
        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("get_scx")
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        JSONArray jsonArray = jsonObject.optJSONArray("ROWS_DETAIL");
                        for (int i=0;i<jsonArray.length();i++)
                        {
                            JSONObject jsonObject1 = jsonArray.optJSONObject(i);
                            mscxsj.add(new Scxsj(jsonObject1.optString("cjm"),
                                    jsonObject1.optString("scxm"),
                                    jsonObject1.optString("zt"),
                                    jsonObject1.optString("hj"),
                                    jsonObject1.optString("ts")));
                        }
                        Log.d("0000000000", "onResponse: ----"+mscxsj);
                        addFragment();
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();
    }

    private void select(int y) {
        if (y==0)
        {
            yi.setBackgroundResource(R.drawable.cjbk2);
            er.setBackgroundResource(R.drawable.cjbk1);
            san.setBackgroundResource(R.drawable.cjbk1);
            si.setBackgroundResource(R.drawable.cjbk1);

        }
        if (y==1)
        {
            er.setBackgroundResource(R.drawable.cjbk2);
            yi.setBackgroundResource(R.drawable.cjbk1);
            san.setBackgroundResource(R.drawable.cjbk1);
            si.setBackgroundResource(R.drawable.cjbk1);
        }
        if (y==2)
        {
            san.setBackgroundResource(R.drawable.cjbk2);
            er.setBackgroundResource(R.drawable.cjbk1);
            yi.setBackgroundResource(R.drawable.cjbk1);
            si.setBackgroundResource(R.drawable.cjbk1);

        }
        if (y==3)
        {
            si.setBackgroundResource(R.drawable.cjbk2);
            yi.setBackgroundResource(R.drawable.cjbk1);
            san.setBackgroundResource(R.drawable.cjbk1);
            er.setBackgroundResource(R.drawable.cjbk1);

        }
    }

    private void addFragment() {
        fragments.add(new S_Fragment_cj1(mscxsj));
        fragments.add(new S_Fragment_cj2(mscxsj));
        fragments.add(new S_Fragment_cj3(mscxsj));
        fragments.add(new S_Fragment_cj4(mscxsj));
        viewPager.setAdapter(new FragmentAdapter(getSupportFragmentManager(),fragments));
        viewPager.setCurrentItem(0);
        viewPager.setOffscreenPageLimit(fragments.size());
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                select(i);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    private void inview() {
        title.setText("车间");
        fragments = new ArrayList<>();
        mscxsj = new ArrayList<>();
    }

    @OnClick({R.id.change, R.id.yi, R.id.er, R.id.san, R.id.si})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.change:
                finish();
                break;
            case R.id.yi:
                viewPager.setCurrentItem(0);
                yi.setBackgroundResource(R.drawable.cjbk2);
                er.setBackgroundResource(R.drawable.cjbk1);
                san.setBackgroundResource(R.drawable.cjbk1);
                si.setBackgroundResource(R.drawable.cjbk1);
                break;
            case R.id.er:
                viewPager.setCurrentItem(1);
                er.setBackgroundResource(R.drawable.cjbk2);
                yi.setBackgroundResource(R.drawable.cjbk1);
                san.setBackgroundResource(R.drawable.cjbk1);
                si.setBackgroundResource(R.drawable.cjbk1);
                break;
            case R.id.san:
                viewPager.setCurrentItem(2);
                san.setBackgroundResource(R.drawable.cjbk2);
                er.setBackgroundResource(R.drawable.cjbk1);
                yi.setBackgroundResource(R.drawable.cjbk1);
                si.setBackgroundResource(R.drawable.cjbk1);
                break;
            case R.id.si:
                viewPager.setCurrentItem(3);
                si.setBackgroundResource(R.drawable.cjbk2);
                yi.setBackgroundResource(R.drawable.cjbk1);
                san.setBackgroundResource(R.drawable.cjbk1);
                er.setBackgroundResource(R.drawable.cjbk1);
                break;
        }
    }
}
