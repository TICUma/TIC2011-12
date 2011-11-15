/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

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
     * @param args the command line arguments
     */
    public static void main(String[] args) {


        //String codigoISBN = "0-444-85193-3"; //"84-206-8186-5";
        String codigoISBN = "84-206-8186-5";
        //String codigoUPC = "9780444485193";     //"039400014416";
        String codigoUPC = "039400014416";
        String codigoDNI = "55515555";
//        String codigoISBN13 = "978-84-473-5602-"; //;
        String codigoISBN13 = "978-84-473-5602-"; //;
        String cuentaBanco = "1234-5678-06-1234567890";

        /*****************
         * 
         */
        Codificacion cod = new UPC();
        System.out.println(cod.verificar(codigoUPC) ? "válido" : "No válido");
        //System.out.println(cod.corregirControl(codigoUPC));
        String[] posiblesUPC;
        posiblesUPC=cod.corregirDatos(codigoUPC);
        for (int i=0; i< posiblesUPC.length; i++){
            System.out.println(cod.verificar(posiblesUPC[i]) ? "válido" : "No válido");
        }
        /**********************
         * 
         */
        cod = new ISBN();
        System.out.println(cod.generarCodigoControl(codigoISBN));
        System.out.println(cod.verificar(codigoISBN) ? "válido" : "No válido");
        String[] posibles;
        posibles = cod.corregirDatos(codigoISBN);
        for (int i=0; i< posibles.length; i++){
        	System.out.println(posibles[i]);
            System.out.println(cod.verificar(posibles[i]) ? "válido" : "No válido");
        }
     
        /*********************************
         * 
         */
        cod = new NIF();
        System.out.println(cod.generarCodigoControl(codigoDNI));
        System.out.println(cod.verificar(codigoDNI+"K")  ? "válido" : "No válido");
        posibles = cod.corregirDatos(codigoDNI+"K");
        for (int i=0; i< posibles.length; i++){
        	System.out.println(posibles[i]);
            System.out.println(cod.verificar(posibles[i]) ? "válido" : "No válido");
        }

        /*********************************
         *
         */
        cod = new ISBN13();
        System.out.println(cod.generarCodigoControl(codigoISBN13));
        System.out.println(cod.verificar(codigoISBN13+"7")  ? "válido" : "No válido");
        posibles = cod.corregirDatos(codigoISBN13+"7");
        for (int i=0; i< posibles.length; i++){
        	System.out.println(posibles[i]);
            System.out.println(cod.verificar(posibles[i]) ? "válido" : "No válido");
        }
        
        cod = new CuentaBancaria();
        System.out.println(cuentaBanco);
        System.out.println(cod.verificar(cuentaBanco)  ? "válido" : "No válido");
        posibles = cod.corregirDatos(cuentaBanco);
        for (int i=0; i< posibles.length; i++){
        	System.out.println(posibles[i]);
            System.out.println(cod.verificar(posibles[i]) ? "válido" : "No válido");
        }

    }
}
