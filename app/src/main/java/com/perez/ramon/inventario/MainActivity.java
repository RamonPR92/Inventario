package com.perez.ramon.inventario;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AbsListView.MultiChoiceModeListener{

    private CursorAdapterProductos cursorAdapterProductos;
    private FloatingActionButton btnAgregar;
    private ListView listView;
    Context context;
    private int checkedCount;
    private ArrayList <Long> listItem = new ArrayList<>();

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
        listView =(ListView)findViewById(R.id.listView);
        //establecemos la lista para poder tener multiple seleccion
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        //enlazamos el adaptador con la lista
        listView.setAdapter(cursorAdapterProductos);
        //enlazamos la lista y un escuchador multiple
        listView.setMultiChoiceModeListener(this);

        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,NuevoProducto.class);
                startActivityForResult(intent,CREAR_PRODUCTO);
            }
        });
    }



    private void actualizarLista(){
        //Se reasigna el cursor con la nueva informacion a el adaptador
        cursorAdapterProductos.changeCursor(Productos.obtenerTodosLosProductos());
        //Se actualiza la lista en un hilo separado
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                cursorAdapterProductos.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked) {
        //Cuenta los items seleccionados
        checkedCount = listView.getCheckedItemCount();
        //Muestra el conteo
        mode.setTitle(checkedCount + " Seleccionados" );

        if(checked){
            listView.getChildAt(position).setBackgroundColor(getResources().getColor(R.color.gris_400));
            listItem.add(id);//Agrega a la lista cuando el item es checado
        }else{
            listView.getChildAt(position).setBackgroundColor(Color.WHITE);
            listItem.remove(id);//Remueve el item si no esta checado
        }

    }

    @Override
    public boolean onCreateActionMode(ActionMode mode, Menu menu) {
        //Aparece un menu de acciones cuando hay una seleccion de items
        MenuInflater menuInflater = mode.getMenuInflater();
        menuInflater.inflate(R.menu.contextual_menu, menu);
        return true;
    }

    @Override
    public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
        return false;
    }

    @Override
    public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
        //Acciones del menu contextual
        final int deleteSize = listItem.size();

        switch (item.getItemId()){
            case R.id.borrar:
                //Borrar todos los items seleccionados de la base de datos
                borrarRegistros(listItem);
                Toast.makeText(MainActivity.this, "Items borrados", Toast.LENGTH_SHORT).show();
                break;
        }
        //Reset a las variables que llevan el seguimineto de los items
        checkedCount = 0;
        listItem.clear();
        //Cierra el menu contextual y
        mode.finish();
        return true;
    }

    @Override
    public void onDestroyActionMode(ActionMode mode) {
        actualizarLista();
    }

    private void borrarRegistros(ArrayList<Long> itemsToDelete){
        for (Long item : itemsToDelete) {
            Productos.eliminarProducto(item);
        }
    }
}
