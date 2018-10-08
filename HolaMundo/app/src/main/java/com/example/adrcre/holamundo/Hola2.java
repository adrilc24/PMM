package com.example.adrcre.holamundo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ImageButton;

public class Hola2 extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hola2);

        final TextView elSaludo2 = (TextView) findViewById(R.id.miLbl);
        final ImageButton miImagen = (ImageButton) findViewById(R.id.miImg);

        Bundle miBundleRecoger=getIntent().getExtras();
        elSaludo2.setText(miBundleRecoger.getString("paso"));

        miImagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent volver=new Intent(Hola2.this, Hola3.class);
                startActivity(volver);
            }
        });
    }
}
