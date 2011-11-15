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
    	//Suponiendo que el codigo de control está bien
        
    	String[] correccion = new String[13];
    	codigo = codigo.replaceAll("-", "");
      
        int resultado = 0;
        //Calculo el resultado sin tener en cuenta el dígito de control
        for (int i = 0; i < codigo.length()-1; i++) {
        	if (i%2==0)
        		resultado += (Integer.parseInt(codigo.substring(i, i+1))*1);
        	else
        		resultado += (Integer.parseInt(codigo.substring(i, i+1))*3);
        }
        //Calculo el dígito de control
        int control = (Integer.parseInt(codigo.substring(12,13)));
        
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
        		diferencia2 = (MODULO - miModulo) + control;
        	}else{
        		diferencia1 = control-miModulo;
        		diferencia2 = (MODULO - miModulo) + control;
        	}
        	
        	int j = 0;
           	int cambio1 = 0;
           	
           	for (int i=0;i<codigo.length()-1;i++){
           		if ((i+1)%2!=0){//Si es una posición impar resto la diferencia
           			cambio1 = (Integer.parseInt(codigo.substring(i,i+1)) + diferencia1);
           			if(cambio1 > 9 || cambio1<0){
    	       			
    	       		}else{
    	       			correccion[j] = "\nCodigo: " + codigo + " modificar posicion " + (i+1) + " por un " + cambio1;
    	       			j++;
    	       		}
           		}
           		else{ //Si es una posición par resto un valor *3
           			if (diferencia1 % 3 == 0){
           		
	    	       		/* Si la diferencia es multiplo de tres busco la posición a cambiar*/
	    	       		
	    	       		/*Saco los valores divisibles por la diferencia que serán las posiciones que pueden sumar
	    	       		 * o restar para ajustar el resultado.
	    	       		 */
	    	       		cambio1 = (Integer.parseInt(codigo.substring(i,i+1)) + (diferencia1/3));
	    	       	
	    	       		if(cambio1 > 9|| cambio1<0){
	    	       			
	    	       		}else{
	    	       			correccion[j] = "\nCodigo: " + codigo + " modificar posicion " + (i+1) + " por un " + cambio1;
	    	       			j++;
	    	       		}
           			}
    	        }
           	}
        
        	int z= j;
           	int cambio2 = 0;
            	
           	for (int i=0;i<codigo.length()-1;i++){
           		if ((i+1)%2!=0){//Si es una posición impar sumo la diferencia
           			cambio2 = (Integer.parseInt(codigo.substring(i,i+1)) - diferencia2);
           			if(cambio2 > 9 || cambio2<0){
    	       			
    	       		}else{
    	       			correccion[j] = "\nCodigo: " + codigo + " modificar posicion " + (i+1) + " por un " + cambio2;
    	       			z++;
    	       		}
           		}
           		else{ //Si es una posición impar resto un valor *3
           			if (diferencia2 % 3 == 0){
           		
	    	       		
	    	       		cambio2 = (Integer.parseInt(codigo.substring(i,i+1)) - (diferencia2/3));
	    	       		if(cambio2 > 9 || cambio2 < 0){
	    	       			
	    	       		}else{
	    	       			correccion[j] = "\nCodigo: " + codigo + " modificar posicion " + (i+1) + " por un " + cambio2;
	    	       			z++;
	    	       		}
           			}
    	        }
           	}
        	
        }
        return correccion;
        //throw new UnsupportedOperationException("Not supported yet.");
    }

}
