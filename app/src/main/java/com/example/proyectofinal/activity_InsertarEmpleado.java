package com.example.proyectofinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

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
import java.util.UUID;

public class activity_InsertarEmpleado extends AppCompatActivity {

    private EditText edtNombre;
    private EditText edtapellido;
    private EditText edtTelefono;
    private Button btnAdd;
    ArrayList<String> Horarios = new ArrayList<String>();
    private Spinner cmbHorario;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__insertar_empleado);

        inicializarDatabase();

        btnAdd = findViewById(R.id.btnAddEmpleado);
        edtNombre = findViewById(R.id.edtNombre);
        edtapellido = findViewById(R.id.edtApellidoEmpleado);
        edtTelefono = findViewById(R.id.edtTlfEmpleado);
        cmbHorario = findViewById(R.id.cmbHorario);

        CargaHorarios();
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String id = UUID.randomUUID().toString();
                String nombre = edtNombre.getText().toString();
                String apellido = edtapellido.getText().toString();
                String Tel = edtTelefono.getText().toString();

                int idhorario = Integer.parseInt(cmbHorario.getSelectedItem().toString());

                if((nombre.equalsIgnoreCase("")||(apellido.equalsIgnoreCase("")||(Tel.equalsIgnoreCase(""))))){

                    Toast.makeText(activity_InsertarEmpleado.this,"Falta por introducir datos",Toast.LENGTH_SHORT).show();
                }else{
                    int telefono = Integer.parseInt(edtTelefono.getText().toString());
                    Empleados e = new Empleados(id, nombre.trim(), apellido.trim(), telefono, idhorario);
                    databaseReference.child("Empleado").child(e.getIdEmpleado()).setValue(e);

                    Toast.makeText(activity_InsertarEmpleado.this, "Empleado Introducido correctamente", Toast.LENGTH_SHORT).show();
                    Limpiar();
                }

            }
        });
    }


    private void inicializarDatabase() {

        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

    private void Limpiar() {

        edtNombre.setText("");
        edtapellido.setText("");
        edtTelefono.setText("");

    }

    private void CargaHorarios(){
        databaseReference.child("Horario").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                Horarios.clear();
                for (DataSnapshot objSnapShot : datasnapshot.getChildren()) {
                    Horarios e = objSnapShot.getValue(Horarios.class);
                    Horarios.add(String.valueOf(e.getIdHorario()));
                    ArrayAdapter<String> a = new ArrayAdapter<String>(activity_InsertarEmpleado.this, android.R.layout.simple_spinner_item, Horarios);
                    a.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    cmbHorario.setAdapter(a);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}