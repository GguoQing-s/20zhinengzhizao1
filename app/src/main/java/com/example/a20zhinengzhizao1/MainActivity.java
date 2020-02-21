package com.example.a20zhinengzhizao1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.a20zhinengzhizao1.activity.RcscActivity;
import com.example.a20zhinengzhizao1.activity.S_CJActivity;
import com.example.a20zhinengzhizao1.activity.S_CLKCActivity;
import com.example.a20zhinengzhizao1.activity.S_GYSActivity;
import com.example.a20zhinengzhizao1.activity.S_KCActivity;
import com.example.a20zhinengzhizao1.activity.S_WXCJActivity;
import com.example.a20zhinengzhizao1.activity.Z_YGXXActivity;
import com.example.a20zhinengzhizao1.net.VolleyLo;
import com.example.a20zhinengzhizao1.net.VolleyTo;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.rencai)
    LinearLayout rencai;
    @BindView(R.id.shiwai)
    TextView shiwai;
    @BindView(R.id.chejian)
    TextView chejian;
    @BindView(R.id.kongtiao)
    TextView kongtiao;
    @BindView(R.id.dianli)
    TextView dianli;
    @BindView(R.id.xiaohao)
    TextView xiaohao;
    @BindView(R.id.time)
    TextView time;
    @BindView(R.id.dengguang)
    TextView dengguang;
    @BindView(R.id.quanju)
    TextView quanju;
    @BindView(R.id.gongchang)
    TextView gongchang;
    @BindView(R.id.left)
    TextView left;
    @BindView(R.id.ruko)
    TextView ruko;
    @BindView(R.id.right)
    TextView right;
    @BindView(R.id.beijing)
    LinearLayout beijing;
    @BindView(R.id.id)
    TextView id;
    @BindView(R.id.user)
    TextView user;
    @BindView(R.id.jine)
    TextView jine;
    @BindView(R.id.gys)
    LinearLayout gys;

    @BindView(R.id.kc)
    LinearLayout kc;
    @BindView(R.id.clkc)
    LinearLayout clkc;
    @BindView(R.id.wxcj)
    LinearLayout wxcj;
    @BindView(R.id.scxxx)
    TextView scxxx;
    @BindView(R.id.ygxx)
    LinearLayout ygxx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        id.setText("用户ID：" + AppClient.getId());
        user.setText("用户姓名：" + AppClient.getName());
        jine.setText("资金：" + AppClient.getZj());

        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("set_factory_light?light=关闭")
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {

                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();

        VolleyTo volleyTo1 = new VolleyTo();
        volleyTo1.setUrl("set_factory_air?air=热风")
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {

                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();
        huoqu();
        setdata();
    }


    private void setdata() {
        SimpleDateFormat format = new SimpleDateFormat("mm:ss");
        Date date = new Date(System.currentTimeMillis());
        time.setText(format.format(date));
    }

    private void huoqu() {
        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("get_factory_info")
                .setLoop(true)
                .setTime(5000)
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {

                        JSONArray jsonArray = jsonObject.optJSONArray("ROWS_DETAIL");
                        JSONObject jsonObject1 = jsonArray.optJSONObject(0);
                        shiwai.setText(jsonObject1.optInt("outWd") + "℃");
                        chejian.setText(jsonObject1.optInt("intWd") + "℃");
                        dianli.setText(jsonObject1.optInt("butteryIn") + "kw/h");
                        xiaohao.setText(jsonObject1.optInt("butteryOut") + "kw/h");
                        dengguang.setText(jsonObject1.optString("light"));
                        kongtiao.setText(jsonObject1.optString("air"));
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                    }
                }).start();
    }

    @OnClick({R.id.quanju, R.id.gongchang, R.id.left, R.id.ruko, R.id.right, R.id.rencai, R.id.gys
            , R.id.kc, R.id.clkc, R.id.scxxx, R.id.wxcj,R.id.ygxx})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ygxx:
                startActivity(new Intent(MainActivity.this, Z_YGXXActivity.class));
                break;
            case R.id.wxcj:
                startActivity(new Intent(MainActivity.this, S_WXCJActivity.class));
                break;
            case R.id.scxxx:
                startActivity(new Intent(MainActivity.this, S_CJActivity.class));
                break;
            case R.id.kc:
                startActivity(new Intent(MainActivity.this, S_KCActivity.class));
                break;
            case R.id.clkc:
                startActivity(new Intent(MainActivity.this, S_CLKCActivity.class));
                break;
            case R.id.gys:
                startActivity(new Intent(MainActivity.this, S_GYSActivity.class));
                break;
            case R.id.rencai:
                startActivity(new Intent(MainActivity.this, RcscActivity.class));
                break;
            case R.id.quanju:
                beijing.setBackgroundResource(R.drawable.timg4);
                break;
            case R.id.gongchang:
                beijing.setBackgroundResource(R.drawable.timg6);
                break;
            case R.id.left:
                beijing.setBackgroundResource(R.drawable.timg8);
                break;
            case R.id.ruko:
                beijing.setBackgroundResource(R.drawable.timg2);
                break;
            case R.id.right:
                beijing.setBackgroundResource(R.drawable.timg9);
                break;
        }
    }

}
