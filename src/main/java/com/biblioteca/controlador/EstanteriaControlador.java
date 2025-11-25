package com.biblioteca.controlador;

import com.biblioteca.modelo.entidad.Libro;
import com.biblioteca.modelo.implementacion.EstanteriaDAO;
import java.util.List;

public class EstanteriaControlador {
    private final EstanteriaDAO dao;

    public EstanteriaControlador(){
        dao = new EstanteriaDAO();
    }

    public boolean inicializar(){
        dao.inicializarEstanteria();
        return true;
    }

    public boolean asignarLibro(int posicion, Libro libro){
        return dao.asignarLibroEnPosicion(posicion, libro);
    }

    public Libro consultarPorPosicion(int posicion){
        return dao.buscarLibroPorPosicion(posicion);
    }

    public List<Integer> listarLibres(){
        return dao.getPosicionesLibres();
    }

    public List<Integer> listarOcupadas(){
        return dao.getPosicionesOcupadas();
    }

    public Libro[] obtenerTodos(){
        return dao.obtenerTodos();
    }
}
 