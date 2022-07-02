package com.example.jololai.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.jololai.entidades.Canciones;
import com.example.jololai.entidades.Canciones;

import java.util.ArrayList;

public class DbCanciones extends DbHelper {

    Context context;

    public DbCanciones(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public long insertarCancion(String nombre,
                                String tipo_cancion,
                                String letra_cancion,
                                byte[] imagen_cancion,
                                Integer id_idol){

        long id = 0;

        try{

            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("nombre", nombre);
            values.put("tipo_cancion", tipo_cancion);
            values.put("letra_cancion", letra_cancion);
            values.put("imagen_cancion", imagen_cancion);
            values.put("id_idol", id_idol);

            id = db.insert(TABLE_SONGS, null, values);

        } catch (Exception ex) {
            ex.toString();
        }

        return id;

    }

    public ArrayList<Canciones> mostrarCanciones() {

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<Canciones> listaCanciones = new ArrayList<>();
        Canciones cancion;
        Cursor cursorCancion;


        cursorCancion = db.rawQuery("SELECT * FROM " + TABLE_SONGS + " ORDER BY nombre ASC", null);

        if (cursorCancion.moveToFirst()) {
            do {
                cancion = new Canciones();
                cancion.setId(cursorCancion.getInt(0));
                cancion.setNombre(cursorCancion.getString(1));
                cancion.setTipo_cancion(cursorCancion.getString(2));
                cancion.setLetra_cancion(cursorCancion.getString(3));
                cancion.setImagen_cancion(cursorCancion.getBlob(4));
                cancion.setId_idol(cursorCancion.getInt(5));

                listaCanciones.add(cancion);

            } while (cursorCancion.moveToNext());
        }

        cursorCancion.close();

        return listaCanciones;
    }

    public Canciones verCancion(int id) {

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Canciones cancion = null;
        Cursor cursorCanciones;

        cursorCanciones = db.rawQuery("SELECT * FROM " + TABLE_SONGS + " WHERE id = " + id + " LIMIT 1", null);

        if (cursorCanciones.moveToFirst()) {
            cancion = new Canciones();
            cancion.setId(cursorCanciones.getInt(0));
            cancion.setNombre(cursorCanciones.getString(1));
            cancion.setTipo_cancion(cursorCanciones.getString(2));
            cancion.setLetra_cancion(cursorCanciones.getString(3));
            cancion.setImagen_cancion(cursorCanciones.getBlob(4));
            cancion.setId_idol(cursorCanciones.getInt(5));
        }

        cursorCanciones.close();

        return cancion;
    }

    public boolean editarCancion(int id,
                              String nombre,
                              String tipo_cancion,
                              String letra_cancion,
                              byte[] imagen_cancion,
                             int id_Idol) {

        boolean correcto = false;

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            db.execSQL("UPDATE " + TABLE_SONGS + " SET nombre = '" + nombre + "', tipo_cancion = '" + tipo_cancion + "'," +
                    " letra_cancion = '" + letra_cancion + "', imagen_cancion = '" + imagen_cancion + "'," +
                    "id_idol = '" + id_Idol + "' WHERE id = '" + id + "' ");

            correcto = true;

        } catch (Exception ex) {
            ex.toString();
            correcto = false;
        } finally {
            db.close();
        }

        return correcto;
    }

    public boolean eliminarCancion(int id) {

        boolean correcto = false;

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            db.execSQL("DELETE FROM " + TABLE_SONGS + " WHERE id = '" + id + "'");
            correcto = true;
        } catch (Exception ex) {
            ex.toString();
            correcto = false;
        } finally {
            db.close();
        }

        return correcto;
    }


}
