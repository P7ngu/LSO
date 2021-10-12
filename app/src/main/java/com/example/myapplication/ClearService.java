package com.example.myapplication;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.example.myapplication.Client;
import com.example.myapplication.CurrentUser;

public class ClearService extends Service {

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("ClearService", "Service Started");
        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
       // Client.inviaRichiestaLogout(CurrentUser.getUsername());
        super.onDestroy();
        Log.d("ClearService", "Service Destroyed");
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        Log.e("ClearService", "END");
        //Code here
        Client.inviaRichiestaLogout(CurrentUser.getUsername());
        stopSelf();
    }
}