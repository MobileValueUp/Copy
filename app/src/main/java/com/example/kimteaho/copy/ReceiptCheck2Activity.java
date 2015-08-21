package com.example.kimteaho.copy;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;


public class ReceiptCheck2Activity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receiptcheck2);

        TextView txt_title_name = (TextView)findViewById(R.id.txt_title_name);
        TextView subject_name1 = (TextView)findViewById(R.id.subject_name1);
        TextView file_name1 = (TextView)findViewById(R.id.file_name1);
        TextView print_info1 = (TextView)findViewById(R.id.print_info1);
        TextView txt_due_time1 = (TextView)findViewById(R.id.txt_due_time1);

        Typeface typeface = Typeface.createFromAsset(getAssets(),"NanumBarunGothic.ttf");

        txt_title_name.setTypeface(typeface);
        subject_name1.setTypeface(typeface);
        file_name1.setTypeface(typeface);
        print_info1.setTypeface(typeface);
        txt_due_time1.setTypeface(typeface);


        final ImageButton btn_back = (ImageButton)findViewById(R.id.btnBackSign);

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_back.setImageResource(R.drawable.btnback_click);
                Intent i = new Intent(ReceiptCheck2Activity.this, ReceiptCheck1Activity.class);
                finish();
                startActivity(i);
            }
        });






        final ImageButton btn_check = (ImageButton)findViewById(R.id.check);

        btn_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_check.setImageResource(R.drawable.button_confirm_clicked);

                EditText edtPwd = (EditText)findViewById(R.id.input_receipt_Pwd);
                Integer Rps = Integer.parseInt(edtPwd.getText().toString());

                if (Rps == 1004) {

                    Intent i = new Intent(ReceiptCheck2Activity.this, ReceiptCheck3Activity.class);
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
