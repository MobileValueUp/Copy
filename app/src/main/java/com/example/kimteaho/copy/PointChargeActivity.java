package com.example.kimteaho.copy;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.app.Activity;

import org.w3c.dom.Text;


public class PointChargeActivity extends Activity {

    boolean selected_card = false;
    boolean selected_deposit = false;
    boolean selected_kpay = false;
    boolean selected_transfer = false;
    boolean selected_pprice1 = false;
    boolean selected_pprice2 = false;
    boolean selected_pprice3 = false;
    boolean selected_pprice4 = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pointcharge);


        TextView txt_title = (TextView)findViewById(R.id.txt_title_name);
        TextView txt_state = (TextView)findViewById(R.id.txt_point_remain);
        TextView txt_charge = (TextView)findViewById(R.id.txt_point_charge);
        TextView total_price_title = (TextView)findViewById(R.id.total_price_title);
        TextView payment_method_title = (TextView)findViewById(R.id.payment_method_title);



        Typeface typeface = Typeface.createFromAsset(getAssets(),"NanumBarunGothic.ttf");
        Typeface typefacel = Typeface.createFromAsset(getAssets(),"NanumBarunGothicLight.ttf");

        txt_title.setTypeface(typeface);
        txt_state.setTypeface(typefacel);
        txt_charge.setTypeface(typefacel);
        total_price_title.setTypeface(typeface);
        payment_method_title.setTypeface(typefacel);



        final ImageButton btn_back = (ImageButton)findViewById(R.id.btnBackSign);

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_back.setImageResource(R.drawable.btnback_click);
                finish();
            }
        });


        final ImageButton pprice1 = (ImageButton)findViewById(R.id.point_price1);
        final ImageButton pprice2 = (ImageButton)findViewById(R.id.point_price2);
        final ImageButton pprice3 = (ImageButton)findViewById(R.id.point_price3);
        final ImageButton pprice4 = (ImageButton)findViewById(R.id.point_price4);

        pprice1.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                TextView total_price = (TextView)findViewById(R.id.total_price);

                if (selected_pprice1) {
                    pprice1.setImageResource(R.drawable.addpoint1_unclicked);
                    selected_pprice1 = false;
                    pprice2.setEnabled(true);
                    pprice3.setEnabled(true);
                    pprice4.setEnabled(true);
                    total_price.setText("0 원");



                } else {
                    pprice1.setImageResource(R.drawable.addpoint1_clicked);
                    selected_pprice1 = true;
                    pprice2.setEnabled(false);
                    pprice3.setEnabled(false);
                    pprice4.setEnabled(false);
                    total_price.setText("10000 원");


                }

            }

        });



        pprice2.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                TextView total_price = (TextView)findViewById(R.id.total_price);

                if (selected_pprice2) {
                    pprice2.setImageResource(R.drawable.addpoint2_unclicked);
                    selected_pprice2 = false;
                    pprice1.setEnabled(true);
                    pprice3.setEnabled(true);
                    pprice4.setEnabled(true);
                    total_price.setText("0 원");



                } else {
                    pprice2.setImageResource(R.drawable.addpoint2_clicked);
                    selected_pprice2 = true;
                    pprice1.setEnabled(false);
                    pprice3.setEnabled(false);
                    pprice4.setEnabled(false);
                    total_price.setText("20000 원");


                }

            }

        });




        pprice3.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                TextView total_price = (TextView)findViewById(R.id.total_price);

                if (selected_pprice3) {
                    pprice3.setImageResource(R.drawable.addpoint3_unclicked);
                    selected_pprice3 = false;
                    pprice1.setEnabled(true);
                    pprice2.setEnabled(true);
                    pprice4.setEnabled(true);
                    total_price.setText("0 원");



                } else {
                    pprice3.setImageResource(R.drawable.addpoint3_clicked);
                    selected_pprice3 = true;
                    pprice1.setEnabled(false);
                    pprice2.setEnabled(false);
                    pprice4.setEnabled(false);
                    total_price.setText("50000 원");


                }

            }

        });



        pprice4.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                TextView total_price = (TextView)findViewById(R.id.total_price);

                if (selected_pprice4) {
                    pprice4.setImageResource(R.drawable.addpoint4_unclicked);
                    selected_pprice4 = false;
                    pprice1.setEnabled(true);
                    pprice2.setEnabled(true);
                    pprice3.setEnabled(true);
                    total_price.setText("0 원");



                } else {
                    pprice4.setImageResource(R.drawable.addpoint4_clicked);
                    selected_pprice4 = true;
                    pprice1.setEnabled(false);
                    pprice2.setEnabled(false);
                    pprice3.setEnabled(false);
                    total_price.setText("100000 원");


                }

            }

        });












        final ImageButton btn_card  = (ImageButton)findViewById(R.id.payment_card);
        final ImageButton btn_deposit  = (ImageButton)findViewById(R.id.payment_deposit);
        final ImageButton btn_kpay  = (ImageButton)findViewById(R.id.payment_kpay);
        final ImageButton btn_trasfer  = (ImageButton)findViewById(R.id.payment_transfer);


        btn_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (selected_card) {
                    btn_card.setImageResource(R.drawable.btncard_unclicked);
                    selected_card = false;
                    btn_deposit.setEnabled(true);
                    btn_kpay.setEnabled(true);
                    btn_trasfer.setEnabled(true);

                } else {
                    btn_card.setImageResource(R.drawable.btncard_clicked);
                    selected_card = true;
                    btn_deposit.setEnabled(false);
                    btn_kpay.setEnabled(false);
                    btn_trasfer.setEnabled(false);
                 }
            }
        });



        btn_deposit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (selected_deposit) {
                    btn_deposit.setImageResource(R.drawable.btndeposit_unclicked);
                    selected_deposit = false;
                    btn_card.setEnabled(true);
                    btn_kpay.setEnabled(true);
                    btn_trasfer.setEnabled(true);

                } else {
                    btn_deposit.setImageResource(R.drawable.btndeposit_clicked);
                    selected_deposit = true;
                    btn_card.setEnabled(false);
                    btn_kpay.setEnabled(false);
                    btn_trasfer.setEnabled(false);
                }
            }
        });




        btn_kpay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (selected_kpay) {
                    btn_kpay.setImageResource(R.drawable.btnkpay_unclicked);
                    selected_kpay = false;
                    btn_card.setEnabled(true);
                    btn_deposit.setEnabled(true);
                    btn_trasfer.setEnabled(true);


                } else {
                    btn_kpay.setImageResource(R.drawable.btnkpay_clicked);
                    selected_kpay = true;
                    btn_card.setEnabled(false);
                    btn_deposit.setEnabled(false);
                    btn_trasfer.setEnabled(false);
                }
            }
        });






        btn_trasfer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (selected_transfer) {
                    btn_trasfer.setImageResource(R.drawable.btntransfer_unclicked);
                    selected_transfer = false;
                    btn_card.setEnabled(true);
                    btn_deposit.setEnabled(true);
                    btn_kpay.setEnabled(true);

                } else {
                    btn_trasfer.setImageResource(R.drawable.btntransfer_clicked);
                    selected_transfer = true;
                    btn_card.setEnabled(false);
                    btn_deposit.setEnabled(false);
                    btn_kpay.setEnabled(false);
                }
            }
        });


        final ImageButton btn_paycheck = (ImageButton)findViewById(R.id.paymentcheck);

        btn_paycheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }

        });

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

}
