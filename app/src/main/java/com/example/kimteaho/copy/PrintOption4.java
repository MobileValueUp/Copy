package com.example.kimteaho.copy;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.PopupWindow;


public class PrintOption4 extends Activity {

    private PopupWindow pwindo;
    private ImageButton btnapply;
    private ImageButton btncancel;
    private ImageButton btncharge;
    private ImageButton btn_print;

    private Typeface typeface;
    private Typeface typefacel;



  /*  private Typeface typeface = Typeface.createFromAsset(getAssets(),"NanumBarunGothicBold.ttf");
    private Typeface typefacel = Typeface.createFromAsset(getAssets(),"NanumBarunGothic.ttf");
    */




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_printoption4);

        final ImageButton btn_back = (ImageButton)findViewById(R.id.backprint);

        btn_back.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                btn_back.setImageResource(R.drawable.btnback_click);
                Intent i = new Intent(PrintOption4.this, PrintOption3.class);
                finish();
                startActivity(i);

            }

        });



        TextView txt_circulation = (TextView)findViewById(R.id.txt_circulation);
        TextView txt_receipt_time = (TextView)findViewById(R.id.txt_receipt_time);
        TextView txt_notice = (TextView)findViewById(R.id.txt_notice);

        Typeface.createFromAsset(getAssets(),"NanumBarunGothicBold.ttf");
        Typeface.createFromAsset(getAssets(),"NanumBarunGothic.ttf");

        txt_circulation.setTypeface(typeface);
        txt_receipt_time.setTypeface(typeface);
        txt_notice.setTypeface(typefacel);




        btn_print = (ImageButton)findViewById(R.id.print);


        btn_print.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                btn_print.setImageResource(R.drawable.page_print_clicked);
                initiatePopupwindow2();

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
                    finish();
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


}
