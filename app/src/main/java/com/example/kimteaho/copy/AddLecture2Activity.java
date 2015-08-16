package com.example.kimteaho.copy;

//import android.app.ActionBar;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;


public class AddLecture2Activity extends ActionBarActivity {

    TextView txt_addlecture2_subn;
    TextView txt_addlecture2_profn;
    TextView txt_addlecture2_subcode;
    TextView txt_addlecture2_subcode_help;

    EditText edt_addlecture2_subn;
    EditText edt_addlecture2_pofn;
    EditText edt_addlecture2_subcode;

    String day1;
    String day2;
    String time1;
    String time2;

    String edt_subn;
    String edt_profn;
    String edt_subcode;
    String pos1;
    String pos2;
    String signOk="no";
    String usCd = "23";
    String school = "광운대학교";

    String[] mWeekTitleIds ={
            "",
            "MON",
            "TUE",
            "WED",
            "THU",
            "FRI"

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_lecture2);

        ActionBar actionBar = getSupportActionBar();

        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.btnback);
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setCustomView(R.layout.actionbar_layout);

        txt_addlecture2_profn = (TextView)findViewById(R.id.txt_addlecture2_profn);
        txt_addlecture2_subn = (TextView)findViewById(R.id.txt_addlecture2_subn);
        txt_addlecture2_subcode = (TextView)findViewById(R.id.txt_addlecture2_subcode);
        txt_addlecture2_subcode_help = (TextView)findViewById(R.id.txt_addlecture2_subcode_help);

        edt_addlecture2_pofn = (EditText)findViewById(R.id.edt_addlecture2_profn);
        edt_addlecture2_subn = (EditText)findViewById(R.id.edt_addlecture2_subn);
        edt_addlecture2_subcode = (EditText)findViewById(R.id.edt_addlecture2_subcode);


        Typeface typeface = Typeface.createFromAsset(getAssets(),"NanumBarunGothic.ttf");
        Typeface typeface1 = Typeface.createFromAsset(getAssets(), "NanumBarunGothicLight.ttf");


        txt_addlecture2_profn.setTypeface(typeface);
        txt_addlecture2_subn.setTypeface(typeface);
        txt_addlecture2_subcode.setTypeface(typeface);
        txt_addlecture2_subcode.setTypeface(typeface);

        txt_addlecture2_subcode_help.setTypeface(typeface1);

        edt_addlecture2_pofn.setTypeface(typeface1);
        edt_addlecture2_subn.setTypeface(typeface1);
        edt_addlecture2_subcode.setTypeface(typeface1);

        txt_addlecture2_subcode_help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt_addlecture2_subcode_help.setVisibility(View.INVISIBLE);


            }
        });

        day1 = "MON";
        day2 = "MON";
        time1 = "1";
        time2 = "1";

        ImageButton imageButton_submit = (ImageButton)findViewById(R.id.imgbtn_addLecture2_submit);
        imageButton_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                edt_profn = edt_addlecture2_pofn.getText().toString();
                edt_subn = edt_addlecture2_subn.getText().toString();
                edt_subcode = edt_addlecture2_subcode.getText().toString();



                for (int j = 0; j < 6; j++) {

                    if (mWeekTitleIds[j].equals(day1))
                    {

                        int temp = 0;
                        temp +=j;
                        temp += 6 * Integer.valueOf(time1);
                        pos1 = String.valueOf(temp);

                    }

                    if ( mWeekTitleIds[j].equals(day2) )
                    {

                        int temp = 0;
                        temp +=j;
                        temp += 6 * Integer.valueOf(time2);
                        pos2 = String.valueOf(temp);

                    }

                }


                if(Integer.valueOf(pos1) > Integer.valueOf(pos2))
                {
                    String temp;

                    temp = pos1;

                    pos1 = pos2;

                    pos2 = temp;
                }


                Net net = new Net();

                net.execute();


            }




        });


        RadioGroup radioGroup_day1 = (RadioGroup)findViewById(R.id.rdg_add_lecture_2);

        radioGroup_day1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                RadioButton checkedBtn = (RadioButton)findViewById(checkedId);


                boolean isChecked = checkedBtn.isChecked();

                if(isChecked)
                    day1 = checkedBtn.getTag().toString();

            }
        });

        RadioGroup radioGroup_time1 = (RadioGroup)findViewById(R.id.rdg_add_lecture_3);
        radioGroup_time1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                RadioButton checkedBtn = (RadioButton)findViewById(checkedId);


                boolean isChecked = checkedBtn.isChecked();

                if(isChecked)
                    time1 = checkedBtn.getTag().toString();
            }
        });


        RadioGroup radioGroup_day2 = (RadioGroup)findViewById(R.id.rdg_add_lecture_4);

        radioGroup_day2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                RadioButton checkedBtn = (RadioButton)findViewById(checkedId);


                boolean isChecked = checkedBtn.isChecked();

                if(isChecked)
                    day2 = checkedBtn.getTag().toString();

            }
        });

        RadioGroup radioGroup_time2 = (RadioGroup)findViewById(R.id.rdg_add_lecture_5);

        radioGroup_time2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                RadioButton checkedBtn = (RadioButton)findViewById(checkedId);


                boolean isChecked = checkedBtn.isChecked();

                if(isChecked)
                    time2 = checkedBtn.getTag().toString();

            }
        });
    }




    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        if( id == android.R.id.home)
        {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_lecture2, menu);
        return true;
    }


    public class Net extends AsyncTask<String,Void,String>
    {
        protected Net()
        {}

        protected void onPreExecute(){}

        protected String doInBackground(String... arg0)
        {
            try {

                String server = "http://52.68.141.174/php/addLecture.php?profn=";
                String values ="";
                values += URLEncoder.encode(edt_profn, "UTF-8");
                values += "&subn=";
                values += URLEncoder.encode(edt_subn,"UTF-8");
                values +="&subcode=";
                values +=URLEncoder.encode(edt_subcode,"UTF-8");
                values +="&day1=";
                values +=URLEncoder.encode(day1,"UTF-8");
                values +="&time1=";
                values +=URLEncoder.encode(time1,"UTF-8");
                values  +="&day2="+day2+"&time2="+time2+ "&usCd="+usCd+"&shcool="+URLEncoder.encode(school);


                //Toast.makeText(getApplicationContext(),link,Toast.LENGTH_LONG).show();


                //link = URLEncoder.encode(link,"UTF-8");
                //String link = "http://52.68.141.174/php/test.php";


                URL url = new URL(server + values);
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
            if( signOk.equals("ok"))
            {
                Toast.makeText(getApplicationContext(), "add success", Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(getApplicationContext(), "add failed", Toast.LENGTH_SHORT).show();
            }

        }

        public void Parsing()
        {
            String link = "http://52.68.141.174/xml/add.xml";

            try
            {
                XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                factory.setNamespaceAware(true);
                XmlPullParser xpp = factory.newPullParser();

                URL Server = new URL(link);
                InputStream is = Server.openStream();
                xpp.setInput(is, "UTF-8");

                int eventType = xpp.getEventType();

                while(eventType != XmlPullParser.END_DOCUMENT)
                {
                    if(eventType == XmlPullParser.START_TAG)
                    {
                        if(xpp.getName().equals("ok") )
                        {
                            signOk = xpp.nextText();

                        }

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
