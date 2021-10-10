package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static com.example.myapplication.Client.contextApp;

public class LoginActivity extends AppCompatActivity {
    EditText nicknameET, pwET;
    Button registratiNowButton, loginSendButton;
    Context mContext;

    @Override
    public void onBackPressed() {
        Log.d("CDA", "onBackPressed Called");
        Intent setIntent = new Intent(mContext, RegisterActivity.class);
        startActivity(setIntent);
        return;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mContext=this;

        nicknameET=findViewById(R.id.editTextTextPersonName_nicknamelogin);
        pwET=findViewById(R.id.editTextTextPassword_password_login);

        registratiNowButton=findViewById(R.id.button_openregistrati_login);
        registratiNowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mContext, RegisterActivity.class));
            }
        });

        loginSendButton=findViewById(R.id.button_accedi_login);
        loginSendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (Client.inviaRichiestaLogin(nicknameET.getText().toString(), pwET.getText().toString())) {
                        startActivity(new Intent(mContext, HomeActivity.class));
                        CurrentUser.getInstance().setUsername(nicknameET.getText().toString());
                        CurrentUser.getInstance().setUserLoggedStatus(1);

                        //Carico lista e prelevo dati relativi al nick loggato
                        String users = Client.getListaUtenti();
                        String[] utentiArray = users.split(";;");
                        if (utentiArray != null && utentiArray.length >= 2) {
                            for (int i = 0; i < utentiArray.length; i = i + 2) {
                                if (utentiArray[i].compareTo(nicknameET.getText().toString()) == 0) {
                                    CurrentUser.getInstance().setMoneyCount(utentiArray[i + 1]);
                                }
                            }
                        }
                    } else {
                        if (nicknameET.getText().length() == 0 || pwET.getText().length() == 0)
                            PopupController.mostraPopup("Errore durante il login", "Compila tutti i campi!", mContext);
                        else
                            PopupController.mostraPopup("Errore durante il login", "Dati non validi!", mContext);
                    }
                } catch (Exception e) {
                    Context contextApp = GlobalApplication.getAppContext();
                    Toast.makeText(contextApp, "Errore di connessione. Riavvia l'app. ", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
