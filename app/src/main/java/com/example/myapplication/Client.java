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
            while(!message.equals("login_success") && !message.equals("login_fail"))
                message = Connection.receiveMessageFromServer();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.d("DEBUGGG", message+"Messaggio ricevuto");
       if(message.equals("login_success")) return true;
       else return false;

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

    public static String getUtentiAttivi(){
        PrintWriter pwrite = Connection.getPwrite();
        pwrite.println("online");// sending to server
        pwrite.flush();                    // flush the data

        String message = null;
        try {
            message = Connection.receiveMessageFromServer();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(!message.equals("register_success") && !message.equals("login_success") && !message.equals("login_fail")
                && message!=null && !message.equals("") && !message.equals(" ")
                && !message.equals("1") && !message.equals("2") && !message.equals("3") && !message.equals("4") && !message.equals("5")
                && !message.equals("6") && !message.equals("7") && !message.equals("8") && !message.equals("9") && !message.equals("10")
                && !message.equals("11") && !message.equals("12") && !message.equals("13") && !message.equals("14") && !message.equals("15")
                && !message.equals("16") && !message.equals("17") && !message.equals("18") && !message.equals("19") && !message.equals("20")
                && !message.equals("21") && !message.equals("22") && !message.equals("23") && !message.equals("24") && !message.equals("25")
                && !message.equals("26") && !message.equals("27") && !message.equals("28") && !message.equals("29") && !message.equals("30")
                && !message.equals("31") && !message.equals("32") && !message.equals("33") && !message.equals("34") && !message.equals("35") && !message.equals("36")
                && !message.equals(1) && !message.equals(2) && !message.equals(3) && !message.equals(4) && !message.equals(5)
                && !message.equals(6) && !message.equals(7) && !message.equals(8) && !message.equals(9) && !message.equals(10)
                && !message.equals(11) && !message.equals(12) && !message.equals(13) && !message.equals(14) && !message.equals(15)
                && !message.equals(16) && !message.equals(17) && !message.equals(18) && !message.equals(19) && !message.equals(20)
                && !message.equals(21) && !message.equals(22) && !message.equals(23) && !message.equals(24) && !message.equals(25)
                && !message.equals(26) && !message.equals(27) && !message.equals(28) && !message.equals(29) && !message.equals(30)
                && !message.equals(31) && !message.equals(32) && !message.equals(33) && !message.equals(34) && !message.equals(35) && !message.equals(36)
        ) {
            Log.d("Utenti Online", message + " end");
            return message;
        }
        return "not received";


    }

    public static int extractLatestNumber() {
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
            try{
                number = new Integer(message);
            } catch (Exception e){

            }
        CurrentUser.setLastNumber(message);
            MakeABetActivity.updateLatestNumber(message);

        return number;

    }

    public static int getLatestNumber() {
        PrintWriter pwrite = Connection.getPwrite();
        pwrite.println("latestnumber");// sending to server
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
            try{
                number = new Integer(message);
            } catch (Exception e){

            }
        return number;

    }
}
