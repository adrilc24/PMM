package com.example.adrcre.dibujitos;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new Dibujo(this));
        Toast.makeText(this,"Dibujar Circulo", Toast.LENGTH_SHORT).show();
    }


    class Dibujo extends View {
        private ShapeDrawable miDrawable;
        public Dibujo(Context context) {
            super(context);

        }

        @Override
        protected void onDraw(Canvas lienzo) {



            Paint pincel = new Paint();
            pincel.setColor(Color.WHITE);
            pincel.setStrokeWidth(8);0
            pincel.setStyle(Paint.Style.STROKE);
            Paint pincel2 = new Paint();
            pincel2.setColor(Color.RED);
            pincel2.setStrokeWidth(8);
            pincel2.setStyle(Paint.Style.STROKE);
            lienzo.drawCircle(672,1066,712, pincel);
            pincel2.setTextSize(60);
            int anchura = getWidth();
            int altura = getHeight();
            String mensaje = "("+anchura+" , "+altura+")";
            lienzo.drawText(mensaje, 500,1000,pincel2);



            int x = 672; int y = 1066;
            int ancho = 1440; int alto = 2112;
            miDrawable = new ShapeDrawable(new OvalShape());
            miDrawable.getPaint().setColor(0xffffffff);
            miDrawable.setBounds(x, y, x+ancho, y + ancho);
            miDrawable.draw(lienzo);



        }
    }
}
