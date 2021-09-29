package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.TextView;

import static com.example.myapplication.Client.getTimerLeft;
import static com.example.myapplication.CurrentUser.*;

public class MainActivity extends AppCompatActivity {
    static TextView testTextView;

    public static void setText_Testing(String message) {
        testTextView.setText(message);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (android.os.Build.VERSION.SDK_INT > 9)
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        Connection connection = new Connection(testTextView);

        CurrentUser.setLastNumber(Client.getLatestNumber()+"");

        //connection.execute();


    }

    @Override
    protected void onStart() {
        super.onStart();
        testTextView = findViewById(R.id.testing);
        Connection connection = new Connection(testTextView);
        connection.execute();
        startActivity(new Intent(this, RegisterActivity.class));


        //CurrentUser.setTimer(new Timer(CurrentUser.getStartTime()));
    }
}