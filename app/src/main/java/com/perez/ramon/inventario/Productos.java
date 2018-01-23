package com.perez.ramon.inventario;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ramon on 23/01/2018.
 */

public class Productos {

    private static TiendaBD tiendaBD;

    public static void inicializaBD(Context context){
        tiendaBD = new TiendaBD(context);
    }

    public void guardarProducto(Producto producto){
        SQLiteDatabase db = tiendaBD.getWritableDatabase();
        db.execSQL("insert into productos values (null, " +
                " '" +  producto.getNombre() + "' , " +
                " '" +  producto.getMarca()  + "' , " +
                producto.getPrecio() + "," +
                producto.getCantidad() + ")");
        db.close();
    }

    public List obtenerTodosLosProductos(){
        List<Producto> productos = new ArrayList<>();
        SQLiteDatabase db = tiendaBD.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from productos order by nombre_producto",null);
        while(cursor.moveToNext()){
            String nombre,marca = null;
            float precio, cantidad = 0;

            nombre = cursor.getString(1);
            marca = cursor.getString(2);
            precio = cursor.getFloat(3);
            cantidad = cursor.getFloat(4);

            productos.add(new Producto(nombre,marca,precio,cantidad));
        }

        return productos;
    }
}
