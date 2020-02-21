package com.example.a20zhinengzhizao1.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.a20zhinengzhizao1.R;
import com.example.a20zhinengzhizao1.bean.Gystj;
import com.example.a20zhinengzhizao1.bean.Noyw;
import com.example.a20zhinengzhizao1.bean.Qbyw;
import com.example.a20zhinengzhizao1.bean.Yesyw;
import com.example.a20zhinengzhizao1.bean.jyje;
import com.example.a20zhinengzhizao1.net.VolleyLo;
import com.example.a20zhinengzhizao1.net.VolleyTo;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.PercentFormatter;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class S_GYSTJActivity extends AppCompatActivity {
    @BindView(R.id.change)
    ImageView change;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.barChart)
    BarChart barChart;
    @BindView(R.id.pieChart)
    PieChart pieChart;
    @BindView(R.id.pieChart1)
    PieChart pieChart1;
    @BindView(R.id.barChart1)
    BarChart barChart1;
    @BindView(R.id.lin1)
    LinearLayout lin1;
    @BindView(R.id.lin2)
    LinearLayout lin2;
    @BindView(R.id.lin4)
    LinearLayout lin4;
    @BindView(R.id.lin3)
    LinearLayout lin3;
    private List<Gystj> mgystj;
    private Map<String, Integer> cs;
    private List<Map.Entry<String, Integer>> listttype;
    private BarDataSet dataSet;
    private BarData data;
    private List<BarEntry> mY;
    private List<String> mX;
    private int he, he1;
    private PieDataSet dataSet1;
    private PieData data1;
    private List<PieEntry> pieEntries;
    private List<Integer> color;
    private Map<String, Integer> jy;
    private List<Map.Entry<String, Integer>> listttype1;
    private List<jyje> mjyje;
    private int jh = 0;
    private List<Yesyw> myesyw;
    private List<Noyw> mnoyw;
    private List<Qbyw> mqbyw;
    private Map<String, Integer> cp;
    private List<Map.Entry<String, Integer>> listttype2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.s_gystjactivity);
        ButterKnife.bind(this);
        inview();
        huoqu();
        huoqu1();
        huoqu2();


    }

    private void settu2() {
        pieEntries = new ArrayList<>();
        color = new ArrayList<>();
        for (int i = 0; i < listttype2.size(); i++) {
            String[] s = listttype2.get(i).toString().split("=");
            pieEntries.add(new PieEntry(Integer.parseInt(s[1]), s[0]));

        }
        color.add(Color.parseColor("#AA4643"));
        dataSet1 = new PieDataSet(pieEntries, "");
        dataSet1.setColors(color);
        dataSet1 = new PieDataSet(pieEntries, "");
        dataSet1.setYValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
        dataSet1.setXValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
        dataSet1.setValueLinePart1OffsetPercentage(80f);
        dataSet1.setValueLinePart2Length(0.5f);
        dataSet1.setValueLinePart1Length(0.5f);
        dataSet1.setColors(color);
        data1 = new PieData(dataSet1);
        pieChart.setData(data1);
        pieChart.setDrawHoleEnabled(false);
        pieChart.setEntryLabelColor(Color.BLACK);
        pieChart.setDescription(null);
        pieChart.setRotationEnabled(false);
        pieChart.getLegend().setEnabled(false);
        pieChart.setTouchEnabled(false);
        pieChart.invalidate();
    }

    private void cpsl() {
        for (int i = 0; i < mgystj.size(); i++) {
            String type = mgystj.get(i).getName();
            if (cp.get(type) == null) {
                cp.put(type, 1);
            } else {
                cp.put(type, cp.get(type) + 1);
            }
        }
        listttype2 = new ArrayList<>(cp.entrySet());
        Log.d("-------", "cpsl: ------" + listttype2);
    }

    private void settu3() {

        pieEntries = new ArrayList<>();
        pieEntries.add(new PieEntry(((float) myesyw.size() / (float) mqbyw.size()) * 100, "有业务"));
        pieEntries.add(new PieEntry((1 - (float) myesyw.size() / (float) mqbyw.size()) * 100, "没业务"));
        color = new ArrayList<>();
        color.add(Color.parseColor("#466AA0"));
        color.add(Color.parseColor("#31121D"));
        dataSet1 = new PieDataSet(pieEntries, "");
        dataSet1.setColors(color);
        dataSet1 = new PieDataSet(pieEntries, "");
        dataSet1.setYValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
        dataSet1.setXValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
        dataSet1.setValueLinePart1OffsetPercentage(80f);
        dataSet1.setValueLinePart2Length(0.5f);
        dataSet1.setValueLinePart1Length(0.5f);
        dataSet1.setColors(color);

        data1 = new PieData(dataSet1);
        data1.setValueFormatter(new PercentFormatter());
        pieChart1.setData(data1);
        pieChart1.setUsePercentValues(true);
        pieChart1.setDrawHoleEnabled(false);
        pieChart1.setEntryLabelColor(Color.BLACK);
        pieChart1.setDescription(null);
        pieChart1.setRotationEnabled(false);
        pieChart1.getLegend().setEnabled(false);
        pieChart1.setTouchEnabled(false);
        pieChart1.invalidate();
    }

    private void huoqu3(final String cs) {

        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("get_supplier_transaction")
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        JSONArray jsonArray = jsonObject.optJSONArray("ROWS_DETAIL");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject1 = jsonArray.optJSONObject(i);
                            for (int y = mqbyw.size(); y > 0; y--) {
                                Qbyw qbyw = mqbyw.get(y - 1);
                                if (qbyw.getName().equals(cs)) {
                                    mqbyw.remove(y - 1);
                                }
                            }
                            mqbyw.add(new Qbyw(cs));
                            if (jsonObject1.optString("csm").equals(cs)) {
                                for (int y = myesyw.size(); y > 0; y--) {
                                    Yesyw yesyw = myesyw.get(y - 1);
                                    if (yesyw.getName().equals(cs)) {
                                        myesyw.remove(y - 1);
                                    }
                                }
                                myesyw.add(new Yesyw(cs));
                            }
                        }
                        settu3();
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();
    }

    private void huoqu2() {
        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("get_supplier_material")
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        JSONArray jsonArray = jsonObject.optJSONArray("ROWS_DETAIL");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject1 = jsonArray.optJSONObject(i);
                            huoqu3(jsonObject1.optString("cshang"));
                        }
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();
    }

    private void settu4() {
        for (int i = 0; i < listttype1.size(); i++) {
            String[] s = listttype1.get(i).toString().split("=");
            he1 += Integer.parseInt(s[1]);
        }
        mX = new ArrayList<>();
        mY = new ArrayList<>();
        addData1();
        dataSet = new BarDataSet(mY, "");
        dataSet.setColors(Color.GRAY);
        data = new BarData(dataSet);
        data.setValueTextColor(Color.GRAY);
        barChart1.setData(data);
        barChart1.getAxisRight().setEnabled(false);
        barChart1.getAxisLeft().setStartAtZero(true);
        XAxis xAxis = barChart1.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setValueFormatter(new IndexAxisValueFormatter(mX));
        xAxis.setGranularity(1f);
        xAxis.setDrawGridLines(false);
        barChart1.setDescription(null);
        barChart1.getLegend().setEnabled(false);
        barChart1.setDoubleTapToZoomEnabled(false);
        barChart1.setTouchEnabled(false);
        barChart1.invalidate();
    }

    private void addData1() {
        for (int i = 0; i < listttype1.size(); i++) {

            String[] s = listttype1.get(i).toString().split("=");
            mX.add(s[0]);
            mY.add(new BarEntry(i, Integer.parseInt(s[1])));

        }
    }

    private void je() {
        for (int i = 0; i < mjyje.size(); i++) {
            String type = mjyje.get(i).getCsm();
            if (jy.get(type) == null) {
                jy.put(type, Integer.parseInt(mjyje.get(i).getZje()));
            } else {
                jy.put(type, jy.get(type) + Integer.parseInt(mjyje.get(i).getZje()));
            }
        }
        listttype1 = new ArrayList<>(jy.entrySet());
    }

    private void huoqu1() {
        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("get_supplier_transaction")
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        JSONArray jsonArray = jsonObject.optJSONArray("ROWS_DETAIL");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject1 = jsonArray.optJSONObject(i);
                            mjyje.add(new jyje(jsonObject1.optString("time")
                                    , jsonObject1.optString("dj")
                                    , jsonObject1.optString("sl")
                                    , jsonObject1.optString("zjine")
                                    , jsonObject1.optString("zh")
                                    , jsonObject1.optString("cgy")
                                    , jsonObject1.optString("lxr")
                                    , jsonObject1.optString("csm")
                                    , jsonObject1.optString("clm")));
                        }
                        je();
                        settu4();
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();
    }

    private void gyssl() {
        for (int i = 0; i < mgystj.size(); i++) {
            String type = mgystj.get(i).getChengshi();
            if (cs.get(type) == null) {
                cs.put(type, 1);
            } else {
                cs.put(type, cs.get(type) + 1);
            }
        }
        listttype = new ArrayList<>(cs.entrySet());
    }

    private void settu1() {
        for (int i = 0; i < listttype.size(); i++) {
            String[] s = listttype.get(i).toString().split("=");
            he += Integer.parseInt(s[1]);
        }
        mX = new ArrayList<>();
        mY = new ArrayList<>();
        addData();
        dataSet = new BarDataSet(mY, "");
        dataSet.setColors(Color.GRAY);
        data = new BarData(dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextColor(Color.GRAY);
        barChart.setData(data);
        barChart.getAxisRight().setEnabled(false);
        barChart.getAxisLeft().setStartAtZero(true);
        XAxis xAxis = barChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setValueFormatter(new IndexAxisValueFormatter(mX));
        xAxis.setGranularity(1f);
        xAxis.setDrawGridLines(false);
        barChart.setDescription(null);
        barChart.getLegend().setEnabled(false);
        barChart.setDoubleTapToZoomEnabled(false);
        barChart.setTouchEnabled(false);
        barChart.invalidate();

    }

    private void addData() {
        for (int i = 0; i < listttype.size(); i++) {

            String[] s = listttype.get(i).toString().split("=");
            mX.add(s[0]);
            mY.add(new BarEntry(i, ((float) Integer.parseInt(s[1]) / (float) he) * 100));

        }
    }

    private void cstj() {
        for (int i = 0; i < mgystj.size(); i++) {
            String type = mgystj.get(i).getChengshi();
            if (cs.get(type) == null) {
                cs.put(type, 1);
            } else {
                cs.put(type, cs.get(type) + 1);
            }
        }
        listttype = new ArrayList<>(cs.entrySet());

    }

    private void inview() {
        mgystj = new ArrayList<>();
        mjyje = new ArrayList<>();
        cs = new HashMap<>();
        jy = new HashMap<>();
        title.setText("供应商-供应商统计");
        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mnoyw = new ArrayList<>();
        myesyw = new ArrayList<>();
        mqbyw = new ArrayList<>();
        cp = new HashMap<>();
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
                            mgystj.add(new Gystj(jsonObject1.optString("name")
                                    , jsonObject1.optString("xh")
                                    , jsonObject1.optString("cshang")
                                    , jsonObject1.optString("cs")
                                    , jsonObject1.optInt("path")));
                        }
                        cstj();
                        settu1();
                        gyssl();
                        cpsl();
                        settu2();
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();
    }

    @OnClick({R.id.change, R.id.lin1, R.id.lin2, R.id.lin3, R.id.lin4})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.change:
                finish();
                break;
            case R.id.lin1:
                startActivity(new Intent(S_GYSTJActivity.this, S_EJLDActivity1.class));
                break;
            case R.id.lin2:
                startActivity(new Intent(S_GYSTJActivity.this, S_EJLDActivity2.class));
                break;
            case R.id.lin3:
                startActivity(new Intent(S_GYSTJActivity.this, S_EJLDActivity3.class));
                break;
            case R.id.lin4:
                startActivity(new Intent(S_GYSTJActivity.this, S_EJLDActivity4.class));
                break;
        }
    }

}
