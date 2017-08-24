package com.mindwaresrl.egpp.v1.core;

import java.util.ArrayList;
import java.util.List;


public class ZonaReparto implements Entidad  {
	
	public static final Integer NRO_CAMPOS = 3;
	public static final Integer INDICE_ID = 0;
	public static final Integer INDICE_NOMBRE = 1;
	public static final Integer INDICE_TIPO_DEPARTO = 2;
	
	public static enum TipoReparto {
		PROPORCIONAL,
		IGUALITARIO;
		
		public static TipoReparto convert(String tipo) {
			TipoReparto tipoReparto = null;
			switch (tipo) {
				case "P":
					tipoReparto = PROPORCIONAL;
					break;
				case "I":
					tipoReparto = IGUALITARIO;				
					break;
				default:
					throw new IllegalStateException("ZonaReparto.TipoReparto valor desconocido: " + tipo);
			}
			
			return tipoReparto;
		}
	}
	
	private String id;
	private String nombre;
	private TipoReparto tipoReparto;
	private List<Gasto> gastos = new ArrayList<>();
	
	public ZonaReparto(String id, String nombre, TipoReparto tipoReparto) {
		this.id = id;
		this.nombre = nombre;
		this.tipoReparto = tipoReparto;
	}
	
	@Override
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public TipoReparto getTipoReparto() {
		return tipoReparto;
	}
	public void setTipoReparto(TipoReparto tipoReparto) {
		this.tipoReparto = tipoReparto;
	}

	public List<Gasto> getGastos() {
		return gastos;
	}

	public void setGastos(List<Gasto> gastos) {
		this.gastos = gastos;
	}
}

