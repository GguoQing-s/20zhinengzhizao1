package com.example.a20zhinengzhizao1.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.a20zhinengzhizao1.R;
import com.example.a20zhinengzhizao1.adapter.FragmentAdapter;
import com.example.a20zhinengzhizao1.bean.Cunuser;
import com.example.a20zhinengzhizao1.bean.Jbxx1;
import com.example.a20zhinengzhizao1.fragment.S_Fragment_1;
import com.example.a20zhinengzhizao1.fragment.S_Fragment_2;
import com.example.a20zhinengzhizao1.fragment.S_Fragment_3;
import com.example.a20zhinengzhizao1.fragment.S_Fragment_4;
import com.example.a20zhinengzhizao1.net.VolleyLo;
import com.example.a20zhinengzhizao1.net.VolleyTo;


import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class S_SJFXActivity extends AppCompatActivity {
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.change)
    ImageView change;
    @BindView(R.id.change1)
    ImageView change1;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.lin)
    LinearLayout lin;
    private List<Fragment> fragments;
    private List<Jbxx1> mjbxx1;
    private int yi,er,san,si;
    private Map<String,Integer> zys;
    private List<Map.Entry<String,Integer>> listttype;
    private List<Cunuser> mcunuser;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.s_sjfxactivity);
        ButterKnife.bind(this);
        inview();
        huoqu();

    }

    private void huoqu1() {
        VolleyTo volleyTo  =new VolleyTo();
        volleyTo.setUrl("get_factory_information")
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        JSONArray jsonArray = jsonObject.optJSONArray("ROWS_DETAIL");
                        for (int i=0;i<jsonArray.length();i++)
                        {
                            JSONObject jsonObject1 = jsonArray.optJSONObject(i);
                            for (int y=0;y<mcunuser.size();y++)
                            {
                                Cunuser cunuser = mcunuser.get(y);

                                if (jsonObject1.optString("user").equals(cunuser.getUsername()))
                                {
                                    mjbxx1.add(new Jbxx1(jsonObject1.optString("name"),
                                            jsonObject1.optString("sex"),
                                            jsonObject1.optString("zy"),
                                            jsonObject1.optString("xx"),
                                            jsonObject1.optString("email"),
                                            jsonObject1.optString("tel"),
                                            jsonObject1.optString("csrq"),
                                            jsonObject1.optString("jg"),
                                            jsonObject1.optString("xl"),
                                            jsonObject1.optString("gzjl"),
                                            jsonObject1.optString("jyxx"),
                                            jsonObject1.optString("hj"),
                                            ""));
                                }

                            }
                        }
                        nl();
                        zy();
                        addFragment();

                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();
    }

    private void zy() {
        for (int i=0;i<mjbxx1.size();i++)
        {
            String type= mjbxx1.get(i).getZy();
            if (zys.get(type)==null)
            {
                zys.put(type,1);
            }else {
                zys.put(type,zys.get(type)+1);
            }
        }
        listttype = new ArrayList<>(zys.entrySet());
        Log.d("*************", "zy: ------"+listttype);
    }

    private void nl() {
        for (int i=0;i<mjbxx1.size();i++)
        {
            String bith = mjbxx1.get(i).getShengqi().substring(0,4);
            String year = (2020-(Integer.parseInt(bith)))+"";
            if (Integer.parseInt(year)<=25)
            {

                yi++;
            }else  if (Integer.parseInt(year)<=30)
            {
                er++;
            }else  if (Integer.parseInt(year)<=35)
            {
                san++;
            }else  if (Integer.parseInt(year)>35)
            {
                si++;
            }
        }
    }

    private void huoqu() {
        VolleyTo volleyTo1 = new VolleyTo();
        volleyTo1.setUrl("get_factory_application")
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        JSONArray jsonArray = jsonObject.optJSONArray("ROWS_DETAIL");
                        for (int i=0;i<jsonArray.length();i++)
                        {
                            JSONObject jsonObject1 = jsonArray.optJSONObject(i);
                            String[] aa=jsonObject1.optString("username").split("-");
                            mcunuser.add(new Cunuser(aa[0]));
                        }
                        Log.d("7777777777777", "onResponse: -----"+mcunuser);
                        huoqu1();
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();


    }

    private void select(int y) {
        for (int i=0;i<4;i++)
        {
            ImageView imageView  = (ImageView) lin.getChildAt(i);
            if (i==y)
            {
                imageView.setImageResource(R.drawable.shi);
            }else {
                imageView.setImageResource(R.drawable.kong);
            }
        }
    }

    private void settu() {
        lin.removeAllViews();
        for (int i=0;i<4;i++)
        {
            ImageView imageView = new ImageView(S_SJFXActivity.this);
            if (i==0)
            {
                imageView.setImageResource(R.drawable.shi);
            }else {
                imageView.setImageResource(R.drawable.kong);
            }
            lin.addView(imageView,50,50);
        }
    }

    private void addFragment() {
        fragments.add(new S_Fragment_1(mjbxx1));
        fragments.add(new S_Fragment_2(listttype));
        fragments.add(new S_Fragment_3(yi,er,san,si));
        fragments.add(new S_Fragment_4(mjbxx1));
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
        settu();

    }

    private void inview() {
        title.setText("数据分析");
        change.setVisibility(View.GONE);
        change1.setVisibility(View.GONE);
        fragments = new ArrayList<>();
        mjbxx1 = new ArrayList<>();
        zys=new HashMap<>();
        mcunuser = new ArrayList<>();
    }
}
