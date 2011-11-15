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

    private static final int MODULO = 10;

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
        return resultado % MODULO == 0;
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
    	codigo = codigo.replaceAll("-", "");

        int resultado = 0;

           try {
               for (int i = 0; i < codigo.length(); i++) {
                  if (i%2==0)
                   resultado += (Integer.parseInt(codigo.substring(i, i+1))*3);
                  else
                   resultado += (Integer.parseInt(codigo.substring(i, i+1))*1);
               }
           } catch (NumberFormatException e) {
               return null;
           }

           resultado = resultado % MODULO;
           resultado = (MODULO -resultado)%MODULO;
           return codigo+resultado;
    }

    
    public String[] corregirDatos(String codigo) {
//Suponiendo que el codigo de control está bien
        
    	String[] correccion = new String[13];
    	codigo = codigo.replaceAll("-", "");
      
        int resultado = 0;
        //Calculo el resultado sin tener en cuenta el dígito de control
        for (int i = 0; i < codigo.length()-1; i++) {
        	if (i%2==0)
        		resultado += (Integer.parseInt(codigo.substring(i, i+1))*3);
        	else
        		resultado += (Integer.parseInt(codigo.substring(i, i+1))*1);
        }
        //Calculo el dígito de control
        int control = (Integer.parseInt(codigo.substring(11,12)));
        
        if ((resultado + control*1) % MODULO !=0){
        	/*Si el dígito de control no coincide quiere decir que algún bit está mal en el código restante
        	 * calculo entonces el modulo que sale de la suma de digitos x1+x2+...x12 mod 10 = miModulo
        	 */
        	int miModulo= MODULO - (resultado % MODULO);
        	
        	int diferencia1=0;
        	int diferencia2 = 0;
        	/*Obtengo las diferencias de miModulo con el valor del codigo de control, tanto en un sentido como en otro
        	 * es decir, si miModulo < control lo que me queda para llegar y si miModulo > control lo que me he pasado o 
        	 * por decirlo de otra forma lo que me queda para volver a llegar
        	 */

        	if(miModulo-control > 0){
        		diferencia1 = miModulo - control;
        		diferencia2 = MODULO - (miModulo - control);
        	}else{
        		diferencia1 = control-miModulo;
        		diferencia2 = MODULO - (control -miModulo);
        	}
        	
        	int j = 0;
           	int cambio1 = 0;
           	
           	for (int i=0;i<codigo.length()-1;i++){
           		if ((i+1)%2!=0){//Si es una posición impar 
           			if (diferencia1 % 3 == 0){
           				cambio1 = (Integer.parseInt(codigo.substring(i,i+1)) - (diferencia1/3));
    	    	       	
	    	       		if(cambio1 > 9|| cambio1<0){
	    	       			cambio1 = Math.abs(cambio1 % MODULO);
	    	       		}
	    	       			correccion[j] = "\nCodigo: " + codigo + " modificar posicion " + (i+1) + " por un " + cambio1;
	    	       			j++;
	    	       		
           			}
           		}
           		else{ //Si es una posición par
           			
           			cambio1 = (Integer.parseInt(codigo.substring(i,i+1)) - diferencia1);
           			if(cambio1 > 9 || cambio1<0){
           				cambio1 = Math.abs(cambio1 % MODULO);
    	       		}
    	       			correccion[j] = "\nCodigo: " + codigo + " modificar posicion " + (i+1) + " por un " + cambio1;
    	       			j++;
    	       		
    	        }
           	}
        
        	int z= j;
           	int cambio2 = 0;
            	
           	for (int i=0;i<codigo.length()-1;i++){
           		if ((i+1)%2!=0){//Si es una posición impar
           			if (diferencia2 % 3 == 0){
           				cambio2 = (Integer.parseInt(codigo.substring(i,i+1)) + (diferencia2/3));
	    	       		if(cambio2 > 9 || cambio2 < 0){
	    	       			cambio2 = Math.abs(cambio2 % MODULO);
	    	       		}
	    	       			correccion[j] = "\nCodigo: " + codigo + " modificar posicion " + (i+1) + " por un " + cambio2;
	    	       			z++;
	    	       		
           			}
           			
           		}
           		else{ //Si es una posición par
           			cambio2 = (Integer.parseInt(codigo.substring(i,i+1)) + diferencia2);
           			if(cambio2 > 9 || cambio2<0){
           				cambio2 = Math.abs(cambio2 % MODULO);
    	       		}
    	       			correccion[j] = "\nCodigo: " + codigo + " modificar posicion " + (i+1) + " por un " + cambio2;
    	       			z++;
    	       		
    	        }
           	}
        	
        }
        return correccion;
        //throw new UnsupportedOperationException("Not supported yet.");
    }

}
