package com.example.adrcre.fragmentosdiversos;

import android.app.AlertDialog;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.app.DialogFragment;
import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    int mStackPosition = 1;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);        // BotÃ³n de aÃ±adir fragments
        Button button = (Button) findViewById(R.id.newFragment);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {addFragment();}
        });
        if (savedInstanceState == null) {
            // aÃ±adir el primer fragment
            Fragment fragmentoVariable = SimpleFragment.newInstance(mStackPosition);
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.add(R.id.fragmentShow, fragmentoVariable).commit();
        } else {
            mStackPosition = savedInstanceState.getInt("position");
        }
        Button buttonDialogF = (Button)findViewById(R.id.showDialogF);
        Button buttonDialog = (Button)findViewById(R.id.showDialog);
        buttonDialogF.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {showDialogF();}
        });
        buttonDialog.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {showDialog();}
        });
    }

    void addFragment() {
        mStackPosition++;
        // Instanciamos nuevo Fragment
        Fragment fragmentoVariable = SimpleFragment.newInstance(mStackPosition);
        // Se aÃ±ade el Fragment a la actividad
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.fragmentShow, fragmentoVariable);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.addToBackStack(null);// poneos la transacion a la pila
        ft.commit();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("position", mStackPosition);
    }

    void showDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Selecciona accion a realizar")
                .setPositiveButton("Nuevo",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                addFragment();
                            }
                        }
                )
                .setNegativeButton("Cancelar",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                // no hacer nada
                            }
                        }
                )
                .setNeutralButton("AtrÃ¡s",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                getFragmentManager().popBackStack();
                            }
                        }
                )
                .create().show();
    }
    void showDialogF() {
        DialogFragment fragmentoVariable = MyDialogFragment.newInstance(
                "Cadena de ejemplo como parÃ¡metro");
        fragmentoVariable.show(getFragmentManager(), "dialog");
    }
}
