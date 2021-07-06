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
                int numeroEstratto = Client.extractLatestNumber();
                Log.d("Debug numero estratto", numeroEstratto+"numero<");
                CurrentUser.setLastNumber(numeroEstratto+"");
                try {
                    if (CurrentUser.getNumeroBettato().equals(numeroEstratto + "")) {
                        int prevMoney = Integer.parseInt(CurrentUser.getMoneyCount());
                        int moneyWon = Integer.parseInt(CurrentUser.getImportoScommesso()) * 30;
                        CurrentUser.setMoneyCount(prevMoney + moneyWon + "");
                        //Notifica
                    }
                }catch (Exception e){

                }
                //Aggiorniamo e notifichiamo l'utente
            }

            Log.d("Debug timer", currentTime+"/n");

        }
    }
}
