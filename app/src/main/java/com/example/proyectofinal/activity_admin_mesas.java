package com.example.proyectofinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.proyectofinal.Adaptadores.AdaptadorMesas;
import com.example.proyectofinal.pojos.Empleados;
import com.example.proyectofinal.pojos.Mesas;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.UUID;

public class activity_admin_mesas extends AppCompatActivity {
    private Button btnAddMesa;
    private Button btnDeleteMesa;
    private ListView lista;
    Mesas ultimaMesa = new Mesas();
    Mesas mesa;
    int cont;
    private Button btnInsertarMesa;
    ArrayList<Mesas> ArrayMesas = new ArrayList<Mesas>();
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    Mesas mesaSeleccionada = new Mesas();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_mesas);
        lista = findViewById(R.id.listaAdminMesas);
        btnAddMesa = findViewById(R.id.btnInsertarMesa);
        btnDeleteMesa = findViewById(R.id.btnBorrarMesa);
        inicializarDatabase();
       listarDatos();
        lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                mesaSeleccionada = (Mesas) parent.getItemAtPosition(position);

                return false;
            }
        });
        btnAddMesa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String id = UUID.randomUUID().toString();
                int nmesa = cont=cont+1;
                String estado = "L";
                Mesas mesa = new Mesas(nmesa,estado);
                databaseReference.child("Mesa").child(String.valueOf(mesa.getNumeroMesa())).setValue(mesa);
                cont =0;
                listarDatos();
                Toast.makeText(activity_admin_mesas.this, "Mesa introducida correctamente", Toast.LENGTH_SHORT).show();

            }
        });

        btnDeleteMesa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder ventana = new AlertDialog.Builder(activity_admin_mesas.this);
                ventana.setTitle("Atención");
                ventana.setMessage("¿Esta seguro que desea eliminar la mesa?");
                ventana.setIcon(android.R.drawable.ic_dialog_alert);

                ventana.setCancelable(false);
                // Método que indica el texto del botón y la clase anónima que capturará su evento onClick
                ventana.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (ultimaMesa.getEstado().equalsIgnoreCase("O")){
                            Toast.makeText(activity_admin_mesas.this,"La mesa seleccionada no se puede borrar por que esta ocupada.", Toast.LENGTH_LONG).show();
                        }else{
                            databaseReference.child("Mesa").child(String.valueOf(ultimaMesa.getNumeroMesa())).removeValue();
                            Toast.makeText(activity_admin_mesas.this, "La mesa ha sido eliminada", Toast.LENGTH_LONG).show();

                        }
                      }
                });
                ventana.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                ventana.show();
            }
        });


    }


    private void listarDatos() {
        databaseReference.child("Mesa").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                ArrayMesas.clear();
                cont = 0;
                int ultima =0;
                for (DataSnapshot objSnapShot : datasnapshot.getChildren()) {
                    mesa = objSnapShot.getValue(Mesas.class);
                    ArrayMesas.add(mesa);


                    if (ultima < mesa.getNumeroMesa() ){
                        ultimaMesa = mesa;
                    }

                    cont =cont+1;
                    AdaptadorMesas adaptadorMesas = new AdaptadorMesas(activity_admin_mesas.this,ArrayMesas);
                    lista.setAdapter(adaptadorMesas);
                     ultima = mesa.getNumeroMesa();
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