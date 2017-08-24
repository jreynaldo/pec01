package com.mindwaresrl.egpp.v1.aplicacion.casodeuso.dto;

import java.util.ArrayList;
import java.util.List;

import com.mindwaresrl.egpp.v1.core.Propiedad;
import com.mindwaresrl.egpp.v1.core.Propietario;

public class ReportePropietario {

	private String idPropietario;
	private String nombrePropietario;
	private String email;
	private List<String> idsPropiedades;
	
	
	public ReportePropietario(Propietario propietario){
		this.idPropietario = propietario.getId();
		this.nombrePropietario = propietario.getNombre();
		this.email = propietario.getEmail();
		this.idsPropiedades = obtenerIdsPropiedades(propietario);
	}
	
	public String getIdPropietario() {
		return idPropietario;
	}
	public String getNombrePropietario() {
		return nombrePropietario;
	}
	public String getEmail() {
		return email;
	}
	public List<String> getIdsPropiedades() {
		return idsPropiedades;
	}
	
	private List<String> obtenerIdsPropiedades(Propietario propietario){
		List<String> idsPropiedades = new ArrayList<>();
		for (Propiedad propiedad : propietario.getPropiedades()){
			idsPropiedades.add(propiedad.getId());
		}
		return idsPropiedades;
	}
}
