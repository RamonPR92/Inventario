package com.perez.ramon.inventario;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private CursorAdapterProductos cursorAdapterProductos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Productos.inicializaBD(this);
        cursorAdapterProductos = new CursorAdapterProductos(
                this, Productos.obtenerTodosLosProductos());
    }
}
