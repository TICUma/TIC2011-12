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


        String codigoISBN = "0-444-85193-3"; //"84-206-8186-5";
        String codigoUPC = "05111146833";     //"039400014416";
        String codigoDNI = "55555555";
        String codigoISBN13 = "978-84-473-5602-"; //;
        String codigoCCC = "2103-2332-1123445622";

        /*****************
         * 
         */
        Codificacion cod;

        /**********************
         * 
         */
        System.out.println("1 - ISBN");
        System.out.println("************************************");
        
        cod = new ISBN();
        System.out.println(cod.generarCodigoControl(codigoISBN));
        System.out.println(cod.verificar(codigoISBN) ? "válido" : "No válido");
        
        System.out.println("Prueba con ISBN: 0-444-85192-30 siendo el correcto 0-444-85193-30");

        cod.corregirDatos("4-444-85193-30");
     
        /*********************************
         * 
         */
        System.out.println("2 - NIF");
        System.out.println("******************************");
        
        cod = new NIF();
        System.out.println(cod.generarCodigoControl(codigoDNI));
        System.out.println(cod.verificar(codigoDNI+"K")  ? "válido" : "No válido");
        System.out.println("Prueba con NIF: 74876229T siendo el correcto 74886229T");
        cod.corregirDatos("74876229T");

        /*********************************
         *
         */
        System.out.println("3 - ISBN13");
        System.out.println("******************************");
        
        cod = new ISBN13();
        System.out.println(cod.generarCodigoControl(codigoISBN13));
        System.out.println(cod.verificar(codigoISBN13+"7")  ? "válido" : "No válido");
        System.out.println("Prueba con ISBN13: 978-0-306-40625-7 siendo el correcto 978-0-306-40615-7");
        cod.corregirDatos("978-0-306-40625-7");
        
        System.out.println("4 - UPC");
        System.out.println("*****************************");
        
        cod = new UPC();       
        System.out.println(cod.verificar(cod.generarCodigoControl(codigoUPC))  ? "válido" : "No válido");
        System.out.println("Prueba con UPC: 091111468333 siendo el correcto 051111468333");
        cod.corregirDatos("091111468333");
        
        System.out.println("5 - CCC");
        System.out.println("*****************************");
        cod = new CCC(); 
        System.out.println(cod.generarCodigoControl(codigoCCC));
        System.out.println(cod.verificar("2103-2332-49-1123445622") ? "válido" : "No válido");
        System.out.println("Prueba con CCC: 2113-2332-49-1123445622 siendo el correcto 2103-2332-49-1123445622");
        cod.corregirDatos("2113-2332-49-1123445622");
    }

}
