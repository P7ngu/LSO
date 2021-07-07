package com.example.myapplication;

import android.util.Log;

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
                if(userLogged==1 && flag==0) {
                    MakeABetActivity.startWaitingActivity();
                    flag=1;
                }
            }

            else if(currentTime <=0){ //Il timer è a 0
                flag=0;
                startTime=45;
                currentTime=45-7;
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
            this.setCurrentTime(currentTime);
            Log.d("Debug timer", currentTime+"/n");
            try{
                WaitingActivity.setTimeLeft(currentTime);
            } catch (Exception e){

            }

            try {
                Thread thread = new Thread();
                thread.sleep(1000);

            } catch (Exception e){

            }

        } while(true);
    }
}
