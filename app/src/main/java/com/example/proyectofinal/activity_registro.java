package com.example.proyectofinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.format.Time;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.proyectofinal.Adaptadores.AdaptadorMesas;
import com.example.proyectofinal.pojos.Mesas;
import com.example.proyectofinal.pojos.Registros;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

public class activity_registro extends AppCompatActivity {
    private EditText nombre, telf1, telf2, telf3;
    private Button btnNota, btnEnviar;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    int cont = 0;    String selc;
    ArrayList<String> m = new ArrayList<String>();
    private Spinner cmbMesas;
    Mesas mesa;
    private Spinner cmbPersonas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        nombre = findViewById(R.id.edtNombreRegistro);
        telf1 = findViewById(R.id.edttlf1);
        telf2 = findViewById(R.id.edttlf2);
        telf3 = findViewById(R.id.edttlf3);
        cmbMesas = (Spinner) findViewById(R.id.Combo);
        cmbPersonas = (Spinner) findViewById(R.id.cmbPersonas);
        btnEnviar = findViewById(R.id.btnAddRegistro);

        btnNota = (Button) findViewById(R.id.btnTomar_Nota);

        inicializarDatabase();
        llenarArray();

        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.NumeroPersonas, R.layout.support_simple_spinner_dropdown_item);
        cmbPersonas.setAdapter(adapter);


        btnNota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity_registro.this, activity_TomarNota.class);
                intent.putExtra("ventana", "registro");
                intent.putExtra("nota", "");
                intent.putExtra("mesa", Integer.parseInt(cmbMesas.getSelectedItem().toString()));
                startActivity(intent);
            }
        });

        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                Date date = new Date();
                String fecha = dateFormat.format(date);
                Time now = new Time();
                now.setToNow();
                String idRegistro = UUID.randomUUID().toString();
                int nmesa = Integer.parseInt(cmbMesas.getSelectedItem().toString());
                int personas = Integer.parseInt(cmbPersonas.getSelectedItem().toString());
                String name = nombre.getText().toString();
                int T1 = 0;
                int T2 = 0;
                int T3 = 0;
                if (!telf1.getText().toString().equalsIgnoreCase("")) {
                    T1 = Integer.parseInt(telf1.getText().toString());
                }
                if (!telf2.getText().toString().equalsIgnoreCase("")) {
                    T2 = Integer.parseInt(telf2.getText().toString());
                }
                if (!telf3.getText().toString().equalsIgnoreCase("")) {
                    T3 = Integer.parseInt(telf3.getText().toString());
                }
                if(name.equalsIgnoreCase("")){
                    Toast.makeText(activity_registro.this,"Falta por introducir datos",Toast.LENGTH_SHORT).show();

                }else{

                    Registros r = new Registros(idRegistro, nmesa, name, T1, T2, T3, fecha, "", now.hour, now.minute, 99, 99, personas, "N");

                    databaseReference.child("Registro").child(r.getIdRegistro()).setValue(r);


                    Mesas m = new Mesas();
                    m.setNumeroMesa(Integer.parseInt(cmbMesas.getSelectedItem().toString()));
                    m.setEstado("O");
                    databaseReference.child("Mesa").child(String.valueOf(m.getNumeroMesa())).setValue(m);
                    Toast.makeText(activity_registro.this, "Registro Introducido", Toast.LENGTH_SHORT).show();

                    finish();

                }

            }
        });


    }

    private void llenarArray() {
        databaseReference.child("Mesa").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                m.clear();
                cont = 0;
                for (DataSnapshot objSnapShot : datasnapshot.getChildren()) {
                    mesa = objSnapShot.getValue(Mesas.class);
                   if (mesa.getEstado().equalsIgnoreCase("L")) {
                        m.add(String.valueOf(mesa.getNumeroMesa()));
                       ArrayAdapter<String> a = new ArrayAdapter<String>(activity_registro.this, android.R.layout.simple_spinner_item, m);
                       a.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                       cmbMesas.setAdapter(a);
                    }

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