package com.example.adrcre.agrupaejercicios;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AgrupaEjercicios extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agrupa_ejercicios);

        final Button botonCheck = (Button) findViewById(R.id.botonCheck);
        final Button botonRadio = (Button) findViewById(R.id.botonRadio);
        final Button botonDiferentes = (Button) findViewById(R.id.botonDiferentes);


        botonCheck.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent mio=new Intent(AgrupaEjercicios.this, checkActivity.class);
                Bundle miBundle=new Bundle();
                mio.putExtras(miBundle);
                startActivity(mio);
            }
        });

        botonRadio.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent mio=new Intent(AgrupaEjercicios.this, radioActivity.class);
                Bundle miBundle=new Bundle();
                mio.putExtras(miBundle);
                startActivity(mio);
            }
        });

        botonDiferentes.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent mio=new Intent(AgrupaEjercicios.this, radioActivity.class);
                Bundle miBundle=new Bundle();
                mio.putExtras(miBundle);
                startActivity(mio);
            }
        });

    }
}
