package com.example.proyectofinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class activity_servicio extends AppCompatActivity {


    private Button btnInicioServicio;
    private Button btnFinServicio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servicio);

        btnInicioServicio = (Button) findViewById(R.id.btnInicioServicio);
        btnFinServicio =(Button) findViewById(R.id.btnFinServicio);


        btnInicioServicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ventana_Datos = new Intent(v.getContext(),activity_registro.class);
                startActivity(ventana_Datos);
            }
        });

        btnFinServicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ventana_mesa = new Intent(v.getContext(),activity_mesa.class);
                startActivity(ventana_mesa);
            }
        });

    }
}