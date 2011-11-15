/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package practica0;

import java.util.ArrayList;

/**
 *
 * @author 
 */
public class UPC extends Codificacion {

	private static final int LENGTH = 12;
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
    		sum += (index % 2 == 0) ? 3 * digit : digit;
    	}
    	
    	return (MULTIPLO - (sum % MULTIPLO)) % MULTIPLO;
    	
    }

    /**************
     * generarCodigoControl genera codigo control de datos
     * @param codigo datos
     * @return datos con cifra control
     */
    public String generarCodigoControl(String codigo) {
    	
    	return codigo + String.valueOf(generarCifraControl(codigo));
    	
    }

    /**************
     * corregirDatos corrige una cifra de datos que la cifra control corresponda
     * @param codigo datos con cifra control
     * @return la lista de codigos corregidos
     */
    public String[] corregirDatos(String codigo) {
    	
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
    		
    		if (index % 2 == 0) {
    			// posiciones pares, múltiples de 3
    			
    			// se repite desde 30
    			for (int i = 0; i < 3; i++) {
    				int newDiffControl = diffControl + MULTIPLO * i;
    				if (newDiffControl % 3 == 0) {
    					int newDigit = digit - newDiffControl / 3;
    					if (newDigit < 0) newDigit = MULTIPLO + newDigit;
    					if (newDigit > 9) newDigit = newDigit - MULTIPLO;
    					datosCorregidos.add(codigo.substring(0, index) + newDigit + codigo.substring(index + 1));
    				}
    			}
    			
    		} else {
    			// posiciones impares
    			
	    		int newDigit = digit - diffControl;
	    		if (newDigit < 0) newDigit = MULTIPLO + newDigit;
	    		if (newDigit > 9) newDigit = newDigit - MULTIPLO;
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
