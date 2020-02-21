package com.example.a20zhinengzhizao1.activity;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.a20zhinengzhizao1.AppClient;
import com.example.a20zhinengzhizao1.R;
import com.example.a20zhinengzhizao1.bean.Jbxx;
import com.example.a20zhinengzhizao1.net.VolleyLo;
import com.example.a20zhinengzhizao1.net.VolleyTo;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class S_XQXXActivity extends AppCompatActivity {
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.change)
    ImageView change;
    @BindView(R.id.change1)
    ImageView change1;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.sex)
    TextView sex;
    @BindView(R.id.zy)
    TextView zy;
    @BindView(R.id.xx)
    TextView xx;
    @BindView(R.id.email)
    TextView email;
    @BindView(R.id.tel)
    TextView tel;
    @BindView(R.id.sr)
    TextView sr;
    @BindView(R.id.jg)
    TextView jg;
    @BindView(R.id.xl)
    TextView xl;
    @BindView(R.id.jl)
    TextView jl;
    @BindView(R.id.yx)
    TextView yx;
    @BindView(R.id.hj)
    TextView hj;
    @BindView(R.id.fanhui)
    Button fanhui;
    @BindView(R.id.tx)
    ImageView tx;
    private AppClient mApp;
    private List<Jbxx> mjbxx;
    private String path;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.s_xqxxactivity);
        ButterKnife.bind(this);
        title.setText("详细信息");
        inview();
        jiesho();
        huoqu();
    }

    private void huoqu() {
        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("get_factory_application")
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        JSONArray jsonArray = jsonObject.optJSONArray("ROWS_DETAIL");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject1 = jsonArray.optJSONObject(i);
                            String[] aa = jsonObject1.optString("username").split("-");
                            for (int y = 0; y < mjbxx.size(); y++) {
                                Jbxx jbxx = mjbxx.get(y);
                                if (jbxx.getUser().equals(aa[0])) {
                                    Toast.makeText(S_XQXXActivity.this, "简历已下载到：" + jsonObject1.optString("path"), Toast.LENGTH_LONG).show();
                                }
                            }
                        }
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();
    }

    private void inview() {
        mApp = (AppClient) getApplication();
        mjbxx = mApp.getMjbxx();
        change.setVisibility(View.GONE);
        change1.setVisibility(View.GONE);
    }

    private void jiesho() {
        String mname = getIntent().getStringExtra("name");
        path = getIntent().getStringExtra("image");
        tx.setImageBitmap(BitmapFactory.decodeFile(path));
        for (int i = 0; i < mjbxx.size(); i++) {
            Jbxx jbxx = mjbxx.get(i);
            if (jbxx.getName().equals(mname)) {
                name.setText("姓名：" + mname);
                sex.setText("性别：" + jbxx.getSex());
                zy.setText("专业：" + jbxx.getZy());
                xx.setText("学校：" + jbxx.getSchool());
                email.setText("邮箱：" + jbxx.getEmial());
                tel.setText("电话：" + jbxx.getTel());
                sr.setText("出生日期：" + jbxx.getShengqi());
                jg.setText("籍贯：" + jbxx.getJg());
                xl.setText("学历：" + jbxx.getXl());
                jl.setText("工作经历：" + jbxx.getJl());
                yx.setText("就业意向：" + jbxx.getYx());
                hj.setText("获奖：" + jbxx.getHj());
            }
        }
    }

    @OnClick(R.id.fanhui)
    public void onFanhuiClicked() {
        finish();
    }
}
