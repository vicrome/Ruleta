package com.example.laruletadelafortuna;

import android.app.Application;
import android.graphics.drawable.Drawable;

public class atributos extends Application {

    private String[] categorias = {"Cine", "Deporte", "Proverbios", "Viajes", "Musica", "CheckBox"};
    private String[] preguntas;
    private String[] pistas;
    private String[] nombres = new String[4];
    private Drawable[] avatares;
    private int[] turno;
    private int nPlayers;
    private int nRondas = 5;
    private int nRondaActual;

    public atributos() {
    }

    public String[] getCategorias() {
        return categorias;
    }

    public void setCategorias(String[] categorias) {
        this.categorias = categorias;
    }

    public String[] getPreguntas() {
        return preguntas;
    }

    public void setPreguntas(String[] preguntas) {
        this.preguntas = preguntas;
    }

    public String[] getPistas() {
        return pistas;
    }

    public void setPistas(String[] pistas) {
        this.pistas = pistas;
    }

    public int getnPlayers() {
        return nPlayers;
    }

    public void setnPlayers(int nPlayers) {
        this.nPlayers = nPlayers;
        turno = new int[nPlayers];
        for (int i = 0; i < nPlayers; i++){
            turno[i] = i+1;
        }
    }

    public int getnRondas() {
        return nRondas;
    }

    public int getnRondaActual() {
        return nRondaActual;
    }

    public void setnRondaActual(int nRondaActual) {
        this.nRondaActual = nRondaActual;
    }

    public int[] getTurno() {
        return turno;
    }

    public void setTurno(int[] turno) {
        this.turno = turno;
    }

    public String[] getNombres(){
        return nombres;
    }

    public void setNombres(String[] lista){
        nombres = lista;
    }

    public Drawable[] getAvatares(){
        return avatares;
    }

    public void setAvatares(Drawable[] lista){
        avatares = lista;
    }
}
