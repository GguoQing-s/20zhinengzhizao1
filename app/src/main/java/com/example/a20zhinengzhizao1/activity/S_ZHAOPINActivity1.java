package com.example.a20zhinengzhizao1.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.a20zhinengzhizao1.AppClient;
import com.example.a20zhinengzhizao1.R;
import com.example.a20zhinengzhizao1.adapter.Chaxunadapter;
import com.example.a20zhinengzhizao1.adapter.Zhaopinadapter1;
import com.example.a20zhinengzhizao1.bean.Chaxun;
import com.example.a20zhinengzhizao1.bean.Zhaopin1;
import com.example.a20zhinengzhizao1.net.VolleyLo;
import com.example.a20zhinengzhizao1.net.VolleyTo;


import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class S_ZHAOPINActivity1 extends AppCompatActivity {
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.shuru)
    EditText shuru;
    @BindView(R.id.quxiao)
    TextView quxiao;
    @BindView(R.id.listView)
    ListView listView;
    @BindView(R.id.change)
    ImageView change;
    @BindView(R.id.change1)
    ImageView change1;
    private List<Zhaopin1> mzhaopin;
    private Zhaopinadapter1 mzhaopinadapter;
    private String index;
    private AppClient mApp;
    private boolean is = false;
    private List<Chaxun> mChaxun;
    private Chaxunadapter mchaxunadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.s_zpxxactivity1);
        ButterKnife.bind(this);
        change.setVisibility(View.GONE);
        change1.setVisibility(View.GONE);
        index = getIntent().getStringExtra("name");
        inview();
        addData();
        shuru.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override

            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    String search = shuru.getText().toString().trim();
                    Intent intent = new Intent(S_ZHAOPINActivity1.this, S_ZHAOPINActivity.class);
                    intent.putExtra("name", search);
                    intent.putExtra("is", true);
                    intent.putExtra("select","1");
                    startActivity(intent);
                    finish();
                    return true;

                }

                return false;

            }

        });
        jianting();

    }

    private void jianting() {
        shuru.addTextChangedListener(new TextWatcher() {
            private String temp;

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                temp = shuru.getText().toString();
                if (!temp.equals("")) {
                    setadapter1();
                } else {
                    setadapter();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void setadapter1() {
        mchaxunadapter = new Chaxunadapter(this, mChaxun);
        listView.setAdapter(mchaxunadapter);
        mchaxunadapter.SetData(new Chaxunadapter.SetData() {
            @Override
            public void setdata(String name) {
                Intent intent = new Intent(S_ZHAOPINActivity1.this, S_ZHAOPINActivity.class);
                intent.putExtra("name", shuru.getText().toString().trim());
                intent.putExtra("name1", name);
                intent.putExtra("is", true);
                intent.putExtra("select","2");
                startActivity(intent);
                finish();
            }
        });
    }

    private void setadapter() {
        mzhaopinadapter = new Zhaopinadapter1(this, mzhaopin);
        listView.setAdapter(mzhaopinadapter);
    }

    private void addData() {

        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("get_factory_recruit")
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        JSONArray jsonArray = jsonObject.optJSONArray("ROWS_DETAIL");
                        for (int i=0;i<jsonArray.length();i++)
                        {
                            JSONObject jsonObject1 = jsonArray.optJSONObject(i);
                            if (is) {
                                mzhaopin.add(new Zhaopin1(
                                        jsonObject1.optString("name")
                                        ,jsonObject1.optString("hy")
                                        ,jsonObject1.optString("gw")
                                        ,jsonObject1.optString("zyyq")
                                        ,jsonObject1.optString("szd")
                                        ,jsonObject1.optString("xl")
                                        ,jsonObject1.optString("xz")
                                        ,jsonObject1.optString("email")
                                        ,jsonObject1.optString("time")));
                            } else {
                                String[] aa = jsonObject1.optString("name").split("-");
                                if (aa[0].equals(index)) {
                                    mzhaopin.add(new Zhaopin1(
                                            jsonObject1.optString("name")
                                            ,jsonObject1.optString("hy")
                                            ,jsonObject1.optString("gw")
                                            ,jsonObject1.optString("zyyq")
                                            ,jsonObject1.optString("szd")
                                            ,jsonObject1.optString("xl")
                                            ,jsonObject1.optString("xz")
                                            ,jsonObject1.optString("email")
                                            ,jsonObject1.optString("time")));
                                }
                            }
                        }
                        setadapter();

                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();

    }

    private void inview() {
        mApp = (AppClient) getApplication();
        title.setText("人才市场-招聘信息");
        mzhaopin = new ArrayList<>();
        mChaxun = mApp.getmChaxun();
    }

    @OnClick(R.id.quxiao)
    public void onQuxiaoClicked() {
        mzhaopin.clear();
        is = true;
        addData();
        mzhaopinadapter.notifyDataSetChanged();
    }
}
