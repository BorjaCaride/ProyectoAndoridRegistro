package com.example.proyectofinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.proyectofinal.pojos.Empleados;
import com.example.proyectofinal.pojos.Pedidos;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

public class activity_TomarNota extends AppCompatActivity {

    private EditText edtNota;
    private Button btnEnviar, btnaceptar;
    private String pedido;
    private int mesa;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__tomar_nota);
        inicializarDatabase();
        btnEnviar = findViewById(R.id.btnEnviar);
        edtNota = findViewById(R.id.edtnota);
        btnaceptar = findViewById(R.id.btnAceptar);

        Bundle datos = this.getIntent().getExtras();



        String ventana = datos.getString("ventana");

        if(ventana.equalsIgnoreCase("pedidos")){
            pedido = datos.getString("nota");
            btnaceptar.setVisibility(View.VISIBLE);
            btnEnviar.setVisibility(View.GONE);
            edtNota.setText(pedido);
            edtNota.setEnabled(false);

        }else{
            mesa = datos.getInt("mesa");
            btnaceptar.setVisibility(View.GONE);
            btnEnviar.setVisibility(View.VISIBLE);
        }

        btnaceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String id = UUID.randomUUID().toString();
                String nota = edtNota.getText().toString();
                if (nota.equalsIgnoreCase("")) {
                    Toast.makeText(activity_TomarNota.this, "Debe intoducir el pedido.", Toast.LENGTH_LONG).show();
                } else {
                    Pedidos p = new Pedidos(id, mesa, nota, "F");
                    databaseReference.child("Pedido").child(p.getIdPedido()).setValue(p);
                    Toast.makeText(activity_TomarNota.this, "Se ha enviado el pedido", Toast.LENGTH_SHORT).show();

                    finish();
                }
            }
        });
    }


    private void inicializarDatabase() {

        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }
}