package com.example.a20zhinengzhizao1.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.a20zhinengzhizao1.R;
import com.example.a20zhinengzhizao1.adapter.Ejldadapter1;
import com.example.a20zhinengzhizao1.bean.Gystj;
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

public class S_EJLDActivity2 extends AppCompatActivity {

    @BindView(R.id.change)
    ImageView change;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.ex1)
    ExpandableListView ex1;
    private List<Gystj> mgystj;
    private List<String> fu;
    private Map<String, List<String>> zi;
    private Ejldadapter1 mejldadapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.s_ejldactivity2);
        ButterKnife.bind(this);
        inview();
        huoqu();

    }

    private void setadapter() {
        mejldadapter = new Ejldadapter1(fu, zi);
        ex1.setAdapter(mejldadapter);

    }

    private void huoqu() {
        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("get_supplier_material")
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        JSONArray jsonArray = jsonObject.optJSONArray("ROWS_DETAIL");
                        for (int i = 0; i < jsonArray.length(); i++) {

                            JSONObject jsonObject1 = jsonArray.optJSONObject(i);
                            mgystj.add(new Gystj(jsonObject1.optString("name")
                                    , jsonObject1.optString("xh")
                                    , jsonObject1.optString("cshang")
                                    , jsonObject1.optString("cs")
                                    , jsonObject1.optInt("path")));
                            for (int y = fu.size(); y > 0; y--) {
                                if (fu.get(y - 1).equals(jsonObject1.optString("name"))) {
                                    fu.remove(y - 1);
                                }
                            }
                            fu.add(jsonObject1.optString("name"));
                        }

                        for (int y = 0; y < fu.size(); y++) {
                            List<String> zz = new ArrayList<>();
                            for (int x = 0; x < mgystj.size(); x++) {
                                Gystj gystj = mgystj.get(x);
                                if (gystj.getName().equals(fu.get(y))) {
                                    zz.add(gystj.getChangshang());
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
        mgystj = new ArrayList<>();
        fu = new ArrayList<>();
        zi = new HashMap<>();
        ex1.setGroupIndicator(null);
    }

    @OnClick(R.id.change)
    public void onChangeClicked() {
        finish();
    }


}

