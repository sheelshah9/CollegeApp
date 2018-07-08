package com.example.sheel9.nirmauniversityapp;

/**
 * Created by Sheel9 on 29-03-2017.
 */
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hp1 on 21-01-2015.
 */
public class Summary extends Fragment {

    double[] dataSet = {8.4,8.6,8.68,9.6,7.2};
    List<Entry> entries = new ArrayList<Entry>();
    LineChart chart=Result.lineChart;

    boolean visibility=false,isViewShown=false;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.summary,container,false);
        chart = (LineChart) v.findViewById(R.id.chart);
        setLineChart();
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
            chart.setVisibility(View.VISIBLE);
            animateChart();
        }
        if (!isVisibleToUser) {
            visibility=false;
            chart.setVisibility(View.GONE);
        }
        if (getView() != null)
            isViewShown = true;
        else
            isViewShown = false;
    }

    public void setLineChart(){
        ArrayList<Entry> spivalues = new ArrayList<Entry>();
        ArrayList<Entry> ppivalues = new ArrayList<Entry>();
        ArrayList<String> rxVals = new ArrayList<String>();
        int no_of_semesters=6;
        for(int i=0;i<no_of_semesters;i++)
            rxVals.add("Sem "+Integer.toString(i+1));

        spivalues.add(new Entry(5.35f,0));
        spivalues.add(new Entry(7.45f,1));
        spivalues.add(new Entry(4.60f,2));
        spivalues.add(new Entry(9.81f,3));
        spivalues.add(new Entry(8.30f,4));
        spivalues.add(new Entry(8.14f,5));

        ppivalues.add(new Entry(5.35f,0));
        ppivalues.add(new Entry(7.16f,1));
        ppivalues.add(new Entry(4.24f,2));
        ppivalues.add(new Entry(6.61f,3));
        ppivalues.add(new Entry(9.83f,4));
        ppivalues.add(new Entry(6.87f,5));

        LineDataSet spi=new LineDataSet(spivalues,"SPI");
        LineDataSet ppi=new LineDataSet(ppivalues,"PPI");

        spi.setColor(getResources().getColor(R.color.lineColor));
        ppi.setColor(getResources().getColor(R.color.valueTextColor));
        spi.setDrawValues(false);
        ppi.setDrawValues(false);
        spi.setLineWidth(2.5f);
        ppi.setLineWidth(2.5f);
        spi.setDrawCircles(false);
        ppi.setDrawCircles(false);

        ArrayList<LineDataSet> sets=new ArrayList<LineDataSet>();
        sets.add(spi);
        sets.add(ppi);

        chart.setData(new LineData(rxVals, sets));
        chart.setDrawGridBackground(false);
        chart.setDrawBorders(false);
        chart.setDescription("");
        //chart.setTouchEnabled(false);

        XAxis xAxis=chart.getXAxis();
        xAxis.setDrawGridLines(false);
        xAxis.setDrawAxisLine(true);
        xAxis.setSpaceBetweenLabels(1);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);

        YAxis rightAxis = chart.getAxisRight();



    }
    public void animateChart(){
        chart.setVisibility(View.VISIBLE);
        chart.animateX(((int) (Math.random() * 1000) + 1500), Easing.EasingOption.EaseInElastic);
    }


}