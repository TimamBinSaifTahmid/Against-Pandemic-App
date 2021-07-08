package com.example.against_pandemic;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class NeedMoneyAdapter extends BaseAdapter {

    String[] needmoney;
    String[] needmoneynumber;
    Context context;
    private LayoutInflater layoutInflater;


    NeedMoneyAdapter(Context context, String[] needmoney, String[] needmoneynumber){
        this.context = context;
        this.needmoney = needmoney;
        this.needmoneynumber = needmoneynumber;
    };
    @Override
    public int getCount() {
        return needmoney.length;
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

        areaView.setText(needmoney[i]);
        affectedView.setText(needmoneynumber[i]);

        return view;
    }
}
