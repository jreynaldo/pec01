package com.mindwaresrl.egpp.v0;

import java.util.StringTokenizer;
//import static Constantes.*;

public class Piso extends Propiedad {
	@Override
	public String getInformacionAdicional() {
		String strTipoVivienda = null;

		 if (this.getTipoVivienda().equalsIgnoreCase(Constantes.STR_VIVHABITUAL) ) {
			 strTipoVivienda = "Habitual";
		 }

		 if (this.getTipoVivienda().equalsIgnoreCase(Constantes.STR_VIVNOHABITUAL)  ) {
			 strTipoVivienda = "No Habitual";
		 }

		return String.format("Viv. %s, %d dorm.", strTipoVivienda, this.nroDomitorios);
	}
	
	public static Piso strToPiso(String linea){
		Piso result = null;

		result = new Piso();
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
					result.setTipoVivienda(token);
					break;
				case 7:
					result.setNroDomitorios(Integer.parseInt(token));
					break;
			}
		}

		return result;

		
	}
	
	public String getTipoVivienda() {
		return tipoVivienda;
	}
	public void setTipoVivienda(String tipoVivienda) {
		this.tipoVivienda = tipoVivienda;
	}
	public int getNroDomitorios() {
		return nroDomitorios;
	}
	public void setNroDomitorios(int nroDomitorios) {
		this.nroDomitorios = nroDomitorios;
	}

	private String tipoVivienda;
	private int nroDomitorios;	
}
