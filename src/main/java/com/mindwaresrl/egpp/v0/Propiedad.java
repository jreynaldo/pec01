package com.mindwaresrl.egpp.v0;

import java.io.BufferedReader;
import java.util.TreeMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.StringTokenizer;
import java.util.Vector;

public abstract class Propiedad extends Entidad {

	public static void getPropiedadesFromFile(BufferedReader fichero, Map<String, Propiedad> unMap) throws Exception{
		   Propiedad propiedad = null;

		   String linea;
		   while ((linea=fichero.readLine()) != null) {
			   if (linea.equalsIgnoreCase("#Propiedad") ) {
				   while ((linea=fichero.readLine()) != null) {
					   propiedad = Propiedad.strToPropiedad(linea);
					   if (null != propiedad) {
						   unMap.put(propiedad.getId(),propiedad);
					   }
					   if ( linea.length() == 0 ) {
						   break;
					   }
				   }
				   break;
			   }
		   }
	}
	
	public String getCuotas(){
		String result = "";

		Map<String, PorcentajeZona> porcentajesZonas = getPorcentajesZonas();
		int nroZonas = 0;
		for (PorcentajeZona p : porcentajesZonas.values() )	{
			nroZonas++;
			if (nroZonas>1) {
				result += ",";
			}
			result += String.format("%d%%%s",p.getPorcentaje(),p.getIdZona());
		}
		
		return result;
	}
	
	public abstract String getInformacionAdicional();
	
	public boolean tienePorcentajeZona(String idZona){
		boolean result = false;

		Map<String,PorcentajeZona> porcentajesZonas = getPorcentajesZonas();
		result = (null != porcentajesZonas.get(idZona));

/*
		for (PorcentajeZona p: porcentajesZonas){
			if ( p.getIdZona().equalsIgnoreCase(idZona)  ) {
				result = true;
				break;
			}
		}
*/
		return result;
	}
	
	public void ajustarPorcentajes(String idZona, int porcentaje ){
		
		Map<String, PorcentajeZona> porcentajesZonas = getPorcentajesZonas();
		PorcentajeZona p = porcentajesZonas.get(idZona); 
		if ( null != p ) {
			p.setPorcentaje(porcentaje);
		}
		/*
		for (PorcentajeZona p: porcentajesZonas) {
			if ( p.getIdZona().equalsIgnoreCase(idZona) ) {
				p.setPorcentaje(porcentaje);
				break;
			}
		}
		*/
		
	}


	public int getPorcentaje(String idZona){
		int result = 0;
		Map<String, PorcentajeZona> porcentajesZonas = getPorcentajesZonas();
		PorcentajeZona p = porcentajesZonas.get(idZona); 
		if ( null != p ) {
			result = p.getPorcentaje();
		}
/*
		for (PorcentajeZona p: porcentajesZonas){
			if ( p.getIdZona().equalsIgnoreCase(idZona) ) {
				result = p.getPorcentaje();
				break;
			}
		}
*/
		return result;
	}
	
	public double getImporte(String idZona, double importeTotal){
		double result = 0;
		Map<String, PorcentajeZona> porcentajesZonas = getPorcentajesZonas();
		PorcentajeZona p = porcentajesZonas.get(idZona); 
		if ( null != p ) {
			result = importeTotal * (p.getPorcentaje()/100.0);
		}
/*		
		
		for (PorcentajeZona p: porcentajesZonas){

			if ( p.getIdZona().equalsIgnoreCase(idZona)  ) {
				result = importeTotal * (p.getPorcentaje()/100.0);
				break;
			}
		}
*/
		return Math.ceil(result);
	}
	
	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getArea() {
		return area;
	}

	public void setArea(int area) {
		this.area = area;
	}

	public String getIdPropietario() {
		return idPropietario;
	}

	public void setIdPropietario(String idPropietario) {
		this.idPropietario = idPropietario;
	}

	public String getStrPorcentajesZonas() {
		return strPorcentajesZonas;
	}

	public void setStrPorcentajesZonas(String strPorcentajesZonas) {
		this.strPorcentajesZonas = strPorcentajesZonas;
	}

	public Map<String,PorcentajeZona> getPorcentajesZonas() {
		if (this.porcentajesZonas.size() ==0) {
			StringTokenizer tokenizer = new StringTokenizer( getStrPorcentajesZonas(), ",");
			while(tokenizer.hasMoreTokens()) {
				PorcentajeZona porcentajeZona = new PorcentajeZona();
				porcentajeZona.setCadenaDatos(tokenizer.nextToken());
				this.porcentajesZonas.put(porcentajeZona.getIdZona(), porcentajeZona );
			}
		}

		return this.porcentajesZonas;
	}


	private String tipo;
	private String id;
	private int area;
	private String idPropietario;
	private String strPorcentajesZonas;

	private SortedMap<String, PorcentajeZona> porcentajesZonas = new TreeMap<String, PorcentajeZona>();

	public static Propiedad strToPropiedad(String linea){
		Propiedad result = null;
		if ( (linea.length() == 0) ) return result;
		
		if ( linea.startsWith("#") || linea.startsWith(".") ) return result;

		if ( linea.startsWith("L") ){
			result = LocalComercial.strToLocal(linea);
		}
		if ( linea.startsWith("P") ) {
			result = Piso.strToPiso(linea);
		}
		if ( linea.startsWith("G") ){
			result = PlazaGaraje.strToGaraje(linea);
		}

		return result;
	}
}
