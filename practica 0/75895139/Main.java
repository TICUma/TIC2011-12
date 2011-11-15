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


        String codigoISBN = "84-206-8176-5";
        String codigoISBN2 = "84-206-8186";
        String codigoUPC = "03940001441";
        String codigoUPC2 = "049400014416";
        String codigoDNI = "55555555";
        String codigoISBN13 = "978-84-473-5602-"; //;
        String codigoISBN132 = "878-84-473-5602-7"; //;
        
        String codigoSICA1 = "1111-2222-4445556667";
        String codigoSICA2 = "1111-2202-09-4445556667"; //Error en la entidad
        String codigoSICA3 = "1121-2222-09-4445556667"; //Error en el banco
        
        Codificacion cod = new SICA();
        System.out.println(cod.generarCodigoControl(codigoSICA1));
        System.out.println(cod.verificar(codigoSICA2));
        System.out.println(cod.verificar(codigoSICA3));
        System.out.println("CORRECCIÓN-SICA: (" + cod.corregirDatos(codigoSICA2)[0] + " ó " + cod.corregirDatos(codigoSICA2)[1] + ")");
        System.out.println("CORRECCIÓN-SICA: (" + cod.corregirDatos(codigoSICA3)[0] + " ó " + cod.corregirDatos(codigoSICA3)[1] + ")");

        /*****************
         * 
         */
        cod = new UPC();
        System.out.println(cod.verificar(codigoUPC) ? "válido" : "No válido");
        System.out.println("UPC:"+cod.generarCodigoControl(codigoUPC));
        System.out.println("CORRECCION-UPC:" + cod.corregirDatos(codigoUPC2)[0] +"\n" + 
        		cod.corregirDatos(codigoUPC2)[1] +"\n"+  cod.corregirDatos(codigoUPC2)[2] +
        		"\n"+ cod.corregirDatos(codigoUPC2)[3] + cod.corregirDatos(codigoUPC2)[4] +
        		"\n" +cod.corregirDatos(codigoUPC2)[5] +"\n"+ cod.corregirDatos(codigoUPC2)[6] +
        		"\n"+ cod.corregirDatos(codigoUPC2)[7] + "\n"+ cod.corregirDatos(codigoUPC2)[8] +
        		"\n"+ cod.corregirDatos(codigoUPC2)[9]+ "\n");
        /**********************
         * 
         */
        cod = new ISBN();
        System.out.println(cod.generarCodigoControl(codigoISBN2));
        System.out.println(cod.verificar(codigoISBN) ? "válido" : "No válido");
        System.out.println("CORRECCION-ISBN:" + cod.corregirDatos(codigoISBN)[0] +"\n" + 
        		cod.corregirDatos(codigoISBN)[1] +"\n"+  cod.corregirDatos(codigoISBN)[2] +
        		"\n"+ cod.corregirDatos(codigoISBN)[3] + cod.corregirDatos(codigoISBN)[4] +
        		"\n" +cod.corregirDatos(codigoISBN)[5] +"\n"+ cod.corregirDatos(codigoISBN)[6] +
        		"\n"+ cod.corregirDatos(codigoISBN)[7] + "\n"+ cod.corregirDatos(codigoISBN)[8] +
        		"\n"+ cod.corregirDatos(codigoISBN)[9]+ "\n");
     
        /*********************************
         * 
         */
        cod = new NIF();
        System.out.println(cod.generarCodigoControl(codigoDNI));
        System.out.println(cod.verificar(codigoDNI+"K")  ? "válido" : "No válido");

        /*********************************
         *
         */
        cod = new ISBN13();
        System.out.println(cod.generarCodigoControl(codigoISBN13));
        System.out.println(cod.verificar(codigoISBN132)  ? "válido" : "No válido");
        System.out.println("CORRECCION-ISBN:" + cod.corregirDatos(codigoISBN132)[0] +"\n" +cod.corregirDatos(codigoISBN132)[1] +"\n"+  cod.corregirDatos(codigoISBN132)[2] +
        		"\n"+ cod.corregirDatos(codigoISBN132)[3] + cod.corregirDatos(codigoISBN132)[4] +
        		"\n" +cod.corregirDatos(codigoISBN132)[5] +"\n"+ cod.corregirDatos(codigoISBN132)[6] +
        		"\n"+ cod.corregirDatos(codigoISBN132)[7] + "\n"+ cod.corregirDatos(codigoISBN132)[8] +
        		"\n"+ cod.corregirDatos(codigoISBN132)[9]+ "\n");
    }

}
