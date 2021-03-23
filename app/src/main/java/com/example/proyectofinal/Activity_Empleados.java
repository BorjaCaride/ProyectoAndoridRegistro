package com.example.proyectofinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.proyectofinal.Adaptadores.AdaptadorEmpleados;
import com.example.proyectofinal.pojos.Empleados;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Activity_Empleados extends Activity {

    private Button btnInsertarEmpleado;
    private ListView lista;
    int cont = 0;
    ArrayList<Empleados> ArrayEmpleados = new ArrayList<Empleados>();
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    Empleados empleadoSeleccionado = new Empleados();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__empleados);
        inicializarDatabase();
        listarDatos();
        lista = findViewById(R.id.listaEmpleados);
        lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                empleadoSeleccionado = (Empleados) parent.getItemAtPosition(position);

                return false;
            }
        });

        btnInsertarEmpleado = findViewById(R.id.btnInsertarEmpleado);

        registerForContextMenu(lista);

        btnInsertarEmpleado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent ventana_InsertarEmpleado = new Intent(v.getContext(), activity_InsertarEmpleado.class);
                startActivity(ventana_InsertarEmpleado);
            }
        });


    }

    private void listarDatos() {
        databaseReference.child("Empleado").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                ArrayEmpleados.clear();
                for (DataSnapshot objSnapShot : datasnapshot.getChildren()) {
                    Empleados e = objSnapShot.getValue(Empleados.class);
                    ArrayEmpleados.add(e);
                    AdaptadorEmpleados adaptador = new AdaptadorEmpleados(Activity_Empleados.this, ArrayEmpleados);
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
        inflater.inflate(R.menu.menu_lv_empleados, menu);
    }


    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()) {
            case R.id.modificar:
                /*Intent v = new Intent(Activity_Empleados.this,activity_InsertarEmpleado.class);
                startActivity(v);*/
                Toast.makeText(Activity_Empleados.this, "Falta", Toast.LENGTH_LONG).show();
                return true;
            case R.id.eliminar:
                AlertDialog.Builder ventana = new AlertDialog.Builder(this);
                ventana.setTitle("Atención");
                ventana.setMessage("¿Esta seguro que desea eliminar el empleado?");
                ventana.setIcon(android.R.drawable.ic_dialog_alert);

                ventana.setCancelable(false);
                // Método que indica el texto del botón y la clase anónima que capturará su evento onClick
                ventana.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Empleados e = new Empleados();
                        e.setIdEmpleado(empleadoSeleccionado.getIdEmpleado());
                        databaseReference.child("Empleado").child(e.getIdEmpleado()).removeValue();
                        Toast.makeText(Activity_Empleados.this, "El empleado ha sido eliminado", Toast.LENGTH_LONG).show();
                    }
                });
                ventana.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                ventana.show();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }


}