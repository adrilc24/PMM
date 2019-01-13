package com.example.adrcre.alquiler_coches_enero;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class Pant2 extends AppCompatActivity  implements TimePickerDialog.OnTimeSetListener{

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public Modelos facturaCoches;

    public int hora;
    public int minutos;
    public int REQUEST_CODE = 1;
    DialogFragment dialogFragment = new fragmento_Hora();


    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        TextView tiempo = (TextView) findViewById(R.id.horaTPicker);
        tiempo.setText("Hora: " + hourOfDay + ". Minutos: " + minute);
        hora = hourOfDay;
        minutos = minute;
        Intent intent = new Intent(Pant2.this, Pant1.class);
        Bundle bundle = new Bundle();
        bundle.putInt("hora", hora);
        bundle.putInt("minutos", minutos);
        intent.putExtras(bundle);
        Toast.makeText(getApplicationContext(),"Resultado correcto pantalla2", Toast.LENGTH_SHORT).show();
        setResult(RESULT_OK, intent);
        startActivityForResult(intent, REQUEST_CODE);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pant2);
        Intent intent = getIntent();
        final TextView mod = (TextView) findViewById(R.id.mod);
        final TextView totalExtras = (TextView) findViewById(R.id.marc);
        final TextView tiempoTotal = (TextView) findViewById(R.id.time);
        final TextView seguro = (TextView) findViewById(R.id.seg);
        final TextView precio = (TextView) findViewById(R.id.price);
        final ImageView img = (ImageView) findViewById(R.id.img2);
        final Button botHora = (Button) findViewById(R.id.horaTPicker);

        facturaCoches = (Modelos) intent.getSerializableExtra("Coche");

        botHora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogFragment.show(getSupportFragmentManager(), "time picker");
            }
        });

        float precioFinal = facturaCoches.getPrecioTot();
        mod.setText(facturaCoches.getModelo());
        tiempoTotal.setText(String.valueOf(facturaCoches.getPrecio()));
        if(facturaCoches.isSeguro() == true){
            seguro.setText("Con seguro");
        }else{
            seguro.setText("Sin seguro");
        }

        precio.setText(String.valueOf(facturaCoches.getPrecioTot()));
        img.setBackground(getDrawable(facturaCoches.getImg()));
        registerForContextMenu(img);

        float extras = 0;
        precio.setText(String.valueOf(precioFinal));
        if(facturaCoches.isGps() == true){
            extras = extras +  50;
        }
        if(facturaCoches.isAire() == true){
            extras = extras + 50;
        }
        if(facturaCoches.isRadio() == true){
            extras = extras + 50;
        }
        totalExtras.setText(String.valueOf(extras));
    }
}
