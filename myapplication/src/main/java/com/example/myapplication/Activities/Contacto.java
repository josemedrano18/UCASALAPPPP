package com.example.myapplication.Activities;


import android.Manifest;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.button.MaterialButton;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.PhoneNumberUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.example.myapplication.R;

public class Contacto extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacto);
       // Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        Button button = (Button) findViewById(R.id.button);
        Button button3 = (Button) findViewById(R.id.button3);
        Button button2 = (Button) findViewById(R.id.button2);
      MaterialButton button4 = (MaterialButton) findViewById(R.id.material1);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AbrirWhatsApp("693718");
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CompartirCarrera();
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Llamar();
            }
        });
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] TO = {"agum_96@hotmail.com"}; //Direcciones email  a enviar.
                String[] CC = {""}; //Direcciones email con copia.

                Intent emailIntent = new Intent(Intent.ACTION_SEND);

                emailIntent.setData(Uri.parse("mailto:"));
                emailIntent.setType("text/plain");
                emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
                emailIntent.putExtra(Intent.EXTRA_CC, CC);
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "CONSULTA APP");
                emailIntent.putExtra(Intent.EXTRA_TEXT, ""); // * configurar email aquí!

                try {
                    startActivity(Intent.createChooser(emailIntent, "Enviar email."));
                    Log.i("EMAIL", "Enviando email...");
                } catch (android.content.ActivityNotFoundException e) {
                    Toast.makeText(Contacto.this, "No hay cliente de mail instalado", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void AbrirWhatsApp(String telefono) {
        Intent _intencion = new Intent("android.intent.action.MAIN");
        _intencion.setComponent(new ComponentName("com.whatsapp", "com.whatsapp.Conversation"));
        _intencion.putExtra("jid", PhoneNumberUtils.stripSeparators("5493874" + telefono) + "@s.whatsapp.net");
        startActivity(_intencion);
    }

    private void CompartirCarrera() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, "El mejor blog de android http://javaheros.blogspot.pe/");
        intent.setPackage("com.whatsapp");
        startActivity(intent);
    }


    private void Llamar() {
        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE);

        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    this,
                    new String[]{Manifest.permission.CALL_PHONE},
                    123);
        } else {
            startActivity(new Intent(Intent.ACTION_CALL).setData(Uri.parse("tel:3874268880")));
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {

            case 123:
                if ((grantResults.length > 0) && (grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    Llamar();
                } else {
                    Log.d("TAG", "Call Permission Not Granted");
                }
                break;

            default:
                break;
        }
    }
}
