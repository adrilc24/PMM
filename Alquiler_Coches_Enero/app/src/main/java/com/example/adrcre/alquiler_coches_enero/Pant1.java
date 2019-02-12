package com.example.adrcre.alquiler_coches_enero;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Pant1 extends AppCompatActivity {

    public Modelos[] coches = new Modelos[]{

            new Modelos("Batmovil","DC",20000,0, 0, false, false, false, false, R.drawable.bat),
            new Modelos("Desatranques","Jaén",450,0, 0, false, false, false, false, R.drawable.jaen),
            new Modelos("Tronco","Móvil",5,0,0, false, false, false, false, R.drawable.pica),
    };
    public Modelos [] micoche;
    public Spinner spinner;
    public int i = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pant1);
        spinner = (Spinner) findViewById(R.id.spinner);
        Zonas miAdaptador = new Zonas(this);
        spinner.setAdapter(miAdaptador);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                i = pos;
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        final RadioButton botSinSeg = (RadioButton) findViewById(R.id.radNo);
        final RadioButton botConSeg = (RadioButton) findViewById(R.id.radSi);
        final CheckBox checkAire = (CheckBox) findViewById(R.id.checkAire);
        final CheckBox checkGps = (CheckBox) findViewById(R.id.checkGps);
        final CheckBox checkRadio = (CheckBox) findViewById(R.id.checkRadio);
        final Button botPrecFinal = (Button) findViewById(R.id.botPrecFinal);
        final Button botFactura = (Button) findViewById(R.id.botFactura);

        final TextView precioFinal = (TextView) findViewById(R.id.precFinal);
        final EditText tiempoUso = (EditText) findViewById(R.id.tiempo);

        botPrecFinal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                coches[i].setPrecioTot(0);
                boolean flag = false;
                if(botSinSeg.isChecked()){
                    coches[i].setSeguro(false);
                    flag = true;
                }else if(botConSeg.isChecked()){
                    coches[i].setSeguro(true);
                    flag = true;
                } else {
                    flag = false;
                }
                if(checkAire.isChecked()){
                    coches[i].setAire(true);
                    coches[i].setPrecioTot(coches[i].getPrecioTot() + 50);
                }
                if(checkGps.isChecked()){
                    coches[i].setGps(true);
                    coches[i].setPrecioTot(coches[i].getPrecioTot() + 50);
                }
                if(checkRadio.isChecked()){
                    coches[i].setRadio(true);
                    coches[i].setPrecioTot(coches[i].getPrecioTot() + 50);
                }

                String comprobarTiempo = tiempoUso.getText().toString();
                if(comprobarTiempo.equalsIgnoreCase("")){
                    Toast.makeText(getApplicationContext(), "Tiempo sin rellenar",Toast.LENGTH_SHORT).show();
                    flag = false;
                }else{
                    coches[i].setTiempo(Integer.parseInt(tiempoUso.getText().toString()));
                }


                float tiempoFinal = 0;
                tiempoFinal = coches[i].getPrecio() * Float.parseFloat(tiempoUso.getText().toString());
                float tiempoPorcentaje;

                if(coches[i].isSeguro() == true){
                    tiempoPorcentaje = (tiempoFinal + coches[i].getPrecioTot()) * 20/100;
                    tiempoFinal = tiempoFinal + tiempoPorcentaje;
                    coches[i].setPrecioTot(tiempoFinal);
                }else{
                    tiempoFinal = (tiempoFinal + coches[i].getPrecioTot());
                    coches[i].setPrecioTot(tiempoFinal);
                }
                precioFinal.setText(String.valueOf(coches[i].getPrecioTot())+ "€");
            }
        });

        botFactura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Pant1.this, Pant2.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("Coche", coches[i]);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    class Zonas extends ArrayAdapter {
        Activity context;
        Zonas(Activity context){
            super(context, R.layout.datos, coches);
            this.context = context;

        }
        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
        public View getDropDownView(int position, View convertView, ViewGroup parent){
            View vista = getView(position, convertView, parent);
            return vista;
        }
        @TargetApi(Build.VERSION_CODES.LOLLIPOP)
        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
        public View getView(int pos, View conView, ViewGroup viewGroup){
            LayoutInflater inflater = context.getLayoutInflater();
            View item = inflater.inflate(R.layout.datos, null);

            TextView precio = (TextView) item.findViewById(R.id.cochePrecio);
            ImageView imagen = (ImageView) item.findViewById(R.id.imgZonas);
            TextView marca = (TextView) item.findViewById(R.id.cocheMarca);
            TextView modelo = (TextView) item.findViewById(R.id.cocheModelo);


            modelo.setText(coches[pos].getModelo());
            marca.setText(coches[pos].getMarca());
            precio.setText(String.valueOf(coches[pos].getPrecio()));
            imagen.setBackground(getDrawable(coches[pos].getImg()));
            return item;
        }
    }


    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflate  = getMenuInflater();
        inflate.inflate(R.menu.menu, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.menDib:
                Intent intent1 = new Intent(Pant1.this, dibujo.class);
                startActivity(intent1);
                return true;

            case R.id.menAce:
                Intent intent2 = new Intent(Pant1.this, acerca.class);
                startActivity(intent2);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
