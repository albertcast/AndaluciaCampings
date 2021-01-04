package com.example.andaluciacampings;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class InicioAplicacion extends AppCompatActivity {

    Button boton_ubicacion, boton_perfil, boton_logout, boton_ranking;
    String username;
    BottomNavigationView bottomNav;

    private boolean locationPermission = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_aplicacion);

        if(getIntent().hasExtra("perfil_guardar")){
            Toast.makeText(this, getIntent().getStringExtra("perfil_guardar"),Toast.LENGTH_LONG).show();
        }

        boton_ubicacion = (Button) findViewById(R.id.button_maps);
        boton_perfil = (Button) findViewById(R.id.button_perfil_inicio);
        boton_logout = (Button) findViewById(R.id.button_logout);
        boton_ranking = (Button) findViewById(R.id.button_ranking_inicio);
        username = getIntent().getStringExtra("usuario");

        boton_ubicacion.setText(R.string.boton_location);
        boton_perfil.setText(R.string.boton_perfil);

        bottomNav = (BottomNavigationView) findViewById(R.id.bottomNavigation);
        bottomNav.setOnNavigationItemSelectedListener(this::onNavigationItemSelected);
    }

    public void Perfil(){
        Intent intento = new Intent(InicioAplicacion.this, Perfil.class);
        intento.putExtra("usuario", username);
        startActivity(intento);
    }

    public void Ubicacion() {
        Intent intento = new Intent(this, MapsActivity.class);
        intento.putExtra("usuario", username);
        startActivity(intento);
    }



    public void Logout(){
        boton_logout.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intento = new Intent(InicioAplicacion.this, Login.class);
                        startActivity(intento);
                    }
                }
        );
    }

    public void Ranking(){
        Intent intento = new Intent(InicioAplicacion.this, RankingPersonas.class);
        intento.putExtra("usuario",username);
        startActivity(intento);
    }


    /*public void Campings(){
        Intent intento = new Intent(InicioAplicacion.this, ListaCampings.class);
        intento.putExtra("usuario", username);
        startActivity(intento);
    }*/


    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        bottomNav.postDelayed(() -> {
            int itemId = item.getItemId();
            if (itemId == R.id.map) {
                Ubicacion();
            } else if (itemId == R.id.perfil) {
                Perfil();
            } else if (itemId == R.id.rank) {
                Ranking();
            } else if (itemId == R.id.listaCampings){
                //Campings();
            }
            finish();
        }, 300);
        return true;
    }
}