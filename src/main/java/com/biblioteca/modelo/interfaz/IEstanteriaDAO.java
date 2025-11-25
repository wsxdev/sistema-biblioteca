package com.biblioteca.modelo.interfaz;

import com.biblioteca.modelo.entidad.Libro;
public interface IEstanteriaDAO {

    public boolean agregarLibros(Libro libro);
    public Libro[] buscarLibroPorCompartimento(int codigo);
    public void listarPosicionesLibres();
    public void listarPosicionesOcupadas();

}