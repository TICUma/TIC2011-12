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


        String codigoISBN = "0-444-85193-3"; //"84-206-8186-5";
        String codigoUPC = "039400014416";     //"039400014416";
        String codigoDNI = "55555555";
        String codigoISBN13 = "978-84-473-5602-";
        //String codigoCCC2 = "5543-9076-0974-6515";
        
        
        String codISBN = "0-13-088976-6";
        //String codISBN = "0-00-000000-2";
        
        String codUPC = "62843215221";
        //String codUPC = "99999999999";
        
        String codDNI1="74886576";
        String codDNI2="74886577W";
              
        String codCCC2 = "5543-9079- -0974651548";
        
        
        /*****************
         * 
         */
        Codificacion cod = new UPC();
        System.out.println("UCP:");
        System.out.println("Deberia ser ok:");
        System.out.println(cod.verificar(codigoUPC) ? "válido" : "No válido");
        System.out.println("-----");
        System.out.println("Debería estar mal:");
        System.out.println(cod.verificar(codUPC) ? "válido" : "No válido");
        System.out.println("-----");
        String codUPC1=cod.generarCodigoControl(codUPC);
        System.out.println("El codigo "+codUPC1+ " deberia estar bien:");
        System.out.println(cod.verificar(codUPC1) ? "válido" : "No válido");
        System.out.println("-----");
        System.out.println("Corrigiendo el control del codigo: "+codUPC+"5");
        System.out.println(cod.corregirControl(codUPC+"5"));
        System.out.println("-----");
        System.out.println("Corrigiendo los datos del codigo: "+codUPC+"5");
        cod.corregirDatos(codUPC+"5");
        System.out.println("");

        /**********************
         * 
         */
        cod = new ISBN();
        System.out.println("ISBN:");
        System.out.println("Calcular control de: "+"0-8044-2957-");
        String c = cod.generarCodigoControl("0-8044-2957-");
        System.out.println(c);
        System.out.println("Comprobando validez");
        System.out.println(cod.verificar(c) ? "válido" : "No válido");
        System.out.println("-----");
        System.out.println("Corrigiendo el codigo: "+"214751102"+"X");
        System.out.println(cod.verificar("214751102"+"X") ? "válido" : "No válido");
        System.out.println(cod.corregirDatos("214751102"+"X"));
        System.out.println("-----");
        System.out.println("Calcular control de: "+"0-8044-2957-");
        System.out.println(cod.verificar(codigoISBN) ? "válido" : "No válido");
        System.out.println(cod.verificar(codigoISBN) ? "válido" : "No válido");
        System.out.println("");
        
        System.out.println("Pruebas ISBN:");
        System.out.println(cod.generarCodigoControl(codISBN));
        System.out.println(cod.verificar(codISBN) ? "válido" : "No válido");
        System.out.println("");
        
        String nuevocodISBN=cod.corregirControl(codISBN);
        System.out.println(cod.verificar(nuevocodISBN) ? "válido" : "No válido");
        System.out.println(""); 
        
        System.out.println("Prueba corregir datos:");
        System.out.println("Codigo a corregir: "+codISBN);
        System.out.println(cod.verificar(codISBN) ? "válido" : "No válido");
        System.out.println(cod.corregirDatos(codISBN));
        
        
        System.out.println(""); 
     
        /*********************************
         * 
         */
        cod = new NIF();
        System.out.println("NIF:");
        System.out.println(cod.generarCodigoControl(codigoDNI));
        System.out.println(cod.verificar(codigoDNI+"K")  ? "válido" : "No válido");
        System.out.println("");
        
        
        
        System.out.println("Pruebas NIF 1:");
        System.out.println(cod.generarCodigoControl(codDNI1));
        System.out.println(cod.verificar(codDNI1));
        System.out.println(cod.verificar(codDNI1+"W"));
        System.out.println("");
        System.out.println("Pruebas NIF 2:");
        System.out.println(cod.verificar(codDNI2));
        System.out.println(cod.corregirDatos(codDNI2));
        System.out.println("");

        /*********************************
         *
         */
        cod = new ISBN13();
        System.out.println("ISBN13:");
        System.out.println(cod.corregirControl(codigoISBN13+"0"));
        System.out.println(cod.generarCodigoControl(codigoISBN13));
        System.out.println(cod.verificar(codigoISBN13+"7")  ? "válido" : "No válido");
        cod.corregirDatos("999999999999"+"0");
        System.out.println("");
        
        /*********************************
         * 
         */
        cod = new CCC();
        System.out.println("CCC:");
        System.out.println(codCCC2);
        String codCCC3=cod.generarCodigoControl(codCCC2);
        System.out.println(codCCC3);
        System.out.println("-----");
        System.out.println(codCCC3);
        System.out.println(cod.verificar(codCCC3)  ? "válido" : "No válido");
        System.out.println("-----");
        cod.corregirDatos("55439079"+"72"+"0974651548");
        System.out.println(cod.verificar("55439079"+"92"+"0974651548")  ? "válido" : "No válido");
        
    }

}
