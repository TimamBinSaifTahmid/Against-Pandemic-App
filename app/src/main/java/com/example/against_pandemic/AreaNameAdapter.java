package com.example.against_pandemic;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class AreaNameAdapter extends BaseAdapter {

    String[] areaname;
    String[] areaNumber;
    Context context;
    private LayoutInflater layoutInflater;


    AreaNameAdapter(Context context, String[] areaname, String[] areanumber){
        this.context = context;
        this.areaname = areaname;
        this.areaNumber = areanumber;
    };
    @Override
    public int getCount() {
        return areaname.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {


        if(view==null){

            layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.help_area_item,viewGroup, false);

        }

        TextView areaView = view.findViewById(R.id.helpAreaItem);
        TextView affectedView = view.findViewById(R.id.affectedNumber);

        areaView.setText(areaname[i]);
        affectedView.setText(areaNumber[i]);

        return view;
    }

}
