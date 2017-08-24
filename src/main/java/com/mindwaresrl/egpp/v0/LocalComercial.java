package com.mindwaresrl.egpp.v0;

import java.util.StringTokenizer;

public class LocalComercial extends Propiedad {
	
	@Override
	public String getInformacionAdicional() {
		return String.format("%s,%s", this.getNombre(), this.getActividad());
	}
	

	public static LocalComercial strToLocal(String linea){
		LocalComercial result = null;

		result = new LocalComercial();
		StringTokenizer tokenizer = new StringTokenizer(linea, ";");
		int tokenCount = 0;
		while(tokenizer.hasMoreTokens()) {
			String token = tokenizer.nextToken();
			tokenCount++;
			switch (tokenCount) {
				case 1:
					result.setTipo(token);
					break;
				case 2:
					result.setId(token);
					break;
				case 3:
					result.setArea(Integer.parseInt(token));
					break;
				case 4:
					result.setIdPropietario(token);
					break;
				case 5:
					result.setStrPorcentajesZonas(token);
					break;
				case 6:
					result.setNombre(token);
					break;
				case 7:
					result.setActividad(token);
					break;
			}
		}

		return result;

	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getActividad() {
		return actividad;
	}
	public void setActividad(String actividad) {
		this.actividad = actividad;
	}

	private String nombre;
	private String actividad;
	
}
