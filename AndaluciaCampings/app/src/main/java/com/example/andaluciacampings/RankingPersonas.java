package com.example.andaluciacampings;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class RankingPersonas extends AppCompatActivity {

    List<PersonaListView> personaList;
    ListView listView_ranking;
    EditText editText_nombre;
    TextView textView_nombre;
    Button button_anterior, button_siguiente, button_buscar;
    DatabaseHelper myDb;
    int offset = 0, limit = 6, offset_buscar = 0;
    BottomNavigationView bottomNav;
    private boolean valido = true;
    private String blockCharacterSet = "[]{}(),;.:-_~#^|$%&*!";

    private InputFilter filter = new InputFilter() {

        @Override
        public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {

            if (source != null && blockCharacterSet.contains(("" + source))) {
                valido = false;
            } else {
                valido = true;
            }
            return null;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking_personas);



        listView_ranking = (ListView) findViewById(R.id.listView_ranking);
        editText_nombre = (EditText) findViewById(R.id.editText_nombre_ranking);
        textView_nombre = (TextView) findViewById(R.id.textView_nombre_ranking);
        button_anterior = (Button) findViewById(R.id.button_anterior_ranking);
        button_siguiente = (Button) findViewById(R.id.button_siguiente_ranking);
        button_buscar = (Button) findViewById(R.id.button_buscar_ranking);

        bottomNav = (BottomNavigationView) findViewById(R.id.bottomNavigation);
        bottomNav.setOnNavigationItemSelectedListener(this::onNavigationItemSelected);

        bottomNav.getMenu().getItem(0).setChecked(false);
        bottomNav.getMenu().getItem(3).setChecked(true);

        textView_nombre.setText(R.string.textView_nombre_string);
        button_siguiente.setText(R.string.boton_siguiente);
        button_anterior.setText(R.string.boton_anterior);
        button_buscar.setText(R.string.boton_buscar);

        editText_nombre.setFilters(new InputFilter[]{filter});

        myDb = new DatabaseHelper(this);

        Adaptar(offset);
        Anterior();
        Siguiente();
        Buscar();
    }

    public void Adaptar(int offset){
        Cursor res = myDb.getAllData(offset);

        personaList = new ArrayList<>();

        res.moveToFirst();
        while(!res.isAfterLast()) {
            personaList.add(new PersonaListView(res.getString(3), res.getString(4), res.getInt(5)));
            res.moveToNext();
        }
        res.close();

        MyCustomListAdapter adapter = new MyCustomListAdapter(this, R.layout.my_list_item, personaList);
        listView_ranking.setAdapter(adapter);
    }

    public void Adaptar(String username){
        Cursor res = myDb.getAllData(offset);
        boolean encontrado = false;
        res.moveToFirst();
        while(!res.isAfterLast() && !encontrado) {
            if (username.equalsIgnoreCase(res.getString(3))) {
                encontrado = true;
            } else {
                offset_buscar++;
            }
            res.moveToNext();
        }
        res.close();

        Adaptar(offset_buscar);

        offset_buscar = 0;
    }

    public void Anterior(){
        button_anterior.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (offset > 0) {
                            offset-= limit;
                            Adaptar(offset);
                        }
                    }
                }
        );
    }

    public void Siguiente(){
        button_siguiente.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor res = myDb.getAllData(offset+limit);
                        if(res.getCount()!= 0){
                            offset+=limit;
                            Adaptar(offset);
                        }
                    }
                }
        );
    }

    public void Buscar(){
        button_buscar.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(valido){
                            if(!TextUtils.isEmpty(editText_nombre.getText().toString())){
                                Cursor res = myDb.getUsername(editText_nombre.getText().toString());
                                if (res.moveToNext()){
                                    Adaptar(res.getString(3));
                                }
                            }
                        } else {
                            AlertDialog.Builder builder = new AlertDialog.Builder(RankingPersonas.this);
                            builder.setCancelable(true);
                            builder.setTitle(R.string.Caracteres_especiales_string);
                            builder.setMessage(R.string.Que_caraceres_especiales_string);
                            builder.show();                          }
                    }
                }
        );
    }

    public void Home(){
        Intent intento = new Intent(RankingPersonas.this, InicioAplicacion.class);
        startActivity(intento);
    }

    public void Perfil(){
        Intent intento = new Intent(RankingPersonas.this, Perfil.class);
        startActivity(intento);
    }

   public void Ubicacion() {
        Intent intento = new Intent(this, MapsActivity.class);
        startActivity(intento);
    }

    public void Ranking(){
        Intent intento = new Intent(RankingPersonas.this, RankingPersonas.class);
        startActivity(intento);
    }

    public void Campings(){
        Intent intento = new Intent(RankingPersonas.this, ListaCampings.class);
        startActivity(intento);
    }


    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        bottomNav.postDelayed(() -> {
            int itemId = item.getItemId();
            if(itemId == R.id.inicioAplicacion){
                Home();
            } else if (itemId == R.id.map) {
                Ubicacion();
            } else if (itemId == R.id.perfil) {
                Perfil();
            } else if (itemId == R.id.rank) {
                Ranking();
            } else if (itemId == R.id.listaCampings){
                Campings();
            }
            finish();
        }, 300);
        return true;
    }
}