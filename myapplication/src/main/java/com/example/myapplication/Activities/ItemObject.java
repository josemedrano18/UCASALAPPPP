package com.example.myapplication.Activities;
import com.google.gson.annotations.SerializedName;

public class ItemObject {
    @SerializedName("nom_eje")
    private String Eje;
    public ItemObject(String Eje)
    {
        this.Eje= Eje;
    }
    public String getEje()
    {
        return Eje;
    }
}
