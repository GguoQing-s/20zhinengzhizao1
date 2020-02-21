package com.example.a20zhinengzhizao1.fragment;


import android.app.Fragment;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.CustomListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.example.a20zhinengzhizao1.R;
import com.example.a20zhinengzhizao1.activity.S_PFTPActivity;
import com.example.a20zhinengzhizao1.adapter.Jyadapter;
import com.example.a20zhinengzhizao1.adapter.Sjpxadapter;
import com.example.a20zhinengzhizao1.adapter.Yllbadapter;
import com.example.a20zhinengzhizao1.bean.Jy;
import com.example.a20zhinengzhizao1.bean.Sjpx;
import com.example.a20zhinengzhizao1.bean.Yllb;
import com.example.a20zhinengzhizao1.net.VolleyLo;
import com.example.a20zhinengzhizao1.net.VolleyTo;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class S_Fragment_yuanliao extends Fragment {
    @BindView(R.id.listView)
    ListView listView;
    @BindView(R.id.cishu)
    TextView cishu;
    @BindView(R.id.jine)
    TextView jine;
    @BindView(R.id.qk)
    Button qk;
    @BindView(R.id.listView1)
    ListView listView1;
    @BindView(R.id.tv1)
    TextView tv1;
    @BindView(R.id.tv2)
    TextView tv2;
    private Unbinder mUnbinder;
    private List<Yllb> myllb;
    private List<Jy> mjy;
    private TimePickerView timePickerView;
    private Yllbadapter myllbadapter;
    private Jyadapter mjyadapter;
    private List<Sjpx> msjpx;
    private Sjpxadapter msjpxadapter;
    private String cs;
    private int select = 0, sum = 0;
    private String path1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.s_fragment_yuanliao, null);
        mUnbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        inview();
        huoqu();

        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("get_supplier_transaction")
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        JSONArray jsonArray = jsonObject.optJSONArray("ROWS_DETAIL");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject1 = jsonArray.optJSONObject(i);
                            mjy.add(new Jy(jsonObject1.optString("time")
                                    , jsonObject1.optString("dj")
                                    , jsonObject1.optString("sl")
                                    , jsonObject1.optString("zjine")
                                    , jsonObject1.optString("zh")
                                    , jsonObject1.optString("cgy")
                                    , jsonObject1.optString("lxr")
                                    , jsonObject1.optString("csm")
                                    , jsonObject1.optString("clm")));

                        }
                        Log.d("111111", "onResponse: ----"+mjy);
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();

    }

    private void sjpxjs() {
        for (int i = 0; i < msjpx.size(); i++) {
            Sjpx sjpx = msjpx.get(i);
            if (sjpx.getCsm().equals(cs)) {
                select++;
                sum += Integer.parseInt(sjpx.getZje());
            }
        }
        Collections.sort(msjpx, new Comparator<Sjpx>() {
            @Override
            public int compare(Sjpx o1, Sjpx o2) {
                try {
                    String time1 = o1.getTime();
                    String time2 = o2.getTime();
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    Date d1 = format.parse(time1);
                    Date d2 = format.parse(time2);
                    if (d1.getTime() < d2.getTime()) {
                        return 1;
                    } else if (d1.getTime() == d2.getTime()) {
                        return 0;
                    } else {
                        return -1;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return 0;
            }
        });
    }

    private void setadapter2() {
        cishu.setText("总供货次数：" + select + "次");
        jine.setText("总交易金额：" + sum + "元");
        msjpxadapter = new Sjpxadapter(getActivity(),msjpx);
        listView1.setAdapter(msjpxadapter);
    }

    private void pandun() {

        if (!tv1.getText().toString().equals("")&&tv2.getText().toString().equals(""))
        {
            for (int i=0;i<mjy.size();i++)
            {
                Jy jy = mjy.get(i);
                SimpleDateFormat format  =new SimpleDateFormat("yyyy-MM-dd");
               try {
                   Date d1 = format.parse(tv1.getText().toString());
                   Date hq= format.parse(jy.getTime());
                   if (hq.getTime()>d1.getTime())
                   {
                       msjpx.add(new Sjpx(jy.getTime(),jy.getDj(),jy.getSl(),jy.getZje(),jy.getZh(),jy.getCgy()
                       ,jy.getLxr(),jy.getCsm(),jy.getClm()));
                   }
               }catch (Exception e)
               {
                   e.printStackTrace();
               }
            }

            sjpxjs();

            setadapter2();
        }
        if (!tv2.getText().toString().equals("")&&tv1.getText().toString().equals(""))
        {
            for (int i=0;i<mjy.size();i++)
            {
                Jy jy = mjy.get(i);
                SimpleDateFormat format  =new SimpleDateFormat("yyyy-MM-dd");
                try {
                    Date d2 = format.parse(tv2.getText().toString());
                    Date hq= format.parse(jy.getTime());
                    if (hq.getTime()<d2.getTime())
                    {
                        msjpx.add(new Sjpx(jy.getTime(),jy.getDj(),jy.getSl(),jy.getZje(),jy.getZh(),jy.getCgy()
                                ,jy.getLxr(),jy.getCsm(),jy.getClm()));
                    }
                }catch (Exception e)
                {
                    e.printStackTrace();
                }
            }

            sjpxjs();
            setadapter2();
        }

        if (!tv2.getText().toString().equals("")&&!tv1.getText().toString().equals(""))
        {
            for (int i=0;i<mjy.size();i++)
            {
                Jy jy = mjy.get(i);
                SimpleDateFormat format  =new SimpleDateFormat("yyyy-MM-dd");
                try {
                    Date d1 = format.parse(tv1.getText().toString());
                    Date d2 = format.parse(tv2.getText().toString());
                    Date hq= format.parse(jy.getTime());
                    if (hq.getTime()<d2.getTime()&&hq.getTime()>d1.getTime())
                    {
                        msjpx.add(new Sjpx(jy.getTime(),jy.getDj(),jy.getSl(),jy.getZje(),jy.getZh(),jy.getCgy()
                                ,jy.getLxr(),jy.getCsm(),jy.getClm()));
                    }
                }catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
            sjpxjs();
            setadapter2();
        }
    }

    private void setadapter1() {
        cishu.setText("总供货次数：" + select + "次");
        jine.setText("总交易金额：" + sum + "元");
        mjyadapter = new Jyadapter(getActivity(), mjy);
        listView1.setAdapter(mjyadapter);
        mjyadapter.SetData(new Jyadapter.SetData() {
            @Override
            public void setdata(int position) {
                Intent intent = new Intent(getActivity(), S_PFTPActivity.class);
                intent.putExtra("path",path1);
                startActivity(intent);
            }
        });

    }

    private void huoqu1(final String cs, final String clm) {
        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("get_supplier_transaction")
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        JSONArray jsonArray = jsonObject.optJSONArray("ROWS_DETAIL");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject1 = jsonArray.optJSONObject(i);
                            if (jsonObject1.optString("csm").equals(cs) && jsonObject1.optString("clm").equals(clm)) {
                                mjy.add(new Jy(jsonObject1.optString("time")
                                        , jsonObject1.optString("dj")
                                        , jsonObject1.optString("sl")
                                        , jsonObject1.optString("zjine")
                                        , jsonObject1.optString("zh")
                                        , jsonObject1.optString("cgy")
                                        , jsonObject1.optString("lxr")
                                        , jsonObject1.optString("csm")
                                        , jsonObject1.optString("clm")));
                            }

                        }
                        for (int i = 0; i < mjy.size(); i++) {
                            Jy jy = mjy.get(i);
                            if (jy.getCsm().equals(cs)) {
                                select++;
                                sum += Integer.parseInt(jy.getZje());
                            }
                        }
                        Collections.sort(mjy, new Comparator<Jy>() {
                            @Override
                            public int compare(Jy o1, Jy o2) {
                                try {
                                    String time1 = o1.getTime();
                                    String time2 = o2.getTime();
                                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                                    Date d1 = format.parse(time1);
                                    Date d2 = format.parse(time2);
                                    if (d1.getTime() < d2.getTime()) {
                                        return 1;
                                    } else if (d1.getTime() == d2.getTime()) {
                                        return 0;
                                    } else {
                                        return -1;
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                return 0;
                            }
                        });
                        setadapter1();
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();
    }

    private void setadapter() {
        myllbadapter = new Yllbadapter(getActivity(), myllb);
        listView.setAdapter(myllbadapter);
        myllbadapter.SetData(new Yllbadapter.SetData() {
            @Override
            public void setdata(String csm, String clm,String path) {
                path1=path;
                mjy.clear();
                select = 0;
                sum = 0;
                cs=csm;
                huoqu1(csm, clm);
            }
        });
    }

    private void inview() {
        myllb = new ArrayList<>();
        mjy = new ArrayList<>();
        msjpx = new ArrayList<>();

    }

    private void huoqu() {
        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("get_supplier_material")
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        JSONArray jsonArray = jsonObject.optJSONArray("ROWS_DETAIL");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject1 = jsonArray.optJSONObject(i);
                            myllb.add(new Yllb(jsonObject1.optString("name")
                                    , jsonObject1.optString("xh")
                                    , jsonObject1.optString("cshang")
                                    , jsonObject1.optString("cs")
                                    , jsonObject1.optString("path")));
                        }
                        Log.d("2222222222", "onResponse: ++++++++++++"+myllb);
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
        mUnbinder.unbind();
    }

    @OnClick({R.id.tv1, R.id.tv2, R.id.qk})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv1:
                if (mjy.size()==0)
                {
                    Toast.makeText(getActivity(),"当前还没有交易详情，不能选择时间",Toast.LENGTH_LONG).show();
                }else {
                    getData();
                }
                break;
            case R.id.tv2:
                if (mjy.size()==0)
                {
                    Toast.makeText(getActivity(),"当前还没有交易详情，不能选择时间",Toast.LENGTH_LONG).show();
                }else {
                    getData1();
                }
                break;
            case R.id.qk:
                tv1.setText("");
                tv2.setText("");
               msjpx.clear();
               setadapter1();
                break;
        }
    }

    private void getData() {
        timePickerView = new TimePickerBuilder(getActivity(), new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                String t = format.format(date);
                  tv1.setText(t);
                timePickerView.dismiss();
            }
        }).setLayoutRes(R.layout.time_dialog, new CustomListener() {
            @Override
            public void customLayout(View v) {
                TextView cancel = v.findViewById(R.id.cancel);
                TextView set = v.findViewById(R.id.set);
                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        timePickerView.dismiss();
                    }
                });
                set.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        select=0;
                        sum=0;
                        timePickerView.returnData();
                        pandun();
                    }
                });
            }
        }).setType(new boolean[]{true, true, true, false, false, false})
                .setLabel("", "", "", "", "", "")
                .setLineSpacingMultiplier(3f)
                .setDividerColor(Color.parseColor("#0098FE"))
                .setOutSideCancelable(false)
                .isDialog(true)
                .build();
        timePickerView.show();
    }
    private void getData1() {
        timePickerView = new TimePickerBuilder(getActivity(), new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                String t = format.format(date);
                tv2.setText(t);
                timePickerView.dismiss();
            }
        }).setLayoutRes(R.layout.time_dialog, new CustomListener() {
            @Override
            public void customLayout(View v) {
                TextView cancel = v.findViewById(R.id.cancel);
                TextView set = v.findViewById(R.id.set);
                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        timePickerView.dismiss();
                    }
                });
                set.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        select=0;
                        sum=0;
                        timePickerView.returnData();
                        pandun();
                    }
                });
            }
        }).setType(new boolean[]{true, true, true, false, false, false})
                .setLabel("", "", "", "", "", "")
                .setLineSpacingMultiplier(3f)
                .setDividerColor(Color.parseColor("#0098FE"))
                .setOutSideCancelable(false)
                .isDialog(true)
                .build();
        timePickerView.show();
    }

}
