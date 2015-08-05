package com.example.kimteaho.copy;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Kimteaho on 15. 7. 7..
 */
public class FragmentTwo extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_page2, container, false);
//레이아웃 인플레이트는 공식처럼 외우면 된다고 합니다!
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        //강의안

        super.onViewCreated(view, savedInstanceState);
        TextView tv = (TextView)view.findViewById(R.id.section_label2);
        tv.setText("Fragment two");
//이런식으로 프래그먼트를 3개 만듭니다. 탭이 4개면 4개~
    }
}

