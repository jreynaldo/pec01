package com.mindwaresrl.egpp.v1.repo.archivo;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.mindwaresrl.egpp.v1.core.Comunidad;

import static com.mindwaresrl.egpp.v1.core.Comunidad.*;

public class RepositorioComunidad extends RepositorioAbstracto<Comunidad> {

	public RepositorioComunidad(){
		obtenerRegistros();
	}

	@Override
	protected List<String> obtenerLineas(BufferedReader reader) throws IOException {
		List<String> lineasSeccion = new ArrayList<String>();
		
		String linea = null;
		while ( (linea = reader.readLine()) != null ) {
			
		   if (linea.startsWith("#Comunidad") ) {
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
	
	//TODO Este método recibe una linea de la seccion #Comunidad del archivo comunidad.txt 
	//Deberá completarlo para que dada una linea retorne una instancia de la clase Comunidad. 
	//Ej: "01;Trebol;Temporal"
	@Override
	Comunidad convertirRegistro(String registro) {

		return new Comunidad("", "", "");
	}


	@Override
	protected String getNombreArchivo() {
		return "comunidad.txt";
	}
	
}
