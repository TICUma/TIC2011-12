/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 * 
 * JORGE PEREZ RUIZ 
 * ATA 2011 Ingeniera Informática
 * 
 */

package practica0;

import java.util.ArrayList;

/**
 *
 * @author monte
 */
public class ISBN13 extends Codificacion{

     private static final int MODULO = 10;
     private static final int TAMDATOS = 12;
     
      public boolean verificar(String codigo) {
        codigo = codigo.replaceAll("-", "");
        int resultado = 0;
        try {
            for (int i = 0; i < codigo.length(); i++) {
               if (i%2==0)
                resultado += (Integer.parseInt(codigo.substring(i, i+1))*1);
               else
                resultado += (Integer.parseInt(codigo.substring(i, i+1))*3);
            }
        } catch (NumberFormatException e) {
            return false;
        }
        return resultado % MODULO == 0;
    }


    @Override
    public String generarCodigoControl(String codigo) {

           codigo = codigo.replaceAll("-", "");

     int resultado = 0;

        try {
            for (int i = 0; i < codigo.length(); i++) {
               if (i%2==0)
                resultado += (Integer.parseInt(codigo.substring(i, i+1))*1);
               else
                resultado += (Integer.parseInt(codigo.substring(i, i+1))*3);
            }
        } catch (NumberFormatException e) {
            return null;
        }

        resultado = resultado % MODULO;
        resultado = (MODULO -resultado)%MODULO;
        return codigo+resultado;
    }


    public String[] corregirDatos(String codigo) {
        ArrayList<String> resultado = new ArrayList<String>();
        codigo = codigo.replaceAll("-", "");
        String cad;
        
        /* Para cada numero vemos todos los posibles valores y comprobamos*/
        for (int i = 0; i < TAMDATOS;i++){
            for (int j = 0; j < 10; j++){
                /*Vemos los valores y si alguno nos da la letra del DNI, lo metemos*/
                cad = ISBN.cambiarValorChar(codigo, i, j);
                if (verificar(cad)){
 //                   System.out.println("=============================Se obtiene un codigo bueno-> " + cad);
                    resultado.add(cad);
                }
            }
        }
        System.out.println(resultado);
        String[] res = new String[resultado.size()];
        for (int i = 0; i < resultado.size(); i++)
            res[i] = resultado.get(i);
        return res;
    }

}
