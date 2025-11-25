package com.biblioteca.controlador;

import com.biblioteca.modelo.entidad.Lector;
import com.biblioteca.modelo.implementacion.RegistroLectoresDAO;
import java.util.List;
import java.util.Map;

public class RegistroLectoresControlador {
	private final RegistroLectoresDAO dao;

	public RegistroLectoresControlador(){
		dao = new RegistroLectoresDAO();
	}

	public boolean registrar(Lector lector){
		return dao.registrarEntrada(lector);
	}

	public boolean retirarPorError(String cedula){
		return dao.retirarPorError(cedula);
	}

	public Lector buscar(String cedula){
		return dao.buscar(cedula);
	}

	public List<Lector> filtrarPorBarrio(String barrio){
		return dao.buscarPorBarrio(barrio);
	}

	public void ordenarPorHora(){
		dao.ordenarHoraLlegada();
	}

	public void depurarDuplicados(){
		dao.eliminarDuplicados();
	}

	public Map<String,Integer> conteoPorBarrio(){
		return dao.conteoRegistoPorBarrio();
	}

	public List<Lector> listarOrdenados(){
		return dao.listarRegistrosOrdenados();
	}

	public List<Lector> obtenerTodos(){
		return dao.obtenerTodos();
	}
}