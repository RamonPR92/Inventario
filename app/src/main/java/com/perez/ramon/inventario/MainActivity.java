package com.perez.ramon.inventario;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    private CursorAdapterProductos cursorAdapterProductos;
    private FloatingActionButton btnAgregar;

    private final int CREAR_PRODUCTO = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnAgregar = (FloatingActionButton)findViewById(R.id.btnAgregar);

        //Inicia la base de datos
        Productos.inicializaBD(this);
        //El cursor se carga con la consulta
        cursorAdapterProductos = new CursorAdapterProductos(
                this, Productos.obtenerTodosLosProductos());
        //enlaza la vista con la variable lista
        ListView listView =(ListView)findViewById(R.id.listView);
        //enlazamos el adaptador con la lista
        listView.setAdapter(cursorAdapterProductos);
        //enlazamos la lista y un escuchador
        listView.setOnItemClickListener(this);

        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,NuevoProducto.class);
                startActivityForResult(intent,CREAR_PRODUCTO);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == CREAR_PRODUCTO && resultCode == RESULT_OK){
            //Se creo el producto y debemos recargar la lista
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
