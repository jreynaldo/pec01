package com.mindwaresrl.egpp.v0;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.Vector;

public class Gasto extends Entidad {

	public static void GastosFromFile(String nombreFichero, String idZona, Map<String, Gasto> unMap) throws Exception{
		Gasto gasto = null;
        BufferedReader fichero = new BufferedReader(new FileReader(nombreFichero));

		if (null != fichero) {
		   String linea;
		   while ((linea=fichero.readLine()) != null) {
			   gasto = strToGasto(linea, idZona);
			   if (null != gasto) {
				   unMap.put(gasto.getId(),gasto);
			   }
		   }
		}

		fichero.close();
	}


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public double getImporte() {
		return importe;
	}

	public void setImporte(double importe) {
		this.importe = importe;
	}

	public String getIdZona() {
		return idZona;
	}

	public void setIdZona(String idZona) {
		this.idZona = idZona;
	}

	private String id;
	private String descripcion;
	private double importe;
	private String idZona;

	public static Gasto strToGasto(String  linea, String idZonaReparto){
		Gasto result = null;
		if ( (linea.length() == 0) ) return result;
		if ( linea.startsWith("#") || linea.startsWith(".") ) return result;

		result = new Gasto();
		StringTokenizer tokenizer = new StringTokenizer(linea, ";");
		int tokenCount = 0;
		while(tokenizer.hasMoreTokens()) {
			String token = tokenizer.nextToken();
			tokenCount++;
			switch (tokenCount) {
				case 1:
					result.setId(token);
					break;
				case 2:
					result.setDescripcion(token);
					break;
				case 3:
					result.setImporte(Math.ceil(Double.parseDouble(token)));
					break;
				case 4:
					result.setIdZona(token);
					break;
			}
		}

		if (! result.idZona.equals(idZonaReparto)) {
			result = null;
		}
		return result;
	}

}
