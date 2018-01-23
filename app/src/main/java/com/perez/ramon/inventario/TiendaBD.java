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

public class TiendaBD extends SQLiteOpenHelper {


    public TiendaBD(Context context){
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

}
