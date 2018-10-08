package com.example.adrcre.aholamundo;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainHolaMundo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_hola_mundo);

        final EditText miTexto= (EditText)findViewById(R.id.miTxt);
        final Button miBoton= (Button)findViewById(R.id.miBtn);
        final TextView elSaludo= (TextView)findViewById(R.id.miLbl);
        miBoton.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String mensajePaso= "Saludos cordiales " + miTexto.getText();
                elSaludo.setText(mensajePaso);
                Intent mio = new Intent(MainHolaMundo.this, pantalla2.class);
                startActivity(mio);
            }
        });

        Toast.makeText(this,  "esto es un TOAST", Toast.LENGTH_SHORT).show();
        new AlertDialog.Builder(this).setMessage("el texto").setPositiveButton(android.R.string.ok, null).show();
    }
}
