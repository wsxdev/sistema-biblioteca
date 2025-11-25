package com.biblioteca.modelo.entidad;

public class Estanteria {
    private Libro[] compartimentos;
    private int codigo;

    public Estanteria(int codigo) {
        this.compartimentos = new Libro[30];
        this.codigo = codigo;
    }
    
    public boolean agregarLibros(Libro libro) {
        for(int i = 0; i < compartimentos.length; i++){
            if(compartimentos[i] == null){
                compartimentos[i] = libro;
                return true;
            }
        }
        return false;
    }

    public Libro[] buscarLibroPorCompartimento(int codigo) {
        return new Libro [0];
    }

    public void listarPosicionesLibres() {
        System.out.println("Posiciones Libres: ");
        for (int i = 0; i < compartimentos.length; i++){
            if ( compartimentos[i] == null){
                System.out.println("Posición " + i + ": Libre");
            }
        }
    }
    
    public void listarPosicionesOcupadas(){
        System.out.println("Posiciones ocupadas: ");
        for ( int i = 0; i < compartimentos.length; i++){
            if (compartimentos [i] != null){
                System.out.println("Posición " + i + ":" + compartimentos[i].getTitulo());
            }
        }
    }

    public Libro[] getCompartimentos() {
        return compartimentos;
    }

    public void setCompartimentos(Libro[] compartimentos) {
        this.compartimentos = compartimentos;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    
}