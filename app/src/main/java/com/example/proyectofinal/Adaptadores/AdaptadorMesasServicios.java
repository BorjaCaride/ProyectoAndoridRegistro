package com.example.proyectofinal.Adaptadores;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.proyectofinal.R;
import com.example.proyectofinal.pojos.Mesas;

import java.util.ArrayList;

public class AdaptadorMesasServicios extends BaseAdapter {

    protected Activity activity;
    protected ArrayList<Mesas> items;

    public AdaptadorMesasServicios(Activity activity, ArrayList<Mesas> items) {
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
            v = inf.inflate(R.layout.fila_lv_mesaservicio, null);
        }
        Mesas mesa = items.get(position);

        //Asignamos los recursos a las variable
        TextView NMesa = (TextView) v.findViewById(R.id.lblFilaNMesaServicio);
        TextView Estado = (TextView) v.findViewById(R.id.lblbEstadoMesaServicio);
        LinearLayout fondo = v.findViewById(R.id.lyFilaMesaServicio);
        //Enviamos informacion a la vista apartir de la informacion que contenga la clase:
        NMesa.setText(String.valueOf(mesa.getNumeroMesa()));
        Estado.setText(mesa.getEstado().toString());

        if (mesa.getEstado().equalsIgnoreCase("O")){
            fondo.setBackgroundColor(activity.getResources().getColor(R.color.pendiente));
            Estado.setText("Ocupada");
        }
        if (mesa.getEstado().equalsIgnoreCase("L")){
            fondo.setBackgroundColor(activity.getResources().getColor(R.color.listo));
            Estado.setText("Libre");
        }

        return v;
    }
}
