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
public class S_Fragment_cj2 extends Fragment {

    @BindView(R.id.gz2)
    TextView gz2;
    @BindView(R.id.wd2)
    TextView wd2;
    @BindView(R.id.dl2)
    TextView dl2;
    @BindView(R.id.xh2)
    TextView xh2;
    @BindView(R.id.ylrl2)
    TextView ylrl2;
    @BindView(R.id.qcrl2)
    TextView qcrl2;
    @BindView(R.id.dg2)
    TextView dg2;
    @BindView(R.id.sw21)
    Switch sw21;
    @BindView(R.id.kt2)
    TextView kt2;
    @BindView(R.id.sw22)
    Switch sw22;
    @BindView(R.id.ms2)
    TextView ms2;
    @BindView(R.id.ds2)
    TextView ds2;
    Unbinder unbinder;
    @BindView(R.id.lin2)
    LinearLayout lin2;
    private List<Scxsj> mscxsj;

    public S_Fragment_cj2(List<Scxsj> mscxsj)
    {
        this.mscxsj=mscxsj;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.s_fragment_cj2, null);
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
            if (scxsj.getCjm().equals("2"))
            {
                if (scxsj.getScxm().equals(scxh))
                {
                    zt=scxsj.getZt();
                }
            }
        }

        Intent intent = new Intent(getContext(), S_SCXXXActivity.class);
        intent.putExtra("cjh","2");
        intent.putExtra("scxh",scxh);
        intent.putExtra("zt",zt);
        startActivity(intent);
    }

    private void settu() {

        lin2.removeAllViews();
        for (int i=0;i<mscxsj.size();i++)
        {
            Scxsj scxsj = mscxsj.get(i);
            if (scxsj.getCjm().equals("2"))
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
                lin2.addView(view,200, LinearLayout.LayoutParams.MATCH_PARENT);
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
                    huoqu3(z + "", "2");
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
        sw21.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    huoqu2("灯光", "开启", "2");
                } else {
                    huoqu2("灯光", "关闭", "2");
                }
            }
        });
        sw22.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    huoqu2("空调", "开启", "2");
                } else {
                    huoqu2("空调", "关闭", "2");
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
                            if (jsonObject1.optString("bh").equals("2")) {
                                dg2.setText("灯光：" + jsonObject1.optString("dg"));
                                kt2.setText("空调：" + jsonObject1.optString("kt"));
                                ds2.setText(jsonObject1.optString("ds") + "度");
                                ms2.setText(jsonObject1.optString("ms"));
                                if (jsonObject1.optString("dg").equals("开启")) {
                                    sw21.setChecked(true);
                                } else {
                                    sw21.setChecked(false);
                                }
                                if (jsonObject1.optString("kt").equals("开启")) {
                                    sw22.setChecked(true);
                                } else {
                                    sw22.setChecked(false);
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
                        gz2.setText("光照：" + jsonObject1.optString("gz"));
                        wd2.setText("温度：" + jsonObject1.optString("wd"));
                        dl2.setText("电力：" + jsonObject1.optString("dl"));
                        xh2.setText("电力消耗：" + jsonObject1.optString("xh"));
                        ylrl2.setText("原材料容量：" + jsonObject1.optString("yl"));
                        qcrl2.setText("汽车容量：" + jsonObject1.optString("qc"));
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();
    }


    @OnClick({R.id.ms2, R.id.ds2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ms2:
                if (ms2.getText().equals("冷风")) {
                    ms("2", "热风");
                } else {
                    ms("2", "冷风");
                }
                break;
            case R.id.ds2:
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
