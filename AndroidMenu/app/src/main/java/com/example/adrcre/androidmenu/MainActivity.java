package com.example.adrcre.androidmenu;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class
MainActivity extends Activity {

    private TextView lblMensaje;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lblMensaje = (TextView)findViewById(R.id.LblMensaje);
        registerForContextMenu(lblMensaje);

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_principal, menu);

        return true;
    }

    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_ctx_imagenes, menu);
    }

   public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.MnuOpc1:
                lblMensaje.setText("Opción 1 pulsada!");
                return true;
            case R.id.MnuOpc2:
                lblMensaje.setText("Opción 2 pulsada!");
                return true;
            case R.id.MnuOpc3:
                lblMensaje.setText("Opción 3 pulsada!");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
