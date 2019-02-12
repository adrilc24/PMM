package com.example.adrcre.gameshopapp;

import android.app.Activity;
import android.content.Intent;
import android.database.SQLException;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegistroUsers extends Activity {
    private Button btInicioSesion;
    private Button btRegistro;
    private EditText nomUser = null;
    private EditText passUser = null;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user1);


        btInicioSesion = findViewById(R.id.botIniSesion);
        btRegistro = findViewById(R.id.botRegistro);

        final DBHelper dbHelper = new DBHelper(this);

        //Botón para el registro
        btRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nomUser = (EditText) findViewById(R.id.textUsuario);
                passUser = (EditText) findViewById(R.id.textPassword);

                String nombre = nomUser.getText().toString();
                String password = passUser.getText().toString();

                //Si hay texto en los campos a rellenar
                if (!emptyValidation()) {
                    try {
                        //Se conectará a la bd e introducirá los datos del usuario
                        dbHelper.open();
                        dbHelper.insertUsers(nombre, password);
                        dbHelper.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }else{
                    Toast.makeText(RegistroUsers.this, "Campo Vacío", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //Botón para el Log In
        btInicioSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                nomUser = (EditText) findViewById(R.id.textUsuario);
                passUser = (EditText) findViewById(R.id.textPassword);

                String nombre = nomUser.getText().toString();
                String password = passUser.getText().toString();
                //Si hay texto en los campos a rellenar
                if (!emptyValidation()) {
                    //Comprobará que el usuario existe y coincide con la contraseña
                    Usuario user = dbHelper.getUsers(nombre, password);
                    if (user != null) {
                        //Si el usuario y la pwd son correctos pasará a la pantalla MainActivity
                        Bundle mBundle = new Bundle();
                        mBundle.putString("user", user.getUsername());
                        Intent intent = new Intent(RegistroUsers.this, MainActivity.class);
                        intent.putExtras(mBundle);
                        startActivity(intent);
                        Toast.makeText(RegistroUsers.this, "Bienvenido " + user.getUsername(), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(RegistroUsers.this, "Este usuario no existe", Toast.LENGTH_SHORT).show();
                        passUser.setText("");
                    }
                }else{
                    Toast.makeText(RegistroUsers.this, "Campo vacío", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    //Para determinar si los campos de texto están vacios
    private boolean emptyValidation() {
        if (TextUtils.isEmpty(nomUser.getText().toString()) || TextUtils.isEmpty(passUser.getText().toString())) {
            return true;
        }else {
            return false;
        }
    }
}
