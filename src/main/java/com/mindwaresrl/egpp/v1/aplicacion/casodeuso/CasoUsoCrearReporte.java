package com.mindwaresrl.egpp.v1.aplicacion.casodeuso;

import java.util.List;

import com.mindwaresrl.egpp.v1.aplicacion.casodeuso.dto.ReporteCuota;
import com.mindwaresrl.egpp.v1.aplicacion.casodeuso.dto.ReportePropiedad;
import com.mindwaresrl.egpp.v1.aplicacion.casodeuso.dto.ReportePropietario;
import com.mindwaresrl.egpp.v1.aplicacion.casodeuso.dto.ReporteResumen;

public interface CasoUsoCrearReporte {
	public void ejecutar();
	public ReporteResumen getResumen();
	public List<ReportePropiedad> getPropiedades();
	public List<ReportePropietario> getPropietarios();
	public ReporteCuota getCuotas();
}
