package com.example.proyectofinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.proyectofinal.Adaptadores.AdaptadorHorarios;
import com.example.proyectofinal.Adaptadores.AdaptadorRegistros;
import com.example.proyectofinal.pojos.Mesas;
import com.example.proyectofinal.pojos.Pedidos;
import com.example.proyectofinal.pojos.Registros;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class activity_visualizacion extends AppCompatActivity {
    private ListView lista;
    private Button filtro;
    private EditText edtnumero;
    int numero;
    String fecha;
    String f = "N";
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    ArrayList<Registros> ArrayRegistros = new ArrayList<Registros>();
    Registros registro = new Registros();
    Registros registroSeleccionado = new Registros();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizacion);

        lista=findViewById(R.id.listaVisualizar);
        filtro = findViewById(R.id.btnFiltroVisualizar);
        edtnumero = findViewById(R.id.edtnumeroFiltro);

        inicializarDatabase();
        listarDatos();
        registerForContextMenu(lista);
        filtro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String n = String.valueOf(edtnumero.getText().toString());
                if(n.equalsIgnoreCase("")){

                }else{
                    numero = Integer.parseInt(edtnumero.getText().toString());
                    f="S";
                    listarDatos();
                }

            }
        });
        lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                registroSeleccionado = (Registros) parent.getItemAtPosition(position);

                return false;
            }
        });


    }

    private void listarDatos() {
        databaseReference.child("Registro").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                ArrayRegistros.clear();
                int cont = 0;
                String estado = "O";
                for (DataSnapshot objSnapShot : datasnapshot.getChildren()) {
                    registro = objSnapShot.getValue(Registros.class);

                    if(f.equalsIgnoreCase("F")){

                        if(registro.getFecha().equalsIgnoreCase(fecha))
                        {
                            ArrayRegistros.add(registro);
                        }

                    }else {

                        if (f.equalsIgnoreCase("S")) {
                            if (numero == 0) {
                                ArrayRegistros.add(registro);
                            } else {
                                if ((registro.getTelefono() == numero) || (registro.getTelefono2() == numero) || (registro.getTelefono3() == numero)) {
                                    ArrayRegistros.add(registro);
                                }
                            }
                        } else {
                            ArrayRegistros.add(registro);
                        }
                    }
                    AdaptadorRegistros adaptador = new AdaptadorRegistros(activity_visualizacion.this, ArrayRegistros);
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


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_lv_registros, menu);
    }


    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()) {
            case R.id.filtrofecha:
                    f = "F";
                    fecha = registroSeleccionado.getFecha();
                    listarDatos();
            default:
                return super.onContextItemSelected(item);
        }
    }
}