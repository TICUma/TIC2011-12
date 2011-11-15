/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package practica0;

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
                codigoCorregido = codigo.substring(0, i) + digito + codigo.substring(i+1, codigo.length());
                if (verificar(codigoCorregido)) {
                    ok = true;
                } else {
                    digito++;
                }
            }
        }
        return codigoCorregido;
    }
    
    /************************
     * 
     * Metodo auxiliar para calucalar la suma 
     * 
     */
    
    int sumaCod(String code) { 
    	int res = 0 ;
    	for(int i = 0 ; i < code.length()-1;i++){ 
    		if(i%2 == 0) { 
    			res += 3*Integer.parseInt(code.substring(i, i+1));
    		}else { 
    			res += Integer.parseInt(code.substring(i, i+1));
    		}
    	}
    	return res ;
    }

    /**************
     * 
     * @param codigo
     * @return
     */
    public String generarCodigoControl(String codigo) {
        String resultado = "";
    	int S = 0 ;
    	if(codigo.length()==12) { 
    		S = sumaCod(codigo)% MULTIPLO ;
    		S = MULTIPLO - S ;
    		resultado = codigo.substring(0, codigo.length()-1)+Integer.toString(S);
    	}
    	return resultado ;
    }
    
    /****** ******
     * 
     * metodo aux para corregir la cadena 
     */
   private void corregir(String[] cadenas , String code ,int num) {
	   
	   int ind = 0 ;
	   int j = 0 ; 
	   
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
	   }else { 
		   j = 1 ;
		   int valor = MULTIPLO - num ; 
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
    
    
    /********************* 
     * 
     * Dado el codigo control correcto 
     * intentaremos ofrecer Upc alternativos 
     * Maximo 8 
     */
    
    public String[] corregirDatos(String codigo) {
    	
    	String[] res = new String[8] ;
    	
    	int Suma = sumaCod(codigo) + Integer.parseInt(codigo.substring(codigo.length()-1, codigo.length()));
    	corregir(res,codigo,Suma%MULTIPLO) ;
        return res ;
    }

}
