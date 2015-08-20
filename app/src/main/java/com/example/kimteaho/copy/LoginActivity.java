package com.example.kimteaho.copy;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLEncoder;



public class LoginActivity extends Activity {

    String usId = "";
    String usPwd= "";
    EditText edtId;
    EditText edtPwd;
    String parId;
    String parPwd;
    private Net net;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        TextView txt_login= (TextView)findViewById(R.id.textId);
        TextView txt_pwd= (TextView)findViewById(R.id.textPwd);
        ImageButton btnLogin = (ImageButton)findViewById(R.id.logBtn);
        ImageButton btnSignup = (ImageButton)findViewById(R.id.signUpBtn);
        edtId = (EditText)findViewById(R.id.inputId);
        edtPwd = (EditText)findViewById(R.id.inputPwd);

        Typeface typeface = Typeface.createFromAsset(getAssets(),"NanumBarunpenR.ttf");

        edtId.setTypeface(typeface);
        edtPwd.setTypeface(typeface);
        txt_login.setTypeface(typeface);
        txt_pwd.setTypeface(typeface);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usId = edtId.getText().toString();
                usPwd = edtPwd.getText().toString();

                Toast.makeText(getApplicationContext(),"click",Toast.LENGTH_SHORT).show();

                net = new Net();
                net.execute();


            }
        });

        btnSignup.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i = new Intent(LoginActivity.this,SignupActivity.class);
                startActivity(i);
            }
        });

    }


    /*@Override
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
    public class Net extends AsyncTask<String,Void,String>
    {
        protected Net()
        {}

        protected void onPreExecute(){}

        protected String doInBackground(String... arg0)
        {
            try {

                String link = "http://52.68.141.174/php/testId.php?usId=";
                link += usId;
                link += "&usPass=";
                link += usPwd;///


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
            if( parId.equals("ok"))
            {
                finish();
            }
            else
            {
                Toast.makeText(getApplicationContext(),"login failed",Toast.LENGTH_SHORT).show();
            }

        }

        public void Parsing()
        {
            String link = "http://52.68.141.174/xml/login.xml";

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
                            parId = xpp.nextText();

                        }
                        else if ( xpp.getName().equals("name"))
                        {

                        }
                        else if ( xpp.getName().equals("usCd"))
                        {

                        }
                        else if( xpp.getName().equals("school"))
                        {

                        }
                        else if ( xpp.getName().equals("point"))
                        {

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

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        switch (keyCode)
        {
            case KeyEvent.KEYCODE_BACK:
                moveTaskToBack(true);
                finish();
                android.os.Process.killProcess(android.os.Process.myPid());
        }

        return super.onKeyDown(keyCode, event);
    }
}
