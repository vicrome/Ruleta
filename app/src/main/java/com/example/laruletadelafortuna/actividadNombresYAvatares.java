package com.example.laruletadelafortuna;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class actividadNombresYAvatares extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    atributos at;
    int contJugadores, nJugadores, id;
    public String[] nombres;
    public Drawable[] avatares;
    Spinner spinNum;
    EditText etNombre;
    TextView[] arrayTv = new TextView[4];
    ImageView[] arrayIv = new ImageView[4];
    Drawable img;
    Button annadirJugador, bt1, bt2, bt3, bt4, bt5, bt6, bt7, bt8, bt9;
    InputMethodManager inputManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nombres_avatares);
        getSupportActionBar().hide();

        bt1 = findViewById(R.id.av1);
        bt2 = findViewById(R.id.av2);
        bt3 = findViewById(R.id.av3);
        bt4 = findViewById(R.id.av4);
        bt5 = findViewById(R.id.av5);
        bt6 = findViewById(R.id.av6);
        bt7 = findViewById(R.id.av7);
        bt8 = findViewById(R.id.av8);
        bt9 = findViewById(R.id.av9);

        bt1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                img = getResources().getDrawable(R.drawable.av1);
            }
        });
        bt2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                img = getResources().getDrawable(R.drawable.av2);
            }
        });
        bt3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                img = getResources().getDrawable(R.drawable.av3);
            }
        });
        bt4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                img = getResources().getDrawable(R.drawable.av4);
            }
        });
        bt5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                img = getResources().getDrawable(R.drawable.av5);
            }
        });
        bt6.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                img = getResources().getDrawable(R.drawable.av6);
            }
        });
        bt7.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                img = getResources().getDrawable(R.drawable.av7);
            }
        });
        bt8.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                img = getResources().getDrawable(R.drawable.av8);
            }
        });
        bt9.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                img = getResources().getDrawable(R.drawable.av9);
            }
        });

        arrayTv[0] = findViewById(R.id.tvJ1);
        arrayTv[1] = findViewById(R.id.tvJ2);
        arrayTv[2] = findViewById(R.id.tvJ3);
        arrayTv[3] = findViewById(R.id.tvJ4);

        arrayIv[0] = findViewById(R.id.ivJ1);
        arrayIv[1] = findViewById(R.id.ivJ2);
        arrayIv[2] = findViewById(R.id.ivJ3);
        arrayIv[3] = findViewById(R.id.ivJ4);

        at = (atributos) getApplication();
        contJugadores = 2;

        spinNum = findViewById(R.id.spNumeroJugador);
        spinNum.setOnItemSelectedListener(this);
        etNombre = findViewById(R.id.etNombreJugador);
        annadirJugador = findViewById(R.id.bAnadirJugador);
        inputManager = (InputMethodManager) this.getSystemService(this.INPUT_METHOD_SERVICE);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        nJugadores = position + 2;
        nombres = new String[nJugadores];
        avatares = new Drawable[nJugadores];
        contJugadores = 0;
        for (TextView tv : arrayTv) {
            tv.setText("");
        }
        for (ImageView iv : arrayIv) {
            iv.setBackground(null);
        }
        annadirJugador.setText("AÑADIR JUGADOR");
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void onAnnadirJugadorClicked(View view) {
        String nombre = "";
        if (annadirJugador.getText().equals("JUGAR")) {
            at.setNombres(nombres);
            at.setnPlayers(nJugadores);
            at.setAvatares(avatares);
            Intent i = new Intent(this, actividadJugar.class);
            startActivity(i);
        }
        if (contJugadores < nJugadores) {
            if (!etNombre.getText().toString().equals("")) {
                if (img != null) {
                    if ((contJugadores + 1) == nombres.length) {
                        annadirJugador.setText("JUGAR");
                    }
                    nombre = etNombre.getText().toString();
                    nombres[contJugadores] = nombre;
                    avatares[contJugadores] = img;
                    arrayTv[contJugadores].setText(nombre);
                    arrayIv[contJugadores].setBackground(img);
                    contJugadores++;
                    etNombre.setText("");
                    img = null;
                    inputManager.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);
                } else {
                    Toast t = Toast.makeText(getApplicationContext(), "Debes seleccionar un avatar.", Toast.LENGTH_LONG);
                    t.show();
                }
            } else {
                Toast t = Toast.makeText(getApplicationContext(), "¿No tienes nombre o qué?", Toast.LENGTH_LONG);
                t.show();
            }
        }
    }


}



