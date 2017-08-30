package com.mindwaresrl.egpp.v1.main;

import com.mindwaresrl.egpp.v1.aplicacion.Aplicacion;
import com.mindwaresrl.egpp.v1.aplicacion.casodeuso.CasoUsoCrearReporte;
import com.mindwaresrl.egpp.v1.aplicacion.casodeuso.CasoUsoCrearReporteImpl;
import com.mindwaresrl.egpp.v1.core.Comunidad;
import com.mindwaresrl.egpp.v1.core.Gasto;
import com.mindwaresrl.egpp.v1.core.Propiedad;
import com.mindwaresrl.egpp.v1.core.Propietario;
import com.mindwaresrl.egpp.v1.core.ZonaReparto;
import com.mindwaresrl.egpp.v1.repo.Repositorio;
import com.mindwaresrl.egpp.v1.repo.archivo.RepositorioComunidad;
import com.mindwaresrl.egpp.v1.repo.archivo.RepositorioGasto;
import com.mindwaresrl.egpp.v1.repo.archivo.RepositorioPropiedad;
import com.mindwaresrl.egpp.v1.repo.archivo.RepositorioPropietario;
import com.mindwaresrl.egpp.v1.repo.archivo.RepositorioZonaReparto;

public class Main {

	public static void construirAplicacion(){
		Repositorio<Comunidad> repositorioComunidad = new RepositorioComunidad();
		Repositorio<Propietario> repositorioPropietario = new RepositorioPropietario();
		Repositorio<ZonaReparto> repositorioZonaReparto = new RepositorioZonaReparto();
		
		
		Repositorio<Propiedad> repositorioPropiedad = new RepositorioPropiedad(repositorioZonaReparto, repositorioPropietario);
			Repositorio<Gasto> repositorioGasto = new RepositorioGasto(repositorioZonaReparto);
		
		CasoUsoCrearReporte casoUsoCrerReporte = new CasoUsoCrearReporteImpl(repositorioComunidad
																			, repositorioPropietario 
																			, repositorioZonaReparto
																			, repositorioPropiedad
																			, repositorioGasto
		
				);
		
		Aplicacion.instancia.setCasoUsoCrearReporte(casoUsoCrerReporte);
		
	}
}
