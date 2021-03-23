package com.example.proyectofinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.proyectofinal.Adaptadores.AdaptadorMesas;
import com.example.proyectofinal.pojos.Horarios;
import com.example.proyectofinal.pojos.Mesas;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class activity_InsertarHorario extends AppCompatActivity {
    private EditText edtNombre, edtHoraEntrada, edtMinutoEntrada, HoraSalida, MinutoSalida;
    private CheckBox Lunes,Martes, Miercoles, Jueves, Viernes, Sabado, Domingo;
    private String L = "N", M = "N", X = "N", J = "N", V = "N", S = "N", D = "N";
    private Button btnAdd;

    Horarios ultimoHorario = new Horarios();
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    private int cont=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__insertar_horario);
        edtNombre = findViewById(R.id.edtNombreHorario);
        btnAdd = findViewById(R.id.btnAddHorario);
        edtHoraEntrada = findViewById(R.id.edtHoraEntrada);
        edtMinutoEntrada = findViewById(R.id.edtMinutoEntrada);
        HoraSalida = findViewById(R.id.edtHorasalida);
        MinutoSalida = findViewById(R.id.edtMinutoSalida);
        Lunes = findViewById(R.id.Lunes);
        Martes = findViewById(R.id.Martes);
        Miercoles = findViewById(R.id.Miercoles);
        Jueves = findViewById(R.id.Jueves);
        Viernes = findViewById(R.id.Viernes);
        Sabado = findViewById(R.id.Sabado);
        Domingo = findViewById(R.id.Domingo);

            inicializarDatabase();
            contarHorarios();

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
    if (Lunes.isChecked()){
            L = "S";
            cont =1; }
                if (Martes.isChecked()){
                    M = "S";
                    cont =1; }
                if (Miercoles.isChecked()){
                    X = "S";
                    cont =1; }
                if (Jueves.isChecked()){
                    J = "S";
                    cont =1; }
                if (Viernes.isChecked()){
                    V = "S";
                    cont =1; }
                if (Sabado.isChecked()){
                    S = "S";
                    cont =1; }
                if (Domingo.isChecked()){
                    D = "S";
                    cont =1; }



                if (cont == 1){
                    String nombre = edtNombre.getText().toString();
                    int HE = Integer.parseInt(edtHoraEntrada.getText().toString()) ;
                    int ME = Integer.parseInt(edtMinutoEntrada.getText().toString()) ;
                    int HS = Integer.parseInt(HoraSalida.getText().toString()) ;
                    int MS = Integer.parseInt(MinutoSalida.getText().toString()) ;
                    int id = ultimoHorario.getIdHorario()+1;
                    Horarios h = new Horarios(id,nombre,HE,ME,HS,MS,L,M,X,J,V,S,D);

                    databaseReference.child("Horario").child(String.valueOf(id)).setValue(h);

                }else {
                    Toast.makeText(activity_InsertarHorario.this, "Seleccione 1 día mínimo", Toast.LENGTH_SHORT).show();


                }
            }
        });
    }

    private void inicializarDatabase() {

        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

    private void contarHorarios() {
        databaseReference.child("Horario").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                cont = 0;
                int ultima =0;
                for (DataSnapshot objSnapShot : datasnapshot.getChildren()) {
                    Horarios horario = new Horarios();
                    horario = objSnapShot.getValue(Horarios.class);

                    if (ultima < horario.getIdHorario() ){
                        ultimoHorario = horario;
                    }
                    ultima = horario.getIdHorario();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}