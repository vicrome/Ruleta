package com.example.laruletadelafortuna;

import android.provider.BaseColumns;

/**
 * Esquema de la base de datos para los paneles
 */
public class estructuraPanel {

    public static abstract class datosTabla implements BaseColumns{
        public static final String TABLE_NAME ="paneles";

        public static final String ID = "id";
        public static final String FRASE = "frase";
        public static final String PISTA = "pista";
        public static final String CATEGORIA = "categoria";
    }
}
