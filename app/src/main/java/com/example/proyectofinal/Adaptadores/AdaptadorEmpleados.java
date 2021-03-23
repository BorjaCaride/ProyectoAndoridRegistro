package com.example.proyectofinal.Adaptadores;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.example.proyectofinal.R;
import com.example.proyectofinal.pojos.Empleados;

import java.util.ArrayList;

public class AdaptadorEmpleados extends BaseAdapter {

    protected Activity activity;
    protected ArrayList<Empleados> items;

    public AdaptadorEmpleados(Activity activity, ArrayList<Empleados> items) {
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
            v = inf.inflate(R.layout.fila_lv_empleados, null);
        }

        //creamos un objeto de la clase WebsEsTl
        Empleados empleado = items.get(position);

        //Asignamos los recursos a las variable
        TextView Nombre = (TextView) v.findViewById(R.id.lblFilaNombre);
        TextView apellido = (TextView) v.findViewById(R.id.lblFilaApellido);
        TextView telefono = (TextView) v.findViewById(R.id.lblFilaTelefono);

        //Enviamos informacion a la vista apartir de la informacion que contenga la clase:
        Nombre.setText(empleado.getNombre().toString());
        apellido.setText(empleado.getApellido().toString());
        telefono.setText(String.valueOf(empleado.getTelefono()));
        return v;
    }


}
