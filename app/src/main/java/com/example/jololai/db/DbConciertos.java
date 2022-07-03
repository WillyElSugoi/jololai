package com.example.jololai.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.jololai.entidades.Canciones;
import com.example.jololai.entidades.Conciertos;

import java.util.ArrayList;

public class DbConciertos extends DbHelper {

    Context context;

    public DbConciertos(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public long insertarConcierto(String Nombre_Concierto,
                                  Integer ID_Idol,
                                  String Fecha_Concierto,
                                  Integer Duracion_Minutos){

        long id = 0;

        try{

            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("Nombre_Concierto", Nombre_Concierto);
            values.put("ID_Idol", ID_Idol);
            values.put("Fecha_Concierto", Fecha_Concierto);
            values.put("Duracion_Minutos", Duracion_Minutos);

            id = db.insert(TABLE_CONCIERTO, null, values);

        } catch (Exception ex) {
            ex.toString();
        }

        return id;

    }

    public ArrayList<Conciertos> mostrarConciertos() {

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<Conciertos> ListaConcierto = new ArrayList<>();
        Conciertos conciertos;
        Cursor cursorConcierto;


        cursorConcierto = db.rawQuery("SELECT * FROM " + TABLE_CONCIERTO + " ORDER BY ID_Idol ASC", null);

        if (cursorConcierto.moveToFirst()) {
            do {
                conciertos = new Conciertos();
                conciertos.setID_Concierto(cursorConcierto.getInt(0));
                conciertos.setNombre_Concierto(cursorConcierto.getString(1));
                conciertos.setID_Idol(cursorConcierto.getInt(2));
                conciertos.setFecha_Concierto(cursorConcierto.getString(3));
                conciertos.setDuracion_Minutos(cursorConcierto.getInt(4));

                ListaConcierto.add(conciertos);

            } while (cursorConcierto.moveToNext());
        }

        cursorConcierto.close();

        return ListaConcierto;
    }

    public Conciertos verConcierto(int ID_Concierto) {

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Conciertos conciertos = null;
        Cursor cursorConcierto;

        cursorConcierto = db.rawQuery("SELECT * FROM " + TABLE_CONCIERTO + " WHERE ID_Concierto = " + ID_Concierto + " LIMIT 1", null);

        if (cursorConcierto.moveToFirst()) {
            conciertos = new Conciertos();
            conciertos.setID_Concierto(cursorConcierto.getInt(0));
            conciertos.setNombre_Concierto(cursorConcierto.getString(1));
            conciertos.setID_Idol(cursorConcierto.getInt(2));
            conciertos.setFecha_Concierto(cursorConcierto.getString(3));
            conciertos.setDuracion_Minutos(cursorConcierto.getInt(4));
        }

        cursorConcierto.close();

        return conciertos;
    }

    public boolean editarConcierto(int ID_Concierto,
                                   String Nombre_Concierto,
                                   int ID_Idol,
                                   String Fecha_Concierto,
                                   int Duracion_Minutos) {

        boolean correcto = false;

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            db.execSQL("UPDATE " + TABLE_CONCIERTO + " SET ID_Concierto = '" + ID_Concierto + "', Nombre_Concierto = '" + Nombre_Concierto + "'," +
                    " ID_Idol = '" + ID_Idol + "', Fecha_Concierto = '" + Fecha_Concierto + "'," +
                    "Duracion_Minutos = '" + Duracion_Minutos + "' WHERE ID_Concierto = '" + ID_Concierto + "' ");

            correcto = true;

        } catch (Exception ex) {
            ex.toString();
            correcto = false;
        } finally {
            db.close();
        }

        return correcto;
    }

    public boolean eliminarConcierto(int ID_Concierto) {

        boolean correcto = false;

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            db.execSQL("DELETE FROM " + TABLE_CONCIERTO + " WHERE ID_Concierto = '" + ID_Concierto + "'");
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
