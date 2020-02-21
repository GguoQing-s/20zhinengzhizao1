package com.example.a20zhinengzhizao1.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.a20zhinengzhizao1.R;
import com.example.a20zhinengzhizao1.adapter.Ejldadapter3;
import com.example.a20zhinengzhizao1.bean.Gystj;
import com.example.a20zhinengzhizao1.bean.Noyw;
import com.example.a20zhinengzhizao1.bean.Qbyw;
import com.example.a20zhinengzhizao1.bean.Yesyw;
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

public class S_EJLDActivity3 extends AppCompatActivity {

    @BindView(R.id.change)
    ImageView change;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.ex2)
    ExpandableListView ex2;
    private List<Gystj> mgystj;
    private List<String> fu;
    private Map<String, List<String>> zi;
    private Ejldadapter3 mejldadapter;
    private List<Yesyw> myesyw;
    private List<Noyw> mnoyw1,mnoyw2;
    private List<Qbyw> mqbyw;
    private Map<String,List<Noyw>> listtype;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.s_ejldactivity3);
        ButterKnife.bind(this);
        inview();
        huoqu();
    }

    private void panduan() {
        for (int y=0;y<myesyw.size();y++)
        {
            Yesyw yesyw = myesyw.get(y);
            for (int i=mqbyw.size();i>0;i--)
            {
                Qbyw qbyw = mqbyw.get(i-1);
                if (qbyw.getName().equals(yesyw.getName()))
                {
                    mqbyw.remove(i-1);
                }
            }
        }
        for (int x=0;x<mqbyw.size();x++)
        {
            Qbyw qbyw = mqbyw.get(x);
            for (int y=mnoyw1.size();y>0;y--)
            {
                Noyw noyw = mnoyw1.get(y-1);
                if (noyw.getName().equals(qbyw.getName()))
                {
                    mnoyw1.remove(y-1);
                }
            }
            mnoyw1.add(new Noyw(qbyw.getName()));
        }
        for (int x=0;x<myesyw.size();x++)
        {
            Yesyw qbyw = myesyw.get(x);
            for (int y=mnoyw2.size();y>0;y--)
            {
                Noyw noyw = mnoyw2.get(y-1);
                if (noyw.getName().equals(qbyw.getName()))
                {
                    mnoyw2.remove(y-1);
                }
            }
            mnoyw2.add(new Noyw(qbyw.getName()));
        }
       // Log.d("-----", "onResponse: ---"+mqbyw+"---"+myesyw+"---");
      //  Log.d("-----", "onResponse: ---"+mnoyw1+"----"+mnoyw2);
        listtype.put("存在业务",mnoyw2);
        listtype.put("不存在业务",mnoyw1);
        Log.d("111111111", "panduan: -----"+listtype.size());
       mejldadapter.notifyDataSetChanged();
    }

    private void setadapter() {


    }

    private void huoqu1(final String cs) {

        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("get_supplier_transaction")
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        JSONArray jsonArray = jsonObject.optJSONArray("ROWS_DETAIL");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject1 = jsonArray.optJSONObject(i);
                            for (int y = mqbyw.size(); y > 0; y--) {
                                Qbyw qbyw = mqbyw.get(y - 1);
                                if (qbyw.getName().equals(cs)) {
                                    mqbyw.remove(y - 1);
                                }
                            }
                            mqbyw.add(new Qbyw(cs));
                            if (jsonObject1.optString("csm").equals(cs)) {
                                for (int y = myesyw.size(); y > 0; y--) {
                                    Yesyw yesyw = myesyw.get(y - 1);
                                    if (yesyw.getName().equals(cs)) {
                                        myesyw.remove(y - 1);
                                    }
                                }
                                myesyw.add(new Yesyw(cs));
                            }
                        }

                        panduan();
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();
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
                            huoqu1(jsonObject1.optString("cshang"));
                        }
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
        ex2.setGroupIndicator(null);
        mqbyw = new ArrayList<>();
        mnoyw1 =new ArrayList<>();
        mnoyw2 = new ArrayList<>();
        listtype = new HashMap<>();
        myesyw = new ArrayList<>();
        mejldadapter = new Ejldadapter3(listtype);
        ex2.setAdapter(mejldadapter);
    }

    @OnClick(R.id.change)
    public void onChangeClicked() {
        finish();
    }


}

