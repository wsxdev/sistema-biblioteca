package com.biblioteca.modelo.implementacion;

// COMENTARIOS: DAO DE REGISTRO DE LECTORES. IMPLEMENTA OPERACIONES REQUERIDAS POR EL PDF.

import com.biblioteca.modelo.entidad.Lector;
import com.biblioteca.modelo.interfaz.IRegistroLectoresDAO;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RegistroLectoresDAO implements IRegistroLectoresDAO {
    // LISTA DINÁMICA DE REGISTROS DE LECTORES
    private final List<Lector> registros;

    public RegistroLectoresDAO(){
        this.registros = new ArrayList<>();
    }

    @Override
    public synchronized boolean registrarEntrada(Lector lector){
        // VALIDACION BASICA: CEDULA NO NULA
        if(lector == null || lector.getCedula() == null || lector.getCedula().trim().isEmpty()){
            return false;
        }
        // EVITA AÑADIR DUPLICADOS POR CEDULA (SE REQUIERE DEPURAR LUEGO SI HAY ERRORES)
        for(Lector l : registros){
            if(l.getCedula().equals(lector.getCedula())){
                return false;
            }
        }
        registros.add(lector);
        return true;
    }

    @Override
    public synchronized boolean retirarPorError(String cedula){
        if(cedula == null) return false;
        for(int i=0;i<registros.size();i++){
            if(cedidosEqual(registros.get(i).getCedula(), cedula)){
                registros.remove(i);
                return true;
            }
        }
        return false;
    }

    private boolean cedidosEqual(String a, String b){
        return a != null && a.equals(b);
    }

    @Override
    public synchronized Lector buscar(String cedula){
        if(cedula == null) return null;
        for(Lector l: registros){
            if(cedidosEqual(l.getCedula(), cedula)) return l;
        }
        return null;
    }

    @Override
    public synchronized List<Lector> buscarPorBarrio(String barrio){
        if(barrio == null) return new ArrayList<>();
        return registros.stream()
                .filter(l -> barrio.equalsIgnoreCase(l.getBarrio()))
                .collect(Collectors.toList());
    }

    @Override
    public synchronized void ordenarHoraLlegada(){
        // ASUME QUE HORA LLEGADA ES STRING EN FORMATO HH:mm o comparable lexicográficamente
        registros.sort(Comparator.comparing(Lector::getHoraLlegada));
    }

    @Override
    public synchronized void eliminarDuplicados(){
        // DEPURAR DUPLICADOS POR CEDULA, CONSERVANDO PRIMER REGISTRO
        Map<String, Lector> seen = new HashMap<>();
        List<Lector> result = new ArrayList<>();
        for(Lector l : registros){
            if(!seen.containsKey(l.getCedula())){
                seen.put(l.getCedula(), l);
                result.add(l);
            }
        }
        registros.clear();
        registros.addAll(result);
    }

    @Override
    public synchronized Map<String,Integer> conteoRegistoPorBarrio(){
        Map<String,Integer> conteo = new HashMap<>();
        for(Lector l: registros){
            String b = l.getBarrio() == null ? "(SIN BARRIO)" : l.getBarrio();
            conteo.put(b, conteo.getOrDefault(b, 0) + 1);
        }
        return conteo;
    }

    @Override
    public synchronized List<Lector> listarRegistrosOrdenados(){
        List<Lector> copia = new ArrayList<>(registros);
        copia.sort(Comparator.comparing(Lector::getHoraLlegada));
        return copia;
    }

    // METODOS AUXILIARES PARA CONTROLADOR/VISTA
    public synchronized List<Lector> obtenerTodos(){
        return new ArrayList<>(registros);
    }
}
