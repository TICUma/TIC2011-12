/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package practica0;

import java.util.ArrayList;

/**
 *
 * @author monte
 */
public class ISBN13 extends Codificacion{

     private static final int LENGTH = 13;
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

    /**************
     * generarCifraControl genera cifra control de datos
     * @param codigo datos
     * @return cifra control
     */
    public int generarCifraControl(String codigo) {
    	
    	if (codigo.length() != LENGTH - 1) {
        	return -1;
        }

    	int sum = 0;
    	
    	for (int index = 0; index < codigo.length(); index++) {
    		int digit = Integer.parseInt(codigo.substring(index, index + 1));
    		sum += (index % 2 == 0) ? digit : 3 * digit;
    	}
    	
    	return (MODULO - (sum % MODULO)) % MODULO;
    	
    }

    public String[] corregirDatos(String codigo) {
    	
    	codigo = codigo.replaceAll("-", "");

    	if (codigo.length() != LENGTH) {
    		return null;
    	}
    	
    	int dadaCifraControl = Integer.parseInt(codigo.substring(codigo.length() - 1));
    	int generadaCifraControl = generarCifraControl(codigo.substring(0, codigo.length() - 1));
    	int diffControl = dadaCifraControl - generadaCifraControl;
    	
    	// no hay que corregir datos
    	if (diffControl == 0) return null;
    	
    	ArrayList<String> datosCorregidos = new ArrayList<String>();
    	
    	for (int index = 0; index < codigo.length() - 2; index++) {
    		
    		int digit = Integer.parseInt(codigo.substring(index, index + 1));
    		
    		if (index % 2 == 1) {
    			// posiciones pares, múltiples de 3
    			
    			// se repite desde 30
    			for (int i = 0; i < 3; i++) {
    				int newDiffControl = diffControl + MODULO * i;
    				if (newDiffControl % 3 == 0) {
    					int newDigit = digit - newDiffControl / 3;
    					if (newDigit < 0) newDigit = MODULO + newDigit;
    					if (newDigit > 9) newDigit = newDigit - MODULO;
    					datosCorregidos.add(codigo.substring(0, index) + newDigit + codigo.substring(index + 1));
    				}
    			}
    			
    		} else {
    			// posiciones impares
    			
	    		int newDigit = digit - diffControl;
	    		if (newDigit < 0) newDigit = MODULO + newDigit;
	    		if (newDigit > 9) newDigit = newDigit - MODULO;
	    		datosCorregidos.add(codigo.substring(0, index) + newDigit + codigo.substring(index + 1));
	    		
    		}

    	}
    	
    	String [] datosArray = new String[datosCorregidos.size()];
    	datosCorregidos.toArray(datosArray);
    	return datosArray;

    }
    
    /**************
     * corregirDatosFuerzaBrutal corrige una cifra de datos
     * que corresponda la cifra control, sirve para control
     * @param codigo datos con cifra control
     * @return la lista de codigos corregidos
     */
    public String[] corregirDatosFuerzaBrutal(String codigo) {
    	
    	codigo = codigo.replaceAll("-", "");
    	
    	ArrayList<String> datosCorregidos = new ArrayList<String>();
    	
    	for (int index = 0; index < codigo.length() - 2; index++) {
    		
    		for (int digit = 0; digit < 10; digit++) {
    		
    			String newCodigo = codigo.substring(0, index) + digit + codigo.substring(index + 1);
    			
    			if (!codigo.equals(newCodigo) && verificar(newCodigo)) {
    				datosCorregidos.add(newCodigo);
    			}
    		
    		}
    	}

    	String [] datosArray = new String[datosCorregidos.size()];
    	datosCorregidos.toArray(datosArray);
    	return datosArray;
    	
    }

}
