package com.example.adrcre.alq_coches_adrian;

import java.io.Serializable;

public class datos implements Serializable {
    public String marca, modelo , seguro;
    public boolean extras;
    public float horasAlq, precio;
    public datos(String marca, String modelo, float horasAlq,float precio, boolean extras, String seguro){
        this.marca = marca;
        this.modelo = modelo;
        this.horasAlq = horasAlq;
        this.precio = precio;
        this.extras = extras;
        this.seguro = seguro;
    }

    public void setMarca(String marca){
        this.marca = marca;
    }

    public String getMarca(){
        return this.marca;
    }

    public void setModelo(String modelo){
        this.modelo = modelo;
    }
    public String getModelo(){
        return this.modelo;
    }

    public void setHorasAlq(float horasAlq){
        this.horasAlq = horasAlq;
    }

    public float getHorasAlq(){
        return this.horasAlq;
    }

    public void setPrecio(float precio){
        this.precio = precio;
    }

    public float getPrecio(){
        return this.precio;
    }


    public void setExtras(boolean extras){
        this.extras = extras;
    }

    public boolean getExtras(){
        return this.extras;
    }

    public void setSeguro(String seguro){
        this.seguro = seguro;
    }

    public String getSeguro(){
        return this.seguro;
    }

    public String toString(){
        return "Marca: " + this.marca + ". Modelo: " + this.modelo
                + ". Extras: " + this.extras + ". Seguro: " + this.seguro;
    }
}
