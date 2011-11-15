/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package practica0;

/**
 *
 * @author monte
 */
public class ISBN13 extends Codificacion{

     private static final int MODULO = 10;
     
     private int sumarCodigo(String codigo) { 
    	 int res = 0 ; 
    	 codigo = codigo.replaceAll("-", "");
    	 
    	 
             for (int i = 0; i < codigo.length(); i++) {
                if (i%2==0)
                 res += (Integer.parseInt(codigo.substring(i, i+1))*1);
                else
                 res += (Integer.parseInt(codigo.substring(i, i+1))*3);
             }
         
         return res ;
    	 
     }

      public boolean verificar(String codigo) {
    	boolean ok = false ; 
    	
    	if(codigo.length() == 13){  
    		
    		int resultado = 0;
    		try {
    			resultado = sumarCodigo(codigo);
    		} catch (NumberFormatException e) {
    			return false;
    		}
           ok = (resultado % MODULO == 0);
    	}
    	return ok ;
    }


    public String generarCodigoControl(String codigo) {

           

     int resultado = 0;

        try {
            resultado = sumarCodigo(codigo);
        } catch (NumberFormatException e) {
            return null;
        }

        resultado = resultado % MODULO;
        resultado = (MODULO -resultado)%MODULO;
        return codigo+resultado;
    }
    
    private void posIsbn(String[] cadenas,int num,String code){ 
    	
    	code = code.replaceAll("-", "");
    	int ind = 0 ;
    	int j = 1 ; 
    		   
    	if(num%3 == 0) {
    	   int valor = num / 3 ; 
    	   while((ind < cadenas.length) &&(j < code.length())) { 
    		   int aux = Integer.parseInt(code.substring(j, j+1));
    		   if(aux > valor) { 
    			 cadenas[ind] = code.substring(0, j)+Integer.toString(aux -valor)+code.substring(j+1, code.length());
    			 ind ++ ; 
    		   }
    	       j = j + 2 ;
    	   }
    	}else{ 
  		   j = 0 ;
  		   int valor = MODULO - num ; 
  		   while((ind < cadenas.length) &&(j < code.length())) { 
  			   int aux = Integer.parseInt(code.substring(j, j+1));
  			   if(valor + aux < 10) { 
  				   cadenas[ind] = code.substring(0, j)+Integer.toString(valor+aux)+code.substring(j+1, code.length());
  				   ind ++ ; 
  			   }
    	       j = j + 2 ;
   		   }
    	}
   }

    public String[] corregirDatos(String codigo) {
        /** se supone que el digito de control esta correcto 
         * 
         */
    	String[] res = new String[10];
    	int suma = sumarCodigo(codigo);
    	int sf = suma % MODULO ; 
    	if(!verificar(codigo)) 	posIsbn(res,sf,codigo);
    	return res ; 
    }
    
   

}
