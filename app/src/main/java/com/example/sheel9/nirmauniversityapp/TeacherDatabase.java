package com.example.sheel9.nirmauniversityapp;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.holder.BadgeStyle;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class TeacherDatabase extends AppCompatActivity {
    private RecyclerView recyclerView;
    private GridLayoutManager gridLayoutManager;
    private TeacherAdapter adapter;
    private List<TeacherData> data_list;
    private List<TeacherData> data_list_filtered;
    private EditText filter;
    private String fil="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_database);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        data_list  = new ArrayList<>();
        load_data_from_server(0);

        gridLayoutManager = new GridLayoutManager(this,1);
        recyclerView.setLayoutManager(gridLayoutManager);
        filter = (EditText) findViewById(R.id.filter);
        adapter = new TeacherAdapter(this,data_list);

        recyclerView.setAdapter(adapter);

        PrimaryDrawerItem item = new PrimaryDrawerItem().withIdentifier(1).withName("");
        item.withName("");
        PrimaryDrawerItem item1 = new PrimaryDrawerItem().withIdentifier(2).withName("Dashboard");
        item1.withName("Dashboard").withIcon(R.drawable.dashboard);
        PrimaryDrawerItem item3 = new PrimaryDrawerItem().withIdentifier(3).withName("News");
        PrimaryDrawerItem item4 = new PrimaryDrawerItem().withIdentifier(4).withName("Events");
        PrimaryDrawerItem item5 = new PrimaryDrawerItem().withIdentifier(5).withName("Result");
        PrimaryDrawerItem item6 = new PrimaryDrawerItem().withIdentifier(6).withName("Attendance");
        PrimaryDrawerItem item7 = new PrimaryDrawerItem().withIdentifier(7).withName("Database");
        PrimaryDrawerItem item8 = new PrimaryDrawerItem().withIdentifier(8).withName("Archives");
        item3.withName("News").withBadge("19").withBadgeStyle(new BadgeStyle().withTextColor(Color.WHITE).withColorRes(R.color.md_red_700)).withIcon(R.drawable.news);
        item4.withName("Events").withIcon(R.drawable.calendar);
        item5.withName("Result").withIcon(R.drawable.result);
        item6.withName("Attendance").withIcon(R.drawable.analytics);
        item7.withName("Database").withIcon(R.drawable.database);
        item8.withName("Archives").withIcon(R.drawable.archive);

//create the drawer and remember the `Drawer` result object

        Drawer result = new DrawerBuilder()
                .withActivity(this)
                .withTranslucentStatusBar(false)
                .withActionBarDrawerToggle(false)
                .addDrawerItems(
                        item,
                        new DividerDrawerItem(),
                        item1,
                        new DividerDrawerItem(),

                        item3,
                        new DividerDrawerItem(),
                        item4,
                        new DividerDrawerItem(),
                        item5,
                        new DividerDrawerItem(),
                        item6,
                        new DividerDrawerItem(),
                        item7,
                        new DividerDrawerItem(),
                        item8
                ).withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        if (drawerItem.getIdentifier() == 2) {
                            Intent intent = new Intent(TeacherDatabase.this,Dashboard.class);
                            startActivity(intent);
                            finish();

                        }
                        if (drawerItem.getIdentifier() == 4) {
                            Intent intent = new Intent(TeacherDatabase.this, Events.class);
                            startActivity(intent);
                            finish();
                        }
                        if (drawerItem.getIdentifier() == 5) {
                            Intent intent = new Intent(TeacherDatabase.this, Result.class);
                            startActivity(intent);
                            finish();
                        }
                        if (drawerItem.getIdentifier() == 6) {
                            Intent intent = new Intent(TeacherDatabase.this, Attendance.class);

                            startActivity(intent);
                            finish();
                        }
                        if (drawerItem.getIdentifier() == 7) {
                            //Intent intent = new Intent(TeacherDatabase.this, TeacherDatabase.class);
                            //startActivity(intent);
                        }


                        return true;
                    }
                })
                .build();
        result.addItem(new DividerDrawerItem());
        result.addStickyFooterItem(new PrimaryDrawerItem().withName("Dashboard"));


        filter.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                fil = filter.getText().toString();
                data_list_filtered = new ArrayList<>();
                for (int i = 0; i < data_list.size(); i++) {
                    if (fil == "" || fil.isEmpty()) {
                        data_list_filtered = data_list;

                    } else if (data_list.get(i).getName().toLowerCase().contains(fil.toLowerCase())) {
                        data_list_filtered.add(data_list.get(i));
                    }
                }
                adapter.setMy_data(data_list_filtered);
                recyclerView.setAdapter(adapter);
            }
        });

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {


            }
        });
    }
    private void load_data_from_server(int id) {

        AsyncTask<Integer,Void,Void> task = new AsyncTask<Integer, Void, Void>() {
            @Override
            protected Void doInBackground(Integer... integers) {

                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .url("http://dbitstudent.webatu.com/new_data.php")
                        .build();
                try {
                    Response response = client.newCall(request).execute();

                    JSONArray array = new JSONArray(response.body().string());

                    for (int i=0; i<array.length(); i++){

                        JSONObject object = array.getJSONObject(i);

                        TeacherData data = new TeacherData(object.getInt("id"),object.getString("name"), object.getString("designation"), object.getString("room"),
                                object.getString("image_link"));

                        data_list.add(data);

                    }



                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    System.out.println("End of content");
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                adapter.notifyDataSetChanged();
            }
        };

        task.execute(id);
    }
}
