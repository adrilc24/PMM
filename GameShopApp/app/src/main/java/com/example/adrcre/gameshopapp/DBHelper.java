package com.example.adrcre.gameshopapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

//Clase de la BD
public class DBHelper {
    private Context myContext = null;
    private DataBaseHelperInternal myDBHelper = null;
    private SQLiteDatabase myDataBase = null;
    private static final String DATABASE_NAME = "Juegos";
    private static final int DB_VERSION = 8;

    // Tablas de la aplicación
    //Tabla de Juegos
    private static final String TABLE_NAME_JUEGOS = "Juegos";
    public static final String TABLE_JUEGOS_ID = "id";
    public static final String TABLE_JUEGOS_NOMBRE = "nombre";
    public static final String TABLE_JUEGOS_MARCA = "marca";
    public static final String TABLE_JUEGOS_PRECIO = "precio";
    public static final String TABLE_JUEGOS_ID_JUEGO = "id_juego";
    //Tabla Pedidos
    private static final String TABLE_NAME_PEDIDO = "pedido";
    public static final String TABLE_PEDIDO_ID = "id";
    public static final String TABLE_PEDIDO_NOMBRE = "nombre";
    public static final String TABLE_PEDIDO_PRECIO = "precio";
    //Tabla Usuarios
    private static final String TABLE_NAME_USERS = "usuarios";
    public static final String TABLE_USERS_ID = "id";
    public static final String TABLE_USERS_NOMBRE = "nombre";
    public static final String TABLE_USERS_PWD = "password";



    // Creación tabla juegos
    private static final String CREATE_TABLE_JUEGOS =
            "create table "+
                    TABLE_NAME_JUEGOS +" " +
                    "("+ TABLE_JUEGOS_ID +" integer primary key, "+ TABLE_JUEGOS_NOMBRE +" text not null, "+
                    TABLE_JUEGOS_PRECIO +" integer not null, "
                    + TABLE_JUEGOS_ID_JUEGO +" integer not null, "+ TABLE_JUEGOS_MARCA +" text)";

    // Creación tabla pedidos
    private static final String CREATE_TABLE_PEDIDOS =
            "create table "+
                    TABLE_NAME_PEDIDO +" " +
                    "("+ TABLE_PEDIDO_ID +" integer primary key, "+ TABLE_PEDIDO_NOMBRE +" text not null, "
                    + TABLE_PEDIDO_PRECIO +" integer not null )";

    // Creación tabla usuarios
    private static final String CREATE_TABLE_USERS =
            "create table "+
                    TABLE_NAME_USERS +" " +
                    "("+ TABLE_USERS_ID +" integer primary key autoincrement, "+ TABLE_USERS_NOMBRE +" text not null, "
                    + TABLE_USERS_PWD +" text not null )";



    public DBHelper(Context ctx) {
        this.myContext = ctx;
    }


    //Controloar la BD de SQLite
    private static class DataBaseHelperInternal extends SQLiteOpenHelper {
        public DataBaseHelperInternal(Context context) {
            super(context, DATABASE_NAME, null, DB_VERSION);		}

        @Override
        public void onCreate(SQLiteDatabase db) {
            createTables(db);
        }
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            deleteTables(db);
            createTables(db);
        }
        private void createTables(SQLiteDatabase db) {
            db.execSQL(CREATE_TABLE_JUEGOS);
            db.execSQL(CREATE_TABLE_PEDIDOS);
            db.execSQL(CREATE_TABLE_USERS);
        }

        private void deleteTables(SQLiteDatabase db) {
            db.execSQL("DROP TABLE IF EXISTS " + DBHelper.TABLE_NAME_JUEGOS);
            db.execSQL("DROP TABLE IF EXISTS " + DBHelper.TABLE_NAME_PEDIDO);
            db.execSQL("DROP TABLE IF EXISTS " + DBHelper.TABLE_NAME_USERS);
        }

    }

    //Iniciar la BD
    public DBHelper open()  {
        myDBHelper = new DataBaseHelperInternal(myContext);
        myDataBase = myDBHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        myDBHelper.close();
    }

    //Metodo para obtener los objetos de la tabla juegos
    public Cursor getItems() {
        return myDataBase.query(TABLE_NAME_JUEGOS, new String[] {TABLE_JUEGOS_ID, TABLE_JUEGOS_NOMBRE, TABLE_JUEGOS_PRECIO, TABLE_JUEGOS_ID_JUEGO}, null, null, null, null, TABLE_JUEGOS_ID_JUEGO);
    }

    //Insertar Juego
    public long insertJuegos(String nombre, String precio, String marca, String id_juego){
        ContentValues initialValues = new ContentValues();
        initialValues.put(TABLE_JUEGOS_ID_JUEGO, id_juego);
        initialValues.put(TABLE_JUEGOS_NOMBRE, nombre);
        initialValues.put(TABLE_JUEGOS_PRECIO, precio);
        initialValues.put(TABLE_JUEGOS_MARCA, marca);
        return myDataBase.insert(TABLE_NAME_JUEGOS, null, initialValues);
    }

    //Insertar Usuarios
    public long insertUsers(String username, String password){
        ContentValues initialValues = new ContentValues();
        //initialValues.put(TABLE_USERS_ID, id);
        initialValues.put(TABLE_USERS_NOMBRE, username);
        initialValues.put(TABLE_USERS_PWD, password);
        return myDataBase.insert(TABLE_NAME_USERS,null ,initialValues);
    }

    public void insertPedidos(int precio){
        ContentValues initialValues = new ContentValues();
        initialValues.put(TABLE_PEDIDO_PRECIO, precio);
        myDataBase.insert(TABLE_NAME_PEDIDO,null,initialValues);
        myDataBase.close();
    }

    public Usuario getUsers(String nombre, String pwd) {

        SQLiteDatabase db = myDBHelper.getReadableDatabase();
        Usuario user = null;

        Cursor cursor = db.query(DBHelper.TABLE_NAME_USERS, new String[]{DBHelper.TABLE_USERS_ID,
                        DBHelper.TABLE_USERS_NOMBRE, DBHelper.TABLE_USERS_PWD}, DBHelper.TABLE_USERS_NOMBRE + "=? and " + DBHelper.TABLE_USERS_PWD + "=?",
                new String[]{nombre, pwd}, null, null, null, "1");
        if (cursor != null)
            cursor.moveToFirst();
        if (cursor != null && cursor.getCount() > 0) {
            user = new Usuario(cursor.getString(1), cursor.getString(2));
        }
        return user;


    }


    public void drop(){
        this.myContext.deleteDatabase(DATABASE_NAME);
    }

    public String price(int precio) {
        String selectQuery = "SELECT SUM("+precio+") INTO "+TABLE_PEDIDO_PRECIO;
        Cursor cursor = myDataBase.rawQuery(selectQuery, null);
        cursor.close();
        return TABLE_PEDIDO_PRECIO;
    }

}