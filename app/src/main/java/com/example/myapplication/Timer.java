package com.example.myapplication;

import android.util.Log;

public class Timer {
    private int startTime;
    private int currentTime;

    public Timer(int startTime1){
        startTime=startTime1;
        currentTime=startTime;
        while(true){
            try {
                Thread thread = new Thread();
                thread.sleep(1000);

            } catch (Exception e){

            }
            if(currentTime!=0)
            currentTime=currentTime-1;
            else { //Il timer è a 0
                startTime=20;
                currentTime=20;
                //Chiediamo al server l'ultimo numero estratto
                try {
                    //Thread.sleep(2000);
                }catch (Exception e){

                }
                //Verifichiamo la puntata
                int numeroEstratto = Client.getLatestNumber();
                Log.d("Debug numero estratto", numeroEstratto+"numero<");
                //Aggiorniamo e notifichiamo l'utente
            }

            Log.d("Debug timer", currentTime+"/n");

        }
    }
}
