package practica0;

import practica0.Codificacion;
import practica0.ISBN;
import practica0.NIF;
import practica0.UPC;

/**
 * 
 * @author Administrador
 */
public class Main {

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String[] args) {

		String codigoISBN = "0-444-85193-3"; // "84-206-8186-5";
		String codigoUPC = "9780444485193"; // "039400014416";
		String codigoDNI = "55555555";
		String codigoISBN13 = "978-84-473-5602-"; // ;

		/*****************
         * 
         */
		Codificacion cod = new UPC();
		System.out.println(cod.verificar(codigoUPC) ? "válido" : "No válido");

		/**********************
         * 
         */
		cod = new ISBN();
		System.out.println(cod.generarCodigoControl(codigoISBN));
		System.out
				.println(cod.verificar(codigoISBN) ? "válido" : "No válido");
		
		System.out.println(cod.corregirDatos(codigoISBN));

		/*********************************
         * 
         */
		cod = new NIF();
		System.out.println(cod.generarCodigoControl(codigoDNI));
		System.out.println(cod.verificar(codigoDNI + "K") ? "válido"
				: "No válido");

		/*********************************
         *
         */
		cod = new ISBN13();
		System.out.println(cod.generarCodigoControl(codigoISBN13));
		System.out.println(cod.verificar(codigoISBN13 + "7") ? "válido"
				: "No válido");
		
		cod = new CCC();
		String codCuenta = "854195620010028945";
		System.out.println(cod.generarCodigoControl(codCuenta));
		System.out.println(cod.verificar("85419562580010028945"));
		
		cod = new ISBN13();
		String cod2 = "978-83-8181-227-";
		System.out.println(cod.generarCodigoControl(cod2));
		System.out.println(cod.verificar(cod2 + "5"));
		
	}

}
