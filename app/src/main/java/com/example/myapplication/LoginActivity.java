package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {
    EditText nicknameET, pwET;
    Button registratiNowButton, loginSendButton;
    Context mContext;

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
                if(Client.inviaRichiestaLogin(nicknameET.getText().toString(), pwET.getText().toString())) {
                    startActivity(new Intent(mContext, HomeActivity.class));
                    CurrentUser.getInstance().setUsername(nicknameET.getText().toString());
                }
                    else {

                        //TODO mostra messaggino
                }
            }
        });
    }
}