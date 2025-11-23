package com.biblioteca.modelo.implementacion;

import com.biblioteca.modelo.interfaz.IEstanteriaDAO;

import com.biblioteca.modelo.entidad.*;
public class EstanteriaDAO implements IEstanteriaDAO {

    public boolean agregarLibros(Libro libro){

        return true;
    }
    public Libro[] buscarLibroPorCompartimento(int codigo){

        return null;
    }
    public void listarPosicionesLibres(){

    }

    public void listarPosicionesOcupadas(){
        
    }
    
}