package com.example.app1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.app1.adaptadores.ListaCursosAdapter;
import com.example.app1.db.DbCurso;
import com.example.app1.entidades.Curso;

import java.util.ArrayList;

public class ListarActivity extends AppCompatActivity {


    RecyclerView listaCursos;
    ArrayList<Curso> listaArrayCursos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar);

        listaCursos=findViewById(R.id.listaCursos);
        listaCursos.setLayoutManager(new LinearLayoutManager(this));

        DbCurso dbCurso=new DbCurso(ListarActivity.this,"Cursos.db",0);

        listaArrayCursos=dbCurso.mostrarCursos();

        ListaCursosAdapter adapter=new ListaCursosAdapter(listaArrayCursos);


        listaCursos.setAdapter(adapter);




    }
}