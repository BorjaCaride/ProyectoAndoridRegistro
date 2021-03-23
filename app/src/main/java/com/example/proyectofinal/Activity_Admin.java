package com.example.proyectofinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Activity_Admin extends AppCompatActivity {

    private Button btnEmpleados;
    private Button btnHorarios;
    private Button btnLocal,btnVisualizar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__admin);

        btnEmpleados = findViewById(R.id.btnEmpleados);

        btnHorarios = findViewById(R.id.btnHorarios);

        btnLocal = findViewById(R.id.btnLocal);
        btnVisualizar = findViewById(R.id.btnVisualizacion);

        btnVisualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ventana = new Intent(v.getContext(),activity_visualizacion.class);

                startActivity(ventana);
            }
        });

        btnEmpleados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ventana_empleados = new Intent(v.getContext(),Activity_Empleados.class);

                startActivity(ventana_empleados);
            }
        });

        btnLocal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ventana_mesas = new Intent(v.getContext(),activity_admin_mesas.class);

                startActivity(ventana_mesas);
            }
        });

        btnHorarios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent horarios = new Intent(Activity_Admin.this,activity_horarios.class);
                startActivity(horarios);
            }
        });

    }



}