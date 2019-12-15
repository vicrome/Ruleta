package com.example.laruletadelafortuna;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.text.Layout;
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

    ImageView imageView2;
    TextView text;
    RotateAnimation r;
    TextView twTurno;
    TextView twPuntuacion;
    atributos at;
    int nPlayers;
    int[] aturno;
    int contador;
    int turno;
    double porcion = 0.083;
    GradientDrawable gd;
    androidx.constraintlayout.widget.ConstraintLayout ly;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jugar);
        getSupportActionBar().hide();

        imageView2 = findViewById(R.id.imageView2);
        text = findViewById(R.id.textView);
        twTurno = findViewById(R.id.twTurno);
        twPuntuacion = findViewById(R.id.twPuntuacion);

        at = (atributos) getApplication();
        nPlayers = at.getnPlayers();
        aturno = at.getTurno();
        contador = 0;
        turno = aturno[contador];
        twTurno.setText("Turno de Player " + turno);

        gd = new GradientDrawable();
        gd.setShape(0);
        gd.setCornerRadius(5);
        ly = findViewById(ConstraintLayout);

    }

    public void botononclick(View view) {
        imageView2.setRotation(0); // Reseteamos a 0. Solo mientras no llamemos a otro activity
        contador++;
        if (contador>nPlayers-1) contador = 0;
        turno = aturno[contador];

        final double rndm = Math.random(); // Rndm que nos dice donde cae

        if (rndm <= porcion) {
            resultado = "Has caido en : 25";
        }
        if (2*porcion > rndm && rndm > porcion) {
            resultado = "Has caido en : 50";
        }
        if (3*porcion > rndm && rndm > 2*porcion) {
            resultado = "Has caido en : 200";
        }
        if (4*porcion > rndm && rndm > 3*porcion) {
            resultado = "Ohh..Pierdes el turno :/";
        }
        if (5*porcion > rndm && rndm > 4*porcion) {
            resultado = "Has caido en : 100";
        }
        if (6*porcion > rndm && rndm > 5*porcion) {
            resultado = "Has caido en : 75";
        }
        if (7*porcion > rndm && rndm > 6*porcion) {
            resultado = "Has ganado un COMODIN!";
        }
        if (8*porcion > rndm && rndm > 7*porcion) {
            resultado = "Has caido en : 50";
        }
        if (9*porcion > rndm && rndm > 8*porcion) {
            resultado = "Has caido en : 200";
        }
        if (10*porcion > rndm && rndm > 9*porcion) {
            resultado = "Has caido en Quiebra";
        }
        if (11*porcion > rndm && rndm > 10*porcion) {
            resultado = "Has caido en : 100";
        }
        if (12*porcion > rndm && rndm > 11*porcion) {
            resultado = "Has caido en : 75";
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
                text.setText(resultado);
                twTurno.setText("Turno de Player " + turno);
                switch(turno){
                    case 1:
                        gd.setStroke(5,Color.GREEN);
                        ly.setBackground(gd);
                        break;
                    case 2:
                        gd.setStroke(5,Color.RED);
                        ly.setBackground(gd);
                        break;
                    case 3:
                        gd.setStroke(5,Color.BLUE);
                        ly.setBackground(gd);
                        break;
                    case 4:
                        gd.setStroke(5,Color.YELLOW);
                        ly.setBackground(gd);
                        break;
                }
            }
        });

    }
}