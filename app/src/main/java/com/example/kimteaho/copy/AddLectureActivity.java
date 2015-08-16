package com.example.kimteaho.copy;

//import android.app.ActionBar;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;


public class AddLectureActivity extends ActionBarActivity {

    boolean radio_subn = true;
    boolean radio_prof = false;
    String radio_result="subjectn";
    String input;
    String urlttcode;

    ListView listView_addLecture;
    CustomAdapterAdd customAdapterAdd;

    private ArrayList<String> arrayList_pron;
    private ArrayList<String> arrayList_subName;
    private ArrayList<String> arrayList_day1;
    private ArrayList<String> arrayList_time1;
    private ArrayList<String> arrayList_day2;
    private ArrayList<String> arrayList_time2;
    private ArrayList<String> arrayList_ttcode;
    private ArrayList<String> arrayList_subc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_lecture);

        final ActionBar actionBar = getSupportActionBar();

        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        //actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.btnback);
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setCustomView(R.layout.actionbar_layout);
        //actionBar.setLogo(R.drawable.btnback);


      /*  RadioGroup radioGroup_schop = (RadioGroup)findViewById(R.id.rdg_add_lecture_1);

        radioGroup_schop.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

            }
        });*/

        RadioButton radioButton_subn = (RadioButton)findViewById(R.id.radio_search_subn);
        radioButton_subn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                radio_subn = true;
                radio_prof = false;
                radio_result = "subjectn";
            }
        });

        final RadioButton radioButton_prof = (RadioButton)findViewById(R.id.radio_search_prof);
        radioButton_prof.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                radio_subn = false;
                radio_prof = true;

                radio_result="pron";
            }
        });




        final EditText edt_input = (EditText)findViewById(R.id.edt_addlecture);

        ImageButton imgbtn_search = (ImageButton)findViewById(R.id.imgbtn_addLecture_search);
        imgbtn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input = edt_input.getText().toString();

                arrayList_pron.clear();
                arrayList_subc.clear();
                arrayList_subName.clear();
                arrayList_day1.clear();
                arrayList_time1.clear();
                arrayList_day2.clear();
                arrayList_time2.clear();
                arrayList_ttcode.clear();

                Net net = new Net();
                net.execute();
            }
        });

        listView_addLecture = (ListView)findViewById(R.id.list_addLecture_search);
        customAdapterAdd = new CustomAdapterAdd();

        listView_addLecture.setAdapter(customAdapterAdd);

        arrayList_pron = new ArrayList<String>();
        arrayList_subName = new ArrayList<String>();
        arrayList_day1 =new ArrayList<String>();
        arrayList_time1=new ArrayList<String>();
        arrayList_day2=new ArrayList<String>();
        arrayList_time2=new ArrayList<String>();
        arrayList_ttcode=new ArrayList<String>();
        arrayList_subc = new ArrayList<String>();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_lecture, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_add_lecture) {

            ArrayList<Boolean> temp = customAdapterAdd.getBool();

            for(int i=0; i<temp.size(); i++)
            {
                if(temp.get(i))
                {
                    urlttcode = arrayList_ttcode.get(i);
                    Toast.makeText(getApplicationContext(),arrayList_ttcode.get(i),Toast.LENGTH_SHORT).show();
                    Net2 net2 = new Net2();
                    net2.execute();
                }
            }

            return true;
        }

        if( id == android.R.id.home)
        {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }


    public class Net extends AsyncTask<String,Void,String>
    {
        protected Net()
        {}

        protected void onPreExecute(){}

        protected String doInBackground(String... arg0)
        {
            try {

                String link = "http://52.68.141.174/php/searchlecture.php?usCd=";
                link += "23&";
                link +="cond=" + radio_result;
                link += "&input=";
                link += URLEncoder.encode(input,"UTF-8");



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

            customAdapterAdd.clearAll();
            for( int i=0; i<arrayList_subName.size(); i++)
            {

                customAdapterAdd.add(arrayList_subName.get(i),arrayList_subc.get(i),arrayList_pron.get(i),(arrayList_day1.get(i)+arrayList_time1.get(i)+arrayList_day2.get(i)+arrayList_time2.get(i)));


            }
            listView_addLecture.setAdapter(customAdapterAdd);
            ((BaseAdapter) listView_addLecture.getAdapter()).notifyDataSetChanged();



        }

        public void Parsing()
        {
            String link = "http://52.68.141.174/xml/searchlecture.xml";

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
                            else if( tag.equals("subc"))
                                tagId=8;
                            break;
                        case XmlPullParser.END_TAG:
                            break;
                        case XmlPullParser.TEXT:
                            if(tagId == 1)
                            {
                                arrayList_pron.add(xpp.getText());
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
                                arrayList_subc.add(xpp.getText());

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



    public class Net2 extends AsyncTask<String,Void,String>
    {
        protected Net2()
        {}

        protected void onPreExecute(){}

        protected String doInBackground(String... arg0)
        {
            try {

                String link = "http://52.68.141.174/php/addlecture.php?usCd=";
                link += "23&";
                link +="ttcode=" + urlttcode;



                //String link = "http://52.68.141.174/php/test.php";

                URL url = new URL(link);
                url.openStream();

            }
            catch(Exception e){
                return new String("Exception:" + e.getMessage());
            }



            return null;
        }

        protected void onPostExecute(String str)
        {




        }


    }
}
