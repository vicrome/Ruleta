package com.example.laruletadelafortuna;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.laruletadelafortuna.R.id.ConstraintLayout;

public class actividadJugar extends AppCompatActivity {

    private static final int MIN_VUELTAS = 4;
    private static final float ROTATE_FROM = 0.0f;
    private static final float VUELTA = 360.0f;


    private String resultado;

    ImageView imageView2, ivAvatar;
    TextView text, twPuntuacion, tvNombre, tvPuntuacion;
    RotateAnimation r;
    atributos at;
    int nPlayers;
    int[] aturno;
    int contador;
    int turno;
    boolean pierde;
    double porcion = 0.083;
    GradientDrawable gd;
    androidx.constraintlayout.widget.ConstraintLayout ly;
    Toast t;

    String[] nombres;
    Drawable[] avatares;

    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jugar);
        getSupportActionBar().hide();

        imageView2 = findViewById(R.id.imageView2);
        ivAvatar = findViewById(R.id.ivAvatar);
        text = findViewById(R.id.textView);
        tvNombre = findViewById(R.id.tvNombre);
        tvPuntuacion = findViewById(R.id.tvPuntuacion);

        at = (atributos) getApplication();
        nPlayers = at.getnPlayers();
        aturno = at.getTurno();
        contador = 0;
        turno = aturno[contador];

        gd = new GradientDrawable();
        gd.setShape(0);
        gd.setCornerRadius(5);
        ly = findViewById(ConstraintLayout);

        nombres = at.getNombres();
        avatares = at.getAvatares();
        tvNombre.setText(nombres[0]);
        ivAvatar.setBackground(avatares[0]);

    }

    @Override
    public void onResume() {
        super.onResume();
        text.setText(resultado);
        tvNombre.setText(nombres[at.getTurnoActual() - 1]);
        ivAvatar.setBackground(avatares[at.getTurnoActual() - 1]);
        Log.i("TURNO AL RESUME", "" + at.getTurnoActual());
        switch (at.getTurnoActual()) {
            case 1:
                gd.setStroke(5, Color.GREEN);
                ly.setBackground(gd);
                break;
            case 2:
                gd.setStroke(5, Color.RED);
                ly.setBackground(gd);
                break;
            case 3:
                gd.setStroke(5, Color.BLUE);
                ly.setBackground(gd);
                break;
            case 4:
                gd.setStroke(5, Color.YELLOW);
                ly.setBackground(gd);
                break;
        }
    }

    public void miResume() {
        super.onResume();
        text.setText(resultado);
        tvNombre.setText(nombres[turno - 1]);
        ivAvatar.setBackground(avatares[turno - 1]);
        switch (turno) {
            case 1:
                gd.setStroke(5, Color.GREEN);
                ly.setBackground(gd);
                break;
            case 2:
                gd.setStroke(5, Color.RED);
                ly.setBackground(gd);
                break;
            case 3:
                gd.setStroke(5, Color.BLUE);
                ly.setBackground(gd);
                break;
            case 4:
                gd.setStroke(5, Color.YELLOW);
                ly.setBackground(gd);
                break;
        }
    }

    public void botononclick(View view) {
        imageView2.setRotation(0); // Reseteamos a 0. Solo mientras no llamemos a otro activity
        contador++;
        if (contador > nPlayers - 1) contador = 0;
        turno = aturno[contador];
        pierde = false;
        Log.i("TURNO AL JUGAR", "" + at.getTurnoActual());

        final double rndm = Math.random(); // Rndm que nos dice donde cae

        if (rndm <= porcion) {
            resultado = "Has caido en : 25";
            at.variarPuntuacion(turno - 1, 25);
        }
        if (2 * porcion > rndm && rndm > porcion) {
            resultado = "Has caido en : 50";
            at.variarPuntuacion(turno - 1, 50);
        }
        if (3 * porcion > rndm && rndm > 2 * porcion) {
            resultado = "Has caido en : 200";
            at.variarPuntuacion(turno - 1, 200);
        }
        if (4 * porcion > rndm && rndm > 3 * porcion) {
            resultado = "Ohh..Pierdes el turno :/";
            pierde = true;
            t = Toast.makeText(getApplicationContext(), "Has perdido el turno :(", Toast.LENGTH_LONG);
        }
        if (5 * porcion > rndm && rndm > 4 * porcion) {
            resultado = "Has caido en : 100";
            at.variarPuntuacion(turno - 1, 100);
        }
        if (6 * porcion > rndm && rndm > 5 * porcion) {
            resultado = "Has caido en : 75";
            at.variarPuntuacion(turno - 1, 75);
        }
        if (7 * porcion > rndm && rndm > 6 * porcion) {
            resultado = "¡Has ganado un COMODIN!";
            at.variarComodines(turno - 1, at.getComodines()[turno - 1] + 1);
        }
        if (8 * porcion > rndm && rndm > 7 * porcion) {
            resultado = "Has caido en : 50";
            at.variarPuntuacion(turno - 1, 50);
        }
        if (9 * porcion > rndm && rndm > 8 * porcion) {
            resultado = "Has caido en : 200";
            at.variarPuntuacion(turno - 1, 200);
        }
        if (10 * porcion > rndm && rndm > 9 * porcion) {
            resultado = "Has caido en Quiebra";
            at.variarPuntuacion(turno - 1, 0);
            pierde = true;
            t = Toast.makeText(getApplicationContext(), "Has perdido el turno y todos tus puntos :((", Toast.LENGTH_LONG);
        }
        if (11 * porcion > rndm && rndm > 10 * porcion) {
            resultado = "Has caido en : 100";
            at.variarPuntuacion(turno - 1, 100);
        }
        if (12 * porcion > rndm && rndm > 11 * porcion) {
            resultado = "Has caido en : 75";
            at.variarPuntuacion(turno - 1, 75);
        }

        r = new RotateAnimation(ROTATE_FROM, (float) ((rndm + MIN_VUELTAS) * VUELTA), Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        r.setDuration((long) 2000);
        r.setInterpolator(new DecelerateInterpolator());
        r.setRepeatCount(0);
        r.setFillAfter(true); // Para que no resetee la imagen
        imageView2.startAnimation(r);
        r.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation arg0) {
            }

            @Override
            public void onAnimationRepeat(Animation arg0) {
            }

            @Override
            public void onAnimationEnd(Animation arg0) {
                if (!pierde) {
                    Intent i = new Intent(getApplicationContext(), actividadJugar_Tablero.class);
                    startActivity(i);
                } else {
                    t.show();
                    onResume();
                }
            }
        });
    }
}