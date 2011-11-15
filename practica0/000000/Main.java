/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package practica0;

/**
 *
 * @author Administrador
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    	
        String codigoISBN = "9-444-85193-3"; // "84-206-8186-5"
        String codigoUPC = "278044448510"; // "539493013002"
        String codigoDNI = "55555555L";
        String codigoISBN13 = "878-84-473-5602-7"; // "079-04-473-5994-9"

        /*****************
         * UPC
         */
        ///*
        UPC upc = new UPC();
        Boolean upcValido = upc.verificar(codigoUPC);
        System.out.println("* UPC " + codigoUPC + ": " + (upcValido ? "válido" : "no válido"));
        if (!upcValido) {
	        System.out.println("* UPC " + codigoUPC + " corregido:");
	        for (String corregido : upc.corregirDatos(codigoUPC)) {
	        	System.out.println(corregido + ": " + (upc.verificar(corregido) ? "válido" : "no válido"));
	        }
	        System.out.println("* UPC " + codigoUPC + " corregido con fuerza brutal:");
	        for (String corregido : upc.corregirDatosFuerzaBrutal(codigoUPC)) {
	        	System.out.println(corregido + ": " + (upc.verificar(corregido) ? "válido" : "no válido"));
	        }
        }
        //*/

        /**********************
         * ISBN-10
         */
        ///*
        ISBN isbn = new ISBN();
        Boolean isbnValido = isbn.verificar(codigoISBN);
        System.out.println("* ISBN-10 " + codigoISBN + ": " + (isbnValido  ? "válido" : "no válido"));
        if (!isbnValido) {
	        System.out.println("* ISBN-10 " + codigoISBN + " corregido:");
	        for (String corregido : isbn.corregirDatos(codigoISBN)) {
	        	System.out.println(corregido + ": " + (isbn.verificar(corregido) ? "válido" : "no válido"));
	        }
	        System.out.println("* ISBN-10 " + codigoISBN + " corregido con fuerza brutal:");
	        for (String corregido : isbn.corregirDatosFuerzaBrutal(codigoISBN)) {
	        	System.out.println(corregido + ": " + (isbn.verificar(corregido) ? "válido" : "no válido"));
	        }
        }
        //*/
     
        /*********************************
         * NIF
         */
        ///*
        NIF nif = new NIF();
        Boolean nifValido = nif.verificar(codigoDNI);
        System.out.println("* NIF " + codigoDNI + ": " + (nifValido  ? "válido" : "no válido"));
        if (!nifValido) {
	        System.out.println("* NIF " + codigoDNI + " corregido:");
	        for (String corregido : nif.corregirDatos(codigoDNI)) {
	        	System.out.println(corregido + ": " + (nif.verificar(corregido) ? "válido" : "no válido"));
	        }
        }
        //*/

        /*********************************
         * ISBN-13
         */
        ///*
        ISBN13 isbn13 = new ISBN13();
        Boolean isbn13Valido = isbn13.verificar(codigoISBN13);
        System.out.println("* ISBN-13 " + codigoISBN13 + ": " + (isbn13Valido ? "válido" : "no válido"));
        if (!isbn13Valido) {
	        System.out.println("* ISBN-13 " + codigoISBN13 + " corregido:");
	        for (String corregido : isbn13.corregirDatos(codigoISBN13)) {
	        	System.out.println(corregido + ": " + (isbn13.verificar(corregido) ? "válido" : "no válido"));
	        }
	        System.out.println("* ISBN-13 " + codigoISBN13 + " corregido con fuerza brutal:");
	        for (String corregido : isbn13.corregirDatosFuerzaBrutal(codigoISBN13)) {
	        	System.out.println(corregido + ": " + (isbn13.verificar(corregido) ? "válido" : "no válido"));
	        }
        }
        //*/
        
    }

}
