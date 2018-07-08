package com.example.sheel9.nirmauniversityapp;

import java.util.ArrayList;

/**
 * Created by Nitin on 26-03-2017.
 */
public class  AttendanceInfo {
    protected String date;
    protected String subjectCode;
    protected String lectureNumber;
    protected String lectureType;
    protected String timeStamp;
    protected float percentage;
    protected int subjectColor;
    protected int numberoflectures=15;
    public int colorpalette[]={
            R.color.md_teal_300,
            R.color.md_red_300,
            R.color.md_blue_300,
            R.color.md_amber_300,
            R.color.md_green_300,
            R.color.md_deep_purple_300,
            R.color.md_deep_orange_300,
            R.color.md_deep_purple_600,
            R.color.md_yellow_900,
            R.color.md_red_900};

    public AttendanceInfo()
    {

    }
    public AttendanceInfo(String date,String subjectCode,String lectureNumber,String lectureType,String timeStamp)
    {
        this.date=date;
        this.subjectCode=subjectCode;
        this.lectureNumber=lectureNumber;
        this.lectureType=lectureType;
        this.timeStamp=timeStamp;
    }
    public int Count(String subjectCode,String lectureType)
    {
        int count=0;
        AttendanceInfo ai=new AttendanceInfo();

        ArrayList<AttendanceInfo> data=ai.dummydata();
        for(int i=0;i<data.size();i++)
            if(data.get(i).subjectCode.equals(subjectCode) && data.get(i).lectureType.equals(lectureType))
                count++;
        return count;
    }

    public ArrayList<AttendanceInfo> dummydata()
    {
        ArrayList<AttendanceInfo> data=new ArrayList<AttendanceInfo>();

        data.add(new AttendanceInfo("10-05-2017","DC-CE401","3","l","2017-05-29 01:21:36"));
        data.add(new AttendanceInfo("12-07-2017","CO-CE402","2","l","2017-05-29 01:21:36"));
        data.add(new AttendanceInfo("16-04-2017","DC-CE401","4","l","2017-05-29 01:21:36"));
        data.add(new AttendanceInfo("14-02-2017","CO-CE402","5","l","2017-05-29 01:21:36"));
        data.add(new AttendanceInfo("19-02-2017","CO-CE402","1","l","2017-05-29 01:21:36"));
        data.add(new AttendanceInfo("10-07-2017","CO-CE402","7","l","2017-05-29 01:21:36"));
        data.add(new AttendanceInfo("15-09-2017","ECO-SS341","5","l","2017-05-29 01:21:36"));
        data.add(new AttendanceInfo("12-06-2017","DC-CE401","8","l","2017-05-29 01:21:36"));
        data.add(new AttendanceInfo("11-04-2017","CO-CE402","5","l","2017-05-29 01:21:36"));
        data.add(new AttendanceInfo("15-09-2017","DS-CE403","2","l","2017-05-29 01:21:36"));
        data.add(new AttendanceInfo("16-08-2017","DS-CE403","4","l","2017-05-29 01:21:36"));
        data.add(new AttendanceInfo("14-03-2017","PSNA-MA403","1","l","2017-05-29 01:21:36"));
        data.add(new AttendanceInfo("12-07-2017","DS-CE403","1","l","2017-05-29 01:21:36"));
        data.add(new AttendanceInfo("10-04-2017","PSNA-MA403","7","l","2017-05-29 01:21:36"));
        data.add(new AttendanceInfo("13-01-2017","CE406","4","l","2017-05-29 01:21:36"));
        data.add(new AttendanceInfo("15-09-2017","DS-CE403","2","t","2017-05-29 01:21:36"));
        data.add(new AttendanceInfo("16-08-2017","DS-CE403","4","la","2017-05-29 01:21:36"));
        data.add(new AttendanceInfo("14-03-2017","PSNA-MA403","1","t","2017-05-29 01:21:36"));
        data.add(new AttendanceInfo("12-07-2017","DS-CE403","1","t","2017-05-29 01:21:36"));
        data.add(new AttendanceInfo("10-04-2017","PSNA-MA403","7","la","2017-05-29 01:21:36"));
        data.add(new AttendanceInfo("13-01-2017","CPW-CE406","4","la","2017-05-29 01:21:36"));
        data.add(new AttendanceInfo("10-05-2017","DC-CE401","3","l","2017-05-29 01:21:36"));
        data.add(new AttendanceInfo("12-07-2017","CO-CE402","2","l","2017-05-29 01:21:36"));
        data.add(new AttendanceInfo("16-04-2017","DC-CE401","4","l","2017-05-29 01:21:36"));
        data.add(new AttendanceInfo("14-02-2017","CO-CE402","5","l","2017-05-29 01:21:36"));
        data.add(new AttendanceInfo("19-02-2017","CO-CE402","1","l","2017-05-29 01:21:36"));
        data.add(new AttendanceInfo("10-07-2017","CO-CE402","7","l","2017-05-29 01:21:36"));
        data.add(new AttendanceInfo("15-09-2017","ECO-SS341","5","l","2017-05-29 01:21:36"));
        data.add(new AttendanceInfo("15-07-2017","ECO-SS341","5","l","2017-05-29 01:21:36"));
        data.add(new AttendanceInfo("12-06-2017","DC-CE401","8","l","2017-05-29 01:21:36"));
        data.add(new AttendanceInfo("11-04-2017","CO-CE402","5","l","2017-05-29 01:21:36"));
        data.add(new AttendanceInfo("15-09-2017","DS-CE403","2","l","2017-05-29 01:21:36"));
        data.add(new AttendanceInfo("16-08-2017","DS-CE403","4","l","2017-05-29 01:21:36"));
        data.add(new AttendanceInfo("14-03-2017","PSNA-MA403","1","l","2017-05-29 01:21:36"));
        data.add(new AttendanceInfo("12-07-2017","DS-CE403","1","l","2017-05-29 01:21:36"));
        data.add(new AttendanceInfo("10-04-2017","PSNA-MA403","7","l","2017-05-29 01:21:36"));
        data.add(new AttendanceInfo("13-01-2017","CE406","4","l","2017-05-29 01:21:36"));
        data.add(new AttendanceInfo("15-09-2017","DS-CE403","2","t","2017-05-29 01:21:36"));
        data.add(new AttendanceInfo("16-08-2017","DS-CE403","4","la","2017-05-29 01:21:36"));
        data.add(new AttendanceInfo("14-03-2017","PSNA-MA403","1","t","2017-05-29 01:21:36"));
        data.add(new AttendanceInfo("12-07-2017","DS-CE403","1","t","2017-05-29 01:21:36"));
        data.add(new AttendanceInfo("10-04-2017","PSNA-MA403","7","la","2017-05-29 01:21:36"));
        data.add(new AttendanceInfo("13-01-2017","CE406","4","la","2017-05-29 01:21:36"));

        return data;
    }
}