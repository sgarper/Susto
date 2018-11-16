package com.example.usuario.susto;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    String [] susto1={"Susto 1","Susto 2","Susto 3","Susto 4","Susto 5","Susto 6","Susto 7","Susto 8","Susto 9","Susto 10"};
    EditText editText;
    int contador;
    Button susto,boton;
    //Thread hilo = null;
    Boolean activo = true;
    Boolean activo2 = true;
    TextView num;
    MiCronometro cronometro= null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Typeface face=Typeface.createFromAsset(getAssets(),"fonts/Beurk.ttf");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        susto=(Button)findViewById(R.id.susto);
        boton=(Button) findViewById(R.id.button);
        num=(TextView)findViewById(R.id.num);

        susto.setTypeface(face);

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (MainActivity.this, Main2Activity.class);
                startActivity(intent);
            }
        });

        susto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                susto.setEnabled(false);
                final android.support.v7.widget.GridLayout layout = findViewById(R.id.layout);
                final android.support.v7.widget.GridLayout layout2 = findViewById(R.id.layout2);
                final android.support.v7.widget.GridLayout layout3 = findViewById(R.id.layout3);


                Spinner boton;
                EditText segundos1;
                Button cuenta_atras1;

                ArrayAdapter botonn=new ArrayAdapter(MainActivity.this,R.layout.support_simple_spinner_dropdown_item,susto1);

                int numero = Integer.parseInt(num.getText().toString());

                for(int i=0;i<numero;i++)
                {
                    ArrayList<String> boton1=new ArrayList<>();
                    boton=new Spinner(MainActivity.this);

                    segundos1 =new EditText(MainActivity.this);
                    segundos1.setText("0");
                    segundos1.setWidth(400);
                    //segundos1.setBackgroundColor(R.color.colorAccent);
                    cuenta_atras1=new Button(MainActivity.this);

                    cuenta_atras1.setBackground(getResources().getDrawable(R.drawable.flecha));

                    boton.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT));
                    segundos1.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT));
                    cuenta_atras1.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT));
                    //boton.setText("Botón "+(i+1));


                    boton.setAdapter(botonn);
                    boton.setBackgroundColor(Color.RED);
                    segundos1.setTextColor(getResources().getColor(R.color.colorAccent));
                    segundos1.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
                    layout.addView(boton,i);


                    layout2.addView(segundos1,i);

                    layout3.addView(cuenta_atras1,i);


                    final EditText finaleditText = segundos1;
                    final Spinner finalBoton = boton;


                    cuenta_atras1.setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {
                            contador=Integer.parseInt(finaleditText.getText().toString());
                            switch(finalBoton.getSelectedItemPosition())
                            {

                                case 0:
                                    editText =finaleditText;
                                    if (cronometro==null) {
                                        cronometro = new MiCronometro();
                                        //cronometro.execute();
                                        cronometro.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                                    }else{activo2=true;}
                                    if(editText.getText().toString()=="0:0")
                                    {
                                        //Toast.makeText(MainActivity.this, "Entra", Toast.LENGTH_SHORT).show();
                                        if (cronometro!=null) {
                                            activo2 = false;
                                        }
                                    }

                                    break;
                            }
                        }
                    });

                }



            }
        });


    }
    private class MiCronometro extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... strings) {
            while (true) {
                while (activo2) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    contador--;
                    publishProgress("" + contador);
                }
            }
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
            int segundos = Integer.parseInt(values[0]);

            editText.setText(""+ (segundos/60)+":" +(segundos%60));
        }
    }
}
