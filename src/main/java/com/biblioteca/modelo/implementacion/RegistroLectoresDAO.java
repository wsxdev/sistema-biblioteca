package com.biblioteca.modelo.implementacion;

import com.biblioteca.modelo.entidad.Lector;
import com.biblioteca.modelo.interfaz.IRegistroLectoresDAO;

public class RegistroLectoresDAO implements IRegistroLectoresDAO {
    public boolean registrarEntrada(Lector){
        return true;
    }
    public boolean retirarPorError(cedula:String){
        return true;
    }
    public Lector<<arrayList>> buscar(cedula:String){
        return;
    }
    public Lector buscarPorBarrio(barrio:String){
        return ;
    }
    public void ordenarHoraLlegada(){
        return;
    }
    public void eliminarDuplicados(cedula:String){
        return;
    }   
    public void conteoRegistoBarrio(barrio:String){
        return;
    }
    public boolean listarRegistrosOrdenados(){
        return true;
    }
}
