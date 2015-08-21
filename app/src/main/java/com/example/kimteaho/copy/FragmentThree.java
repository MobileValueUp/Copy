package com.example.kimteaho.copy;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

/**
 * Created by Kimteaho on 15. 7. 7..
 */
public class FragmentThree extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_page3, container, false);


//레이아웃 인플레이트는 공식처럼 외우면 된다고 합니다!
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        //친구



        super.onViewCreated(view, savedInstanceState);

        TextView txt_friend_list = (TextView)view.findViewById(R.id.friend_list);
        Typeface typeface = Typeface.createFromAsset(getActivity().getAssets(),"NanumBarunGothicBold.ttf");
        txt_friend_list.setTypeface(typeface);


        final ImageButton timetable = (ImageButton)view.findViewById(R.id.friend_timetable);

        timetable.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                timetable.setImageResource(R.drawable.timetable_friend_clicked);

            }

        });


        final ImageButton search = (ImageButton)view.findViewById(R.id.friend_search);

        search.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                search.setImageResource(R.drawable.search_friend_clicked);

            }

        });

        final ImageButton add = (ImageButton)view.findViewById(R.id.friend_add);

        add.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                add.setImageResource(R.drawable.add_friend_clicked);

            }

        });



//이런식으로 프래그먼트를 3개 만듭니다. 탭이 4개면 4개~
    }
}

