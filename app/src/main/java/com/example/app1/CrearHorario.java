package com.example.app1;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.app1.db.dbHelp;

import java.util.Calendar;

public class CrearHorario extends AppCompatActivity implements View.OnClickListener {
    Button bInicio;
    Button bFin;
    Button guardar;
    Button volver;

    EditText eInicio;
    EditText eFin;
    EditText eNombre;

    private int horaI,horaF,minI,minF;
    //Guarda las horas y los minutos, 0-hora inicio,1-min inicio, 2-hora fin,3-min fin
    private int[] horas= new int[4];
    private String nombreHorario;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.crear_horario);

        eNombre=(EditText) findViewById(R.id.eNombre);

        //Horas de inicio y final
        bInicio = (Button) findViewById(R.id.bInicio);
        bFin = (Button) findViewById(R.id.bFIn);
        eInicio=(EditText)findViewById(R.id.eInicio);
        eFin=(EditText)findViewById(R.id.eFin);
        bInicio.setOnClickListener(this);
        bFin.setOnClickListener(this);
        eInicio.setOnClickListener(this);
        eFin.setOnClickListener(this);

        //Boton Volver
        volver=findViewById(R.id.btnVolver);
        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(CrearHorario.this, MainActivity.class);
                startActivity(intent);

            }
        });
        //Boton Guardar
        guardar=(Button) findViewById(R.id.guardar);
        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nombreHorario= eNombre.getText().toString();
                if (validar()){
                   crearDb(nombreHorario,0);
                }else{
                    Toast.makeText(CrearHorario.this, "Datos incorrectos", Toast.LENGTH_SHORT).show();
                }

            }
        });



    }
    //Onclick general
    @Override
    public void onClick(View view) {
        //Eleccion de hora de inicio
        if (view==bInicio||view==eInicio){

            final Calendar c= Calendar.getInstance();
            horaI=c.get(Calendar.HOUR_OF_DAY);
            minI=c.get(Calendar.MINUTE);


            TimePickerDialog t= new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker timePicker, int i, int i1) {
                    String mensaje=i+":"+i1;
                    horas[0]=i;
                    horas[1]=i1;
                    if (i1<10){
                        mensaje=i+":0"+i1;
                    }
                    if (i<10){
                        mensaje="0"+mensaje;
                    }
                    eInicio.setText(mensaje);
                }
            },horaI,minI,false);
            t.show();

        }
        //Eleccion de hora Final
        else if (view==bFin||view==eFin){
            final Calendar c= Calendar.getInstance();
            horaF=c.get(Calendar.HOUR_OF_DAY);
            minF=c.get(Calendar.MINUTE);

            TimePickerDialog t= new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker timePicker, int i, int i1) {
                    String mensaje=i+":"+i1;
                    horas[2]=i;
                    horas[3]=i1;
                    if (i1<10){
                        mensaje=i+":0"+i1;
                    }
                    if (i<10){
                        mensaje="0"+mensaje;
                    }
                    eFin.setText(mensaje);
                }
            },horaI,minI,false);
            t.show();

        }

    }

    public boolean validar(){
        boolean ret=true;

        //validacion de datos vacios
        if (eNombre.getText().toString().isEmpty()){
            eNombre.setError("Este campo no debe estar vacio");
            ret=false;
        }
        if (eInicio.getText().toString().isEmpty()){
            eInicio.setError("Este campo no debe estar vacio");
            ret=false;
        }
        if (eFin.getText().toString().isEmpty()){
            eFin.setError("Este campo no debe estar vacio");
            ret=false;
        }

        //validacion de hora congruente
        if (horas[0]>horas[2]){
            Toast.makeText(this, "La hora final no puede ser anterior a la inicial", Toast.LENGTH_SHORT).show();
            eInicio.setError("");
            eFin.setError("");
            ret=false;
        }

        return ret;
    }

    public void crearDb(@Nullable String name,@Nullable int v) {
        dbHelp dbHleper = new dbHelp(CrearHorario.this,name,v);
        SQLiteDatabase db = dbHleper.getWritableDatabase();

        if (db != null) {
            Toast.makeText(this, "Horario Creado", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Error al crear el horario", Toast.LENGTH_SHORT).show();
        }
    }
}