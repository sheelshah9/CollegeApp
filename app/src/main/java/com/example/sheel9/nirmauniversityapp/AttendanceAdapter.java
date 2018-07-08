package com.example.sheel9.nirmauniversityapp;

/**
 * Created by Nitin on 26-03-2017.
 */
import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.rey.material.widget.FloatingActionButton;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class AttendanceAdapter extends RecyclerView.Adapter<AttendanceAdapter.AttendanceViewHolder> {

    private List<AttendanceInfo> attendanceList;
    private String currentSubject;
    private int j=0;
    private String lectureType;
    private int subjectColor;

    public AttendanceAdapter(List<AttendanceInfo> attendanceList, String lectureType) {
        this.attendanceList = attendanceList;
        this.lectureType=lectureType;
    }


    @Override
    public int getItemCount() {
        return attendanceList.size();
    }

    @Override
    public void onBindViewHolder(AttendanceViewHolder AttendanceViewHolder, int i) {
        AttendanceInfo ci = attendanceList.get(i);
        AttendanceViewHolder.vTitle.setText(ci.subjectCode);
    }

    @Override
    public AttendanceViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.attendance_card, viewGroup, false);

        AttendanceInfo ci = attendanceList.get(j++);
        currentSubject=ci.subjectCode;
        subjectColor=ci.subjectColor;

        return new AttendanceViewHolder(itemView,currentSubject,lectureType,subjectColor,ci.percentage);
    }

    public static class AttendanceViewHolder extends RecyclerView.ViewHolder {


        protected TextView vTitle;

        public AttendanceViewHolder(View v,String currentSubject,String lectureType,int subjectColor,float percentage) {
            super(v);
            int counter=1;
            vTitle = (TextView) v.findViewById(R.id.title);
            final CardView card=(CardView)v.findViewById(R.id.card_view);
            card.setCardBackgroundColor(subjectColor);
            final FloatingActionButton fab_line = (FloatingActionButton)v.findViewById(R.id.fab_line);
            fab_line.setBackgroundColor(subjectColor);
            if(percentage==100)
                fab_line.setVisibility(View.GONE);
          /*  if(percentage<85)
            {
                Animation shake = AnimationUtils.loadAnimation(v.getContext(), R.anim.shake);
                card.startAnimation(shake);
            }*/
            //RippleView ripple=(RippleView)v.findViewById(R.id.rippleView);
            //ripple.setRippleDuration(500);
            TableLayout t=(TableLayout)v.findViewById(R.id.collapsibleTable);
            final TableLayout table=t;
            //final ImageView arrow= (ImageView)v.findViewById(R.id.arrow);
            TextView percentageView=(TextView)v.findViewById(R.id.percentage);
            DecimalFormat df=new DecimalFormat("##.##");
            String percent=df.format(percentage)+"%";
            percentageView.setText(percent);

            LayoutInflater hli = (LayoutInflater) v.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View header = hli.inflate(R.layout.attendance_tablerow, null);
            TableRow tr=(TableRow)header.findViewById(R.id.row);
            TextView hdate=(TextView)header.findViewById(R.id.date);
            TextView hlectureNumber=(TextView)header.findViewById(R.id.lecturenumber);
            TextView hserialno=(TextView)header.findViewById(R.id.serialno);
            hdate.setTypeface(null, Typeface.BOLD_ITALIC);
            hlectureNumber.setTypeface(null, Typeface.BOLD_ITALIC);
            hserialno.setTypeface(null,Typeface.BOLD_ITALIC);
            hdate.setText("Date");
            hlectureNumber.setText("Lec. No.");
            hserialno.setText("Serial No.");
            if(percentage!=100)
                t.addView(tr);
            AttendanceInfo ai=new AttendanceInfo();
            ArrayList<AttendanceInfo> data=ai.dummydata();
            for(int i=0;i<data.size();i++)
            {
                if(data.get(i).subjectCode.equals(currentSubject) && data.get(i).lectureType.equals(lectureType)) {
                    LayoutInflater li = (LayoutInflater) v.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    View rowView = li.inflate(R.layout.attendance_tablerow, null);
                    TableRow newDataRow = (TableRow) rowView.findViewById(R.id.row);
                    TextView date = (TextView) rowView.findViewById(R.id.date);
                    TextView lecturenumber = (TextView) rowView.findViewById(R.id.lecturenumber);
                    TextView serialno=(TextView)rowView.findViewById(R.id.serialno);
                    date.setText(data.get(i).date);
                    lecturenumber.setText(data.get(i).lectureNumber);
                    serialno.setText(Integer.toString(counter++));
                    t.addView(newDataRow);
                }
            }

            card.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (table.getVisibility() == View.GONE) {
                        expand(table);
                        //table.setVisibility(View.VISIBLE);
                        RotateAnimation rotate = new RotateAnimation(0, 90, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                        rotate.setDuration(500);
                        rotate.setInterpolator(new LinearInterpolator());
                        rotate.setFillEnabled(true);
                        rotate.setFillAfter(true);
                        //arrow.startAnimation(rotate);
                        fab_line.setLineMorphingState((fab_line.getLineMorphingState() + 1) % 2, true);

                    }
                    else {
                        collapse(table);
                        //table.setVisibility(View.GONE);
                        RotateAnimation rotate = new RotateAnimation(90, 0, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                        rotate.setDuration(500);
                        rotate.setInterpolator(new LinearInterpolator());
                        rotate.setFillEnabled(true);
                        rotate.setFillAfter(true);
                        //arrow.startAnimation(rotate);
                        fab_line.setLineMorphingState((fab_line.getLineMorphingState() + 1) % 2, true);
                    }
                }
            });
        }
        private ValueAnimator slideAnimator(int start, int end, final TableLayout table) {
            ValueAnimator animator = ValueAnimator.ofInt(start, end);
            animator.setDuration(500);
            animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    int value = (Integer) valueAnimator.getAnimatedValue();
                    ViewGroup.LayoutParams layoutParams = table.getLayoutParams();
                    layoutParams.height = value;
                    table.setLayoutParams(layoutParams);
                }
            });
            return animator;
        }
        public void expand(TableLayout table)
        {
            table.setVisibility(View.VISIBLE);

            final int widthSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
            final int heightSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);

            table.measure(widthSpec, heightSpec);

            ValueAnimator mAnimator = slideAnimator(0, table.getMeasuredHeight(), table);
            mAnimator.start();
        }
        private void collapse(final TableLayout table) {

            int finalHeight = table.getHeight();
            ValueAnimator mAnimator = slideAnimator(finalHeight, 0,table);
            mAnimator.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {

                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    table.setVisibility(View.GONE);

                }

                @Override
                public void onAnimationCancel(Animator animation) {

                }

                @Override
                public void onAnimationRepeat(Animator animation) {

                }
            });
            mAnimator.start();
        }
    }
}