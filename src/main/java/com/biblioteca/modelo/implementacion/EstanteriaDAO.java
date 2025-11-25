package com.biblioteca.modelo.implementacion;

import com.biblioteca.modelo.interfaz.IEstanteriaDAO;
import com.biblioteca.modelo.entidad.Estanteria;
import com.biblioteca.modelo.entidad.Libro;

public class EstanteriaDAO implements IEstanteriaDAO{
   private Estanteria estanteria;

    public EstanteriaDAO(Estanteria estanteria) {
        this.estanteria = estanteria;
    }
    
    public boolean agregarLibros(Libro libro){
        return estanteria.agregarLibros(libro);
    }
    
   
}
