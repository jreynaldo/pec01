package com.mindwaresrl.egpp.v1.repo.archivo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mindwaresrl.egpp.v1.core.Entidad;
import com.mindwaresrl.egpp.v1.repo.Repositorio;

public abstract class RepositorioAbstracto<E extends Entidad> implements Repositorio<E> {

	public static final String SEPARADOR = ";";
	
	private Map<String, E> bd = null;

	@Override
	public List<E> recuperarTodos() {
		if (null == bd ) {
			obtenerRegistros();
		}
		return new ArrayList<E>(bd.values());
	}

	@Override
	public E recuperar(String  id) {
		if (null == bd ) {
			obtenerRegistros();
		}
		return bd.get(id);
	}
	
	abstract List<String> obtenerLineas(BufferedReader reader) throws IOException;

	abstract E convertirRegistro(String registro);

	protected abstract String getNombreArchivo();
	
	protected void obtenerRegistros(){
		List<String> registrosSeccion = obtenerLineasSeccion();
		convertirRegistros(registrosSeccion);
	}
	
	protected List<String> obtenerLineasSeccion() {
		try (BufferedReader reader = new BufferedReader(new FileReader(getNombreArchivo()))) {
			return obtenerLineas(reader);
		}
		catch (FileNotFoundException e) {
			throw new IllegalStateException("Archivo no encontrado " + getNombreArchivo());
		}
		catch (IOException e) {
			throw new IllegalStateException("Error de entrada/salida");
		}
	}

	protected void convertirRegistros(List<String> registrosSeccion) {
		this.bd = new HashMap<>();

		for (String registro:registrosSeccion) {
			E entidad = convertirRegistro(registro);
			this.bd.put(entidad.getId(), entidad);
		}
	}

}
