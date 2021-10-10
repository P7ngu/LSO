package com.example.myapplication;

import androidx.annotation.RequiresApi;
import androidx.annotation.WorkerThread;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethod;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;

import java.util.ArrayList;

public class MakeABetActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Spinner importoSpinner;
    Button sendBetButton;
    String numeroPuntato, importoScommesso;
    static Context mContext;
    static Button latestNumber;
    ArrayList<String> betSelezionate = new ArrayList<>();
    ArrayList<CheckBox> checkBoxesCliccate = new ArrayList<>();

    @Override
    public void onBackPressed() {
        Log.d("CDA", "onBackPressed Called");
        Intent setIntent = new Intent(mContext, HomeActivity.class);
        startActivity(setIntent);
        return;
    }



    public static void showWinMessage() {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                Integer prevMoney=Integer.parseInt(CurrentUser.getMoneyCount());
                Integer cash=Integer.parseInt(CurrentUser.getImportoScommesso()) *30;
                Client.getMoneyCountForCurrentUser();
                PopupController.mostraPopup("Complimenti!", "Hai vinto ben "+cash+" gettoni! Ora ne hai "+CurrentUser.getMoneyCount(), mContext);

                WaitingActivity.showWinMessage();
                HomeActivity.showWinMessage();
            }
        });
    }

    public static void showLostMessage() {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                Integer prevMoney=Integer.parseInt(CurrentUser.getMoneyCount());
                Integer cash=Integer.parseInt(CurrentUser.getImportoScommesso());
                Client.getMoneyCountForCurrentUser();
             if (mContext!=null) PopupController.mostraPopup("Mi dispiace!", "Hai perso: "+CurrentUser.getImportoScommesso()+" gettoni! :( Ora ne hai "+
                     CurrentUser.getMoneyCount(), mContext);
             WaitingActivity.showLostMessage();
             HomeActivity.showLostMessage();
            }
        });

    }

    public void onCheckboxClicked(View view) {
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();

        // Check which checkbox was clicked
        switch(view.getId()) {
            case R.id.cb0:
                if (checked) betSelezionate.add("0");
                // Put some meat on the sandwich
                else betSelezionate.remove("0");

                break;
            case R.id.cb1:
                if (checked) betSelezionate.add("1");
                else betSelezionate.remove("1");
                // Cheese me

                break;

        }

    }



    public static void startWaitingActivity() {
        try {
            int logStatus = CurrentUser.getUserLoggedStatus();
            if(logStatus==1)
            mContext.startActivity(new Intent(mContext, WaitingActivity.class));
        } catch (Exception e){

        }
    }


    public void onItemSelected(AdapterView<?> parent, View view,
                                   int pos, long id) {
        // An item was selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos)
        Spinner spinner = (Spinner) findViewById(R.id.importo_spinner);
        Log.d("Debuggg", (String) parent.getItemAtPosition(pos));
        Spinner spin = (Spinner)parent;


            importoScommesso = spinner.getItemAtPosition(pos)+"";


        }

        public void onNothingSelected(AdapterView<?> parent) {
            // Another interface callback
            Log.d("10 ottobre", "on nothing selected");
        }

public void rimuoviChecks(CheckBox checkDaLasciare){
    for(int i=0; i<checkBoxesCliccate.size(); i++)
        if(!checkBoxesCliccate.get(i).equals(checkDaLasciare)) checkBoxesCliccate.get(i).setChecked(false);
    checkBoxesCliccate.clear();

    checkBoxesCliccate.add(checkDaLasciare);
}



    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_a_bet);
        mContext=this;


        importoSpinner = (Spinner) findViewById(R.id.importo_spinner);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,
                R.array.importi_array, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        importoSpinner.setAdapter(adapter1);

        importoSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                importoScommesso = importoSpinner.getItemAtPosition(position)+"";
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        CheckBox cPari = findViewById(R.id.cB_pari);
        cPari.setBackgroundColor(Color.BLACK);
        cPari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = ((CheckBox) cPari).isChecked();
                if (checked) {
                    rimuoviChecks(cPari);
                    betSelezionate.add("40");
                }
                else betSelezionate.remove("40");
            }
        });


        CheckBox cDisPari = findViewById(R.id.cB_dispari);
        cDisPari.setBackgroundColor(Color.RED);
        cDisPari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = ((CheckBox) cDisPari).isChecked();
                if (checked) {
                    rimuoviChecks(cDisPari);
                    betSelezionate.add("39");
                }
                else betSelezionate.remove("39");
            }
        });


        CheckBox cRed = findViewById(R.id.cb_rosso);
        cRed.setBackgroundColor(Color.RED);
        cRed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = ((CheckBox) cRed).isChecked();
                if (checked) {
                    rimuoviChecks(cRed);
                    betSelezionate.add("37");
                }
                else betSelezionate.remove("37");
            }
        });

        CheckBox cBlack = findViewById(R.id.cb_nero);
        cBlack.setBackgroundColor(Color.BLACK);
        cBlack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = ((CheckBox) cBlack).isChecked();
                if (checked) {
                    rimuoviChecks(cBlack);
                    betSelezionate.add("38");
                }
                else betSelezionate.remove("38");
            }
        });

        CheckBox cAlto = findViewById(R.id.cb_alto);
        cAlto.setBackgroundColor(Color.BLACK);
        cAlto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = ((CheckBox) cAlto).isChecked();
                if (checked) {
                    rimuoviChecks(cAlto);
                    betSelezionate.add("42");
                }
                else betSelezionate.remove("42");
            }
        });

        CheckBox cBasso = findViewById(R.id.cb_basso);
        cBasso.setBackgroundColor(Color.RED);
        cBasso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = ((CheckBox) cBasso).isChecked();
                if (checked) {
                    rimuoviChecks(cBasso);
                    betSelezionate.add("41");
                }
                else betSelezionate.remove("41");
            }
        });

        CheckBox cc1 = findViewById(R.id.cb_1colonna);
        cc1.setBackgroundColor(Color.rgb(0,100,0));
        cc1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = ((CheckBox) cc1).isChecked();
                if (checked) {
                    rimuoviChecks(cc1);
                    betSelezionate.add("43");
                }
                else betSelezionate.remove("43");
            }
        });

        CheckBox cc2 = findViewById(R.id.cb_2colonna);
        cc2.setBackgroundColor(Color.rgb(0,100,0));
        cc2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = ((CheckBox) cc2).isChecked();
                if (checked) {
                    rimuoviChecks(cc2);
                    betSelezionate.add("44");
                }
                else betSelezionate.remove("44");
            }
        });

        CheckBox cc3 = findViewById(R.id.cb_3colonna);
        cc3.setBackgroundColor(Color.rgb(0,100,0));
        cc3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = ((CheckBox) cc3).isChecked();
                if (checked) {
                    rimuoviChecks(cc3);
                    betSelezionate.add("45");
                }
                else betSelezionate.remove("45");
            }
        });

        CheckBox c0 = findViewById(R.id.cb0);
        c0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = ((CheckBox) c0).isChecked();
                if (checked) {
                    rimuoviChecks(c0);
                    betSelezionate.add("0");
                }
                else betSelezionate.remove("0");
            }
        });
        c0.setBackgroundColor(Color.rgb(139,195,74));

        CheckBox c1 = findViewById(R.id.cb1);
        c1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = ((CheckBox) c1).isChecked();
                if (checked) {
                    rimuoviChecks(c1);
                    betSelezionate.add("1");
                }
                else betSelezionate.remove("1");
            }
        });
        c1.setBackgroundColor(Color.RED);

        CheckBox c3 = findViewById(R.id.cb3);
        c3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = ((CheckBox) c3).isChecked();
                if (checked) {
                    rimuoviChecks(c3);
                    betSelezionate.add("3");
                }
                else betSelezionate.remove("3");
            }
        });
        c3.setBackgroundColor(Color.RED);

        CheckBox c5 = findViewById(R.id.cb5);
        c5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = ((CheckBox) c5).isChecked();
                if (checked) {
                    rimuoviChecks(c5);
                    betSelezionate.add("5");
                }
                else betSelezionate.remove("5");
            }
        });
        c5.setBackgroundColor(Color.RED);

        CheckBox c7 = findViewById(R.id.cb7);
        c7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = ((CheckBox) c7).isChecked();
                if (checked) {
                    rimuoviChecks(c7);
                    betSelezionate.add("7");
                }
                else betSelezionate.remove("7");
            }
        });
        c7.setBackgroundColor(Color.RED);

        CheckBox c9 = findViewById(R.id.cb9);
        c9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = ((CheckBox) c9).isChecked();
                if (checked) {
                    rimuoviChecks(c9);
                    betSelezionate.add("9");
                }
                else betSelezionate.remove("9");
            }
        });
        c9.setBackgroundColor(Color.RED);

        CheckBox c12 = findViewById(R.id.cb12);
        c12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = ((CheckBox) c12).isChecked();
                if (checked) {
                    rimuoviChecks(c12);
                    betSelezionate.add("12");
                }
                else betSelezionate.remove("12");
            }
        });
        c12.setBackgroundColor(Color.RED);

        CheckBox c14 = findViewById(R.id.cb14);
        c14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = ((CheckBox) c14).isChecked();
                if (checked) {
                    rimuoviChecks(c14);
                    betSelezionate.add("14");
                }
                else betSelezionate.remove("14");
            }
        });
        c14.setBackgroundColor(Color.RED);

        CheckBox c16 = findViewById(R.id.cb16);
        c16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = ((CheckBox) c16).isChecked();
                if (checked) {
                    rimuoviChecks(c16);
                    betSelezionate.add("16");
                }
                else betSelezionate.remove("16");
            }
        });
        c16.setBackgroundColor(Color.RED);

        CheckBox c18 = findViewById(R.id.cb18);
        c18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = ((CheckBox) c18).isChecked();
                if (checked) {
                    rimuoviChecks(c18);
                    betSelezionate.add("18");
                }
                else betSelezionate.remove("18");
            }
        });
        c18.setBackgroundColor(Color.RED);

        CheckBox c19 = findViewById(R.id.cb19);
        c19.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = ((CheckBox) c19).isChecked();
                if (checked) {
                    rimuoviChecks(c19);
                    betSelezionate.add("19");
                }
                else betSelezionate.remove("19");
            }
        });
        c19.setBackgroundColor(Color.RED);

        CheckBox c21 = findViewById(R.id.cb21);
        c21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = ((CheckBox) c21).isChecked();
                if (checked) {
                    rimuoviChecks(c21);
                    betSelezionate.add("21");
                }
                else betSelezionate.remove("21");
            }
        });
        c21.setBackgroundColor(Color.RED);

        CheckBox c23 = findViewById(R.id.cb23);
        c23.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = ((CheckBox) c23).isChecked();
                if (checked) {
                    rimuoviChecks(c23);
                    betSelezionate.add("23");
                }
                else betSelezionate.remove("23");
            }
        });
        c23.setBackgroundColor(Color.RED);

        CheckBox c25 = findViewById(R.id.cb25);
        c25.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = ((CheckBox) c25).isChecked();
                if (checked) {
                    rimuoviChecks(c25);
                    betSelezionate.add("25");
                }
                else betSelezionate.remove("25");
            }
        });
        c25.setBackgroundColor(Color.RED);

        CheckBox c27 = findViewById(R.id.cb27);
        c27.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = ((CheckBox) c27).isChecked();
                if (checked) {
                    rimuoviChecks(c27);
                    betSelezionate.add("27");
                }
                else betSelezionate.remove("27");
            }
        });
        c27.setBackgroundColor(Color.RED);

        CheckBox c30 = findViewById(R.id.cb30);
        c30.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = ((CheckBox) c30).isChecked();
                if (checked) {
                    rimuoviChecks(c30);
                    betSelezionate.add("30");
                }
                else betSelezionate.remove("30");
            }
        });
        c30.setBackgroundColor(Color.RED);

        CheckBox c32 = findViewById(R.id.cb32);
        c32.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = ((CheckBox) c32).isChecked();
                if (checked) {
                    rimuoviChecks(c32);
                    betSelezionate.add("32");
                }
                else betSelezionate.remove("32");
            }
        });
        c32.setBackgroundColor(Color.RED);

        CheckBox c34 = findViewById(R.id.cb34);
        c34.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = ((CheckBox) c34).isChecked();
                if (checked) {
                    rimuoviChecks(c34);
                    betSelezionate.add("34");
                }
                else betSelezionate.remove("34");
            }
        });
        c34.setBackgroundColor(Color.RED);

        CheckBox c36 = findViewById(R.id.cb36);
        c36.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = ((CheckBox) c36).isChecked();
                if (checked) {
                    rimuoviChecks(c36);
                    betSelezionate.add("36");
                }
                else betSelezionate.remove("36");
            }
        });
        c36.setBackgroundColor(Color.RED);


















        ////////neri
        CheckBox c2 = findViewById(R.id.cb2);
        c2.setBackgroundColor(Color.BLACK);
        c2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = ((CheckBox) c2).isChecked();
                if (checked) {
                    rimuoviChecks(c2);
                    betSelezionate.add("2");
                }
                else betSelezionate.remove("2");
            }
        });


        CheckBox c4 = findViewById(R.id.cb4);
        c4.setBackgroundColor(Color.BLACK);
        c4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = ((CheckBox) c4).isChecked();
                if (checked) {
                    rimuoviChecks(c4);
                    betSelezionate.add("4");
                }
                else betSelezionate.remove("4");
            }
        });


        CheckBox c6 = findViewById(R.id.cb6);
        c6.setBackgroundColor(Color.BLACK);
        c6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = ((CheckBox) c6).isChecked();
                if (checked) {
                    rimuoviChecks(c6);
                    betSelezionate.add("6");
                }
                else betSelezionate.remove("6");
            }
        });

        CheckBox c8 = findViewById(R.id.cb8);
        c8.setBackgroundColor(Color.BLACK);
        c8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = ((CheckBox) c8).isChecked();
                if (checked) {
                    rimuoviChecks(c8);
                    betSelezionate.add("8");
                }
                else betSelezionate.remove("8");
            }
        });


        CheckBox c10 = findViewById(R.id.cb10);
        c10.setBackgroundColor(Color.BLACK);
        c10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = ((CheckBox) c10).isChecked();
                if (checked) {
                    rimuoviChecks(c10);
                    betSelezionate.add("10");
                }
                else betSelezionate.remove("10");
            }
        });


        CheckBox c11 = findViewById(R.id.cb11);
        c11.setBackgroundColor(Color.BLACK);
        c11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = ((CheckBox) c11).isChecked();
                if (checked) {
                    rimuoviChecks(c11);
                    betSelezionate.add("11");
                }
                else betSelezionate.remove("11");
            }
        });


        CheckBox c13 = findViewById(R.id.cb13);
        c13.setBackgroundColor(Color.BLACK);
        c13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = ((CheckBox) c13).isChecked();
                if (checked) {
                    rimuoviChecks(c13);
                    betSelezionate.add("13");
                }
                else betSelezionate.remove("13");
            }
        });


        CheckBox c15 = findViewById(R.id.cb15);
        c15.setBackgroundColor(Color.BLACK);
        c15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = ((CheckBox) c15).isChecked();
                if (checked) {
                    rimuoviChecks(c15);
                    betSelezionate.add("15");
                }
                else betSelezionate.remove("15");
            }
        });


        CheckBox c17 = findViewById(R.id.cb17);
        c17.setBackgroundColor(Color.BLACK);
        c17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = ((CheckBox) c17).isChecked();
                if (checked) {
                    rimuoviChecks(c17);
                    betSelezionate.add("17");
                }
                else betSelezionate.remove("17");
            }
        });


        CheckBox c20 = findViewById(R.id.cb20);
        c20.setBackgroundColor(Color.BLACK);
        c20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = ((CheckBox) c20).isChecked();
                if (checked) {
                    rimuoviChecks(c20);
                    betSelezionate.add("20");
                }
                else betSelezionate.remove("20");
            }
        });


        CheckBox c22 = findViewById(R.id.cb22);
        c22.setBackgroundColor(Color.BLACK);
        c22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = ((CheckBox) c22).isChecked();
                if (checked) {
                    rimuoviChecks(c22);
                    betSelezionate.add("22");
                }
                else betSelezionate.remove("22");
            }
        });


        CheckBox c24 = findViewById(R.id.cb24);
        c24.setBackgroundColor(Color.BLACK);
        c24.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = ((CheckBox) c24).isChecked();
                if (checked) {
                    rimuoviChecks(c24);
                    betSelezionate.add("24");
                }
                else betSelezionate.remove("24");
            }
        });


        CheckBox c26 = findViewById(R.id.cb26);
        c26.setBackgroundColor(Color.BLACK);
        c26.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = ((CheckBox) c26).isChecked();
                if (checked) {
                    rimuoviChecks(c26);
                    betSelezionate.add("26");
                }
                else betSelezionate.remove("26");
            }
        });


        CheckBox c28 = findViewById(R.id.cb28);
        c28.setBackgroundColor(Color.BLACK);
        c28.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = ((CheckBox) c28).isChecked();
                if (checked) {
                    rimuoviChecks(c28);
                    betSelezionate.add("28");
                }
                else betSelezionate.remove("28");
            }
        });

        CheckBox c29 = findViewById(R.id.cb29);
        c29.setBackgroundColor(Color.BLACK);
        c29.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = ((CheckBox) c29).isChecked();
                if (checked) {
                    rimuoviChecks(c29);
                    betSelezionate.add("29");
                }
                else betSelezionate.remove("29");
            }
        });


        CheckBox c31 = findViewById(R.id.cb31);
        c31.setBackgroundColor(Color.BLACK);
        c31.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = ((CheckBox) c31).isChecked();
                if (checked) {
                    rimuoviChecks(c31);
                    betSelezionate.add("31");
                }
                else betSelezionate.remove("31");
            }
        });


        CheckBox c33 = findViewById(R.id.cb33);
        c33.setBackgroundColor(Color.BLACK);
        c33.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = ((CheckBox) c33).isChecked();
                if (checked) {
                    rimuoviChecks(c33);
                    betSelezionate.add("33");
                }
                else betSelezionate.remove("33");
            }
        });


        CheckBox c35 = findViewById(R.id.cb35);
        c35.setBackgroundColor(Color.BLACK);
        c35.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = ((CheckBox) c35).isChecked();
                if (checked) {
                    rimuoviChecks(c35);
                    betSelezionate.add("35");
                }
                else betSelezionate.remove("35");
            }
        });

        







        sendBetButton=findViewById(R.id.button_sendbet);
        sendBetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Da riaggiornare
                //for (int i = 0; i < betSelezionate.size(); i++){
                  //  Client.inviaScommessa((betSelezionate.get(i)), importoScommesso);
                //Log.d("Debug 2.0", "Scommessa inviata" + betSelezionate.get(i));
            //}
                try {
                    numeroPuntato = betSelezionate.get(0).toString();
                    Integer prevMoney= Integer.parseInt(CurrentUser.getMoneyCount());
                    Integer intBetMoney=Integer.parseInt(importoScommesso);
                    if( prevMoney >= intBetMoney) {
                        Client.inviaScommessa(numeroPuntato, importoScommesso);
                        CurrentUser.setNumeroBettato(numeroPuntato);
                        CurrentUser.setImportoScommesso(importoScommesso);
                        Client.getMoneyCountForCurrentUser();
                        startActivity(new Intent(mContext, WaitingActivity.class));
                    } else PopupController.mostraPopup("Errore!", "Non possiedi abbastanza gettoni!", mContext);
                } catch (Exception e){
                    e.printStackTrace();
                    PopupController.mostraPopup("Errore!", "Assicurati di aver selezionato una casella", mContext);
                }
            }
        });

            latestNumber = findViewById(R.id.button2_latestnumber);
            String numeroEstratto = CurrentUser.getLastNumber()+"";
            Log.d("29 settembre", numeroEstratto+"< Latest number");
            latestNumber.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int numeretto = Client.getLatestNumber();
                    latestNumber.setText(numeretto+"");
                }
            });

            //latestNumber.setText(numeroEstratto + "");
        try{
            latestNumber.setText("Show");
        } catch (Exception e){
            Client.getLatestNumber();
        }






    }


    public static void updateLatestNumber(String nuovoNumero){
    if(latestNumber!=null) new Handler(Looper.getMainLooper()).post(new Runnable() {
        @Override
        public void run() {
            latestNumber.setText(nuovoNumero);
        }
    });
    }
}