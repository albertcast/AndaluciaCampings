package com.example.andaluciacampings;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    DatabaseHelper myDb;
    EditText editText_usuario, editText_password;
    Button boton_login, boton_registro;
    TextView textView_usuario, textView_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDb = new DatabaseHelper(this);
        editText_usuario = (EditText) findViewById(R.id.editText_usuario);
        editText_password = (EditText) findViewById(R.id.editText_register_password);
        boton_login = (Button) findViewById(R.id.boton_login_login);
        boton_registro = (Button) findViewById(R.id.boton_registro_login);
        textView_usuario = (TextView) findViewById(R.id.textView_usuario_login);
        textView_password = (TextView) findViewById(R.id.textView_password_login);
        textView_usuario.setText(R.string.textView_usuario_string);
        textView_password.setText(R.string.textView_password_string);
        boton_login.setText(R.string.boton_login);
        boton_registro.setText(R.string.boton_registro);
        Login();
        Registro();

        if (getIntent() != null && getIntent().hasExtra("registro")){
            Toast.makeText(Login.this, R.string.Usuario_creado_string, Toast.LENGTH_LONG).show();
        }
    }

    public void Registro(){
        boton_registro.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intento = new Intent(Login.this, Register.class);
                        startActivity(intento);
                    }
                }
        );
    }

    public void Login(){
        boton_login.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        if (!TextUtils.isEmpty(editText_usuario.getText().toString()) && !TextUtils.isEmpty(editText_password.getText().toString())){
                            Cursor res = myDb.getUsername(editText_usuario.getText().toString());
                            String password;
                            if(res.moveToNext()) {
                                password = res.getString(2);
                                if(editText_password.getText().toString().equalsIgnoreCase(password)){
                                    Intent intento = new Intent(Login.this, InicioAplicacion.class);
                                    intento.putExtra("usuario", editText_usuario.getText().toString());
                                    startActivity(intento);
                                } else {
                                    Toast.makeText(Login.this,R.string.Usuario_o_contraseña_inválidos_string, Toast.LENGTH_LONG).show();
                                }
                            } else {
                                Toast.makeText(Login.this,R.string.Usuario_o_contraseña_inválidos_string, Toast.LENGTH_LONG).show();
                            }
                        } else {
                            Toast.makeText(Login.this,R.string.Algunos_campos_están_vacíos_string, Toast.LENGTH_LONG).show();
                        }
                    }
                }
        );
    }

    public void showMessage(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}