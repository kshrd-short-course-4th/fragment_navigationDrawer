package com.example.rathana.arcticlewithfragmentdemo.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.rathana.arcticlewithfragmentdemo.R;

/**
 * Created by RATHANA on 2/10/2018.
 */

public class DetailFragment extends Fragment {

    private TextView textView;
    private String detail;
    private static DetailFragment INSTANCE;
    public static DetailFragment getInstance(String tag){
        if(INSTANCE ==null)
            INSTANCE=new DetailFragment();

        Bundle b=new Bundle();
        b.putString("TAG",tag);
        INSTANCE.setArguments(b);
        return INSTANCE;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
       View view=inflater.inflate(R.layout.detail_fragament_layout,container,false);
        textView=view.findViewById(R.id.text1);
       return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if(detail!=null)
            textView.setText(detail);
    }

    public void setDetail(String detail){
        this.detail=detail;
    }
}
