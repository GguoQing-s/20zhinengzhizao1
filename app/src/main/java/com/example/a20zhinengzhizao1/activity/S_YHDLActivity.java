package com.example.a20zhinengzhizao1.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.a20zhinengzhizao1.AppClient;
import com.example.a20zhinengzhizao1.MainActivity;
import com.example.a20zhinengzhizao1.R;
import com.example.a20zhinengzhizao1.net.VolleyLo;
import com.example.a20zhinengzhizao1.net.VolleyTo;

import org.json.JSONArray;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class S_YHDLActivity extends BaseActivity {
    @BindView(R.id.zcyh)
    TextView zcyh;
    @BindView(R.id.zhmm)
    TextView zhmm;
    @BindView(R.id.yhm)
    EditText yhm;
    @BindView(R.id.mm)
    EditText mm;
    @BindView(R.id.jizhumima)
    CheckBox jizhumima;
    @BindView(R.id.zidongdenglu)
    CheckBox zidongdenglu;
    @BindView(R.id.qd)
    Button qd;
    @BindView(R.id.qx)
    Button qx;
    private Intent intent;




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.s_yhdlactivity);
        ButterKnife.bind(this);
        /* intent = new Intent(this, SQLserver.class);
        startService(intent);
        stopService(intent);*/
        inview();
       /* int url = R.drawable.baoma;
       tu1.setImageResource(url);*/

    }

    private void add() {
        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("get_login")
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        JSONArray jsonArray = jsonObject.optJSONArray("ROWS_DETAIL");
                        for (int i=0;i<jsonArray.length();i++)
                        {
                            JSONObject jsonObject1 = jsonArray.optJSONObject(i);

                            Log.d("1111", "onResponse: ---"+jsonObject1.optString("username"));
                        }

                    }
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                    }
                }).start();
    }

    private void inview() {
        if (!AppClient.getUserName().equals("")) {
            yhm.setText(AppClient.getUserName());
            mm.setText(AppClient.getPassWord());
        }
        if (AppClient.getXz()) {
            startActivity(new Intent(S_YHDLActivity.this, MainActivity.class));
        }
    }


    @OnClick({R.id.zcyh, R.id.zhmm, R.id.qd, R.id.qx})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.zcyh:
                startActivity(new Intent(S_YHDLActivity.this, S_YHZCActivity.class));
                finish();
                break;
            case R.id.zhmm:
                startActivity(new Intent(S_YHDLActivity.this, S_ZHMMActivity.class));
                finish();
                break;
            case R.id.qd:
                final String y = yhm.getText().toString();
                final String m = mm.getText().toString();

                if (jizhumima.isChecked()) {
                    AppClient.setUserName(y);
                    AppClient.setPassWord(m);
                } else {
                    AppClient.setUserName("");
                    AppClient.setPassWord("");
                }

                if (zidongdenglu.isChecked()) {
                    AppClient.setXz(true);
                } else {
                    AppClient.setXz(false);
                }
                VolleyTo volleyTo = new VolleyTo();
                volleyTo.setUrl("get_login")
                        .setVolleyLo(new VolleyLo() {
                            @Override
                            public void onResponse(JSONObject jsonObject) {
                                JSONArray jsonArray = jsonObject.optJSONArray("ROWS_DETAIL");
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject1 = jsonArray.optJSONObject(i);

                                    if (jsonObject1.optString("username").equals(y) && jsonObject1.optString("password").equals(m)) {
                                        Toast.makeText(S_YHDLActivity.this, "登录成功", Toast.LENGTH_LONG).show();
                                        startActivity(new Intent(S_YHDLActivity.this, MainActivity.class));
                                        AppClient.setId("1");
                                        AppClient.setName(y);
                                        AppClient.setZj(jsonObject1.optString("jine"));
                                        return;
                                    }
                                }
                                Toast.makeText(S_YHDLActivity.this, "登录失败", Toast.LENGTH_LONG).show();
                            }

                            @Override
                            public void onErrorResponse(VolleyError volleyError) {
                            }
                        }).start();

                break;
            case R.id.qx:
                finish();
                add();
                break;
        }
    }


}
