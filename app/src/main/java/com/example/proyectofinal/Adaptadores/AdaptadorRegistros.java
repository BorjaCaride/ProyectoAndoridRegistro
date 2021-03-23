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
import com.example.proyectofinal.pojos.Registros;

import java.util.ArrayList;

public class AdaptadorRegistros extends BaseAdapter {

    protected Activity activity;
    protected ArrayList<Registros> items;

    public AdaptadorRegistros(Activity activity, ArrayList<Registros> items) {
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
            v = inf.inflate(R.layout.fila_lv_visualizar, null);
        }

        //creamos un objeto de la clase WebsEsTl
        Registros registro = items.get(position);

        //Asignamos los recursos a las variable
        TextView Fecha = v.findViewById(R.id.lblFilaVisualizarFecha);
        TextView npersonas = v.findViewById(R.id.lblFilaVisualizarNPersonas);
        TextView nombre = v.findViewById(R.id.lblFilaVisualizarNombre);
        TextView HE = v.findViewById((R.id.lblFilaVisualizarHE));
        TextView ME = v.findViewById((R.id.lblFilaVisualizarME));
        TextView HS = v.findViewById((R.id.lblFilaVisualizarHS));
        TextView MS = v.findViewById((R.id.lblFilaVisualizarMS));
        TextView T1 = v.findViewById((R.id.lblFilaVisualizarTlf1));
        TextView T2 = v.findViewById((R.id.lblFilaVisualizarTlf2));
        TextView T3 = v.findViewById((R.id.lblFilaVisualizarTlf3));

        Fecha.setText(registro.getFecha());
        npersonas.setText(String.valueOf(registro.getnPersonas()));
        nombre.setText(registro.getNombre());
        HE.setText(String.valueOf(registro.getHoraE()));
        ME.setText(String.valueOf(registro.getMinutoE()));
        HS.setText(String.valueOf(registro.getHoraS()));
        MS.setText(String.valueOf(registro.getMinutoS()));
        T1.setText(String.valueOf(registro.getTelefono()));
        T2.setText(String.valueOf(registro.getTelefono2()));
        T3.setText(String.valueOf(registro.getTelefono3()));
        return v;
    }


}
