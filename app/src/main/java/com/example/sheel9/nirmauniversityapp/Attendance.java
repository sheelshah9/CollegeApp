package com.example.sheel9.nirmauniversityapp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.holder.BadgeStyle;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

public class Attendance extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.attendance);

        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

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
                            Intent intent = new Intent(Attendance.this,Dashboard.class);
                            startActivity(intent);
                            finish();
                        }
                        if (drawerItem.getIdentifier() == 3) {
                            Intent intent = new Intent(Attendance.this, News.class);
                            startActivity(intent);
                        }
                        if (drawerItem.getIdentifier() == 4) {
                            Intent intent = new Intent(Attendance.this, Events.class);
                            startActivity(intent);
                            finish();
                        }
                        if (drawerItem.getIdentifier() == 5) {
                            Intent intent = new Intent(Attendance.this, Result.class);
                            startActivity(intent);
                            finish();
                        }
                        if (drawerItem.getIdentifier() == 6) {
                            //Intent intent = new Intent(Attendance.this, Attendance.class);

                            //startActivity(intent);
                        }
                        if (drawerItem.getIdentifier() == 7) {
                            Intent intent = new Intent(Attendance.this, TeacherDatabase.class);
                            startActivity(intent);
                            finish();
                        }


                        return true;
                    }
                })
                .build();
        result.addItem(new DividerDrawerItem());
        result.addStickyFooterItem(new PrimaryDrawerItem().withName("Attendance"));


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:
                    AttendanceSummary as = new AttendanceSummary();
                    return as;
                case 1:
                    AttendanceLecture la = new AttendanceLecture();
                    return la;
                case 2:
                    TutorialAttendence ta = new TutorialAttendence();
                    return ta;
                case 3:
                    LabAttendence pa = new LabAttendence();
                    return pa;
                default:
                        return null;
            }
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 4;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "SUMMARY";
                case 1:
                    return "LECTURE";
                case 2:
                    return "TUTORIAL";
                case 3:
                    return "LAB";
            }
            return null;
        }
    }
}
