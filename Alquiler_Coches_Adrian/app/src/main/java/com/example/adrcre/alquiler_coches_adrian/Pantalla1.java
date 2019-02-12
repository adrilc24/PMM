package com.example.adrcre.alquiler_coches_adrian;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;



public class Pantalla1 extends AppCompatActivity {
    public MainActivity[] alquiler = new MainActivity[]{
            new MainActivity("Opel", "Corsa", 48,100,true, "Sin seguro"),
            new MainActivity("Mercedes", "Benz", 72,200, false, "Seguro a Todo Riesgo"),
            new MainActivity("Mazda", "CX5", 12,50, true, "Seguro a Todo Riesgo")
    };

    public int i = 0;
    public Spinner spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla1);
        spinner = (Spinner) findViewById(R.id.spinner);
        adapterCoches miAdaptador = new adapterCoches(this);
        spinner.setAdapter(miAdaptador);
        spinner.setSelection(-1);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                i = pos;
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        final RadioButton botonSeguroNo = (RadioButton) findViewById(R.id.sinSeguro);
        final RadioButton botonSeguroTodoRiesgo = (RadioButton) findViewById(R.id.seguroTodoRiesgo);
        final CheckBox checkBoxAire = (CheckBox) findViewById(R.id.aire);
        final CheckBox checkBoxGPS = (CheckBox) findViewById(R.id.gps);
        final CheckBox checkBoxDVD = (CheckBox) findViewById(R.id.dvd);

        Button factura = (Button) findViewById(R.id.factura);
        Button dibujos = (Button) findViewById(R.id.dibujos);
        final RadioGroup tipoSeguro = (RadioGroup) findViewById(R.id.tipoSeguro);
        final TextView resultado = (TextView) findViewById(R.id.resultado);
        final EditText horas = (EditText) findViewById(R.id.numHoras);
        final EditText marca = (EditText) findViewById(R.id.marca);
        final EditText modelo = (EditText) findViewById(R.id.modelo);
        final Object texto = spinner.getSelectedItem();
        factura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean bandera = false;
                if(botonSeguroNo.isChecked()){
                    alquiler[i].setSeguro("Sin seguro");
                    bandera = true;
                }else if(botonSeguroTodoRiesgo.isChecked()){
                    alquiler[i].setSeguro("Seguro a Todo Riesgo");
                    bandera = true;
                }else{
                    Toast.makeText(getApplicationContext(), "Seleccione un tipo de seguro",Toast.LENGTH_SHORT).show();
                    bandera = false;
                }


                if(checkBoxAire.isChecked()){
                    alquiler[i].setExtras(true);
                }else if(checkBoxDVD.isChecked()){
                    alquiler[i].setExtras(true);
                }
                else if(checkBoxGPS.isChecked()) {
                    alquiler[i].setExtras(true);
                }
                else {
                    alquiler[i].setExtras(false);
                }

                String comp = horas.getText().toString();
                if(comp.equalsIgnoreCase("")){
                    Toast.makeText(getApplicationContext(), "Introduzca el tiempo de alquiler",Toast.LENGTH_SHORT).show();
                    bandera = false;
                }else{
                    alquiler[i].setHorasAlq(Float.parseFloat(horas.getText().toString()));
                }
                if (bandera == true){
                    Intent intent = new Intent(Pantalla1.this, Pantalla2.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("Horas", alquiler[i]);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }

                String comp1 = marca.getText().toString();
                if(comp1.equalsIgnoreCase("")){
                    Toast.makeText(getApplicationContext(), "Introduzca la marca del coche",Toast.LENGTH_SHORT).show();
                    bandera = false;
                }else{
                    alquiler[i].setMarca((marca.getText().toString()));
                }
                if (bandera == true){
                    Intent intent = new Intent(Pantalla1.this, Pantalla2.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("Marca", alquiler[i]);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }

                String comp2 = modelo.getText().toString();
                if(comp2.equalsIgnoreCase("")){
                    Toast.makeText(getApplicationContext(), "Introduzca el modelo del coche",Toast.LENGTH_SHORT).show();
                    bandera = false;
                }else{
                    alquiler[i].setModelo((modelo.getText().toString()));
                }
                if (bandera == true){
                    Intent intent = new Intent(Pantalla1.this, Pantalla2.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("Modelo", alquiler[i]);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }

                resultado.setText(alquiler[i].toString());
            }
        });
        dibujos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Pantalla1.this, dibujitos.class);
                startActivity(intent);
            }
        });

    }
    class adapterCoches extends ArrayAdapter{
        Activity context;
        adapterCoches(Activity context){
            super(context, R.layout.activity_main, alquiler);
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
            View item = inflater.inflate(R.layout.activity_main, null);
            TextView marca = (TextView) item.findViewById(R.id.marca);
            TextView modelo = (TextView) item.findViewById(R.id.modelo);
            TextView horas = (TextView) item.findViewById(R.id.numHoras);
            marca.setText(alquiler[position].getMarca());
            modelo.setText(alquiler[position].getModelo());
            horas.setText(String.valueOf(alquiler[position].getPrecio()));
            return item;
        }
    }
}