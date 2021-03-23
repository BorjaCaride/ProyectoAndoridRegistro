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
import com.example.proyectofinal.pojos.Horarios;

import java.util.ArrayList;

public class AdaptadorHorarios extends BaseAdapter {


    protected Activity activity;
    protected ArrayList<Horarios> items;

    public AdaptadorHorarios(Activity activity, ArrayList<Horarios> items) {
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
            v = inf.inflate(R.layout.fila_lv_horarios, null);
        }

        //creamos un objeto de la clase WebsEsTl
        Horarios horario = items.get(position);

        //Asignamos los recursos a las variable
        TextView edtNombre = v.findViewById(R.id.lblFilaNombreHorario);
        TextView edtHoraEntrada = v.findViewById(R.id.lblFilaHEntrada);
        TextView edtMinutoEntrada = v.findViewById(R.id.lblFilaMEntrada);
        TextView HoraSalida = v.findViewById(R.id.lblFilaHSalida);
        TextView MinutoSalida = v.findViewById(R.id.lblFilaMSalida);
        TextView Lunes = (TextView) v.findViewById(R.id.lblFilaLunes);
        TextView Martes = (TextView) v.findViewById(R.id.lblFilaMartes);
        TextView Miercoles = (TextView) v.findViewById(R.id.lblFilaMiercoles);
        TextView Jueves = (TextView) v.findViewById(R.id.lblFilaJueves);
        TextView Viernes = (TextView) v.findViewById(R.id.lblFilaViernes);
        TextView Sabado = (TextView) v.findViewById(R.id.lblFilaSabado);
        TextView Domingo = (TextView) v.findViewById(R.id.lblFilaDomingo);

        //Enviamos informacion a la vista apartir de la informacion que contenga la clase:
        edtNombre.setText(horario.getNombre());
        edtHoraEntrada.setText(String.valueOf(horario.getHoraEntrada()));
        edtMinutoEntrada.setText(String.valueOf(horario.getMinutoEntrada()));
        HoraSalida.setText(String.valueOf(horario.getHoraSalida()));
        MinutoSalida.setText(String.valueOf(horario.getMinutoSalida()));
        Lunes.setText(horario.getLunes().toString());
        Martes.setText(horario.getMartes().toString());
        Miercoles.setText(horario.getMiercoles().toString());
        Jueves.setText(horario.getJueves().toString());
        Viernes.setText(horario.getViernes().toString());
        Sabado.setText(horario.getSabado().toString());
        Domingo.setText(horario.getDomingo().toString());
        return v;
    }
}
