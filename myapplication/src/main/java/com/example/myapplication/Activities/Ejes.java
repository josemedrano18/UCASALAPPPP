package com.example.myapplication.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

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

public class Ejes extends AppCompatActivity {
    private final String TAG= "MainActivity";
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private RecyclerViewAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejes);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.addItemDecoration(new SimpleDividerItemDecoration(this));
        layoutManager = new LinearLayoutManager(Ejes.this);
        recyclerView.setLayoutManager(layoutManager);
        requestJsonObject();
    }
    private void requestJsonObject()
    {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://192.168.100.3:8080/ucasal/obtener_ejes.php";
        StringRequest stringRequest= new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Response " + response);
                GsonBuilder builder = new GsonBuilder();
                Gson mGson = builder.create();
                List<ItemObject> posts = new ArrayList<ItemObject>();
                posts = Arrays.asList(mGson.fromJson(response, ItemObject[].class));
//                ItemObject posts = mGson.fromJson(response, ItemObject.class);
                adapter = new RecyclerViewAdapter(Ejes.this, posts);
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

