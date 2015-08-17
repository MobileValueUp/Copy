package com.example.kimteaho.copy;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;


public class FileViewActivity extends Activity {

    String subN;
    private String tt;
    ArrayList<String> arrayList_path;
    ArrayList<String>arrayList_time;
    ArrayList<String>arrayList_filecode;
    ListView file_list;
    CustomAdapterFile adapterFile;
    private Net net;
    String parId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_view);
        Intent intent = getIntent();
        subN = intent.getExtras().getString("subn");
        tt = intent.getExtras().getString("tt");

        arrayList_path = new ArrayList<String>();
        arrayList_time = new ArrayList<String>();
        arrayList_filecode = new ArrayList<String>();

        TextView txt_subn = (TextView)findViewById(R.id.txt_file_subjectn);
        txt_subn.setText(subN);

        file_list = (ListView)findViewById(R.id.list_file);

        adapterFile = new CustomAdapterFile();

        //Toast.makeText(getApplicationContext(),tt,Toast.LENGTH_SHORT).show();

        net = new Net();
        net.execute();
    }




    public class Net extends AsyncTask<String,Void,String> {
        protected Net() {
        }

        protected void onPreExecute() {
        }

        protected String doInBackground(String... arg0) {
            try {

                String link = "http://52.68.141.174/php/file.php?ttCd=";
                link += tt;


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

            for (int i = 0; i < arrayList_path.size(); i++) {

                adapterFile.add(arrayList_path.get(i), arrayList_filecode.get(i),arrayList_time.get(i),subN);


            }
            file_list.setAdapter(adapterFile);

        }

        public void Parsing() {
            String link = "http://52.68.141.174/xml/file.xml";

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

                            if (tag.equals("path"))
                                tagId = 1;
                            else if (tag.equals("time"))
                                tagId = 2;
                            else if (tag.equals("filecode"))
                                tagId = 3;
                            break;
                        case XmlPullParser.END_TAG:
                            break;
                        case XmlPullParser.TEXT:
                            if (tagId == 1) {
                                arrayList_path.add(xpp.getText());
                            } else if (tagId == 2) {
                                arrayList_time.add(xpp.getText());
                            } else if (tagId == 3) {
                                arrayList_filecode.add(xpp.getText());
                            }
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