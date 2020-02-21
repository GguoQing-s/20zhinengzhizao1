package com.example.a20zhinengzhizao1.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.a20zhinengzhizao1.R;
import com.example.a20zhinengzhizao1.net.VolleyLo;
import com.example.a20zhinengzhizao1.net.VolleyTo;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class S_Fragment_bx extends Fragment {
    @BindView(R.id.clbh)
    EditText clbh;
    @BindView(R.id.clxh)
    EditText clxh;
    @BindView(R.id.spinner)
    Spinner spinner;
    @BindView(R.id.qt)
    EditText qt;
    @BindView(R.id.tj)
    Button tj;
    @BindView(R.id.qx)
    Button qx;
    Unbinder unbinder;
    private String index,select;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.s_fragment_bx, null);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        jianting();

    }

    private void huoqu(String bh,String xh,String wt,String time,String zt) {
        VolleyTo volleyTo  =new VolleyTo();
        volleyTo.setUrl("set_repair")
                .setJsonObject("clbh",bh)
                .setJsonObject("clxh",xh)
                .setJsonObject("clwt",wt)
                .setJsonObject("wxsj","无")
                .setJsonObject("zt",zt)
                .setJsonObject("bxsj",time)
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        Toast.makeText(getContext(),"提交成功！",Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();
    }

    private void jianting() {
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                index = spinner.getSelectedItem().toString();
                if (index.equals("其它")) {
                    qt.setVisibility(View.VISIBLE);
                } else {
                    qt.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.tj, R.id.qx})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tj:
                SimpleDateFormat format  =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date =new Date(System.currentTimeMillis());
                String t= format.format(date);
                String bh = clbh.getText().toString();
                String xh = clxh.getText().toString();
                if (index.equals("其它")) {
                    select=qt.getText().toString();
                } else {
                    select=index;
                }
                if (bh.equals(""))
                {
                    Toast.makeText(getContext(),"编号不能为空！",Toast.LENGTH_LONG).show();
                    return;
                }
                if (xh.equals(""))
                {
                    Toast.makeText(getContext(),"型号不能为空！",Toast.LENGTH_LONG).show();
                    return;
                }
                if (select.equals(""))
                {
                    Toast.makeText(getContext(),"问题不能为空！",Toast.LENGTH_LONG).show();
                    return;
                }
                huoqu(bh,xh,select,t,"未修");
                break;
            case R.id.qx:
                clbh.setText("");
                clxh.setText("");
                qt.setText("");
                spinner.setSelection(0);
                break;
        }
    }
}
