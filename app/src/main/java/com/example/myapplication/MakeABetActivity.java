package com.example.myapplication;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;

import java.util.ArrayList;

public class MakeABetActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Spinner importoSpinner, numeroSpinner;
    Button sendBetButton;
    String numeroPuntato, importoScommesso;
    static Context mContext;
    static Button latestNumber;
    ArrayList<String> betSelezionate = new ArrayList<>();
    ArrayList<CheckBox> checkBoxesCliccate = new ArrayList<>();

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
            mContext.startActivity(new Intent(mContext, WaitingActivity.class));
        } catch (Exception e){

        }
    }


    public void onItemSelected(AdapterView<?> parent, View view,
                                   int pos, long id) {



        }

        public void onNothingSelected(AdapterView<?> parent) {
            // Another interface callback
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

        CheckBox cRed = findViewById(R.id.cb_rosso);
        cRed.setBackgroundColor(Color.RED);
        //cRed.getButtonTintList(Color.RED);

        CheckBox cc1 = findViewById(R.id.cb_1colonna);
        cc1.setBackgroundColor(Color.rgb(0,100,0));

        CheckBox cc2 = findViewById(R.id.cb_2colonna);
        cc2.setBackgroundColor(Color.rgb(0,100,0));

        CheckBox cc3 = findViewById(R.id.cb_3colonna);
        cc3.setBackgroundColor(Color.rgb(0,100,0));

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
        c0.setBackgroundColor(Color.rgb(0,100,0));

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
                    rimuoviChecks(c3);
                    betSelezionate.add("36");
                }
                else betSelezionate.remove("36");
            }
        });
        c36.setBackgroundColor(Color.RED);


















        ////////neri
        CheckBox c2 = findViewById(R.id.cb2);
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
                Client.inviaScommessa(numeroPuntato, importoScommesso);
                CurrentUser.setNumeroBettato(numeroPuntato);
                CurrentUser.setImportoScommesso(importoScommesso);
                startActivity(new Intent(mContext, WaitingActivity.class));
            }
        });




        String numeroEstratto = Client.getLatestNumber()+"";
            Button latestNumber = findViewById(R.id.button2_latestnumber);
            latestNumber.setText(numeroEstratto + "");






    }


    public static void updateLatestNumber(String nuovoNumero){
    if(latestNumber!=null) latestNumber.setText(nuovoNumero);
    }
}