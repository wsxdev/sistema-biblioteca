package com.biblioteca.modelo.entidad;

import com.biblioteca.modelo.enumeracion.TipoTarjeta;
import com.biblioteca.modelo.enumeracion.ZonaLectura;

public record Tarjeta(String prefijoCodigo, ZonaLectura zonaLectura, TipoTarjeta tipoTarjeta, int limitePrestamos) {
    
}