package com.example.adrcre.areas_figuras_enero;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

public class Pant1 extends AppCompatActivity {

    Figuras [] figuras = new Figuras[] {

            new Figuras("Circulo", 1, R.drawable.circulo),
            new Figuras("Rectángulo", 2, R.drawable.rectangulo),
            new Figuras("Triángulo", 3, R.drawable.triangulo)
    };

    Spinner spinner;
    int i = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pant1);

        final Button botPantalla = (Button) findViewById(R.id.botFigura);
        spinner = (Spinner) findViewById(R.id.spinner);
        adaptarFigura adaptar = new adaptarFigura(this);
        spinner.setAdapter(adaptar);

        spinner.setSelection(-1);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                i = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        botPantalla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Pant1.this, Pant2.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("Figura", figuras[i]);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });



    }

    class adaptarFigura extends ArrayAdapter {

        Activity context;
        adaptarFigura(Activity context) {
            super(context, R.layout.spinner, figuras);
            this.context = context;
        }

        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
        public View getDropDownView(int position, View convertView, ViewGroup parent){
            View vista = getView(position, convertView, parent);
            return vista;
        }

        @TargetApi(Build.VERSION_CODES.LOLLIPOP)
        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
        public View getView(int position, View convertiView, ViewGroup parent){
            LayoutInflater inflater = context.getLayoutInflater();
            View item = inflater.inflate(R.layout.spinner, null);

            TextView nombre = (TextView) item.findViewById(R.id.nombreFiguraSpinner);
            ImageView img = (ImageView) item.findViewById(R.id.imgFiguraSpinner);

            nombre.setText(figuras[position].getNombre());
            img.setBackground(getDrawable(figuras[position].getImg()));

            return item;
        }

    }

}
