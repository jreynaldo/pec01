package com.mindwaresrl.egpp.v0;

import java.util.StringTokenizer;

public class PlazaGaraje extends Propiedad {
	@Override
	public String getInformacionAdicional() {
		String strTipoGaraje = null;
		String strTrastero = null;

		 if ( this.getTipoGaraje().equalsIgnoreCase(Constantes.STR_GRJABIERTA)  ) {
			 strTipoGaraje="Abierta";
		 }

		 if (this.getTipoGaraje().equalsIgnoreCase(Constantes.STR_GRJCERRADA)  ) {
			 strTipoGaraje = "Cerrada";
		 }

		 if (this.getConTrastero().equalsIgnoreCase(Constantes.STR_CONTRASTERO)  ) {
			 strTrastero = "Con Depósito";
		 }

		 if (this.getConTrastero().equalsIgnoreCase(Constantes.STR_SINTRASTERO)  ) {
			 strTrastero = "Sin Depósito";
		 }


		return String.format("%s, %s", strTipoGaraje, strTrastero);

	}
	
	public static PlazaGaraje strToGaraje(String linea){
		PlazaGaraje result = null;

		result = new PlazaGaraje();
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
					result.setTipoGaraje(token);
					break;
				case 7:
					result.setConTrastero(token);
					break;
			}
		}

		return result;
		
	}
	
	public String getTipoGaraje() {
		return tipoGaraje;
	}
	public void setTipoGaraje(String tipoGaraje) {
		this.tipoGaraje = tipoGaraje;
	}
	public String getConTrastero() {
		return conTrastero;
	}
	public void setConTrastero(String conTrastero) {
		this.conTrastero = conTrastero;
	}

	private String tipoGaraje;
	private String conTrastero;
	
}
