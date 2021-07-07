package com.example.myapplication;

import java.util.ArrayList;

public class CurrentUser {
    private static int startTime;
    private static Timer timer;

    public static Timer getTimer() {
        return timer;
    }

    public static void setTimer(Timer timer) {
        CurrentUser.timer = timer;
    }

    public static int getStartTime() {
        return startTime;
    }

    public static void setStartTime(int startTime) {
        CurrentUser.startTime = startTime;
    }

    private static CurrentUser instance;
    private static String username;
    private static String userId;
    private static String moneyCount;
    private static String lastNumber;
    private static String numeroBettato;
    private static String importoScommesso;

    public static String getNumeroBettato() {
        return numeroBettato;
    }

    public static void setNumeroBettato(String numeroBettato) {
        CurrentUser.numeroBettato = numeroBettato;
    }

    public static String getImportoScommesso() {
        return importoScommesso;
    }

    public static void setImportoScommesso(String importoScommesso) {
        CurrentUser.importoScommesso = importoScommesso;
    }

    public static void setInstance(CurrentUser instance) {
        CurrentUser.instance = instance;
    }

    public static String getLastNumber() {
        return lastNumber;
    }

    public static void setLastNumber(String lastNumber) {
        CurrentUser.lastNumber = lastNumber;
        try{
            MakeABetActivity.updateLatestNumber(lastNumber);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public static String getMoneyCount() {
        return moneyCount;
    }

    public static void setMoneyCount(String moneyCount) {
        CurrentUser.moneyCount = moneyCount;
    }

    private static ArrayList<Utente> listaUtenti;

    public static CurrentUser getInstance() {
        if (instance == null)
            instance = new CurrentUser();
        return instance;

    }

    public CurrentUser(){}


    public static String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public static String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public static void setListaUtenti(ArrayList<Utente> userList) {
        CurrentUser.listaUtenti = userList;
    }

    public static ArrayList<Utente> getListaUtenti(){
        return CurrentUser.listaUtenti;
    }
}
