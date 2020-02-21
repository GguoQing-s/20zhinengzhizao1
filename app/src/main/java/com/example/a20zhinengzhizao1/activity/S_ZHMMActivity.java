package com.example.a20zhinengzhizao1.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.a20zhinengzhizao1.R;
import com.example.a20zhinengzhizao1.net.VolleyLo;
import com.example.a20zhinengzhizao1.net.VolleyTo;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class S_ZHMMActivity extends BaseActivity {

    @BindView(R.id.yhm)
    EditText yhm;
    @BindView(R.id.yx)
    EditText yx;
    @BindView(R.id.qd)
    Button qd;
    @BindView(R.id.miao)
    TextView miao;
    @BindView(R.id.lin)
    LinearLayout lin;
    @BindView(R.id.lin1)
    LinearLayout lin1;
    @BindView(R.id.zuoding)
    TextView zuoding;
    @BindView(R.id.qx)
    Button qx;
    private boolean is = true;
    private int index = 0, select = 31;
    private AlertDialog.Builder builder;
    Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            miao.setText((--select) + "");
            if (select == 0) {
                is = false;
                index = 0;
                select = 31;
                lin1.setBackgroundColor(Color.WHITE);
                zuoding.setTextColor(Color.WHITE);
                miao.setTextColor(Color.WHITE);
                lin1.setEnabled(true);
                yhm.setEnabled(true);
                yx.setEnabled(true);

                yhm.setBackgroundColor(Color.WHITE);
                yx.setBackgroundColor(Color.WHITE);
                yx.setBackgroundResource(R.drawable.biankuang);
                yhm.setBackgroundResource(R.drawable.biankuang);
                qd.setBackgroundResource(R.drawable.biankuang1);
                lin1.setBackgroundColor(Color.WHITE);
                qd.setTextColor(Color.BLACK);
            }
            return false;
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.s_zhmmactivity);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.qd)
    public void onQdClicked() {
        final String y = yhm.getText().toString();
        final String y1 = yx.getText().toString();

        String yxpd = "^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$";
        String yxpd1 = "^[A-Za-z0-9\\u4e00-\\u9fa5]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$";

        boolean matches1 = Pattern.matches(yxpd, y1);
        boolean matches2 = Pattern.matches(yxpd1, y1);
        if (y.equals("")) {
            Toast.makeText(S_ZHMMActivity.this, "用户名不能为空", Toast.LENGTH_LONG).show();
            return;
        }

        if (!matches1) {
            Toast.makeText(S_ZHMMActivity.this, "邮箱格式错误", Toast.LENGTH_LONG).show();
            return;
        } else if (!matches2) {
            Toast.makeText(S_ZHMMActivity.this, "邮箱格式错误", Toast.LENGTH_LONG).show();
            return;
        }


        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("get_login")
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        JSONArray jsonArray = jsonObject.optJSONArray("ROWS_DETAIL");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject1 = jsonArray.optJSONObject(i);

                            if (jsonObject1.optString("username").equals(y) && jsonObject1.optString("email").equals(y1)) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(S_ZHMMActivity.this);
                                builder.setTitle("提示");
                                builder.setCancelable(false);
                                builder.setMessage("找回密码为：" + jsonObject1.optString("password"));
                                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                });
                                builder.show();
                                return;
                            }
                        }

                        index++;
                        if (index == 3) {
                            is = true;
                            zuoding.setTextColor(Color.BLACK);
                            miao.setTextColor(Color.BLACK);
                            lin1.setEnabled(false);
                            yhm.setEnabled(false);
                            yx.setEnabled(false);
                            yhm.setBackgroundColor(Color.parseColor("#E9E7E7"));
                            yx.setBackgroundColor(Color.parseColor("#E9E7E7"));
                            qd.setBackgroundColor(Color.parseColor("#bbbbbb"));
                            lin1.setBackgroundColor(Color.parseColor("#bbbbbb"));
                            qd.setTextColor(Color.parseColor("#E9E7E7"));
                            new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    do {
                                        handler.sendEmptyMessage(0);
                                        try {
                                            Thread.sleep(1000);
                                        } catch (InterruptedException e) {
                                            e.printStackTrace();
                                        }
                                    } while (is);
                                }
                            }).start();
                        }
                        Toast.makeText(S_ZHMMActivity.this, "用户名或邮箱输入错误", Toast.LENGTH_LONG).show();

                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                    }
                }).start();


    }

    @OnClick(R.id.qx)
    public void onQxClicked() {
        startActivity(new Intent(S_ZHMMActivity.this, S_YHDLActivity.class));
        finish();
    }
}
