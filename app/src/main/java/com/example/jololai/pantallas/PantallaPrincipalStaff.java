package com.example.jololai.pantallas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.jololai.R;
import com.example.jololai.crud_usuarios.ListaDeUsuarios;
import com.example.jololai.registros.ListaDeComprasCanciones;
import com.example.jololai.registros.ListaDeReproduccionVideos;

public class PantallaPrincipalStaff extends AppCompatActivity {

    ImageButton botonIdols;
    ImageButton botonUsuarios;
    ImageButton botonCanciones;
    ImageButton botonVideos;
    ImageButton botonComprasCanciones;
    ImageButton botonReproducciones;

    Button botonReportes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pantalla_principal_staff);

        botonIdols = findViewById(R.id.verPantallaIdols);
        botonCanciones = findViewById(R.id.verPantallaCanciones);
        botonUsuarios = findViewById(R.id.verPantallaUsuarios);
        botonVideos = findViewById(R.id.verPantallaVideoStaff);
        botonReproducciones = findViewById(R.id.verHistorialVideos);
        botonComprasCanciones = findViewById(R.id.verHistorialCompraCanciones);

        botonIdols.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(PantallaPrincipalStaff.this, PantallaDeIdols.class);
                intent.putExtra("SesionActual", "StaffActivo");
                startActivity(intent);

            }
        });

        botonUsuarios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(PantallaPrincipalStaff.this, ListaDeUsuarios.class);
                intent.putExtra("SesionActual", "StaffActivo");
                startActivity(intent);
            }
        });

        botonCanciones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(PantallaPrincipalStaff.this, PantallaDeCanciones.class);
                intent.putExtra("SesionActual", "StaffActivo");
                startActivity(intent);
            }
        });

        botonVideos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(PantallaPrincipalStaff.this, PantallaDeVideos.class);
                intent.putExtra("SesionActual", "StaffActivo");
                startActivity(intent);
            }
        });


        botonReproducciones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(PantallaPrincipalStaff.this, ListaDeReproduccionVideos.class);
                startActivity(intent);

            }
        });

        botonComprasCanciones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(PantallaPrincipalStaff.this, ListaDeComprasCanciones.class);
                startActivity(intent);

            }
        });

        botonReportes = findViewById(R.id.BotonReportes);

        botonReportes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(PantallaPrincipalStaff.this, PantallaReportes.class);
                startActivity(intent);

            }
        });
    }
}