package com.example.myapplication;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class Client {
    public static void gesticiOperazione(String messages) {
        switch (messages){
            case "Start":

                break;

            case "LoginEffettuato":

                break;

            case "RegisterEffettuata":

                break;
        }
    }

    public static boolean inviaRichiestaRegistrazione(String nome, String password) {
        PrintWriter pwrite = Connection.getPwrite();
        String blankspaces = " ";
        for(int i=nome.length(); i<10; i++){
            blankspaces=blankspaces+" ";
        }
        pwrite.println("register  "+nome+blankspaces+password);// sending to server
        Log.d("DEBUGGG", "register  "+nome+blankspaces+password);
        pwrite.flush();                    // flush the data
        //GetMessage getMessage = new GetMessage();
        //getMessage.run();
        //if(getMessage.getMessage().equals("register_success")) return true;
        //else
            return false;
    }

    public static boolean inviaRichiestaLogin(String nome, String password) {

        PrintWriter pwrite = Connection.getPwrite();
        String blankspaces = " ";
        for(int i=nome.length(); i<10; i++){
            blankspaces=blankspaces+" ";
        }
        pwrite.println("login     "+nome+blankspaces+password);// sending to server
        Log.d("DEBUGGG", "login inviato  "+nome+blankspaces+password);
        pwrite.flush();                    // flush the data

        String message = null;
        try {
            message = Connection.receiveMessageFromServer();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.d("DEBUGGG", message+"Messaggio ricevuto");
       return true;

    }

    public static void inviaScommessa(String numeroPuntato, String importoScommesso) {
        PrintWriter pwrite = Connection.getPwrite();
        String numeroDaMandare=null;
        String nomeUtente=CurrentUser.getUsername();
        String blankspaces = " ";
        for(int i=nomeUtente.length(); i<10; i++){
            blankspaces=blankspaces+" ";
        }
        if (numeroPuntato.length()==1) numeroDaMandare = numeroPuntato.concat(" ");
        else numeroDaMandare=numeroPuntato;
        Log.d("Debug puntata", numeroDaMandare+nomeUtente);
        pwrite.println("puntata   "+numeroDaMandare+nomeUtente+blankspaces+importoScommesso);// sending to server
        pwrite.flush();                    // flush the data

    }

    public static int getTimerLeft(){
        PrintWriter pwrite = Connection.getPwrite();
        pwrite.println("timeleft");// sending to server
        pwrite.flush();                    // flush the data

        String message = null;
        try {
            message = Connection.receiveMessageFromServer();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Integer timeLeft = new Integer(message);
        return timeLeft;


    }

    public static int getLatestNumber() {
        PrintWriter pwrite = Connection.getPwrite();
        pwrite.println("estrazione");// sending to server
        pwrite.flush();                    // flush the data

        String message = null;
        try {
            Thread.sleep(5000);
            message = Connection.receiveMessageFromServer();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Integer number=0;
        if(!message.equals("register_success") && !message.equals("login_success") && !message.equals("login_fail")
                && message!=null && !message.equals("") && !message.equals(" "))
            number = new Integer(message);
        return number;

    }
}
