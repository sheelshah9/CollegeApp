package com.example.sheel9.nirmauniversityapp;

import android.support.v4.app.Fragment;

/**
 * Created by Nitin on 26-03-2017.
 */
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
public class AttendanceSummary extends Fragment{
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.attendance_summary,container,false);
        TableLayout attendanceMaster=(TableLayout)v.findViewById(R.id.attendanceMaster);
        AttendanceInfo ai=new AttendanceInfo();
        ArrayList<AttendanceInfo> data=ai.dummydata();

        LayoutInflater hli = (LayoutInflater) v.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View headerView = hli.inflate(R.layout.attendance_tablerow_summary, null);
        TableRow dataRow = (TableRow) headerView.findViewById(R.id.row);
        dataRow.setBackgroundColor(getResources().getColor(R.color.md_teal_100));
        TextView hdate = (TextView) headerView.findViewById(R.id.date);
        TextView hlecturenumber = (TextView) headerView.findViewById(R.id.lecturenumber);
        TextView hlecturetype = (TextView) headerView.findViewById(R.id.lecturetype);
        TextView hsubjectcode = (TextView) headerView.findViewById(R.id.subjectcode);
        TextView hserialnumber = (TextView) headerView.findViewById(R.id.serialnumber);
        hserialnumber.setTypeface(null, Typeface.BOLD_ITALIC);
        hserialnumber.setText("Sr. No.");
        hdate.setTypeface(null, Typeface.BOLD_ITALIC);
        hdate.setText("Date");
        hlecturenumber.setTypeface(null, Typeface.BOLD_ITALIC);
        hlecturenumber.setText("Lec. No.");
        hlecturetype.setTypeface(null, Typeface.BOLD_ITALIC);
        hlecturetype.setText("Type");
        hsubjectcode.setTypeface(null, Typeface.BOLD_ITALIC);
        hsubjectcode.setText("Sub. Code");
        dataRow.setPadding(3, 3, 3, 3);
        TableLayout summaryHeader=(TableLayout)v.findViewById(R.id.summaryheader);
        summaryHeader.addView(dataRow);

        for(int i=0;i<data.size();i++) {
            LayoutInflater li = (LayoutInflater) v.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View rowView = li.inflate(R.layout.attendance_tablerow_summary, null);
            TableRow newDataRow = (TableRow) rowView.findViewById(R.id.row);
            if(i%2==1)
                newDataRow.setBackgroundColor(getResources().getColor(R.color.md_teal_100));
            TextView date = (TextView) rowView.findViewById(R.id.date);
            TextView lecturenumber = (TextView) rowView.findViewById(R.id.lecturenumber);
            TextView lecturetype = (TextView) rowView.findViewById(R.id.lecturetype);
            TextView subjectcode = (TextView) rowView.findViewById(R.id.subjectcode);
            TextView serialnumber = (TextView) rowView.findViewById(R.id.serialnumber);
            serialnumber.setText(Integer.toString(i+1));
            date.setText(data.get(i).date);
            lecturenumber.setText(data.get(i).lectureNumber);
            lecturetype.setText(data.get(i).lectureType);
            subjectcode.setText(data.get(i).subjectCode);
            attendanceMaster.startAnimation(AnimationUtils.loadAnimation(v.getContext(), android.R.anim.slide_in_left));
            attendanceMaster.addView(newDataRow);
        }

        return v;
    }
}
