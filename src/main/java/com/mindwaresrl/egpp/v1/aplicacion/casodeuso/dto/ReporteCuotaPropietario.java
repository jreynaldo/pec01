package com.mindwaresrl.egpp.v1.aplicacion.casodeuso.dto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mindwaresrl.egpp.v1.core.Gasto;
import com.mindwaresrl.egpp.v1.core.Propiedad;
import com.mindwaresrl.egpp.v1.core.Propietario;
import com.mindwaresrl.egpp.v1.core.ZonaReparto;

public class ReporteCuotaPropietario {
	
	private String idPropietario;	
	private String nombrePropietario;
	private Map<String, Double> porcentajesPorIdZona;
	private Map<String, Double> importesPorIdZona;
	private Double importeTotal;

	public ReporteCuotaPropietario(Propietario propietario, List<ZonaReparto> zonas){
		this.idPropietario = propietario.getId();
		this.nombrePropietario = propietario.getNombre();
		this.porcentajesPorIdZona = obtenerPorcentajesPorIdZona(propietario, zonas);
		this.importesPorIdZona = obtenerImportesPorIdZona(propietario, zonas);
		this.importeTotal = obtenerImporteTotal(obtenerImportesPorIdZona(propietario, zonas));
		//Ejemplo de acoplamiento temporal!
//		this.importeTotal = obtenerImporteTotal(importePorZona);		
		
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
	
	private Map<String, Double> obtenerPorcentajesPorIdZona(Propietario propietario, List<ZonaReparto> zonas){
		Map<String, Double> porcentajesPorZona = new HashMap<>();
		for (ZonaReparto zona: zonas) {
			porcentajesPorZona.put(zona.getId(), getPorcentajePorZona(propietario, zona));
		}
		
		return porcentajesPorZona;
	}	
	
	private Map<String, Double> obtenerImportesPorIdZona(Propietario propietario, List<ZonaReparto> zonas){
		Map<String, Double> importesPorZona = new HashMap<>();
		for (ZonaReparto zona: zonas) {
			Double importePorZona = (getPorcentajePorZona(propietario, zona) * importeTotalPorZona(zona)) / 100; 
			importesPorZona.put(zona.getId(), importePorZona);
		}
		
		return importesPorZona;
	}
	
	
	private Double getPorcentajePorZona(Propietario propietario, ZonaReparto zona) {
		Double porcentajePorZona = 0.0;
		
		for(Propiedad propiedad : propietario.getPropiedades()) {
			if (null != propiedad.getPorcentajePorZona().get(zona)) {
				porcentajePorZona += propiedad.getPorcentajePorZona().get(zona);
			}
		}
			
		return porcentajePorZona;
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
		for (Double importe : importesPorZona.values()) {
			importeTotal += importe;
		}
		return importeTotal;
	}	
}
