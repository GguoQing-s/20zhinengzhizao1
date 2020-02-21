package com.example.a20zhinengzhizao1.fragment;

import android.content.Intent;
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
import com.example.a20zhinengzhizao1.activity.S_XQYActivity;
import com.example.a20zhinengzhizao1.adapter.Jcadapter;
import com.example.a20zhinengzhizao1.bean.Clkc;
import com.example.a20zhinengzhizao1.net.VolleyLo;
import com.example.a20zhinengzhizao1.net.VolleyTo;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class S_Fragment_suv extends Fragment {
    @BindView(R.id.listView)
    ListView listView;
    Unbinder unbinder;
    private List<Clkc> mjc;

    private Jcadapter mjcadapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.s_fragment_suv, null);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        inview();


    }

    private void setadapter() {
        mjcadapter = new Jcadapter(getContext(),mjc);
        listView.setAdapter(mjcadapter);
        mjcadapter.SetData(new Jcadapter.SetData() {
            @Override
            public void setdata(int position, String name, String clxh, String jb, String cs, String lx, String hbbz, String sssj, String jg, String sl, String sms, String cspz, String video, String image) {
                Intent intent = new Intent(getActivity(), S_XQYActivity.class);
                intent.putExtra("name",name);
                intent.putExtra("clxh",clxh);
                intent.putExtra("jb",jb);
                intent.putExtra("cs",cs);
                intent.putExtra("lx",lx);
                intent.putExtra("hbbz",hbbz);
                intent.putExtra("sssj",sssj);
                intent.putExtra("jg",jg);
                intent.putExtra("sl",sl);
                intent.putExtra("sms",sms);
                intent.putExtra("cspz",cspz);
                intent.putExtra("video",video);
                intent.putExtra("image",image);
                startActivity(intent);
            }
        });
    }

    private void inview() {
        mjc = new ArrayList<>();
    }

    private void huoqu() {
        VolleyTo volleyTo  =new VolleyTo();
        volleyTo.setUrl("get_vehiclea")
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        JSONArray jsonArray = jsonObject.optJSONArray("ROWS_DETAIL");
                        for (int i=0;i<jsonArray.length();i++)
                        {
                            JSONObject jsonObject1 = jsonArray.optJSONObject(i);
                            if (jsonObject1.optString("lx").equals("SUV"))
                            {
                                mjc.add(new Clkc(jsonObject1.optString("name")
                                ,jsonObject1.optString("clxh")
                                        ,jsonObject1.optString("jb")
                                        ,jsonObject1.optString("cs")
                                        ,jsonObject1.optString("lx")
                                        ,jsonObject1.optString("hbbz")
                                        ,jsonObject1.optString("sssj")
                                        ,jsonObject1.optString("jg")
                                        ,jsonObject1.optString("sl")
                                        ,jsonObject1.optString("sms")
                                        ,jsonObject1.optString("cspz")
                                        ,jsonObject1.optString("video")
                                        ,jsonObject1.optString("image")));
                            }
                        }
                        setadapter();
                        //Log.d("----------------------", "onResponse: -----"+mjc);
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
    @Override
    public void onResume() {
        super.onResume();
        mjc.clear();
        huoqu();
        Log.d("*********", "onResume: --------");
    }
}
