package com.mindwaresrl.egpp.v1.aplicacion.casodeuso.dto;

import java.util.List;

public class ReporteCuota {

	private List<ReporteCuotaPropiedad> cuotasPropiedad;
	private List<ReporteCuotaPropietario> cuotasPropietario;
	private List<String> idsZonas;
	
	
	public ReporteCuota(List<ReporteCuotaPropiedad> cuotasPropiedad
						, List<ReporteCuotaPropietario> cuotasPropietario
						, List<String> idsZonas
						) {
		this.cuotasPropiedad = cuotasPropiedad;
		this.cuotasPropietario = cuotasPropietario;
		this.idsZonas = idsZonas;
	}
	
	public List<ReporteCuotaPropiedad> getCuotasPropiedad() {
		return cuotasPropiedad;
	}

	public List<ReporteCuotaPropietario> getCuotasPropietario() {
		return cuotasPropietario;
	}

	public List<String> getIdsZonas() {
		return idsZonas;
	}
	
	
}
