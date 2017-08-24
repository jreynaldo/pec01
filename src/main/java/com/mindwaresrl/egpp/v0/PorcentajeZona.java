package com.mindwaresrl.egpp.v0;

import java.util.StringTokenizer;

public class PorcentajeZona extends Entidad {

	
	public String getIdZona() {
		if (0 == this.idZona.length()) {
			StringTokenizer tokenizer = new StringTokenizer(cadenaDatos, "-");
			this.idZona = tokenizer.nextToken();
		}
		return this.idZona;
	}
	

	public int getPorcentaje() {
		if (-1 == this.porcentaje) {
			StringTokenizer tokenizer = new StringTokenizer(cadenaDatos, "-");
			tokenizer.nextToken();
			this.porcentaje = Integer.parseInt(tokenizer.nextToken()) ;
		}
		return this.porcentaje;
	}
	public void setPorcentaje(int porcentaje) {
		this.porcentaje = porcentaje;
	}


	public void setCadenaDatos(String cadenaDatos) {
		this.cadenaDatos = cadenaDatos;
	}
	
	private String idZona = "";
	private int porcentaje = -1;
	private String cadenaDatos;

}
