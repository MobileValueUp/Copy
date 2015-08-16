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
public class CustomAdapterFile extends BaseAdapter {

    private ArrayList<String> m_List_name;
    private ArrayList<String> m_List_filecode;

    public CustomAdapterFile(){
        m_List_name = new ArrayList<String>();
        m_List_filecode = new ArrayList<String>();
    }

    @Override
    public int getCount(){
        return m_List_name.size();

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

        if( convertView == null  ){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            convertView = inflater.inflate(R.layout.list_file_item,parent,false);

            TextView txt_filename = (TextView)convertView.findViewById(R.id.txt_file_name);
            txt_filename.setText(m_List_name.get(position));


            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context,"click" + m_List_name.get(pos),Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(context,FileViewActivity.class);
                    i.putExtra("filen",m_List_name.get(pos));
                    i.putExtra("filecode",m_List_filecode.get(pos));
                    context.startActivity(i);
                }
            });
        }

        return convertView;
    }

    public void add(String msg, String filecode){
        m_List_name.add(msg);
        m_List_filecode.add(filecode);
    }


    public void remove(int position)
    {
        m_List_name.remove(position);

    }
}
