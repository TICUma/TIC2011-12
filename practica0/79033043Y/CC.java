/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 * 
 * http://descartes.cnice.mec.es/materiales_didacticos/digitocontrol/DigitoControl.htm
 * 
 * JORGE PEREZ RUIZ 
 * ATA 2011 Ingeniera Informática
 * 
 */

package practica0;

import java.util.ArrayList;

/**
 * Similar a las anteriores pero contamos con dos partes que tenemos que unir.
 * @author 
 */
public class CC extends Codificacion {

    private static final int MULTIPLO = 11;
    private static final int TAMDATOSL = 8;
    private static final int TAMDATOSR = 10;

/**********************
 * 
 * @param codigo
 * @return
 */
    
    public static int sumaDeMultiplicaciones(String numeros)
    {
        int res = 0;
        int vector10[] = {10, 9, 7, 3, 6, 1, 2, 4, 8, 5};
        int vector8[] = {7, 3, 6, 1, 2, 4, 8, 5};
        for (int i = 0; i < numeros.length(); i++){
            if (numeros.length() == 8)
                res += vector8[i]*Integer.parseInt(numeros.substring(i, i+1));
            else if (numeros.length() == 10)
                res += vector10[i]*Integer.parseInt(numeros.substring(i, i+1));
            else
                System.out.println("ERROR EN EL TAMAÑO = " + numeros.length());
        }
        return res;
    }
    
    public boolean verificarIzq(String codigo) {
        int resultado1 = 0;
        int aux = 0;
        try {
            
            resultado1 = sumaDeMultiplicaciones(codigo.substring(0, 8));
//            System.out.println("8 primeros -> "+ codigo.substring(0, 8)+ " y res = "+ resultado1);
        } catch (NumberFormatException e) {
            return false;
        }
        aux = resultado1 % MULTIPLO;
        if (aux > 9) aux = 1;
        
        return aux == Integer.parseInt(codigo.substring(8, 9));
    }
    
    
    public boolean verificarDer(String codigo) {
        int resultado2 = 0, aux = 0;
        try {
//            System.out.println("Los diez ultimos" + codigo.substring(10));
            resultado2 = sumaDeMultiplicaciones(codigo.substring(10));
        } catch (NumberFormatException e) {
            return false;
        }
        aux = resultado2 % MULTIPLO;
        if (aux > 9) aux = 1;
        
        return aux == Integer.parseInt(codigo.substring(9, 10));
    }
    
    @Override
    public boolean verificar(String codigo) {
        return verificarIzq(codigo) && verificarDer(codigo);
    }

    /**************
     * 
     * @param codigo
     * @return
     */
    @Override
    public String generarCodigoControl(String codigo) {
        codigo = codigo.replaceAll("-", "");
        String res, primerosOcho = codigo.substring(0, 8), ultimosDiez = codigo.substring(10);
        int resultado1 = 0, resultado2 = 0;
        
        try {
//            System.out.println("8 primeros -> "+ primerosOcho + "y los ultimos son "+ultimosDiez );
            resultado1 = sumaDeMultiplicaciones(primerosOcho);
            resultado2 = sumaDeMultiplicaciones(ultimosDiez);
        } catch (NumberFormatException e) {
            return null;
        }
        
        resultado1 = resultado1 % MULTIPLO;
        resultado2 = resultado2 % MULTIPLO;
        
        if (resultado1 > 9)
            resultado1 = 1;
        if (resultado2 > 9)
            resultado2 = 1;
        
        res = primerosOcho + resultado1 + resultado2 + ultimosDiez;
        
        return res;
    }

    
    @Override
    public String[] corregirDatos(String codigo) {
        ArrayList<String> resultado1 = new ArrayList<String>();
        ArrayList<String> resultado2 = new ArrayList<String>();
        codigo = codigo.replaceAll("-", "");
        String cad, control = codigo.substring(8, 10);
        int ind = 0;
        /* Para cada numero vemos todos los posibles valores y comprobamos*/
        String primerosOcho = codigo.substring(0, 8), ultimosDiez = codigo.substring(10);
        for (int i = 0; i < TAMDATOSL; i++){
            for (int j = 0; j < 10; j++){
                /*Vemos los valores y si alguno nos da la letra del DNI, lo metemos*/
                cad = ISBN.cambiarValorChar(primerosOcho, i, j);
//                System.out.println(cad+control+ultimosDiez);
                if (verificarIzq(cad+control+ultimosDiez) && !resultado1.contains(cad))
                    resultado1.add(cad);
            }
        }
        for (int i = 0; i < TAMDATOSR; i++){
            for (int j = 0; j < 10; j++){
                /*Vemos los valores y si alguno nos da la letra del DNI, lo metemos*/
                cad = ISBN.cambiarValorChar(ultimosDiez, i, j);
 //               System.out.println(primerosOcho+control+cad);
                if (verificarDer(primerosOcho+control+cad)&& !resultado2.contains(cad))
                    resultado2.add(cad);
            }
        }
        System.out.println(resultado1);
        System.out.println(resultado2);
        //Combinamos los resultados.
        String[] res = new String[resultado1.size()*resultado2.size()];
        for (int i = 0; i < resultado1.size(); i++)
            for (int j = 0; j < resultado2.size(); j++){
                res[ind] = resultado1.get(i)+ control+ resultado2.get(j);
//                System.out.println(res[ind]);
                ind++;
            }
        return res;
    }

}
