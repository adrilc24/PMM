package com.example.adrcre.examenej1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.widget.ImageView;
import java.io.Serializable;
public class zona implements Serializable{
    public String continente, nombre, envio;
    public float precio, peso;
    public boolean regalo = false, dedicatoria = false;
    public int imagen;
    public Zona(String continente, String nombre, float precio, float peso, String envio, boolean regalo, boolean dedicatoria, int imagen){
        this.continente = continente;
        this.nombre = nombre;
        this.precio = precio;
        this.peso = peso;
        this.envio = envio;
        this.regalo = regalo;
        this.dedicatoria = dedicatoria;
        this.imagen = imagen;
    }
    public void setContinente(String continente){
        this.continente = continente;
    }
    public String getContinente(){
        return this.continente;
    }
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    public String getNombre(){
        return this.nombre;
    }
    public void setPrecio(int precio){
        this.precio = precio;
    }
    public float getPrecio(){
        return this.precio;
    }
    public void setPeso(float peso){
        this.peso = peso;
    }
    public float getPeso(){
        return this.peso;
    }
    public void setEnvio(String envio){
        this.envio = envio;
    }
    public String getEnvio(){
        return this.envio;
    }
    public void setRegalo(boolean regalo){
        this.regalo = regalo;
    }
    public boolean getRegalo(){
        return this.regalo;
    }
    public void setDedicatoria(boolean dedicatoria){
        this.dedicatoria = dedicatoria;
    }
    public boolean getDedicatoria(){
        return this.dedicatoria;
    }
    public void setImagen(int imagen){
        this.imagen = imagen;
    }
    public int getImagen(){
        return this.imagen;
    }
    public String toString(){
        return "Continente: " + this.continente + ". Zona: " + this.nombre + " Peso: " + this.peso
                + ". Envio: " + this.envio + ". Regalo: " + this.regalo + ". Dedicatoria: " + this.dedicatoria;
    }
}
