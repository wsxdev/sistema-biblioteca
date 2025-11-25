package com.biblioteca.modelo.implementacion;

import com.biblioteca.modelo.entidad.Lector;
import com.biblioteca.modelo.interfaz.ILectorDAO;
import java.util.ArrayList;
import java.util.List;

public class LectorDAO implements ILectorDAO{
    // LISTA EN MEMORIA DE LECTORES
    private final List<Lector> lectores = new ArrayList<>();

    @Override
    public synchronized boolean guardarLector(Lector lector){
        if(lector == null || lector.getCedula() == null || lector.getCedula().trim().isEmpty()) return false;
        // Evitar duplicados por cédula
        for(Lector l: lectores){
            if(l.getCedula().equals(lector.getCedula())) return false;
        }
        lectores.add(lector);
        return true;
    }

    @Override
    public synchronized Lector buscarPorCedula(String cedula){
        if(cedula == null) return null;
        for(Lector l: lectores) if(cedula.equals(l.getCedula())) return l;
        return null;
    }

    @Override
    public synchronized ArrayList<Lector> buscarPorBarrio(String barrio){
        ArrayList<Lector> res = new ArrayList<>();
        if(barrio == null) return res;
        for(Lector l: lectores) if(barrio.equalsIgnoreCase(l.getBarrio())) res.add(l);
        return res;
    }

    @Override
    public synchronized boolean eliminarLector(String cedula){
        if(cedula == null) return false;
        for(int i=0;i<lectores.size();i++){
            if(cedula.equals(lectores.get(i).getCedula())){
                lectores.remove(i);
                return true;
            }
        }
        return false;
    }

    // METODO AUXILIAR: OBTENER TODOS
    public synchronized List<Lector> obtenerTodos(){
        return new ArrayList<>(lectores);
    }
}
