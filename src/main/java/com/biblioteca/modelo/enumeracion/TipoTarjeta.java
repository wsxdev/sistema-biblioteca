package com.biblioteca.modelo.enumeracion;

// COMENTARIOS: TIPOS DE TARJETA INMUTABLES, CARGADOS AL INICIO Y NO CAMBIAN EN TIEMPO DE EJECUCION

import java.util.Arrays;
import java.util.List;

public enum TipoTarjeta {
    LECTOR("L", Arrays.asList("LECTURA"), 2),
    VOLUNTARIO("V", Arrays.asList("LECTURA","DEPOSITO"), 4),
    COORDINADOR("C", Arrays.asList("LECTURA","DEPOSITO","ADMINISTRACION"), 10);

    private final String prefijo;
    private final List<String> zonasPermitidas;
    private final int limitePrestamos;

    TipoTarjeta(String prefijo, List<String> zonasPermitidas, int limitePrestamos){
        this.prefijo = prefijo;
        this.zonasPermitidas = zonasPermitidas;
        this.limitePrestamos = limitePrestamos;
    }

    public String getPrefijo(){
        return prefijo;
    }

    public List<String> getZonasPermitidas(){
        return zonasPermitidas;
    }

    public int getLimitePrestamos(){
        return limitePrestamos;
    }
}