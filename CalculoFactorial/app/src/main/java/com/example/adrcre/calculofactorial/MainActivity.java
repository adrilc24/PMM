package com.example.adrcre.calculofactorial;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText texto1 = (EditText) findViewById(R.id.texto1);
        final Button boton1 = (Button) findViewById(R.id.boton1);
        final TextView textView = (TextView) findViewById(R.id.textView);

        boton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calcularOperacion();
                String aux="El resultado es: "+ textView.getText();
                textView.setText(aux);
                Intent mio=new Intent(MainActivity.this, Pantalla2.class);
                Bundle miBundle=new Bundle();
                miBundle.putString("paso",aux);
                mio.putExtras(miBundle);
                startActivity(mio);
            }

            public void calcularOperacion(){
                int n = Integer.parseInt(texto1.getText().toString());
                textView.append(n + "! = ");
                int res = factorial(n);
                textView.append(res + "\n");
            }

            public int factorial (int a) {
                int res = a;
                for (int i = 2; i < a; i++){
                    res *=i;
                }
                return res;
            }

        });
    }
}
