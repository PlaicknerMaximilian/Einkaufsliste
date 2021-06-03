package com.example.einkaufsliste;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class EinkaufsAdapter extends BaseAdapter {
    Context context;
    List<Produkt> einkaufslist;
    List<Produkt> produktList;

    LayoutInflater inflter;
    Button bestandAdd;

    public EinkaufsAdapter(Context applicationContext, List<Produkt> einkaufslist, List<Produkt> produktList) {
        this.context = context;
        this.einkaufslist= einkaufslist;
        this.produktList= produktList;
        inflter = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return einkaufslist.size();
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
        view = inflter.inflate(R.layout.activity_listview, null);
        TextView produkt = (TextView) view.findViewById(R.id.textView);
        TextView count= (TextView)view.findViewById(R.id.textView2);

        bestandAdd=(Button) view.findViewById(R.id.button7);

        produkt.setText( einkaufslist.get(i).name);
        count.setText(einkaufslist.get(i).anzahl);

        bestandAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String value= (String) count.getText();
                int amount= Integer.parseInt(value);
                amount=amount+1;
                value= String.valueOf(amount);
                count.setText(value);
                einkaufslist.get(i).anzahl= String.valueOf(count.getText());
                produktList.get(i).anzahl= String.valueOf(count.getText());
            }
        });
        return view;
    }

}
