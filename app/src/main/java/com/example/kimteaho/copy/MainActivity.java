package com.example.kimteaho.copy;

import android.app.Activity;
import android.content.Intent;
import android.graphics.NinePatch;
import android.graphics.drawable.NinePatchDrawable;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
//import android.app.Fragment;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.widget.DrawerLayout;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;


public class MainActivity extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks,ActionBar.TabListener {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    FragmentPagerAdapter Adapter;
    private NavigationDrawerFragment mNavigationDrawerFragment;
    SectionsPagerAdapter mSectionsPagerAdapter;
    ViewPager mViewPager;
    FragmentTabHost tabHost;
    FragmentOne fr = new FragmentOne();
    FragmentTwo fr2 = new FragmentTwo();
    FragmentThree fr3 = new FragmentThree();
    FragmentFour fr4 = new FragmentFour();

    ImageView btn1;
    ImageView btn2;
    ImageView btn3;




    private static final String TAB1 = "tab1";
    private static final String TAB2 = "tab2";
    private static final String TAB3 = "tab3";
    private static final String TAB4 = "tab4";
    private static final String TAB5 = "tab5";


//add name test
    //test

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn1 = (ImageView)findViewById(R.id.imageView1);
        btn2 = (ImageView)findViewById(R.id.imageView2);
        btn3 = (ImageView)findViewById(R.id.imageView3);

       // Intent intentLoginActivity = new Intent(MainActivity.this,LoginActivity.class);
       // startActivity(intentLoginActivity);

        final ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.


        //this.setActionBar();

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));

        btn1.setImageResource(R.drawable.timetable_unclicked);
        btn2.setImageResource(R.drawable.paper_unclicked);
        btn3.setImageResource(R.drawable.frined_unclicked);

        btn1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN)
                    btn1.setImageResource(R.drawable.timetable_clicked);
                if (event.getAction() == MotionEvent.ACTION_UP)
                    btn1.setImageResource(R.drawable.timetable_unclicked);

                return false;
            }

        });

        btn2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() ==MotionEvent.ACTION_DOWN)
                    btn2.setImageResource(R.drawable.paper_clicked);
                if(event.getAction() ==MotionEvent.ACTION_UP)
                    btn2.setImageResource(R.drawable.paper_unclicked);

                return false;

            }
        });
        btn3.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
               if(event.getAction() ==MotionEvent.ACTION_DOWN)
                   btn3.setImageResource(R.drawable.friend_clicked);
                if(event.getAction() ==MotionEvent.ACTION_UP)
                   btn3.setImageResource(R.drawable.frined_unclicked);
                return false;

            }
        });



        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn1.setImageResource(R.drawable.timetable_unclicked);
                FragmentManager fmm = getSupportFragmentManager();
                FragmentTransaction fra2 =fmm.beginTransaction();
                fra2.replace(R.id.realtabcontent,fr);
                fra2.commit();

            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn2.setImageResource(R.drawable.paper_unclicked);
                FragmentManager fmm = getSupportFragmentManager();
                FragmentTransaction fra2 =fmm.beginTransaction();
                fra2.replace(R.id.realtabcontent,fr2);
                fra2.commit();
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn3.setImageResource(R.drawable.frined_unclicked);
                FragmentManager fmm = getSupportFragmentManager();
                FragmentTransaction fra2 =fmm.beginTransaction();
                fra2.replace(R.id.realtabcontent,fr3);
                fra2.commit();

            }
        });




        /*tabHost = (FragmentTabHost)findViewById(R.id.tabhost);

        tabHost.setup(this, getSupportFragmentManager(),R.id.realtabcontent);
        tabHost.addTab(tabHost.newTabSpec(TAB1).setIndicator("시간표"), FragmentOne.class ,null);
        tabHost.addTab(tabHost.newTabSpec(TAB2).setIndicator("강의"), FragmentTwo.class ,null);
        tabHost.addTab(tabHost.newTabSpec(TAB3).setIndicator("친구"), FragmentThree.class ,null);
        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                Toast.makeText(getApplicationContext(),tabId,Toast.LENGTH_SHORT).show();
                if(tabId=="tab1")
                {

                    FragmentManager fmm = getSupportFragmentManager();
                    FragmentTransaction fra2 =fmm.beginTransaction();
                    fra2.replace(R.id.realtabcontent,fr);
                    fra2.commit();
                }
                else if(tabId=="tab2")
                {

                    FragmentManager fmm = getSupportFragmentManager();
                    FragmentTransaction fra2 =fmm.beginTransaction();
                    fra2.replace(R.id.realtabcontent,fr2);
                    fra2.commit();

                }else
                {

                    FragmentManager fmm = getSupportFragmentManager();
                    FragmentTransaction fra2 =fmm.beginTransaction();
                    fra2.replace(R.id.realtabcontent,fr3);
                    fra2.commit();

                }

            }
        });*/


       // tabHost.addTab(tabHost.newTabSpec(TAB4).setIndicator("TAB4"), FragmentFour.class ,null);
       // tabHost.addTab(tabHost.newTabSpec(TAB5).setIndicator("TAB5"), FragmentFive.class ,null);


    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, PlaceholderFragment.newInstance(position + 1))
                .commit();
        if(position ==0)
        {

            Intent intent = new Intent(getApplicationContext(), UploadActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_USER_ACTION);
            overridePendingTransition(0, 0);
            // intent.putExtra("myid", myid); //값 전달
            //  intent.putExtra("price", price);
            startActivityForResult(intent, 1); // 값 받는방법
        }



        if(position ==1)
        {
            Intent intent = new Intent(getApplicationContext(), ReceiptActivity.class);
            // intent.putExtra("myid", myid); //값 전달
            //  intent.putExtra("price", price);
            startActivityForResult(intent, 1); // 값 받는방법
        }
        else if(position ==2)
        {
            Intent intent = new Intent(getApplicationContext(), PersonalPrintActivity.class);
            // intent.putExtra("myid", myid); //값 전달
            //  intent.putExtra("price", price);
            startActivityForResult(intent, 1); // 값  받는방법
        }


    }

    public void onSectionAttached(int number) {
        switch (number) {
            case 1:
                mTitle = getString(R.string.title_section1);
                Toast.makeText(getApplicationContext(),"asd",Toast.LENGTH_SHORT).show();
                break;
            case 2:
                mTitle = getString(R.string.title_section2);
                break;
            case 3:
                mTitle = getString(R.string.title_section3);
                break;
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.

            getMenuInflater().inflate(R.menu.main, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
         //   FragmentFour fr = new FragmentFour();

           // fr = new FragmentFour();

         //   android.app.FragmentManager fm = getFragmentManager();
            FragmentManager fmm = getSupportFragmentManager();


          //  android.app.FragmentTransaction  fra = fm.beginTransaction();
            FragmentTransaction fra2 =fmm.beginTransaction();

            //fra.replace(R.id.realtabcontent, fr);
            fra2.replace(R.id.realtabcontent,fr4);
            //tabHost.setup(this, getSupportFragmentManager(),R.id.realtabcontent2);
            fra2.commit();


            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            Locale l = Locale.getDefault();
            switch (position) {
                case 0:
                    return getString(R.string.title_section1).toUpperCase(l);
                case 1:
                    return getString(R.string.title_section2).toUpperCase(l);
                case 2:
                    return getString(R.string.title_section3).toUpperCase(l);
            }
            return null;
        }
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        if(tab.getPosition()==0)
        {


        }
        else if(tab.getPosition()==1)
        {
            Toast.makeText(getApplicationContext(),"2222",Toast.LENGTH_SHORT).show();


        }else
        {

        }
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((MainActivity) activity).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));
        }
    }


    /**
     * A placeholder fragment containing a simple view.
     */

}
