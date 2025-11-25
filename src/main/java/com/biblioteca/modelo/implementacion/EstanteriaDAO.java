package com.biblioteca.modelo.implementacion;

import com.biblioteca.modelo.interfaz.IEstanteriaDAO;

import com.biblioteca.modelo.entidad.*;
public class EstanteriaDAO implements IEstanteriaDAO {
    
    @Override
    public boolean agregarLibros(Libro libro){
        
        return true;
    }
    @Override
    public Libro[] buscarLibroPorCompartimento(int codigo){

        return null;
    }
    @Override
    public void listarPosicionesLibres(){

    }
    @Override
    public void listarPosicionesOcupadas(){
        
    }
    
}