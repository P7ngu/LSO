package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

public class WaitingActivity extends AppCompatActivity {
    static Button tempoResiduo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waiting);

        tempoResiduo=findViewById(R.id.button_temporesiduo_waitingactivity);

    }

    public static void setTimeLeft(int timeLeft){
            tempoResiduo.setText(timeLeft+ "");
    }
}