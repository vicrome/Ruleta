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

        if (rndm <= 0.25) {
            resultado = "Has caido en : 10";
        }
        if (0.5 > rndm && rndm > 0.25) {
            resultado = "Has caido en : 40";
        }
        if (0.5 < rndm && rndm < 0.75) {
            resultado = "Has caido en : 20";
        }
        if (rndm > 0.75) {
            resultado = "Has caido en : 30";
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