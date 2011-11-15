/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package practica0;

import java.util.Arrays;

/**
 *
 * @author Administrador
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {


        String codigoISBN = "84-206-8186-5"; //"84-206-8186-5";
        String codigoISBNError = "84-206-8186-3";
        String codigoUPC = "039400014416";     //"039400014416";
        String codigoUPCError = "039400013416";
        String codigoDNI = "55555555";
        String codigoDNIError = "78978161J";
        String codigoISBN13 = "978-84-473-5602-2"; //;
        String codigoISBN13Error = "978-85-473-5602-2";
        String codigoCCC = "2863-3107-4831012256";
        String codigoCCCError = "2863-3107-21-4831012256";

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
        System.out.println(cod.verificar(codigoISBN) ? "válido" : "No válido");

        /*********************************
         * 
         */
        cod = new NIF();
        System.out.println(cod.generarCodigoControl(codigoDNI));
        System.out.println(cod.verificar(codigoDNI + "K") ? "válido" : "No válido");

        /*********************************
         *
         */
        cod = new ISBN13();
        System.out.println(cod.generarCodigoControl(codigoISBN13));
        System.out.println(cod.verificar(codigoISBN13 + "5") ? "válido" : "No válido");
        /*********************************
         *
         */
        cod = new CCC();
        System.out.println(cod.generarCodigoControl(codigoCCC));
        System.out.println(cod.verificar("2863-3107-11-4831012256") ? "válido" : "No válido");


        cod = new ISBN();
        System.out.println(cod.verificar(codigoISBNError) ? "válido" : "No válido");
        System.out.println("Posibles correcciones: " + Arrays.toString(cod.corregirDatos(codigoISBNError)));

        cod = new ISBN13();
        System.out.println(cod.verificar(codigoISBN13Error) ? "válido" : "No válido");
        System.out.println("Posibles correcciones: " + Arrays.toString(cod.corregirDatos(codigoISBN13Error)));

        cod = new UPC();
        System.out.println(cod.verificar(codigoUPCError) ? "válido" : "No válido");
        System.out.println("Posibles correcciones: " + Arrays.toString(cod.corregirDatos(codigoUPCError)));

        cod = new NIF();
        System.out.println(cod.verificar(codigoDNIError) ? "válido" : "No válido");
        System.out.println("Posibles correcciones: " + Arrays.toString(cod.corregirDatos(codigoDNIError)));

        cod = new CCC();
        System.out.println(cod.verificar(codigoCCCError) ? "válido" : "No válido");
        System.out.println("Posibles correcciones: " + Arrays.toString(cod.corregirDatos(codigoCCCError)));
    }
}
