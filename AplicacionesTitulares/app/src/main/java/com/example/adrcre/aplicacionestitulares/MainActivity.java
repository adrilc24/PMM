package com.example.adrcre.aplicacionestitulares;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button myButton = (Button) findViewById(R.id.miBoton);
        miBoton.setOnClickLister( (v)) {
            String auxEdad = miEdad.getText().toString();
            if (auxEdad == ""; auxEdad="0");
            p= new Persona (miNombre.getText().toString(), miApellido.getText().toString());
            showToast(p.toString());
            Intent miIntent = new Intent(MainActivity.this, Pantalla2.class);
            Bundle miBundle = new Bundle();

            miBundle.putSerializable("CLAVEper", p);
            miIntent.putExtras(miBundle);
            startActivity(miIntent);

        }
    }
}
