package com.example.kimteaho.copy;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by user on 8/10/15.
 */
public class CustomAdapterReciept extends BaseAdapter {

    private ArrayList<String> m_List_name;
    private ArrayList<String> arrayList_subn;
    private ArrayList<String> arrayList_ispaid;
    private ArrayList<String> arrayList_id;

    public CustomAdapterReciept(){
        m_List_name = new ArrayList<String>();
        arrayList_subn = new ArrayList<String>();
        arrayList_ispaid = new ArrayList<String>();
        arrayList_id = new ArrayList<String>();
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

        if( convertView == null && arrayList_ispaid.get(position).equals("0")  ){

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            convertView = inflater.inflate(R.layout.list_receipt_item,parent,false);

            TextView txt_subname = (TextView)convertView.findViewById(R.id.txt_file_name);
            txt_subname.setText(arrayList_subn.get(position));

            TextView txt_filename = (TextView)convertView.findViewById(R.id.txt_fileitem_subn);
            txt_filename.setText(m_List_name.get(position));

            /*TextView txt_uptime = (TextView)convertView.findViewById(R.id.txt_fileitem_upload);
            txt_uptime.setText(m_List_upTime.get(position));

            TextView txt_due = (TextView)convertView.findViewById(R.id.txt_fileitem_due);
            txt_due.setText(m_List_upTime.get(position));
*/
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context,"click" + m_List_name.get(pos),Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(context,ReceiptCheck1Activity.class);
                    i.putExtra("filen",m_List_name.get(pos));
                    i.putExtra("subn",arrayList_subn.get(pos));
                    i.putExtra("id",arrayList_id.get(pos));
                    context.startActivity(i);
                }
            });
        }

        return convertView;
    }

    public void add(String msg, String sn,String pay, String id){
        m_List_name.add(msg);
        arrayList_subn.add(sn);
        arrayList_ispaid.add(pay);
        arrayList_id.add(id);

    }


    public void remove(int position)
    {
        m_List_name.remove(position);

    }
}
