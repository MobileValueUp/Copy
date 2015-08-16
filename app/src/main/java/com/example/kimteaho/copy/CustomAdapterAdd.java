package com.example.kimteaho.copy;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by user on 8/10/15.
 */
public class CustomAdapterAdd extends BaseAdapter {

    private ArrayList<String> m_List_name;
    private ArrayList<String> m_List_subc;
    private ArrayList<String> m_List_time;
    private ArrayList<String> m_List_prof;
    private ArrayList<Boolean> m_List_isChecked;


    public CustomAdapterAdd(){
        m_List_name = new ArrayList<String>();
        m_List_subc = new ArrayList<String>();
        m_List_time = new ArrayList<String>();
        m_List_prof = new ArrayList<String>();
        m_List_isChecked = new ArrayList<Boolean>();
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

        if( convertView == null && (pos != (this.getCount()-1))   ){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            convertView = inflater.inflate(R.layout.list_addlecture_item,parent,false);

            TextView txt_subname = (TextView)convertView.findViewById(R.id.txt_addLecture_subn);
            txt_subname.setText(m_List_name.get(position));

            TextView txt_profn = (TextView)convertView.findViewById(R.id.txt_addLecture_prof);
            txt_profn.setText(m_List_prof.get(position));

            TextView txt_time = (TextView)convertView.findViewById(R.id.txt_addLecture_time);
            txt_time.setText(m_List_time.get(position));

            TextView txt_number = (TextView)convertView.findViewById(R.id.txt_addLecture_number);
            txt_number.setText(m_List_subc.get(position));

            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context,"click" + String.valueOf(pos),Toast.LENGTH_SHORT).show();
                }
            });

            final ImageButton imgbtn_check = (ImageButton)convertView.findViewById(R.id.imgbtn_addLecture_select);
            imgbtn_check.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(m_List_isChecked.get(pos))
                    {
                        m_List_isChecked.set(pos,Boolean.FALSE);
                        imgbtn_check.setImageResource(R.drawable.uncheck_signup);
                    }
                    else
                    {
                        m_List_isChecked.set(pos,Boolean.TRUE);
                        imgbtn_check.setImageResource(R.drawable.check_signup);

                    }

                }
            });


        }
        else if( convertView == null && ( pos == (this.getCount()-1)))
        {
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            convertView =  inflater.inflate(R.layout.list_addlecture_addbtn,parent,false);


            ImageButton imgBtn_addlecture_add = (ImageButton)convertView.findViewById(R.id.imgbtn_addLecture_input);

            imgBtn_addlecture_add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent i = new Intent(context,AddLecture2Activity.class);
                    context.startActivity(i);
                }
            });


        }


        return convertView;
    }

    public void add(String subn, String subc, String profn, String time){
        m_List_name.add(subn);
        m_List_subc.add(subc);
        m_List_prof.add(profn);
        m_List_time.add(time);
        m_List_isChecked.add(Boolean.FALSE);
    }


    public void remove(int position)
    {
        m_List_name.remove(position);

    }

    public ArrayList getBool()
    {
        return m_List_isChecked;
    }

    public void clearAll()
    {
        m_List_name.clear();
        m_List_prof.clear();
        m_List_time.clear();
        m_List_subc.clear();
        m_List_isChecked.clear();
    }
}
