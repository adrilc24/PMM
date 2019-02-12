package com.example.adrcre.gameshopapp;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends ListActivity {

    public static final int NEW_ITEM = 1;
    public static final int SHOW_ITEM = 2;

    public static DBHelper myDbHelper = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button botStartCrearP = (Button) findViewById(R.id.botCrear);
        Button botUpdate= (Button) findViewById(R.id.botActualizar);
        Button botBorrar = (Button)findViewById(R.id.botBorrar);
        Button botonFactura=findViewById(R.id.botJuegos);

        myDbHelper = new DBHelper(this);
        myDbHelper.open();

        try {
            introDatos();
        } catch (android.database.SQLException e) {
            e.printStackTrace();
        }

        //Botón para actualizar la pantalla
        botUpdate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                startActivityForResult(intent, SHOW_ITEM);
            }
        });

        //Botón que lanza el Drop para borrar tablas y actualiza la pantalla
        botBorrar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                myDbHelper.drop();
                Toast.makeText(MainActivity.this, "Datos Borrados", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                startActivityForResult(intent, SHOW_ITEM);
            }
        });

        //Botón para crear un pedido
        botStartCrearP.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent (MainActivity.this,CrearPedido.class);
                startActivityForResult(intent, NEW_ITEM);
            }


        });

        //Botón para pasar a la pantalla de selección de juego mediante spinner
        botonFactura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RealizarPedidos.class);
                startActivity(intent);
            }
        });

    }
    private void showMessage(int message){
        Context context = getApplicationContext();
        CharSequence text = getResources().getString(message);
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    //Introduce los datos del juego en la lista
    private void introDatos() {
        myDbHelper.open();
        Cursor cursor = myDbHelper.getItems();
        ListaDatos listaDatos = null;
        ArrayList<ListaDatos> arrayListaDatos = new ArrayList<ListaDatos>();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex(DBHelper.TABLE_JUEGOS_ID));
            String nombre = cursor.getString(cursor.getColumnIndex(DBHelper.TABLE_JUEGOS_NOMBRE));
            int precio = cursor.getInt(cursor.getColumnIndex(DBHelper.TABLE_JUEGOS_PRECIO));
            int id_juego = cursor.getInt(cursor.getColumnIndex(DBHelper.TABLE_JUEGOS_ID_JUEGO));

            listaDatos = new ListaDatos();
            listaDatos.id = id;
            listaDatos.nombre = nombre;
            listaDatos.precio = precio;
            listaDatos.id_juego = id_juego;

            arrayListaDatos.add(listaDatos);
        }

        cursor.close();
        myDbHelper.close();

        AdaptarDatos objects = new AdaptarDatos(this, R.layout.lista, arrayListaDatos, getLayoutInflater() );
        setListAdapter(objects);
    }

    private class ListaDatos {
        String nombre, marca;
        int id, precio, id_juego;
    }

    private class ListaUsers {
        String nombre, password;
        int id;
    }

    //Clase que obtendrá los datos de los juegos para implementarlos
    private class AdaptarDatos extends ArrayAdapter<ListaDatos> {
        private LayoutInflater inflater;
        private List<ListaDatos> myLista;

        private AdaptarDatos(Context context, int resource, List<ListaDatos> lista, LayoutInflater inflater) {
            super(context, resource, lista);
            this.inflater = inflater;
            this.myLista = lista;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ListaDatos listEntry = myLista.get(position);
            View row = inflater.inflate(R.layout.lista, null);
            TextView nombre = (TextView) row.findViewById(R.id.rNombre);
            ImageView icon = (ImageView) row.findViewById(R.id.rID);

            nombre.setText(listEntry.nombre);

            // Según la id se mostrará la imagen

            icon.setTag(new Integer(listEntry.id));
            switch (listEntry.id_juego) {
                case 1:
                    icon.setImageResource(R.drawable.bloodborne);
                    break;
                case 2:
                    icon.setImageResource(R.drawable.gow);
                    break;
                case 3:
                    icon.setImageResource(R.drawable.rdr2);
                    break;
                case 4:
                    icon.setImageResource(R.drawable.spiderman);
                    break;
                case 5:
                    icon.setImageResource(R.drawable.mario);
                    break;
                case 6:
                    icon.setImageResource(R.drawable.forza);
                    break;
                case 7:
                    icon.setImageResource(R.drawable.halo);
                    break;
                default:
                    icon.setImageResource(R.drawable.spiderman);
                    break;
            }
            return row;
        }

    }
}
