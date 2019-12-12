package com.example.laruletadelafortuna;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

// TODO
/*  PASAR como parametro que grupon box de jugador esta seleccionado
 */

public class actividad_SeleccionNJugadores extends AppCompatActivity {

    RadioGroup radioGroup;
    int nPlayers = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seleccion_n_jugadores);
        getSupportActionBar().hide();

        radioGroup = findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                RadioButton r = findViewById(checkedId);
                Log.i("checkedId", ""+checkedId);
                String selectedtext = r.getText().toString();
                String n = selectedtext.substring(0, 1);
                Log.i("n", ""+n);
                nPlayers = Integer.parseInt(n);
            }
        });
    }


    public void onSeleccionDeJugadoresClicked(View view) {
        atributos at = (atributos) getApplication();
        switch (nPlayers){
            case 2:
                at.setnPlayers(2);
                break;
            case 3:
                at.setnPlayers(3);
                break;
            case 4:
                at.setnPlayers(4);
                break;
        }
        Intent i = new Intent(this, actividadJugar.class);
        startActivity(i);
    }
}