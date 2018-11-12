package com.example.adrcre.plantilla;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView lblMensaje = (TextView)findViewById(R.id.LblSeleccion);
        final RadioGroup rg = (RadioGroup)findViewById(R.id.gruporb);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            public void onCheckedChanged(RadioGroup group, int checkedId) {
                lblMensaje.setText("ID opción seleccionada: " + checkedId);
            }
        });
    }
}