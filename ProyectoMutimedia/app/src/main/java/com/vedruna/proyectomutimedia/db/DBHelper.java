package com.vedruna.proyectomutimedia.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    // Se definen algunas constantes para el nombre y la version de la base de datos:
    private static final String DATABASE_NAME = "login.db";
    private static final int DATABASE_VERSION = 1;
    public static final String TABLE_USERS = "users";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_PASSWORD = "password";

    // Se define una consulta SQL para crear la tabla de usuarios con tres columnas:
    private static final String CREATE_USERS_TABLE = "CREATE TABLE " + TABLE_USERS + " (" +
            COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_USERNAME + " TEXT, " +
            COLUMN_PASSWORD + " TEXT);";

    // Se define un constructor para la clase DBHelper, que llama al constructor de la clase base
    // SQLiteOpenHelper con el nombre de la base de datos y la version.
    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Se implementa el método onCreate, que se llama cuando la base de datos se crea por primera vez.
    // Ejecuta la consulta SQL para crear la tabla de usuarios y luego inserta un usuario.
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USERS_TABLE);

        // Insertar usuario admin predeterminado
        ContentValues values = new ContentValues();
        values.put(COLUMN_USERNAME, "admin");
        values.put(COLUMN_PASSWORD, "admin");
        db.insert(TABLE_USERS, null, values);
    }

    // Se implementa el método onUpgrade, que se llama cuando se actualiza la version de la base de
    // datos. Elimina la tabla de usuarios existente y luego llama al método onCreate para recrear
    // la tabla.
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        onCreate(db);
    }
}
