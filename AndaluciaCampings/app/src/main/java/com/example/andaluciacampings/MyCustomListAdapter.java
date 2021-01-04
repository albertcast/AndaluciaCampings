package com.example.andaluciacampings;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class MyCustomListAdapter extends ArrayAdapter<PersonaListView> {

    Context mCtx;
    int resource;
    List<PersonaListView> list;
    TextView textViewName, textViewNivel;

    public MyCustomListAdapter(Context mCtx, int resource, List<PersonaListView> list){
        super(mCtx, resource, list);

        this.mCtx = mCtx;
        this.resource = resource;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(resource, null);
        textViewName = view.findViewById(R.id.textView_nombre_listview);
        textViewNivel = view.findViewById(R.id.textView_nivel_listview);


        PersonaListView persona = list.get(position);
        System.out.println(persona.getNombre());
        System.out.println(persona.getApellido());
        System.out.println(persona.getNivel());


        if (textViewName!=null && textViewNivel!= null) {
            textViewName.setText(persona.getNombre() + " " + persona.getApellido());
            textViewNivel.setText(Integer.toString(persona.getNivel()));
        }

        return view;
    }
}
