package com.perez.ramon.inventario;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Ramon on 23/01/2018.
 */

public class NuevoProducto extends AppCompatActivity {
    private EditText nombre, marca, precio, producto;
    private Button descartar, guardar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nuevo_producto);
    }
}
