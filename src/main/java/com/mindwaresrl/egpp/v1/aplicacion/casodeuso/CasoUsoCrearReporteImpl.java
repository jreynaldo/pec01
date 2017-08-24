package com.mindwaresrl.egpp.v1.aplicacion.casodeuso;

import java.util.ArrayList;
import java.util.List;

import com.mindwaresrl.egpp.v1.aplicacion.casodeuso.dto.ReporteCuota;
import com.mindwaresrl.egpp.v1.aplicacion.casodeuso.dto.ReporteCuotaPropiedad;
import com.mindwaresrl.egpp.v1.aplicacion.casodeuso.dto.ReporteCuotaPropietario;
import com.mindwaresrl.egpp.v1.aplicacion.casodeuso.dto.ReportePropiedad;
import com.mindwaresrl.egpp.v1.aplicacion.casodeuso.dto.ReportePropietario;
import com.mindwaresrl.egpp.v1.aplicacion.casodeuso.dto.ReporteResumen;
import com.mindwaresrl.egpp.v1.core.Comunidad;
import com.mindwaresrl.egpp.v1.core.Gasto;
import com.mindwaresrl.egpp.v1.core.Propiedad;
import com.mindwaresrl.egpp.v1.core.Propietario;
import com.mindwaresrl.egpp.v1.core.ZonaReparto;
import com.mindwaresrl.egpp.v1.repo.Repositorio;

public class CasoUsoCrearReporteImpl implements CasoUsoCrearReporte {

	private Repositorio<Comunidad> repositorioComunidad;
	private Repositorio<Propietario> repositorioPropietario;
	private Repositorio<ZonaReparto> repositorioZonaReparto;
	private Repositorio<Propiedad> repositorioPropiedad;
	private Repositorio<Gasto> repositorioGasto;

	private ReporteResumen resumen;
	private List<ReportePropiedad> propiedades;
	private List<ReportePropietario> propietarios;
	private ReporteCuota cuotas;
	

	public CasoUsoCrearReporteImpl(Repositorio<Comunidad> repositorioComunidad
									, Repositorio<Propietario> repositorioPropietario
									, Repositorio<ZonaReparto> repositorioZonaReparto
									, Repositorio<Propiedad> repositorioPropiedad
									, Repositorio<Gasto> repositorioGasto
								   ) {
		
		this.repositorioComunidad = repositorioComunidad;
		this.repositorioPropietario = repositorioPropietario;
		this.repositorioZonaReparto = repositorioZonaReparto;
		this.repositorioPropiedad = repositorioPropiedad;
		this.repositorioGasto = repositorioGasto;
	}
	
	@Override
	public void ejecutar() {
		generarResumen();
		generarPropiedades();
		generarPropietarios();
		generarCuotas();
	}
	
	@Override
	public ReporteResumen getResumen() {
		return resumen;
	}
	
	@Override
	public List<ReportePropiedad> getPropiedades() {
		return propiedades;
	}
	
	@Override
	public List<ReportePropietario> getPropietarios() {
		return propietarios;
	}

	@Override	
	public ReporteCuota getCuotas() {
		return cuotas;
	}
	
	
	private void generarResumen() {
		resumen = new ReporteResumen(repositorioComunidad.recuperarTodos().get(0).getId()
									,repositorioComunidad.recuperarTodos().get(0).getNombre()
									,repositorioZonaReparto.recuperarTodos().size()
									,repositorioPropiedad.recuperarTodos().size()
									,repositorioPropietario.recuperarTodos().size()
									,repositorioGasto.recuperarTodos().size()
									);
	}

	private void generarPropiedades() {
		propiedades = new ArrayList<>();
		for (Propiedad propiedad:repositorioPropiedad.recuperarTodos()){
			propiedades.add(new ReportePropiedad(propiedad));
		}
	}

	private void generarPropietarios() {
		propietarios = new ArrayList<>();
		for (Propietario propietario : repositorioPropietario.recuperarTodos()){
			propietarios.add(new ReportePropietario(propietario));
		}
	}

	private void generarCuotas() {
		List<ReporteCuotaPropiedad> cuotasPropiedad = generarCuotasPropiedad();
		List<ReporteCuotaPropietario> cuotasPropietario = generarCuotasPropietario();
		List<String> idsZonas = generarIdsZonas(); 

		this.cuotas = new ReporteCuota(cuotasPropiedad, cuotasPropietario, idsZonas);
	}
	
	private List<ReporteCuotaPropiedad> generarCuotasPropiedad() {
		List<ReporteCuotaPropiedad> cuotasPropiedad = new ArrayList<>();
		for (Propiedad propiedad : repositorioPropiedad.recuperarTodos()){
			cuotasPropiedad.add(new ReporteCuotaPropiedad(propiedad, repositorioZonaReparto.recuperarTodos()));
		}
		return cuotasPropiedad;
	}
	
	private List<ReporteCuotaPropietario> generarCuotasPropietario() {
		List<ReporteCuotaPropietario> cuotasPropietario = new ArrayList<>();
		for (Propietario propietario : repositorioPropietario.recuperarTodos()){
			cuotasPropietario.add(new ReporteCuotaPropietario(propietario, repositorioZonaReparto.recuperarTodos()));
		}
		return cuotasPropietario;
		
	}
	
	private List<String> generarIdsZonas() {
		List<String> idsZonas = new ArrayList<>();
		for(ZonaReparto zona : repositorioZonaReparto.recuperarTodos()) {
			idsZonas.add(zona.getId());
		}
		 
		return idsZonas;
	}
}
