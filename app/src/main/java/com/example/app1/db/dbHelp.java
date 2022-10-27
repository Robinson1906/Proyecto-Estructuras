package com.example.app1.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import kotlin.text.UStringsKt;

public class dbHelp extends SQLiteOpenHelper {
    private static final int DATABASE_V=1;

    public static final String Tabla_Horarios="tablaHorario";

    public dbHelp(@Nullable Context context, @Nullable String name, int version) {

        super(context, "h_"+name+".db", null, DATABASE_V+version);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE "+Tabla_Horarios+" ( id INTEGER PRIMARY KEY AUTOINCREMENT,"+
            "materia TEXT NOT NULL,"+
            "horas INTEGER NOT NULL,"+
            "profesor TEXT NOT NULL)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE "+Tabla_Horarios);
        onCreate(sqLiteDatabase);
    }
}
