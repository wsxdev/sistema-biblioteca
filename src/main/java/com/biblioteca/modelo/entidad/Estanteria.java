package com.biblioteca.modelo.entidad;

public class Estanteria {
    private Libro[] compartimentos = new Libro[30];
    private int codigo;
    
    public Estanteria(Libro[] compartimentos, int codigo) {
        this.compartimentos = compartimentos;
        this.codigo = codigo;
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