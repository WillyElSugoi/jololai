package com.example.jololai.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.jololai.entidades.RepVideos;

import java.util.ArrayList;

public class DbRepVideos extends DbHelper {

    Context context;

    public DbRepVideos(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public long insertarRepVideo(String nombre_usuario,
                                 Integer id_video,
                                 String mes) {

        long id = 0;

        try {

            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("nombre_usuario", nombre_usuario);
            values.put("id_video", id_video);
            values.put("mes", mes);

            id = db.insert(TABLE_REP_VID, null, values);

        } catch (Exception ex) {

            ex.toString();
        }

        return id;

    }

    public ArrayList<RepVideos> mostrarRepVideos() {

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<RepVideos> listaRepVideos = new ArrayList<>();
        RepVideos RepVideo;
        Cursor cursorRepVideo;

        cursorRepVideo = db.rawQuery("SELECT * FROM " + TABLE_REP_VID + " ORDER BY id ASC", null);

        if (cursorRepVideo.moveToFirst()) {
            do {

                RepVideo = new RepVideos();
                RepVideo.setId(cursorRepVideo.getInt(0));
                RepVideo.setNombre_usuario(cursorRepVideo.getString(1));
                RepVideo.setId_video(cursorRepVideo.getInt(2));
                RepVideo.setMes(cursorRepVideo.getString(3));

                listaRepVideos.add(RepVideo);

            } while (cursorRepVideo.moveToNext());
        }

        cursorRepVideo.close();

        return listaRepVideos;

    }

    public RepVideos verRepVideo(int id) {

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        RepVideos repVideos = null;
        Cursor cursorRepVideo;

        cursorRepVideo = db.rawQuery("SELECT * FROM " + TABLE_REP_VID + " WHERE id = " + id + " LIMIT 1", null);

        if (cursorRepVideo.moveToFirst()) {

            repVideos = new RepVideos();
            repVideos.setId(cursorRepVideo.getInt(0));
            repVideos.setNombre_usuario(cursorRepVideo.getString(1));
            repVideos.setId_video(cursorRepVideo.getInt(2));
            repVideos.setMes(cursorRepVideo.getString(3));

        }

        cursorRepVideo.close();

        return repVideos;

    }

}

/*********** Esto posiblemente no se ocupe, al final son registros, pero igual los dejamos
 * para futuras referencias *****************/

/**************************

    public  boolean editarRepVideo(int id,
                                   String nombre_usuario,
                                   Integer id_video,
                                   String mes){

        boolean correcto = false;

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {

            db.execSQL("UPDATE " + TABLE_REP_VID + " SET nombre_usuario '" + nombre_usuario + "'," +
                    " id_video = '" + id_video + "', mes = '" + mes + "' WHERE id = ' " + id + "' ");

            correcto = true;

        } catch (Exception ex) {

            ex.toString();
            correcto = false;

        } finally {

            db.close();

        }

        return correcto;

    }

    public boolean eliminarRepVideo(int id){

        boolean correcto = false;

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {

            db.execSQL("DELETE FROM " + TABLE_REP_VID + " WHERE id = ' " + id + "'");
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