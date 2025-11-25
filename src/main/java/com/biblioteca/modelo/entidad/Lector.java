package com.biblioteca.modelo.entidad;

import com.biblioteca.modelo.enumeracion.TipoTarjeta;

public class Lector {
    private String cedula;
    private String barrio;
    private String horaLlegada;

    public Lector(String cedula, String barrio, String horaLlegada) {
        this.cedula = cedula;
        this.barrio = barrio;
        this.horaLlegada = horaLlegada;
    }

    public boolean registrarLector(TipoTarjeta tipoTarjeta){
        System.out.println("Registrando lector con cédula: "+ cedula+ " y tarjeta: "+tipoTarjeta);
        return true;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getBarrio() {
        return barrio;
    }

    public void setBarrio(String barrio) {
        this.barrio = barrio;
    }

    public String getHoraLlegada() {
        return horaLlegada;
    }

    public void setHoraLlegada(String horaLlegada) {
        this.horaLlegada = horaLlegada;
    }
    
}