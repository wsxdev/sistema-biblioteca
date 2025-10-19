package com.tarjeta;

public record Tarjeta(String prefijoCodigo, ZonaLectura zonaLectura, TipoTarjeta tipoTarjeta, int limitePrestamos) {
    
}