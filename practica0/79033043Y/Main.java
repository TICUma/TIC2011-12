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


//        String codigoISBN = "0-444-85193-3"; //"84-206-8186-5";
        String codigoISBN = "84-206-8186-5"; //"84-206-8186-5";
        String codigoISBN_2 = "84-206-5186-X"; //"84-206-8186-5";
        String codigoUPC = "9780444485193";     //"039400014416";
        String codigoUPC_2 = "9780449485193";     //"039400014416";
        String codigoUPC_sin = "978044448519";     //"039400014416";
    //    String codigoDNI = "55555555";
        String codigoDNI = "79029405";
        String codigoDNI2 = "79033003Y";
        String codigoISBN13 = "978-84-473-5602-"; //;
        String codigoISBN13_2 = "978-86-473-5602-7"; //;
        
        String codigoCC_sin = "01234567000123456789"; //;
        String codigoCCcorrecto = "01234567810123456789"; //;
        String codigoCCIncorrecto = "01234567810123456989"; //;
        

        /*****************
         * 
         */
        Codificacion cod = new UPC();
        System.out.println("%%%%%%%%%% CODIGO UPC %%%%%%%%%%");
        System.out.println(cod.verificar(codigoUPC) ? "válido" : "No válido");
        System.out.println(cod.generarCodigoControl(codigoUPC_sin));
        System.out.println(cod.corregirDatos(codigoUPC_2));

        /**********************
         * 
         */
        cod = new ISBN();
        System.out.println("%%%%%%%%%% CODIGO ISBN %%%%%%%%%%");
        System.out.println(cod.generarCodigoControl(codigoISBN));
        System.out.println(cod.verificar(codigoISBN) ? "válido" : "No válido");
        System.out.println(cod.corregirDatos(codigoISBN_2));
        /*********************************
         * 
         */
        cod = new NIF();
        System.out.println("%%%%%%%%%% CODIGO NIF %%%%%%%%%%");
        System.out.println(cod.generarCodigoControl(codigoDNI));
        System.out.println(cod.verificar(codigoDNI+"K")  ? "válido" : "No válido");
           cod.corregirDatos(codigoDNI2);
        /*********************************
         *
         */
        cod = new ISBN13();
        System.out.println("%%%%%%%%%% CODIGO ISBN13 %%%%%%%%%%");
        System.out.println(cod.generarCodigoControl(codigoISBN13));
        System.out.println(cod.verificar(codigoISBN13+"7")  ? "válido" : "No válido");
        cod.corregirDatos(codigoISBN13_2);
        /*********************************
         *
         */
        cod = new CC();
        System.out.println("%%%%%%%%%% CODIGO CuentaCorriente %%%%%%%%%%");
        System.out.println(cod.generarCodigoControl(codigoCC_sin));
        System.out.println(cod.verificar(codigoCCcorrecto)  ? "válido" : "No válido");
        cod.corregirDatos(codigoCCIncorrecto);
    }

}
