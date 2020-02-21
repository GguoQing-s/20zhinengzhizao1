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
import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarDataSet;
import com.github.mikephil.charting.data.RadarEntry;
import com.github.mikephil.charting.interfaces.datasets.IRadarDataSet;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

@SuppressLint("ValidFragment")
public class S_Fragment_4 extends Fragment {


    @BindView(R.id.radarChart)
    RadarChart radarChart;
    private List<Jbxx1> mjbxx1;
    private Unbinder mUnbinder;
    private List<RadarEntry> radarEntries;
    private RadarDataSet dataSet;
    private RadarData data;
    private List<IRadarDataSet> iRadarDataSets;
    private int b,s,b1,z,z1;
    public S_Fragment_4(List<Jbxx1> mjbxx1)
    {
        this.mjbxx1=mjbxx1;
        for (int i=0;i<mjbxx1.size();i++)
        {
            Jbxx1 jbxx = mjbxx1.get(i);
            if (jbxx.getXl().equals("博士"))
            {
                b++;
            }else  if (jbxx.getXl().equals("硕士"))
            {
                s++;
            }else  if (jbxx.getXl().equals("本科"))
            {
                b1++;
            }else  if (jbxx.getXl().equals("专科"))
            {
                z++;
            }else {
                z1++;
            }
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.s_fragment_4, null);
        mUnbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        settu();

    }

    private void settu() {
        radarEntries = new ArrayList<>();
        radarEntries.add(new RadarEntry(b));
        radarEntries.add(new RadarEntry(s));
        radarEntries.add(new RadarEntry(b1));
        radarEntries.add(new RadarEntry(z));
        radarEntries.add(new RadarEntry(z1));
        dataSet = new RadarDataSet(radarEntries,"");

        dataSet.setColors(Color.parseColor("#A8CAEC"));
        dataSet.setFillAlpha(180);
        dataSet.setLineWidth(2f);
        dataSet.setFillColor(Color.parseColor("#A8CAEC"));
        dataSet.setDrawFilled(true);
        dataSet.setDrawValues(false);
        XAxis xAxis = radarChart.getXAxis();
        xAxis.setAxisMinimum(0);
        xAxis.setTextColor(Color.WHITE);

        YAxis yAxis = radarChart.getYAxis();
        yAxis.setStartAtZero(true);
        yAxis.setAxisMaximum(10);
        yAxis.setTextSize(15f);

        iRadarDataSets = new ArrayList<>();
        iRadarDataSets.add(dataSet);
        iRadarDataSets.add(tu());
        data = new RadarData(iRadarDataSets);
        radarChart.setData(data);
        radarChart.invalidate();
        radarChart.getLegend().setEnabled(false);
        radarChart.setDescription(null);
        radarChart.setTouchEnabled(false);
    }
    private RadarDataSet tu()
    {
        List<RadarEntry> yValue = new ArrayList<>();
        int[] dra  =new int[]{
                R.drawable.shape_1,
                R.drawable.shape_2,
                R.drawable.shape_3,
                R.drawable.shape_4,
                R.drawable.shape_5,
        };

        for (int i=0;i<5;i++)
        {
            RadarEntry radarEntry = new RadarEntry(13.5f);
            radarEntry.setIcon(getResources().getDrawable(dra[i]));
            yValue.add(radarEntry);
        }
        RadarDataSet radarDataSet = new RadarDataSet(yValue,"");
        radarDataSet.setDrawValues(false);
        radarDataSet.setColors(Color.TRANSPARENT);
        return radarDataSet;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }
}
