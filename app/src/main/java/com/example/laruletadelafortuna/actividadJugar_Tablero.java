package com.example.laruletadelafortuna;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.Arrays;
import java.util.Objects;

public class actividadJugar_Tablero extends AppCompatActivity {

    public String[] letras;
    public android.widget.Button[] botonesLetras;
    Button button_resolver;
    GridLayout GridTeclado;

    String FrasePrueba = "HOLA MUNDO"; // Tienen que estar en mayusculas sino el == da fallo
    TextView prueba;
    TextView TW;
    String[] HuecosPalabra;
    String[] FraseResuelta;
    boolean resolviendo = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jugar_tablero);
        getSupportActionBar().hide();

        letras = getResources().getStringArray(R.array.teclado_ES);
        button_resolver = findViewById(R.id.button_resolver);
        GridTeclado = findViewById(R.id.GridTeclado);
        prueba = findViewById(R.id.prueba);
        TW = findViewById(R.id.TextView);

        generarBotonesTeclado();
        habilitarBotonesLetra();
        generarBotonesPanel();
    }

    private String arrayToString(String[] a){
        String b = "";
        for (int i = 0; i < a.length; i++) {
            b+=a[i]+" ";
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
                    if (resolviendo){
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
                    }else {
                        btn.setEnabled(false);
                        for (int i = 0; i < FrasePrueba.length(); i++) {
                            if (FrasePrueba.charAt(i) == a) {
                                HuecosPalabra[i] = a + "";
                            }
                        }
                        TW.setText(arrayToString(HuecosPalabra));
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
            TW.append(HuecosPalabra[i]+" ");
        }
    }

    public void onResolverClicked(View view) {
        prueba.setText("Resolver");
        if(resolviendo){
            // ha ganado la ronda
            // o ha perdido -> pierde turno
        }else {
            resolviendo = true;
        }
        // boolean resolviendo; y en los botones si esta resolviendo que vaya por orden y si acierta guay y sino se resetea a lo nuevo
    }
}
