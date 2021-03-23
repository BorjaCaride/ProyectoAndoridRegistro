package com.example.proyectofinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.proyectofinal.Adaptadores.AdaptadorEmpleados;
import com.example.proyectofinal.pojos.Empleados;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Button btnIncioSesion;
    private ListView lista;

    ArrayList<Empleados> ArrayEmpleados = new ArrayList<Empleados>();
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lista = findViewById(R.id.listalogin);

                inicializarDatabase();
                listarDatos();
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent ventana_opciones = new Intent(MainActivity.this,activity_opciones.class);
                startActivity(ventana_opciones);
            }
        });
        /*btnIncioSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ventana_opciones = new Intent(v.getContext(),activity_opciones.class);
                startActivity(ventana_opciones);
            }
        });
*/

    }

    private void listarDatos() {
        databaseReference.child("Empleado").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                ArrayEmpleados.clear();
                for (DataSnapshot objSnapShot : datasnapshot.getChildren()) {
                    Empleados e = objSnapShot.getValue(Empleados.class);
                    ArrayEmpleados.add(e);
                    AdaptadorEmpleados adaptador = new AdaptadorEmpleados(MainActivity.this, ArrayEmpleados);
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