package com.example.kimteaho.copy;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;


public class ReceiptCheck3Activity extends Activity {

    String id;

    Net net;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receiptcheck3);

        Intent intent = getIntent();

        id = intent.getExtras().getString("id");
        net = new Net();

        TextView txt_title_name = (TextView)findViewById(R.id.txt_title_name);
        TextView subject_name = (TextView)findViewById(R.id.subject_name);
        TextView file_name = (TextView)findViewById(R.id.file_name);
        TextView txt_done_receipt = (TextView)findViewById(R.id.txt_done_receipt);

        Typeface typeface = Typeface.createFromAsset(getAssets(),"NanumBarunGothic.ttf");
        Typeface typefacel = Typeface.createFromAsset(getAssets(),"NanumBarunGothicBold.ttf");

        txt_title_name.setTypeface(typeface);
        subject_name.setTypeface(typeface);
        file_name.setTypeface(typeface);
        txt_done_receipt.setTypeface(typefacel);


        final ImageButton btn_back = (ImageButton)findViewById(R.id.btnBackSign);

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_back.setImageResource(R.drawable.btnback_click);
                Intent i = new Intent(ReceiptCheck3Activity.this, ReceiptCheck1Activity.class);
                finish();
                startActivity(i);
            }
        });




        final ImageButton btn_check = (ImageButton)findViewById(R.id.btn_check);

        btn_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_check.setImageResource(R.drawable.button_done_clicked);
                Intent i = new Intent(ReceiptCheck3Activity.this, ReceiptActivity.class);
                net.execute();
                finish();
                startActivity(i);

            }
        });







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

                String link = "http://52.68.141.174/php/Receiptconfirm.php?id=";
                link += id;


                //String link = "http://52.68.141.174/php/test.php";

                URL url = new URL(link);
                url.openStream();

            } catch (Exception e) {
                return new String("Exception:" + e.getMessage());
            }




            return null;
        }

        protected void onPostExecute(String str) {



        }


    }
}
