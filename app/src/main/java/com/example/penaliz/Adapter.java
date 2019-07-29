package com.example.penaliz;

import android.app.Activity;
import androidx.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;


    public class Adapter extends ArrayAdapter<Traffic> {
        private Activity context;
        private List<Traffic> finelist;


        public Adapter(Activity context, List<Traffic> finelist) {
            super(context, R.layout.listitem, finelist);
            this.context = context;
            this.finelist = finelist;


        }

        @NonNull
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = context.getLayoutInflater();
            View listitemview = inflater.inflate(R.layout.listitem, null, true);
            TextView regnpo = listitemview.findViewById(R.id.regno);
            TextView date=listitemview.findViewById(R.id.date);
            TextView amt=listitemview.findViewById(R.id.amount);

            Traffic traffic=finelist.get(position);
            regnpo.setText(traffic.getRegno());
            date.setText(traffic.getDate());
            amt.setText(traffic.getAmt());
            return listitemview;
        }
    }


