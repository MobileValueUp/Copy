package com.example.kimteaho.copy;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.support.v4.app.Fragment;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;


/**
 * Created by Kimteaho on 15. 7. 7..
 */
public class FragmentOne extends Fragment {

    GridView GridSchedule;
    ScheduleAdapter scheduleAdapter;
    String usCd;
    private ArrayList<String> arrayList_pron;
    private ArrayList<String> arrayList_subName;
    private ArrayList<String> arrayList_day1;
    private ArrayList<String> arrayList_time1;
    private ArrayList<String> arrayList_day2;
    private ArrayList<String> arrayList_time2;
    private ArrayList<String> arrayList_ttcode;
    private ArrayList<String> arrayList_pos1;
    private ArrayList<String> arrayList_pos2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_page1, container, false);
//레이아웃 인플레이트는 공식처럼 외우면 된다고 합니다!
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        //시간표


        super.onViewCreated(view, savedInstanceState);


        arrayList_pron = new ArrayList<String>();
        arrayList_subName = new ArrayList<String>();
        arrayList_day1 = new ArrayList<String>();
        arrayList_time1 = new ArrayList<String>();
        arrayList_day2 = new ArrayList<String>();
        arrayList_time2 = new ArrayList<String>();
        arrayList_ttcode = new ArrayList<String>();
        arrayList_pos1 = new ArrayList<String>();
        arrayList_pos2 = new ArrayList<String>();


        UserInfoGlobal userInfoGlobal = (UserInfoGlobal)getActivity().getApplication();

        usCd = userInfoGlobal.getUsCd();

        GridSchedule = (GridView)getActivity().findViewById(R.id.schedule_grid);

        scheduleAdapter = new ScheduleAdapter(getActivity());


        GridSchedule.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               Toast.makeText(getActivity().getApplicationContext(), "Selected position : " + position, Toast.LENGTH_SHORT).show();
           }
        });


        Net net = new Net();
        net.execute();

//이런식으로 프래그먼트를 3개 만듭니다. 탭이 4개면 4개~
    }

    public class Net extends AsyncTask<String,Void,String>
    {
        protected Net()
        {}

        protected void onPreExecute(){}

        protected String doInBackground(String... arg0)
        {
            try {

                String link = "http://52.68.141.174/php/schedule.php?usCd=";
                link += usCd;



                //String link = "http://52.68.141.174/php/test.php";

                URL url = new URL(link);
                url.openStream();

            }
            catch(Exception e){
                return new String("Exception:" + e.getMessage());
            }

            Parsing();


            return null;
        }

        protected void onPostExecute(String str)
        {

            for( int i=0; i<arrayList_subName.size(); i++)
            {

                scheduleAdapter.add(arrayList_subName.get(i),arrayList_day1.get(i),arrayList_day2.get(i),arrayList_time1.get(i),arrayList_time2.get(i));


            }

            GridSchedule.setAdapter(scheduleAdapter);
        }

        public void Parsing()
        {
            String link = "http://52.68.141.174/xml/schedule.xml";

            try
            {
                XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                factory.setNamespaceAware(true);
                XmlPullParser xpp = factory.newPullParser();

                URL Server = new URL(link);
                InputStream is = Server.openStream();
                xpp.setInput(is, "UTF-8");
                String tag;
                //String temp;
                int tagId=0;


                int eventType = xpp.getEventType();

                while(eventType != XmlPullParser.END_DOCUMENT)
                {
                    switch(eventType){
                        case XmlPullParser.START_DOCUMENT:
                            break;
                        case XmlPullParser.END_DOCUMENT:
                            break;
                        case XmlPullParser.START_TAG:
                            tag = xpp.getName();

                            if(tag.equals("pron"))
                                tagId = 1;
                            else if(tag.equals("subjectn"))
                                tagId = 2;
                            else if(tag.equals("day1"))
                                tagId = 3;
                            else if(tag.equals("time1"))
                                tagId = 4;
                            else if(tag.equals("day2"))
                                tagId = 5;
                            else if( tag.equals("time2"))
                                tagId = 6;
                            else if( tag.equals("ttcode"))
                                tagId = 7;
                            else if (tag.equals("pos1"))
                                tagId =8;
                            else if (tag.equals("pos2"))
                                tagId=9;
                            break;
                        case XmlPullParser.END_TAG:
                            break;
                        case XmlPullParser.TEXT:
                            if(tagId == 1)
                            {
                            }
                            else if (tagId == 2)
                            {
                                arrayList_subName.add(xpp.getText());
                            }
                            else if(tagId == 3)
                            {
                                arrayList_day1.add(xpp.getText());
                            }
                            else if(tagId == 4)
                            {
                                arrayList_time1.add(xpp.getText());
                            }
                            else if( tagId == 5)
                                arrayList_day2.add(xpp.getText());
                            else if(tagId == 6)
                                arrayList_time2.add(xpp.getText());
                            else if (tagId == 7)
                            {
                                arrayList_ttcode.add(xpp.getText());
                            }
                            else if (tagId == 8)
                                arrayList_pos1.add(xpp.getText());
                            else if(tagId == 9)
                                arrayList_pos2.add(xpp.getText());

                            tagId=0;
                            break;
                    }
                    eventType = xpp.next();
                }


            }
            catch(XmlPullParserException e)
            {
                e.printStackTrace();
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
        }


    }
}

