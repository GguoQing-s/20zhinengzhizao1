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
import com.example.a20zhinengzhizao1.bean.Jbxx1;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

@SuppressLint("ValidFragment")
public class S_Fragment_1 extends Fragment {
    @BindView(R.id.pieChart1)
    PieChart pieChart1;
    @BindView(R.id.pieChart2)
    PieChart pieChart2;
    private Unbinder mUnbinder;
    private float yi,er,san,si;
    private List<Jbxx1> mjbxx1;
    private List<PieEntry> pieEntries;
    private PieDataSet dataSet;
    private PieData data;
    private List<Integer> color;
    private int nan,nv;
    public S_Fragment_1(List<Jbxx1> mjbxx1)
    {
        this.mjbxx1=mjbxx1;
        for (int i=0;i<mjbxx1.size();i++)
        {
            Jbxx1 jbxx = mjbxx1.get(i);
            if (jbxx.getSex().equals("男"))
            {
                nan+=1;
            }else {
                nv+=1;
            }
            if (jbxx.getJl().equals("无"))
            {
                yi++;
            }else if (jbxx.getJl().equals("1年"))
            {
                er++;
            }else if (jbxx.getJl().equals("2年"))
            {
                san++;
            }else if (jbxx.getJl().equals("3年"))
            {
                si++;
            }
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.s_fragment_1, null);
        mUnbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        settu();
        settu1();

    }

    private void settu() {
        pieEntries = new ArrayList<>();
        pieEntries.add(new PieEntry((float) yi/mjbxx1.size(),"无"));
        pieEntries.add(new PieEntry((float) er/mjbxx1.size(),"1年"));
        pieEntries.add(new PieEntry((float) san/mjbxx1.size(),"1-3年"));
        pieEntries.add(new PieEntry((float) si/mjbxx1.size(),"3年以上"));
        color = new ArrayList<>();
        color.add(Color.parseColor("#466AA0"));
        color.add(Color.parseColor("#31121D"));
        color.add(Color.parseColor("#040485"));
        color.add(Color.parseColor("#AA4643"));


        dataSet = new PieDataSet(pieEntries,"");
        dataSet.setYValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
        dataSet.setXValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
        dataSet.setValueLinePart1OffsetPercentage(80f);
        dataSet.setValueLinePart2Length(0.5f);
        dataSet.setValueLinePart1Length(0.5f);
        dataSet.setColors(color);

        data = new PieData(dataSet);
        data.setValueFormatter(new PercentFormatter());
        pieChart1.setData(data);
        pieChart1.setDrawHoleEnabled(false);
        pieChart1.setEntryLabelColor(Color.BLACK);
        pieChart1.setUsePercentValues(false);
        pieChart1.setDescription(null);
        Legend legend = pieChart1.getLegend();
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        legend.setFormSize(20);
        legend.setTextSize(20);
        pieChart1.setTouchEnabled(false);
        pieChart1.setRotationEnabled(false);
        pieChart1.invalidate();
    }


    private void settu1() {
        pieEntries = new ArrayList<>();
        pieEntries.add(new PieEntry((float)nan/mjbxx1.size(),"男"));
        pieEntries.add(new PieEntry((float)1-nan/mjbxx1.size(),"女"));
        color = new ArrayList<>();
        color.add(Color.parseColor("#561E25"));
        color.add(Color.parseColor("#9D2C73"));


        dataSet = new PieDataSet(pieEntries,"");
        dataSet.setYValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
        dataSet.setXValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
        dataSet.setValueLinePart1OffsetPercentage(80f);
        dataSet.setValueLinePart2Length(0.8f);
        dataSet.setValueLinePart1Length(0.8f);
        dataSet.setColors(color);

        data = new PieData(dataSet);
        data.setValueFormatter(new PercentFormatter());
        pieChart2.setData(data);
        pieChart2.setDrawHoleEnabled(false);
        pieChart2.setEntryLabelColor(Color.BLACK);
        pieChart2.setUsePercentValues(false);
        pieChart2.setDescription(null);
        Legend legend = pieChart2.getLegend();
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        legend.setFormSize(20);
        legend.setTextSize(20);
        pieChart2.setTouchEnabled(false);
        pieChart2.setRotationEnabled(false);
        pieChart2.invalidate();
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }
}
