package com.mindwaresrl.egpp.v1.repo.archivo;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.mindwaresrl.egpp.v1.core.Departamento;
import com.mindwaresrl.egpp.v1.core.Departamento.TipoVivienda;
import com.mindwaresrl.egpp.v1.core.LocalComercial;
import com.mindwaresrl.egpp.v1.core.PlazaGaraje;
import com.mindwaresrl.egpp.v1.core.PlazaGaraje.TipoGaraje;
import com.mindwaresrl.egpp.v1.core.Propiedad;
import com.mindwaresrl.egpp.v1.core.Propietario;
import com.mindwaresrl.egpp.v1.core.ZonaReparto;
import com.mindwaresrl.egpp.v1.repo.Repositorio;

public class RepositorioPropiedad extends RepositorioAbstracto<Propiedad> {

	public static final Integer INDICE_ID_ZONA = 0;
	public static final Integer INDICE_PORCENTAJE_PARTICIPACION = 1;

	private Repositorio<ZonaReparto> repositorioZona;
	private Repositorio<Propietario> repositorioPropietario;

	public RepositorioPropiedad(Repositorio<ZonaReparto> repositorioZona,
			Repositorio<Propietario> repositorioPropietario) {
		this.repositorioZona = repositorioZona;
		this.repositorioPropietario = repositorioPropietario;
		obtenerRegistros();
	}

	@Override
	protected List<String> obtenerLineas(BufferedReader reader) throws IOException {
		List<String> lineasSeccion = new ArrayList<String>();

		String linea = null;
		while ((linea = reader.readLine()) != null) {

			if (linea.startsWith("#Propiedad")) {
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

	Map<ZonaReparto, Double> convertirZonaReparto(String cadenaZonaReparto) {
		Map<ZonaReparto, Double> porcentajePorZona = new HashMap<>();

		if (StringUtils.isEmpty(cadenaZonaReparto))
			return porcentajePorZona;

		String[] zonas = cadenaZonaReparto.split(",");
		for (String zona : zonas) {
			String[] valores = zona.split("\\-");
			ZonaReparto zonaReparto = repositorioZona.recuperar(valores[INDICE_ID_ZONA]);
			Double porcentajeParticipacion = Double.valueOf(valores[INDICE_PORCENTAJE_PARTICIPACION]);
			porcentajePorZona.put(zonaReparto, porcentajeParticipacion);
		}
		return porcentajePorZona;
	}

	@Override
	Propiedad convertirRegistro(String registro) {
		String[] valores = registro.split(SEPARADOR);

		Propiedad propiedad = null;
		switch (valores[0]) {
		case "D":
			propiedad = crearDepartamento(valores);
			break;
		case "L":
			propiedad = crearLocalComercial(valores);
			break;
		case "G":
			propiedad = crearPlazaGaraje(valores);
			break;

		default:
			throw new IllegalStateException("Tipo de propiedad desconocido");
		}

		return propiedad;
	}

	@Override
	protected String getNombreArchivo() {
		return "comunidad.txt";
	}

	// TODO Este metodo recibe un arreglo con los campos de un departamento
	// incluyendo una referencia al propietario y a los porcentajes de la zona de
	// reparto
	// Deber� completarlo para que devuelva una instancia de tipo propiedad.
	// P/ej. D;1-A;90;07;E-9,C-12;VH;2
	private Propiedad crearDepartamento(String[] valores) {

		

		return new Departamento(valores[1], new Double(valores[2]),
				this.repositorioPropietario.recuperar(valores[3]), convertirZonaReparto(valores[4]), TipoVivienda.convert(valores[5]), new Integer(valores[6]));

			}

	// TODO Este metodo recibe un arreglo con los campos de un local comercial
	// incluyendo una referencia al propietario y a los porcentajes de la zona de
	// reparto
	// Deber� completarlo para que devuelva una instancia de tipo propiedad.
	// P/ej. L;0-A;80;04;E-8;Banco Mundial;Banca
	private Propiedad crearLocalComercial(String[] valores) {

		return new LocalComercial(valores[1], new Double(valores[2]),
				this.repositorioPropietario.recuperar(valores[3]), convertirZonaReparto(valores[4]), valores[5], valores[6]);
		
	}

	// TODO Este metodo recibe un arreglo con los campos de plaza garaje
	// incluyendo una referencia al propietario y a los porcentajes de la zona de
	// reparto
	// Deber� completarlo para que devuelva una instancia de tipo propiedad.
	// P/ej. G;P01;12;14;G-10;A;N
	private Propiedad crearPlazaGaraje(String[] valores) {

		return new PlazaGaraje(valores[1], new Double(valores[2]),
				this.repositorioPropietario.recuperar(valores[3]), convertirZonaReparto(valores[4]),TipoGaraje.convert(valores[5]), valores[6]=="S"? true:false);
		
	}

}
