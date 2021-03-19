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

    public static void inviaRichiestaRegistrazione(String nome, String password) {
        PrintWriter pwrite = Connection.getPwrite();
        String blankspaces = " ";
        for(int i=nome.length(); i<10; i++){
            blankspaces=blankspaces+" ";
        }
        pwrite.println("register  "+nome+blankspaces+password);// sending to server
        Log.d("DEBUGGG", "register  "+nome+blankspaces+password);
        pwrite.flush();                    // flush the data
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

        BufferedReader receiveRead = Connection.getReceiveRead();
        boolean flag=false;
        String message = null;
        while(message!=null){
            try {
                message = receiveRead.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Log.d("DEBUGGG ricevuto", message);
           if(message.equals("login_success")) {
              return true;
           } else if(message.equals(("login_fail"))) return false;
        }
        return false;

    }

    public static void inviaScommessa(String numeroPuntato, String importoScommesso) {
        PrintWriter pwrite = Connection.getPwrite();
        pwrite.println("puntata   "+numeroPuntato+" "+importoScommesso);// sending to server
        pwrite.flush();                    // flush the data

    }
}
