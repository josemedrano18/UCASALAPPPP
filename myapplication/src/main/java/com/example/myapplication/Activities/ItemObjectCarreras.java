package com.example.myapplication.Activities;
import com.google.gson.annotations.SerializedName;

public class ItemObjectCarreras {
    @SerializedName("nom_carrera")
    private String NomCarrera;
    public ItemObjectCarreras(String NomCarrera)
    {
        this.NomCarrera= NomCarrera;
    }
    public String getNomCarrera()
    {
        return NomCarrera;
    }
}
