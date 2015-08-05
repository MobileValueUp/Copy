package com.example.kimteaho.copy;

import android.os.Bundle;
import android.support.v4.app.Fragment;
//import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Kimhangyeol on 15. 7. 19..
 */
public class FragmentFour extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_page4, container, false);
//레이아웃 인플레이트는 공식처럼 외우면 된다고 합니다!
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onViewCreated(view, savedInstanceState);
        TextView tv = (TextView)view.findViewById(R.id.section_label4);
        tv.setText("Fragment Four");
//이런식으로 프래그먼트를 3개 만듭니다. 탭이 4개면 4개~
    }
}

