package com.mindwaresrl.egpp.v0;

import java.io.BufferedReader;
import java.util.Collection;
import java.util.Map;
import java.util.SortedMap;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class ZonaReparto extends Entidad {

	public static void getZonasFromFile(BufferedReader fichero, Map<String,ZonaReparto> unMapa) throws Exception{
	   ZonaReparto zona = null;

	   String linea;
	   while ((linea=fichero.readLine()) != null) {
		   if (linea.equalsIgnoreCase("#Zona") ) {
			   while ((linea=fichero.readLine()) != null) {
				   zona = strToZona(linea);
				   if (null != zona) {
					   Gasto.GastosFromFile("gastos.txt", zona.getId(), zona.gastos );
					   unMapa.put(zona.getId(),zona);
				   }
				   if ( linea.length() == 0) {
					   break;
				   }
			   }
			   break;
		   }
	   }

	}

	public void calcularTotalExistentes(Collection<Propiedad> propiedades){
		this.totalExistentes = 0;
		for (Propiedad propiedad: propiedades){
			this.totalExistentes += propiedad.tienePorcentajeZona(this.getId()) ? 1 : 0;
		}
	}
	
	public int getPorcentaje(){
		return (int) Math.ceil(100.0 / this.totalExistentes);
	}
	
	public double getTotalGasto(){
		double result = 0.0;

		 for (Gasto g: this.gastos.values()){
			 result += g.getImporte();
		 }

		return result;
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

	public TipoReparto getTipoReparto() {
		return tipoReparto;
	}

	public void setTipoReparto(TipoReparto tipoReparto) {
		this.tipoReparto = tipoReparto;
	}

	public int getTotalExistentes() {
		return totalExistentes;
	}

	public void setTotalExistentes(int totalExistentes) {
		this.totalExistentes = totalExistentes;
	}

	public Map<String,Gasto> getGastos() {
		return gastos;
	}

	public void setGastos(SortedMap<String, Gasto> gastos) {
		this.gastos = gastos;
	}

	
	public static ZonaReparto strToZona(String linea){
		ZonaReparto result = null;
		if ( linea.length() == 0 ) return result;
		if ( linea.startsWith("#") || linea.startsWith(".") ) return result;

		result = new ZonaReparto();
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
					result.setTipoReparto(strToTipoReparto(token));
					break;
			}
		}

		return result;

	}
	
	public static TipoReparto strToTipoReparto(String str){
		TipoReparto result = null;

		if (str.equalsIgnoreCase("P")) result = TipoReparto.PROPORCIONAL;
		if (str.equalsIgnoreCase("I")) result = TipoReparto.IGUALITARIO;

		return result;
	}
	
	
	private String id;
	private String nombre;
	private TipoReparto tipoReparto;
	private int totalExistentes;

	private SortedMap<String,Gasto> gastos = new TreeMap<String, Gasto>();	
}


enum TipoReparto {
	PROPORCIONAL,
	IGUALITARIO
}