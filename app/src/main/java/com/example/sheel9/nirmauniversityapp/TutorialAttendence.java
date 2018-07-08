package com.example.sheel9.nirmauniversityapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.github.mikephil.charting.charts.PieChart;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nitin on 26-03-2017.
 */

public class TutorialAttendence extends Fragment {
    private ArrayList<String> listOfSubjects=new ArrayList<String>();
    public ArrayList<PieChart> chartReferences=new ArrayList<PieChart>();
    private boolean isViewShown = false;
    private boolean visibility = false;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.attendance_tutorial,container,false);

        listOfSubjects.add("CO-CE402");
        listOfSubjects.add("DC-CE401");
        listOfSubjects.add("DS-CE403");
        listOfSubjects.add("PSNA-MA403");
        listOfSubjects.add("ECO-SS341");
        listOfSubjects.add("CPW-CE406");
        AttendanceChartAdapter adapter=new AttendanceChartAdapter(getActivity());
        RelativeLayout rl=(RelativeLayout)v.getRootView().findViewById(R.id.chartview);

        RecyclerView recList = (RecyclerView)v.getRootView().findViewById(R.id.cardList);
        recList.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recList.setLayoutManager(llm);

        AttendanceAdapter ca = new AttendanceAdapter(createList(listOfSubjects,v),"t");
        recList.setAdapter(ca);

        if (!isViewShown && visibility) {
            animateCharts();
        }

        return v;
    }

    private List<AttendanceInfo> createList(ArrayList<String> listOfSubjects,View v) {

        AttendanceChartAdapter adapter=new AttendanceChartAdapter(getActivity());
        RelativeLayout rl=(RelativeLayout)v.getRootView().findViewById(R.id.chartview);

        List<AttendanceInfo> attendance = new ArrayList<AttendanceInfo>();
        int numberOfSubjects=listOfSubjects.size();
        int smallestRadius=100-(numberOfSubjects*5);
        for (int i=0;i<listOfSubjects.size();i++) {
            AttendanceInfo att_info = new AttendanceInfo();
            att_info.subjectCode=listOfSubjects.get(i);
            att_info.subjectColor=getResources().getColor(att_info.colorpalette[i]);
            att_info.percentage=((float)(att_info.numberoflectures-att_info.Count(att_info.subjectCode,"t"))/att_info.numberoflectures)*100;
            chartReferences.add(adapter.createNewChart(v.getRootView(), rl, smallestRadius + (i * 5), att_info.colorpalette[i], att_info.percentage));
            attendance.add(att_info);
        }
        listOfSubjects.clear();
        chartReferences.add(adapter.createNewChart(v.getRootView(), rl, 100, R.color.background_material_light, 100)); // Blank Chart to hide default legend
        return attendance;
    }
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            visibility=true;
            for(int i=0;i<chartReferences.size();i++) {
                chartReferences.get(i).setVisibility(View.VISIBLE);
                chartReferences.get(i).animateY((int) (Math.random() * 1000) + 1500);
            }
        }
        if (!isVisibleToUser) {
            visibility=false;
            for(int i=0;i<chartReferences.size();i++) {
                chartReferences.get(i).setVisibility(View.GONE);
            }
        }
        if (getView() != null)
            isViewShown = true;
        else
            isViewShown = false;
    }

    public void animateCharts()
    {
        for(int i=0;i<chartReferences.size();i++) {
            chartReferences.get(i).setVisibility(View.VISIBLE);
            chartReferences.get(i).animateY((int) (Math.random() * 1000) + 1500);
        }
    }

}
