package com.example.laruletadelafortuna;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class actividadConfiguracion extends AppCompatActivity {


    CheckBox cbmusica;
    CheckBox cbdeportes;
    CheckBox cbhistoria;
    CheckBox cbpeliulas;
    CheckBox cbviajes;
    CheckBox cbciencia;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracion);
        getSupportActionBar().hide();

        cbmusica = findViewById(R.id.checkBox);
        cbdeportes = findViewById(R.id.checkBox2);
        cbhistoria = findViewById(R.id.checkBox3);
        cbpeliulas = findViewById(R.id.checkBox4);
        cbviajes = findViewById(R.id.checkBox5);
        cbciencia = findViewById(R.id.checkBox6);

        CheckBox[] cb = {cbmusica, cbdeportes, cbhistoria, cbpeliulas, cbviajes, cbciencia};

        atributos at = (atributos) getApplication();
        String[] categorias = at.getCategorias();
        for (int i = 0; i < categorias.length; i++) {
            for (int j = 0; j < cb.length; j++) {
                if (categorias[i].equals(cb[j].getText())) {
                    // hay que compararlo con cada elemento de cb no solo con i
                    cb[j].setChecked(true);
                }
            }
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        cbmusica = findViewById(R.id.checkBox);
        cbdeportes = findViewById(R.id.checkBox2);
        cbhistoria = findViewById(R.id.checkBox3);
        cbpeliulas = findViewById(R.id.checkBox4);
        cbviajes = findViewById(R.id.checkBox5);
        cbciencia = findViewById(R.id.checkBox6);
        CheckBox[] cb = {cbmusica, cbdeportes, cbhistoria, cbpeliulas, cbviajes, cbciencia};
        atributos at = (atributos) getApplication();
        String[] categorias = at.getCategorias();
        for (int i = 0; i < categorias.length; i++) {
            for (int j = 0; j < cb.length; j++) {
                if (categorias[i].equals(cb[j].getText())) {
                    // hay que compararlo con cada elemento de cb no solo con i
                    cb[j].setChecked(true);
                }
            }
        }
    }

    // Hay que mirar en que estado estan y ponerlos checked acordemente, sino siempre cheked..
    public void onGuardarClicked(View view) {


        atributos at = (atributos) getApplication();
        ArrayList a = new ArrayList();

        if (cbmusica.isChecked()) {
            a.add(cbmusica.getText());
        }
        if (cbdeportes.isChecked()) {
            a.add(cbdeportes.getText());
        }
        if (cbhistoria.isChecked()) {
            a.add(cbhistoria.getText());
        }
        if (cbpeliulas.isChecked()) {
            a.add(cbpeliulas.getText());
        }
        if (cbviajes.isChecked()) {
            a.add(cbviajes.getText());
        }
        if (cbciencia.isChecked()) {
            a.add(cbciencia.getText());
        }

        String[] categorias = (String[]) a.toArray(new String[a.size()]);
        at.setCategorias(categorias);
        Toast toast = Toast.makeText(getApplicationContext(), "Categorias guardadas", Toast.LENGTH_SHORT);
        toast.show();
        finish();
    }
}