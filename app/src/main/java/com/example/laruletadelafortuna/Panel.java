package com.example.laruletadelafortuna;

import android.content.ContentValues;
import android.database.Cursor;

import com.example.laruletadelafortuna.estructuraPanel.datosTabla;

import java.util.UUID;

/**
 * Entidad "panel"
 */
public class Panel {
    private String id;
    private String frase;
    private String pista;
    private String categoria;

    public Panel(String id, String frase, String pista, String categoria) {
        this.id = UUID.randomUUID().toString();
        this.frase = frase;
        this.pista = pista;
        this.categoria = categoria;
    }

    public Panel(Cursor cursor) {
        id = cursor.getString(cursor.getColumnIndex(datosTabla.ID));
        frase = cursor.getString(cursor.getColumnIndex(datosTabla.FRASE));
        pista = cursor.getString(cursor.getColumnIndex(datosTabla.PISTA));
        categoria = cursor.getString(cursor.getColumnIndex(datosTabla.CATEGORIA));
    }

    public ContentValues toContentValues() {
        ContentValues values = new ContentValues();
        values.put(datosTabla.ID, id);
        values.put(datosTabla.FRASE, frase);
        values.put(datosTabla.PISTA, pista);
        values.put(datosTabla.CATEGORIA, categoria);
        return values;
    }

    public String getId() {
        return id;
    }

    public String getFrase() {
        return frase;
    }

    public String getPista() {
        return pista;
    }

    public String getCategoria() {
        return categoria;
    }
}