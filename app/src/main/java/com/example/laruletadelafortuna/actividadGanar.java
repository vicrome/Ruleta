package com.example.laruletadelafortuna;

import android.content.Context;
import android.view.Gravity;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;


public class actividadGanar extends AppCompatActivity {
    private TableLayout tableLayout;
    private Context context;
    private String[] cabecera;
    private ArrayList<String[]> datos;
    private TableRow tableRow;
    private TextView textView; // representa cada celda
    private int indexC;
    private int indexR;

    public actividadGanar(TableLayout tableLayout, Context context) {
        this.tableLayout = tableLayout;
        this.context = context;
    }

    public void añadirCabecera(String[] cabecera) {
        this.cabecera = cabecera;

    }

    public void añadirDatos(ArrayList<String[]> datos) {
        this.datos = datos;
    }

    private void nuevaFila() {
        tableRow = new TableRow(context);
    }

    private void nuevaCelda() {
        textView = new TextView(context);
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(25);
    }

    private void crearCabecera() {
        indexC = 0;
        nuevaFila();
        while (indexC < cabecera.length) {
            nuevaCelda();
            textView.setText(cabecera[indexC++]);
            tableRow.addView(textView, newTableRowParams());
        }
        tableLayout.addView(tableRow);
    }

    private void CrearDatosTabla() {
        String info;
        //recorremos las filas con el primer for
        for (indexR = 1; indexR <= cabecera.length; indexR++) {
            nuevaFila();
            for (indexC = 0; indexC < cabecera.length; indexC++) {
                nuevaCelda();
                String [] columnas = datos.get(indexR-1); //-1 porque el for inicia en 1
                info = (indexC < columnas.length) ? columnas[indexC] : "";
                textView.setText(info);
                tableRow.addView(textView, newTableRowParams());
            }
            tableLayout.addView(tableRow);
        }
    }

    private TableRow.LayoutParams newTableRowParams() {
        TableRow.LayoutParams parametros = new TableRow.LayoutParams();
        parametros.setMargins(1,1,1,1);
        parametros.weight = 1;
        return parametros;
    }
}
