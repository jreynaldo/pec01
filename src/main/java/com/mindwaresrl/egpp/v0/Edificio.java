package com.mindwaresrl.egpp.v0;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.TreeMap;
import java.util.SortedMap;
import java.util.StringTokenizer;
import java.util.Vector;

public class Edificio extends Entidad {

	public static Edificio getEdificioFromFile(String nombreFichero) throws Exception{
	   Edificio result = null;
       BufferedReader fichero = new BufferedReader(new FileReader(nombreFichero));

	   if (null != fichero) {
		   String linea;
		   while ( (linea=fichero.readLine()) != null ) {
			   if (linea.equalsIgnoreCase("#Comunidad") ) {
				   while  ( (linea=fichero.readLine()) != null ) {
					   result = strToEdificio(linea);
					   if (null != result) {
						   break;
					   }
				   }
				   break;
			   }
		   }

		   ZonaReparto.getZonasFromFile(fichero, result.zonas);
		   Propiedad.getPropiedadesFromFile(fichero, result.propiedades);
		   Propietario.getPropietariosFromFile(fichero, result.propietarios);
		   result.ajustarPorcentajes();
	   }

	   fichero.close();

	   return result;
	
	}


	public static boolean insert(Entidad entidad, String nombreFichero) throws Exception{
         BufferedReader fichero = new BufferedReader(new FileReader(nombreFichero));
		 boolean result = false;

		 result = entidad.insert(fichero);
		 fichero.close();
		 
		 return result;
	}
	public static boolean remove(Entidad entidad, String nombreFichero) throws Exception{
        BufferedReader fichero = new BufferedReader(new FileReader(nombreFichero));
		 boolean result = false;

		 result = entidad.remove(fichero);
		 fichero.close();
		 
		 return result;
	}
	public static boolean  update(Entidad entidad, String nombreFichero) throws Exception{
        BufferedReader fichero = new BufferedReader(new FileReader(nombreFichero));
		 boolean result = false;

		 result = entidad.update(fichero);
		 fichero.close();
		 
		 return result;
	}

	public void ajustarPorcentajes(){
		for (ZonaReparto zona: zonas.values()){
			   zona.calcularTotalExistentes(this.propiedades.values());
			    if ( TipoReparto.IGUALITARIO == zona.getTipoReparto() ) {
			    	String idZona = zona.getId();
		    		int porcentaje = zona.getPorcentaje();
		    		for (Propiedad propiedad : propiedades.values()){
		    			propiedad.ajustarPorcentajes( idZona , porcentaje );
		    		}
			    }
		}
	}
	
	
	public void generarResumen() throws Exception {
		PrintWriter fichero = new PrintWriter(new FileWriter("/tmp/resumen.txt"));
		
		if (null != fichero) {
			///ENCABEZADO
			fichero.println("ESTADISTICAS: ");
			fichero.println("");

			//CONTENIDO
			fichero.println("Comunidad: " + this.getId() + " " + this.getNombre());
			fichero.println("Numero de Zonas\t\t: " + this.getZonas().size());
			fichero.println("Numero de Propiedades\t: " + this.getPropiedades().size());
			fichero.println("Numero de Propietarios\t: " + this.getPropietarios().size());
			
			int nroGastos = 0;
			for (ZonaReparto zona : zonas.values()) {
				nroGastos += zona.getGastos().size();
			}

			fichero.println("Numero de Gastos\t: " + nroGastos);

			//PIE
		}

		fichero.close();
	}
	
	public void generarPropiedades() throws Exception {
		PrintWriter fichero = new PrintWriter(new FileWriter("/tmp/propiedades.txt"));

		if(null != fichero) {
			///ENCABEZADO
			fichero.print("Cod  " + "m2  " + "C. " + "Nombre Propietario                       ");
			fichero.print("Cuotas         " + "Informacion Adicional                   ");
			fichero.println("");
			
			fichero.print("-----" + "----" + "---" + "-----------------------------------------");
			fichero.print("---------------" + "----------------------------------------");
			fichero.println("");

			//CONTENIDO

			for (Propiedad p : propiedades.values()){
				 Propietario propietario = this.getPropietario(p); ///

				 fichero.printf("%-5s",p.getId()); 

				 fichero.printf("%3d ",p.getArea()); 

				 fichero.printf("%-3s",propietario.getId()); 
			 
				 fichero.printf("%-41s",propietario.getNombre()); 
				 
				 fichero.printf("%-15s",p.getCuotas()); 
				 
				 fichero.printf("%s",p.getInformacionAdicional());
				 fichero.println("");
				 
				 
			}


			//PIE

			fichero.print("-----" + "----" + "---" + "-----------------------------------------");
			fichero.print("---------------" + "----------------------------------------");
			fichero.println("");
			fichero.println(this.propiedades.size() + " propiedades total" );
		}

		fichero.close();
		
	}
	
	public void generarPropietarios() throws Exception{
		PrintWriter fichero = new PrintWriter(new FileWriter("/tmp/propietarios.txt"));

		if(null != fichero) {
			///ENCABEZADO
			fichero.print("C. " + "Nombre Propietario                       ");
			fichero.print("Email                                    ");
			fichero.print("Propiedades         ");
			fichero.println("");

			fichero.print("---" + "-----------------------------------------");
			fichero.print("-----------------------------------------");
			fichero.print("--------------------");
			fichero.println("");

			//CONTENIDO
			for (Propietario p : propietarios.values()){

				 fichero.printf("%-3s",p.getId());
				 

				 fichero.printf("%-41s",p.getNombre());

				 fichero.printf("%-41s",p.getEmail());

				 fichero.printf("%-21s",this.getCodigoPropiedades(p));

				 fichero.println("");
			}


			//PIE
			fichero.print("---" + "-----------------------------------------");
			fichero.print("-----------------------------------------");
			fichero.print("--------------------");
			fichero.println("");
			fichero.println(propietarios.size() + " propietarios total");

		}

		fichero.close();
		
	}
	
	public void generarCuotas() throws Exception{
		PrintWriter fichero = new PrintWriter(new FileWriter("/tmp/vi cuotas.txt"));

		 Vector<TotalGastoZona> totalGastos = new Vector<TotalGastoZona>();
		 for(ZonaReparto z : this.zonas.values()){
			 TotalGastoZona totalGastoZona = new TotalGastoZona();
			 totalGastoZona.idZona = z.getId();
			 totalGastoZona.totalGasto = z.getTotalGasto();
			 totalGastoZona.totalPorcentaje = 0;
			 totalGastoZona.totalGastoPorPorcentaje = 0.0;
			 totalGastos.add(totalGastoZona);
		 }

		generarCuotasPorPropiedades(fichero, totalGastos);

		for (TotalGastoZona g : totalGastos){
			 g.totalPorcentaje = 0;
			 g.totalGastoPorPorcentaje = 0.0;
		}
		generarCuotasPorPropietarios(fichero, totalGastos);

		for (TotalGastoZona g : totalGastos){
			 g = null;
		}
		fichero.close();

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
	public SortedMap<String, ZonaReparto> getZonas() {
		return zonas;
	}
	public void setZonas(SortedMap<String, ZonaReparto> zonas) {
		this.zonas = zonas;
	}
	public SortedMap<String, Propiedad> getPropiedades() {
		return propiedades;
	}
	public void setPropiedades(SortedMap<String, Propiedad> propiedades) {
		this.propiedades = propiedades;
	}
	public SortedMap<String, Propietario> getPropietarios() {
		return propietarios;
	}
	public void setPropietarios(SortedMap<String, Propietario> propietarios) {
		this.propietarios = propietarios;
	}
	
	private Propietario getPropietario(Propiedad propiedad){
		Propietario result = null;

		//Se asume que no hay propiedades compartidas (con mas de un propietario)
		for (Propietario p : propietarios.values()) {
			 if (p.getId().equalsIgnoreCase(propiedad.getIdPropietario()) ) {
				result = p;
				break;
			 }
		}

		return result;
	}
	
	private String  getCodigoPropiedades(Propietario propietario){
		String result = "";
		
		int nroPropiedades = 0;
		for(Propiedad p : propiedades.values()) {
			 if (propietario.getId().equalsIgnoreCase(p.getIdPropietario() ) ) {
				    nroPropiedades++;
				    if (nroPropiedades > 3) break;
				    
					if (nroPropiedades > 1) {
						result += ", ";
					}
					result += p.getId();
				 }
		}
		
		return result;
	}
	
	private int getPorcentaje(Propietario propietario, String idZona){
		int result = 0;

		for (Propiedad p: this.propiedades.values()){
			 if ( propietario.getId().equalsIgnoreCase(p.getIdPropietario()) ) {
				 result += p.getPorcentaje(idZona);
			 }
		}

		return result;
	}
	
	private double getImporte(Propietario propietario,String idZona, double importeTotal){
		double result = 0.0;

		for (Propiedad p: this.propiedades.values()){
			 if (propietario.getId().equalsIgnoreCase(p.getIdPropietario()) ) {
				 result += p.getImporte(idZona,importeTotal);
			 }
		}

		return result;
	}

	private void generarCuotasPorPropiedades(PrintWriter fichero, Vector<TotalGastoZona> totalGastos){
		if(null != fichero) {

			///ENCABEZADO
			fichero.print("CUOTAS POR PROPIEDADES: ");
			fichero.println("");
			fichero.println("");
			fichero.println("");
			

			fichero.print("CPd " + "Nombre Propietario                        ");

			for (TotalGastoZona g: totalGastos){
				fichero.print("%" + g.idZona + "  ");
			}
			
			for (TotalGastoZona g: totalGastos){
				fichero.print("       " + g.idZona + ". ");
			}
			
			fichero.print("     Total");
			fichero.println("");

			fichero.print("---" + "------------------------------------------");
			for (TotalGastoZona g: totalGastos){
				 fichero.print("----") ;
			}
			
			for (TotalGastoZona g: totalGastos){
				 fichero.print( "----------") ;
			}
			
			fichero.print( "----------") ;
			fichero.println( "") ;

			//CONTENIDO

			double totalPropiedades = 0;
			for(Propiedad p: this.propiedades.values()) {
				 Propietario propietario = this.getPropietario(p);

  				 fichero.printf( "%-4s",p.getId()) ;

  				 fichero.printf( "%-41s",propietario.getNombre()) ;

				for (TotalGastoZona g: totalGastos){
					 int porcentaje = p.getPorcentaje(g.idZona);
					 g.totalPorcentaje +=porcentaje;
					 fichero.printf("%3d ",porcentaje);
				}


	      		double totalPropiedad = 0.0;
				//Imprimir Importes
	 			for (TotalGastoZona g: totalGastos){
	 				 double importe = p.getImporte(g.idZona, g.totalGasto);
					 g.totalGastoPorPorcentaje += importe;
	 				 totalPropiedad += importe;
	 				 fichero.printf("%10.2f", importe);
	 			}
     			fichero.printf("%10.2f", totalPropiedad);
     			fichero.println("");

	   		    totalPropiedades += totalPropiedad;
			}


			//PIE
			fichero.print("---" + "------------------------------------------");
			for (TotalGastoZona g: totalGastos){
				 fichero.print("----") ;
			}
			
			for (TotalGastoZona g: totalGastos){
				 fichero.print( "----------") ;
			}
			
			fichero.print( "----------") ;
			fichero.println( "") ;

			
			fichero.printf("%-3d propiedades                             ",propiedades.size());

			for(TotalGastoZona g: totalGastos){
				 fichero.printf("%3d ", g.totalPorcentaje);
			}

			for (TotalGastoZona g: totalGastos){
				 fichero.printf("%10.2f", g.totalGastoPorPorcentaje);
			}

			fichero.printf("%10.2f", totalPropiedades);
			fichero.println("");
		}
		
	}
	
	private void generarCuotasPorPropietarios(PrintWriter fichero, Vector<TotalGastoZona> totalGastos){
		if(null != fichero) {
			///ENCABEZADO
			fichero.println(""); fichero.println(""); fichero.println("");
			fichero.print("CUOTAS POR PROPIETARIO: ");
			fichero.println(""); fichero.println(""); fichero.println("");
			
			fichero.print("CP " + "Nombre Propietario                        ");
			
			for (TotalGastoZona g: totalGastos){
				 fichero.print("%" + g.idZona + "  ");
			}

			for (TotalGastoZona g: totalGastos){
				 fichero.print("       " + g.idZona + ". ");
			}
			
 		    fichero.print("     Total");
			fichero.println("");

			fichero.print("---" + "------------------------------------------");
			for (TotalGastoZona g: totalGastos ){
				fichero.print("----");
			}
			for (TotalGastoZona g: totalGastos ){
				fichero.print("----------");
			}
			
			fichero.print("----------");
			fichero.println("");

			//CONTENIDO

			double totalPropiedades = 0;
			for (Propietario p: this.propietarios.values()){

				 fichero.printf("%-3s", p.getId());
				 
				 fichero.printf("%-41s", p.getNombre());

				for (TotalGastoZona g: totalGastos){
					 int porcentaje = this.getPorcentaje(p,g.idZona);
					 g.totalPorcentaje +=porcentaje;
					 fichero.printf("%3d ", porcentaje);
				}


	      		float totalPropiedad = 0;
				//Imprimir Importes
	 			for (TotalGastoZona g: totalGastos){
	 				 double importe = this.getImporte(p,g.idZona, g.totalGasto);
					 g.totalGastoPorPorcentaje += importe;
	 				 totalPropiedad += importe;
					 fichero.printf("%10.2f", importe);
	 			}
				fichero.printf("%10.2f", totalPropiedad);
				fichero.println("");

	   		    totalPropiedades += totalPropiedad;
			}


			//PIE
			fichero.print("---" + "------------------------------------------");
			for (TotalGastoZona g: totalGastos ){
				fichero.print("----");
			}
			for (TotalGastoZona g: totalGastos ){
				fichero.print("----------");
			}
			
			fichero.print("----------");
			fichero.println("");

			fichero.printf("%-2d propietarios                             ", propietarios.size());

			for (TotalGastoZona g: totalGastos){
				 fichero.printf("%3d ", g.totalPorcentaje);
			}

			for (TotalGastoZona g: totalGastos){
				 fichero.printf("%10.2f", g.totalGastoPorPorcentaje);
			}
  
			fichero.printf("%10.2f", totalPropiedades);
			fichero.println("");
		}
		
	}

	public static Edificio strToEdificio(String linea){
		Edificio result = null;
		if ( (linea.length() == 0) || (linea.startsWith(".")) ) return result;

		result = new Edificio();
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
			}
		}

		return result;
	}
	
	private String id;
	private String nombre;
	private String poblacion;

	private SortedMap<String, ZonaReparto> zonas = new TreeMap<String, ZonaReparto>();
	private SortedMap<String, Propiedad> propiedades = new TreeMap<String, Propiedad>();
	private SortedMap<String, Propietario> propietarios = new TreeMap<String, Propietario>();
}

class TotalGastoZona{
	String idZona;
	double totalGasto;
	int totalPorcentaje;
	double totalGastoPorPorcentaje;
};


