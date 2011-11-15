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
public class NIF extends Codificacion {

	private static final int LENGTH = 9;
    private static final String NIF_TABLA = "TRWAGMYFPDXBNJZSQVHLCKE";

    @Override
    public boolean verificar(String nif) {
       int dni = Integer.parseInt(nif.substring(0, nif.length()-1));
       try {
           return NIF_TABLA.charAt(dni % NIF_TABLA.length()) == nif.charAt(nif.length()-1);
       } catch (NumberFormatException e) {
           return false;
       }
    }

    /*************
     * 
     * @param codigo
     * @return
     */
    public String generarCodigoControl(String codigo) {
       
       if (codigo!=null){
        int dni = Integer.parseInt(codigo, 10);
        return codigo+ NIF_TABLA.charAt(dni % NIF_TABLA.length());
       }
       else return null;
    }
    
    public String [] corregirDatos(String codigo) {
    	
    	if (codigo.length() != LENGTH || verificar(codigo)) {
    		return null;
    	}
        
    	String datos = codigo.substring(0, codigo.length() - 1);
    	char letraControl = codigo.charAt(codigo.length() - 1);
    	int moduloControl = NIF_TABLA.indexOf(letraControl);
    	
    	ArrayList<String> datosCorregidos = new ArrayList<String>();
    	
    	for (int index = 0; index < datos.length(); index++) {
    		for (int cifra = 0; cifra < 10; cifra++) {
    			String datosNuevos = datos.substring(0, index) + cifra;
    			if (index < datos.length() - 1) {
    				datosNuevos += datos.substring(index + 1);
    			}
    			if (Integer.parseInt(datosNuevos) % NIF_TABLA.length() == moduloControl) {
    				datosCorregidos.add(datosNuevos + letraControl);
    			}
    		}
    	}
    	
    	String [] datosArray = new String[datosCorregidos.size()];
    	datosCorregidos.toArray(datosArray);
    	return datosArray;
    	
    }
    
}
