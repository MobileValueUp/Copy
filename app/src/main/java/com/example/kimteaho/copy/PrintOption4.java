package com.example.kimteaho.copy;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.PopupWindow;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLEncoder;


public class PrintOption4 extends Activity {

    private PopupWindow pwindo;
    private ImageButton btnapply;
    private ImageButton btncancel;
    private ImageButton btncharge;
    private ImageButton btn_print;

    private Typeface typeface;
    private Typeface typefacel;

    String filen;
    String filecd;
    String subn;

    String uscd;
    UserInfoGlobal userInfoGlobal;


  /*  private Typeface typeface = Typeface.createFromAsset(getAssets(),"NanumBarunGothicBold.ttf");
    private Typeface typefacel = Typeface.createFromAsset(getAssets(),"NanumBarunGothic.ttf");
    */


    Net net;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_printoption4);

        userInfoGlobal = (UserInfoGlobal)getApplication();

        Intent intent = getIntent();
        filen = intent.getExtras().getString("filen");
        filecd = intent.getExtras().getString("filecode");
        subn = intent.getExtras().getString("subn");

        uscd = userInfoGlobal.getUsCd();
        net = new Net();

        //net.execute();
        final ImageButton btn_back = (ImageButton)findViewById(R.id.backprint);

        btn_back.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                btn_back.setImageResource(R.drawable.btnback_click);
                Intent i = new Intent(PrintOption4.this, PrintOption3.class);
                i.putExtra("filen",filen);
                i.putExtra("filecode",filecd);
                i.putExtra("subn",subn);
                finish();
                startActivity(i);

            }

        });



        TextView txt_circulation = (TextView)findViewById(R.id.txt_circulation);
        TextView txt_receipt_time = (TextView)findViewById(R.id.txt_receipt_time);
        TextView txt_notice = (TextView)findViewById(R.id.txt_notice);

        TextView txt_title = (TextView)findViewById(R.id.txt_prtop4_title);
        txt_title.setText(filen);


        Typeface.createFromAsset(getAssets(),"NanumBarunGothicBold.ttf");
        Typeface.createFromAsset(getAssets(),"NanumBarunGothic.ttf");

        txt_circulation.setTypeface(typeface);
        txt_receipt_time.setTypeface(typeface);
        txt_notice.setTypeface(typefacel);

        txt_title.setTypeface(typeface);



        btn_print = (ImageButton)findViewById(R.id.print);


        btn_print.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                btn_print.setImageResource(R.drawable.page_print_clicked);
                if( Integer.valueOf(userInfoGlobal.getPoint())< 10)
                    initiatePopupwindow2();
                else
                    initiatePopupwindow1();

            }

        });

    }

    private void initiatePopupwindow1() {

        try {

            LayoutInflater inflater = (LayoutInflater) PrintOption4.this
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View layout = inflater.inflate(R.layout.popup1, (ViewGroup) findViewById(R.id.popup_elemnet1));



            pwindo = new PopupWindow(layout, 500, 500, true);
            pwindo.showAtLocation(layout, Gravity.CENTER, 0, 0);


            btnapply = (ImageButton) layout.findViewById(R.id.apply);
            btncancel = (ImageButton) layout.findViewById(R.id.cancel);


            btncancel.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    btncancel.setImageResource(R.drawable.popup_cancel_clicked);
                    pwindo.dismiss();
                    btn_print.setImageResource(R.drawable.page_print_unclicked);

                }


            });

            btnapply.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    btnapply.setImageResource(R.drawable.popup_apply_print_clicked);

                    userInfoGlobal.setPoint(String.valueOf(Integer.valueOf(userInfoGlobal.getPoint()) - 10));
                    String link = "http://52.68.141.174/php/Receiptadd.php?usCd=";
                    link += userInfoGlobal.getUsCd();
                    link += "&filen=" +filen +"&subn=" + subn;



                    net.execute();

                    pwindo.dismiss();

                    Intent i = new Intent(PrintOption4.this,ReceiptActivity.class);
                    startActivity(i);
                    btnapply.setImageResource(R.drawable.popup_apply_print_unclicked);

                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initiatePopupwindow2() {


        try {

            LayoutInflater inflater = (LayoutInflater) PrintOption4.this
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View layout = inflater.inflate(R.layout.popup2, (ViewGroup) findViewById(R.id.popup_elemnet2));



            pwindo = new PopupWindow(layout, 500, 500, true);
            pwindo.showAtLocation(layout, Gravity.CENTER, 0, 0);


            btncharge = (ImageButton) layout.findViewById(R.id.point_charge);
            btncharge.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    btncharge.setImageResource(R.drawable.popup_add_point_clicked);
                    pwindo.dismiss();
                    Intent i = new Intent(PrintOption4.this, PointChargeActivity.class);
                    i.putExtra("subn",subn);
                    startActivity(i);


                }


            });

        } catch (Exception e) {
            e.printStackTrace();
        }
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


    public class Net extends AsyncTask<String,Void,String>
    {
        protected Net()
        {}

        protected void onPreExecute(){}

        protected String doInBackground(String... arg0)
        {
            try {

                String link = "http://52.68.141.174/php/Receiptadd.php?usCd=";
                link += userInfoGlobal.getUsCd();
                link += "&filen=" + URLEncoder.encode(filen,"UTF-8") +"&subn=" + URLEncoder.encode(subn,"UTF-8");



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

    /*public class Net extends AsyncTask<String,Void,String> {
        protected Net() {
        }

        protected void onPreExecute() {
        }

        protected String doInBackground(String... arg0) {
            try {

                String link = "http://52.68.141.174/php/Receiptadd.php?usCd=";
                link += userInfoGlobal.getUsCd();
                link += "&filen=" + URLEncoder.encode(filen,"UTF-8") +"&subn=" + URLEncoder.encode(subn,"UTF-8");

                Toast.makeText(getApplicationContext(),link,Toast.LENGTH_LONG).show();

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

                    }
                    eventType = xpp.next();
                }


            } catch (XmlPullParserException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }*/
}
