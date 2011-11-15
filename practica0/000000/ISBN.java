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
public class ISBN extends Codificacion {

	private static final int LENGTH = 10;
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
    		sum += digit * (index + 1);
    	}
    	
    	return sum % MODULO;
    	
    }

    /**************
     * corregirDatos corrige una cifra de datos que la cifra control corresponda
     * @param codigo datos con cifra control
     * @return la lista de codigos corregidos
     */
    @Override
    public String[] corregirDatos(String codigo) {
    	
    	codigo = codigo.replaceAll("-", "");
        
    	if (codigo.length() != LENGTH) {
    		return null;
    	}
    	
    	String s = codigo.substring(codigo.length() - 1);
    	int dadaCifraControl = (s.toLowerCase() == "x") ? 10 : Integer.parseInt(s);
    	int generadaCifraControl = generarCifraControl(codigo.substring(0, codigo.length() - 1));
    	
    	int correction1 = dadaCifraControl - generadaCifraControl;
    	if (correction1 == 0) return null;
       	int correction2 = (correction1 < 0) ? correction1 + MODULO : correction1 - MODULO;
    	if (correction1 < correction2) {
    		int swap = correction2; correction2 = correction1; correction1 = swap;
    	}
    	
    	ArrayList<String> datosCorregidos = new ArrayList<String>();
    	
    	for (int index = 0; index < codigo.length() - 2; index++) {
    		
    		int digit = Integer.parseInt(codigo.substring(index, index + 1));
    		
			for (int i = 0; i < index + 1; i++) {
				
				int newCorrection1 = correction1 + MODULO * i;
				if (newCorrection1 % (index + 1) == 0) {
					int newDigit = digit + newCorrection1 / (index + 1);
					if (newDigit <= 9) {
						datosCorregidos.add(codigo.substring(0, index) + newDigit + codigo.substring(index + 1));
					}
				}
				
				int newCorrection2 = correction2 - MODULO * i;
				if (newCorrection2 % (index + 1) == 0) {
					int newDigit = digit + newCorrection2 / (index + 1);
					if (newDigit >= 0) {
						datosCorregidos.add(codigo.substring(0, index) + newDigit + codigo.substring(index + 1));
					}
				}
				
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

