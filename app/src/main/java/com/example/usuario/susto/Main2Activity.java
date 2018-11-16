package com.example.usuario.susto;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    TextView titulo,t1,t2,t3,t4;
    Button atras;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Typeface face=Typeface.createFromAsset(getAssets(),"fonts/letra4.ttf");
        Typeface face2=Typeface.createFromAsset(getAssets(),"fonts/Beurk.ttf");

        atras=(Button) findViewById(R.id.atras);
        titulo=(TextView)findViewById(R.id.titulo);
        t1=(TextView)findViewById(R.id.textView2);
        t2=(TextView)findViewById(R.id.textView3);
        t3=(TextView)findViewById(R.id.textView4);
        t4=(TextView)findViewById(R.id.textView5);

        titulo.setTypeface(face);
        t1.setTypeface(face2);
        t2.setTypeface(face2);
        t3.setTypeface(face2);
        t4.setTypeface(face2);

        atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Main2Activity.this,MainActivity.class));
            }
        });
    }
}
