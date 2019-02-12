package com.example.adrcre.alquiler_coches_enero;

import java.io.Serializable;

public class Modelos implements Serializable{
    public String modelo, marca;
    public float precio, precioTot;
    public int tiempo, img;
    public boolean seguro, aire, gps, radio;

    public Modelos(String modelo, String marca, float precio , float precioTot, int tiempo, boolean seguro, boolean aire, boolean gps, boolean radio, int img){
        this.modelo = modelo;
        this.marca = marca;
        this.precio = precio;
        this.tiempo = tiempo;
        this.seguro = seguro;
        this.aire = aire;
        this.gps = gps;
        this.radio = radio;
        this.img = img;
        this.precioTot = precioTot;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public float getPrecioTot() {
        return precioTot;
    }

    public void setPrecioTot(float precioTot) {
        this.precioTot = precioTot;
    }



    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public int getTiempo() {
        return tiempo;
    }

    public void setTiempo(int tiempo) {
        this.tiempo = tiempo;
    }

    public boolean isSeguro() {
        return seguro;
    }

    public void setSeguro(boolean seguro) {
        this.seguro = seguro;
    }

    public boolean isAire() {
        return aire;
    }

    public void setAire(boolean aire) {
        this.aire = aire;
    }

    public boolean isGps() {
        return gps;
    }

    public void setGps(boolean gps) {
        this.gps = gps;
    }

    public boolean isRadio() {
        return radio;
    }

    public void setRadio(boolean radio) {
        this.radio = radio;
    }
}
