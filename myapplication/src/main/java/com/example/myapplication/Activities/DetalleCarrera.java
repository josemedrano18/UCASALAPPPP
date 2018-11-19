package com.example.myapplication.Activities;
import com.google.gson.annotations.SerializedName;

public class DetalleCarrera {
    //private int id;
    @SerializedName("titulo")
    private String titulo;
    @SerializedName("duracion")
    private String duracion;
    @SerializedName("salida")
    private String salida;
    @SerializedName("perfil")
    private String perfil;
    @SerializedName("requisitos")
    private String requisitos;
    @SerializedName("curso")
    private String curso;
    @SerializedName("plan_estudio")
    private String planestudio;
    public DetalleCarrera(String titulo, String duracion, String salida, String perfil, String requisitos, String curso, String planestudio) {
        this.titulo=titulo;
        this.duracion=duracion;
        this.salida=salida;
        this.perfil=perfil;
        this.requisitos=requisitos;
        this.curso=curso;
        this.planestudio=planestudio;
    }


    public String getTitulo() {
        return titulo;
    }

    public String getDuracion() {
        return duracion;
    }

    public String getSalida() {
        return salida;
    }
    public String getPerfil() {
        return perfil;
    }
    public String getRequisitos() {
        return requisitos;
    }
    public String getCurso() {
        return curso;
    }
    public String getPlanestudio() {
        return planestudio;
    }
}
