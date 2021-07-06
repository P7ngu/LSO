package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class MakeABetActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Spinner importoSpinner, numeroSpinner;
    Button sendBetButton;
    String numeroPuntato, importoScommesso;
    Context mContext=this;
    static Button latestNumber;

    public static void updateLatestNumber(Integer number) {
       try {
           if(latestNumber!=null) latestNumber.setText(number + "");
       } catch (Exception e){
        e.printStackTrace();
       }
    }


    public void onItemSelected(AdapterView<?> parent, View view,
                                   int pos, long id) {
            // An item was selected. You can retrieve the selected item using
            // parent.getItemAtPosition(pos)
            Spinner spinner = (Spinner) findViewById(R.id.numero_spinner);
            Log.d("Debuggg", (String) parent.getItemAtPosition(pos));
            Spinner spin = (Spinner)parent;
            Spinner spin2 = (Spinner)parent;

            if(spin.getId() == R.id.importo_spinner)
            {
               importoScommesso=parent.getItemAtPosition(pos)+"";
            }
            if(spin2.getId() == R.id.numero_spinner)
            {
                numeroPuntato=parent.getItemAtPosition(pos)+"";
            }


        }

        public void onNothingSelected(AdapterView<?> parent) {
            // Another interface callback
        }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_a_bet);


            numeroSpinner = (Spinner) findViewById(R.id.numero_spinner);
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                    R.array.numbers_array, android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            numeroSpinner.setAdapter(adapter);

        numeroSpinner.setOnItemSelectedListener(this);

        importoSpinner = (Spinner) findViewById(R.id.importo_spinner);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,
                R.array.importi_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        importoSpinner.setAdapter(adapter1);

        importoSpinner.setOnItemSelectedListener(this);

        sendBetButton=findViewById(R.id.button_sendbet);
        sendBetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Client.inviaScommessa(numeroPuntato, importoScommesso);
                CurrentUser.setNumeroBettato(numeroPuntato);
                CurrentUser.setImportoScommesso(importoScommesso);
                startActivity(new Intent(mContext, WaitingActivity.class));
            }
        });

        int numeroEstratto = Client.getLatestNumber();
        Log.d("Debug numero estratto", numeroEstratto+"numero<");
        CurrentUser.setLastNumber(numeroEstratto+"");
       Button latestNumber=findViewById(R.id.button2_latestnumber);
        latestNumber.setText(numeroEstratto+"");




    }
}