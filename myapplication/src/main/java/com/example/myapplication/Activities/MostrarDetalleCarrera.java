package com.example.myapplication.Activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
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

public class MostrarDetalleCarrera extends AppCompatActivity {
    private final String TAG= "DetalleCarrera";
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private DetalleCarreraAdapter adapter;
    private DetalleCarrera detalle;
 TextView textViewTitulo;
 TextView textViewDuracion;
  TextView textViewSalida;
  TextView textViewPerfil;
  TextView textViewRequisitos;
  TextView textViewCurso;
  TextView textViewPlan;
  FloatingActionButton buttonCompartir;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_carrera);
        String nomcarrera= getIntent().getStringExtra("NOM_CARRERA");


        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_nav_view3);
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
                      Intent intent = new Intent(MostrarDetalleCarrera.this, MainActivity.class);
                    startActivity(intent);
                      break;
                    case R.id.action_contacto:
                        Intent intent2 = new Intent(MostrarDetalleCarrera.this, Contacto.class);
                        startActivity(intent2);
                        break;
                }

                return false;
            }
        });
       // layoutManager = new LinearLayoutManager( MostrarDetalleCarrera.this);

       // Toast.makeText(this, "Clickeaste "+ nomcarrera, Toast.LENGTH_LONG).show();
        requestJsonObject(nomcarrera);
        textViewTitulo = (TextView)findViewById(R.id.textViewTitulo);
        textViewDuracion =(TextView)findViewById(R.id.textViewDuracion);
        textViewSalida =(TextView)findViewById(R.id.textViewSalida);
        textViewPerfil =(TextView)findViewById(R.id.textViewPerfil);
        textViewRequisitos =(TextView)findViewById(R.id.textViewRequisitos);
        textViewCurso =(TextView)findViewById(R.id.textViewCurso);
        textViewPlan =(TextView)findViewById(R.id.textViewPlan);
        textViewPlan.setClickable(true);


        buttonCompartir = (FloatingActionButton) findViewById(R.id.buttonCompartir);
        buttonCompartir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CompartirCarrera();
            }
        });

    }
    private void CompartirCarrera() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, "Mirá esta carrera:" +textViewTitulo.getText() + ", "+ textViewDuracion.getText()+ ", " +textViewDuracion.getText() + ", " + textViewRequisitos.getText()+ ", " +textViewPlan.getText() );
        intent.setPackage("com.whatsapp");
        startActivity(intent);
    }

    private void requestJsonObject(String nomcarrera)
    {
        String path = getString(R.string.path);
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = path + "/ucasal/obtener_detalle_carrera.php?nomcarrera=" + nomcarrera;
        url= url.replaceAll(" ", "%20");
        StringRequest stringRequest= new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
         //Log.d(TAG, "Response " + response);
                GsonBuilder builder = new GsonBuilder();
                Gson mGson = builder.create();
               // DetalleCarrera post = new DetalleCarrera();
              //  post = Arrays.asList(mGson.fromJson(response, DetalleCarrera[].class));
              DetalleCarrera post = mGson.fromJson(response, DetalleCarrera.class);
              //  adapter = new DetalleCarreraAdapter(MostrarDetalleCarrera.this, posts);
                //recyclerView.setAdapter(adapter);
                LayoutInflater inflater = LayoutInflater.from(MostrarDetalleCarrera.this);
               // View view = inflater.inflate(R.layout.detalle_carrera, null);
              //  Toast.makeText(MostrarDetalleCarrera.this, "Clickeaste "+ post.getTitulo(), Toast.LENGTH_LONG).show();
                textViewTitulo.setText(post.getTitulo());
                textViewDuracion.setText(post.getDuracion());
                textViewSalida.setText(post.getSalida());
                textViewPerfil.setText(post.getPerfil());
                textViewRequisitos.setText(post.getRequisitos());
                textViewCurso.setText(post.getCurso());
                textViewPlan.setText(post.getPlanestudio());
             /*   view.textViewDuracion.setText(detalle.getDuracion());
                view.textViewSalida.setText(String.valueOf(detalle.getSalida()));
                view.textViewPerfil.setText(String.valueOf(detalle.getPerfil()));
                view.textviewRequisitos.setText(detalle.getRequisitos());
                view.textviewCurso.setText(String.valueOf(detalle.getCurso()));
                view.textviewPlan.setText(String.valueOf(detalle.getPlanestudio()));*/

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
