package com.example.app1.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelperCursos extends SQLiteOpenHelper {
    private static final int DATABASE_V=1;

    public static final String Tabla_Cursos ="tablaCursos";

    public DbHelperCursos(@Nullable Context context, @Nullable String name, int version) {

        super(context, "Cursos.db", null, DATABASE_V+0);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE "+ Tabla_Cursos +" ( id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "materia TEXT NOT NULL,"+
                "grado TEXT NOT NULL,"+
                "accesoTabla NOT NULL,"+
                "nEstudiantes INTEGER NOT NULL," +
                "nClases INTEGER NOT NULL,"+
                "clasesDictadas INTEGER NOT NULL)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE "+ Tabla_Cursos);
        onCreate(sqLiteDatabase);
    }


}
