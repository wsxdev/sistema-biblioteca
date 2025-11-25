package com.biblioteca.modelo.entidad;

import com.biblioteca.modelo.enumeracion.EstadoLibro;

public class Libro {
    private int codigo;
    private String titulo;
    private String autor;
    private String genero;
    private EstadoLibro estado;

    public Libro(int codigo, String titulo, String autor, String genero, EstadoLibro estado) {
        this.codigo = codigo;
        this.titulo = titulo;
        this.autor = autor;
        this.genero = genero;
        // ASIGNA EL ESTADO PASADO COMO PARÃMETRO (USAR ENUM EstadoLibro)
        this.estado = estado == null ? EstadoLibro.DISPONIBLE : estado;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public EstadoLibro getEstado() {
        return estado;
    }

    public void setEstado(EstadoLibro estado) {
        this.estado = estado;
    }
}