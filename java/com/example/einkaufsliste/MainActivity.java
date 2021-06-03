package com.example.einkaufsliste;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView simpleList;
    ListView einkaufsList;
    Button addNewProdukt;
    Button showEinkauf;
    Button done;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        simpleList = (ListView) findViewById(R.id.simpleListView);
        einkaufsList = (ListView) findViewById(R.id.einkaufslist);
        addNewProdukt= (Button) findViewById(R.id.button);
        showEinkauf= (Button) findViewById(R.id.button2);
        done= (Button) findViewById(R.id.done);


        List<Produkt> produktList = new ArrayList<>();
        List<Produkt> einkaufsliste= new ArrayList<>();

        CustomAdapter customAdapter = new CustomAdapter(getApplicationContext(), produktList,einkaufsliste);
        EinkaufsAdapter einkaufsAdapter = new EinkaufsAdapter(getApplicationContext(),einkaufsliste, produktList);

        simpleList.setAdapter(customAdapter);
        einkaufsList.setAdapter(einkaufsAdapter);

        addNewProdukt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddItemDialog(MainActivity.this,customAdapter,produktList);
            }
        });

        showEinkauf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                simpleList.setVisibility(View.INVISIBLE);
                einkaufsList.setVisibility(View.VISIBLE);
                addNewProdukt.setVisibility(View.INVISIBLE);
                done.setVisibility(View.VISIBLE);
                showEinkauf.setVisibility(View.INVISIBLE);

                for(int x=0;x<einkaufsliste.size()-1;x++){
                    if(einkaufsliste.size()==0){
                        break;
                    }
                    if(Integer.parseInt(einkaufsliste.get(x).anzahl)>0){
                        einkaufsliste.remove(einkaufsliste.get(x));
                        x=0;
                    }
                }
                einkaufsAdapter.notifyDataSetChanged();
            }
        });

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                simpleList.setVisibility(View.VISIBLE);
                einkaufsList.setVisibility(View.INVISIBLE);
                addNewProdukt.setVisibility(View.VISIBLE);
                done.setVisibility(View.INVISIBLE);
                showEinkauf.setVisibility(View.VISIBLE);

                int sizeOFeinkaufslist=einkaufsliste.size()-1;
                for(int x=0;x<=produktList.size()-1;x++){
                    for(int y=0;y<=sizeOFeinkaufslist;y++){
                        if(einkaufsliste.get(y).name==produktList.get(x).name){
                            produktList.get(x).anzahl=einkaufsliste.get(y).anzahl;
                            if(einkaufsliste.get(y).anzahl != String.valueOf(0)){
                                einkaufsliste.remove(einkaufsliste.get(y));
                                sizeOFeinkaufslist=einkaufsliste.size()-1;
                                einkaufsAdapter.notifyDataSetChanged();
                            }
                        }
                    }
                }
                customAdapter.notifyDataSetChanged();

            }
        });
    }

    private void showAddItemDialog(Context c, CustomAdapter adapter, List<Produkt> list) {
        final EditText taskEditText = new EditText(c);
        String task=null;
        AlertDialog dialog = new AlertDialog.Builder(c)
                .setTitle("Neues Produkt hinzufuegen")
                .setMessage("Produktnamen eingeben:")
                .setView(taskEditText)
                .setPositiveButton("Hinzufuegen", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        String produktname= String.valueOf(taskEditText.getText());
                        Produkt newProdukt= new Produkt(produktname,"1");

                        list.add(newProdukt);
                        adapter.notifyDataSetChanged();
                    }
                })
                .setNegativeButton("Cancel", null)
                .create();
        dialog.show();
    }

}