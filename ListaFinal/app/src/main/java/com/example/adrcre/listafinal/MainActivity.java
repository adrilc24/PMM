package com.example.adrcre.listafinal;

import android.app.Activity;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Titular[] datos = new Titular[]{
            new Titular("Título 1", "Subtítulo largo 1", R.drawable.img1),
            new Titular("Título 2", "Subtítulo largo 2", R.drawable.img2),
            new Titular("Título 3", "Subtítulo largo 3", R.drawable.img3)

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AdaptadorTitulares adaptador = new AdaptadorTitulares(this);
        ListView lstOpciones = (ListView) findViewById(R.id.LstOpciones);
        lstOpciones.setAdapter(adaptador);

        lstOpciones.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView arg0, View arg1, int position, long id) {
                String mensaje = "Título: " + datos[position].getTitulo() + " Subtítulo: " + datos[position].getSubtitulo();
                Toast.makeText(getApplicationContext(), mensaje, Toast.LENGTH_LONG).show();
            }
        });
    }

    static class ViewHolder {
        TextView lblTitulo;
        TextView lblSubtitulo;
        ImageView lblImagen;
    }



    class AdaptadorTitulares extends ArrayAdapter {
        Activity context;
        AdaptadorTitulares(Activity context) {
            super(context, R.layout.titular, datos);
            this.context = context;
        }
        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        public View getView(int position, View convertView, ViewGroup parent) {
            View item = convertView;
            ViewHolder holder;
            if(item == null)
            { LayoutInflater inflater = context.getLayoutInflater();
                item = inflater.inflate(R.layout.titular, null);

                holder = new ViewHolder();
                holder.lblTitulo = (TextView)item.findViewById(R.id.itvTitulo);
                holder.lblSubtitulo = (TextView)item.findViewById(R.id.itvSubtitulo);
                holder.lblImagen = (ImageView)item.findViewById(R.id.ivlImagen);

                item.setTag(holder);
            }
            else
            {
                holder = (ViewHolder)item.getTag(); }
            holder.lblTitulo.setText(datos[position].getTitulo());
            holder.lblSubtitulo.setText(datos[position].getSubtitulo());
            holder.lblImagen.setBackground(getDrawable(datos[position].getImagen()));
            return(item);
        }
    }
}
