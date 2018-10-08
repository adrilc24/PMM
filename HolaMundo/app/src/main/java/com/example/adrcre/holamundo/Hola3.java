package com.example.adrcre.holamundo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Hola3 extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hola3);

        final EditText miTexto = (EditText) findViewById(R.id.miTxt);
        final Button miBoton = (Button) findViewById(R.id.miBtn);
        final TextView elSaludo = (TextView) findViewById(R.id.miLbl);


        miBoton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String aux="Te saludo "+ miTexto.getText();
                elSaludo.setText(aux);
                Intent mio=new Intent(Hola3.this, Hola2.class);
                Bundle miBundle=new Bundle();
                miBundle.putString("paso",aux);
                mio.putExtras(miBundle);
                startActivity(mio);
            }
        });
    }
}