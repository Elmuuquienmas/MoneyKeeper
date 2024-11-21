package com.example.conexionbasededatos;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnVerGastos = findViewById(R.id.btnVerGastos);
        Button btnAgregarGasto = findViewById(R.id.btnAgregarGasto);

        // Navegar a la pantalla de ver gastos
        btnVerGastos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, GastosActivity.class);
                startActivity(intent);
            }
        });

        // Navegar a la pantalla de agregar gastos
        btnAgregarGasto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AgregarGastoActivity.class);
                startActivity(intent);
            }
        });
    }
}
