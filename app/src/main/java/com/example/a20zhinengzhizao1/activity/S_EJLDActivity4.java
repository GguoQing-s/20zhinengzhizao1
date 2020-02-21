package com.example.a20zhinengzhizao1.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.a20zhinengzhizao1.R;
import com.example.a20zhinengzhizao1.adapter.Ejldadapter4;
import com.example.a20zhinengzhizao1.bean.jyje;
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
import butterknife.OnClick;

public class S_EJLDActivity4 extends AppCompatActivity {

    @BindView(R.id.change)
    ImageView change;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.ex3)
    ExpandableListView ex3;
    private List<String> fu;
    private Map<String, List<String>> zi;
    private Ejldadapter4 mejldadapter;
     private List<jyje> mjyje;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.s_ejldactivity4);
        ButterKnife.bind(this);
        inview();
        huoqu();

    }

    private void setadapter() {
        mejldadapter = new Ejldadapter4(fu, zi);
        ex3.setAdapter(mejldadapter);

    }

    private void huoqu() {
        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("get_supplier_transaction")
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        JSONArray jsonArray = jsonObject.optJSONArray("ROWS_DETAIL");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject1 = jsonArray.optJSONObject(i);
                            mjyje.add(new jyje(jsonObject1.optString("time")
                                    , jsonObject1.optString("dj")
                                    , jsonObject1.optString("sl")
                                    , jsonObject1.optString("zjine")
                                    , jsonObject1.optString("zh")
                                    , jsonObject1.optString("cgy")
                                    , jsonObject1.optString("lxr")
                                    , jsonObject1.optString("csm")
                                    , jsonObject1.optString("clm")));

                            for (int y = fu.size(); y > 0; y--) {
                                if (fu.get(y - 1).equals(jsonObject1.optString("csm"))) {
                                    fu.remove(y - 1);
                                }
                            }
                            fu.add(jsonObject1.optString("csm"));
                        }
                        for (int y = 0; y < fu.size(); y++) {
                            List<String> zz = new ArrayList<>();
                            for (int x = 0; x < mjyje.size(); x++) {
                                jyje jyjes = mjyje.get(x);
                                if (jyjes.getCsm().equals(fu.get(y))) {
                                    zz.add(jyjes.getCsm()+"="+jyjes.getTime()+"="+jyjes.getZje());
                                }
                            }
                            zi.put(fu.get(y), zz);
                        }
                        setadapter();
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();
    }

    private void inview() {
        title.setText("二级联动");
        fu = new ArrayList<>();
        zi = new HashMap<>();
        ex3.setGroupIndicator(null);
        mjyje = new ArrayList<>();
    }

    @OnClick(R.id.change)
    public void onChangeClicked() {
        finish();
    }


}

