package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import java.sql.Time;
import java.util.ArrayList;

public class Timer {
    private int startTime;

    public int getStartTime() {
        return startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public int getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(int currentTime) {
        this.currentTime = currentTime;
    }

    private int currentTime;

    public Timer(int startTime1){
        TimerAsyncTask t= new TimerAsyncTask(startTime1, this);
        t.execute();
    }



    class TimerAsyncTask extends AsyncTask<String, String, String> {
        int startTime1;
        Timer timer;

        public TimerAsyncTask(int startTime, Timer thisTimer) {
            timer=thisTimer;
            startTime1 = startTime;
            Log.d("11 ott", "chiamato asynctask dopo click su bet");
        }

        @Override
        protected String doInBackground(String... strings) {
            startTime=startTime1;
            int flag=0;

            currentTime=startTime;
            do{
                int userLogged=CurrentUser.getUserLoggedStatus();

                if(currentTime>=30) {
                    currentTime = currentTime - 1;

                }else if(currentTime>0 && currentTime<30){
                    currentTime = currentTime-1;
                    //Start waiting activity
                    userLogged=CurrentUser.getUserLoggedStatus();
                    if(userLogged==1 && flag==0) {
                        MakeABetActivity.startWaitingActivity();
                        flag=1;
                    }
                }

                else if(currentTime <=0){ //Il timer è a 0
                    flag=0;
                    startTime=45;
                    currentTime=45-7;
                    int numeroEstratto = -1;
                    //Chiediamo al server l'ultimo numero estratto
                    try {
                        //Thread.sleep(2000);
                        numeroEstratto = Client.extractLatestNumber();
                        Log.d("Debug numero estratto", numeroEstratto+"numero<");
                    }catch (Exception e){
                        e.printStackTrace();

                    }
                    //Verifichiamo la puntata


                    //CurrentUser.setLastNumber(numeroEstratto+"");
                    try {
                        if (numeroEstratto!=-1 && CurrentUser.getNumeroBettato()!=null && CurrentUser.getNumeroBettato().equals(numeroEstratto + "")) {
                            int prevMoney = Integer.parseInt(CurrentUser.getMoneyCount());
                            int moneyWon = Integer.parseInt(CurrentUser.getImportoScommesso()) * 30;
                            CurrentUser.setMoneyCount(prevMoney + moneyWon + "");
                            //Notifica
                        }

                    }catch (Exception e){
                        e.printStackTrace();

                    }
                    finally {
                        CurrentUser.setNumeroBettato("-1");
                    }
                    //Aggiorniamo e notifichiamo l'utente
                }
                timer.setCurrentTime(currentTime);
                Log.d("Debug timer", currentTime+"/n");
                try{
                    WaitingActivity.setTimeLeft(currentTime);
                } catch (Exception e){
                    e.printStackTrace();
                }

                try {
                    Thread thread = new Thread();
                    thread.sleep(1000);

                } catch (Exception e){
                    e.printStackTrace();
                }

            } while(true);
        }
    }


}


