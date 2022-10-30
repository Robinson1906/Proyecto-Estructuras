package com.example.app1.adaptadores;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app1.VerActivity;
import com.example.app1.R;
import com.example.app1.entidades.Curso;

import java.util.ArrayList;

public class ListaCursosAdapter extends RecyclerView.Adapter<ListaCursosAdapter.CursoViewHolder> {


    ArrayList<Curso> listaCursos;

    public ListaCursosAdapter(ArrayList<Curso> listaCursos){
        this.listaCursos=listaCursos;
    }
    @NonNull
    @Override
    public CursoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_curso,null,false);

        return new CursoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CursoViewHolder holder, int position) {
        holder.viewMateria.setText(listaCursos.get(position).getMateria());
        holder.viewEstudiantes.setText((String.valueOf(listaCursos.get(position).getNumEstudiantes())));
        holder.viewGrado.setText(listaCursos.get(position).getGrado());
        if (listaCursos.size()==0){
            holder.viewMateria.setText("No hay cursos para mostrar");
        };
    }

    @Override
    public int getItemCount() {
        return listaCursos.size();


    }

    public class CursoViewHolder extends RecyclerView.ViewHolder {
        TextView viewMateria,viewGrado,viewEstudiantes;

        public CursoViewHolder(@NonNull View itemView) {
            super(itemView);

            viewEstudiantes=itemView.findViewById(R.id.viewEstudiantes);
            viewGrado=itemView.findViewById(R.id.viewGrado);
            viewMateria=itemView.findViewById(R.id.viewMateria);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context=view.getContext();
                    Intent i = new Intent(context, VerActivity.class);
                    i.putExtra("id",listaCursos.get(getAdapterPosition()).getId());
                    context.startActivity(i);

                }
            });
        }
    }
}
