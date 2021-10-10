package com.example.myapplication;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.Toast;


public class PopupController {

    public static void mostraPopup(String titolo, String messaggio, Context myContext) {
        try {
            AlertDialog alertDialog = new AlertDialog.Builder(myContext).create(); //Read Update
            alertDialog.setTitle(titolo);
            alertDialog.setMessage(messaggio);

            alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    // here you can add functions
                }
            });

            alertDialog.show();
        } catch (Exception e) {
            Context contextApp=GlobalApplication.getAppContext();
            Toast.makeText(contextApp, "Errore di connessione. Riavvia l'app. ", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }

    }

    public static void mostraPopupDiConfermaOAnnulla(String titolo, String messaggio, Context myContext, String classe, String path, String idObject) {
        try {
            AlertDialog.Builder builder = new AlertDialog.Builder(myContext);
            boolean risultato;

            builder.setPositiveButton("Continua", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {


                }
            });
            builder.setNegativeButton("Annulla azione", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {

                    // User cancelled the dialog
                }
            });

            AlertDialog dialog = builder.create();
            dialog.setTitle(titolo);
            dialog.setMessage(messaggio);
            dialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
