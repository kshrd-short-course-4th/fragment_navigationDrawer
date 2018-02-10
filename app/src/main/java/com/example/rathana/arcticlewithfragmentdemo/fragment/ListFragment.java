package com.example.rathana.arcticlewithfragmentdemo.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.rathana.arcticlewithfragmentdemo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by RATHANA on 2/10/2018.
 */

public class ListFragment extends Fragment{

    //TextView textView;
    ListView listView;
    private String info;
    private ListFragmentListener mListener;
    private List<String> list=new ArrayList<>();
    public  void setInfo(String info){
        this.info=info;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.list_fragament_layout,container,false);
        //textView=view.findViewById(R.id.textView);
        listView=view.findViewById(R.id.list);
        mListener=(ListFragmentListener) getActivity();

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //bind data to layout
       /* textView.setText(info);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onClickListener(info);
            }
        });*/

       for(int i=0 ;i<50;i++){
           list.add("item "+i);
       }
        ArrayAdapter adapter=new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1,list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mListener.onItemClick(list.get(position));
            }
        });
    }

    private static ListFragment INSTANCE;
    public static ListFragment getInstance(String tag){
        if(INSTANCE ==null)
            INSTANCE=new ListFragment();

        Bundle b=new Bundle();
        b.putString("TAG",tag);
        INSTANCE.setArguments(b);
        return INSTANCE;
    }

    public interface ListFragmentListener{
        void onClickListener(String s);
        void onItemClick(String s);
    }

}
