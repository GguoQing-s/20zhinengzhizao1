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
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.a20zhinengzhizao1.R;
import com.example.a20zhinengzhizao1.adapter.Clwxadapter;
import com.example.a20zhinengzhizao1.adapter.Wwxadapter;
import com.example.a20zhinengzhizao1.adapter.Ywxadapter;
import com.example.a20zhinengzhizao1.bean.Wxcjqb;
import com.example.a20zhinengzhizao1.bean.Wxcjywwx;
import com.example.a20zhinengzhizao1.net.VolleyLo;
import com.example.a20zhinengzhizao1.net.VolleyTo;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class S_Fragment_rz extends Fragment {

    @BindView(R.id.spinner)
    Spinner spinner;
    @BindView(R.id.listView)
    ListView listView;
    @BindView(R.id.wx)
    Button wx;
    @BindView(R.id.qx)
    Button qx;
    @BindView(R.id.lin)
    LinearLayout lin;
    Unbinder unbinder;
    private String index;
    private List<Wxcjqb> mwxcjqb;
    private Clwxadapter clwxadapter;
    private List<Wxcjywwx> mwxcjyywx;
    private Ywxadapter ywxadapter;
    private Wwxadapter wwxadapter;
    private boolean is=false;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.s_fragment_rz, null);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        inview();
        jianting();

    }

    private void update(String t,String bh,String xh,String zt) {
        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("update_repair")
                .setJsonObject("zt",zt)
                .setJsonObject("wxsj",t)
                .setJsonObject("clbh",bh)
                .setJsonObject("clxh",xh)
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        Toast.makeText(getActivity(),"维修成功！",Toast.LENGTH_LONG).show();
                        is=true;
                        huoqu();

                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();
    }

    private void setadapter2() {
        wwxadapter = new Wwxadapter(getContext(), mwxcjyywx);
        listView.setAdapter(wwxadapter);
        wwxadapter.SetData(new Wwxadapter.SetData() {
            @Override
            public void setdata(int position, boolean xz) {
                Wxcjywwx wxcjywwx = mwxcjyywx.get(position);
                wxcjywwx.setXz(xz);
                mwxcjyywx.set(position, wxcjywwx);
            }
        });
    }

    private void setadapter1() {
        ywxadapter = new Ywxadapter(getContext(), mwxcjyywx);
        listView.setAdapter(ywxadapter);
    }

    private void huoqu1(String zt) {
        Log.d("22222222222222+++", "huoqu1: ------"+zt);
        mwxcjyywx.clear();
        for (int i = 0; i < mwxcjqb.size(); i++) {
            Wxcjqb wxcjqb = mwxcjqb.get(i);
            if (wxcjqb.getZt().equals(zt)) {
                mwxcjyywx.add(new Wxcjywwx(wxcjqb.getClbh(), wxcjqb.getClxh(), wxcjqb.getClwt()
                        , wxcjqb.getBxsj(), wxcjqb.getWxsj(), wxcjqb.getZt(), false));
            }
        }
        if (zt.equals("未修")) {
            setadapter2();
        }
        if (zt.equals("已修")) {
            setadapter1();
        }

    }

    private void setadapter() {
        clwxadapter = new Clwxadapter(getContext(), mwxcjqb);
        listView.setAdapter(clwxadapter);
    }

    private void inview() {
        mwxcjqb = new ArrayList<>();
        mwxcjyywx = new ArrayList<>();
    }

    private void huoqu() {
        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("get_repair")
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        mwxcjqb.clear();
                        JSONArray jsonArray = jsonObject.optJSONArray("ROWS_DETAIL");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject1 = jsonArray.optJSONObject(i);
                            mwxcjqb.add(new Wxcjqb(jsonObject1.optString("clbh")
                                    , jsonObject1.optString("clxh")
                                    , jsonObject1.optString("clwt")
                                    , jsonObject1.optString("bxsj")
                                    , jsonObject1.optString("wxsj")
                                    , jsonObject1.optString("zt")));
                        }
                        Log.d("88888888888888", "onResponse: ----"+mwxcjqb);
                        if (is)
                        {
                            huoqu1(spinner.getSelectedItem().toString());
                        }
                        if (index.equals("全部")) {
                            setadapter();
                        }
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
                if (index.equals("未修")) {
                    lin.setVisibility(View.VISIBLE);
                    huoqu1("未修");
                }
                if (index.equals("全部")) {
                    lin.setVisibility(View.INVISIBLE);
                    huoqu();
                }
                if (index.equals("已修")) {
                    lin.setVisibility(View.INVISIBLE);
                    huoqu1("已修");
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

    @OnClick({R.id.wx, R.id.qx})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.wx:
                String bh="",xh="";
                for (int i=0;i<mwxcjyywx.size();i++)
                {
                    Wxcjywwx wxcjywwx =mwxcjyywx.get(i);
                    if (wxcjywwx.isXz())
                    {
                        if (bh.equals(""))
                        {
                            bh+=wxcjywwx.getClbh();
                            xh+=wxcjywwx.getClxh();
                        }else {
                            bh+=","+wxcjywwx.getClbh();
                            xh+=","+wxcjywwx.getClxh();
                        }
                    }
                }
                SimpleDateFormat format  =new SimpleDateFormat("yyyy-MM-dd");
                Date date =new Date(System.currentTimeMillis());
                String t= format.format(date);

                String[] b= bh.split(",");
                String[] x = xh.split(",");

                for (int i=0;i<b.length;i++)
                {
                    update(t,b[i],x[i],"已修");
                }
                break;
            case R.id.qx:
                for (int i=0;i<mwxcjyywx.size();i++)
                {
                    Wxcjywwx wxcjywwx = mwxcjyywx.get(i);
                    wxcjywwx.setXz(false);
                    mwxcjyywx.set(i, wxcjywwx);
                }
                wwxadapter.notifyDataSetChanged();
                break;
        }
    }
}
