package com.example.myapplication;

public class Utente {
    String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMoneyCount() {
        return moneyCount;
    }

    public void setMoneyCount(String moneyCount) {
        this.moneyCount = moneyCount;
    }

    String moneyCount;

    public Utente(String username, String moneyCount) {
        this.username = username;
        this.moneyCount = moneyCount;
    }
}
