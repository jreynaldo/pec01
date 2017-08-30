package com.mindwaresrl.egpp.v1.repo.archivo;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.mindwaresrl.egpp.v1.core.ZonaReparto;
import com.mindwaresrl.egpp.v1.core.ZonaReparto.TipoReparto;

import static com.mindwaresrl.egpp.v1.core.ZonaReparto.*;

public class RepositorioZonaReparto extends RepositorioAbstracto<ZonaReparto> {

	public RepositorioZonaReparto() {
		obtenerRegistros();
	}

	// TODO Este metodo debe obtener las lineas de la seccion #Zona del archivo
	// comunidad.txt
	@Override
	List<String> obtenerLineas(BufferedReader reader) throws IOException {
		List<String> lineasSeccion = new ArrayList<String>();

		String linea = null;
		while ((linea = reader.readLine()) != null) {

			if (linea.startsWith("#Zona")) {
				while ((linea = reader.readLine()) != null) {
					if (StringUtils.isBlank(linea)) {
						break;
					} else if (linea.startsWith(".")) {
						continue;
					}
					lineasSeccion.add(linea);
				}
				break;
			}
		}

		return lineasSeccion;
	}

	// TODO Este metodo recibe una linea de la seccion #Zona del archivo
	// comunidad.txt
	// Deber� completarlo para que dada una linea retorne una instancia de la clase
	// ZonaReparto.
	// P/Ej: E;Escalera;P
	@Override
	ZonaReparto convertirRegistro(String registro) {
		String[] registros = registro.split(SEPARADOR);

		return new ZonaReparto(registros[0], registros[1], TipoReparto.convert(registros[2]));
	}

	@Override
	protected String getNombreArchivo() {
		return "comunidad.txt";
	}

}
