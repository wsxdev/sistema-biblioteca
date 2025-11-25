package com.biblioteca.modelo.entidad;

import com.biblioteca.modelo.enumeracion.TipoTarjeta;
import com.biblioteca.modelo.enumeracion.ZonaLectura;

public class Tarjeta{
    private String prefijoCodigo;
    private ZonaLectura  zonaLectura;
    private TipoTarjeta tipoTarjeta;
    private int limitePrestamos;
    
    public int consultarLimitePrestamos(){
        return limitePrestamos;
    }
    
}