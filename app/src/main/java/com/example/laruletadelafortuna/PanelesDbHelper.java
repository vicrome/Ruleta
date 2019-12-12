package com.example.laruletadelafortuna;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.example.laruletadelafortuna.estructuraPanel.datosTabla;

/**
 * Manejador de la base de datos
 */
public class PanelesDbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Paneles.db";

    public PanelesDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + datosTabla.TABLE_NAME + " ("
                + datosTabla._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + datosTabla.ID + " TEXT NOT NULL,"
                + datosTabla.FRASE + " TEXT NOT NULL,"
                + datosTabla.PISTA + " TEXT NOT NULL,"
                + datosTabla.CATEGORIA + " TEXT NOT NULL,"
                + "UNIQUE (" + datosTabla.ID + "))");


        // Insertar datos ficticios para prueba inicial
        datosPrueba(db);

    }

    private void datosPrueba(SQLiteDatabase sqLiteDatabase) {
        pruebaPanel(sqLiteDatabase, new Panel("M1", "Los Beatles", "Los 4", "Musica"));
        pruebaPanel(sqLiteDatabase, new Panel("M2", "Daddy Yankee", "The Big Boss", "Musica"));
        pruebaPanel(sqLiteDatabase, new Panel("M3", "Wolfgang Amadeus Mozart", "Maestro del clasicismo", "Musica"));
        pruebaPanel(sqLiteDatabase, new Panel("M4", "Michael Jackson", "Rey", "Musica"));
        pruebaPanel(sqLiteDatabase, new Panel("M5", "Louis Armstrong", "Jazz", "Musica"));

        pruebaPanel(sqLiteDatabase, new Panel("D1", "Zinedine Zidane", "Exfutbolista", "Deportes"));
        pruebaPanel(sqLiteDatabase, new Panel("D2", "Michael Phelps", "Agua", "Deportes"));
        pruebaPanel(sqLiteDatabase, new Panel("D3", "Kobe Bryant", "La mamba negra", "Deportes"));
        pruebaPanel(sqLiteDatabase, new Panel("D4", "Rafa Nadal", "ATP", "Deportes"));
        pruebaPanel(sqLiteDatabase, new Panel("D5", "Sebastian Vettel", "Ferrari", "Deportes"));

        pruebaPanel(sqLiteDatabase, new Panel("H1", "Alfonso X", "El sabio", "Historia"));
        pruebaPanel(sqLiteDatabase, new Panel("H2", "John F Kennedy", "Asesinado", "Historia"));
        pruebaPanel(sqLiteDatabase, new Panel("H3", "Francisco Franco", "Dictador", "Historia"));
        pruebaPanel(sqLiteDatabase, new Panel("H4", "William Shakespeare", "El Bardo de Avon", "Historia"));
        pruebaPanel(sqLiteDatabase, new Panel("H5", "Aristoteles", "Filosofo e influencer", "Historia"));

        pruebaPanel(sqLiteDatabase, new Panel("P1", "El señor de los anillos", "Tierra Media", "Peliculas"));
        pruebaPanel(sqLiteDatabase, new Panel("P2", "Maditos Bastardos", "Alemania nazi", "Peliculas"));
        pruebaPanel(sqLiteDatabase, new Panel("P3", "Pesadilla antes de navidad", "Halloween", "Peliculas"));
        pruebaPanel(sqLiteDatabase, new Panel("P4", "Origen", "¿Alguna vez has soñado que estabas soñando?", "Peliculas"));
        pruebaPanel(sqLiteDatabase, new Panel("P5", "El viaje de Chihiro", "Studio Ghibli", "Peliculas"));

        pruebaPanel(sqLiteDatabase, new Panel("V1", "Stonehenge", "Ruinas", "Viajes"));
        pruebaPanel(sqLiteDatabase, new Panel("V2", "Chichen Itza", "Templo de Kukulkán", "Viajes"));
        pruebaPanel(sqLiteDatabase, new Panel("V3", "Opera de Sídney", "Australia", "Viajes"));
        pruebaPanel(sqLiteDatabase, new Panel("V4", "Estatua de la Libertad", "Americana pero no", "Viajes"));
        pruebaPanel(sqLiteDatabase, new Panel("V5", "Notre Dame", "La dama francesa", "Viajes"));

        pruebaPanel(sqLiteDatabase, new Panel("C1", "Hepatitis", "Ataca al higado", "Ciencia"));
        pruebaPanel(sqLiteDatabase, new Panel("C2", "Marie Curie", "Nobel", "Ciencia"));
        pruebaPanel(sqLiteDatabase, new Panel("C3", "Insulina", "Diabetes", "Ciencia"));
        pruebaPanel(sqLiteDatabase, new Panel("C4", "Isaac Newton", "Manzana", "Ciencia"));
        pruebaPanel(sqLiteDatabase, new Panel("C5", "Charles Darwin", "Evolucion", "Ciencia"));




    }

    public long pruebaPanel(SQLiteDatabase db, Panel panel) {
        return db.insert(
                datosTabla.TABLE_NAME,
                null,
                panel.toContentValues());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // No hay operaciones
    }

    public long guardarPanel(Panel panel) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        return sqLiteDatabase.insert(
                datosTabla.TABLE_NAME,
                null,
                panel.toContentValues());

    }

    public Cursor getAllPaneles() {
        return getReadableDatabase()
                .query(
                        datosTabla.TABLE_NAME,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null);
    }

    public Cursor getPanelById(String panelId) {
        Cursor c = getReadableDatabase().query(
                datosTabla.TABLE_NAME,
                null,
                datosTabla.ID + " LIKE ?",
                new String[]{panelId},
                null,
                null,
                null);
        return c;
    }
}
