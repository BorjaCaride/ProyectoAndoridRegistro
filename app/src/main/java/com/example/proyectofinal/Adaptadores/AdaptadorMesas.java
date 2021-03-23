package com.example.proyectofinal.Adaptadores;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.proyectofinal.R;
import com.example.proyectofinal.pojos.Empleados;
import com.example.proyectofinal.pojos.Mesas;

import java.util.ArrayList;

public class AdaptadorMesas extends BaseAdapter {
    protected Activity activity;
    protected ArrayList<Mesas> items;

    public AdaptadorMesas(Activity activity, ArrayList<Mesas> items) {
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
            v = inf.inflate(R.layout.fila_lv_mesas, null);
        }
        Mesas mesa = items.get(position);

        //Asignamos los recursos a las variable
        TextView NMesa = (TextView) v.findViewById(R.id.lblFilaNMesa);
        //Enviamos informacion a la vista apartir de la informacion que contenga la clase:
        NMesa.setText(String.valueOf(mesa.getNumeroMesa()));
        return v;
    }
}
