package com.example.a20zhinengzhizao1.fragment;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.a20zhinengzhizao1.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

@SuppressLint("ValidFragment")
public class S_Fragment_3 extends Fragment {
    @BindView(R.id.lineChart)
    LineChart lineChart;
    private Unbinder mUnbinder;
    private LineData data;
    private LineDataSet dataSet;
    private List<Entry> mY;
    private List<String> mX;
    private List<Integer> color;
    private int yi,er,san,si;
    public S_Fragment_3(int yi,int er,int san,int si)
    {
        this.yi=yi;
        this.er=er;
        this.si=si;
        this.san=san;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.s_fragment_3, null);
        mUnbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        settu();
    }

    private void settu() {
        mY= new ArrayList<>();
        mX = new ArrayList<>();
        addData();
        dataSet = new LineDataSet(mY,"");
        dataSet.setCircleColor(Color.BLUE);
        dataSet.setColors(Color.BLUE);
        dataSet.setDrawCircleHole(false);
        data = new LineData(dataSet);
        lineChart.setData(data);
        lineChart.setDoubleTapToZoomEnabled(false);
        lineChart.setDescription(null);
        lineChart.getLegend().setEnabled(false);
        XAxis xAxis = lineChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setValueFormatter(new IndexAxisValueFormatter(mX));
        xAxis.setGranularity(1f);
        xAxis.setDrawGridLines(false);
        lineChart.getAxisRight().setEnabled(false);
        lineChart.getAxisLeft().setStartAtZero(true);
        lineChart.invalidate();
    }

    private void addData() {
        mX.add("25岁以下"); mX.add("25-30岁"); mX.add("30-35岁"); mX.add("35岁以上");
        mY.add(new Entry(0,yi));
        mY.add(new Entry(1,er));
        mY.add(new Entry(2,san));
        mY.add(new Entry(3,si));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }
}
