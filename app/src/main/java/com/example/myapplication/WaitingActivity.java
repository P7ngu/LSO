package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

public class WaitingActivity extends AppCompatActivity {
    static Button tempoResiduo;
    static Context mContext;
    static boolean flagWin;

    @Override
    public void onBackPressed() {
        Log.d("CDA", "onBackPressed Called");
        Intent setIntent = new Intent(mContext, HomeActivity.class);
        startActivity(setIntent);
        return;
    }

    public static void showWinMessage() {
        flagWin=true;

    }

    public static void showLostMessage() {
        flagWin=false;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext=this;
        Log.d("11 ott", "waiting oncreate");
        setContentView(R.layout.activity_waiting);

        tempoResiduo=findViewById(R.id.button_temporesiduo_waitingactivity);

        tempoResiduo.setText("Clicca per visualizzare");

    }

    public static void setTimeLeft(int timeLeft){
            tempoResiduo.setText(timeLeft+ "");
            if(timeLeft==0) {
                try{Thread.sleep(500);} catch (Exception e){}
               if(CurrentUser.getUserLoggedStatus()==1) mContext.startActivity(new Intent(mContext, MakeABetActivity.class));
                if(!CurrentUser.getNumeroBettato().equals("-1")) {
                    if (flagWin && CurrentUser.getUserLoggedStatus()==1) MakeABetActivity.showWinMessage();
                    else if (CurrentUser.getUserLoggedStatus()==1) MakeABetActivity.showLostMessage();
                }

                flagWin=false;
                Log.d("11 ott", "set time left ended");
            }

    }
}