package com.example.laruletadelafortuna;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class actividadConfiguracion extends AppCompatActivity {


    CheckBox cbcine;
    CheckBox cbdeporte;
    CheckBox cbproverbios;
    CheckBox cbviajes;
    CheckBox cbmusica;
    CheckBox cbcb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracion);
        getSupportActionBar().hide();

        cbcine = findViewById(R.id.checkBox);
        cbdeporte = findViewById(R.id.checkBox2);
        cbproverbios = findViewById(R.id.checkBox3);
        cbviajes = findViewById(R.id.checkBox4);
        cbmusica = findViewById(R.id.checkBox5);
        cbcb = findViewById(R.id.checkBox6);

        CheckBox[] cb = {cbcine, cbdeporte, cbproverbios, cbviajes, cbmusica, cbcb};

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
        cbcine = findViewById(R.id.checkBox);
        cbdeporte = findViewById(R.id.checkBox2);
        cbproverbios = findViewById(R.id.checkBox3);
        cbviajes = findViewById(R.id.checkBox4);
        cbmusica = findViewById(R.id.checkBox5);
        cbcb = findViewById(R.id.checkBox6);
        CheckBox[] cb = {cbcine, cbdeporte, cbproverbios, cbviajes, cbmusica, cbcb};
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

        if (cbcine.isChecked()) {
            a.add(cbcine.getText());
        }
        if (cbdeporte.isChecked()) {
            a.add(cbdeporte.getText());
        }
        if (cbproverbios.isChecked()) {
            a.add(cbproverbios.getText());
        }
        if (cbviajes.isChecked()) {
            a.add(cbviajes.getText());
        }
        if (cbmusica.isChecked()) {
            a.add(cbmusica.getText());
        }
        if (cbcb.isChecked()) {
            a.add(cbcb.getText());
        }

        String[] categorias = (String[]) a.toArray(new String[a.size()]);
        at.setCategorias(categorias);
        Toast toast = Toast.makeText(getApplicationContext(), "Categorias guardadas", Toast.LENGTH_SHORT);
        toast.show();
        finish();
    }
}