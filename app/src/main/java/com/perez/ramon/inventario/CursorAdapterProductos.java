package com.perez.ramon.inventario;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Ramon on 23/01/2018.
 */

public class CursorAdapterProductos extends CursorAdapter {
    private LayoutInflater inflater;
    TextView nombre, marca, precio, cantidad;

    public CursorAdapterProductos(Context context, Cursor cursor){
        super(context,cursor,false);
    }
    @Override
    public View newView(Context contexto, Cursor cursor, ViewGroup padre) {
        inflater = (LayoutInflater) contexto.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
        View vista = inflater.inflate(R.layout.elemento_lista, padre, false);
        return vista;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        nombre = (TextView)view.findViewById(R.id.nombre);
        marca = (TextView)view.findViewById(R.id.marca);
        precio = (TextView)view.findViewById(R.id.precio);
        cantidad = (TextView)view.findViewById(R.id.cantidad);

        nombre.setText(cursor.getString(1));
        marca.setText(cursor.getString(3));
        precio.setText( "$ " + Float.toString(cursor.getFloat(4)));
        cantidad.setText(Float.toString(cursor.getFloat(5)));
    }
}
