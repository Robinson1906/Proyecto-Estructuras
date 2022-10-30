package com.example.app1.entidades;

public class Curso {
    private int id;
    private int numClases;
    private int numEstudiantes;
    private String materia;
    private String grado;
    private String acceso;
    private int clasesDictadas;

    public int getId() {
        return id;
    }public void setId(int id) {
        this.id = id;
    }

    public int getNumClases() {
        return numClases;
    }public void setNumClases(int numClases) {
        this.numClases = numClases;
    }

    public int getNumEstudiantes() {
        return numEstudiantes;
    }public void setNumEstudiantes(int numEstudiantes) {
        this.numEstudiantes = numEstudiantes;
    }

    public String getMateria() {
        return materia;
    }public void setMateria(String materia) {
        this.materia = materia;
    }

    public String getGrado() {
        return grado;
    }public void setGrado(String grado) {
        this.grado = grado;
    }

    public String getAcceso() {
        return acceso;
    }public void setAcceso(String acceso) {
        this.acceso = acceso;
    }

    public int getClasesDictadas() {
        return clasesDictadas;
    }  public void setClasesDictadas(int clasesDictadas) {
        this.clasesDictadas = clasesDictadas;
    }
}
