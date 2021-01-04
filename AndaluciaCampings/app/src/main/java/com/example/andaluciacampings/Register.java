package com.example.andaluciacampings;

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

public class Register extends AppCompatActivity {

    EditText editText_usuario, editText_password, editText_repetir_password, editText_nombre, editText_apellido;
    Button boton_registro;
    DatabaseHelper myDb;
    TextView textView_usuario, textView_password, textView_repetir_password, textView_nombre, textView_apellido;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        myDb = new DatabaseHelper(this);

        editText_usuario = (EditText) findViewById(R.id.editText_register_user);
        editText_password = (EditText) findViewById(R.id.editText_register_password);
        editText_repetir_password = (EditText) findViewById(R.id.editText_register_repetir_password);
        editText_nombre = (EditText) findViewById(R.id.editText_register_nombre);
        editText_apellido = (EditText) findViewById(R.id.editText_register_apellido);


        boton_registro = (Button) findViewById(R.id.button_register);
        boton_registro.setText(R.string.boton_registro);

        textView_usuario = (TextView) findViewById(R.id.textview_usuario_registro);
        textView_password = (TextView) findViewById(R.id.textview_contraseña_registro);
        textView_repetir_password = (TextView) findViewById(R.id.textview_repetir_contraseña_registro);
        textView_nombre = (TextView) findViewById(R.id.textView_register_nombre);
        textView_apellido = (TextView) findViewById(R.id.textView_register_apellido);

        textView_usuario.setText(R.string.textView_usuario_string);
        textView_password.setText(R.string.textView_password_string);
        textView_repetir_password.setText(R.string.textView_repetir_password_string);
        textView_nombre.setText(R.string.textView_nombre_string);
        Register();
    }

    public void Register(){
        boton_registro.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!TextUtils.isEmpty(editText_usuario.getText().toString()) && !TextUtils.isEmpty(editText_password.getText().toString()) && !TextUtils.isEmpty(editText_repetir_password.getText().toString())
                                && !TextUtils.isEmpty(editText_nombre.getText().toString()) && !TextUtils.isEmpty(editText_apellido.getText().toString())){
                            if (editText_password.getText().toString().equals(editText_repetir_password.getText().toString())){
                                Cursor res = myDb.getUsername(editText_usuario.getText().toString());
                                if (res.moveToNext()){
                                    Toast.makeText(Register.this, R.string.Nombre_de_usuario_ya_está_en_uso_string, Toast.LENGTH_LONG).show();
                                } else {
                                    myDb.insertData(editText_usuario.getText().toString(), editText_password.getText().toString(), editText_nombre.getText().toString(), editText_apellido.getText().toString());
                                    Intent intento = new Intent(Register.this, Login.class);
                                    intento.putExtra("registro", "Usuario creado");
                                    startActivity(intento);
                                }
                            } else {
                                Toast.makeText(Register.this, R.string.Las_contraseñas_introducidas_no_coinciden_string, Toast.LENGTH_LONG).show();
                            }
                        } else {
                            Toast.makeText(Register.this, R.string.Algunos_campos_están_vacíos_string, Toast.LENGTH_LONG).show();
                        }
                    }
                }
        );
    }
}