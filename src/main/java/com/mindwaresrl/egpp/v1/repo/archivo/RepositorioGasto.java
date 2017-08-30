package com.mindwaresrl.egpp.v1.repo.archivo;

import static com.mindwaresrl.egpp.v1.core.Gasto.INDICE_DESCRIPCION;
import static com.mindwaresrl.egpp.v1.core.Gasto.INDICE_ID;
import static com.mindwaresrl.egpp.v1.core.Gasto.INDICE_ID_ZONA_REPARTO;
import static com.mindwaresrl.egpp.v1.core.Gasto.INDICE_IMPORTE;
import static com.mindwaresrl.egpp.v1.core.Gasto.NRO_CAMPOS;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.mindwaresrl.egpp.v1.core.Gasto;
import com.mindwaresrl.egpp.v1.core.ZonaReparto;
import com.mindwaresrl.egpp.v1.repo.Repositorio;

public class RepositorioGasto extends RepositorioAbstracto<Gasto> {

	private Repositorio<ZonaReparto> repositorioZona;

	public RepositorioGasto(Repositorio<ZonaReparto> repositorioZona) {
		this.repositorioZona = repositorioZona;
		obtenerRegistros();
	}

	@Override
	protected List<String> obtenerLineas(BufferedReader reader) throws IOException {
		List<String> lineasSeccion = new ArrayList<String>();

		String linea = null;
		while ((linea = reader.readLine()) != null) {

			if (linea.startsWith("#")) {
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

	@Override
	Gasto convertirRegistro(String registro) {
		String[] valores = registro.split(SEPARADOR);
		if (valores.length != NRO_CAMPOS) {
			throw new IllegalStateException("Error en el registro de Gasto");
		}
		ZonaReparto zonaReparto = repositorioZona.recuperar(valores[INDICE_ID_ZONA_REPARTO]);
		return new Gasto(valores[INDICE_ID], valores[INDICE_DESCRIPCION], Double.valueOf(valores[INDICE_IMPORTE]),
				zonaReparto);
	}

	@Override
	protected String getNombreArchivo() {
		return "gastos.txt";
	}

}
