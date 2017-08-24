package com.mindwaresrl.egpp.v1.repo.archivo;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.mindwaresrl.egpp.v1.core.Propietario;

import static com.mindwaresrl.egpp.v1.core.Propietario.*;


public class RepositorioPropietario extends RepositorioAbstracto<Propietario> {

	public RepositorioPropietario(){
		obtenerRegistros();
	}

	@Override
	protected List<String> obtenerLineas(BufferedReader reader) throws IOException {
		List<String> lineasSeccion = new ArrayList<String>();
		
		String linea = null;
		while ( (linea = reader.readLine()) != null ) {
			
		   if (linea.startsWith("#Propietario") ) {
			   while  ( (linea = reader.readLine()) != null ) {
				   if ( StringUtils.isBlank(linea) ) {
					   break;
				   } else if (linea.startsWith(".") ) {
					   continue;
				   }
				   lineasSeccion.add(linea);
			   }
			   break;
		   }
		}
		
		return lineasSeccion;
	}
	//TODO Este método recibe una linea de la seccion #Propietario del archivo comunidad.txt
	//Deberá completarlo para que dada una linea retorne un objeto de la clase Propietario
	//P/Ej: 01;Jorge Salas;Sopocachi (La Paz);jsalas@dominio.com
	@Override
	Propietario convertirRegistro(String registro) {
		
		return new Propietario(" ", " ", " ", " ");
	}
	
	@Override
	protected String getNombreArchivo() {
		return "comunidad.txt";
	}
		
}
