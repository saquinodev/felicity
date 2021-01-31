package com.felicity.Adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.felicity.Models.Persona;
import com.felicity.R;

import java.util.ArrayList;

public class ListViewPersonasAdapter extends BaseAdapter {
    Context context;
    ArrayList<Persona> personaData;
    LayoutInflater layoutInflater;
    Persona personaModel;

    public ListViewPersonasAdapter(Context context, ArrayList<Persona> personaData) {
        this.context = context;
        this.personaData = personaData;
        layoutInflater=(LayoutInflater) context.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE
        );
    }

    @Override
    public int getCount() {
        return personaData.size();
    }

    @Override
    public Object getItem(int position) {
        return personaData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = convertView;
        if (rowView == null){
            rowView = layoutInflater.inflate(R.layout.lista_personas,
                    null,
                    true);
        }
        //enlazar vistas
        TextView nombres = rowView.findViewById(R.id.nombres);
        TextView apellido = rowView.findViewById(R.id.apellido);
        TextView DNI = rowView.findViewById(R.id.DNI);
        TextView direccion = rowView.findViewById(R.id.direccion);
        TextView telefono = rowView.findViewById(R.id.telefono);

        personaModel = personaData.get(position);
        nombres.setText(personaModel.getNombres());
        apellido.setText(personaModel.getApellido());
        DNI.setText(personaModel.getDNI());
        direccion.setText(personaModel.getDireccion());
        telefono.setText(personaModel.getTelefono());

        return rowView;
    }
}
