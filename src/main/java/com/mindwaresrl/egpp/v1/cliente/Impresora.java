package com.mindwaresrl.egpp.v1.cliente;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mindwaresrl.egpp.v0.Propietario;
import com.mindwaresrl.egpp.v1.aplicacion.casodeuso.dto.ReporteCuota;
import com.mindwaresrl.egpp.v1.aplicacion.casodeuso.dto.ReporteCuotaPropiedad;
import com.mindwaresrl.egpp.v1.aplicacion.casodeuso.dto.ReporteCuotaPropietario;
import com.mindwaresrl.egpp.v1.aplicacion.casodeuso.dto.ReportePropiedad;
import com.mindwaresrl.egpp.v1.aplicacion.casodeuso.dto.ReportePropietario;
import com.mindwaresrl.egpp.v1.aplicacion.casodeuso.dto.ReporteResumen;

public class Impresora {
	public static final String ARCHIVO_RESUMEN = "resumen-v1.txt";
	public static final String ARCHIVO_PROPIEDADES = "propiedades-v1.txt";
	public static final String ARCHIVO_PROPIETARIOS = "propietarios-v1.txt";
	public static final String ARCHIVO_CUOTAS = "cuotas-v1.txt";
	
	public void imprimirResumen(ReporteResumen reporteResumen) {
		try (PrintWriter fichero = new PrintWriter(new FileWriter(ARCHIVO_RESUMEN)) ){
			if (null != fichero) {
				///ENCABEZADO
				fichero.println("ESTADISTICAS: ");
				fichero.println("");

				//CONTENIDO
				fichero.println("Comunidad: " + reporteResumen.getIdComunidad() + " " + reporteResumen.getNombreComunidad());
				fichero.println("Numero de Zonas\t\t: " + reporteResumen.getConteoZonas());
				fichero.println("Numero de Propiedades\t: " + reporteResumen.getConteoPropiedades());
				fichero.println("Numero de Propietarios\t: " + reporteResumen.getConteoPropietarios());
				fichero.println("Numero de Gastos\t: " + reporteResumen.getConteoGastos());

				//PIE
			}
		} catch (IOException e) {

		}

	}
	
	public void imprimirPropiedades(List<ReportePropiedad> propiedades) {
		try (PrintWriter fichero = new PrintWriter(new FileWriter(ARCHIVO_PROPIEDADES)) )  {
			if(null != fichero) {
				///ENCABEZADO
				fichero.print("Cod  " + "m2  " + "C. " + "Nombre Propietario                       ");
				fichero.print("Cuotas         " + "Informacion Adicional                   ");
				fichero.println("");
				
				fichero.print("-----" + "----" + "---" + "-----------------------------------------");
				fichero.print("---------------" + "----------------------------------------");
				fichero.println("");

				//CONTENIDO
				for (ReportePropiedad p : propiedades){

					 fichero.printf("%-5s",p.getIdPropiedad()); 
					 fichero.printf("%3d ",p.getMetrosCuadrados().intValue()); 
					 fichero.printf("%-3s",p.getIdPropietario()); 
					 fichero.printf("%-41s",p.getNombrePropietario()); 
					 fichero.printf("%-15s",obtenerCuotas(p.getPorcentajesPorIdZona())); 
					 fichero.printf("%s",p.getInformacionAdicional());

					 fichero.println("");
				}


				//PIE
				fichero.print("-----" + "----" + "---" + "-----------------------------------------");
				fichero.print("---------------" + "----------------------------------------");
				fichero.println("");
				fichero.println(propiedades.size() + " propiedades total" );
			}			
		} catch (IOException e) {

		}
		
	}

	public void imprimirPropietarios(List<ReportePropietario> propietarios) {
		try (PrintWriter fichero = new PrintWriter(new FileWriter(ARCHIVO_PROPIETARIOS)) ){
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
				for (ReportePropietario p : propietarios){

					 fichero.printf("%-3s",p.getIdPropietario());
					 

					 fichero.printf("%-41s",p.getNombrePropietario());

					 fichero.printf("%-41s",p.getEmail());

					 fichero.printf("%-21s",obtenerPropiedades(p.getIdsPropiedades()));

					 fichero.println("");
				}


				//PIE
				fichero.print("---" + "-----------------------------------------");
				fichero.print("-----------------------------------------");
				fichero.print("--------------------");
				fichero.println("");
				fichero.println(propietarios.size() + " propietarios total");
			}
			
		} catch (IOException e) {

		}
	}
	
	public void imprimirCuotas(ReporteCuota reporteCuota ) {
		
		try( PrintWriter fichero = new PrintWriter(new FileWriter(ARCHIVO_CUOTAS)) ){
			imprimirCuotasPorPropiedades(fichero, reporteCuota);
			imprimirCuotasPorPropietarios(fichero, reporteCuota);
		} catch (IOException e) {

		}
	}
	
	private void imprimirCuotasPorPropiedades(PrintWriter fichero, ReporteCuota reporteCuota){
		///ENCABEZADO
		fichero.print("CUOTAS POR PROPIEDADES: ");
		fichero.println("");
		fichero.println("");
		fichero.println("");
		

		fichero.print("CPd " + "Nombre Propietario                        ");

		for ( String idZona: reporteCuota.getIdsZonas() ){
			fichero.print("%" + idZona + "  ");
		}
		
		for (String idZona: reporteCuota.getIdsZonas()){
			fichero.print("       " + idZona + ". ");
		}
		
		fichero.print("     Total");
		fichero.println("");

		fichero.print("---" + "------------------------------------------");
		for (String idZona: reporteCuota.getIdsZonas()){
			 fichero.print("----") ;
		}
		
		for (String idZona: reporteCuota.getIdsZonas()){
			 fichero.print( "----------") ;
		}
		
		fichero.print( "----------") ;
		fichero.println( "") ;

		//CONTENIDO

		double totalImporte = 0.0;
		Map<String, Double> totalPorcentajes = new HashMap<>();
		Map<String, Double> totalImportes = new HashMap<>();
		
		for(ReporteCuotaPropiedad p: reporteCuota.getCuotasPropiedad() ) {

			fichero.printf( "%-4s",p.getIdPropiedad()) ;
			fichero.printf( "%-41s",p.getNombrePropietario()) ;

			//Imprimir porcentajes
			for (String idZona: reporteCuota.getIdsZonas()){
				 int porcentaje = p.getPorcentajesPorIdZona().get(idZona).intValue();
				 fichero.printf("%3d ",porcentaje);
				 if (null == totalPorcentajes.get(idZona)) {
					 totalPorcentajes.put(idZona, 0.0);
				 }
				 totalPorcentajes.put(idZona, totalPorcentajes.get(idZona) + porcentaje);
			}


			//Imprimir Importes
 			for (String idZona: reporteCuota.getIdsZonas()){
 				 double importe = p.getImportesPorIdZona().get(idZona);
 				 fichero.printf("%10.2f", importe);
				 if (null == totalImportes.get(idZona)) {
					 totalImportes.put(idZona, 0.0);
				 }
				 totalImportes.put(idZona, totalImportes.get(idZona) + importe);
 			}
 			fichero.printf("%10.2f", p.getImporteTotal());
 			fichero.println("");

 			totalImporte += p.getImporteTotal();
		}


		//PIE
		fichero.print("---" + "------------------------------------------");
		for (String idZona: reporteCuota.getIdsZonas()){
			 fichero.print("----") ;
		}
		
		for (String idZona: reporteCuota.getIdsZonas()){
			 fichero.print( "----------") ;
		}
		
		fichero.print( "----------") ;
		fichero.println( "") ;

		
		fichero.printf("%-3d propiedades                              ",reporteCuota.getCuotasPropiedad().size());

		for(String idZona: reporteCuota.getIdsZonas()){
			 fichero.printf("%3d ", totalPorcentajes.get(idZona).intValue());
		}

		for (String idZona: reporteCuota.getIdsZonas()){
			 fichero.printf("%10.2f", totalImportes.get(idZona));
		}

		fichero.printf("%10.2f", totalImporte);
		fichero.println("");		
	}


	private void imprimirCuotasPorPropietarios(PrintWriter fichero, ReporteCuota reporteCuota){
		///ENCABEZADO
		fichero.println(""); fichero.println(""); fichero.println("");
		fichero.print("CUOTAS POR PROPIETARIOS: ");
		fichero.println("");
		fichero.println("");
		fichero.println("");
		

		fichero.print("CPd " + "Nombre Propietario                        ");

		for ( String idZona: reporteCuota.getIdsZonas() ){
			fichero.print("%" + idZona + "  ");
		}
		
		for (String idZona: reporteCuota.getIdsZonas()){
			fichero.print("       " + idZona + ". ");
		}
		
		fichero.print("     Total");
		fichero.println("");

		fichero.print("---" + "------------------------------------------");
		for (String idZona: reporteCuota.getIdsZonas()){
			 fichero.print("----") ;
		}
		
		for (String idZona: reporteCuota.getIdsZonas()){
			 fichero.print( "----------") ;
		}
		
		fichero.print( "----------") ;
		fichero.println( "") ;

		//CONTENIDO

		double totalImporte = 0.0;
		Map<String, Double> totalPorcentajes = new HashMap<>();
		Map<String, Double> totalImportes = new HashMap<>();
		
		for(ReporteCuotaPropietario p: reporteCuota.getCuotasPropietario() ) {

			fichero.printf( "%-4s",p.getIdPropietario()) ;
			fichero.printf( "%-41s",p.getNombrePropietario()) ;

			//Imprimir porcentajes
			for (String idZona: reporteCuota.getIdsZonas()){
				 int porcentaje = p.getPorcentajesPorIdZona().get(idZona).intValue();
				 fichero.printf("%3d ",porcentaje);
				 if (null == totalPorcentajes.get(idZona)) {
					 totalPorcentajes.put(idZona, 0.0);
				 }
				 totalPorcentajes.put(idZona, totalPorcentajes.get(idZona) + porcentaje);
			}


			//Imprimir Importes
 			for (String idZona: reporteCuota.getIdsZonas()){
 				 double importe = p.getImportesPorIdZona().get(idZona);
 				 fichero.printf("%10.2f", importe);
				 if (null == totalImportes.get(idZona)) {
					 totalImportes.put(idZona, 0.0);
				 }
				 totalImportes.put(idZona, totalImportes.get(idZona) + importe);
 			}
 			fichero.printf("%10.2f", p.getImporteTotal());
 			fichero.println("");

 			totalImporte += p.getImporteTotal();
		}


		//PIE
		fichero.print("---" + "------------------------------------------");
		for (String idZona: reporteCuota.getIdsZonas()){
			 fichero.print("----") ;
		}
		
		for (String idZona: reporteCuota.getIdsZonas()){
			 fichero.print( "----------") ;
		}
		
		fichero.print( "----------") ;
		fichero.println( "") ;

		
		fichero.printf("%-3d propietarios                             ",reporteCuota.getCuotasPropietario().size());

		for(String idZona: reporteCuota.getIdsZonas()){
			 fichero.printf("%3d ", totalPorcentajes.get(idZona).intValue());
		}

		for (String idZona: reporteCuota.getIdsZonas()){
			 fichero.printf("%10.2f", totalImportes.get(idZona));
		}

		fichero.printf("%10.2f", totalImporte);
		fichero.println("");		
		
	}	
	
	private String obtenerCuotas(Map<String, Double> porcentajesPorIdZona){
		String result = "";

		int nroZonas = 0;
		for (Map.Entry<String, Double> porcentajePorIdZona : porcentajesPorIdZona.entrySet() )	{
			if ( porcentajePorIdZona.getValue() <= 0.0)  continue;
			nroZonas++;
			if (nroZonas > 1) {
				result += ",";
			}
			result += String.format("%d%%%s", porcentajePorIdZona.getValue().intValue(), porcentajePorIdZona.getKey() );
		}
		
		return result;
	}
	
	private String  obtenerPropiedades(List<String> idsPropiedades){
		String result = "";
		
		int nroPropiedades = 0;
		for(String p : idsPropiedades) {
		    nroPropiedades++;
		    if (nroPropiedades > 3) break;
		    
			if (nroPropiedades > 1) {
				result += ", ";
			}
			result += p;
		}
		
		return result;
	}	
	
}
