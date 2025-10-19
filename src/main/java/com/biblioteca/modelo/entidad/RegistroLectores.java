package com.biblioteca.modelo.entidad;

import java.util.ArrayList;

public class RegistroLectores {
    private ArrayList<Lector> lector;

    public boolean depurarDuplicados(String cedula) {
        return true;
    }

    public boolean eliminarRegistros(String cedula) {
        return true;
    }

    public ArrayList<Lector> buscarRegistrosCedula(String cedula) {
        return null;
    }
    
    public ArrayList<Lector> buscarRegistrosBarrio(String barrio) {
        return null;
    }
    
    public int contarRegistros(String barrio) {
        return 0;
    }
    
    public ArrayList<Lector> listarRegistros() {
        return null;
    }
    

}