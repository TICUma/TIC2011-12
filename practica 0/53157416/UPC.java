/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package practica0;

import java.util.LinkedList;

/**
 *
 * @author 
 */
public class UPC extends Codificacion {

    private static final int MULTIPLO = 10;

/**********************
 * 
 * @param codigo
 * @return
 */
    public boolean verificar(String codigo) {
        int resultado = 0;
        int v;
        try {
            for (int i = 0; i < codigo.length(); i++) {
                v = Integer.parseInt(codigo.substring(i, i+1));
                resultado += i%2 == 0 ? v*3 : v;
            }
        } catch (NumberFormatException e) {
            return false;
        }
        return resultado % MULTIPLO == 0;
    }

    /*******************
     * 
     * @param codigo
     * @return
     */
    
    public String corregirControl(String codigo) {
        int i = 0;
        boolean ok = false;
        String codigoCorregido = new String();

        while (i < codigo.length() && !ok) {
            int digito = 0;
            while (digito < 9 && !ok) {
                codigoCorregido = codigo.substring(0, i-1) + digito + codigo.substring(i+1, codigo.length());
                if (verificar(codigoCorregido)) {
                    ok = true;
                } else {
                    digito++;
                }
            }
        }
        return codigoCorregido;
    }

    /**************
     * 
     * @param codigo
     * @return
     */
    public String generarCodigoControl(String codigo) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    
    public String[] corregirDatos(String codigo) {
    	  int resultado = 0;
          int v;
          
              for (int i = 0; i < codigo.length(); i++) {
                  v = Integer.parseInt(codigo.substring(i, i+1));
                  resultado += i%2 == 0 ? v*3 : v;
              }
         
          resultado = resultado % MULTIPLO;
          if (resultado != 0){
        	  
          resultado=0;
          String code = "";
          LinkedList<String> ss = new LinkedList<String>();
          for (int i=0;i<11;i++){
        	  for(int j=0;j<10;j++){
        		resultado=0;  
        	  code = codigo.substring(0,i)+String.valueOf(j)+codigo.substring(i+1,codigo.length());
        	  
        	  for(int k=0;k<code.length();k++){
        	  v = Integer.parseInt(code.substring(k, k+1));
              resultado += k%2 == 0 ? v*3 : v;  
        	  }
        	  
        	  if (resultado % MULTIPLO == 0){
        		  ss.add(code);
        	  }
        	  }
          }
          return ss.toArray(new String[0]);
          }
          else return new String[0];
    }

}
