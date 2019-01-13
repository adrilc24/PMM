package com.example.adrcre.spinnerdoble;

public class Peliculas {
    public String titulo;
    public int year, img;

    public Peliculas(String titulo, int year, int img){
        this.titulo = titulo;
        this.year = year;
        this.img = img;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }
}
