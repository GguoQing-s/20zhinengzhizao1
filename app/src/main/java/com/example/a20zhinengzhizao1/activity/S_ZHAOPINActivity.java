package com.example.a20zhinengzhizao1.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.a20zhinengzhizao1.AppClient;
import com.example.a20zhinengzhizao1.R;
import com.example.a20zhinengzhizao1.adapter.Zhaopinadapter;
import com.example.a20zhinengzhizao1.adapter.Zhaopinadapter1;
import com.example.a20zhinengzhizao1.bean.Sc;
import com.example.a20zhinengzhizao1.bean.Yf;
import com.example.a20zhinengzhizao1.bean.Zhaopin;
import com.example.a20zhinengzhizao1.bean.Zhaopin1;
import com.example.a20zhinengzhizao1.dialog.S_FSDialog;
import com.example.a20zhinengzhizao1.net.VolleyLo;
import com.example.a20zhinengzhizao1.net.VolleyTo;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class S_ZHAOPINActivity extends AppCompatActivity {

    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.chazhao)
    TextView chazhao;
    @BindView(R.id.listView)
    ListView listView;
    @BindView(R.id.shuru)
    EditText shuru;
    @BindView(R.id.change)
    ImageView change;
    @BindView(R.id.change1)
    ImageView change1;
    private List<Zhaopin> mzhaopin;
    private List<Zhaopin1> mzhaopin1;
    private Zhaopinadapter mzhaopinadapter;
    private String index,select,index1;
    private Zhaopinadapter1 mzhaopinadapter1;
    private boolean is = false;
    private AppClient mApp;
    private List<Yf> myf;
    private List<Sc> msc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.s_zpxxactivity);
        ButterKnife.bind(this);
        index = getIntent().getStringExtra("name");
        index1 = getIntent().getStringExtra("name1");
        select = getIntent().getStringExtra("select");
        is = getIntent().getBooleanExtra("is", false);
        inview();
        addData();

    }

    private void jiesho() {
        if (select.equals("1"))
        {

            for (int i = 0; i < mzhaopin.size(); i++) {
                Zhaopin zhaopin = mzhaopin.get(i);
                String[] aa = zhaopin.getName1().split("-");
                String[] bb = zhaopin.getName().split("-");
                if (bb[1].equals(index) || aa[0].equals(index) || zhaopin.getYaoqiu().equals(index)) {
                    mzhaopin1.add(new Zhaopin1(zhaopin.getName(), zhaopin.getType(),
                            zhaopin.getName1(), zhaopin.getYaoqiu(), zhaopin.getChengshi(), zhaopin.getXiuli(),
                            zhaopin.getXinzi(), zhaopin.getEmail(), zhaopin.getTime()));
                }
            }


        }else if (select.equals("2"))
        {

            if (index1.equals("全部招聘信息"))
            {
                for (int i = 0; i < mzhaopin.size(); i++) {
                    Zhaopin zhaopin = mzhaopin.get(i);
                    mzhaopin1.add(new Zhaopin1(zhaopin.getName(), zhaopin.getType(),
                            zhaopin.getName1(), zhaopin.getYaoqiu(), zhaopin.getChengshi(), zhaopin.getXiuli(),
                            zhaopin.getXinzi(), zhaopin.getEmail(), zhaopin.getTime()));
                }

            }else if (index1.equals("按岗位查询"))
            {
                for (int i = 0; i < mzhaopin.size(); i++) {
                    Zhaopin zhaopin = mzhaopin.get(i);
                    if (zhaopin.getName1().equals(index)) {
                        mzhaopin1.add(new Zhaopin1(zhaopin.getName(), zhaopin.getType(),
                                zhaopin.getName1(), zhaopin.getYaoqiu(), zhaopin.getChengshi(), zhaopin.getXiuli(),
                                zhaopin.getXinzi(), zhaopin.getEmail(), zhaopin.getTime()));
                    }
                }


            }else if (index1.equals("按所在地查询"))
            {

                for (int i = 0; i < mzhaopin.size(); i++) {
                    Zhaopin zhaopin = mzhaopin.get(i);
                    String[] bb = zhaopin.getChengshi().split("-");
                    if (bb[0].equals(index)) {
                        mzhaopin1.add(new Zhaopin1(zhaopin.getName(), zhaopin.getType(),
                                zhaopin.getName1(), zhaopin.getYaoqiu(), zhaopin.getChengshi(), zhaopin.getXiuli(),
                                zhaopin.getXinzi(), zhaopin.getEmail(), zhaopin.getTime()));
                    }
                }

            }else if (index1.equals("按学历查询"))
            {

                for (int i = 0; i < mzhaopin.size(); i++) {
                    Zhaopin zhaopin = mzhaopin.get(i);
                    String[] dd= zhaopin.getName().split("-");
                    if (dd[1].equals(index)) {
                        mzhaopin1.add(new Zhaopin1(zhaopin.getName(), zhaopin.getType(),
                                zhaopin.getName1(), zhaopin.getYaoqiu(), zhaopin.getChengshi(), zhaopin.getXiuli(),
                                zhaopin.getXinzi(), zhaopin.getEmail(), zhaopin.getTime()));
                    }
                }


            }else if (index1.equals("按薪资查询"))
            {
                for (int i = 0; i < mzhaopin.size(); i++) {
                    Zhaopin zhaopin = mzhaopin.get(i);
                    String[] aa= zhaopin.getXinzi().split("-");
                    if (Integer.parseInt(index)>=Integer.parseInt(aa[0])&&Integer.parseInt(index)<=Integer.parseInt(aa[1])) {
                        mzhaopin1.add(new Zhaopin1(zhaopin.getName(), zhaopin.getType(),
                                zhaopin.getName1(), zhaopin.getYaoqiu(), zhaopin.getChengshi(), zhaopin.getXiuli(),
                                zhaopin.getXinzi(), zhaopin.getEmail(), zhaopin.getTime()));
                    }
                }

            }
        }
        if (mzhaopin1.size()==0)
        {
            Toast.makeText(S_ZHAOPINActivity.this, "没有查到公司信息", Toast.LENGTH_LONG).show();
        }
        setadapter1();
    }

    private void setadapter1() {
        mzhaopinadapter1 = new Zhaopinadapter1(this, mzhaopin1);
        listView.setAdapter(mzhaopinadapter1);
    }

    private void setadapter() {
        mzhaopinadapter = new Zhaopinadapter(this, mzhaopin);
        listView.setAdapter(mzhaopinadapter);
        mzhaopinadapter.SetData(new Zhaopinadapter.SetData() {
            @Override
            public void setdata(boolean sc, int lx, int position, String name, String type, String name1, String yaoqiu, String chengshi, String xiuli, String xinzi, String email, String time) {
                if (lx==1)
                {
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Date date = new Date(System.currentTimeMillis());
                    String t = format.format(date);
                    Zhaopin zhaopin = mzhaopin.get(position);
                    zhaopin.setSc(sc);
                    mzhaopin.set(position,zhaopin);
                    mzhaopinadapter.notifyDataSetChanged();
                    if (sc)
                    {
                        msc.add(new Sc(name, type, name1, yaoqiu, chengshi, xiuli, xinzi, email, time, t, true));
                    }else {
                        for (int i=msc.size();i>0;i--)
                        {
                            Sc sc1 = msc.get(i-1);
                            if (sc1.getName().equals(name))
                            {
                                msc.remove(i-1);
                            }
                        }
                    }
                    mzhaopinadapter.notifyDataSetChanged();
                }else if (lx==2)
                {
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Date date = new Date(System.currentTimeMillis());
                    String t = format.format(date);
                    String[] cc= name.split("-");
                    S_FSDialog dialog = new S_FSDialog(cc[0],t);
                    dialog.show(getSupportFragmentManager(),"");
                    myf.add(new Yf(name, type, name1, yaoqiu, chengshi, xiuli, xinzi, email, time, t));
                }
            }
        });
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
                            mzhaopin.add(new Zhaopin(
                            jsonObject1.optString("name")
                                    ,jsonObject1.optString("hy")
                                    ,jsonObject1.optString("gw")
                                    ,jsonObject1.optString("zyyq")
                                    ,jsonObject1.optString("szd")
                                    ,jsonObject1.optString("xl")
                                    ,jsonObject1.optString("xz")
                                    ,jsonObject1.optString("email")
                            ,jsonObject1.optString("time"),false,false));
                        }

                        Collections.sort(mzhaopin, new Comparator<Zhaopin>() {
                            @Override
                            public int compare(Zhaopin o1, Zhaopin o2) {
                                try {
                                    String time1 = o1.getTime();
                                    String time2 = o2.getTime();
                                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                                    Date d1 = format.parse(time1);
                                    Date d2 = format.parse(time2);
                                    if (d1.getTime() < d2.getTime()) {
                                        return 1;
                                    } else if (d1.getTime() < d2.getTime()) {
                                        return 0;
                                    } else {
                                        return -1;
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                return 0;
                            }
                        });
                        Log.d("2222222", "onResponse: "+mzhaopin);

                        if (is) {
                            jiesho();
                        }else {
                            setadapter();
                        }
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();
    }

    private void inview() {
        title.setText("人才市场-招聘信息");
        mzhaopin1 = new ArrayList<>();
        mApp = (AppClient) getApplication();
        msc = mApp.getMsc();
        mzhaopin = mApp.getZhaopins();
        myf = mApp.getMyf();
    }

    @OnClick(R.id.chazhao)
    public void onChazhaoClicked() {
        String s = shuru.getText().toString();
        for (int i = 0; i < mzhaopin.size(); i++) {
            Zhaopin zhaopin = mzhaopin.get(i);
            String[] a=zhaopin.getName().split("-");
            if (a[0].equals(s)) {
                Intent intent = new Intent(S_ZHAOPINActivity.this, S_ZHAOPINActivity1.class);
                intent.putExtra("name", s);
                startActivity(intent);
                finish();
                return;
            }
        }
        Toast.makeText(S_ZHAOPINActivity.this, "没有查到公司信息", Toast.LENGTH_LONG).show();
    }

    @OnClick({R.id.change, R.id.change1})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.change:
               startActivity(new Intent(S_ZHAOPINActivity.this,S_SCActivity.class));
                break;
            case R.id.change1:
                startActivity(new Intent(S_ZHAOPINActivity.this,S_YFJLActivity.class));
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mzhaopin.clear();
        msc.clear();
    }
}
