package com.example.andaluciacampings;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Perfil extends AppCompatActivity {

    DatabaseHelper myDb;
    EditText editText_nombre, editText_apellidos;
    TextView textView_nombre, textView_apellidos, textView_nivel, textView_experiencia;
    Button boton_logout, boton_guardar;
    String username;
    ProgressBar progressBar_experiencia;
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
        setContentView(R.layout.activity_perfil);
        username = UsuarioAplicacion.get().getNombre();
        editText_nombre = (EditText) findViewById(R.id.editText_nombre_perfil);
        editText_apellidos = (EditText) findViewById(R.id.editText_apellidos_perfil);
        textView_nombre = (TextView) findViewById(R.id.textView_nombre_perfil);
        textView_apellidos = (TextView) findViewById(R.id.textView_apellido_perfil);
        textView_experiencia = (TextView) findViewById(R.id.textView_experiencia_perfil);
        textView_nivel = (TextView) findViewById(R.id.textView_nivel_numero_perfil);
        boton_logout = (Button) findViewById(R.id.button_logout_perfil);
        boton_guardar = (Button) findViewById(R.id.button_guardar_perfil);
        progressBar_experiencia = (ProgressBar) findViewById(R.id.progressBar_experiencia_perfil);
        progressBar_experiencia.setIndeterminate(false);

        bottomNav = (BottomNavigationView) findViewById(R.id.bottomNavigation);
        bottomNav.setOnNavigationItemSelectedListener(this::onNavigationItemSelected);

        bottomNav.getMenu().getItem(0).setChecked(false);
        bottomNav.getMenu().getItem(2).setChecked(true);

        textView_nombre.setText(R.string.textView_nombre_string);
        textView_apellidos.setText(R.string.textView_apellido_string);
        boton_logout.setText(R.string.boton_logout);
        boton_guardar.setText(R.string.boton_guardar);
        InputFilter[] inputFilters = new InputFilter[]{filter};

        editText_nombre.setFilters(inputFilters);
        editText_apellidos.setFilters(inputFilters);

        progressBar_experiencia.setMax(100);


        myDb = new DatabaseHelper(this);

        Cursor res = myDb.getUsername(username);
        if (res.moveToNext()){
            editText_nombre.setText(res.getString(3));
            editText_apellidos.setText(res.getString(4));
            textView_nivel.setText(Integer.toString(res.getInt(5)));
            textView_experiencia.setText(Integer.toString(res.getInt(6))+" / 100");
            progressBar_experiencia.setProgress(res.getInt(6));
        }



        Guardar();
        Logout();


    }




    public void Guardar(){
        boton_guardar.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor res = myDb.getUsername(username);
                        if (valido) {
                            if (res.moveToNext()) {
                                myDb.updateData(res.getString(0), editText_nombre.getText().toString(), editText_apellidos.getText().toString());
                                Toast.makeText(Perfil.this, R.string.Los_cambios_han_sido_guardados_string, Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(Perfil.this, R.string.No_se_han_podido_guardar_los_cambios_string, Toast.LENGTH_LONG).show();
                            }
                        } else {
                            AlertDialog.Builder builder = new AlertDialog.Builder(Perfil.this);
                            builder.setCancelable(true);
                            builder.setTitle(R.string.Caracteres_especiales_string);
                            builder.setMessage(R.string.Que_caraceres_especiales_string);
                            builder.show();                          }
                    }
                }
        );
    }

    public void Home(){
        Intent intento = new Intent(Perfil.this, InicioAplicacion.class);
        startActivity(intento);
    }

    public void Perfil(){
        Intent intento = new Intent(Perfil.this, Perfil.class);
        startActivity(intento);
    }

   public void Ubicacion() {
        Intent intento = new Intent(this, MapsActivity.class);
        startActivity(intento);
    }

    public void Logout(){
        boton_logout.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intento = new Intent(Perfil.this, Login.class);
                        startActivity(intento);
                    }
                }
        );
    }

    public void Ranking(){
        Intent intento = new Intent(Perfil.this, RankingPersonas.class);
        startActivity(intento);
    }


    public void Campings(){
        Intent intento = new Intent(Perfil.this, ListaCampings.class);
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