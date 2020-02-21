package com.example.a20zhinengzhizao1.fragment;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.a20zhinengzhizao1.R;
import com.example.a20zhinengzhizao1.activity.S_SCXXXActivity;
import com.example.a20zhinengzhizao1.bean.Scxsj;
import com.example.a20zhinengzhizao1.net.VolleyLo;
import com.example.a20zhinengzhizao1.net.VolleyTo;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

@SuppressLint("ValidFragment")
public class S_Fragment_cj3 extends Fragment {


    @BindView(R.id.gz3)
    TextView gz3;
    @BindView(R.id.wd3)
    TextView wd3;
    @BindView(R.id.dl3)
    TextView dl3;
    @BindView(R.id.xh3)
    TextView xh3;
    @BindView(R.id.ylrl3)
    TextView ylrl3;
    @BindView(R.id.qcrl3)
    TextView qcrl3;
    @BindView(R.id.dg3)
    TextView dg3;
    @BindView(R.id.sw31)
    Switch sw31;
    @BindView(R.id.kt3)
    TextView kt3;
    @BindView(R.id.sw32)
    Switch sw32;
    @BindView(R.id.ms3)
    TextView ms3;
    @BindView(R.id.ds3)
    TextView ds3;
    Unbinder unbinder;
    @BindView(R.id.lin3)
    LinearLayout lin3;
    private List<Scxsj> mscxsj;

    public S_Fragment_cj3(List<Scxsj> mscxsj) {
        this.mscxsj = mscxsj;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.s_fragment_cj3, null);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        huoqu();
        huoqu1();
        jianting();
        settu();
    }

    private void setdialog(String scxh) {
        String zt="";
        for (int i=0;i<mscxsj.size();i++)
        {
            Scxsj scxsj = mscxsj.get(i);
            if (scxsj.getCjm().equals("3"))
            {
                if (scxsj.getScxm().equals(scxh))
                {
                    zt=scxsj.getZt();
                }
            }
        }

        Intent intent = new Intent(getContext(), S_SCXXXActivity.class);
        intent.putExtra("cjh","3");
        intent.putExtra("scxh",scxh);
        intent.putExtra("zt",zt);
        startActivity(intent);
    }

    private void settu() {

        lin3.removeAllViews();
        for (int i = 0; i < mscxsj.size(); i++) {
            Scxsj scxsj = mscxsj.get(i);
            if (scxsj.getCjm().equals("3")) {
                final View view = LayoutInflater.from(getContext()).inflate(R.layout.scx_item, null, false);
                TextView tv1 = view.findViewById(R.id.tv1);
                if (scxsj.getZt().equals("建设中")) {
                    tv1.setBackgroundResource(R.drawable.lsk);
                }
                if (scxsj.getZt().equals("缺原材料")) {
                    tv1.setBackgroundResource(R.drawable.hsk);
                }
                if (scxsj.getZt().equals("生产中")) {
                    tv1.setBackgroundResource(R.drawable.lvsk);
                }
                if (scxsj.getZt().equals("库存已满")) {
                    tv1.setBackgroundResource(R.drawable.hosk);
                }
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        TextView tv2 = view.findViewById(R.id.tv1);
                        String name = tv2.getText().toString();
                        setdialog(name.substring(name.length() - 1));
                    }
                });
                tv1.setText("生产线" + scxsj.getScxm());
                lin3.addView(view, 200, LinearLayout.LayoutParams.MATCH_PARENT);
            }
        }
    }

    private void ms(String bh, String ms) {
        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("update_ms")
                .setJsonObject("ms",ms)
                .setJsonObject("bianhao",bh)
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        huoqu1();
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();
    }

    private void ds() {
        final Dialog dialog = new Dialog(getActivity());
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.kt_dialog);
        Button qd = dialog.findViewById(R.id.qd);
        final EditText editText = dialog.findViewById(R.id.zhi);
        qd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int z = Integer.parseInt(editText.getText().toString());
                if (z >= 0 && z <= 30) {
                    huoqu3(z + "", "3");
                    dialog.dismiss();
                } else {
                    editText.setText("");
                    Toast.makeText(getContext(), "您输入的数值不正确，请重新输入", Toast.LENGTH_LONG).show();
                }


            }
        });
        WindowManager.LayoutParams layoutParams = dialog.getWindow().getAttributes();
        layoutParams.width = 600;
        layoutParams.height = 300;
        dialog.getWindow().setAttributes(layoutParams);
        dialog.show();
    }

    private void huoqu3(String wd, String bh) {
        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("update_wd")
                .setJsonObject("wd",wd)
                .setJsonObject("bianhao",bh)
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        Log.d("888888", "onResponse: ---");
                        huoqu1();
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();
    }

    private void huoqu2(String pd, String zt, String bh) {
        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("update_ktkg")
                .setJsonObject("pd",pd)
                .setJsonObject("zt",zt)
                .setJsonObject("bianhao",bh)
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        Log.d("888888", "onResponse: ---");
                        huoqu1();
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();
    }

    private void jianting() {
        sw31.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    huoqu2("灯光", "开启", "3");
                } else {
                    huoqu2("灯光", "关闭", "3");
                }
            }
        });
        sw32.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    huoqu2("空调", "开启", "3");
                } else {
                    huoqu2("空调", "关闭", "3");
                }
            }
        });
    }


    private void huoqu1() {
        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("get_ktkg")
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        JSONArray jsonArray = jsonObject.optJSONArray("ROWS_DETAIL");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject1 = jsonArray.optJSONObject(i);
                            if (jsonObject1.optString("bh").equals("3")) {
                                dg3.setText("灯光：" + jsonObject1.optString("dg"));
                                kt3.setText("空调：" + jsonObject1.optString("kt"));
                                ds3.setText(jsonObject1.optString("ds") + "度");
                                ms3.setText(jsonObject1.optString("ms"));
                                if (jsonObject1.optString("dg").equals("开启")) {
                                    sw31.setChecked(true);
                                } else {
                                    sw31.setChecked(false);
                                }
                                if (jsonObject1.optString("kt").equals("开启")) {
                                    sw32.setChecked(true);
                                } else {
                                    sw32.setChecked(false);
                                }
                            }
                        }
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();
    }

    private void huoqu() {
        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("get_hjzb")
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        JSONArray jsonArray = jsonObject.optJSONArray("ROWS_DETAIL");
                        JSONObject jsonObject1 = jsonArray.optJSONObject(0);
                        gz3.setText("光照：" + jsonObject1.optString("gz"));
                        wd3.setText("温度：" + jsonObject1.optString("wd"));
                        dl3.setText("电力：" + jsonObject1.optString("dl"));
                        xh3.setText("电力消耗：" + jsonObject1.optString("xh"));
                        ylrl3.setText("原材料容量：" + jsonObject1.optString("yl"));
                        qcrl3.setText("汽车容量：" + jsonObject1.optString("qc"));
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();
    }


    @OnClick({R.id.ms3, R.id.ds3})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ms3:
                if (ms3.getText().equals("冷风")) {
                    ms("3", "热风");
                } else {
                    ms("3", "冷风");
                }
                break;
            case R.id.ds3:
                ds();
                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
