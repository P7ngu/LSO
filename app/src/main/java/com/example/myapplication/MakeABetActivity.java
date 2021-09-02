package com.example.myapplication;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;

import java.util.ArrayList;

public class MakeABetActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Spinner importoSpinner, numeroSpinner;
    Button sendBetButton;
    String numeroPuntato, importoScommesso;
    static Context mContext;
    static Button latestNumber;
    ArrayList<String> betSelezionate = new ArrayList<>();
    ArrayList<CheckBox> checkBoxesCliccate = new ArrayList<>();

    public void onCheckboxClicked(View view) {
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();

        // Check which checkbox was clicked
        switch(view.getId()) {
            case R.id.cb0:
                if (checked) betSelezionate.add("0");
                // Put some meat on the sandwich
                else betSelezionate.remove("0");

                break;
            case R.id.cb1:
                if (checked) betSelezionate.add("1");
                else betSelezionate.remove("1");
                // Cheese me

                break;

        }

    }



    public static void startWaitingActivity() {
        try {
            mContext.startActivity(new Intent(mContext, WaitingActivity.class));
        } catch (Exception e){

        }
    }


    public void onItemSelected(AdapterView<?> parent, View view,
                                   int pos, long id) {



        }

        public void onNothingSelected(AdapterView<?> parent) {
            // Another interface callback
        }

public void rimuoviChecks(CheckBox checkDaLasciare){
    for(int i=0; i<checkBoxesCliccate.size(); i++)
        if(!checkBoxesCliccate.get(i).equals(checkDaLasciare)) checkBoxesCliccate.get(i).setChecked(false);
    checkBoxesCliccate.clear();

    checkBoxesCliccate.add(checkDaLasciare);
}

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_a_bet);
        mContext=this;

        CheckBox cRed = findViewById(R.id.cb_rosso);
        cRed.setBackgroundColor(Color.RED);
        //cRed.getButtonTintList(Color.RED);

        CheckBox cc1 = findViewById(R.id.cb_1colonna);
        cc1.setBackgroundColor(Color.rgb(0,100,0));

        CheckBox cc2 = findViewById(R.id.cb_2colonna);
        cc2.setBackgroundColor(Color.rgb(0,100,0));

        CheckBox cc3 = findViewById(R.id.cb_3colonna);
        cc3.setBackgroundColor(Color.rgb(0,100,0));

        CheckBox c0 = findViewById(R.id.cb0);
        c0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = ((CheckBox) c0).isChecked();
                if (checked) {
                    rimuoviChecks(c0);
                    betSelezionate.add("0");
                }
                else betSelezionate.remove("0");
            }
        });
        c0.setBackgroundColor(Color.rgb(0,100,0));

        CheckBox c1 = findViewById(R.id.cb1);
        c1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = ((CheckBox) c1).isChecked();
                if (checked) {
                    rimuoviChecks(c1);
                    betSelezionate.add("1");
                }
                else betSelezionate.remove("1");

            }
        });
        c1.setBackgroundColor(Color.RED);

        CheckBox c3 = findViewById(R.id.cb3);
        c3.setBackgroundColor(Color.RED);

        CheckBox c5 = findViewById(R.id.cb5);
        c5.setBackgroundColor(Color.RED);

        CheckBox c7 = findViewById(R.id.cb7);
        c7.setBackgroundColor(Color.RED);

        CheckBox c9 = findViewById(R.id.cb9);
        c9.setBackgroundColor(Color.RED);

        CheckBox c12 = findViewById(R.id.cb12);
        c12.setBackgroundColor(Color.RED);

        CheckBox c14 = findViewById(R.id.cb14);
        c14.setBackgroundColor(Color.RED);

        CheckBox c16 = findViewById(R.id.cb16);
        c16.setBackgroundColor(Color.RED);

        CheckBox c18 = findViewById(R.id.cb18);
        c18.setBackgroundColor(Color.RED);

        CheckBox c19 = findViewById(R.id.cb19);
        c19.setBackgroundColor(Color.RED);

        CheckBox c21 = findViewById(R.id.cb21);
        c21.setBackgroundColor(Color.RED);

        CheckBox c23 = findViewById(R.id.cb23);
        c23.setBackgroundColor(Color.RED);

        CheckBox c25 = findViewById(R.id.cb25);
        c25.setBackgroundColor(Color.RED);

        CheckBox c27 = findViewById(R.id.cb27);
        c27.setBackgroundColor(Color.RED);

        CheckBox c30 = findViewById(R.id.cb30);
        c30.setBackgroundColor(Color.RED);

        CheckBox c32 = findViewById(R.id.cb32);
        c32.setBackgroundColor(Color.RED);

        CheckBox c34 = findViewById(R.id.cb34);
        c34.setBackgroundColor(Color.RED);

        CheckBox c36 = findViewById(R.id.cb36);
        c36.setBackgroundColor(Color.RED);





        sendBetButton=findViewById(R.id.button_sendbet);
        sendBetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Da riaggiornare
                //for (int i = 0; i < betSelezionate.size(); i++){
                  //  Client.inviaScommessa((betSelezionate.get(i)), importoScommesso);
                //Log.d("Debug 2.0", "Scommessa inviata" + betSelezionate.get(i));
            //}
                Client.inviaScommessa(numeroPuntato, importoScommesso);
                CurrentUser.setNumeroBettato(numeroPuntato);
                CurrentUser.setImportoScommesso(importoScommesso);
                startActivity(new Intent(mContext, WaitingActivity.class));
            }
        });




        String numeroEstratto = Client.getLatestNumber()+"";
            Button latestNumber = findViewById(R.id.button2_latestnumber);
            latestNumber.setText(numeroEstratto + "");






    }


    public static void updateLatestNumber(String nuovoNumero){
    if(latestNumber!=null) latestNumber.setText(nuovoNumero);
    }
}