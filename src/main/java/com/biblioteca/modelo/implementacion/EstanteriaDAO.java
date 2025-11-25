package com.biblioteca.modelo.implementacion;

// COMENTARIOS: DAO DE ESTANTERIA. ESTRUCTURA FIJA DE 30 POSICIONES, ACCESO O(1) POR INDICE.

import com.biblioteca.modelo.interfaz.IEstanteriaDAO;
import com.biblioteca.modelo.entidad.Libro;
import java.util.ArrayList;
import java.util.List;

public class EstanteriaDAO implements IEstanteriaDAO {

    private static final int CAPACIDAD = 30;
    private final Libro[] estanteria;

    public EstanteriaDAO(){
        estanteria = new Libro[CAPACIDAD];
        inicializarEstanteria();
    }

    // INICIALIZA LAS 30 POSICIONES A NULO
    public void inicializarEstanteria(){
        for(int i=0;i<CAPACIDAD;i++) estanteria[i] = null;
    }

    @Override
    public boolean agregarLibros(Libro libro){
        // AGREGA EN LA PRIMERA POSICION LIBRE
        for(int i=0;i<CAPACIDAD;i++){
            if(estanteria[i] == null){
                estanteria[i] = libro;
                return true;
            }
        }
        return false; // ESTANTERIA LLENA
    }

    // ASIGNAR O REEMPLAZAR POR POSICION (1-30)
    public boolean asignarLibroEnPosicion(int posicion, Libro libro){
        if(posicion < 1 || posicion > CAPACIDAD) return false;
        estanteria[posicion-1] = libro;
        return true;
    }

    // DEVUELVE EL LIBRO EN LA POSICION (1-30) O NULL
    public Libro buscarLibroPorPosicion(int posicion){
        if(posicion < 1 || posicion > CAPACIDAD) return null;
        return estanteria[posicion-1];
    }

    @Override
    public Libro[] buscarLibroPorCompartimento(int codigo){
        // INTERFAZ ORIGINAL: SE ENTREGA ARRAY (SE DEVUELVE ARRAY CON 0 O 1 ELEMENTO)
        if(codigo < 1 || codigo > CAPACIDAD) return new Libro[0];
        Libro l = estanteria[codigo-1];
        if(l == null) return new Libro[0];
        return new Libro[]{l};
    }

    @Override
    public void listarPosicionesLibres(){
        // IMPRIME EN CONSOLA, LA VISTA PUEDE USAR getPosicionesLibres
        List<Integer> libres = getPosicionesLibres();
        System.out.println("POSICIONES LIBRES: " + libres);
    }

    @Override
    public void listarPosicionesOcupadas(){
        List<Integer> ocupadas = getPosicionesOcupadas();
        System.out.println("POSICIONES OCUPADAS: " + ocupadas);
    }

    public List<Integer> getPosicionesLibres(){
        List<Integer> libres = new ArrayList<>();
        for(int i=0;i<CAPACIDAD;i++) if(estanteria[i]==null) libres.add(i+1);
        return libres;
    }

    public List<Integer> getPosicionesOcupadas(){
        List<Integer> ocupadas = new ArrayList<>();
        for(int i=0;i<CAPACIDAD;i++) if(estanteria[i]!=null) ocupadas.add(i+1);
        return ocupadas;
    }

    public Libro[] obtenerTodos(){
        return estanteria.clone();
    }
}