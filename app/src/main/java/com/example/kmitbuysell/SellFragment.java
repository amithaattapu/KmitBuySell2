package com.example.kmitbuysell;


import android.content.Context;
import android.os.Bundle;
//import android.support.v4.app.Fragment;
//import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.ListFragment;

/**
 * A simple {@link Fragment} subclass.
 */



/**
 * A simple {@link Fragment} subclass.
 */
public class SellFragment extends ListFragment {


    public SellFragment() {
        // Required empty public constructor
    }

    static interface Myinter
    {
        public void onClick(int id);
    }
    Myinter listener;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ArrayAdapter<SellingThings> aa=new ArrayAdapter<SellingThings>(inflater.getContext(),
                android.R.layout.simple_list_item_1
                ,SellingThings.sthings);
        setListAdapter(aa);
        return super.onCreateView(inflater,container,savedInstanceState);
    }
    @Override
    public void onAttach(Context ctx)
    {
        super.onAttach(ctx);
        listener=(Myinter) ctx;
    }
    @Override
    public void onListItemClick(ListView lv,
                                View v,
                                int pos,
                                long id)
    {
        listener.onClick((int) id);
    }
}
