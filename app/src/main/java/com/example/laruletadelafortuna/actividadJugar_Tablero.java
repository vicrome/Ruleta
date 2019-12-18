package com.example.laruletadelafortuna;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.Arrays;
import java.util.Objects;

public class actividadJugar_Tablero extends AppCompatActivity {

    public String[] letras;
    public android.widget.Button[] botonesLetras;
    Button button_resolver;
    GridLayout GridTeclado;
    atributos at;

    String FrasePrueba; // Tienen que estar en mayusculas sino el == da fallo
    TextView prueba, TW, tvPuntos;
    String[] HuecosPalabra;
    String[] FraseResuelta;
    boolean resolviendo = false;
    int[] puntuacion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jugar_tablero);
        getSupportActionBar().hide();

        at = (atributos) getApplication();
        FrasePrueba = at.getPreguntas()[at.getnRondaActual()];
        letras = getResources().getStringArray(R.array.teclado_ES);
        button_resolver = findViewById(R.id.button_resolver);
        GridTeclado = findViewById(R.id.GridTeclado);
        prueba = findViewById(R.id.prueba);
        TW = findViewById(R.id.TextView);
        tvPuntos = findViewById(R.id.tvPuntuacion);
        puntuacion = at.getPuntuacion();
        HuecosPalabra = at.getFraseActualMantenida();
        generarBotonesTeclado();
        habilitarBotonesLetra();
        generarBotonesPanel();
    }

    private String arrayToString(String[] a) {
        String b = "";
        for (int i = 0; i < a.length; i++) {
            b += a[i] + " ";
        }
        return b;
    }

    private void generarBotonesTeclado() {
        GridTeclado.removeAllViewsInLayout();
        botonesLetras = new Button[letras.length];

        for (int i = 0; i < letras.length; i++) {
            botonesLetras[i] = new Button(this);
            botonesLetras[i].setText(letras[i]);
            botonesLetras[i].setTextColor(Color.parseColor("#FFAB00"));
            botonesLetras[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Button btn = (Button) v;
                    char a = btn.getText().charAt(0);
                    if (resolviendo) {
                        FraseResuelta = HuecosPalabra; // estan las _ y los " ".
                        for (int i = 0; i < FrasePrueba.length(); i++) {
                            if (FraseResuelta[i] == "_") {
                                FraseResuelta[i] = a + "";
                                break;
                            }
                        }
                        TW.setText(arrayToString(FraseResuelta));
                        prueba.setText("" + a);
                        /*
                        Hacemos una copia,  y mostramos la copia,  entonces reemplazados cada_ con la siguiente letra que meta
                        Cuando haya metido todas las letras se compara con la solucion
                        Si es errónea pues se dice que falla y pierde turno
                                                 */
                    } else {
                        btn.setEnabled(false);
                        if (a == 'a' || a == 'e' || a == 'i' || a == 'o' || a == 'u') {
                            if (at.getComodines()[at.getTurnoActual()] > 0) {
                                at.variarComodines(at.getTurnoActual(), -1);
                                for (int i = 0; i < FrasePrueba.length(); i++) {
                                    if (FrasePrueba.charAt(i) == a) {
                                        HuecosPalabra[i] = a + "";
                                    }
                                }
                            } else if (puntuacion[at.getTurnoActual()] > 50) {
                                puntuacion[at.getTurnoActual()] -= 50;
                                for (int i = 0; i < FrasePrueba.length(); i++) {
                                    if (FrasePrueba.charAt(i) == a) {
                                        HuecosPalabra[i] = a + "";
                                    }
                                }
                            } else {
                                Toast t = Toast.makeText(getApplicationContext(), "¡No tienes suficientes puntos!", Toast.LENGTH_LONG);
                                t.show();
                            }
                        } else {
                            int contador = 0;
                            for (int i = 0; i < FrasePrueba.length(); i++) {
                                if (FrasePrueba.charAt(i) == a) {
                                    HuecosPalabra[i] = a + "";
                                    contador++;
                                }
                            }
                            if (contador == 0) {
                                Toast t = Toast.makeText(getApplicationContext(), "¡La letra introducida no está en la frase!", Toast.LENGTH_LONG);
                                t.show();
                                if (at.getTurnoActual() < at.getnPlayers()) {
                                    at.setTurnoActual(at.getTurnoActual() + 1);
                                } else {
                                    at.setTurnoActual(1);
                                }
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        finish();
                                    }
                                }, 2000);
                            } else {
                                Toast t = Toast.makeText(getApplicationContext(), "Letra correcta. Gira de nuevo!", Toast.LENGTH_LONG);
                                t.show();
                                at.setTurnoActual(at.getTurnoActual());
                                Log.i("TURNO CUANDO ACIERTA", "" + at.getTurnoActual());
                                TW.setText(arrayToString(HuecosPalabra));

                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        finish();
                                    }
                                }, 2000);
                            }
                        }
                        TW.setText(arrayToString(HuecosPalabra));
                        at.setFraseActualMantenida(HuecosPalabra);
                        prueba.setText("" + a);
                    }
                }
            });
            GridTeclado.addView(botonesLetras[i]);
        }

    }


    private void habilitarBotonesLetra() {
        for (android.widget.Button boton : botonesLetras) {
            if (!boton.isEnabled()) {
                boton.setEnabled(true);
            }
        }
    }

    private void generarBotonesPanel() {
        TW.setText("");
        HuecosPalabra = new String[FrasePrueba.length()];

        for (int i = 0; i < FrasePrueba.length(); i++) {
            if (Character.isWhitespace(FrasePrueba.charAt(i))) {
                HuecosPalabra[i] = " ";
            } else {
                HuecosPalabra[i] = "_";
            }
            TW.append(HuecosPalabra[i] + " ");
        }
    }

    public void onResolverClicked(View view) {
        prueba.setText("Resolver");
        char[] letras = FrasePrueba.toCharArray();
        boolean fallo = false;
        if (resolviendo) {
            for (int i = 0; i < FraseResuelta.length; i++) {
                if (!FraseResuelta[i].equals(letras[i])) {
                    fallo = true;
                }
            }
            if (fallo) {
                TW.setText(arrayToString(HuecosPalabra));
                Toast.makeText(getApplicationContext(), "¡Has fallado! Pierdes el turno...", Toast.LENGTH_LONG);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        finish();
                    }
                }, 2000);
            } else {
                Toast.makeText(getApplicationContext(), "¡Respuesta correcta!", Toast.LENGTH_LONG);
                int jugador = at.getTurnoActual();
                at.variarPuntuacionGlobal(jugador, at.getPuntuacionGlobal()[jugador] += at.getPuntuacion()[jugador]);
                at.setnRondaActual(at.getnRondaActual() + 1);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        finish();
                    }
                }, 2000);
            }
        } else {
            resolviendo = true;
        }
        // boolean resolviendo; y en los botones si esta resolviendo que vaya por orden y si acierta guay y sino se resetea a lo nuevo
    }
}