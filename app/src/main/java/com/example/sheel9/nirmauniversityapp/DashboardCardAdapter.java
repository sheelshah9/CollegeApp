package com.example.sheel9.nirmauniversityapp;

/**
 * Created by Sheel9 on 29-03-2017.
 */
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;

import java.util.HashMap;
import java.util.Random;

public class DashboardCardAdapter extends RecyclerView.Adapter<DashboardCardAdapter.ViewHolder>  {
    public View view;
    private Context context;
    String[][] dataSlider;
    int c[] = new int[10];


    public DashboardCardAdapter(Context context,String[][] dataSlider) {
        this.context = context;
        this.dataSlider=dataSlider;
        c[0] = ContextCompat.getColor(context, R.color.c1);
        c[1] = ContextCompat.getColor(context, R.color.c2);
        c[2] = ContextCompat.getColor(context, R.color.c3);
        c[3] = ContextCompat.getColor(context, R.color.c4);
        c[4] = ContextCompat.getColor(context, R.color.c5);
        c[5] = ContextCompat.getColor(context, R.color.c6);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.dashboard_card,parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Random r = new Random();
        holder.label.setText(dataSlider[position][0]);
        holder.imgv.setBackgroundColor(c[position]);
        holder.itemView.setBackgroundColor(c[position]);

        HashMap<String,String> url_maps = new HashMap<String, String>();
        url_maps.put(dataSlider[position][0]+"n",dataSlider[position][1]);
        url_maps.put(dataSlider[position][0]+"a",dataSlider[position][2]);
        url_maps.put(dataSlider[position][0]+"b",dataSlider[position][3]);
        url_maps.put(dataSlider[position][0]+"c",dataSlider[position][4]);

        for(String name : url_maps.keySet()){

            // initialize a SliderLayout
            DefaultSliderView dsv = new DefaultSliderView(context);
            dsv.image(url_maps.get(name)).setScaleType(BaseSliderView.ScaleType.Fit);


            //add your extra information
            dsv.bundle(new Bundle());
            dsv.getBundle().putString("extra", name);


            holder.sliderShow.addSlider(dsv);
        }
        animate(holder.itemView,position);
        holder.sliderShow.setPresetTransformer(r.nextInt()%15+1);
        holder.sliderShow.setDuration(5000 + 800*r.nextInt(10));
        holder.sliderShow.startAutoCycle();

    }

    @Override
    public int getItemCount() {
        return 6;
    }


    public  class ViewHolder extends  RecyclerView.ViewHolder{

        SliderLayout sliderShow;
        TextView label;
        ImageView imgv;
        public ViewHolder(final View itemView) {

            super(itemView);

            sliderShow = (SliderLayout) itemView.findViewById(R.id.slider);
            label = (TextView) itemView.findViewById(R.id.name);
            imgv = (ImageView) itemView.findViewById(R.id.imageView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (label.getText().toString().compareTo("News")==0) {

                            Intent intent = new Intent(context, News.class);
                            context.startActivity(intent);

                    }
                    else if(label.getText().toString().compareTo("Events")==0){
                        Intent intent = new Intent(context, Events.class);
                        context.startActivity(intent);
                    }
                    else if(label.getText().toString().compareTo("Result")==0){
                        Intent intent = new Intent(context, Result.class);
                        context.startActivity(intent);
                    }
                    else if(label.getText().toString().compareTo("Attendance")==0){
                        Intent intent = new Intent(context, Attendance.class);
                        context.startActivity(intent);
                    }
                    else if(label.getText().toString().compareTo("Database")==0){
                        Intent intent = new Intent(context, TeacherDatabase.class);
                        context.startActivity(intent);
                    }
                    else if(label.getText().toString().compareTo("Archives")==0){
                        //Intent intent = new Intent(context, WebViewActivity.class);
                        //context.startActivity(intent);
                    }
                }
            });


        }
    }


    private void animate(View view, final int pos) {
        view.animate().cancel();
        view.setTranslationY(100);
        view.setAlpha(0);
        view.animate().alpha(1.0f).translationY(0).setDuration(300).setStartDelay(pos * 300);
    }
}