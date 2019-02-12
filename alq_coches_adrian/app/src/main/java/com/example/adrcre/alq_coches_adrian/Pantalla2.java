package com.example.adrcre.alq_coches_adrian;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
public class Pantalla2 extends AppCompatActivity {
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla2);
        Intent intent = getIntent();
        final TextView marca = (TextView) findViewById(R.id.marca2);
        final TextView modelo = (TextView) findViewById(R.id.modelo2);
        final TextView horas = (TextView) findViewById(R.id.horas2);
        final TextView seguro = (TextView) findViewById(R.id.seguro2);
        final TextView extras = (TextView) findViewById(R.id.extras2);
        final Button backPantalla1 = (Button) findViewById(R.id.volver);

        datos objetoCoche = (datos) intent.getSerializableExtra("Coche");
        float precioFinal = 0;
        marca.setText(objetoCoche.getMarca());
        modelo.setText(objetoCoche.getModelo());
        horas.setText(String.valueOf(objetoCoche.getHorasAlq()));
        seguro.setText(objetoCoche.getSeguro());


        if(objetoCoche.getExtras() == true){
            precioFinal += 50;
        }
        if(objetoCoche.getSeguro().equalsIgnoreCase("Seguro a Todo Riesgo")){
            precioFinal += 20;
        }else{
            precioFinal += 100;
        }
        extras.setText(String.valueOf(precioFinal));
        backPantalla1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Pantalla2.this, Pantalla1.class);
                startActivity(intent);
            }
        });
    }
}
