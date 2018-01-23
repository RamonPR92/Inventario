package com.perez.ramon.inventario;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ramon on 23/01/2018.
 */

public class ProductosBD extends SQLiteOpenHelper {


    public ProductosBD(Context context){
        super(context,"tienda",null,1);
        //el contexto, el nombre de la base de datos, cursor, la version
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table productos (" +
                "id_producto integer primary key autoincrement, " +
                 "nombre_producto text, marca_producto text, " +
                "precio_producto float, cantidad float)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    //En caso de nueva version de la base de datos
    }

    public void guardarProducto(Producto producto){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("insert into productos values (null, " +
                " '" +  producto.getNombre() + "' , " +
                " '" +  producto.getMarca()  + "' , " +
                        producto.getPrecio() + "," +
                        producto.getCantidad() + ")");
        db.close();
    }

    public List obtenerTodosLosProductos(){
        List<Producto> productos = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
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
