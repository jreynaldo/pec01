package com.mindwaresrl.egpp.v1.core;

import java.util.Map;
public class Departamento extends Propiedad {
	
	public static final Integer NRO_CAMPOS = 7;	
	public static final Integer INDICE_TIPO_VIVIENDA = 5;
	public static final Integer INDICE_NRO_DORMITORIOS= 6;
	
	public static enum TipoVivienda {
		HABITUAL,
		NO_HABITUAL;
		
		public static TipoVivienda convert(String tipo) {
			TipoVivienda tipoVivienda = null;
			switch (tipo) {
				case "VH":
					tipoVivienda = HABITUAL;
					break;
				case "VNH":
					tipoVivienda = NO_HABITUAL;				
					break;
				default:
					throw new IllegalStateException("Departamento.TipoVivienda valor desconocido: " + tipo);
			}
			
			return tipoVivienda;
		}		
	}
	
	private TipoVivienda tipoVivienda;
	private Integer numeroDomitorios;
	
	public Departamento(String id, Double metrosCuadrados, Propietario propietario, Map<ZonaReparto, Double> porcentajePorZona
						,TipoVivienda tipoVivienda, Integer numeroDomitorios){
		
		super(id, metrosCuadrados,propietario, porcentajePorZona);
		this.tipoVivienda = tipoVivienda;
		this.numeroDomitorios = numeroDomitorios;
	}	
	
	public TipoVivienda getTipoVivienda() {
		return tipoVivienda;
	}
	public void setTipoVivienda(TipoVivienda tipoVivienda) {
		this.tipoVivienda = tipoVivienda;
	}
	
	public Integer getNumeroDomitorios() {
		return numeroDomitorios;
	}
	public void setNumeroDomitorios(Integer numeroDomitorios) {
		this.numeroDomitorios = numeroDomitorios;
	}
}
