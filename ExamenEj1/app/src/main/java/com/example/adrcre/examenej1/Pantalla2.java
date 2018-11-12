package com.example.adrcre.examenej1;

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
        final TextView continente = (TextView) findViewById(R.id.continente2);
        final TextView zona = (TextView) findViewById(R.id.zona2);
        final TextView peso = (TextView) findViewById(R.id.peso2);
        final TextView envio = (TextView) findViewById(R.id.envio2);
        final TextView precio = (TextView) findViewById(R.id.precio2);
        final ImageView imagen2 = (ImageView) findViewById(R.id.imagen2);
        final Button volver = (Button) findViewById(R.id.volver);
        zona zonaObjeto = (zona) intent.getSerializableExtra("Objeto");
        float precioTotal = zonaObjeto.getPeso() + zonaObjeto.getPrecio();
        continente.setText(zonaObjeto.getContinente());
        zona.setText(zonaObjeto.getNombre());
        peso.setText(String.valueOf(zonaObjeto.getPeso()));
        envio.setText(zonaObjeto.getEnvio());
        imagen2.setBackground(getDrawable(zonaObjeto.getImagen()));
        if(zonaObjeto.getRegalo() == true){
            precioTotal += 5;
        }
        if(zonaObjeto.getDedicatoria() == true){
            precioTotal += 7;
        }
        if(zonaObjeto.getEnvio().equalsIgnoreCase("Tarifa Normal")){
            precioTotal += 5;
        }else{
            precioTotal += 20;
        }
        precio.setText(String.valueOf(precioTotal));
        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Pantalla2.this, Pantalla1.class);
                startActivity(intent);
            }
        });
    }
}