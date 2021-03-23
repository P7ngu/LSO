package com.example.myapplication;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class GetMessage extends Thread {
    String message=null;

    public String getMessage() {
        //if(message!=null) return message;
        //else {
          //  Log.d("DEBUGGG", "dentro getter message");
            //run();
            return message;
        //}
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void run()
        {
            BufferedReader in = Connection.getReceiveRead();
            try {
                //message = in.readLine();
            } catch (Exception e) {
                e.printStackTrace();
            }


        }
}
