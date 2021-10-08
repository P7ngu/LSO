package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class InserimentoDatiServerActivity extends AppCompatActivity {
    Button sendConnectBTN, autoConnectBTN;
    EditText ip1, ip2, ip3, ip4, port;
    Context mContext;
    SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dati_server);

        mContext=this;
        prefs = mContext.getSharedPreferences("myPrefsKeys", Context.MODE_PRIVATE);


        sendConnectBTN=findViewById(R.id.button_connect);
        autoConnectBTN=findViewById(R.id.auto_connect);
        ip1=findViewById(R.id.editTextNumber_ip1);
        ip2=findViewById(R.id.editTextNumber_ip2);
        ip3=findViewById(R.id.editTextNumber_ip3);
        ip4=findViewById(R.id.editTextNumber_ip4);
        port=findViewById(R.id.editTextNumber_port);
        String ipPrestabilito="52.236.135.22";

        autoConnectBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    prefs = mContext.getSharedPreferences("myPrefsKeys", Context.MODE_PRIVATE);
                    final String ip_sp = prefs.getString("ip", "");
                    final String port_sp = prefs.getString("port", "");
                    Log.d("Stored Data", ip_sp + port_sp);
                    Connection connection = new Connection(ip_sp, port_sp);
                    Connection.setConnectionData(ip_sp, port_sp);
                    connection.execute();
                    CurrentUser.setLastNumber(Client.getLatestNumber()+"");
                    startActivity(new Intent(mContext, RegisterActivity.class));
                } catch (Exception e){
                    PopupController.mostraPopup("Errore", "Errore connessione server", mContext);
                    e.printStackTrace();

                }
            }
        });

        sendConnectBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ip_formattato = ip1.getText().toString().concat(".").
                        concat(ip2.getText().toString().concat(".")
                        .concat(ip3.getText().toString().concat(".").
                                concat(ip4.getText().toString())));

                try {
                    Connection connection = new Connection(ip_formattato, port.getText().toString());
                    Connection.setConnectionData(ip_formattato, port.getText().toString());
                    connection.execute();
                    CurrentUser.setLastNumber(Client.getLatestNumber()+"");
                    try { //Let's save the data
                        SharedPreferences.Editor editor = prefs.edit();
                        editor.putString("ip", ip_formattato);
                        editor.putString("port", port.getText().toString());
                        editor.apply();
                    } catch (Exception e){
                        e.printStackTrace();
                    }
                    startActivity(new Intent(mContext, RegisterActivity.class));
                } catch (Exception e){
                    PopupController.mostraPopup("Errore", "Errore connessione server", mContext);
                    e.printStackTrace();

                }
            }
        });
    }
}