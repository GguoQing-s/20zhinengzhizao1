package com.example.a20zhinengzhizao1.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.android.volley.VolleyError;
import com.example.a20zhinengzhizao1.AppClient;
import com.example.a20zhinengzhizao1.R;
import com.example.a20zhinengzhizao1.adapter.Wdddadapter;
import com.example.a20zhinengzhizao1.bean.Wddd;
import com.example.a20zhinengzhizao1.net.VolleyLo;
import com.example.a20zhinengzhizao1.net.VolleyTo;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class S_Fragment_yzf extends Fragment {
    @BindView(R.id.listView)
    ListView listView;
    Unbinder unbinder;
    private List<Wddd> mwddd;
    private Wdddadapter wdddadapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.s_fragment_yzf, null);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        inview();
        huoqu();
    }

    private void huoqu() {
        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("get_order")
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        JSONArray jsonArray = jsonObject.optJSONArray("ROWS_DETAIL");
                        for (int i=0;i<jsonArray.length();i++)
                        {
                            JSONObject jsonObject1= jsonArray.optJSONObject(i);
                            mwddd.add(new Wddd(jsonObject1.optString("name")
                                    ,jsonObject1.optString("jine")
                                    ,jsonObject1.optString("time")
                            ));
                        }
                        setadapter();
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();
    }

    private void inview() {
        mwddd = new ArrayList<>();
    }

    private void setadapter() {
        wdddadapter = new Wdddadapter(getContext(),mwddd);
        listView.setAdapter(wdddadapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
