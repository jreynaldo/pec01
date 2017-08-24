package com.mindwaresrl.egpp.v1.aplicacion.casodeuso.dto;

import java.util.HashMap;
import java.util.Map;

import com.mindwaresrl.egpp.v1.core.Departamento;
import com.mindwaresrl.egpp.v1.core.LocalComercial;
import com.mindwaresrl.egpp.v1.core.PlazaGaraje;
import com.mindwaresrl.egpp.v1.core.Propiedad;
import com.mindwaresrl.egpp.v1.core.ZonaReparto;

public class ReportePropiedad {

	private String idPropiedad;
	private Double metrosCuadrados;
	private String idPropietario;
	private String nombrePropietario;
	private Map<String,Double> porcentajesPorIdZona;
	private String informacionAdicional;
	
	public ReportePropiedad(Propiedad propiedad) {
		this.idPropiedad = propiedad.getId();
		this.metrosCuadrados = propiedad.getMetrosCuadrados();
		this.idPropietario = propiedad.getPropietario().getId();
		this.nombrePropietario = propiedad.getPropietario().getNombre();
		this.porcentajesPorIdZona = obtenerPorcentajesPorIdZona(propiedad);
		this.informacionAdicional = obtenerInformacionAdicional(propiedad);
		
	}
	
	public String getIdPropiedad() {
		return idPropiedad;
	}

	public Double getMetrosCuadrados() {
		return metrosCuadrados;
	}

	public String getIdPropietario() {
		return idPropietario;
	}

	public String getNombrePropietario() {
		return nombrePropietario;
	}

	public Map<String, Double> getPorcentajesPorIdZona() {
		return porcentajesPorIdZona;
	}

	public String getInformacionAdicional() {
		return informacionAdicional;
	}
	
	
	private Map<String,Double> obtenerPorcentajesPorIdZona(Propiedad propiedad){
		Map<String,Double> porcentajesPorIdZona = new HashMap<>();
		for (Map.Entry<ZonaReparto, Double> porcentajeZona : propiedad.getPorcentajePorZona().entrySet() ) {
			porcentajesPorIdZona.put(porcentajeZona.getKey().getId(), porcentajeZona.getValue());
		}
		return porcentajesPorIdZona;
	}
	
	private String obtenerInformacionAdicional(Propiedad propiedad){
		String informacionAdicional = null;
		if (propiedad instanceof Departamento) {
			informacionAdicional = obtenerInformacion((Departamento) propiedad);
		} else if (propiedad instanceof LocalComercial) {
			informacionAdicional = obtenerInformacion((LocalComercial) propiedad);			
		} else if (propiedad instanceof PlazaGaraje) {
			informacionAdicional = obtenerInformacion((PlazaGaraje) propiedad);			
		} else {
			throw new IllegalStateException("error informacionAdicional. Tipo de propiedad desconocida");
		}
		
		return informacionAdicional;
	}

	private String obtenerInformacion(Departamento propiedad) {		
		String strTipoVivienda = null;
		switch (propiedad.getTipoVivienda()) {
			case HABITUAL:
				 strTipoVivienda = "Habitual";
				break;
			case NO_HABITUAL:
				 strTipoVivienda = "No Habitual";
				break;
			default:
				throw new IllegalStateException("error informacionAdicional. Tipo de vivencia de Departamente desconocido");
		}
		return String.format("Viv. %s, %d dorm.", strTipoVivienda, propiedad.getNumeroDomitorios());
	}
	
	private String obtenerInformacion(LocalComercial propiedad) {		
		return String.format("%s,%s", propiedad.getNombre(), propiedad.getActividad());
	}
	
	private String obtenerInformacion(PlazaGaraje propiedad) {		
		String strTipoGaraje = null;
		String strDeposito = null;
		
		switch (propiedad.getTipoGaraje()) {
			case ABIERTO:
				 strTipoGaraje="Abierta";
				break;
			case CERRADO:
				 strTipoGaraje="Cerrada";
				break;
			default:
				throw new IllegalStateException("error informacionAdicional. Tipo de PlazaGaraje desconocido");
		}
		
		strDeposito = propiedad.isConDeposito() ? "Con depósito": "Sin depósito";

		return String.format("%s, %s", strTipoGaraje, strDeposito);
	}	
	
}
