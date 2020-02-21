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
import com.example.a20zhinengzhizao1.AppClient;
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
public class S_Fragment_cj1 extends Fragment {
    @BindView(R.id.gz)
    TextView gz;
    @BindView(R.id.wd)
    TextView wd;
    @BindView(R.id.dl)
    TextView dl;
    @BindView(R.id.xh)
    TextView xh;
    @BindView(R.id.ylrl)
    TextView ylrl;
    @BindView(R.id.qcrl)
    TextView qcrl;
    Unbinder unbinder;
    @BindView(R.id.dg1)
    TextView dg1;
    @BindView(R.id.sw11)
    Switch sw11;
    @BindView(R.id.kt1)
    TextView kt1;
    @BindView(R.id.sw12)
    Switch sw12;
    @BindView(R.id.ds1)
    TextView ds1;
    @BindView(R.id.ms1)
    TextView ms1;
    @BindView(R.id.lin)
    LinearLayout lin;
    private List<Scxsj> mscxsj;

    public S_Fragment_cj1(List<Scxsj> mscxsj)
    {
        this.mscxsj=mscxsj;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.s_fragment_cj1, null);
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
            if (scxsj.getCjm().equals("1"))
            {
                if (scxsj.getScxm().equals(scxh))
                {
                    zt=scxsj.getZt();
                }
            }
        }

        Intent intent = new Intent(getContext(), S_SCXXXActivity.class);
        intent.putExtra("cjh","1");
        intent.putExtra("scxh",scxh);
        intent.putExtra("zt",zt);
        startActivity(intent);
    }

    private void settu() {

        lin.removeAllViews();
        for (int i=0;i<mscxsj.size();i++)
        {
            Scxsj scxsj = mscxsj.get(i);
            if (scxsj.getCjm().equals("1"))
            {
                final View view  =LayoutInflater.from(getContext()).inflate(R.layout.scx_item,null,false);
                TextView tv1 = view.findViewById(R.id.tv1);
                if (scxsj.getZt().equals("建设中"))
                {
                    tv1.setBackgroundResource(R.drawable.lsk);
                }
                if (scxsj.getZt().equals("缺原材料"))
                {
                    tv1.setBackgroundResource(R.drawable.hsk);
                }
                if (scxsj.getZt().equals("生产中"))
                {
                    tv1.setBackgroundResource(R.drawable.lvsk);
                }
                if (scxsj.getZt().equals("库存已满"))
                {
                    tv1.setBackgroundResource(R.drawable.hosk);
                }
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        TextView tv2 = view.findViewById(R.id.tv1);
                        String name= tv2.getText().toString();
                        setdialog(name.substring(name.length()-1));
                    }
                });
                tv1.setText("生产线"+scxsj.getScxm());
                lin.addView(view,200, LinearLayout.LayoutParams.MATCH_PARENT);
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
                    huoqu3(z + "", "1");
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
        sw11.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    huoqu2("灯光", "开启", "1");
                } else {
                    huoqu2("灯光", "关闭", "1");
                }
            }
        });
        sw12.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    huoqu2("空调", "开启", "1");
                } else {
                    huoqu2("空调", "关闭", "1");
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
                            if (jsonObject1.optString("bh").equals("1")) {
                                dg1.setText("灯光：" + jsonObject1.optString("dg"));
                                kt1.setText("空调：" + jsonObject1.optString("kt"));
                                ds1.setText(jsonObject1.optString("ds") + "度");
                                ms1.setText(jsonObject1.optString("ms"));
                                if (jsonObject1.optString("dg").equals("开启")) {
                                    sw11.setChecked(true);
                                } else {
                                    sw11.setChecked(false);
                                }
                                if (jsonObject1.optString("kt").equals("开启")) {
                                    sw12.setChecked(true);
                                } else {
                                    sw12.setChecked(false);
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
                        gz.setText("光照：" + jsonObject1.optString("gz"));
                        wd.setText("温度：" + jsonObject1.optString("wd"));
                        dl.setText("电力：" + jsonObject1.optString("dl"));
                        xh.setText("电力消耗：" + jsonObject1.optString("xh"));
                        ylrl.setText("原材料容量：" + jsonObject1.optString("yl"));
                        qcrl.setText("汽车容量：" + jsonObject1.optString("qc"));
                        Log.d("777777777", "onResponse: -------");
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.ms1, R.id.ds1})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ms1:
                if (ms1.getText().equals("冷风")) {
                    ms("1", "热风");
                } else {
                    ms("1", "冷风");
                }

                break;
            case R.id.ds1:
                ds();
                break;
        }
    }
}
