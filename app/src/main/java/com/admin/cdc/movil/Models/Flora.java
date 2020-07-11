package com.admin.cdc.movil.Models;

public class Flora {

    private String Titulo;
    private String Categoria ;
    private String Descripcion ;
    private int Imagen ;

    public Flora(){

    }

    public Flora(String titulo, String categoria, String descripcion, int imagen) {
        Titulo = titulo;
        Categoria = categoria;
        Descripcion = descripcion;
        Imagen = imagen;
    }

    public String getTitulo() {
        return Titulo;
    }

    public void setTitulo(String titulo) {
        Titulo = titulo;
    }

    public String getCategoria() {
        return Categoria;
    }

    public void setCategoria(String categoria) {
        Categoria = categoria;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public int getImagen() {
        return Imagen;
    }

    public void setImagen(int imagen) {
        Imagen = imagen;
    }
}
