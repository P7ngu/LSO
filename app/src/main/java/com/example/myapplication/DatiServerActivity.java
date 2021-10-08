package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class DatiServerActivity extends AppCompatActivity {
    Button sendConnectBTN, autoConnectBTN;
    EditText ip1, ip2, ip3, ip4, port;
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dati_server);

        mContext=this;

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
                    Connection connection = new Connection(ipPrestabilito, port.getText().toString());
                    Connection.setConnectionData(ipPrestabilito, port.getText().toString());
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
                    startActivity(new Intent(mContext, RegisterActivity.class));
                } catch (Exception e){
                    e.printStackTrace();

                }
            }
        });
    }
}