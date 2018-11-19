package com.example.myapplication.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class Carreras extends AppCompatActivity {
    private final String TAG= "Carreras";
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private RecyclerViewAdapterCarreras adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carreras);
        String ejetematico= getIntent().getStringExtra("EJE_TEMATICO");
        Toast.makeText(this, "Clickeaste "+ ejetematico, Toast.LENGTH_LONG).show();
        recyclerView = (RecyclerView) findViewById(R.id.recycler_viewcarreras);
        recyclerView.addItemDecoration(new SimpleDividerItemDecoration(this));
        layoutManager = new LinearLayoutManager(Carreras.this);
        recyclerView.setLayoutManager(layoutManager);
        requestJsonObject(ejetematico);
    }
    private void requestJsonObject(String ejetematico)
    {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://192.168.1.8:8080/ucasal/obtener_carreras.php?nomeje=" + ejetematico;
        StringRequest stringRequest= new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Response " + response);
                GsonBuilder builder = new GsonBuilder();
                Gson mGson = builder.create();
                List<ItemObjectCarreras> posts = new ArrayList<ItemObjectCarreras>();
                posts = Arrays.asList(mGson.fromJson(response, ItemObjectCarreras[].class));
//                ItemObject posts = mGson.fromJson(response, ItemObject.class);
                adapter = new RecyclerViewAdapterCarreras(Carreras.this, posts);
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
