package com.biblioteca.modelo.implementacion;

import com.biblioteca.modelo.enumeracion.TipoTarjeta;
import java.util.Arrays;
import java.util.List;

public class TarjetaDAO {
    private final List<TipoTarjeta> tipos;

    public TarjetaDAO(){
        // CARGA INICIAL DE TIPOS, INMUTABLES
        tipos = Arrays.asList(TipoTarjeta.values());
    }

    public List<TipoTarjeta> obtenerTipos(){
        return tipos;
    }

    public TipoTarjeta buscarPorNombre(String nombre){
        if(nombre == null) return null;
        for(TipoTarjeta t: tipos) if(t.name().equalsIgnoreCase(nombre)) return t;
        return null;
    }

    public String getPrefijo(TipoTarjeta tipo){
        if(tipo == null) return null;
        return tipo.getPrefijo();
    }

    public List<String> getZonasPermitidas(TipoTarjeta tipo){
        if(tipo == null) return null;
        return tipo.getZonasPermitidas();
    }

    public int consultarLimitePrestamos(TipoTarjeta tipo){
        if(tipo == null) return 0;
        return tipo.getLimitePrestamos();
    }
}
