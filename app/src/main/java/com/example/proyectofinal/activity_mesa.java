package com.example.proyectofinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.text.format.Time;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.proyectofinal.Adaptadores.AdaptadorMesas;
import com.example.proyectofinal.Adaptadores.AdaptadorMesasServicios;
import com.example.proyectofinal.pojos.Mesas;
import com.example.proyectofinal.pojos.Pedidos;
import com.example.proyectofinal.pojos.Registros;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class activity_mesa extends AppCompatActivity {

    private ListView lista;
    ArrayList<Mesas> ArrayMesas = new ArrayList<Mesas>();
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    Mesas mesa = new Mesas();
    Registros registro = new Registros();
    Mesas mesaSeleccionada = new Mesas();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mesa);
        lista = findViewById(R.id.listaMesas);
            inicializarDatabase();
            listarDatos();

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {

                mesaSeleccionada = (Mesas) arg0.getItemAtPosition(position);
                if(mesaSeleccionada.getEstado().equalsIgnoreCase("L")){
                    AlertDialog.Builder v = new AlertDialog.Builder(activity_mesa.this);
                    v.setTitle("Mesa Número : "+String.valueOf(mesaSeleccionada.getNumeroMesa()));
                    v.setMessage("Esta mesa esta libre.");
                    v.setIcon(android.R.drawable.ic_dialog_alert);
                    v.show();

                }else{
                AlertDialog.Builder ventana = new AlertDialog.Builder(activity_mesa.this);
                ventana.setTitle("Mesa Número : "+String.valueOf(mesaSeleccionada.getNumeroMesa()));
                ventana.setMessage("¿Finalizar servicio de la mesa?");
                ventana.setIcon(android.R.drawable.ic_dialog_alert);

                ventana.setCancelable(false);
                // Método que indica el texto del botón y la clase anónima que capturará su evento onClick
                ventana.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Mesas m = new Mesas();
                        m.setNumeroMesa(mesaSeleccionada.getNumeroMesa());
                        m.setEstado("L");
                        databaseReference.child("Mesa").child(String.valueOf(m.getNumeroMesa())).setValue(m);
                        buscaregistro();
                        finish();
                        startActivity(getIntent());
                        Toast.makeText(activity_mesa.this, "Servicio Finalizado", Toast.LENGTH_LONG).show();
                    }
                });
                ventana.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                ventana.show();}
            }
        });
    }


    private void listarDatos() {
        databaseReference.child("Mesa").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                ArrayMesas.clear();
                for (DataSnapshot objSnapShot : datasnapshot.getChildren()) {
                    mesa = objSnapShot.getValue(Mesas.class);
                    ArrayMesas.add(mesa);

                    AdaptadorMesasServicios adaptadorMesas = new AdaptadorMesasServicios(activity_mesa.this,ArrayMesas);
                    lista.setAdapter(adaptadorMesas);
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

    private void buscaregistro(){

        databaseReference.child("Registro").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                ArrayMesas.clear();
                for (DataSnapshot objSnapShot : datasnapshot.getChildren()) {
                    registro = objSnapShot.getValue(Registros.class);
                    ArrayMesas.add(mesa);

                    if(registro.getMesa()==mesaSeleccionada.getNumeroMesa()){
                        if(registro.getHoraS()==99){
                            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                            Date date = new Date();
                            String fecha = dateFormat.format(date);
                            Time now = new Time();
                            now.setToNow();
                            Registros r = registro;
                            r.setHoraS(now.hour);
                            r.setMinutoS(now.minute);
                            r.setFechaSalida(fecha);
                            databaseReference.child("Registro").child(String.valueOf(r.getIdRegistro())).setValue(r);


                        }
                    }

                    AdaptadorMesasServicios adaptadorMesas = new AdaptadorMesasServicios(activity_mesa.this,ArrayMesas);
                    lista.setAdapter(adaptadorMesas);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
