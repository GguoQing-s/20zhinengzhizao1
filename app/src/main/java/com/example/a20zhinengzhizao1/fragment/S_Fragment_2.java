package com.example.a20zhinengzhizao1.fragment;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.a20zhinengzhizao1.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

@SuppressLint("ValidFragment")
public class S_Fragment_2 extends Fragment {
    @BindView(R.id.barChart)
    BarChart barChart;
    private Unbinder mUnbinder;
    private List<BarEntry> barEntries;
    private BarData data;
    private BarDataSet dataSet;
    private List<BarEntry> mY;
    private List<String> mX;
    private List<Map.Entry<String,Integer>> listttype;
    public S_Fragment_2(List<Map.Entry<String,Integer>> listttype)
    {
        this.listttype=listttype;
        Log.d("----", "S_Fragment_2: ----"+listttype);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.s_fragment_2, null);
        mUnbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        settu();
    }

    private void settu() {
        mX = new ArrayList<>();
        mY = new ArrayList<>();
        addData();
        dataSet = new BarDataSet(mY,"");
        dataSet.setColors(Color.GRAY);
        data=new BarData(dataSet);
        data.setValueTextColor(Color.GRAY);
        barChart.setData(data);
        XAxis xAxis = barChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setValueFormatter(new IndexAxisValueFormatter(mX));
        xAxis.setGranularity(1f);
        xAxis.setDrawGridLines(false);
        barChart.setDescription(null);
        barChart.getLegend().setEnabled(false);
        barChart.getAxisRight().setEnabled(false);
        barChart.getAxisLeft().setStartAtZero(true);
        barChart.getAxisRight().setStartAtZero(true);
        barChart.invalidate();
    }

    private void addData() {
        for (int i=0;i<listttype.size();i++)
        {

            String[] s = listttype.get(i).toString().split("=");
            mX.add(s[0]);
            mY.add(new BarEntry(i,Integer.parseInt(s[1])));

        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }
}
