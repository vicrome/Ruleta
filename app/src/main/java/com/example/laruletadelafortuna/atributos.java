package com.example.laruletadelafortuna;

import android.app.Application;
import android.graphics.drawable.Drawable;

import androidx.lifecycle.ViewModel;

public class atributos extends Application {

    private String[] categorias = {"Cine", "Deporte", "Proverbios", "Viajes", "Musica", "CheckBox"};
    private String[] preguntas;
    private String[] pistas;
    private String[] nombres;
    private Drawable[] avatares;
    private int[] turno;
    private int[] puntuacion;
    private int[] puntuacionGlobal;
    private int[] comodines;
    private String[] FraseActualMantenida;
    private int nPlayers;
    private int nRondas = 5;
    private int nRondaActual;
    private int turnoActual;
    ViewModel viewModel;

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
        setPuntuacion(nPlayers);
        setComodines(nPlayers);
        setTurnoActual(1);
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

    public int[] getPuntuacion(){
        return puntuacion;
    }

    public void setPuntuacion(int nPlayers){
        puntuacion = new int[nPlayers];
    }

    public void variarPuntuacion(int nPlayer, int puntos){
        puntuacion[nPlayer] = puntos;
    }

    public int[] getPuntuacionGlobal(){
        return puntuacionGlobal;
    }

    public void setPuntuacionGlobal(int nPlayers){
        puntuacionGlobal = new int[nPlayers];
    }

    public void variarPuntuacionGlobal(int nPlayer, int puntos){
        puntuacionGlobal[nPlayer] += puntos;
    }

    public int getTurnoActual(){
        return turnoActual;
    }

    public void setTurnoActual(int turno){
        turnoActual = turno;
    }

    public int[] getComodines() {
        return comodines;
    }

    public void setComodines(int nPlayers){
        comodines = new int[nPlayers];
    }

    public void variarComodines(int nPlayer, int valor) {
        this.comodines[nPlayer] += valor ;
    }

    public String[] getFraseActualMantenida() {
        return FraseActualMantenida;
    }

    public void setFraseActualMantenida(String[] fraseActualMantenida) {
        FraseActualMantenida = fraseActualMantenida;
    }



}
