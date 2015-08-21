package com.example.kimteaho.copy;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by user on 8/10/15.
 */
public class CustomAdapter extends BaseAdapter {

    private ArrayList<String> m_List_name;
    private ArrayList<String> m_List_ttcode;
    private ArrayList<String> m_List_profn;
    private ArrayList<String> m_List_time;


    public CustomAdapter(){
        m_List_name = new ArrayList<String>();
        m_List_ttcode = new ArrayList<String>();
        m_List_time = new ArrayList<String>();
        m_List_profn = new ArrayList<String>();
    }

    @Override
    public int getCount(){
        return m_List_name.size()+1;

    }

    @Override
    public Object getItem(int position) {
        return m_List_name.get(position);
    }

    @Override
    public long getItemId(int position){
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final int pos = position;
        final Context context = parent.getContext();

        if( convertView == null && (pos != (this.getCount()-1)) ){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            convertView = inflater.inflate(R.layout.list_lecture_item,parent,false);

            TextView txt_lecname = (TextView)convertView.findViewById(R.id.txt_lecture_name);
            txt_lecname.setText(m_List_name.get(position));

            TextView txt_profn = (TextView)convertView.findViewById(R.id.txt_lecture_profn);
            txt_profn.setText(m_List_profn.get(position));

            TextView txt_time = (TextView)convertView.findViewById(R.id.txt_lecture_classtime);
            txt_time.setText(m_List_time.get(position));

            Typeface typeface = Typeface.createFromAsset(context.getAssets(), "NanumBarunGothic.ttf");

            txt_lecname.setTypeface(typeface);
            txt_profn.setTypeface(typeface);
            txt_time.setTypeface(typeface);


            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context,"click" + m_List_name.get(pos),Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(context,FileViewActivity.class);
                    i.putExtra("subn",m_List_name.get(pos));
                    i.putExtra("tt",m_List_ttcode.get(pos));
                    context.startActivity(i);
                }
            });
        }
        else if( convertView == null && ( pos == (this.getCount()-1)))
        {
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            convertView =  inflater.inflate(R.layout.list_lecture_addbtn,parent,false);

            TextView txt_lec_add = (TextView)convertView.findViewById(R.id.txt_lecture_addbtn);
            txt_lec_add.setText("수업을 추가해주세요.");
            ImageButton imgBtn_lecture_add = (ImageButton)convertView.findViewById(R.id.imgbtn_list_addbtn);

            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent i = new Intent(context,AddLectureActivity.class);
                    //i.putExtra("subn",m_List_name.get(pos));
                    //i.putExtra("tt",m_List_ttcode.get(pos));
                    context.startActivity(i);
                    Toast.makeText(context,"click",Toast.LENGTH_SHORT).show();
                }
            });


        }
        return convertView;
    }

    public void add(String subn, String ttcode,String pron,String time){
        m_List_name.add(subn);
        m_List_ttcode.add(ttcode);
        m_List_profn.add(pron);
        m_List_time.add(time);

    }


    public void remove(int position)
    {
        m_List_name.remove(position);

    }
}
