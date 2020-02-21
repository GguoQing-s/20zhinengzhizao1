package com.example.a20zhinengzhizao1.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.android.volley.VolleyError;
import com.example.a20zhinengzhizao1.R;
import com.example.a20zhinengzhizao1.activity.S_CKActivity;
import com.example.a20zhinengzhizao1.adapter.Ckadapter;
import com.example.a20zhinengzhizao1.bean.Ck;
import com.example.a20zhinengzhizao1.bean.Rkjl;
import com.example.a20zhinengzhizao1.bean.Rkjl1;
import com.example.a20zhinengzhizao1.net.VolleyLo;
import com.example.a20zhinengzhizao1.net.VolleyTo;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class S_Fragment_ck extends Fragment {
    @BindView(R.id.listView2)
    ListView listView;
    Unbinder unbinder;
    private List<Ck> mck;
    private Ckadapter ckadapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.s_fragment_ck, null);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        inview();
        huoqu();

    }

    private void setadapter() {
        ckadapter = new Ckadapter(getActivity(),mck);
        listView.setAdapter(ckadapter);
        ckadapter.SetData(new Ckadapter.SetData() {
            @Override
            public void setdata(String name, String xh,String path) {
                Intent intent = new Intent(getActivity(), S_CKActivity.class);
                intent.putExtra("name",name);
                intent.putExtra("xh",xh);
                intent.putExtra("path",path);
                startActivity(intent);
            }
        });
    }

    private void inview() {
        mck = new ArrayList<>();
    }

    private void huoqu() {
        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("get_stock_warehousing")
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        JSONArray jsonArray = jsonObject.optJSONArray("ROWS_DETAIL");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject1 = jsonArray.optJSONObject(i);
                            mck.add(new Ck(jsonObject1.optString("name"),
                                    jsonObject1.optString("xh"),
                                    jsonObject1.optString("gys"),
                                    jsonObject1.optString("shuliang"),
                                    jsonObject1.optString("dj"),
                                    jsonObject1.optString("weizhi"),
                                    jsonObject1.optString("caigoyuan"),
                                    jsonObject1.optString("lianxiren"),
                                    jsonObject1.optString("zhanghao"),
                                    jsonObject1.optString("ren"),
                                    jsonObject1.optString("time"),
                                    jsonObject1.optString("path")));

                        }
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
