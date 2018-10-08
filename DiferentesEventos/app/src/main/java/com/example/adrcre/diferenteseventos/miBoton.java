package com.example.adrcre.diferenteseventos;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Toast;

public class miBoton extends android.support.v7.widget.AppCompatButton implements
        View.OnClickListener {
    Context ctx=null;
    public miBoton(Context context) {
        super(context);
        ctx=context;
        this.setOnClickListener(this);//recoger evento
    }
    //cuando se cree desde un recurso XML
    public miBoton(Context context, AttributeSet attr){
        super(context,attr);
        ctx=context;
        this.setOnClickListener(this);
    }
    //cuando se cree desde un recurso XML
    public miBoton(Context context, AttributeSet attr,
                        int defaultStyles){
        super(context, attr, defaultStyles);
        ctx=context;
        this.setOnClickListener(this);
    }
    @Override
    public void onClick(View v){
        Toast.makeText(ctx, "Pulsado botón Uno",
                Toast.LENGTH_SHORT).show();
    }
}
