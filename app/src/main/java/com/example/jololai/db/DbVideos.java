package com.example.jololai.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.jololai.entidades.Videos;

import java.util.ArrayList;

public class DbVideos extends DbHelper {

    Context context;

    public DbVideos(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public long insertarVideo(String nombreVideo,
                              String genero_video,
                              String promocional,
                              byte[] imagen_video,
                              String link,
                              Integer id_idol) {

        long id = 0;

        try {

            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("nombreVideo", nombreVideo);
            values.put("genero_video", genero_video);
            values.put("promocional", promocional);
            values.put("imagen_video", imagen_video);
            values.put("link", link);
            values.put("id_idol", id_idol);

            id = db.insert(TABLE_VIDEOS, null, values);

        } catch (Exception ex) {
            ex.toString();
        }

        return id;

    }

    public ArrayList<Videos> mostrarVideos() {

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<Videos> listaVideos = new ArrayList<>();
        Videos video;
        Cursor cursorVideo;


        cursorVideo = db.rawQuery("SELECT * FROM " + TABLE_VIDEOS + " ORDER BY nombreVideo ASC", null);

        if (cursorVideo.moveToFirst()) {
            do {
                video = new Videos();
                video.setId(cursorVideo.getInt(0));
                video.setNombreVideo(cursorVideo.getString(1));
                video.setGenero_video(cursorVideo.getString(2));
                video.setPromocional(cursorVideo.getString(3));
                video.setImagen_video(cursorVideo.getBlob(4));
                video.setLink(cursorVideo.getString(5));
                video.setId_idol(cursorVideo.getInt(6));

                listaVideos.add(video);

            } while (cursorVideo.moveToNext());
        }

        cursorVideo.close();

        return listaVideos;
    }

    public Videos verVideo(int id) {

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Videos video = null;
        Cursor cursorVideos;

        cursorVideos = db.rawQuery("SELECT * FROM " + TABLE_VIDEOS + " WHERE id = " + id + " LIMIT 1", null);

        if (cursorVideos.moveToFirst()) {
            video = new Videos();
            video.setId(cursorVideos.getInt(0));
            video.setNombreVideo(cursorVideos.getString(1));
            video.setGenero_video(cursorVideos.getString(2));
            video.setPromocional(cursorVideos.getString(3));
            video.setImagen_video(cursorVideos.getBlob(4));
            video.setLink(cursorVideos.getString(5));
            video.setId_idol(cursorVideos.getInt(6));
        }

        cursorVideos.close();

        return video;
    }

    public boolean editarVideo(int id,
                               String nombreVideo,
                               String genero_video,
                               String promocional,
                               byte[] imagen_video,
                               String link,
                               int id_Idol) {

        boolean correcto = false;

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {

            db.execSQL("UPDATE " + TABLE_VIDEOS + " SET nombreVideo = '" + nombreVideo + "', genero_video = '" + genero_video + "'," +
                    " promocional = '" + promocional + "', imagen_video = '" + imagen_video + "'," +
                    "link = '" + link + "', id_idol = '" + id_Idol + "' WHERE id = '" + id + "' ");

            correcto = true;

        } catch (Exception ex) {
            ex.toString();
            correcto = false;
        } finally {
            db.close();
        }

        return correcto;
    }

    public boolean eliminarVideo(int id) {

        boolean correcto = false;

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            db.execSQL("DELETE FROM " + TABLE_VIDEOS + " WHERE id = '" + id + "'");
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