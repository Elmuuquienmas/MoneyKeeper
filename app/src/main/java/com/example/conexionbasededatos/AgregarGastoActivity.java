package com.example.conexionbasededatos;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class AgregarGastoActivity extends AppCompatActivity {
    private EditText editTextNombre, editTextTipo, editTextCantidad, editTextFecha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_gasto);

        editTextNombre = findViewById(R.id.editTextNombre);
        editTextTipo = findViewById(R.id.editTextTipo);
        editTextCantidad = findViewById(R.id.editTextCantidad);
        editTextFecha = findViewById(R.id.editTextFecha);

        FloatingActionButton fabGuardar = findViewById(R.id.fabGuardar);
        fabGuardar.setOnClickListener(v -> guardarGasto());

        FloatingActionButton fabVerGastos = findViewById(R.id.fabVerGastos);
        fabVerGastos.setOnClickListener(v -> startActivity(new Intent(this, GastosActivity.class)));
    }

    private void guardarGasto() {
        String nombre = editTextNombre.getText().toString().trim();
        String tipo = editTextTipo.getText().toString().trim();
        String cantidad = editTextCantidad.getText().toString().trim();
        String fecha = editTextFecha.getText().toString().trim();

        if (!nombre.isEmpty() && !tipo.isEmpty() && !cantidad.isEmpty() && !fecha.isEmpty()) {
            Map<String, Object> gasto = new HashMap<>();
            gasto.put("nombre", nombre);
            gasto.put("tipo", tipo);
            gasto.put("cantidad", cantidad);
            gasto.put("fecha", fecha);

            FirebaseFirestore.getInstance().collection("Gastos").add(gasto)
                    .addOnSuccessListener(documentReference -> {
                        Toast.makeText(this, "Gasto guardado correctamente", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(this, GastosActivity.class));
                    })
                    .addOnFailureListener(e -> Toast.makeText(this, "Error al guardar gasto", Toast.LENGTH_SHORT).show());
        } else {
            Toast.makeText(this, "Complete todos los campos", Toast.LENGTH_SHORT).show();
        }
    }
}
