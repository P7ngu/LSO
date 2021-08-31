package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class WaitingActivity extends AppCompatActivity {
    static Button tempoResiduo;
    static Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext=this;
        setContentView(R.layout.activity_waiting);

        tempoResiduo=findViewById(R.id.button_temporesiduo_waitingactivity);

    }

    public static void setTimeLeft(int timeLeft){
            tempoResiduo.setText(timeLeft+ "");
            if(timeLeft==0)
                mContext.startActivity(new Intent(mContext, MakeABetActivity.class));

    }
}