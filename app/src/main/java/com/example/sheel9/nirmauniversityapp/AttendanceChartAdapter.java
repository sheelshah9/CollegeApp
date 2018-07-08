package com.example.sheel9.nirmauniversityapp;

/**
 * Created by Nitin on 26-03-2017.
 */
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;


import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;

import java.util.ArrayList;
public class AttendanceChartAdapter {

    private Context context;
    private PieChart chart;
    public AttendanceChartAdapter(Context context)
    {
        this.context=context;
    }
    public PieData createDataForChart(int color)
    {
        return createDataForChart(color,100f);
    }

    public PieData createDataForChart(int color,float percentage)
    {
        ArrayList<Entry> values = new ArrayList<Entry>();
        ArrayList<String> xVals = new ArrayList<String>();
        xVals.add(""); xVals.add("");

        values.add(new Entry(percentage, 0));
        values.add(new Entry(100 - percentage, 1));

        PieDataSet dataSet = new PieDataSet(values, "");
        dataSet.setDrawValues(false);
        dataSet.setColors(new int[]{context.getResources().getColor(color),context.getResources().getColor(R.color.background_material_light)});

        return new PieData(xVals,dataSet);
    }

    public void setChartAttributes(PieChart p,float radius)
    {
        p.setHoleColorTransparent(true);
        p.setHoleRadius(radius);
        p.setRotationEnabled(false);
        p.setUsePercentValues(true);
        p.setTouchEnabled(false);
        p.setDrawSliceText(false);
        p.setCenterText(" ");
        p.setDescriptionColor(context.getResources().getColor(R.color.background_material_light));
        p.setVisibility(View.GONE);
    }

    public PieChart createNewChart(View v,RelativeLayout rl,int radius,int color,float percentage)
    {
        LayoutInflater li=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View customChart=li.inflate(R.layout.attendance_chart,null);
        chart=(PieChart)customChart.findViewById(R.id.chart);
        chart.getLegend().setEnabled(false);
        chart.setData(createDataForChart(color,percentage)); // Color and Percentage of the Ring
        setChartAttributes(chart,radius); // Transparent Hole Radius of the Ring
        rl.addView(chart);
        return chart;
    }
}
