package com.example.kimteaho.copy;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;


public class ReceiptActivity extends Activity {


    UserInfoGlobal userInfoGlobal;
    ListView rept_list;
    CustomAdapterReciept adapterR;
    ArrayList<String> arrayList_filen;
    ArrayList<String> arrayList_ispaid;
    ArrayList<String> arrayList_subn;
    ArrayList<String> arrayList_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receipt);

        userInfoGlobal = (UserInfoGlobal) getApplication();

        arrayList_filen = new ArrayList<String>();
        arrayList_ispaid = new ArrayList<String>();
        arrayList_subn = new ArrayList<String>();
        arrayList_id = new ArrayList<String>();


        rept_list = (ListView)findViewById(R.id.list_receipt);
        adapterR = new CustomAdapterReciept();


        TextView txt_title = (TextView)findViewById(R.id.txt_title_name);
    /*    TextView subject_name1  = (TextView)findViewById(R.id.subject_name1);
        TextView file_name1 = (TextView)findViewById(R.id.file_name1);
        TextView due_time1 = (TextView)findViewById(R.id.txt_due_time1);
        TextView subject_name2  = (TextView)findViewById(R.id.subject_name2);
        TextView file_name2 = (TextView)findViewById(R.id.file_name2);
        TextView due_time2 = (TextView)findViewById(R.id.txt_due_time2);*/


        Typeface typeface = Typeface.createFromAsset(getAssets(),"NanumBarunGothic.ttf");
        Typeface typefacel=  Typeface.createFromAsset(getAssets(), "NanumBarunGothicLight.ttf");

        txt_title.setTypeface(typeface);
       /* subject_name1.setTypeface(typeface);
        file_name1.setTypeface(typefacel);
        due_time1.setTypeface(typeface);
        subject_name2.setTypeface(typeface);
        file_name2.setTypeface(typefacel);
        due_time2.setTypeface(typeface);*/


       // final ImageButton btn_next1 = (ImageButton)findViewById(R.id.next1);

        /*btn_next1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                Intent i = new Intent(ReceiptActivity.this, ReceiptCheck1Activity.class);
                finish();
                startActivity(i);

            }

        });*/


        Net net = new Net();

        net.execute();



    }

/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
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

*/


    public class Net extends AsyncTask<String,Void,String> {
        protected Net() {
        }

        protected void onPreExecute() {
        }

        protected String doInBackground(String... arg0) {
            try {

                String link = "http://52.68.141.174/php/Receiptfind.php?usCd=";
                link += userInfoGlobal.getUsCd();


                //String link = "http://52.68.141.174/php/test.php";

                URL url = new URL(link);
                url.openStream();

            } catch (Exception e) {
                return new String("Exception:" + e.getMessage());
            }

            Parsing();


            return null;
        }

        protected void onPostExecute(String str) {

            for (int i = 0; i < arrayList_filen.size(); i++) {

                adapterR.add(arrayList_filen.get(i), arrayList_subn.get(i),arrayList_ispaid.get(i),arrayList_id.get(i));


            }
            rept_list.setAdapter(adapterR);

        }

        public void Parsing() {
            String link = "http://52.68.141.174/xml/Receiptfind.xml";

            try {
                XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                factory.setNamespaceAware(true);
                XmlPullParser xpp = factory.newPullParser();

                URL Server = new URL(link);
                InputStream is = Server.openStream();
                xpp.setInput(is, "UTF-8");
                String tag;
                //String temp;
                int tagId = 0;


                int eventType = xpp.getEventType();

                while (eventType != XmlPullParser.END_DOCUMENT) {
                    switch (eventType) {
                        case XmlPullParser.START_DOCUMENT:
                            break;
                        case XmlPullParser.END_DOCUMENT:
                            break;
                        case XmlPullParser.START_TAG:
                            tag = xpp.getName();

                            if (tag.equals("filen"))
                                tagId = 1;
                            else if (tag.equals("ispaid"))
                                tagId = 2;
                            else if ( tag.equals("subn"))
                                tagId = 3;
                            else if(tag.equals("id"))
                                tagId = 4;
                            break;
                        case XmlPullParser.END_TAG:
                            break;
                        case XmlPullParser.TEXT:
                            if (tagId == 1) {
                                arrayList_filen.add(xpp.getText());
                            } else if (tagId == 2) {
                                arrayList_ispaid.add(xpp.getText());
                            }
                            else if ( tagId == 3)
                                arrayList_subn.add(xpp.getText());
                            else if( tagId ==4)
                                arrayList_id.add(xpp.getText());
                            tagId = 0;
                            break;
                    }
                    eventType = xpp.next();
                }


            } catch (XmlPullParserException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
