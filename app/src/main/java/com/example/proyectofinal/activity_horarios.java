package com.example.proyectofinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.proyectofinal.Adaptadores.AdaptadorEmpleados;
import com.example.proyectofinal.Adaptadores.AdaptadorHorarios;
import com.example.proyectofinal.pojos.Empleados;
import com.example.proyectofinal.pojos.Horarios;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class activity_horarios extends AppCompatActivity {

    private Button btnInsertar;
    private ListView lista;
    ArrayList<Horarios> ArrayHorarios = new ArrayList<Horarios>();
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    Horarios horarioSeleccionado = new Horarios();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horarios);

        btnInsertar = findViewById(R.id.btnInsertaHorario);
        lista = findViewById(R.id.listaHorarios);

        inicializarDatabase();
        listarDatos();
        btnInsertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent introducir = new Intent(activity_horarios.this,activity_InsertarHorario.class);
                startActivity(introducir);
            }
        });

        lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                horarioSeleccionado = (Horarios) parent.getItemAtPosition(position);

                return false;
            }
        });
    }

    private void listarDatos() {
        databaseReference.child("Horario").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                ArrayHorarios.clear();
                for (DataSnapshot objSnapShot : datasnapshot.getChildren()) {
                    Horarios e = objSnapShot.getValue(Horarios.class);
                    ArrayHorarios.add(e);
                    AdaptadorHorarios adaptador = new AdaptadorHorarios(activity_horarios.this, ArrayHorarios);
                    lista.setAdapter(adaptador);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


    private void inicializarDatabase() {

        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }
}