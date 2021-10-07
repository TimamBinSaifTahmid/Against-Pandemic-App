package com.example.against_pandemic;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class HistoryAdapter extends BaseAdapter {
    String[] provider;
    String[] date;
    Context context;
    private LayoutInflater layoutInflater;

    public HistoryAdapter(String[] provider, String[] date, Context context) {
        this.provider = provider;
        this.date = date;
        this.context = context;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view==null){

            layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.help_history,viewGroup, false);

        }

        TextView providerInfo = view.findViewById(R.id.providerHistory);
        TextView helpDate = view.findViewById(R.id.helpDate);

        providerInfo.setText(provider[i]);
        helpDate.setText(date[i]);

        return view;
    }
}
