package com.example.adrcre.areas_figuras_enero;

import java.io.Serializable;

public class Figuras implements Serializable {

    public String nombre;
    public int id, img;


    public Figuras(String nombre, int id, int img) {
        this.nombre =  nombre;
        this.id = id;
        this.img = img;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }




}
