package com.example.kimteaho.copy;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.support.v4.app.Fragment;
import android.widget.Toast;


/**
 * Created by Kimteaho on 15. 7. 7..
 */
public class FragmentOne extends Fragment {
    ImageView button1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_page1, container, false);
//레이아웃 인플레이트는 공식처럼 외우면 된다고 합니다!
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        //시간표


        super.onViewCreated(view, savedInstanceState);
        button1 = (ImageView)view.findViewById(R.id.page1_button);
        TextView tv = (TextView)view.findViewById(R.id.section_label1);
        tv.setText("Fragment One");

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), LectureActivity.class);
               // intent.putExtra("myid", myid); //값 전달
              //  intent.putExtra("price", price);
                startActivityForResult(intent, 1); // 갑 받는방법
            }
        });



//이런식으로 프래그먼트를 3개 만듭니다. 탭이 4개면 4개~
    }
}

