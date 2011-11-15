/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package practica0;

public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {


        String codigoISBN = "0-1234-5678"; //
        String codigoUPC = "05111146833";     //"9780444485193" "039400014416";
        String codigoDNI = "55555555";
        String codigoISBN13 = "978-84-473-5602-"; //;
        String codigoNumeroCuenta = "3333344444";
        String codigoBanco = "1111";
        String codigoSucursal="2222";
        String codigoCuentaBancaria= codigoBanco+codigoSucursal+codigoNumeroCuenta;
        /*****************
         * 
         */
        Codificacion cod ;
        
        System.out.println("UPC: ");
        cod = new UPC();
        System.out.println(cod.generarCodigoControl(codigoUPC));
        System.out.println(cod.verificar(codigoUPC+3) ? "válido" : "No válido");
        mostrarPosiblesSoluciones(cod.corregirDatos(codigoUPC+2));
        /**********************
         * 
         */
        System.out.println("**********************");
        
        System.out.println("ISBN: ");
        cod = new ISBN();
        System.out.println(cod.generarCodigoControl(codigoISBN));
        System.out.println(cod.verificar(cod.generarCodigoControl(codigoISBN
                + "9")) ? "válido" : "No válido");
        mostrarPosiblesSoluciones(cod.corregirDatos(codigoISBN));
        
        /*********************************
         * 
         */
        System.out.println("**********************");
        
        System.out.println("NIF: ");
        cod = new NIF();
        System.out.println(cod.generarCodigoControl(codigoDNI));
        System.out.println(cod.verificar(codigoDNI+"V")  ? "válido" : "No válido");
        mostrarPosiblesSoluciones(cod.corregirDatos(codigoDNI+"C"));
    

        /*********************************
         *
         */
        System.out.println("**********************");
        System.out.println("ISBN13: ");
        
         cod = new ISBN13();
         System.out.println( cod.generarCodigoControl(codigoISBN13));
         System.out.println(cod.verificar(codigoISBN13+7)  ? "válido" : "No válido");
         mostrarPosiblesSoluciones(cod.corregirDatos(codigoISBN13+4));

         System.out.println("**********************");
          
         System.out.println("CCC: ");
         cod=new CuentaBancaria();
         System.out.println(cod.generarCodigoControl(codigoCuentaBancaria));
         System.out.println(cod.verificar(codigoBanco+codigoSucursal+"09"+codigoNumeroCuenta)  ? "válido" : "No válido");
//        System.out.println(cod.verificar(codigoBanco+codigoSucursal+"92"+codigoNumeroCuenta)  ? "válido" : "No válido");
         mostrarPosiblesSoluciones(cod.corregirDatos(codigoBanco+codigoSucursal+"01"+codigoNumeroCuenta));
    }
    
    
    private static void mostrarPosiblesSoluciones(String[] correcciones)
    {
        for (int i=0;i< correcciones.length;i++)
        {
            System.out.println("correcion"+i+": "+correcciones[i]);
        }
    }

}

