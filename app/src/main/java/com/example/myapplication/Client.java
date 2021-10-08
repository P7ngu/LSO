package com.example.myapplication;

import android.util.Log;

import java.io.IOException;
import java.io.PrintWriter;

public class Client {

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
        String message = null;
        try {
            message = Connection.receiveMessageFromServer();
        } catch (IOException e) {
            e.printStackTrace();
        }

            return true;
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
        Integer timeLeft=12345678;

        String message = null;
        try {
            message = Connection.receiveMessageFromServer();
            Log.d("15 settembre", "timerleft");
           if( (!message.endsWith(",") && !message.contains(";;") && !message.contains("*") && !message.contains("-")  ) )
               try{ timeLeft = new Integer(message);
               return timeLeft;
               } catch (Exception e){return getTimerLeft2();}
        } catch (IOException e) {
            e.printStackTrace();
            return getTimerLeft2();
        }
        return getTimerLeft2();
    }

    public static int getTimerLeft2(){
        Integer timeLeft=21298129;
        String message = null;
        try {
            message = Connection.receiveMessageFromServer();
            Log.d("15 settembre", "timerleft");
            if( (!message.endsWith(",") && !message.contains(";;") && !message.contains("*") && !message.contains("-")  ) )
                try{
                    timeLeft = new Integer(message);
                    return timeLeft;
                } catch (Exception e){
                    e.printStackTrace();
                    return getTimerLeft2();}
        } catch (IOException e) {
            e.printStackTrace();
            return getTimerLeft2();
        }
        return getTimerLeft2();
    }

    public static String getUtentiAttivi(){
        PrintWriter pwrite = Connection.getPwrite();
        pwrite.println("online");// sending to server
        pwrite.flush();                    // flush the data

        String message = null;
        try {
            message = Connection.receiveMessageFromServer();
            Log.d("15 settembre", "utenti attivi");
        } catch (IOException e) {
            e.printStackTrace();
        }
        if( !message.contains(";") && message.contains(",") ) {
            Log.d("Utenti Online", message + " end");
            return message;
        }
        return getUtentiAttivi2();
    }

    public static String getUtentiAttivi2(){
        String message = null;
        try {
            message = Connection.receiveMessageFromServer();
            Log.d("15 settembre", "utenti attivi2");
        } catch (IOException e) {
            e.printStackTrace();
        }
        if( !message.contains(";") && message.contains(",") ) {
            Log.d("Utenti Online", message + " end");
            return message;
        }
        return getUtentiAttivi2();
    }

    public static Integer extractLatestNumber() {
        Integer number = null;
        PrintWriter pwrite = Connection.getPwrite();
        pwrite.println("estrazione");// sending to server
        pwrite.flush();                    // flush the data

        String message = null;
        try {
            Thread.sleep(100);
            message = Connection.receiveMessageFromServer();
            if(message == null) extractLatestNumber2();
            Log.d("15 settembre", "estrazione");
        } catch (Exception e) {
            e.printStackTrace();
        }

        
        if(!message.equals("register_success") && !message.equals("login_success") && !message.equals("login_fail")
                && message!=null && !message.equals("") && !message.equals(" ")) {
            if(message.contains("**")) {
                Log.d("15 settembre", "Contiene**");
                //E' il numero estratto
                String numberString = message.replace("**", "");
                number = Integer.parseInt(numberString);

                if(CurrentUser.getInstance() != null && CurrentUser.getNumeroBettato()!=null && checkForWin(number)){
                    MakeABetActivity.showWinMessage();
                    Log.d("15 settembre", "Vittoria");
                } else {
                    if(CurrentUser.getNumeroBettato()!=null && !CurrentUser.getNumeroBettato().equals("-1") && !CurrentUser.getNumeroBettato().contains("-")
                    && !CurrentUser.getNumeroBettato().equals("null")) {
                        MakeABetActivity.showLostMessage();
                        Log.d("8 ottobre", "Sconfitta, bettato: " + CurrentUser.getNumeroBettato() + " Uscito: " + message);
                    } else Log.d("16 settembre", "Nessun numero bettato  " + CurrentUser.getNumeroBettato() + " Uscito: " + message);
                }

                CurrentUser.setLastNumber(numberString);
                MakeABetActivity.updateLatestNumber(numberString);
                try{MakeABetActivity.latestNumber.setText(message);}catch (Exception e){}
            } else return extractLatestNumber2();
        }
        return number;

    }

    public static int extractLatestNumber2() { //Da modificare con colonne etc
        Integer number = null;
        String message = null;
        try {
            Thread.sleep(100);
            message = Connection.receiveMessageFromServer();
            if(message == null) extractLatestNumber2();
            Log.d("15 settembre", "estrazione");
        } catch (Exception e) {
            e.printStackTrace();
        }

        if(!message.equals("register_success") && !message.equals("login_success") && !message.equals("login_fail")
                && message!=null && !message.equals("") && !message.equals(" ")) {
            if(message.contains("**")) {
                Log.d("15 settembre", "Contiene**");
                //E' il numero estratto
                String numberString = message.replace("**", "");
                number = Integer.parseInt(numberString);

                if(CurrentUser.getInstance() != null && CurrentUser.getNumeroBettato()!=null && checkForWin(number)){
                    MakeABetActivity.showWinMessage();
                    Log.d("15 settembre", "Vittoria");
                } else {
                    MakeABetActivity.showLostMessage();
                    Log.d("15 settembre", "Sconfitta, bettato: "+CurrentUser.getNumeroBettato()+"Uscito: "+ message);
                }

                CurrentUser.setLastNumber(numberString);
                MakeABetActivity.updateLatestNumber(numberString);
                try{MakeABetActivity.latestNumber.setText(message);}catch (Exception e){}
            } else return extractLatestNumber2();
        }
        return number;

    }

    public static boolean checkForWin(Integer numeroUscitoInt){
        String numeroBettatoString=  CurrentUser.getNumeroBettato();
        Integer numeroBettatoInt = Integer.parseInt(numeroBettatoString);
        if(numeroBettatoInt==-1) return false;
        if (numeroBettatoInt<37){
            Log.d("16 settembre", "numeroB <37"+ "uscito "+numeroUscitoInt);
            return (numeroBettatoInt==numeroUscitoInt);
        }
        else if(numeroBettatoInt==37){
            Log.d("16 settembre", "numeroB =37"+ "uscito "+numeroUscitoInt);
        return isNumeroRosso(numeroUscitoInt);
        }else if(numeroBettatoInt==38){
            Log.d("16 settembre", "numeroB =38"+ "uscito "+numeroUscitoInt);
        return (!isNumeroRosso(numeroUscitoInt));
        } else if(numeroBettatoInt==39){
            Log.d("16 settembre", "numeroB =39"+ "uscito "+numeroUscitoInt);
            return (numeroUscitoInt%2 != 0); //dispari
        } else if(numeroBettatoInt==40){
            Log.d("16 settembre", "numeroB =40"+ "uscito "+numeroUscitoInt);
            return (numeroUscitoInt%2 == 0); //pari
        } else if(numeroBettatoInt==41){ //bassi
            Log.d("16 settembre", "numeroB =41" + "uscito "+numeroUscitoInt);
            return (numeroUscitoInt<19);
        } else if(numeroBettatoInt==42){ //alti
            Log.d("16 settembre", "numeroB =42, uscito "+numeroUscitoInt);
            if(numeroUscitoInt > 18) return true;
            else return false;
        } else if(numeroBettatoInt==43){
            Log.d("16 settembre", "numeroB =43, uscito "+numeroUscitoInt);
            return (numeroUscitoInt>0 && numeroUscitoInt < 13);
        } else if(numeroBettatoInt==44){
            Log.d("16 settembre", "numeroB =44 uscito" +numeroUscitoInt);
            return (numeroUscitoInt>12 && numeroUscitoInt < 25);
        } else if(numeroBettatoInt==45){
            Log.d("16 settembre", "numeroB =45"+ "uscito "+numeroUscitoInt);
        return (numeroUscitoInt>24 && numeroUscitoInt < 37);
        } else{
            Log.d("16 settembre", "nessun caso    + \"uscito \"+numeroUscitoInt");
            return false;
        }
    }

    //Check numeri
    static boolean isNumeroRosso(int input){
        int rossi[] = {1, 3, 5, 7, 9, 12, 14, 16, 18, 19, 21, 23, 25, 27, 30, 32, 34, 36};
        for(int i = 0; i<18; i++){
            if(rossi[i] == input){
                Log.d("16 Settembre", "\n NUMERO ROSSO\n");
                return true;
            }
        }
        return false;
    }



    public static int getLatestNumber() {
        String message = null;
        try {
            Thread.sleep(505);
            PrintWriter pwrite = Connection.getPwrite();
            pwrite.println("latestnumber");// sending to server
            pwrite.flush();                    // flush the data
            message = Connection.receiveMessageFromServer();
            Log.d("15 settembre", "latest number1 out "+message);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Integer number=0;
        if(!message.equals("register_success") && !message.equals("login_success") && !message.equals("login_fail")
                && message!=null && !message.equals("") && !message.equals(" ") && !message.contains("**") && message.contains("-"))
            try{
                Log.d("15 settembre", "latest number1 in"+message);
                String numberString = message.replace("--", "");
                number = Integer.parseInt(numberString);
               if(CurrentUser.getLastNumber()==null || CurrentUser.getLastNumber()=="") try{MakeABetActivity.latestNumber.setText(message);}catch (Exception e){}

                return number;
            } catch (Exception e){

            }
        else return getLatestNumber2();
        return number;

    }

    public static int getLatestNumber2() {
        String message = null;
        try {
            Thread.sleep(95);
            message = Connection.receiveMessageFromServer();
            Log.d("15 settembre", "latest number out "+message);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Integer number=0;
        if(!message.equals("register_success") && !message.equals("login_success") && !message.equals("login_fail")
                && message!=null && !message.equals("") && !message.equals(" ") && !message.contains("**") && message.contains("--"))
            try{
                String numberString = message.replace("--", "");
                number = Integer.parseInt(numberString);
                Log.d("15 settembre", "latest number"+message);
                if(CurrentUser.getLastNumber()==null || CurrentUser.getLastNumber()=="") try{MakeABetActivity.latestNumber.setText(message);}catch (Exception e){}
                return number;
            } catch (Exception e){

            }
        else return getLatestNumber2();
        return number;

    }

    public static String getListaUtenti() {
        PrintWriter pwrite = Connection.getPwrite();
        pwrite.println("listautenti");// sending to server
        pwrite.flush();                    // flush the data

        String message = null;
        try {
            message = Connection.receiveMessageFromServer();
            Log.d("15 settembre", "lista utenti");
        } catch (IOException e) {
            e.printStackTrace();
        }
        if( !message.contains(",") && //Scartiamo gli utenti online
                 !message.contains("--") &&
                !message.equals("register_success") && !message.equals("login_success") && !message.equals("login_fail")
                && message!=null
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
            Log.d("Lista utenti", message + " end");
            return message;
        } else return getListaUtenti2();

    }
    public static String getListaUtenti2() {
        String message = null;
        try {
            message = Connection.receiveMessageFromServer();
            Log.d("15 settembre", "lista utenti");
        } catch (IOException e) {
            e.printStackTrace();
        }
        if( message.contains(";") || message.equals("") || message.equals(" ")) {
            Log.d("Lista utenti", message + " end");
            return message;
        } else return getListaUtenti2();

    }

    public static void inviaRichiestaLogout(String username) {
        PrintWriter pwrite = Connection.getPwrite();
        pwrite.println("logout "+username);// sending to server
        pwrite.flush();                    // flush the data

        String message = null;
        try {
            message = Connection.receiveMessageFromServer();
            if(message.contains("logout"))
            Log.d("28 settembre", "logout "+message);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
