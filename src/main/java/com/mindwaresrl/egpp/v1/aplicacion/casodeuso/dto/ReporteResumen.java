package com.mindwaresrl.egpp.v1.aplicacion.casodeuso.dto;

public class ReporteResumen {

	private String idComunidad;
	private String nombreComunidad;
	private Integer conteoZonas;
	private Integer conteoPropiedades;
	private Integer conteoPropietarios;
	private Integer conteoGastos;
	
	public ReporteResumen(String idComunidad, String nombreComunidad, Integer conteoZonas
						 ,Integer conteoPropiedades, Integer conteoPropietarios, Integer conteoGastos) {
		this.idComunidad = idComunidad;
		this.nombreComunidad = nombreComunidad;
		this.conteoZonas = conteoZonas;
		this.conteoPropiedades = conteoPropiedades;
		this.conteoPropietarios = conteoPropietarios;
		this.conteoGastos = conteoGastos;
	}
	
	
	public String getNombreComunidad() {
		return nombreComunidad;
	}
	public String getIdComunidad() {
		return idComunidad;
	}
	
	public Integer getConteoPropietarios() {
		return conteoPropietarios;
	}
	public void setNombreComunidad(String nombreComunidad) {
		this.nombreComunidad = nombreComunidad;
	}
	
	public Integer getConteoZonas() {
		return conteoZonas;
	}
	
	public void setConteoZonas(Integer conteoZonas) {
		this.conteoZonas = conteoZonas;
	}
	public Integer getConteoPropiedades() {
		return conteoPropiedades;
	}
	public void setConteoPropiedades(Integer conteoPropiedades) {
		this.conteoPropiedades = conteoPropiedades;
	}
	public Integer getConteoGastos() {
		return conteoGastos;
	}
	public void setConteoGastos(Integer conteoGastos) {
		this.conteoGastos = conteoGastos;
	}
}
