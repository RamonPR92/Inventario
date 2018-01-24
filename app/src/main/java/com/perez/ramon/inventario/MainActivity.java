package com.perez.ramon.inventario;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    private CursorAdapterProductos cursorAdapterProductos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
