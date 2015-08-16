package com.example.kimteaho.copy;

import android.content.Context;
import android.content.Intent;
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


    public CustomAdapter(){
        m_List_name = new ArrayList<String>();
        m_List_ttcode = new ArrayList<String>();
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

            imgBtn_lecture_add.setOnClickListener(new View.OnClickListener() {
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

    public void add(String subn, String ttcode){
        m_List_name.add(subn);
        m_List_ttcode.add(ttcode);
    }


    public void remove(int position)
    {
        m_List_name.remove(position);

    }
}
