package com.example.myapplication;

import java.util.ArrayList;

public class CurrentUser {
    private static CurrentUser instance;
    private static String username;
    private static String userId;
    private static String moneyCount;
    private static String lastNumber;

    public static void setInstance(CurrentUser instance) {
        CurrentUser.instance = instance;
    }

    public static String getLastNumber() {
        return lastNumber;
    }

    public static void setLastNumber(String lastNumber) {
        CurrentUser.lastNumber = lastNumber;
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
