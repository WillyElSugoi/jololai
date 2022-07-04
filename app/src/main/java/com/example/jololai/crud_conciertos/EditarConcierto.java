package com.example.jololai.crud_conciertos;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.jololai.R;
import com.example.jololai.crud_canciones.EditarCanciones;
import com.example.jololai.crud_canciones.ListaDeCanciones;
import com.example.jololai.db.DbConciertos;
import com.example.jololai.db.DbIdols;
import com.example.jololai.entidades.Conciertos;
import com.example.jololai.entidades.Idols;

import java.util.ArrayList;
import java.util.Calendar;

public class EditarConcierto extends AppCompatActivity {


    EditText nombreConcierto, duracionConcierto, fechaConcierto;
    String nombreConciertoString, duracionConciertoString, fechaConciertoString;

    Spinner spinnerIdol;
    ArrayList<String> listaNombreIdols;
    ArrayList<Idols> listaIdols;
    String idolConciertoString;

    Button botonAgregarConcierto;

    boolean correcto = false;
    Integer id = 0;
    Idols Idol;
    Conciertos conciertos;

    private int diaConcierto, mesConcierto, añoConcierto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_concierto);

        /*********** Acá están las barras de texto ***********/

        nombreConcierto = findViewById(R.id.Nombre_ConciertoEd);

        duracionConcierto = findViewById(R.id.duracionEd);

        fechaConcierto = findViewById(R.id.Fecha_ConciertoEd);

        /*********** Invocar botones *****************/
        botonAgregarConcierto = findViewById(R.id.botonEditarConcierto);

        /**************** Spinner de las idols ************/

        spinnerIdol = findViewById(R.id.spinnerIdolEd);

        consultarListaIdols();

        ArrayAdapter<CharSequence> adaptador = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item, listaNombreIdols);

        spinnerIdol.setAdapter(adaptador);

        spinnerIdol.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {

                idolConciertoString = parent.getItemAtPosition(i).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        /*************** Boton para guardar los cambios *********************/

        DbConciertos dbConciertos = new DbConciertos(EditarConcierto.this);
        DbIdols dbIdols = new DbIdols(EditarConcierto.this);

        Idol = dbIdols.verIdol(conciertos.getID_Idol());

        botonAgregarConcierto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                nombreConciertoString = nombreConcierto.getText().toString();
                duracionConciertoString = duracionConcierto.getText().toString();

                if (nombreConciertoString.equals("") ||
                        duracionConciertoString.equals("") ||
                        spinnerIdol.getSelectedItem().toString().equals("Idol") ||
                        fechaConcierto.getText().toString().equals("")){

                    Log.e("nombreConcierto", nombreConciertoString);
                    Log.e("duracionConcierto", duracionConciertoString);
                    Log.e("Idol", spinnerIdol.getSelectedItem().toString());
                    Log.e("Fecha", fechaConciertoString);

                    Toast.makeText(EditarConcierto.this, "Rellene todos los campos", Toast.LENGTH_LONG).show();

                } else {

                    int idSpinner = (int) spinnerIdol.getSelectedItemId();
                    int duracionInt = Integer.parseInt(duracionConciertoString);

                    if (idSpinner != 0){
                        int idIdol = listaIdols.get(idSpinner - 1).getId();

                        correcto = dbConciertos.editarConcierto(id,
                                nombreConciertoString,
                                idIdol,
                                fechaConciertoString,
                                duracionInt);

                        if (correcto) {

                            Toast.makeText(EditarConcierto.this, "REGISTRO MODIFICADO", Toast.LENGTH_LONG).show();

                        }

                        if (id > 0) {

                            Toast.makeText(EditarConcierto.this, "Concierto guardado", Toast.LENGTH_LONG).show();
                            finish();
                            limpiar();

                        } else {

                            Toast.makeText(EditarConcierto.this, "Se ha producido un error", Toast.LENGTH_LONG).show();

                        }

                    }

                }
            }
        });


    }

    private void limpiar() {

        nombreConcierto.setText("");
        duracionConcierto.setText("");
        fechaConcierto.setText("");

    }

    private void consultarListaIdols() {

        SQLiteDatabase db;
        try (DbIdols dbIdols = new DbIdols(EditarConcierto.this)) {

            db = dbIdols.getReadableDatabase();
        }
        Idols idol = null;
        listaIdols = new ArrayList<Idols>();

        /** No entiendo porqué no pude ocupar el de DbIdols, lo hice de nuevo no más xd **/

        Cursor cursorIdols;

        cursorIdols = db.rawQuery("SELECT * FROM " + DbIdols.TABLE_IDOLS + " ORDER BY nombre ASC", null);

        while(cursorIdols.moveToNext()){

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
            idol.setImagen(cursorIdols.getBlob(12));;

            listaIdols.add(idol);
        }

        mostrarLista();

    }

    private void mostrarLista() {

        listaNombreIdols = new ArrayList<String>();
        listaNombreIdols.add("Idol");

        for(int i = 0; i < listaIdols.size(); i++){

            listaNombreIdols.add(listaIdols.get(i).getNombre());
        }
    }
}