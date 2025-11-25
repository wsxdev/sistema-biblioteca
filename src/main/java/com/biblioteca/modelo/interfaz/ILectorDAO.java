package com.biblioteca.modelo.interfaz;

import com.biblioteca.modelo.entidad.Lector;
import java.util.ArrayList;

public interface ILectorDAO {
    boolean guardarLector(Lector lector);
    Lector buscarPorCedula(String cedula);
    ArrayList<Lector> buscarPorBarrio(String barrio);
    boolean eliminarLector(String cedula);
    
}
