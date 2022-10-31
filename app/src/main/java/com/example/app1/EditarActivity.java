package com.example.app1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.app1.db.DbCurso;
import com.example.app1.entidades.Curso;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class EditarActivity extends AppCompatActivity {
    EditText txtMateria,txtClases,txtGrado,txtAcceso;
    Button btnGuardar;
    FloatingActionButton fabEditar,fabBorrar;
    Curso curso;

    boolean correcto=false;
    int id=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver);

        txtMateria=findViewById(R.id.txtMateri);
        txtClases=findViewById(R.id.txtClase);
        txtGrado=findViewById(R.id.txtGrad);
        btnGuardar=findViewById(R.id.btnGuardar);
        fabEditar=findViewById(R.id.fabEditar);
        fabBorrar=findViewById(R.id.fabBorrar);


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

        DbCurso dbCurso= new DbCurso(EditarActivity.this,"Cursos",0);
        curso=dbCurso.verCurso(id);

        if (curso!=null){
            txtMateria.setText(curso.getMateria());
            txtClases.setText(String.valueOf(curso.getNumClases()));
            txtGrado.setText(curso.getGrado());
        }
        fabEditar.setVisibility(View.INVISIBLE);
        fabBorrar.setVisibility(View.INVISIBLE);
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validar()){
                    correcto=dbCurso.editarCurso(id,txtMateria.getText().toString(),txtGrado.getText().toString(),Integer.parseInt(txtClases.getText().toString()),"",0,0);
                }
                if (correcto){
                    Toast.makeText(EditarActivity.this, "Registro Modificado", Toast.LENGTH_SHORT).show();
                    verRegistro();
                }else{
                    Toast.makeText(EditarActivity.this, "Error al Modificar", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
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
                Integer.parseInt(txtClases.getText().toString());
                ret=false;
            }
            if (txtGrado.getText().toString().isEmpty()){
                txtGrado.setError("Este campo no debe estar vacio");
                ret=false;
            }
        }catch (Exception e) {
            txtClases.setError("Debe ser un numero");
          return false;
        }

        return ret;
    }

    private void verRegistro(){
        Intent i = new Intent(this,VerActivity.class);
        i.putExtra("id", id);
        startActivity(i);

    }
}
