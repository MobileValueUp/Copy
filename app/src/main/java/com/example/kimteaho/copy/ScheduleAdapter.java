package com.example.kimteaho.copy;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by user on 8/11/15.
 */


public class ScheduleAdapter extends BaseAdapter {

    Context mContext;
    int count = 60;
    String[] mWeekTitleIds ={
            "",
            "MON",
            "TUE",
            "WED",
            "THU",
            "FRI"

    };

    String[] timeColors={ "#b28850", "#69ac57", "#fc8785", "#c4dc6e", "#8e83a5", "#f59e20", "#99cfd5" };

    ArrayList<String> arrayList_subn;
    ArrayList<String> arrayList_day1;
    ArrayList<String> arrayList_time1;
    ArrayList<String> arrayList_day2;
    ArrayList<String> arrayList_time2;

    ArrayList<String> arrayList_pos1;
    ArrayList<String> arrayList_pos2;
    int in = 0;
    int in2=0;
    int inx=0;

    boolean isSub1= false;
    boolean isSub2 = false;


    ScheduleAdapter(Context context){

        mContext = context;

        arrayList_subn = new ArrayList<String>();
        arrayList_day1 = new ArrayList<String>();
        arrayList_time1 = new ArrayList<String>();
        arrayList_day2 = new ArrayList<String>();
        arrayList_time2 = new ArrayList<String>();

        arrayList_pos1 = new ArrayList<String>();
        arrayList_pos2 = new ArrayList<String>();


    }


    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return count;
    }

    @Override
    public Object getItem(int arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public long getItemId(int arg0) {
        // TODO Auto-generated method stub
        return arg0;
    }

    @Override
    public View getView(int position, View oldView, ViewGroup parent) {
        // TODO Auto-generated method stub
        View v=null;


        int pos = position;

        for(int k =0 ; k<arrayList_subn.size(); k++)
        {
            for (int j = 0; j < 6; j++) {

                if (mWeekTitleIds[j].equals(arrayList_day1.get(k)))
                {

                    int temp = 0;
                    temp +=j;
                    temp += 6 * Integer.valueOf(arrayList_time1.get(k));
                    arrayList_pos1.add(String.valueOf(temp)) ;

                }

                if ( mWeekTitleIds[j].equals(arrayList_day2.get(k)) )
                {

                    int temp = 0;
                    temp +=j;
                    temp += 6 * Integer.valueOf(arrayList_time2.get(k));
                    arrayList_pos2.add(String.valueOf(temp));

                }

            }
        }

        for(int k=0; k<arrayList_subn.size(); k++)
        {
            if( Integer.valueOf(arrayList_pos1.get(k)) == pos  )
            {
                isSub1=true;
                in = k;
            }

            if(Integer.valueOf(arrayList_pos2.get(k))  == pos)
            {
                isSub2=true;
                in2= k;
            }

        }


        if(oldView == null) {
            v = new TextView(mContext);
            //v.setLayoutParams(new GridView.LayoutParams(120, 100));
        }
        else if (position < 6) {
            v = new TextView(mContext);
            ((TextView)v).setGravity(Gravity.CENTER);
            ((TextView)v).setText(mWeekTitleIds[position]);
            v.setLayoutParams(new GridView.LayoutParams(120, 58));
            v.setBackgroundColor(Color.WHITE);

        }
        else {
            if (position >= 6 && position < count) {
                if (position % 6 == 0) {
                    v = new TextView(mContext);
                    ((TextView) v).setGravity(Gravity.CENTER);
                    ((TextView) v).setText(Integer.toString(position/6));
                    v.setLayoutParams(new GridView.LayoutParams(120, 118));
                    v.setBackgroundColor(Color.WHITE);

                }
                else if(isSub1)
                {
                    v = new TextView(mContext);
                    ((TextView) v).setGravity(Gravity.CENTER);
                    ((TextView) v).setText(arrayList_subn.get(in));
                    v.setLayoutParams(new GridView.LayoutParams(120, 118));
                    v.setBackgroundColor(Color.parseColor(timeColors[in]));


                    isSub1=false;
                    in = 0;
                }
                else if (isSub2)
                {
                    v = new TextView(mContext);
                    ((TextView) v).setGravity(Gravity.CENTER);
                    ((TextView) v).setText(arrayList_subn.get(in2));
                    v.setLayoutParams(new GridView.LayoutParams(120, 118));
                    v.setBackgroundColor(Color.parseColor(timeColors[in2]));
                    isSub2=false;
                    in2= 0;
                }
                /*else if ( position == Integer.valueOf(arrayList_pos1.get(in)))
                {
                    if(  (Integer.valueOf(arrayList_pos1.get(in)) - Integer.valueOf(arrayList_pos2.get(in)) ) == -6)
                    {
                        v = new TextView((mContext));
                        v.setLayoutParams(new GridView.LayoutParams(120, 118));
                        v.setBackgroundColor(100);
                        in++;
                    }
                    else
                    {
                        v = new TextView(mContext);
                        ((TextView) v).setGravity(Gravity.CENTER);
                        ((TextView) v).setText(arrayList_subn.get(in));
                        v.setLayoutParams(new GridView.LayoutParams(120, 118));
                        v.setBackgroundColor(Color.RED);
                        in++;
                        if( in == arrayList_subn.size())
                            in =0;
                    }
                }
                else if (position == Integer.valueOf(arrayList_pos2.get(in2)))
                {
                    if(  (Integer.valueOf(arrayList_pos2.get(in2)) - Integer.valueOf(arrayList_pos1.get(in2)) ) == 6)
                    {
                        v = new TextView((mContext));
                        ((TextView) v).setText(arrayList_subn.get(in2));
                        v.setLayoutParams(new GridView.LayoutParams(120, 118));
                        v.setBackgroundColor(100);
                        in2++;
                    }
                    else
                    {
                        v = new TextView(mContext);
                        ((TextView) v).setGravity(Gravity.CENTER);
                        ((TextView) v).setText(arrayList_subn.get(in2));
                        v.setLayoutParams(new GridView.LayoutParams(120, 118));
                        v.setBackgroundColor(Color.BLUE);
                        in2++;

                        if( in2 == arrayList_subn.size())
                            in2 = 0;
                    }
                }*/
                else {


                    v = new TextView(mContext);
                    ((TextView) v).setGravity(Gravity.CENTER);
                    v.setLayoutParams(new GridView.LayoutParams(120, 118));
                    v.setBackgroundColor(Color.WHITE);


                    /*switch (position) {
                        case 20:
                            v = new TextView(mContext);
                            ((TextView) v).setGravity(Gravity.CENTER);
                            ((TextView) v).setText("Economics");
                            v.setLayoutParams(new GridView.LayoutParams(120, 118));
                            v.setBackgroundColor(Color.RED);
                            break;

                        case 40:
                            v = new TextView(mContext);
                            ((TextView) v).setGravity(Gravity.CENTER);
                            ((TextView) v).setText("Business");
                            v.setLayoutParams(new GridView.LayoutParams(120, 118));
                            v.setBackgroundColor(Color.MAGENTA);
                            break;

                        case 50:
                            v = new TextView(mContext);
                            ((TextView) v).setGravity(Gravity.CENTER);
                            ((TextView) v).setText("Business");
                            v.setLayoutParams(new GridView.LayoutParams(120, 118));
                            v.setBackgroundColor(Color.TRANSPARENT);
                            break;


                        case 59:
                            v = new TextView(mContext);
                            ((TextView) v).setGravity(Gravity.CENTER);
                            ((TextView) v).setText("Mathmatics");
                            v.setLayoutParams(new GridView.LayoutParams(120, 118));
                            v.setBackgroundColor(Color.YELLOW);
                            break;

                        default:
                            v = new TextView(mContext);
                            ((TextView) v).setGravity(Gravity.CENTER);
                            v.setLayoutParams(new GridView.LayoutParams(120, 118));
                            v.setBackgroundColor(Color.WHITE);
                            break;
                    }*/

                }

            }
            else {
                v = oldView;
            }
        }



        return v;
    }

    public void add(String subn, String day1, String day2, String time1, String time2)
    {
        arrayList_subn.add(subn);
        arrayList_day1.add(day1);
        arrayList_day2.add(day2);
        arrayList_time1.add(time1);
        arrayList_time2.add(time2);
    }


}

