/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package practica0;

import java.util.LinkedList;

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
				if (i % 2 == 0)
					resultado += (Integer.parseInt(codigo.substring(i, i + 1)) * 1);
				else
					resultado += (Integer.parseInt(codigo.substring(i, i + 1)) * 3);
			}
		} catch (NumberFormatException e) {
			return null;
		}

		resultado = resultado % MODULO;
		resultado = (MODULO - resultado) % MODULO;
		return codigo + resultado;
	}

//    public String[] corregirDatos(String codigo) {
//        throw new UnsupportedOperationException("Not supported yet.");
//    }
    
    public String[] corregirDatos(String codigo) {
        codigo = codigo.replaceAll("-", "");
    	//throw new UnsupportedOperationException("Not supported yet.");
    	int dControl = desviacion(codigo);
    	LinkedList<Par> errores = new LinkedList<Par>();
    	LinkedList<String> posibles = new LinkedList<String>();
    	String res[];
    	
    	// Detecta los errores
    	if (dControl != 0){
    		for(int i=1;i<=codigo.length();i++){
    			if (i%2!=0){ // IMPAR multiplican por 3
    				int n=(dControl*3 % MODULO);
        			errores.add(new Par(i,n));
        			errores.add(new Par(i,n-MODULO));
    			}else{
        			errores.add(new Par(i,dControl));
        			errores.add(new Par(i,dControl-MODULO));    				
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
    private int desviacion(String codigo){
        codigo = codigo.replaceAll("-", "");

        int resultado = 0;
        int v;
        try {
            for (int i = 0; i < codigo.length(); i++) {
                v = Integer.parseInt(codigo.substring(i, i+1));
                resultado += i%2 == 0 ? v*3 : v;
            }
        } catch (NumberFormatException e) {}
        return resultado % MODULO;
    }

}
