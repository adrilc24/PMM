package com.example.adrcre.examenej1;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.provider.MediaStore;
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
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import org.w3c.dom.Text;
public class Pantalla1 extends AppCompatActivity {
    public zona[] zona = new zona[]{
            new zona("Asia", "China", 20,0,"", false, false, android.R.drawable.paquete),
            new zona("Africa", "Uganda", 30,0, "",false, false, android.R.drawable.paquete),
            new zona("America", "Ohio", 10,0, "",false, false, android.R.drawable.paquete)
    };
    public zona [] mizona;
    public int indice = 0;
    public Spinner spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla1);
        spinner = (Spinner) findViewById(R.id.spinner);
        AdaptadorZonas miAdaptador = new AdaptadorZonas(this);
        spinner.setAdapter(miAdaptador);
        spinner.setSelection(-1);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                indice = pos;
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        final RadioButton botonTarifaNormal = (RadioButton) findViewById(R.id.tarifaNormal);
        final RadioButton botonTarifaUrgente = (RadioButton) findViewById(R.id.tarifaUrgente);
        final CheckBox checkBoxRegalo = (CheckBox) findViewById(R.id.envolver);
        final CheckBox checkBoxTarjeta = (CheckBox) findViewById(R.id.tarjeta);
        Button calcular = (Button) findViewById(R.id.calcular);
        final RadioGroup tarifas = (RadioGroup) findViewById(R.id.tarifas);
        final TextView resultado = (TextView) findViewById(R.id.resultado);
        final EditText peso = (EditText) findViewById(R.id.peso);
        final Object texto = spinner.getSelectedItem();
        calcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean flag = false;
                if(botonTarifaNormal.isChecked()){
                    zona[indice].setEnvio("Tarifa Normal");
                    flag = true;
                }else if(botonTarifaUrgente.isChecked()){
                    zona[indice].setEnvio("Tarifa Urgente");
                    flag = true;
                }else{
                    Toast.makeText(getApplicationContext(), "Debes seleccionaruna forma de envio",Toast.LENGTH_SHORT).show();
                    flag = false;
                }
                if(checkBoxRegalo.isChecked()){
                    zona[indice].setRegalo(true);
                }else if(checkBoxTarjeta.isChecked()){
                    zona[indice].setDedicatoria(true);
                }
                String comparador = peso.getText().toString();
                if(comparador.equalsIgnoreCase("")){
                    Toast.makeText(getApplicationContext(), "Debes introducir un peso",Toast.LENGTH_SHORT).show();
                    flag = false;
                }else{
                    zona[indice].setPeso(Float.parseFloat(peso.getText().toString()));
                }
                if (flag == true){
                    Intent intent = new Intent(Pantalla1.this, Pantalla2.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("Objeto", zona[indice]);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
                resultado.setText(zona[indice].toString());
            }
        });
    }
    class AdaptadorZonas extends ArrayAdapter{
        Activity context;
        AdaptadorZonas(Activity context){
            super(context, R.layout.activity_zona, zona);
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
            View item = inflater.inflate(R.layout.activity_zona, null);
            TextView continente = (TextView) item.findViewById(R.id.continente);
            TextView nombre = (TextView) item.findViewById(R.id.zona);
            TextView precio = (TextView) item.findViewById(R.id.precio);
            ImageView imagen = (ImageView) item.findViewById(R.id.imagenSpinner);
            continente.setText(zona[position].getContinente());
            nombre.setText(zona[position].getNombre());
            precio.setText(String.valueOf(zona[position].getPrecio()));
            imagen.setBackground(getDrawable(zona[position].getImagen()));
            return item;
        }
    }
}

