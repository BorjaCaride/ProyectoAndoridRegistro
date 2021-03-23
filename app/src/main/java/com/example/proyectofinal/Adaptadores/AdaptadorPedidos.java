package com.example.proyectofinal.Adaptadores;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.proyectofinal.R;
import com.example.proyectofinal.pojos.Mesas;
import com.example.proyectofinal.pojos.Pedidos;

import java.util.ArrayList;
import java.util.ResourceBundle;

import io.grpc.internal.SharedResourceHolder;

public class AdaptadorPedidos extends BaseAdapter {
    protected Activity activity;
    protected ArrayList<Pedidos> items;

    public AdaptadorPedidos(Activity activity, ArrayList<Pedidos> items) {
        this.activity = activity;
        this.items = items;
    }
    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if(convertView == null){
            LayoutInflater inf = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inf.inflate(R.layout.fila_lv_pedidos, null);
        }
        Pedidos pedido = items.get(position);

        TextView NMesa = (TextView) v.findViewById(R.id.lblFilaMesaPedido);
        TextView estado = (TextView) v.findViewById(R.id.lblFilaEstadoPedido);
        LinearLayout fondo = v.findViewById(R.id.lyFilaPedidos);


        NMesa.setText(String.valueOf(pedido.getMesa()));


        if (pedido.getEstado().equalsIgnoreCase("F")){
            fondo.setBackgroundColor(activity.getResources().getColor(R.color.pendiente));
            estado.setText("Pendiente");
        }
        if (pedido.getEstado().equalsIgnoreCase("L")){
            fondo.setBackgroundColor(activity.getResources().getColor(R.color.listo));
            estado.setText("Listo");
        }
        if (pedido.getEstado().equalsIgnoreCase("P")){
            fondo.setBackgroundColor(activity.getResources().getColor(R.color.espera));
            estado.setText("En Preparación");
        }

        if (pedido.getEstado().equalsIgnoreCase("E")){
            fondo.setBackgroundColor(activity.getResources().getColor(R.color.fondolista));
            estado.setText("Entregado");
        }
        return v;

    }
}
