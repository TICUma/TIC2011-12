/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package practica0;

import practica0.ISBN;
import practica0.NIF;
import practica0.UPC;

/**
 *
 * @author Administrador
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        String codigo = "0-13-088976-6";
        String codigoISBN = "0-444-85193-3"; //"84-206-8186-5";
        String codigoUPC = "036000291451";//"9780444485193";     //";
        String codigoDNI = "55555555";
        String codigoISBN13 = "567-80-126-1234-8";//"978-84-473-5602-"; //;
        String DniError = "05449659S";

        /*****************
         * 
         */
        System.out.println("UPC"); 
        Codificacion cod = new UPC();
        System.out.println(cod.verificar(codigoUPC) ? "válido" : "No válido");
        String control = cod.generarCodigoControl(codigoUPC) ; 
        System.out.println("Control Corregido : " +control );
        String[] res = cod.corregirDatos(codigoUPC);
        for (int i = 0 ; i < res.length;i++) { 
        	if(res[i] != null){
        		System.out.println(res[i]);
        		System.out.println(cod.verificar(res[i])  ? "válido" : "No válido");
        		System.out.println("");
        	}
    	}
        
        /**********************
         * 
         */
        System.out.println("ISBN-10");
        cod = new ISBN();
        System.out.println(cod.generarCodigoControl(codigoISBN));
        System.out.println(cod.verificar(codigoISBN) ? "válido" : "No válido");
        res = cod.corregirDatos(codigo);
        for (int i = 0 ; i < res.length;i++) { 
        	if(res[i] != null){
        		System.out.println(res[i]);
        		System.out.println(cod.verificar(res[i])  ? "válido" : "No válido");
        		System.out.println("");
        	}
    	}
        
        /**
         * verificacion de validez de los nuevos codigos
         */
        for (int i = 0 ; i < res.length;i++) { 
        	if(res[i] != null){
        		System.out.println(res[i]);
        		System.out.println(cod.verificar(res[i])  ? "válido" : "No válido");
        		System.out.println("");
        	}
    	}
        /*********************************
        *
        */
       System.out.println("ISBN-13");	 
	   cod = new ISBN13();
       System.out.println(cod.generarCodigoControl(codigoISBN13.substring(0, codigoISBN13.length()-1)));
       System.out.println(cod.verificar(codigoISBN13+"7")  ? "válido" : "No válido");
       res = cod.corregirDatos(codigoISBN13);
       for (int i = 0 ; i < res.length;i++) { 
    	   if(res[i] != null){
    		   System.out.println(res[i]);
    		   System.out.println(cod.verificar(res[i])  ? "válido" : "No válido");
    		   System.out.println("");
       		}
   	   }
       
       /*********************************
        * 
        */
       System.out.println("NIF \n");
       cod = new NIF();
       System.out.println(cod.generarCodigoControl(codigoDNI));
       System.out.println(cod.verificar(codigoDNI+"K")  ? "válido" : "No válido");
       res = cod.corregirDatos(DniError);
       for (int i = 0 ; i < res.length;i++) { 
    	   if(res[i] != null){
    		   System.out.println(res[i]);
    		   System.out.println(cod.verificar(res[i])  ? "válido" : "No válido");
    		   System.out.println("");
       		}
   	   }
        
       
    }

}
