package com.biblioteca.modelo.interfaz;

import com.biblioteca.modelo.entidad.Libro;

public interface IEstanteriaDAO {
    boolean agregarLibros(Libro libro);
    Libro[] buscarLibroPorCompartimento(int codigo);
    void listarPosicionesLibres();
    void listarPosicionesOcupadas();
}
