package com.example.sheel9.nirmauniversityapp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.holder.BadgeStyle;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

public class Dashboard extends AppCompatActivity {
    private RecyclerView recyclerView;
    private GridLayoutManager gridLayoutManager;
    private DashboardCardAdapter adapter;
    String[][] dataSlider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        dataSlider=new String[6][5];

        feedData();

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        gridLayoutManager = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(gridLayoutManager);
        adapter = new DashboardCardAdapter(this,dataSlider);

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
                            //Intent intent = new Intent(Dashboard.this,News.class);
                            //startActivity(intent);

                        }
                        if (drawerItem.getIdentifier() == 3) {
                            Intent intent = new Intent(Dashboard.this, News.class);
                            startActivity(intent);
                        }
                        if (drawerItem.getIdentifier() == 4) {
                            Intent intent = new Intent(Dashboard.this, Events.class);
                            startActivity(intent);
                        }
                        if (drawerItem.getIdentifier() == 5) {
                            Intent intent = new Intent(Dashboard.this, Result.class);
                            startActivity(intent);
                        }
                        if (drawerItem.getIdentifier() == 6) {
                            Intent intent = new Intent(Dashboard.this, Attendance.class);

                            startActivity(intent);
                        }
                        if (drawerItem.getIdentifier() == 7) {
                            Intent intent = new Intent(Dashboard.this, TeacherDatabase.class);
                            startActivity(intent);
                        }


                        return true;
                    }
                })
                .build();
        result.addItem(new DividerDrawerItem());
        result.addStickyFooterItem(new PrimaryDrawerItem().withName("Dashboard"));

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {


            }
        });
    }

    public void feedData(){


        dataSlider[0][0]="News";
        dataSlider[0][1]="http://students.iitk.ac.in/robocon/images/2017.png";
        dataSlider[0][2]="http://deshgujarat.com/wp-content/uploads/2012/02/Narendra-Modi-Gujarat.jpg";
        dataSlider[0][3]="http://www.nirmauni.ac.in/ImageResize.aspx?imageUrl=~/Upload/ITNU/NewsPhoto/PB1_01032017_110639PM.jpg&imageWidth=400&imageHeight=300";
        dataSlider[0][4]="http://www.nirmauni.ac.in/ImageResize.aspx?imageUrl=~/Upload/ITNU/NewsPhoto/CL/fs2_28022017_034214PM.jpg&imageWidth=400&imageHeight=300";
        dataSlider[1][0]="Events";
        dataSlider[1][1]="http://nutech.nirmauni.ac.in/2014/04/DSC04583.jpg";
        dataSlider[1][2]="http://nutech.nirmauni.ac.in/images/nu_tech_16.jpg";
        dataSlider[1][3]="http://nutech.nirmauni.ac.in/images/event_1.jpg";
        dataSlider[1][4]="http://nutech.nirmauni.ac.in/images/nutech_event_3.jpg";
        dataSlider[2][0]="Result";
        dataSlider[2][1]="http://media2.intoday.in/indiatoday/images/stories/results-305-2_061516032339.jpg";
        dataSlider[2][2]="http://www.gmcs.edu.in/weos/weos/upload/notification_imgs/resultImage.jpg?i=86929";
        dataSlider[2][3]="http://www.excel-easy.com/ogi/examples/line-chart.png";
        dataSlider[2][4]="http://psd-files.com/wp-content/uploads/164_352.jpg";
        dataSlider[3][0]="Attendance";
        dataSlider[3][1]="https://docs.oracle.com/javase/8/javafx/user-interface-tutorial/img/pie-sample.png";
        dataSlider[3][2]="https://cdn.pixabay.com/photo/2016/08/04/14/32/pie-chart-1569175_960_720.jpg";
        dataSlider[3][3]="https://thumbs.dreamstime.com/z/vector-circular-chart-graph-infographic-template-background-stylish-multicolor-round-percent-line-step-indicators-infographics-59050580.jpg";
        dataSlider[3][4]="https://s-media-cache-ak0.pinimg.com/originals/0d/be/95/0dbe95fa8a8c57af91d28fa8a6759813.png";
        dataSlider[4][0]="Database";
        dataSlider[4][1]="https://www.essentialsql.com/wp-content/uploads/2014/05/database-parts.jpg";
        dataSlider[4][2]="https://news.bitcoin.com/wp-content/uploads/2015/12/Database-300x300.png";
        dataSlider[4][3]="http://www.d11.org/Technology/ADA/PublishingImages/database2.jpg";
        dataSlider[4][4]="https://www.essentialsql.com/wp-content/uploads/2014/05/database-parts.jpg";
        dataSlider[5][0]="Archives";
        dataSlider[5][1]="https://www.doaks.org/library-archives/library-and-archives-banner-images/ICFA%20Archives_Landscape_02.jpg";
        dataSlider[5][2]="http://www.shetlandmuseumandarchives.org.uk/site/assets/files/1076/archives_main_thumbnail.jpg";
        dataSlider[5][3]="http://www.exploreyourarchive.org/wp-content/uploads/sites/11/2016/11/Parliamentary-Archive-tour.jpg";
        dataSlider[5][4]="http://www.acrobatremovals.com/wp-content/uploads/2014/12/archives-folders.jpg";


    }
}
