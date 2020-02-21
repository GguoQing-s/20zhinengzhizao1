package com.example.a20zhinengzhizao1.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.a20zhinengzhizao1.R;
import com.example.a20zhinengzhizao1.bean.Yhm;
import com.example.a20zhinengzhizao1.net.VolleyLo;
import com.example.a20zhinengzhizao1.net.VolleyTo;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class S_YHZCActivity extends BaseActivity {

    @BindView(R.id.yhm)
    EditText yhm;
    @BindView(R.id.mm)
    EditText mm;
    @BindView(R.id.mm1)
    EditText mm1;
    @BindView(R.id.yx)
    EditText yx;
    @BindView(R.id.qd)
    Button qd;
    @BindView(R.id.qx)
    Button qx;
    private boolean is=true;
    private List<Yhm> myhm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.s_yhzcactivity);
        ButterKnife.bind(this);
        myhm=new ArrayList<>();
        adddata();
        jianting();
    }

    private void adddata() {
        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("get_login")
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        JSONArray jsonArray = jsonObject.optJSONArray("ROWS_DETAIL");
                        for (int i=0;i<jsonArray.length();i++)
                        {
                            JSONObject jsonObject1 = jsonArray.optJSONObject(i);
                            myhm.add(new Yhm(jsonObject1.optString("username")));

                        }
                    }
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                    }
                }).start();
    }

    private void jianting() {
        mm.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String str=  mm.getText().toString();
                if (!str.equals("")&&s.length()>16)
                {
                    mm.setSelection(16);
                    mm.setText(str.substring(0,16));
                    Toast.makeText(S_YHZCActivity.this,"最长只能是16位",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @OnClick({R.id.qd, R.id.qx})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.qd:
                final String y = yhm.getText().toString();
                final String m = mm.getText().toString();
                String m1= mm1.getText().toString();
                final String y1 = yx.getText().toString();
                String pd = "(.*[A-Za-z0-9].*){8,16}";
                String yxpd = "^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$";
                String yxpd1="^[A-Za-z0-9\\u4e00-\\u9fa5]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$";
                boolean matches = Pattern.matches(pd,m);
                boolean matches1 = Pattern.matches(yxpd,y1);
                boolean matches2 = Pattern.matches(yxpd1,y1);
                if (y.equals(""))
                {
                    Toast.makeText(S_YHZCActivity.this,"用户名不能为空",Toast.LENGTH_LONG).show();
                    return;
                }
                if (!matches)
                {

                    Toast.makeText(S_YHZCActivity.this,"密码必须由8-16的数字、大写字母和小写字母构成",Toast.LENGTH_LONG).show();
                    return;
                }
                if (!m.equals(m1))
                {
                    Toast.makeText(S_YHZCActivity.this,"密码不一致",Toast.LENGTH_LONG).show();
                    return;
                }
                if (!matches1)
                {
                    Toast.makeText(S_YHZCActivity.this,"邮箱格式错误",Toast.LENGTH_LONG).show();
                    return;
                }else if (!matches2)
                {
                    Toast.makeText(S_YHZCActivity.this,"邮箱格式错误",Toast.LENGTH_LONG).show();
                    return;
                }
                for (int i=0;i<myhm.size();i++)
                {
                    Yhm yhm = myhm.get(i);
                    if (yhm.getUser().equals(y))
                    {
                        Toast.makeText(S_YHZCActivity.this,"用户名已存在",Toast.LENGTH_LONG).show();
                        return;
                    }
                }
                VolleyTo volleyTo1 = new VolleyTo();
                volleyTo1.setUrl("set_login")
                        .setJsonObject("username",y)
                        .setJsonObject("password",m)
                        .setJsonObject("email",y1)
                        .setVolleyLo(new VolleyLo() {
                            @Override
                            public void onResponse(JSONObject jsonObject) {
                                if (jsonObject.optString("RESULT").equals("S"))
                                {
                                    Toast.makeText(S_YHZCActivity.this,"注册成功",Toast.LENGTH_LONG).show();
                                }
                            }
                            @Override
                            public void onErrorResponse(VolleyError volleyError) {
                            }
                        }).start();




                break;
            case R.id.qx:
                startActivity(new Intent(S_YHZCActivity.this, S_YHDLActivity.class));
               finish();
                break;
        }
    }
}
