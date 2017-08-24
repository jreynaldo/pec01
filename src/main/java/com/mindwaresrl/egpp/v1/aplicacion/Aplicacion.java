package com.mindwaresrl.egpp.v1.aplicacion;

import com.mindwaresrl.egpp.v1.aplicacion.casodeuso.CasoUsoCrearReporte;

public class Aplicacion {

	private CasoUsoCrearReporte casoUsoCrearReporte;
	
	public static final Aplicacion instancia = new Aplicacion();
	
	private Aplicacion() {}
	
	public CasoUsoCrearReporte getCasoUsoCrearReporte() {
		return casoUsoCrearReporte;
	}

	public void setCasoUsoCrearReporte(CasoUsoCrearReporte casoUsoCrearReporte) {
		this.casoUsoCrearReporte = casoUsoCrearReporte;
	}
	
	public void ejecutar(){
		casoUsoCrearReporte.ejecutar();
	}
	
}
