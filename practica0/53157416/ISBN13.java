/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package practica0;

import java.util.LinkedList;

/**
 *
 * @author monte
 */
public class ISBN13 extends Codificacion{

     private static final int MODULO = 10;

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
    	 int resultado = 0;
         
         codigo = codigo.replaceAll("-", "");
         for (int i = 0; i < codigo.length(); i++) {
             if (i%2==0)
              resultado += (Integer.parseInt(codigo.substring(i, i+1))*1);
             else
              resultado += (Integer.parseInt(codigo.substring(i, i+1))*3);
          }
        
         resultado = resultado % MODULO;
         if (resultado != 0){
       	  
         resultado=0;
         String code = "";
         LinkedList<String> ss = new LinkedList<String>();
         for (int i=0;i<12;i++){
       	  	for(int j=0;j<10;j++){
       		resultado=0;  
       	    code = codigo.substring(0,i)+String.valueOf(j)+codigo.substring(i+1,codigo.length());
       	  
       	    	for (int k = 0; k < codigo.length(); k++) {
       	    		if (k%2==0)
       	    			resultado += (Integer.parseInt(codigo.substring(k, k+1))*1);
       	    		else
       	    			resultado += (Integer.parseInt(codigo.substring(k, k+1))*3);
       	    	}
       	  
       	    	if (resultado % MODULO == 0){
       	    		ss.add(code);
       	    	}
       	  	}	
         }
         return ss.toArray(new String[0]);
         }
         else return new String[0];
   }

}
