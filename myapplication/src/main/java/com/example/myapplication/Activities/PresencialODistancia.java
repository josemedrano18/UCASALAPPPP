package com.example.myapplication.Activities;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.MenuItem;
import android.view.View;

import com.example.myapplication.R;

public class PresencialODistancia extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_presencial_odistancia);
CardView cardViewPresencial = (CardView) findViewById(R.id.cardViewPresencial);
        CardView cardViewDistancia = (CardView) findViewById(R.id.cardViewDistancia);
        cardViewPresencial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //   Toast.makeText(context, "Clickeaste "+ itemList.get(position).getEje(), Toast.LENGTH_LONG).show();
                Context context = v.getContext();
                Intent intent = new Intent(context, Ejes.class);
                context.startActivity(intent);

            }
        });

        cardViewDistancia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //   Toast.makeText(context, "Clickeaste "+ itemList.get(position).getEje(), Toast.LENGTH_LONG).show();
                Context context = v.getContext();
                Intent intent = new Intent(context, CarrerasDistancia.class);
                context.startActivity(intent);

            }
        });
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_nav_view5);
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
                        Intent intent = new Intent(PresencialODistancia.this, MainActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.action_contacto:
                        Intent intent2 = new Intent(PresencialODistancia.this, Contacto.class);
                        startActivity(intent2);
                        break;
                }

                return false;
            }
        });
    }
}
