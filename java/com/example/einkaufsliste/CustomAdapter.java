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
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class CustomAdapter extends BaseAdapter {
    Context context;
    List<Produkt> produktlist;
    List<Produkt> einkaufslist;

    LayoutInflater inflter;
    Button bestandAdd;
    Button bestandDel;

    public CustomAdapter(Context applicationContext, List<Produkt> produktlist, List<Produkt> einkaufslist) {
        this.context = context;
        this.produktlist = produktlist;
        this.einkaufslist= einkaufslist;
        inflter = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return produktlist.size();
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
        bestandDel=(Button) view.findViewById(R.id.button6);

        produkt.setText( produktlist.get(i).name);
        count.setText(produktlist.get(i).anzahl);


        bestandAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String value= (String) count.getText();
                int amount= Integer.parseInt(value);
                if(amount==0){
                    einkaufslist.remove(produktlist.get(i));
                }
                amount=amount+1;
                value= String.valueOf(amount);
                count.setText(value);
                produktlist.get(i).anzahl= String.valueOf(count.getText());
            }
        });

        bestandDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast empty = Toast.makeText(v.getContext(),"Produkt ist leer", Toast.LENGTH_SHORT);
                String value= (String) count.getText();
                int amount= Integer.parseInt(value);
                if(amount<=0){
                    empty.show();
                }else{
                    amount=amount-1;
                    if(amount==0){
                        empty.show();
                        einkaufslist.add(produktlist.get(i));
                    }
                }
                value= String.valueOf(amount);
                count.setText(value);
                produktlist.get(i).anzahl=String.valueOf(count.getText());
            }
        });
        return view;
    }

}
