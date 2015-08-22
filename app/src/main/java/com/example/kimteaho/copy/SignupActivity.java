package com.example.kimteaho.copy;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.net.URL;
import java.net.URLEncoder;


public class SignupActivity extends Activity {

    EditText edt_name;
    EditText edt_email;
    EditText edt_pwd;
    EditText edt_pwdcheck;
    boolean chkbox_sign= false;

    String name;
    String email;
    String pwd;
    String pwdchk;
    String school;
    String stdNum;
    boolean agree;
    String signOk="no";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        TextView txt_title = (TextView)findViewById(R.id.txt_sign_title);
        TextView txt_name = (TextView)findViewById(R.id.txt_sign_name);
        TextView txt_email = (TextView)findViewById(R.id.txt_sign_email);
        TextView txt_pwd = (TextView)findViewById(R.id.txt_sign_pwd);
        TextView txt_pwdcheck = (TextView)findViewById(R.id.txt_sign_pwdcheck);
        final TextView txt_studentnum = (TextView)findViewById(R.id.txt_sign_student_number);
        final TextView txt_schoolsearch = (TextView)findViewById(R.id.txt_sign_school_search);
        final TextView txt_school = (TextView)findViewById(R.id.txt_sign_school);
        final TextView txt_school_select = (TextView)findViewById(R.id.txt_sign_school_select);
        TextView txt_agree = (TextView)findViewById(R.id.txt_sign_agree);
        TextView txt_policy = (TextView)findViewById(R.id.txt_sign_policy);


        edt_name = (EditText)findViewById(R.id.edt_sign_name);
        edt_email = (EditText)findViewById(R.id.edt_sign_email);
        edt_pwd = (EditText)findViewById(R.id.edt_sign_pwd);
        edt_pwdcheck = (EditText)findViewById(R.id.edt_sign_pwdcheck);


        Typeface typeface = Typeface.createFromAsset(getAssets(),"NanumBarunGothic.ttf");
        Typeface typefacel = Typeface.createFromAsset(getAssets(),"NanumBarunGothicLight.ttf");

        txt_title.setTypeface(typeface);
        txt_name.setTypeface(typeface);
        txt_email.setTypeface(typeface);
        txt_pwd.setTypeface(typeface);
        txt_pwdcheck.setTypeface(typeface);
        txt_studentnum.setTypeface(typeface);
        txt_schoolsearch.setTypeface(typeface);
        txt_school.setTypeface(typeface);
        txt_agree.setTypeface(typeface);
        txt_policy.setTypeface(typeface);

        txt_school_select.setTypeface(typefacel);

        edt_name.setTypeface(typefacel);
        edt_email.setTypeface(typefacel);
        edt_pwd.setTypeface(typefacel);
        edt_pwdcheck.setTypeface(typefacel);

        final Spinner dropdown_student = (Spinner)findViewById(R.id.spinner_student_number);
        String[] items = new String[]{"09", "10", "11"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.spinner_txt,items);



        dropdown_student.setAdapter(adapter);

        dropdown_student.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                stdNum = (String)dropdown_student.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        ImageButton btn_back = (ImageButton)findViewById(R.id.btnBackSign);

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        final ImageButton btn_agree = (ImageButton)findViewById(R.id.sign_checkbox);

        btn_agree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(chkbox_sign)
                {
                    btn_agree.setImageResource(R.drawable.uncheck_signup);
                    chkbox_sign = false;
                }
                else
                {
                    btn_agree.setImageResource(R.drawable.check_signup);
                    chkbox_sign = true;
                }

            }
        });


        ImageButton btn_signup = (ImageButton)findViewById(R.id.btn_sign_signup);

        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                name = edt_name.getText().toString();
                email = edt_email.getText().toString();
                pwd = edt_pwd.getText().toString();
                pwdchk = edt_pwdcheck.getText().toString();

                school = txt_school_select.getText().toString();

                if(name.isEmpty() && email.isEmpty() && pwd.isEmpty() && pwdchk.isEmpty() )
                    Toast.makeText(getApplicationContext(), "input values",Toast.LENGTH_SHORT).show();
                else if( (pwd.equals(pwdchk) == false) && chkbox_sign )
                    Toast.makeText(getApplicationContext(),"pwd incorrect",Toast.LENGTH_SHORT).show();
                else
                {
                    Net net = new Net();
                    net.execute();

                }
            }
        });
    }


    public class Net extends AsyncTask<String,Void,String>
    {
        protected Net()
        {}

        protected void onPreExecute(){}

        protected String doInBackground(String... arg0)
        {
            try {

                String server = "http://52.68.141.174/php/signUp.php?name=";
                String values ="";
                values += URLEncoder.encode(name,"UTF-8");
                values += "&email=";
                values += URLEncoder.encode(email,"UTF-8");
                values +="&pwd=";
                values +=URLEncoder.encode(pwd,"UTF-8");
                values +="&school=";
                values +=URLEncoder.encode(school,"UTF-8");
                values +="&stdNum=";
                values +=URLEncoder.encode(stdNum,"UTF-8");

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
                Toast.makeText(getApplicationContext(), "sign up success", Toast.LENGTH_SHORT).show();
                finish();
            }
            else
            {
                Toast.makeText(getApplicationContext(), "sign up failed", Toast.LENGTH_SHORT).show();
            }

        }

        public void Parsing()
        {
            String link = "http://52.68.141.174/xml/signUp.xml";

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
