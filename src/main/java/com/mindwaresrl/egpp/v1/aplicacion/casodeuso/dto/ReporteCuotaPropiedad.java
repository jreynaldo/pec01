package com.mindwaresrl.egpp.v1.aplicacion.casodeuso.dto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mindwaresrl.egpp.v1.core.Gasto;
import com.mindwaresrl.egpp.v1.core.Propiedad;
import com.mindwaresrl.egpp.v1.core.ZonaReparto;

public class ReporteCuotaPropiedad {
	
	private String idPropiedad;
	private String idPropietario;
	private String nombrePropietario;
	private Map<String, Double> porcentajesPorIdZona;
	private Map<String, Double> importesPorIdZona;
	private Double importeTotal;
	
	public ReporteCuotaPropiedad(Propiedad propiedad, List<ZonaReparto> zonas){
		this.idPropiedad = propiedad.getId();
		this.idPropietario = propiedad.getPropietario().getId();
		this.nombrePropietario = propiedad.getPropietario().getNombre();
		this.porcentajesPorIdZona = obtenerPorcentajesPorIdZona(propiedad, zonas);
		this.importesPorIdZona = obtenerImportesPorIdZona(propiedad, zonas);
		this.importeTotal = obtenerImporteTotal(obtenerImportesPorIdZona(propiedad, zonas));
		//Ejemplo de acoplamiento temporal!
//		this.importeTotal = obtenerImporteTotal(importePorZona);
	}
	
	public ReporteCuotaPropiedad(){
		
	}
	
	public String getIdPropiedad() {
		return idPropiedad;
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

	public Map<String, Double> getImportesPorIdZona() {
		return importesPorIdZona;
	}

	public Double getImporteTotal() {
		return importeTotal;
	}
	
	private Map<String, Double> obtenerPorcentajesPorIdZona(Propiedad propiedad, List<ZonaReparto> zonas) {
		Map<String, Double> porcentajesPorZona = new HashMap<>();
		for (ZonaReparto zona: zonas) {
			porcentajesPorZona.put(zona.getId(), getPorcentajePorZona(propiedad, zona));
		}

		return porcentajesPorZona;
	}
	
	private Map<String, Double> obtenerImportesPorIdZona(Propiedad propiedad, List<ZonaReparto> zonas){
		Map<String, Double> importesPorZona = new HashMap<>();
		for (ZonaReparto zona: zonas) {
			Double importePorZona = (getPorcentajePorZona(propiedad, zona) * importeTotalPorZona(zona)) / 100; 
			importesPorZona.put(zona.getId(), importePorZona);
		}
		
		return importesPorZona;
	}
	
	private Double getPorcentajePorZona(Propiedad propiedad, ZonaReparto zona) {
		return (null != propiedad.getPorcentajePorZona().get(zona) ? propiedad.getPorcentajePorZona().get(zona): 0.0) ; 
	}
	
	private Double importeTotalPorZona(ZonaReparto zona){
		Double importeTotalPorZona = 0.0;
		for (Gasto gasto : zona.getGastos() ){
			importeTotalPorZona += gasto.getImporte();
		}
		return importeTotalPorZona;
	}
	
	private Double obtenerImporteTotal(Map<String, Double> importesPorZona){
		Double importeTotal = 0.0;
		//REM: buen ejemplo para usar streams de java 8
		for (Double importe : importesPorZona.values()) {
			importeTotal += importe;
		}
		return importeTotal;
	}
}
