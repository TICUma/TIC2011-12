/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package practica0;

/**
 *
 * @author 
 */
public class ISBN extends Codificacion {

    private static final int MODULO = 11;

/*************
 * 
 * @param codigo
 * @return
 */
    public boolean verificar(String codigo) {
        codigo = codigo.replaceAll("-", "");
        int resultado = 0;
        try {
            for (int i = 0; i < codigo.length(); i++) {
                resultado += Integer.parseInt(codigo.substring(i, i+1))*(i+1);
            }
        } catch (NumberFormatException e) {
            return false;
        }
        return resultado % MODULO == 0;
    }

/***************
 * 
 * @param codigo
 * @return
 */
    @Override
    public String generarCodigoControl(String codigo) {

     codigo = codigo.replaceAll("-", "");

     int resultado = 0;

        try {
            for (int i = 0; i < codigo.length(); i++) {
                resultado += (Integer.parseInt(codigo.substring(i, i+1))*(i+1));
            }
        } catch (NumberFormatException e) {
            return null;
        }
        return codigo+(resultado% MODULO);
    }


    
    @Override
    public String[] corregirDatos(String codigo) {
    	//Suponiendo que el codigo de control está bien
    
    	String[] correccion = new String[10];
    	codigo = codigo.replaceAll("-", "");
      
        int resultado = 0;
        //Calculo el resultado sin tener en cuenta el dígito de control
        for (int i = 0; i < codigo.length()-1; i++) {
            resultado += (Integer.parseInt(codigo.substring(i, i+1))*(i+1));
        }
        //Calculo el dígito de control
        int control = (Integer.parseInt(codigo.substring(9,10)));
        
        if ((resultado + control*10) % MODULO !=0){
        	/*Si el dígito de control no coincide quiere decir que algún bit está mal en el código restante
        	 * calculo entonces el modulo que sale de la suma de digitos x1+x2+...x9 mod 11 = miModulo
        	 */
        	int miModulo= resultado % MODULO;
        	
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
    	       	if (diferencia1 % (i+1) == 0){
    	       		/*Saco los valores divisibles por la diferencia que serán las posiciones que pueden sumar
    	       		 * o restar para ajustar el resultado.
    	       		 */
    	       		cambio1 = (Integer.parseInt(codigo.substring(i,i+1)) - (diferencia1/(i+1)));
    	       		if(cambio1 > 9){
    	       			
    	       		}else{
    	       			correccion[j] = "\nCodigo: " + codigo + " modificar posicion " + (i+1) + " por un " + cambio1;
    	       			j++;
    	       		}
    	 
    	        }
           	}
        
        	int z= j;
           	int cambio2 = 0;
            	
           	for (int i=0;i<codigo.length()-1;i++){
    	      	if (diferencia2 % (i+1) == 0){
    	        		
    	      		cambio2 = (Integer.parseInt(codigo.substring(i,i+1)) + (diferencia2/(i+1)));
    	      		if(cambio2 > 9){
    	       			
    	       		}else{
	    	      		correccion[z] = "\nCodigo: " + codigo + " modificar posicion " + (i+1) + " por un " + cambio2;
	    	      		z++;
    	       		}
    	       	}
           	}
        	
        }
        return correccion;
        //throw new UnsupportedOperationException("Not supported yet.");
        
    }

}

