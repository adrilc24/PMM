package com.example.adrcre.radiobutton.;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.adrcre.radiobutton.R;


public class RadioButton extends AppCompatActivity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radio_button);
        final TextView lblMensaje = (TextView) findViewById(R.id.LblSeleccion);
        final RadioGroup rg = (RadioGroup) findViewById(R.id.gruporb);

        rg.clearCheck();
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                String textoOpcion="";
                if( group.getCheckedRadioButtonId()==R.id.radio1)
                    textoOpcion +="OPCION 1 con ID:" + checkedId;
                if( group.getCheckedRadioButtonId()==R.id.radio2)
                    textoOpcion +="OPCION 2 con ID:" + checkedId ;
                lblMensaje.setText(textoOpcion);
            }
        });
    }
}
