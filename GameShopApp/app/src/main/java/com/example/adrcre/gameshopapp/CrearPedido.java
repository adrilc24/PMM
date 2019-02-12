package com.example.adrcre.gameshopapp;

import android.app.Activity;
import android.content.Context;
import android.database.SQLException;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class CrearPedido  extends Activity {
    TextView myNombre = null;
    TextView myPrecio = null;
    TextView myMarca = null;
    TextView myId_juego = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear);
        Button btnGuardar = (Button) findViewById(R.id.guardar);
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                setResult(RESULT_OK);
                saveDatos();
                finish();
            }
        });

        myNombre = (TextView) findViewById(R.id.nombre);
        myPrecio = (TextView) findViewById(R.id.precio);
        myMarca = (TextView) findViewById(R.id.marca);
        myId_juego = (TextView) findViewById(R.id.id_juego);

    }


    //Los datos que pongamos aquí se guardarán tras pulsar el botón de guardar
    protected void saveDatos() {
        String nombre = myNombre.getText().toString();
        String precio = myPrecio.getText().toString();
        String marca = myMarca.getText().toString();
        String id_juego = myId_juego.getText().toString();

        //Se llama a la BD para insertar los datos obtenidos
        try {
            MainActivity.myDbHelper.open();
            MainActivity.myDbHelper.insertJuegos(nombre, precio,marca, id_juego);
            MainActivity.myDbHelper.close();
        } catch (SQLException e) {
            e.printStackTrace();
            showMessage(R.string.dataError);
        }
    }


    private void showMessage(int msg){
        Context context = getApplicationContext();
        CharSequence text = getResources().getString(msg);
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }
}
