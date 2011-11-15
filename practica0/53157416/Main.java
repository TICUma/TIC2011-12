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
        String codigoUPC = "9780444485193";     //"039400014416";
        String codigoDNI = "55555555";
        String codigoISBN13 = "978-84-473-5602-"; //;

        /*****************
         * 
         */
        Codificacion cod = new UPC();
        System.out.println(cod.verificar(codigoUPC) ? "válido" : "No válido");
        //String[] s = cod.corregirDatos("9780444485163");
       /* for (int i=0;i<s.length;i++){
        	System.out.println(s[i]);
        	System.out.println(cod.verificar(s[i]) ? "válido" : "No válido");
        
        }*/
        /**********************
         * 
         */
        cod = new ISBN();
        System.out.println(cod.generarCodigoControl(codigoISBN));
        System.out.println(cod.verificar("0815100000") ? "válido" : "No válido");
        /*String[] ss=cod.corregirDatos("0-8152-0000-0");
        for (int i=0;i<ss.length;i++){
        	System.out.println(ss[i]);
        }*/
        /*********************************
         * 
         */
        cod = new NIF();
        System.out.println(cod.generarCodigoControl(codigoDNI));
        System.out.println(cod.verificar("55553554"+"K")  ? "válido" : "No válido");
       /* String[] ss=cod.corregirDatos("55555554"+"K");
        for (int i=0;i<ss.length;i++){
        	System.out.println(ss[i]);
        }*/
        /*********************************
         *
         */
        cod = new ISBN13();
        System.out.println(cod.generarCodigoControl(codigoISBN13));
        System.out.println(cod.verificar(codigoISBN13+"7")  ? "válido" : "No válido");
        String[] s = cod.corregirDatos("978-84-453-5602-");
         for (int i=0;i<s.length;i++){
         	System.out.println(s[i]);
         	System.out.println(cod.verificar(s[i]) ? "válido" : "No válido");
         
         }
    }

}
