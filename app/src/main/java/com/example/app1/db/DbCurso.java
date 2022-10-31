package com.example.app1.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.app1.entidades.Curso;

import java.util.ArrayList;

public class DbCurso extends DbHelperCursos {

    Context context;
    String name;
    int version;

    public DbCurso(@Nullable Context context, @Nullable String name, int version) {
        super(context, name, version);
        this.context=context;
        this.name=name;
        this.version=version;
    }

    public long insertaClase(String materia, String Grado, int numClases,
                             String accesoTablaEstudiantes, int nEstudiantes){
        long id=0;
        try {
            DbHelperCursos dbHelper = new DbHelperCursos(context, name, version);

            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();

            values.put("materia", materia);
            values.put("grado", Grado);
            values.put("accesoTabla", accesoTablaEstudiantes);
            values.put("nEstudiantes", nEstudiantes);
            values.put("nClases", numClases);
            values.put("clasesDictadas",0);

            id = db.insert(Tabla_Cursos, null, values);
        }catch (Exception x){
            x.toString();
        }

        return id;

    }


    public ArrayList<Curso> mostrarCursos(){
        DbHelperCursos dbHelper = new DbHelperCursos(context, name, version);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<Curso> listaCursos=new ArrayList<>();
        Curso curso=null;
        Cursor cursor=null;

        cursor=db.rawQuery("SELECT * FROM "+Tabla_Cursos,null);

        if(cursor.moveToFirst()) {
            do {
                curso = new Curso();
                curso.setId(cursor.getInt(0));
                curso.setMateria(cursor.getString(1));
                curso.setGrado(cursor.getString(2));
                curso.setAcceso(cursor.getString(3));
                curso.setNumEstudiantes(cursor.getInt(4));
                curso.setNumClases(cursor.getInt(5));
                curso.setClasesDictadas(cursor.getInt(6));

                listaCursos.add(curso);

            } while (cursor.moveToNext());
        }
        cursor.close();
        return listaCursos;


    }
    public Curso verCurso(int id){
        DbHelperCursos dbHelper = new DbHelperCursos(context, name, version);
        SQLiteDatabase db = dbHelper.getWritableDatabase();


        Curso curso=null;
        Cursor cursor=null;

        cursor=db.rawQuery("SELECT * FROM "+Tabla_Cursos +" WHERE id="+id+" LIMIT 1",null);

       if (cursor.moveToFirst()){
            curso = new Curso();
            curso.setId(cursor.getInt(0));
            curso.setMateria(cursor.getString(1));
            curso.setGrado(cursor.getString(2));
            curso.setAcceso(cursor.getString(3));
            curso.setNumEstudiantes(cursor.getInt(4));
            curso.setNumClases(cursor.getInt(5));
            curso.setClasesDictadas(cursor.getInt(6));}
        cursor.close();
        return curso;


    }


    public boolean editarCurso(int id, String materia, String Grado, int numClases,
                               String accesoTablaEstudiantes, int nEstudiantes, int dictadas){

        boolean correcto=false;

        DbHelperCursos dbHelper = new DbHelperCursos(context, name, version);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            db.execSQL("UPDATE "+Tabla_Cursos+" SET materia ='"+materia+"', grado= '"+String.valueOf(Grado)+"', accesoTabla = '"+accesoTablaEstudiantes+"' , nEstudiantes='"+nEstudiantes+"',nClases= '"+numClases+"', clasesDictadas= '"+dictadas+"' WHERE id= '"+id+"' ");
            correcto=true;
            }
        catch (Exception x){
            x.toString();
            correcto=false;
        }finally {
            db.close();
        }

        return correcto;

    }
    public boolean borrarCurso(int id){

        boolean correcto=false;

        DbHelperCursos dbHelper = new DbHelperCursos(context, name, version);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            db.execSQL("DELETE FROM "+Tabla_Cursos+" WHERE id = '"+id+"' ");
            correcto=true;
        }
        catch (Exception x){
            x.toString();
            correcto=false;
        }finally {
            db.close();
        }

        return correcto;

    }
}
