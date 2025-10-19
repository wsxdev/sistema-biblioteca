package com.biblioteca.modelo.entidad;

public class Estanteria {
    private Libro[] compartimentos = new Libro[30];
    private int codigo;

    public boolean agregarLibros(Libro libro) {
        return true;
    }

    public Libro buscarEstanteria(int codigo) {
        return null;
    }

    public Libro[] listarCompartimentos() {
        return null;
    }

}