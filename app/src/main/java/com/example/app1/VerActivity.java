package com.example.app1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.app1.db.DbCurso;
import com.example.app1.entidades.Curso;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class VerActivity extends AppCompatActivity {

    EditText txtMateria,txtClases,txtGrado,txtAcceso;
    Button btnGuardar;

    FloatingActionButton fadEditar;

    Curso curso;
    int id=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver);

        txtMateria=findViewById(R.id.txtMateri);
        txtClases=findViewById(R.id.txtClase);
        txtGrado=findViewById(R.id.txtGrad);
        btnGuardar=findViewById(R.id.btnGuardar);
        fadEditar=findViewById(R.id.fabEditar);
        //Espacio para lo de estudiantes

        if (savedInstanceState==null){
            Bundle extras= getIntent().getExtras();

            if (extras==null) {
                id = Integer.parseInt(null);
            }else {
                id=extras.getInt("id");
            }
        }else{
            id=(int) savedInstanceState.getSerializable("id");
        }

        DbCurso dbCurso= new DbCurso(VerActivity.this,"Cursos",0);
        curso=dbCurso.verCurso(id);

        if (curso!=null){
            txtMateria.setText(curso.getMateria());
            txtClases.setText(String.valueOf(curso.getNumClases()));
            txtGrado.setText(curso.getGrado());
            txtMateria.setInputType(InputType.TYPE_NULL);
            txtClases.setInputType(InputType.TYPE_NULL);
            txtGrado.setInputType(InputType.TYPE_NULL);

        }
        btnGuardar.setVisibility(View.INVISIBLE);
        fadEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(VerActivity.this,EditarActivity.class);
                i.putExtra("id",id);
                startActivity(i);
            }
        });
        }




}
