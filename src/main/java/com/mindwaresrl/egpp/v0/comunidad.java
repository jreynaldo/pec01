package com.mindwaresrl.egpp.v0;


public class comunidad {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {

		Edificio edificio = Edificio.getEdificioFromFile("/tmp/comunidad.txt");
			System.out.println("hola");
		edificio.generarResumen();
		edificio.generarPropiedades();
		edificio.generarPropietarios();
		edificio.generarCuotas();

		}
		catch (Exception e) {
			System.out.println("Exception: " + e);
			e.printStackTrace();				
		}
		
}

}
