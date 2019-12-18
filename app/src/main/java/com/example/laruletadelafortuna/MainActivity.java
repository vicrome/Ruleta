package com.example.laruletadelafortuna;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

// TODO 1->5
/*       3 - Un Toast cuando diga letra que le diga el resultado + sonidito de moneda up
         1 - Boton Ingles (Android Localize)
         1 - Avatar/es
         1 - Sonidos?
         2 - Usuario (nombre?)
         1 - Modo especial con vidas y un timer en plan tienes X segundos para terminar o palmas
         Que es mejor, un array con todas las frases a pelo, leido desde SQLite, leer solo una frase desde sqlite
                usar una clase que herede de applicacion, pasar el array como intent, o una clase pregunta con atributos que sean
                string pregunta, puntuacion jugadores? con varios constructores segun el numero de jugadores, la Pregunta se construye cuando se sabe el num de jugadores?
                con algo aleatorio para sacarlo de la sql o del array
 */


public class MainActivity extends AppCompatActivity {
    TextView prueba;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        prueba = findViewById(R.id.prueba);

        //SharedPreferences si sigue el bug
        atributos at = (atributos) getApplication();
        String[] s = at.getCategorias();
        if (s != null) {
            prueba.setText(s.toString()); // Cuando empieza esta como null asique necesitamos la lista en otro lado
        }

        Resources res = getResources();
        String[] frases = res.getStringArray(R.array.frases);
        String[] pistas = res.getStringArray(R.array.pistas);

        String[] frases1 = new String[5];
        String[] pistas1 = new String[5];
        for (int i = 0; i < 5; i++) {
            int aleatorio = (int) Math.floor(Math.random() * frases.length);
            if(repetido(frases1, frases[aleatorio]) == false){
                frases1[i] = frases[aleatorio].toUpperCase();
            }
            pistas1[i] = pistas[aleatorio].toUpperCase();
        }
        at.setPreguntas(frases1);
        at.setPistas(pistas1);

    }

    @Override
    protected void onResume() {
        super.onResume();
        atributos at = (atributos) getApplication();
        String[] s = at.getCategorias();
        String c = "";

        if (s != null) {
            for (String a : s) {
                c += a + " ";
            }
            prueba.setText(c);
        }
    }
     /*     --- First activity: ---
            Intent intent = new Intent(getActivity(), SecondActivity.class);
            intent.putExtra(Intent.EXTRA_TEXT, "my text");
            startActivity(intent);

            --- Second activity: ---
            Intent intent = getIntent();
            String myText = intent.getExtras().getString(Intent.EXTRA_TEXT);
    */

    public void jugar(View view) {
        Intent i = new Intent(this, actividadNombresYAvatares.class);
        startActivity(i);
    }

    public void configuracion(View view) {
        Intent i = new Intent(this, actividadConfiguracion.class);
        startActivity(i);
    }

    public void informacion(View view) {
        //Intent i = new Intent(this, actividadInformacion.class);
        //startActivity(i);
        Intent i = new Intent(this, actividadJugar_Tablero.class);
        startActivity(i);
    }

    public static boolean repetido(String[] array, String aleatorio) {
        boolean repetido = false;
        for(int i = 0; i < array.length; i++) {
            if(array[i] == aleatorio){
                repetido = true;
                return repetido;
            }
        }
        return repetido;
    }

}
