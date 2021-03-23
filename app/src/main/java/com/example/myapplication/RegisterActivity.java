package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RegisterActivity extends AppCompatActivity {
    EditText nicknameET, pw1ET, pw2ET;
    Button registratiSendButton, lognowButton;
    Context mContext;

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
                if(checkCampiRegistrazione(nicknameET.getText().toString(), pw1ET.getText().toString(), pw2ET.getText().toString())){
                    if(Client.inviaRichiestaRegistrazione(nicknameET.getText().toString(), pw2ET.getText().toString())){
                        //Mostra popup
                        startActivity(new Intent(mContext, LoginActivity.class));
                    }
                }

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