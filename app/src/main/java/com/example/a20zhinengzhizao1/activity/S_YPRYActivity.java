package com.example.a20zhinengzhizao1.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.a20zhinengzhizao1.AppClient;
import com.example.a20zhinengzhizao1.R;
import com.example.a20zhinengzhizao1.adapter.Jbxxadapter;
import com.example.a20zhinengzhizao1.adapter.Ypryadapter;
import com.example.a20zhinengzhizao1.bean.Jbxx;
import com.example.a20zhinengzhizao1.bean.Ypry;
import com.example.a20zhinengzhizao1.dialog.TsDialog;
import com.example.a20zhinengzhizao1.net.VolleyLo;
import com.example.a20zhinengzhizao1.net.VolleyTo;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class S_YPRYActivity extends AppCompatActivity {
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.change)
    ImageView change;
    @BindView(R.id.change1)
    ImageView change1;
    @BindView(R.id.listView)
    ListView listView;
    @BindView(R.id.sum)
    TextView sum;
    @BindView(R.id.quanxuan)
    Button quanxuan;
    @BindView(R.id.end)
    Button end;
    @BindView(R.id.tu)
    ImageView tu;
    private List<Ypry> mypry;
    private Ypryadapter mypryadapter;
    private List<Jbxx> mjbxx;
    private Jbxxadapter mjbxxadapter;
    private AppClient mApp;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.s_zpryactivity);
        ButterKnife.bind(this);
        inview();
        huoqu();
        huoqu1("");

    }

    private void huoqu2(final String user, final String na) {
        VolleyTo volleyTo  =new VolleyTo();
        volleyTo.setUrl("get_factory_information")
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        JSONArray jsonArray = jsonObject.optJSONArray("ROWS_DETAIL");
                        for (int i=0;i<jsonArray.length();i++)
                        {
                            JSONObject jsonObject1 = jsonArray.optJSONObject(i);
                            if (jsonObject1.optString("user").equals(user))
                            {

                                if (na.equals(""))
                                {
                                    mjbxx.add(new Jbxx(jsonObject1.optString("name"),
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
                                            jsonObject1.optString("path"),
                                            jsonObject1.optString("user"),false));
                                    sum.setText("总应聘人数："+mjbxx.size()+"人");
                                }


                                if (jsonObject1.optString("jyxx").equals(na))
                                {
                                    mjbxx.add(new Jbxx(jsonObject1.optString("name"),
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
                                            jsonObject1.optString("path"),
                                            jsonObject1.optString("user"),false));
                                }
                            }

                        }
                        Log.d("9999999999999", "onResponse: ----"+mjbxx);

                        if (!na.equals(""))
                        {
                            setadapter1();
                        }
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();


    }

    private void setadapter1() {
        mjbxxadapter = new Jbxxadapter(this,mjbxx);
        listView.setAdapter(mjbxxadapter);
        mjbxxadapter.SetData(new Jbxxadapter.SetData() {
            @Override
            public void setdata(int position, String name, int lx, String path) {
                if (lx==1)
                {
                    Intent intent = new Intent(S_YPRYActivity.this,S_XQXXActivity.class);
                    intent.putExtra("name",name);
                    intent.putExtra("image",path);
                    startActivity(intent);
                }else {
                    for (int i=0;i<mjbxx.size();i++)
                    {
                        Jbxx jbxx = mjbxx.get(i);
                        if (position==i)
                        {
                            if (jbxx.isXz())
                            {
                                jbxx.setXz(false);
                                mjbxx.set(position,jbxx);
                            }else {
                                jbxx.setXz(true);
                                mjbxx.set(position,jbxx);
                            }

                        }
                        mjbxxadapter.notifyDataSetChanged();
                    }
                }
            }
        });
    }

    private void setadapter() {
        mypryadapter = new Ypryadapter(this,mypry);
        listView.setAdapter(mypryadapter);
        mypryadapter.SetData(new Ypryadapter.SetData() {
            @Override
            public void setdata(int position, String name) {
                quanxuan.setEnabled(true);
                end.setEnabled(true);
                mjbxx.clear();
                Log.d("1111+++", "setdata: ----"+name);
                huoqu1(name);
            }
        });
    }

    private void huoqu() {
        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("get_factory_recruit")
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        JSONArray jsonArray = jsonObject.optJSONArray("ROWS_DETAIL");
                        for (int i=0;i<jsonArray.length();i++)
                        {
                            JSONObject jsonObject1 = jsonArray.optJSONObject(i);
                            String[] aa= jsonObject1.optString("name").split("-");
                            String[] bb= jsonObject1.optString("gw").split("-");
                            mypry.add(new Ypry(bb[1],aa[0] , jsonObject1.optString("gw")));
                        }
                        Log.d("11111111111111111", "onResponse: ----"+mypry);
                        setadapter();
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();


    }

    private void huoqu1(final String na) {
        VolleyTo volleyTo1 = new VolleyTo();
        volleyTo1.setUrl("get_factory_application")
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        JSONArray jsonArray = jsonObject.optJSONArray("ROWS_DETAIL");
                        if (jsonArray.length()==0)
                        {
                            if (!na.equals(""))
                            {
                                Toast.makeText(S_YPRYActivity.this,"没有应聘人员",Toast.LENGTH_LONG).show();
                            }

                            return;
                        }

                        for (int i=0;i<jsonArray.length();i++)
                        {
                            JSONObject jsonObject1 = jsonArray.optJSONObject(i);
                            Log.d("8888888", "onResponse: ---"+jsonObject1.optString("username"));
                            String[] aa = jsonObject1.optString("username").split("-");
                            huoqu2(aa[0],na);
                        }
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();
    }
    private void inview() {
        title.setText("人才市场—招聘人员");
        change.setVisibility(View.GONE);
        change1.setVisibility(View.GONE);
        mypry = new ArrayList<>();
        mApp = (AppClient) getApplication();
        mjbxx = mApp.getMjbxx();
        quanxuan.setEnabled(false);
        end.setEnabled(false);
    }

    @OnClick({R.id.quanxuan, R.id.end,R.id.tu})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.quanxuan:
                for (int i=0;i<mjbxx.size();i++)
                {
                    Jbxx jbxx = mjbxx.get(i);
                    if (jbxx.isXz())
                    {
                        jbxx.setXz(false);
                        mjbxx.set(i,jbxx);
                    }else {
                        jbxx.setXz(true);
                        mjbxx.set(i,jbxx);
                    }
                    mjbxxadapter.notifyDataSetChanged();
                }
                break;
            case R.id.end:
                for (int i=0;i<mjbxx.size();i++)
                {
                    Jbxx jbxx = mjbxx.get(i);
                    if (jbxx.isXz())
                    {
                        TsDialog dialog  =new TsDialog("13563521293");
                        dialog.show(getFragmentManager(),"");
                        return;
                    }
                }
                Toast.makeText(S_YPRYActivity.this,"没有选中用户",Toast.LENGTH_LONG).show();


                break;
            case R.id.tu:

                VolleyTo volleyTo  =new VolleyTo();
                volleyTo.setUrl("get_factory_information")
                        .setVolleyLo(new VolleyLo() {
                            @Override
                            public void onResponse(JSONObject jsonObject) {
                                JSONArray jsonArray = jsonObject.optJSONArray("ROWS_DETAIL");
                                if (jsonArray.length()==0)
                                {
                                    Toast.makeText(S_YPRYActivity.this,"没有应聘人员",Toast.LENGTH_LONG).show();
                                    return;
                                }
                                startActivity(new Intent(S_YPRYActivity.this,S_SJFXActivity.class));
                            }

                            @Override
                            public void onErrorResponse(VolleyError volleyError) {

                            }
                        }).start();



                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mjbxx.clear();
    }
}
