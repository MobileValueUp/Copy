package com.example.kimteaho.copy;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;


public class PrintOption3 extends Activity {

    boolean selected_page2_1 = false;
    boolean selected_page4_1 = false;
    boolean selected_page4_2 = false;
    boolean selected_page4_3 = false;
    boolean selected_page4_4 = false;
    boolean selected_page6_1 = false;
    boolean selected_page6_2 = false;
    boolean selected_page6_3 = false;
    boolean selected_page6_4 = false;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_printoption3);



        final ImageButton btn_back = (ImageButton)findViewById(R.id.backprint);

        btn_back.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                btn_back.setImageResource(R.drawable.btnback_click);
                Intent i = new Intent(PrintOption3.this, PrintOption2.class);
                finish();
                startActivity(i);

            }

        });



        TextView txt_page2 = (TextView)findViewById(R.id.txt_page2);
        TextView txt_page4 = (TextView)findViewById(R.id.txt_page4);
        TextView txt_page6 = (TextView)findViewById(R.id.txt_page6);


        Typeface typeface = Typeface.createFromAsset(getAssets(),"NanumBarunGothicBold.ttf");

        txt_page2.setTypeface(typeface);
        txt_page4.setTypeface(typeface);
        txt_page6.setTypeface(typeface);



        final ImageButton page2_1 = (ImageButton)findViewById(R.id.page2_1);
        final ImageButton page4_1 = (ImageButton)findViewById(R.id.page4_1);
        final ImageButton page4_2 = (ImageButton)findViewById(R.id.page4_2);
        final ImageButton page4_3 = (ImageButton)findViewById(R.id.page4_3);
        final ImageButton page4_4 = (ImageButton)findViewById(R.id.page4_4);
        final ImageButton page6_1 = (ImageButton)findViewById(R.id.page6_1);
        final ImageButton page6_2 = (ImageButton)findViewById(R.id.page6_2);
        final ImageButton page6_3 = (ImageButton)findViewById(R.id.page6_3);
        final ImageButton page6_4 = (ImageButton)findViewById(R.id.page6_4);


        page2_1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                if (selected_page2_1) {
                    page2_1.setImageResource(R.drawable.setting_height_2_1_unchecked);
                    selected_page2_1 = false;
                    page4_1.setEnabled(true);
                    page4_2.setEnabled(true);
                    page4_3.setEnabled(true);
                    page4_4.setEnabled(true);
                    page6_1.setEnabled(true);
                    page6_2.setEnabled(true);
                    page6_3.setEnabled(true);
                    page6_4.setEnabled(true);


                } else {
                    page2_1.setImageResource(R.drawable.setting_height_2_1_checked);
                    selected_page2_1 = true;
                    page4_1.setEnabled(false);
                    page4_2.setEnabled(false);
                    page4_3.setEnabled(false);
                    page4_4.setEnabled(false);
                    page6_1.setEnabled(false);
                    page6_2.setEnabled(false);
                    page6_3.setEnabled(false);
                    page6_4.setEnabled(false);

                }
            }
        });




        page4_1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                if (selected_page4_1) {
                    page4_1.setImageResource(R.drawable.setting_height_4_1_unchecked);
                    selected_page4_1 = false;
                    page2_1.setEnabled(true);
                    page4_2.setEnabled(true);
                    page4_3.setEnabled(true);
                    page4_4.setEnabled(true);
                    page6_1.setEnabled(true);
                    page6_2.setEnabled(true);
                    page6_3.setEnabled(true);
                    page6_4.setEnabled(true);



                } else {
                    page4_1.setImageResource(R.drawable.setting_height_4_1_checked);
                    selected_page4_1 = true;
                    page2_1.setEnabled(false);
                    page4_2.setEnabled(false);
                    page4_3.setEnabled(false);
                    page4_4.setEnabled(false);
                    page6_1.setEnabled(false);
                    page6_2.setEnabled(false);
                    page6_3.setEnabled(false);
                    page6_4.setEnabled(false);

                }
            }
        });


        page4_2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                if (selected_page4_2) {
                    page4_2.setImageResource(R.drawable.setting_height_4_2_unchecked);
                    selected_page4_2 = false;
                    page2_1.setEnabled(true);
                    page4_1.setEnabled(true);
                    page4_3.setEnabled(true);
                    page4_4.setEnabled(true);
                    page6_1.setEnabled(true);
                    page6_2.setEnabled(true);
                    page6_3.setEnabled(true);
                    page6_4.setEnabled(true);


                } else {
                    page4_2.setImageResource(R.drawable.setting_height_4_2_checked);
                    selected_page4_2 = true;
                    page2_1.setEnabled(false);
                    page4_1.setEnabled(false);
                    page4_3.setEnabled(false);
                    page4_4.setEnabled(false);
                    page6_1.setEnabled(false);
                    page6_2.setEnabled(false);
                    page6_3.setEnabled(false);
                    page6_4.setEnabled(false);


                }
            }
        });


        page4_3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                if (selected_page4_3) {
                    page4_3.setImageResource(R.drawable.setting_height_4_3_unchecked);
                    selected_page4_3 = false;
                    page2_1.setEnabled(true);
                    page4_1.setEnabled(true);
                    page4_2.setEnabled(true);
                    page4_4.setEnabled(true);
                    page6_1.setEnabled(true);
                    page6_2.setEnabled(true);
                    page6_3.setEnabled(true);
                    page6_4.setEnabled(true);



                } else {
                    page4_3.setImageResource(R.drawable.setting_height_4_3_checked);
                    selected_page4_3 = true;
                    page2_1.setEnabled(false);
                    page4_1.setEnabled(false);
                    page4_2.setEnabled(false);
                    page4_4.setEnabled(false);
                    page6_1.setEnabled(false);
                    page6_2.setEnabled(false);
                    page6_3.setEnabled(false);
                    page6_4.setEnabled(false);

                }
            }
        });


        page4_4.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                if (selected_page4_4) {
                    page4_4.setImageResource(R.drawable.setting_height_4_4_unchecked);
                    selected_page4_4 = false;
                    page2_1.setEnabled(true);
                    page4_1.setEnabled(true);
                    page4_2.setEnabled(true);
                    page4_3.setEnabled(true);
                    page6_1.setEnabled(true);
                    page6_2.setEnabled(true);
                    page6_3.setEnabled(true);
                    page6_4.setEnabled(true);



                } else {
                    page4_4.setImageResource(R.drawable.setting_height_4_4_checked);
                    selected_page4_4 = true;
                    page2_1.setEnabled(false);
                    page4_1.setEnabled(false);
                    page4_2.setEnabled(false);
                    page4_3.setEnabled(false);
                    page6_1.setEnabled(false);
                    page6_2.setEnabled(false);
                    page6_3.setEnabled(false);
                    page6_4.setEnabled(false);

                }
            }
        });




        page6_1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                if (selected_page6_1) {
                    page6_1.setImageResource(R.drawable.setting_height_6_1_unchecked);
                    selected_page6_1 = false;
                    page2_1.setEnabled(true);
                    page4_1.setEnabled(true);
                    page4_2.setEnabled(true);
                    page4_3.setEnabled(true);
                    page4_4.setEnabled(true);
                    page6_2.setEnabled(true);
                    page6_3.setEnabled(true);
                    page6_4.setEnabled(true);


                } else {
                    page6_1.setImageResource(R.drawable.setting_height_6_1_checked);
                    selected_page6_1 = true;
                    page2_1.setEnabled(false);
                    page4_1.setEnabled(false);
                    page4_2.setEnabled(false);
                    page4_3.setEnabled(false);
                    page4_4.setEnabled(false);
                    page6_2.setEnabled(false);
                    page6_3.setEnabled(false);
                    page6_4.setEnabled(false);

                }
            }
        });


        page6_2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                if (selected_page6_2) {
                    page6_2.setImageResource(R.drawable.setting_height_6_2_unchecked);
                    selected_page6_2 = false;
                    page2_1.setEnabled(true);
                    page4_1.setEnabled(true);
                    page4_2.setEnabled(true);
                    page4_3.setEnabled(true);
                    page4_4.setEnabled(true);
                    page6_1.setEnabled(true);
                    page6_3.setEnabled(true);
                    page6_4.setEnabled(true);


                } else {
                    page6_2.setImageResource(R.drawable.setting_height_6_2_checked);
                    selected_page6_2 = true;
                    page2_1.setEnabled(false);
                    page4_1.setEnabled(false);
                    page4_2.setEnabled(false);
                    page4_3.setEnabled(false);
                    page4_4.setEnabled(false);
                    page6_1.setEnabled(false);
                    page6_3.setEnabled(false);
                    page6_4.setEnabled(false);

                }
            }
        });


        page6_3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                if (selected_page6_3) {
                    page6_3.setImageResource(R.drawable.setting_height_6_3_unchecked);
                    selected_page6_3 = false;
                    page2_1.setEnabled(true);
                    page4_1.setEnabled(true);
                    page4_2.setEnabled(true);
                    page4_3.setEnabled(true);
                    page4_4.setEnabled(true);
                    page6_1.setEnabled(true);
                    page6_2.setEnabled(true);
                    page6_4.setEnabled(true);


                } else {
                    page6_3.setImageResource(R.drawable.setting_height_6_3_checked);
                    selected_page6_3 = true;
                    page2_1.setEnabled(false);
                    page4_1.setEnabled(false);
                    page4_2.setEnabled(false);
                    page4_3.setEnabled(false);
                    page4_4.setEnabled(false);
                    page6_1.setEnabled(false);
                    page6_2.setEnabled(false);
                    page6_4.setEnabled(false);

                }
            }
        });


        page6_4.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                if (selected_page6_4) {
                    page6_4.setImageResource(R.drawable.setting_height_6_4_unchecked);
                    selected_page6_4 = false;
                    page2_1.setEnabled(true);
                    page4_1.setEnabled(true);
                    page4_2.setEnabled(true);
                    page4_3.setEnabled(true);
                    page4_4.setEnabled(true);
                    page6_1.setEnabled(true);
                    page6_2.setEnabled(true);
                    page6_3.setEnabled(true);



                } else {
                    page6_4.setImageResource(R.drawable.setting_height_6_4_checked);
                    selected_page6_4 = true;
                    page2_1.setEnabled(false);
                    page4_1.setEnabled(false);
                    page4_2.setEnabled(false);
                    page4_3.setEnabled(false);
                    page4_4.setEnabled(false);
                    page6_1.setEnabled(false);
                    page6_2.setEnabled(false);
                    page6_3.setEnabled(false);

                }
            }
        });


        final ImageButton btn_next = (ImageButton)findViewById(R.id.btn_next);

        btn_next.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                if (selected_page2_1 || selected_page4_1 || selected_page4_2 || selected_page4_3
                        || selected_page4_4 || selected_page6_1 || selected_page6_2 || selected_page6_3 || selected_page6_4) {


                    btn_next.setImageResource(R.drawable.page_next_checked);
                    Intent i = new Intent(PrintOption3.this, PrintOption4.class);
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
