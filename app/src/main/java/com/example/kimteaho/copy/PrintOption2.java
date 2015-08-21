package com.example.kimteaho.copy;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;


public class PrintOption2 extends Activity {

    boolean selected_color = false;
    boolean selected_uncolor = false;
    boolean selected_width = false;
    boolean selected_height = false;
    String filen;
    String filecd;
    String subn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_printoption2);

        Intent intent = getIntent();

        filen = intent.getExtras().getString("filen");
        filecd = intent.getExtras().getString("filecode");
        subn = intent.getExtras().getString("subn");


        TextView txt_prtop2 = (TextView)findViewById(R.id.txt_prtop2_title);
        txt_prtop2.setText(filen);
        Typeface typeface = Typeface.createFromAsset(getAssets(),"NanumBarunGothicBold.ttf");
        txt_prtop2.setTypeface(typeface);

        final ImageButton btn_back = (ImageButton)findViewById(R.id.backprint);

        btn_back.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                btn_back.setImageResource(R.drawable.btnback_click);
                Intent i = new Intent(PrintOption2.this, PrintOption1.class);
                i.putExtra("filen",filen);
                i.putExtra("filecode",filecd);
                i.putExtra("subn",subn);
                finish();
                startActivity(i);

            }

        });


        TextView txt_print_color = (TextView)findViewById(R.id.txt_color_print);
        TextView txt_print_uncolor = (TextView)findViewById(R.id.txt_uncolor_print);
        TextView txt_width = (TextView)findViewById(R.id.txt_width);
        TextView txt_height = (TextView)findViewById(R.id.txt_height);



        txt_print_color.setTypeface(typeface);
        txt_print_uncolor.setTypeface(typeface);
        txt_width.setTypeface(typeface);
        txt_height.setTypeface(typeface);


        final ImageButton color_print = (ImageButton)findViewById(R.id.color_print);
        final ImageButton uncolor_print = (ImageButton)findViewById(R.id.uncolor_print);



        color_print.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                if (selected_color) {
                    color_print.setImageResource(R.drawable.page_color_unchecked);
                    selected_color = false;
                    uncolor_print.setEnabled(true);

                } else {
                    color_print.setImageResource(R.drawable.page_color_checked);
                    selected_color = true;
                    uncolor_print.setEnabled(false);
                }
            }
        });


        uncolor_print.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                if (selected_uncolor) {
                    uncolor_print.setImageResource(R.drawable.page_uncolor_unchecked);
                    selected_uncolor = false;
                    color_print.setEnabled(true);

                } else {
                    uncolor_print.setImageResource(R.drawable.page_uncolor_checked);
                    selected_uncolor = true;
                    color_print.setEnabled(false);
                }
            }

        });

        final ImageButton width = (ImageButton)findViewById(R.id.width);
        final ImageButton height = (ImageButton)findViewById(R.id.height);

        width.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                if (selected_width) {
                    width.setImageResource(R.drawable.page_width_unchecked);
                    selected_width = false;
                    height.setEnabled(true);

                } else {
                    width.setImageResource(R.drawable.page_width_checked);
                    selected_width = true;
                    height.setEnabled(false);
                }
            }

        });


        height.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                if (selected_height) {
                    height.setImageResource(R.drawable.page_height_unchecked);
                    selected_height = false;
                    width.setEnabled(true);

                } else {
                    height.setImageResource(R.drawable.page_height_checked);
                    selected_height = true;
                    width.setEnabled(false);
                }
            }

        });

        final ImageButton btn_next = (ImageButton)findViewById(R.id.btn_next);

        btn_next.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                if ((selected_color || selected_uncolor) && (selected_width  || selected_height))  {

                    btn_next.setImageResource(R.drawable.page_next_checked);
                    Intent i = new Intent(PrintOption2.this, PrintOption3.class);
                    i.putExtra("filen",filen);
                    i.putExtra("subn",subn);
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
