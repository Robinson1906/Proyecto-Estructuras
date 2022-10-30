package com.example.app1;

import static com.example.app1.entidades.helper.isAlpha;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.app1.db.DbCurso;
import com.example.app1.db.DbHelp;

public class CrearActivity extends AppCompatActivity{

    public String  n;

    Button guardar;
    Button volver;

    int nEstudiantes=12;
    EditText txtClases;
    EditText txtGrado;
    EditText txtMateria, txtSalon;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear);

        txtMateria =(EditText) findViewById(R.id.eMateria);
        txtClases =(EditText)findViewById(R.id.numClases);
        txtGrado =(EditText)findViewById(R.id.grado);
        txtSalon =(EditText)findViewById(R.id.salon);


        //Boton Volver
        volver=findViewById(R.id.btnVolver);
        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(CrearActivity.this, MainActivity.class);
                startActivity(intent);

            }
        });
        //Boton Guardar
        guardar=(Button) findViewById(R.id.guardar);
        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (validar()){
                    n="e_"+txtMateria.getText().toString()+"_"+txtGrado.getText().toString()+txtSalon.getText().toString()+".db";
                   boolean b=crearDb(n,0);
                   if (b) {

                       llenarDb("Cursos", 0);
                   }
                }else{
                    Toast.makeText(CrearActivity.this, "Datos incorrectos", Toast.LENGTH_SHORT).show();
                }

            }
        });



    }
    //Onclick general

    public boolean validar(){
        boolean ret=true;
        try{
        //validacion de datos vacios
        if (txtMateria.getText().toString().isEmpty()){
            txtMateria.setError("Este campo no debe estar vacio");
            ret=false;
        }
        if (txtClases.getText().toString().isEmpty()){
            txtClases.setError("Este campo no debe estar vacio");
            ret=false;
        }
        if (txtGrado.getText().toString().isEmpty()){
            txtGrado.setError("Este campo no debe estar vacio");
            ret=false;
        }
        if(Integer.parseInt(txtGrado.getText().toString())<0||Integer.parseInt(txtGrado.getText().toString())>11){
            txtGrado.setError("Debe ser un numero entre 0 y 11");
            ret=false;
        }
        if (txtSalon.getText().toString().isEmpty()){
            txtSalon.setError("Este campo no puede quedar vacio");
            ret=false;
        }
        if (txtSalon.getText().toString().length()!=1 || !isAlpha(txtSalon.getText().toString())){
            txtSalon.setError("Debe ser una unica letra");
            ret=false;
        }
        }catch (Exception e){
            txtGrado.setError("Debe ser un numero");
            txtClases.setError("Debe ser un numero");
            return false;
        }

        return ret;
    }

    public void llenarDb(@Nullable String name, @Nullable int v) {
        DbCurso db = new DbCurso(CrearActivity.this,name,v);
        long id=db.insertaClase(txtMateria.getText().toString(),(txtGrado.getText().toString()+"-"+ txtSalon.getText().toString()),Integer.parseInt(txtClases.getText().toString()), this.n,nEstudiantes);
        if (id>0){
            Toast.makeText(this, "Registro Guardado", Toast.LENGTH_SHORT).show();
            limpiar();

        }else{
            Toast.makeText(this,"Error al Guardar",Toast.LENGTH_SHORT).show();
        }
    }

    public boolean crearDb(@Nullable String name, @Nullable int v) {

        DbHelp dbHelp = new DbHelp(CrearActivity.this,name,v);
        SQLiteDatabase db =dbHelp.getWritableDatabase();
        if (db!=null){
            Toast.makeText(CrearActivity.this, "Base de Datos Creada", Toast.LENGTH_SHORT).show();
            return true;
        }else{
            Toast.makeText(CrearActivity.this, "Error al crear la base de datos", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    private void limpiar(){
        txtClases.setText("");
        txtMateria.setText("");
        txtGrado.setText("");
        txtSalon.setText("");
    }
}