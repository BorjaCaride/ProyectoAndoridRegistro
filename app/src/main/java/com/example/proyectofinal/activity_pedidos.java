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
import android.widget.ListView;
import android.widget.Toast;

import com.example.proyectofinal.Adaptadores.AdaptadorEmpleados;
import com.example.proyectofinal.Adaptadores.AdaptadorPedidos;
import com.example.proyectofinal.pojos.Empleados;
import com.example.proyectofinal.pojos.Mesas;
import com.example.proyectofinal.pojos.Pedidos;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class activity_pedidos extends AppCompatActivity {

        private ListView lista;
    ArrayList<Pedidos> ArrayPedidos = new ArrayList<Pedidos>();
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    private Pedidos pedidoSeleccionado = new Pedidos();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedidos);

        lista = findViewById(R.id.listaPedidos);
        inicializarDatabase();
        listarDatos();

        lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                pedidoSeleccionado = (Pedidos) parent.getItemAtPosition(position);

                return false;
            }
        });

        registerForContextMenu(lista);


    }

    private void listarDatos() {
        databaseReference.child("Pedido").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                ArrayPedidos.clear();
                for (DataSnapshot objSnapShot : datasnapshot.getChildren()) {
                    Pedidos p = objSnapShot.getValue(Pedidos.class);
                    ArrayPedidos.add(p);
                    AdaptadorPedidos adaptador = new AdaptadorPedidos(activity_pedidos.this, ArrayPedidos);
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
        inflater.inflate(R.menu.menu_lv_pedidos, menu);
    }


    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()) {
            case R.id.verPedido:

                Intent intent = new Intent(activity_pedidos.this, activity_TomarNota.class);
                intent.putExtra("ventana","pedidos");
                intent.putExtra("nota", pedidoSeleccionado.getPedido());
                intent.putExtra("mesa", 0);
                startActivity(intent);

                return true;
            case R.id.prepararPedido:
                AlertDialog.Builder ventana = new AlertDialog.Builder(this);
                ventana.setTitle("Atención");
                ventana.setMessage("¿Empezar el pedido?");
                ventana.setIcon(android.R.drawable.ic_dialog_alert);

                ventana.setCancelable(false);
                // Método que indica el texto del botón y la clase anónima que capturará su evento onClick
                ventana.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Pedidos p = new Pedidos();
                        p.setIdPedido(pedidoSeleccionado.getIdPedido());
                        p.setEstado("P");
                        p.setMesa(pedidoSeleccionado.getMesa());
                        p.setPedido(pedidoSeleccionado.getPedido());
                        databaseReference.child("Pedido").child(p.getIdPedido()).setValue(p);
                        Toast.makeText(activity_pedidos.this, "Pedido en Preparacion", Toast.LENGTH_LONG).show();
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
            case R.id.PedidoListo:
                AlertDialog.Builder ventana_listo = new AlertDialog.Builder(this);
                ventana_listo.setTitle("Atención");
                ventana_listo.setMessage("¿Pedido Listo?");
                ventana_listo.setIcon(android.R.drawable.ic_dialog_alert);

                ventana_listo.setCancelable(false);
                // Método que indica el texto del botón y la clase anónima que capturará su evento onClick
                ventana_listo.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Pedidos p = new Pedidos();
                        p.setIdPedido(pedidoSeleccionado.getIdPedido());
                        p.setMesa(pedidoSeleccionado.getMesa());
                        p.setPedido(pedidoSeleccionado.getPedido());
                        p.setEstado("L");
                        databaseReference.child("Pedido").child(p.getIdPedido()).setValue(p);
                        Toast.makeText(activity_pedidos.this, "Pedido listo", Toast.LENGTH_LONG).show();
                    }
                });
                ventana_listo.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                ventana_listo.show();
                return true;

            case R.id.PedidoEntregado:
                AlertDialog.Builder ventana_entregar = new AlertDialog.Builder(this);
                ventana_entregar.setTitle("Atención");
                ventana_entregar.setMessage("¿Entregar Pedido? Se borrara el pedido.");
                ventana_entregar.setIcon(android.R.drawable.ic_dialog_alert);

                ventana_entregar.setCancelable(false);
                // Método que indica el texto del botón y la clase anónima que capturará su evento onClick
                ventana_entregar.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Pedidos p = new Pedidos();
                        p.setIdPedido(pedidoSeleccionado.getIdPedido());
                        p.setEstado("E");
                        databaseReference.child("Pedido").child(p.getIdPedido()).removeValue();
                        Toast.makeText(activity_pedidos.this, "Pedido entregado y borrado", Toast.LENGTH_LONG).show();
                        finish();
                        startActivity(getIntent());
                    }
                });
                ventana_entregar.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                ventana_entregar.show();

                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }
}