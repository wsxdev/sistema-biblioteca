package com.biblioteca.modelo.interfaz;

import com.biblioteca.modelo.entidad.Lector;
import java.util.List;

public interface IRegistroLectoresDAO {
	public boolean registrarEntrada(Lector lector);
	public boolean retirarPorError(String cedula);
	public Lector buscar(String cedula);
	public List<Lector> buscarPorBarrio(String barrio);
	public void ordenarHoraLlegada();
	public void eliminarDuplicados();
	public java.util.Map<String,Integer> conteoRegistoPorBarrio();
	public List<Lector> listarRegistrosOrdenados();
}
