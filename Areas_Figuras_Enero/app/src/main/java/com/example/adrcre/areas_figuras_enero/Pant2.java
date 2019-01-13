package com.example.adrcre.areas_figuras_enero;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.ShapeDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.View;

public class Pant2 extends AppCompatActivity {

    String nombre;

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new DrawForma(this));

        Intent intent = getIntent();
        Figuras figuras = (Figuras) intent.getSerializableExtra("Figura");

        nombre = figuras.getNombre();

    }

    class DrawForma extends View {

        private ShapeDrawable miDrawable;

        public DrawForma(Context c) {
            super(c);
        }

        public DrawForma(Context c, AttributeSet a){
            super(c, a);
        }

        protected void onDraw(Canvas canvas) {

            Paint pincel = new Paint();
            int x = getWidth();
            int y = getHeight();
            int radio;
            radio = 100;



            if(nombre.equalsIgnoreCase("Circulo")){
                super.onDraw(canvas);

                pincel.setColor(Color.BLUE);
                pincel.setStyle(Paint.Style.FILL);
                canvas.drawCircle(x / 2, y / 2, 450, pincel);

            }else if(nombre.equalsIgnoreCase("Triángulo")){
                Path path = new Path();

                pincel.setColor(Color.GREEN);
                pincel.setStyle(Paint.Style.FILL);

                path.moveTo(500, 400);
                path.lineTo(250, 800);
                path.lineTo(750, 800);
                canvas.drawPath(path, pincel);

                canvas.drawPath(path, pincel);
            }else{
                pincel.setColor(Color.RED);
                pincel.setStyle(Paint.Style.FILL);
                canvas.drawRect(200 , 400, 900, 800, pincel);
            }

        }
    }
    }

