package com.example.andaluciacampings;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class ListaCampings extends AppCompatActivity {

    BottomNavigationView bottomNav;
    String username;
    private String[] names = {"Camping Cabopino", "Campings Los Escullos", "Camping Giralda", "Campings Canos del Meca", "Camping La Rosaleda",
            "Camping San Jose", "Camping Almayate Costa", "Camping Las Lomas", "Camping Playa Agua Dulce", "Camping Almanat", "Camping Valdevaqueros",
            "Camping Balerma", "Camping La Aldea", "Camping Tarifa", "Camping Luz"};
    private int[] images = {R.drawable._1, R.drawable._2, R.drawable._3, R.drawable._4, R.drawable._5, R.drawable._6, R.drawable._7, R.drawable._8,
            R.drawable._9, R.drawable._10, R.drawable._11, R.drawable._12, R.drawable._13, R.drawable._14, R.drawable._15};
    private List<Camping> campingList = new ArrayList<>();
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_campings);
        recyclerView = findViewById(R.id.home_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        prepareTheList();
        RecyclerAdapter adapter = new RecyclerAdapter(campingList);
        recyclerView.setAdapter(adapter);
        username = getIntent().getStringExtra("usuario");
        bottomNav = (BottomNavigationView) findViewById(R.id.bottomNavigation);
        bottomNav.setOnNavigationItemSelectedListener(this::onNavigationItemSelected);

        bottomNav.getMenu().getItem(0).setChecked(false);
        bottomNav.getMenu().getItem(4).setChecked(true);
    }

    private void prepareTheList(){

        int count = 0;
        for(String name: names){
            Camping camping = new Camping(name, images[count]);
            campingList.add(camping);
            count++;
        }
    }

    public void Home(){
        Intent intento = new Intent(ListaCampings.this, InicioAplicacion.class);
        intento.putExtra("usuario",username);
        startActivity(intento);
    }

    public void Perfil(){
        Intent intento = new Intent(ListaCampings.this, Perfil.class);
        intento.putExtra("usuario", username);
        startActivity(intento);
    }

    public void Ubicacion() {
        Intent intento = new Intent(this, MapsActivity.class);
        intento.putExtra("usuario", username);
        startActivity(intento);
    }

    public void Ranking(){
        Intent intento = new Intent(ListaCampings.this, RankingPersonas.class);
        intento.putExtra("usuario",username);
        startActivity(intento);
    }

    public void Campings(){
        Intent intento = new Intent(ListaCampings.this, ListaCampings.class);
        intento.putExtra("usuario", username);
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