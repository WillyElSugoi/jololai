package com.example.jololai.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.jololai.entidades.Idols;

import java.util.ArrayList;

public class DbIdols extends DbHelper {

    Context context;

    public DbIdols(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public long insertarIdol(String nombre,
                             String nombre_original,
                             String estado,
                             String unidad,
                             String generacion,
                             String debut,
                             String nickname,
                             String cumple,
                             String altura,
                             String disenador,
                             String bio,
                             byte[] imagen) {

        long id = 0;

        try {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("nombre", nombre);
            values.put("nombre_original", nombre_original);
            values.put("estado", estado);
            values.put("unidad", unidad);
            values.put("generacion", generacion);
            values.put("debut", debut);
            values.put("nickname", nickname);
            values.put("cumple", cumple);
            values.put("altura", altura);
            values.put("disenador", disenador);
            values.put("bio", bio);
            values.put("imagen", imagen);

            id = db.insert(TABLE_IDOLS, null, values);
        } catch (Exception ex) {
            ex.toString();
        }

        return id;
    }

    public ArrayList<Idols> mostrarIdols() {

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<Idols> listaIdols = new ArrayList<>();
        Idols idol;
        Cursor cursorIdols;


        cursorIdols = db.rawQuery("SELECT * FROM " + TABLE_IDOLS + " ORDER BY nombre ASC", null);

        if (cursorIdols.moveToFirst()) {
            do {
                idol = new Idols();
                idol.setId(cursorIdols.getInt(0));
                idol.setNombre(cursorIdols.getString(1));
                idol.setNombreOriginal(cursorIdols.getString(2));
                idol.setEstado(cursorIdols.getString(3));
                idol.setUnidad(cursorIdols.getString(4));
                idol.setGeneracion(cursorIdols.getString(5));
                idol.setDebut(cursorIdols.getString(6));
                idol.setNickname(cursorIdols.getString(7));
                idol.setCumple(cursorIdols.getString(8));
                idol.setAltura(cursorIdols.getString(9));
                idol.setDisenador(cursorIdols.getString(10));
                idol.setBio(cursorIdols.getString(11));
                idol.setImagen(cursorIdols.getBlob(12));
                listaIdols.add(idol);

            } while (cursorIdols.moveToNext());
        }

        cursorIdols.close();

        return listaIdols;
    }

    public Idols verIdol(int id) {

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Idols idol = null;
        Cursor cursorIdols;

        cursorIdols = db.rawQuery("SELECT * FROM " + TABLE_IDOLS + " WHERE id = " + id + " LIMIT 1", null);

        if (cursorIdols.moveToFirst()) {
            idol = new Idols();
            idol.setId(cursorIdols.getInt(0));
            idol.setNombre(cursorIdols.getString(1));
            idol.setNombreOriginal(cursorIdols.getString(2));
            idol.setEstado(cursorIdols.getString(3));
            idol.setUnidad(cursorIdols.getString(4));
            idol.setGeneracion(cursorIdols.getString(5));
            idol.setDebut(cursorIdols.getString(6));
            idol.setNickname(cursorIdols.getString(7));
            idol.setCumple(cursorIdols.getString(8));
            idol.setAltura(cursorIdols.getString(9));
            idol.setDisenador(cursorIdols.getString(10));
            idol.setBio(cursorIdols.getString(11));
            idol.setImagen(cursorIdols.getBlob(12));
        }

        cursorIdols.close();

        return idol;
    }

    public boolean editarIdol(int id,
                              String nombre,
                              String nombre_original,
                              String estado,
                              String unidad,
                              String generacion,
                              String debut,
                              String nickname,
                              String cumple,
                              String altura,
                              String disenador,
                              String bio,
                              byte[] imagen) {

        boolean correcto = false;

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            db.execSQL("UPDATE " + TABLE_IDOLS + " SET nombre = '" + nombre + "', nombre_original = '" + nombre_original + "'," +
                    " estado = '" + estado + "', unidad = '" + unidad + "'," +
                    " generacion = '" + generacion + "', debut = '" + debut + "'," +
                    " nickname = '" + nickname + "', cumple = '" + cumple + "'," +
                    " altura = '" + altura + "', disenador = '" + disenador + "'," +
                    " bio = '" + bio + "', imagen = '" + imagen + "' WHERE id='" + id + "' ");
            correcto = true;
        } catch (Exception ex) {
            ex.toString();
            correcto = false;
        } finally {
            db.close();
        }

        return correcto;
    }

    public boolean eliminarIdol(int id) {

        boolean correcto = false;

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            db.execSQL("DELETE FROM " + TABLE_IDOLS + " WHERE id = '" + id + "'");
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
