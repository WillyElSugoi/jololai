package com.example.jololai.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.jololai.entidades.ComCanciones;

import java.util.ArrayList;

public class DbComCanciones extends DbHelper {

    Context context;

    public DbComCanciones(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public long insertarComCancion(String nombre_usuario,
                                 Integer id_cancion,
                                 String mes) {

        long id = 0;

        try {

            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("nombre_usuario", nombre_usuario);
            values.put("id_cancion", id_cancion);
            values.put("mes", mes);

            id = db.insert(TABLE_COM_CAN, null, values);

        } catch (Exception ex) {

            ex.toString();
        }

        return id;

    }

    public ArrayList<ComCanciones> mostrarComCanciones() {

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<ComCanciones> listaComCanciones = new ArrayList<>();
        ComCanciones ComCancion;
        Cursor cursorComCancion;

        cursorComCancion = db.rawQuery("SELECT * FROM " + TABLE_COM_CAN + " ORDER BY id ASC", null);

        if (cursorComCancion.moveToFirst()) {
            do {

                ComCancion = new ComCanciones();
                ComCancion.setId(cursorComCancion.getInt(0));
                ComCancion.setNombre_usuario(cursorComCancion.getString(1));
                ComCancion.setId_cancion(cursorComCancion.getInt(2));
                ComCancion.setMes(cursorComCancion.getString(3));

                listaComCanciones.add(ComCancion);

            } while (cursorComCancion.moveToNext());
        }

        cursorComCancion.close();

        return listaComCanciones;

    }

    public ComCanciones verComCancion(int id) {

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ComCanciones ComCanciones = null;
        Cursor cursorComCancion;

        cursorComCancion = db.rawQuery("SELECT * FROM " + TABLE_COM_CAN + " WHERE id = " + id + " LIMIT 1", null);

        if (cursorComCancion.moveToFirst()) {

            ComCanciones = new ComCanciones();
            ComCanciones.setId(cursorComCancion.getInt(0));
            ComCanciones.setNombre_usuario(cursorComCancion.getString(1));
            ComCanciones.setId_cancion(cursorComCancion.getInt(2));
            ComCanciones.setMes(cursorComCancion.getString(3));

        }

        cursorComCancion.close();

        return ComCanciones;

    }

}

/*********** Esto posiblemente no se ocupe, al final son registros, pero igual los dejamos
 * para futuras referencias *****************/

/**************************

 public  boolean editarComCancion(int id,
 String nombre_usuario,
 Integer id_cancion,
 String mes){

 boolean correcto = false;

 DbHelper dbHelper = new DbHelper(context);
 SQLiteDatabase db = dbHelper.getWritableDatabase();

 try {

 db.execSQL("UPDATE " + TABLE_COM_CAN + " SET nombre_usuario '" + nombre_usuario + "'," +
 " id_cancion = '" + id_cancion + "', mes = '" + mes + "' WHERE id = ' " + id + "' ");

 correcto = true;

 } catch (Exception ex) {

 ex.toString();
 correcto = false;

 } finally {

 db.close();

 }

 return correcto;

 }

 public boolean eliminarComCancion(int id){

 boolean correcto = false;

 DbHelper dbHelper = new DbHelper(context);
 SQLiteDatabase db = dbHelper.getWritableDatabase();

 try {

 db.execSQL("DELETE FROM " + TABLE_COM_CAN + " WHERE id = ' " + id + "'");
 correcto = true;
 } catch (Exception ex) {

 ex.toString();
 correcto = false;

 } finally {

 db.close();

 }

 return correcto;

 }
 ********************/