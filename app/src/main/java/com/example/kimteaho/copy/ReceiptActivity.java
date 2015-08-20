package com.example.kimteaho.copy;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;


public class ReceiptActivity extends Activity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receipt);


        TextView txt_title = (TextView)findViewById(R.id.txt_title_name);
        TextView subject_name1  = (TextView)findViewById(R.id.subject_name1);
        TextView file_name1 = (TextView)findViewById(R.id.file_name1);
        TextView due_time1 = (TextView)findViewById(R.id.txt_due_time1);
        TextView subject_name2  = (TextView)findViewById(R.id.subject_name2);
        TextView file_name2 = (TextView)findViewById(R.id.file_name2);
        TextView due_time2 = (TextView)findViewById(R.id.txt_due_time2);


        Typeface typeface = Typeface.createFromAsset(getAssets(),"NanumBarunGothic.ttf");
        Typeface typefacel=  Typeface.createFromAsset(getAssets(), "NanumBarunGothicLight.ttf");

        txt_title.setTypeface(typeface);
        subject_name1.setTypeface(typeface);
        file_name1.setTypeface(typefacel);
        due_time1.setTypeface(typeface);
        subject_name2.setTypeface(typeface);
        file_name2.setTypeface(typefacel);
        due_time2.setTypeface(typeface);


        final ImageButton btn_next1 = (ImageButton)findViewById(R.id.next1);

        btn_next1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                Intent i = new Intent(ReceiptActivity.this, ReceiptCheck1Activity.class);
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
}
