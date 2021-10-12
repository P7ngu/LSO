package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class RegisterActivity extends AppCompatActivity {
    EditText nicknameET, pw1ET, pw2ET;
    Button registratiSendButton, lognowButton;
    Context mContext;

    @Override
    public void onBackPressed() {
        Log.d("CDA", "onBackPressed Called");
        Intent setIntent = new Intent(mContext, LoginActivity.class);
        startActivity(setIntent);
        return;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext=this;
        setContentView(R.layout.activity_register);
        nicknameET=findViewById(R.id.editTextTextPersonName);
        pw1ET=findViewById(R.id.editTextTextPassword);
        pw2ET=findViewById(R.id.editTextTextPassword2);

        registratiSendButton=findViewById(R.id.button_registrati_registrati);

        registratiSendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pw1=pw1ET.getText().toString();
                String pw2=pw2ET.getText().toString();
                String nome=nicknameET.getText().toString();

                if(checkCampiRegistrazione(nicknameET.getText().toString(), pw1ET.getText().toString(), pw2ET.getText().toString())){
                        Boolean userAlreadyRegistered = false;
                        String users = Client.getListaUtenti();
                        String[] utentiArray = users.split(";;");
                        if(utentiArray!=null && utentiArray.length >=2)
                            for (int i = 0; i < utentiArray.length; i = i + 2) {
                            Log.d("7 settembre", "Nomi:-"+utentiArray[i]+","+nome);
                            if (utentiArray[i].compareTo(nome)==0) {
                                PopupController.mostraPopup("Errore server", "Credenziali già utilizzate", mContext);
                                userAlreadyRegistered = true;
                            }
                        }

                        if (userAlreadyRegistered==false) {
                            if(Client.inviaRichiestaRegistrazione(nicknameET.getText().toString(), pw2ET.getText().toString()))
                            startActivity(new Intent(mContext, LoginActivity.class));
                        }
                        else if (userAlreadyRegistered==true){
                            PopupController.mostraPopup("Errore server", "Credenziali già utilizzate", mContext);
                        }
                    }


                    if(pw1.length()>5 && pw2.length()>5 && !pw2.equals(pw1)) PopupController.mostraPopup("Errore", "Le password devono coincidere!", mContext);
                    else if (pw1.length()<5 || pw2.length()<5 || nome.length()<3) PopupController.mostraPopup("Compila tutti i campi!", "Devi compilare tutti i campi!", mContext);
                    else if( (pw2.length() < 6 || pw2.length() > 10) ) PopupController.mostraPopup("Errore password", "La password deve contenere più di 5 e meno di 10 caratteri", mContext);
                    else if(nome.length() < 3 && nome.length() > 10) PopupController.mostraPopup("Errore nickname", "Il nickname deve contenere almeno 3 e meno di 10 caratteri", mContext);


            }
        });

        lognowButton=findViewById(R.id.button_openlogin_registrati);
        lognowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mContext, LoginActivity.class));
            }
        });


    }

    private boolean checkCampiRegistrazione(String nome, String pw1, String pw2) {
        if( (pw2.equals(pw1)) && (pw2.length() > 5 && pw2.length() <= 10) && nome.length()>3 && nome.length()<=10 )
            return true;

        return false;
    }
}