package com.mindwaresrl.egpp.v1.cliente;

import com.mindwaresrl.egpp.v1.aplicacion.Aplicacion;
import com.mindwaresrl.egpp.v1.main.Main;

public class Inicio {

	public static void main(String[] args) {
		Main.construirAplicacion();
		Aplicacion.instancia.getCasoUsoCrearReporte().ejecutar();
		
		Impresora impresora = new Impresora();
		impresora.imprimirResumen(Aplicacion.instancia.getCasoUsoCrearReporte().getResumen());
		impresora.imprimirPropiedades(Aplicacion.instancia.getCasoUsoCrearReporte().getPropiedades());
		impresora.imprimirPropietarios(Aplicacion.instancia.getCasoUsoCrearReporte().getPropietarios());
		impresora.imprimirCuotas(Aplicacion.instancia.getCasoUsoCrearReporte().getCuotas());
	}

}
