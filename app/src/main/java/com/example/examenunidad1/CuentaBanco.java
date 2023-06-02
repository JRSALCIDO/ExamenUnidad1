package com.example.examenunidad1;

import androidx.appcompat.app.AppCompatActivity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.content.Intent;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Button;
import android.view.View;
import android.widget.Toast;

public class CuentaBanco extends AppCompatActivity {
    private String saldo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cuenta_banco);

        Intent intent = getIntent();
        String num = intent.getStringExtra("num");
        String nombre = intent.getStringExtra("nombre");
        String banco = intent.getStringExtra("banco");
        final String[] saldo = {intent.getStringExtra("saldo")};

        //Colocando los datos en los TextViews
        TextView nombreView = (TextView) findViewById(R.id.nombre);
        nombreView.setText(nombre);

        TextView saldoView = (TextView) findViewById(R.id.saldo);
        saldoView.setText(saldo[0]);

        EditText cantidadEditText = (EditText) findViewById(R.id.cantidad);
        Button depositarButton = (Button) findViewById(R.id.hacerdep);
        Button retirarButton = (Button) findViewById(R.id.hacerretiro);
        Button salirButton = (Button) findViewById(R.id.salir);

        depositarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtiene la cantidad ingresada por el usuario para depositar
                String cantidadIngresada = cantidadEditText.getText().toString();

                // Verifica si la cantidad ingresada no está vacía
                if (!cantidadIngresada.isEmpty()) {
                    // Convierte la cantidad ingresada y el saldo actual a double
                    double cantidad = Double.parseDouble(cantidadIngresada);
                    double saldoActual = Double.parseDouble(saldo[0]);

                    // Realiza la operación de depósito
                    saldoActual += cantidad;

                    // Actualiza el saldo en la vista y en la variable saldo
                    saldo[0] = String.valueOf(saldoActual);
                    saldoView.setText(saldo[0]);

                    // Limpia el campo de la cantidad
                    cantidadEditText.setText("");
                } else {
                    Toast.makeText(CuentaBanco.this, "Debes ingresar una cantidad para realizar la operación.", Toast.LENGTH_SHORT).show();
                }
            }
        });


        retirarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtiene la cantidad ingresada por el usuario para retirar
                String cantidadIngresada = cantidadEditText.getText().toString();

                // Verifica si la cantidad ingresada no está vacía
                if (!cantidadIngresada.isEmpty()) {
                    // Convierte la cantidad ingresada y el saldo actual a double
                    double cantidad = Double.parseDouble(cantidadIngresada);
                    double saldoActual = Double.parseDouble(saldo[0]);

                    // Comprueba si el saldo actual es mayor o igual a la cantidad a retirar
                    if(saldoActual >= cantidad) {
                        // Realiza la operación de retiro
                        saldoActual -= cantidad;

                        saldo[0] = String.valueOf(saldoActual);
                        saldoView.setText(saldo[0]);

                        // Limpia el campo de la cantidad
                        cantidadEditText.setText("");
                    } else {
                        Toast.makeText(CuentaBanco.this, "No tienes suficiente saldo para realizar esta operación.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(CuentaBanco.this, "Debes ingresar una cantidad para realizar la operación.", Toast.LENGTH_SHORT).show();

                }
            }
        });


        salirButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(CuentaBanco.this)
                        .setTitle("Confirmar salida")
                        .setMessage("¿Estás seguro de que quieres salir?")
                        .setNegativeButton(android.R.string.no, null)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface arg0, int arg1) {
                                CuentaBanco.super.onBackPressed();
                            }
                        }).create().show();
            }
        });
    }
}
