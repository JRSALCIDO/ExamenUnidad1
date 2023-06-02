package com.example.examenunidad1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.app.AlertDialog;
import android.content.DialogInterface;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText num, nombre, banco, saldo;
    private Button enviar, salir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        num = findViewById(R.id.num);
        nombre = findViewById(R.id.nombre);
        banco = findViewById(R.id.banco);
        saldo = findViewById(R.id.saldo);
        enviar = findViewById(R.id.Enviar);
        salir = findViewById(R.id.salir);

        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, CuentaBanco.class);

                intent.putExtra("num", num.getText().toString());
                intent.putExtra("nombre", nombre.getText().toString());
                intent.putExtra("banco", banco.getText().toString());
                intent.putExtra("saldo", saldo.getText().toString());

                startActivity(intent);
            }
        });

        salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Confirmar salida")
                        .setMessage("¿Estás seguro de que quieres salir?")
                        .setNegativeButton(android.R.string.no, null)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface arg0, int arg1) {
                                MainActivity.super.onBackPressed();
                            }
                        }).create().show();
            }
        });

    }
}
