package com.example.adrcre.spinnerdoble;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Spinner spinnerAutores;
    Spinner spinnerLibros;
    int i;

    Directores[] directores = new Directores[]{
            new Directores("Tarantino"),
            new Directores("Spielberg"),
            new Directores("Kubrick")
    };

    Peliculas[][] peliculas = new Peliculas[][]{

            {new Peliculas("Kill Bill", 2005, R.drawable.killbill), new Peliculas("Django", 2012, R.drawable.django), new Peliculas("Pulp Fiction", 2007, R.drawable.pulp)},
            {new Peliculas("Tiburon", 1976, R.drawable.tiburao), new Peliculas("E.T.", 1991, R.drawable.et), new Peliculas("Indiana Jones", 1987, R.drawable.indi)},
            {new Peliculas("El Resplandor", 1975, R.drawable.resplandor), new Peliculas("La Naranja Mecanica", 1985, R.drawable.naranja), new Peliculas("2001: A Space Odyssey", 2001, R.drawable.odisea)},
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinnerAutores = (Spinner) findViewById(R.id.spinDirectores);
        AdaptarDirectores adaptarDirectores = new AdaptarDirectores(this);
        spinnerAutores.setAdapter(adaptarDirectores);
        spinnerAutores.setSelection(-1);
        spinnerAutores.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                i = pos;
                spinnerLibros.setSelection(-1);
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        spinnerLibros = (Spinner) findViewById(R.id.spinPelis);
        AdaptarPelis adaptarPelis = new AdaptarPelis(this);
        spinnerLibros.setAdapter(adaptarPelis);
        spinnerLibros.setSelection(-1);

    }
    class AdaptarDirectores extends ArrayAdapter {
        Activity context;
        AdaptarDirectores(Activity context){
            super(context, R.layout.spinner_directores, directores);
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
            View item = inflater.inflate(R.layout.spinner_directores, null);

            TextView nombre = (TextView) item.findViewById(R.id.spinPelisTitulo);

            nombre.setText(directores[position].getNombre());
            return item;
        }
    }
    class AdaptarPelis extends ArrayAdapter {
        Activity context;
        AdaptarPelis(Activity context){
            super(context, R.layout.spinner_peliculas, peliculas[i]);
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
            View item = inflater.inflate(R.layout.spinner_peliculas, null);

            TextView titulo = (TextView) item.findViewById(R.id.spinPelisTitulo);
            TextView year = (TextView) item.findViewById(R.id.spinPelisYear);
            ImageView img = (ImageView) item.findViewById(R.id.spinPelisImg);

            titulo.setText(peliculas[i][position].getTitulo());
            year.setText(String.valueOf(peliculas[i][position].getYear()));
            img.setBackground(getDrawable(peliculas[i][position].getImg()));

            //img.setBackground(getDrawable([i][position].getImg());

            //ImageView img = (ImageView) item.findViewById(R.id.imgFiguraSpinner);

            //nombre.setText(figuras[position].getNombre());
            //img.setBackground(getDrawable(figuras[position].getImg()));


            return item;
        }
    }
}
