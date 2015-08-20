package com.example.kimteaho.copy;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.app.Activity;
import android.widget.ImageButton;
import android.widget.TextView;


public class PrintOption1 extends Activity {


    boolean selected_content = false;
    boolean selected_setting = false;
    boolean selected_next = false;
    String filen;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_printoption1);

        Intent intent = getIntent();
        filen = intent.getExtras().getString("filen");

        TextView txt_prtop1_title = (TextView)findViewById(R.id.txt_prtop1_title);
        txt_prtop1_title.setText(filen);

        TextView txt_content_check = (TextView)findViewById(R.id.txt_check_content);
        TextView txt_print_setting = (TextView)findViewById(R.id.txt_setting_print);


        Typeface typeface = Typeface.createFromAsset(getAssets(),"NanumBarunGothicBold.ttf");
        //Typeface typefacel = Typeface.createFromAsset(getAssets(),"NanumBarunGothicLight.ttf");

        txt_content_check.setTypeface(typeface);
        txt_print_setting.setTypeface(typeface);




        final ImageButton btn_back = (ImageButton)findViewById(R.id.backprint);

        btn_back.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                btn_back.setImageResource(R.drawable.btnback_click);
                finish();

            }

        });


        final ImageButton check_content = (ImageButton)findViewById(R.id.check_content);
        final ImageButton setting_print = (ImageButton)findViewById(R.id.setting_print);


        check_content.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

                if (selected_content) {
                    check_content.setImageResource(R.drawable.page_check_deteil_unchecked);
                    selected_content = false;
                    setting_print.setEnabled(true);

                } else {
                    check_content.setImageResource(R.drawable.page_check_deteil_checked);
                    selected_content = true;
                    setting_print.setEnabled(false);
                }
            }
        });




            setting_print.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View view){

                    if (selected_setting) {
                        setting_print.setImageResource(R.drawable.page_settings_unchecked);
                        selected_setting = false;
                        check_content.setEnabled(true);

                    } else {
                        setting_print.setImageResource(R.drawable.page_settings_checked);
                        selected_setting = true;
                        check_content.setEnabled(false);




                    }
                }
                });




        final ImageButton btn_next = (ImageButton)findViewById(R.id.btn_next);

        btn_next.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                if (selected_setting) {


                    btn_next.setImageResource(R.drawable.page_next_checked);
                    Intent i = new Intent(PrintOption1.this, PrintOption2.class);
                    finish();
                    startActivity(i);


                }
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


}
