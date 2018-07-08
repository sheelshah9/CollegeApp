package com.example.sheel9.nirmauniversityapp;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

/**
 * Created by Sheel9 on 29-03-2017.
 */
public class Cpw extends Fragment {

    BarChart barChart=Result.barChartMain;
    boolean visibility=false,isViewShown=false;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.result_display,container,false);
        barChart = (BarChart) v.findViewById(R.id.cpwchart);
        setData();
        if (!isViewShown && visibility) {
            animateChart();
        }
        return v;
    }
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            visibility=true;
            barChart.setVisibility(View.VISIBLE);
            animateChart();
        }
        if (!isVisibleToUser) {
            visibility=false;
            barChart.setVisibility(View.GONE);
        }
        if (getView() != null)
            isViewShown = true;
        else
            isViewShown = false;
    }
    public void setData(){
        barChart.clear();
        int c[] = new int[6];
        ArrayList<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(95,0));
        entries.add(new BarEntry(60, 1));
        entries.add(new BarEntry(78, 2));
        entries.add(new BarEntry(30, 3));
        entries.add(new BarEntry(86, 4));
        BarDataSet dataset = new BarDataSet(entries, "Marks");
        final ArrayList<String> xlabels = new ArrayList<String>();
        xlabels.add("DC");
        xlabels.add("DS");
        xlabels.add("CO");
        xlabels.add("PSNA");
        xlabels.add("ECO");
        int i=0;
        for(BarEntry be:entries){
            if(be.getVal()<40){
                c[i]= Color.RED;
            }
            else
                c[i]= ColorTemplate.COLORFUL_COLORS[i];
            i++;
        }


        dataset.setColors(c);

        BarData data = new BarData(xlabels,dataset);

        barChart.setData(data);

        barChart.invalidate();

    }
    public void animateChart(){
        barChart.setVisibility(View.VISIBLE);
        barChart.animateY((int) (Math.random() * 1000) + 1500);
    }
}