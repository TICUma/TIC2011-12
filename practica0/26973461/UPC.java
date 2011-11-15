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
    // No hace falta!
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
            i++;
        }
        return codigoCorregido;
    }

    /**************
     * 
     * @param codigo
     * @return
     */
    public String generarCodigoControl(String codigo) {
        //throw new UnsupportedOperationException("Not supported yet.");
    	int lleno = codigo.length()==12 ? -1 : 0;
    	int dControl=digitoControl(codigo.substring(0, codigo.length()-lleno));
    	return codigo.substring(0, codigo.length()-lleno)+dControl;
    }

    
    public String[] corregirDatos(String codigo) {
    	//throw new UnsupportedOperationException("Not supported yet.");
    	int dControl = digitoControl(codigo);
    	LinkedList<Par> errores = new LinkedList<Par>();
    	LinkedList<String> posibles = new LinkedList<String>();
    	String res[];
    	
    	// Detecta los errores
    	if (!verificar(codigo)){
    		for(int i=1;i<=codigo.length();i++){
    			if (i%2!=0){ // IMPAR multiplican por 3
    				int n=(dControl*3 % MULTIPLO);
        			errores.add(new Par(i,-n));
        			errores.add(new Par(i,MULTIPLO-n));
    			}else{
        			errores.add(new Par(i,dControl));
        			errores.add(new Par(i,dControl-MULTIPLO));    				
    			}
    		}
    	}
    	
    	//Intenta aplicar cambios para sacar posibles combinaciones
    	int iesimo;
    	int nuevoValor;
    	String tmp;
    	for (Par error : errores){
    		iesimo=Integer.parseInt(codigo.substring(error.getPosicion()-1, error.getPosicion()));
    		nuevoValor = iesimo+error.getValor();
    		if ((nuevoValor>=0) && (nuevoValor<=9)){
    			tmp = codigo.substring(0, error.getPosicion()-1);
    			tmp += nuevoValor;
    			tmp += codigo.substring(error.getPosicion());
    			posibles.add(tmp);
    		}
    	}
    	
		res = new String[posibles.size()];
        int i=0;
        for (String posible : posibles){
        	res[i]=new String(posible);
        	i++;
        }
		return res;
    }
    
    // Dado un codigo, devuelve un entero que es su dígito de control
    private int digitoControl(String codigo){
        int resultado = 0;
        int v;
        try {
            for (int i = 0; i < codigo.length(); i++) {
                v = Integer.parseInt(codigo.substring(i, i+1));
                resultado += i%2 == 0 ? v*3 : v;
            }
        } catch (NumberFormatException e) {}
        return (MULTIPLO-(resultado % MULTIPLO));
    }
}
