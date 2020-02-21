package com.example.a20zhinengzhizao1.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.a20zhinengzhizao1.R;
import com.example.a20zhinengzhizao1.net.VolleyLo;
import com.example.a20zhinengzhizao1.net.VolleyTo;

import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class S_JYActivity extends AppCompatActivity {
    @BindView(R.id.clm)
    EditText clm;
    @BindView(R.id.cshang)
    EditText cshang;
    @BindView(R.id.time)
    EditText time;
    @BindView(R.id.dj)
    EditText dj;
    @BindView(R.id.sl)
    EditText sl;
    @BindView(R.id.zjine)
    EditText zjine;
    @BindView(R.id.zh)
    EditText zh;
    @BindView(R.id.cgy)
    EditText cgy;
    @BindView(R.id.lxr)
    EditText lxr;
    @BindView(R.id.qd)
    Button qd;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.s_jyactivity);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.qd)
    public void onQdClicked() {
        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("set_supplier_transaction")
                .setJsonObject("csm",cshang.getText().toString())
                .setJsonObject("clm",clm.getText().toString())
                .setJsonObject("time",time.getText().toString())
                .setJsonObject("dj",dj.getText().toString())
                .setJsonObject("sl",sl.getText().toString())
                .setJsonObject("zjine",zjine.getText().toString())
                .setJsonObject("zh",zh.getText().toString())
                .setJsonObject("cgy",cgy.getText().toString())
                .setJsonObject("lxr",lxr.getText().toString())
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {

                        Toast.makeText(S_JYActivity.this,"添加成功",Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();
    }
}
