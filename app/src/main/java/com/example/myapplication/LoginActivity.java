package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {
    EditText nicknameET, pwET;
    Button registratiNowButton, loginSendButton;
    SharedPreferences prefs;
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
        prefs = mContext.getSharedPreferences("myPrefsKeys", Context.MODE_PRIVATE);

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
                if(Client.inviaRichiestaLogin(nicknameET.getText().toString(), pwET.getText().toString())) {
                    prefs = mContext.getSharedPreferences("myPrefsKeys", Context.MODE_PRIVATE);
                    startActivity(new Intent(mContext, HomeActivity.class));
                    CurrentUser.getInstance().setUsername(nicknameET.getText().toString());
                    CurrentUser.getInstance().setUserLoggedStatus(1);
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putString("nome", nicknameET.getText().toString());
                    editor.putString("password", pwET.getText().toString());
                    editor.apply();
                }
                else {
                    if(nicknameET.getText().length()==0 || pwET.getText().length()==0) PopupController.mostraPopup("Errore durante il login", "Compila tutti i campi!", mContext);
                    else PopupController.mostraPopup("Errore durante il login", "Dati non validi!", mContext);
                }
            }
        });
    }
}