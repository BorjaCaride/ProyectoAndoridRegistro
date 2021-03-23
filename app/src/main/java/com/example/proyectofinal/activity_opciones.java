package com.example.proyectofinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class activity_opciones extends AppCompatActivity {
    private Button btnServicio;
    private Button btnAdmin;
    private Button btnPedido;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opciones);

        btnPedido = findViewById(R.id.btnPedido);
        btnServicio = findViewById(R.id.btnServicio);
        btnAdmin = findViewById(R.id.btnAdministracion);

        btnServicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent ventana_Servicio = new Intent(v.getContext(),activity_servicio.class);
                startActivity(ventana_Servicio);
            }
        });

        btnPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ventana_Pedidos = new Intent(v.getContext(),activity_pedidos.class);
                startActivity(ventana_Pedidos);
            }
        });
        btnAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ventana_Admin = new Intent(v.getContext(),Activity_Admin.class);
                startActivity(ventana_Admin);
            }
        });
    }
}