package com.example.myapplication.Activities;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.MenuItem;

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
       // recyclerView.addItemDecoration(new SimpleDividerItemDecoration(this));
        layoutManager = new LinearLayoutManager(Ejes.this);
        recyclerView.setLayoutManager(layoutManager);
        //recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));

        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_nav_view1);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.action_sedes:
                        Uri uri = Uri.parse("http://www.ucasal.edu.ar/htm/mapa/sedes.htm");
                        Intent intent1 = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent1);
                        break;
                    case R.id.action_home:
                        Intent intent = new Intent(Ejes.this, MainActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.action_contacto:
                        Intent intent2 = new Intent(Ejes.this, Contacto.class);
                        startActivity(intent2);
                        break;
                }
                return false;
            }
        });

        requestJsonObject();
        RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
        itemAnimator.setAddDuration(1000);
        itemAnimator.setRemoveDuration(1000);
        recyclerView.setItemAnimator(itemAnimator);
    }
    private void requestJsonObject()
    {
        String path = getString(R.string.path);

        RequestQueue queue = Volley.newRequestQueue(this);
        String url = path + "/ucasal/obtener_ejes.php";
        url= url.replaceAll(" ", "%20");
        StringRequest stringRequest= new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
              //  Log.d(TAG, "Response " + response);
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

