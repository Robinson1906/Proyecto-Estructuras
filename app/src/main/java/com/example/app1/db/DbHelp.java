package com.example.app1.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelp extends SQLiteOpenHelper {
    private static final int DATABASE_V=1;

    public static final String Tabla_Estudiantes ="TablaEstudiantes";

    public DbHelp(@Nullable Context context, @Nullable String name, int version) {

        super(context, name, null, DATABASE_V+version);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE "+ Tabla_Estudiantes +" ( id INTEGER PRIMARY KEY AUTOINCREMENT,"+
            "nombre TEXT NOT NULL,"+
            "Asistencias INTEGER NOT NULL,"+
            "fallas INTEGER NOT NULL,"+
            "retrasos INTEGER NOT NULL,"+
            "excusas INTEGER NOT NULL)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE "+ Tabla_Estudiantes);
        onCreate(sqLiteDatabase);
    }


}
