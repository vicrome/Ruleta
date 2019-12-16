package com.example.laruletadelafortuna;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class actividadNombresYAvatares extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    atributos at;
    int contJugadores, nJugadores;
    GridLayout GridAvatares;
    public int[] avatares = {R.drawable.av1, R.drawable.av2, R.drawable.av3, R.drawable.av4, R.drawable.av5, R.drawable.av6, R.drawable.av7, R.drawable.av8, R.drawable.av9};
    public android.widget.ImageButton[] botonesAvatares;
    public String[] nombres;
    Spinner spinNum;
    EditText etNombre;
    Button annadirJugador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nombres_avatares);
        getSupportActionBar().hide();

        /*bt1 = findViewById(R.id.av1);
        bt2 = findViewById(R.id.av2);
        bt3 = findViewById(R.id.av3);
        bt4 = findViewById(R.id.av4);
        bt5 = findViewById(R.id.av5);
        bt6 = findViewById(R.id.av6);
        bt7 = findViewById(R.id.av7);
        bt8 = findViewById(R.id.av8);
        bt9 = findViewById(R.id.av9);*/

        at = (atributos) getApplication();
        contJugadores = 0;
        GridAvatares = findViewById(R.id.gridAvatares);
        //avatares = getResources().getStringArray(R.array.avatares);
        spinNum = findViewById(R.id.spNumeroJugador);
        etNombre = findViewById(R.id.etNombreJugador);
        annadirJugador = findViewById(R.id.bAnadirJugador);
        generarBotonesAvatares();

    }


    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        nJugadores = parent.getSelectedItemPosition();
        nombres = new String[nJugadores];
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void onAnnadirJugadorClicked(View view) {
        if(contJugadores <= nJugadores){
            if (!etNombre.getText().toString().equals("")) {
                nombres[contJugadores] = etNombre.getText().toString();
                contJugadores++;
            } else {
                Toast t = Toast.makeText(getApplicationContext(), "¡Debes escribir un nombre!", Toast.LENGTH_LONG);
                t.show();
            }
        }else{
            at.setNombres(nombres);
            Intent i = new Intent(this, actividadJugar.class);
            startActivity(i);
        }
    }

    public void generarBotonesAvatares() {
        GridAvatares.removeAllViewsInLayout();
        botonesAvatares = new ImageButton[avatares.length];

        for (int i = 0; i < avatares.length; i++) {
            botonesAvatares[i] = new ImageButton(this);
            botonesAvatares[i].setImageResource(avatares[i]);
            botonesAvatares[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ImageButton btn = (ImageButton) v;
                    btn.setEnabled(true);
                }
            });
            GridAvatares.addView(botonesAvatares[i]);
        }
    }
}
