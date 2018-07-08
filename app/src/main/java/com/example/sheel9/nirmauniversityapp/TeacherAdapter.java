package com.example.sheel9.nirmauniversityapp;

/**
 * Created by Sheel9 on 29-03-2017.
 */
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;
public class TeacherAdapter extends RecyclerView.Adapter<TeacherAdapter.ViewHolder> {
    public View view;
    private Context context;
    private List<TeacherData> my_data;

    public TeacherAdapter(Context context, List<TeacherData> my_data) {
        this.context = context;
        this.my_data = my_data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.data_card,parent,false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.name.setText(my_data.get(position).getName());
        holder.designation.setText(my_data.get(position).getDesignation());
        holder.room.setText(my_data.get(position).getRoom());
        Glide.with(context).load(my_data.get(position).getImage_link()).into(holder.imageView);


    }

    @Override
    public int getItemCount() {
        return my_data.size();
    }

    public  class ViewHolder extends  RecyclerView.ViewHolder{
        public TextView name;
        public TextView designation;
        public TextView room;
        public ImageView imageView;

        public ViewHolder(View itemView) {

            super(itemView);

            name = (TextView) itemView.findViewById(R.id.name);
            designation = (TextView) itemView.findViewById(R.id.designation);
            room = (TextView) itemView.findViewById(R.id.room);
            imageView = (ImageView) itemView.findViewById(R.id.image);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, DataWebView.class);
                    context.startActivity(intent);
                }
            });
        }
    }

    public void setMy_data(List<TeacherData> my_data) {
        this.my_data = my_data;
    }


}