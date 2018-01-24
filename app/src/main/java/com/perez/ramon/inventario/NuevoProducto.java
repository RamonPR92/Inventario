package com.perez.ramon.inventario;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Ramon on 23/01/2018.
 */

public class NuevoProducto extends AppCompatActivity {
    private EditText nombre, marca, precio, cantidad;
    private Button descartar, guardar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nuevo_producto);

        nombre = (EditText) findViewById(R.id.nombre);
        marca = (EditText)findViewById(R.id.marca);
        precio = (EditText)findViewById(R.id.precio);
        cantidad = (EditText)findViewById(R.id.cantidad);

        descartar = (Button)findViewById(R.id.descartar);
        guardar = (Button)findViewById(R.id.guardar);

        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Productos.guardarProducto( new Producto(
                        nombre.getText().toString(),marca.getText().toString(),
                        Float.valueOf(precio.getText().toString()),
                        Float.valueOf(cantidad.getText().toString())));
                Toast.makeText(NuevoProducto.this,"Datos guardados exitosamente",
                        Toast.LENGTH_SHORT).show();
                Intent intent = new Intent();
                setResult(RESULT_OK);
                finish();
            }
        });

        descartar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                accionDescartar();
            }
        });

    }

    private void accionDescartar(){
        new AlertDialog.Builder(NuevoProducto.this)
                .setTitle("Descartar cambios")
                .setMessage("¿Seguro que No desea guardar cambios?")
                .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .setNegativeButton("Cancelar", null)
                .show();
    }

    @Override
    public void onBackPressed() {
        accionDescartar();
    }
}
