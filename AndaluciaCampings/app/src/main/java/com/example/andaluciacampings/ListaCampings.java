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

    private String[] names = {"Camping Cabopino", "Campings Los Escullos", "Camping Giralda", "Campings Canos del Meca", "Camping La Rosaleda",
            "Camping San Jose", "Camping Almayate Costa", "Camping Las Lomas", "Camping Playa Agua Dulce", "Camping Almanat", "Camping Valdevaqueros",
            "Camping Balerma", "Camping La Aldea", "Camping Tarifa", "Camping Luz"};

    private String[] locations = {"N-340, Km.194, 7, 29604 Marbella, Málaga", "Paraje Los Escullos, s/n, 04118, Almería",
            "Carr. Prov. 4117, Isla Cristina-La Antilla Km 1,5, 21410 Isla Cristina, Huelva", "Carretera Vejer - Caños de Meca, km. 10, 11159 Barbate, Cádiz",
            "Carr. del Pradillo, Km 1.3, 11140 Conil de la Frontera, Cádiz", "Lugar Pago Zahora, 17, 11159 Zahora", "N-340, Km. 267, 29749 Almayate, Málaga",
            "Carretera de Güejar Sierra km 6.5, 18160 Güejar Sierra, Granada", "Carretera A-491 Km. 6,5, 11520 Rota, Cádiz",
            "Ctra. N-340 kilómetro 269, 29749 Almayate, Málaga", "N-340, Km 75.5, 11380 Tarifa, Cádiz", "Carr. de Guardias Viejas, s/n, 04712 Balerma, Almería",
            "Carretera de El Rocío, km. 25, 21750 El Rocío, Huelva", "N-340, Km. 78, 870, 11380 Tarifa, Cádiz", "Camino de las Colas, s/n, 21410 Isla Cristina, Huelva"};

    private String[] phone_numbers = {"952 83 43 73", "950 38 98 11", "959 34 33 18", "956 43 71 20", "956 44 33 27", "956 43 70 30",
                                      "952 55 62 89", "958 48 47 42", "956 84 70 78", "952 55 64 62", "956 68 41 74", "950 93 76 37", "959 44 26 77",
                                      "956 68 47 78", "959 34 11 42"};

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
        bottomNav = (BottomNavigationView) findViewById(R.id.bottomNavigation);
        bottomNav.setOnNavigationItemSelectedListener(this::onNavigationItemSelected);

        bottomNav.getMenu().getItem(0).setChecked(false);
        bottomNav.getMenu().getItem(4).setChecked(true);
    }

    private void prepareTheList(){

        int count = 0;
        for(String name: names){
            Camping camping = new Camping(name, locations[count], phone_numbers[count], images[count]);
            campingList.add(camping);
            count++;
        }
    }

    public void Home(){
        Intent intento = new Intent(ListaCampings.this, InicioAplicacion.class);
        startActivity(intento);
    }

    public void Perfil(){
        Intent intento = new Intent(ListaCampings.this, Perfil.class);
        startActivity(intento);
    }

    public void Ubicacion() {
        Intent intento = new Intent(this, MapsActivity.class);
        startActivity(intento);
    }

    public void Ranking(){
        Intent intento = new Intent(ListaCampings.this, RankingPersonas.class);
        startActivity(intento);
    }

    public void Campings(){
        Intent intento = new Intent(ListaCampings.this, ListaCampings.class);
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