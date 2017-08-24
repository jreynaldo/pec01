package com.mindwaresrl.egpp.v0;

import java.io.BufferedReader;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.Vector;

public class Propietario extends Entidad {

	public static void getPropietariosFromFile(BufferedReader fichero, Map<String, Propietario> unMap) throws Exception {
		Propietario propietario = null;

		String linea;
		while ((linea=fichero.readLine()) != null) {
		   if (linea.equalsIgnoreCase("#Propietario") ) {
			   while ((linea=fichero.readLine()) != null) {
				   propietario = strToPropietario(linea);
				   if (null != propietario) {
					   unMap.put(propietario.getId(),propietario);
				   }

				   if ( linea.length() == 0) {
					   break;
				   }
			   }
			   break;
		   }
		}
	}
	
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

	public String getPoblacion() {
		return poblacion;
	}

	public void setPoblacion(String poblacion) {
		this.poblacion = poblacion;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	private String id;
	private String nombre;
	private String poblacion;
	private String email;

	public static Propietario strToPropietario(String linea){
		Propietario result = null;
		if ( linea.length() == 0 ) return result;
		if (  linea.startsWith("#") || linea.startsWith(".") ) return result;

		result = new Propietario();
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
					result.setNombre(token);
					break;
				case 3:
					result.setPoblacion(token);
					break;
				case 4:
					result.setEmail(token);
					break;
			}
		}

		return result;

	}
	
}
