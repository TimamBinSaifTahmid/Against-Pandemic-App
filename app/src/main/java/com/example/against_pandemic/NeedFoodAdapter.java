package com.example.against_pandemic;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class NeedFoodAdapter extends BaseAdapter {

    String[] needfood;
    String[] needfoodnumber;
    Context context;
    private LayoutInflater layoutInflater;


    NeedFoodAdapter(Context context, String[] needfood, String[] needfoodnumber){
        this.context = context;
        this.needfood = needfood;
        this.needfoodnumber = needfoodnumber;
    };
    @Override
    public int getCount() {
        return needfood.length;
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

        areaView.setText(needfood[i]);
        affectedView.setText(needfoodnumber[i]);

        return view;
    }
}
