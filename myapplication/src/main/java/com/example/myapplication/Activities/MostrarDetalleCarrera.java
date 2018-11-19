package com.example.myapplication.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MostrarDetalleCarrera extends AppCompatActivity {
    private final String TAG= "DetalleCarrera";
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private DetalleCarreraAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_carrera);
        String nomcarrera= getIntent().getStringExtra("NOM_CARRERA");
        recyclerView = (RecyclerView) findViewById(R.id.recylcerViewDetalleCarrera);
        recyclerView.addItemDecoration(new SimpleDividerItemDecoration(this));
        layoutManager = new LinearLayoutManager( MostrarDetalleCarrera.this);
        recyclerView.setLayoutManager(layoutManager);
        Toast.makeText(this, "Clickeaste "+ nomcarrera, Toast.LENGTH_LONG).show();
        requestJsonObject(nomcarrera);
    }
    private void requestJsonObject(String nomcarrera)
    {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://192.168.1.8:8080/ucasal/obtener_detalle_carrera.php?nomcarrera=" + nomcarrera;
        url= url.replaceAll(" ", "%20");
        StringRequest stringRequest= new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Response " + response);
                GsonBuilder builder = new GsonBuilder();
                Gson mGson = builder.create();
                List<DetalleCarrera> posts = new ArrayList<DetalleCarrera>();
                posts = Arrays.asList(mGson.fromJson(response, DetalleCarrera[].class));
//                ItemObject posts = mGson.fromJson(response, ItemObject.class);
                adapter = new DetalleCarreraAdapter(MostrarDetalleCarrera.this, posts);
                recyclerView.setAdapter(adapter);

            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "Error" + error.getMessage());
            }
        });
        queue.add(stringRequest);

    }
}
