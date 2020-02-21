package com.example.a20zhinengzhizao1.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.android.volley.VolleyError;
import com.example.a20zhinengzhizao1.R;
import com.example.a20zhinengzhizao1.adapter.Xscpadapter;
import com.example.a20zhinengzhizao1.bean.Xslb;
import com.example.a20zhinengzhizao1.net.VolleyLo;
import com.example.a20zhinengzhizao1.net.VolleyTo;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

@SuppressLint("ValidFragment")
public class S_Fragment_xscp extends Fragment {
    @BindView(R.id.listView)
    ListView listView;
    Unbinder unbinder;
    private List<Xslb> mxslb;
    private Xscpadapter xscpadapter;
    private String cjh,scxh;
    public S_Fragment_xscp(String cjh,String scxh)
    {
        this.cjh=cjh;
        this.scxh=scxh;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.s_fragment_xscp, null);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mxslb = new ArrayList<>();
        huoqu();

    }

    private void setadapter() {
        xscpadapter = new Xscpadapter(getContext(),mxslb);
        listView.setAdapter(xscpadapter);
    }

    private void huoqu() {
        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("get_automobile")
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        JSONArray jsonArray = jsonObject.optJSONArray("ROWS_DETAIL");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject1 = jsonArray.optJSONObject(i);
                            if (jsonObject1.optString("cjh").equals(cjh)&&
                                    jsonObject1.optString("scxh").equals(scxh))
                            {
                                mxslb.add(new Xslb(jsonObject1.optString("cjh")
                                        ,jsonObject1.optString("scxh")
                                        ,jsonObject1.optString("name")
                                        ,jsonObject1.optString("xh")
                                        ,jsonObject1.optString("sl")));
                            }

                        }
                        Log.d("22222222", "onResponse: ---"+mxslb);
                        setadapter();
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
}
