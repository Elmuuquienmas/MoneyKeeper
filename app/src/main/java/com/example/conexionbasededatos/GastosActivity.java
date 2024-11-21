package com.example.conexionbasededatos;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class GastosActivity extends AppCompatActivity {
    private RecyclerView recyclerViewGastos;
    private GastoAdapter adapter;
    private List<Gasto> gastosList;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gastos);

        recyclerViewGastos = findViewById(R.id.recyclerViewGastos);
        FloatingActionButton fabAgregar = findViewById(R.id.fabAgregar);

        // Inicializar lista, adapter y RecyclerView
        gastosList = new ArrayList<>();
        adapter = new GastoAdapter(gastosList);
        recyclerViewGastos.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewGastos.setAdapter(adapter);

        // Inicializar Firebase Firestore
        db = FirebaseFirestore.getInstance();

        // Cargar datos desde Firebase
        cargarGastos();

        // Configurar botón flotante para agregar gastos
        fabAgregar.setOnClickListener(v -> startActivity(new Intent(this, AgregarGastoActivity.class)));
    }

    private void cargarGastos() {
        db.collection("Gastos")
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    gastosList.clear(); // Limpia la lista para evitar duplicados
                    for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
                        Log.d("GastosActivity", "Documento encontrado: " + document.getData());
                        Gasto gasto = new Gasto(
                                document.getString("nombre"),
                                document.getString("tipo"),
                                Double.parseDouble(document.getString("cantidad")),
                                document.getString("fecha")
                        );
                        gastosList.add(gasto);
                    }
                    adapter.notifyDataSetChanged(); // Actualiza el adapter
                })
                .addOnFailureListener(e -> Log.e("GastosActivity", "Error al cargar datos: ", e));
    }
}
