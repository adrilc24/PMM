package com.example.adrcre.gameshopapp;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.database.SQLException;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

public class RealizarPedidos extends Activity {

    TextView myNombre = null;
    TextView myPrecio = null;
    TextView myMarca = null;
    TextView myId_juego = null;

    public Juegos[] juegos = new Juegos[]{

            new Juegos(1, "Bloodborne", "Sony", 20, 0, true, R.drawable.bloodborne),
            new Juegos(2, "GOW", "Sony", 30, 0, true, R.drawable.gow),
            new Juegos(3, "Red Dead 2", "Rockstar", 59, 0, true, R.drawable.rdr2),
            new Juegos(4, "Spider-Man", "Sony", 40, 0, true, R.drawable.spiderman),
            new Juegos(5, "Mario", "Nintendo", 50, 0, true, R.drawable.mario),
            new Juegos(6, "Forza", "MS", 15, 0, true, R.drawable.forza),
            new Juegos(7, "Halo", "MS", 10, 0, true, R.drawable.halo),
    };
    public Juegos [] myJuego;
    public Spinner spinner;
    public int i = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        spinner = (Spinner) findViewById(R.id.spinner);
        AdaptadorSpinner miAdaptador = new AdaptadorSpinner(this);
        spinner.setAdapter(miAdaptador);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                i = pos;
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        final RadioButton radBotNuevo = (RadioButton) findViewById(R.id.radNuevo);
        final RadioButton radBotSegunda = (RadioButton) findViewById(R.id.radSegunda);
        final Button botPrecFinal = (Button) findViewById(R.id.botPrecFinal);
        final Button botFactura = (Button) findViewById(R.id.botFactura);
        final TextView precioFinal = (TextView) findViewById(R.id.precFinal);

        //Boton que mostrará precio final del producto
        botPrecFinal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                juegos[i].setPrecioFinal(0);
                boolean flag = false;
                if(radBotNuevo.isChecked()){
                    juegos[i].setEstado(false);
                    flag = true;
                }else if(radBotSegunda.isChecked()){
                    juegos[i].setEstado(true);
                    flag = true;
                } else {
                    flag = false;
                }
                float precioJuego = juegos[i].getPrecio();
                if(juegos[i].isEstado() == false){
                    precioJuego = (precioJuego + juegos[i].getPrecioFinal());
                    juegos[i].setPrecioFinal(precioJuego);
                }else{
                    precioJuego = (precioJuego + juegos[i].getPrecioFinal() - 5);
                    juegos[i].setPrecioFinal(precioJuego);
                }
                precioFinal.setText(String.valueOf(juegos[i].getPrecioFinal())+ "€");
            }
        });


        //Botones para guardar juegos
        botFactura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RealizarPedidos.this, MainActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("Juego", juegos[i]);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        botFactura.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                setResult(RESULT_OK);
                saveDatos();
                finish();
            }
        });

    }
    //Metodo para guardar datos
    protected void saveDatos() {

        myNombre = (TextView) findViewById(R.id.spinNombre);
        myPrecio = (TextView) findViewById(R.id.spinPrecio);
        myMarca = (TextView) findViewById(R.id.spinMarca);
        myId_juego = (TextView) findViewById(R.id.spinID);

        String id = myId_juego.getText().toString();
        String precio = myPrecio.getText().toString();
        String marca = myMarca.getText().toString();
        String nombre = myNombre.getText().toString();

        try {
            MainActivity.myDbHelper.open();
            MainActivity.myDbHelper.insertJuegos(nombre, precio, marca, id);
            MainActivity.myDbHelper.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Clase para adaptar los objetos del spinner
    class AdaptadorSpinner extends ArrayAdapter {
        Activity context;
        AdaptadorSpinner(Activity context){
            super(context, R.layout.spinner_juegos, juegos);
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
            View item = inflater.inflate(R.layout.spinner_juegos, null);

            TextView id = (TextView) item.findViewById(R.id.spinID);
            TextView precio = (TextView) item.findViewById(R.id.spinPrecio);
            ImageView imagen = (ImageView) item.findViewById(R.id.spinIMG);
            TextView marca = (TextView) item.findViewById(R.id.spinMarca);
            TextView nombre = (TextView) item.findViewById(R.id.spinNombre);

            id.setText(String.valueOf(juegos[pos].getId()));
            nombre.setText(juegos[pos].getNombre());
            marca.setText(juegos[pos].getMarca());
            precio.setText(String.valueOf(juegos[pos].getPrecio()));
            imagen.setBackground(getDrawable(juegos[pos].getImg()));

            return item;
        }
    }


    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflate  = getMenuInflater();
        inflate.inflate(R.menu.menu, menu);
        return true;
    }

}
