/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package practica0;

import java.util.*;
/**
 *
 * @author Francisco José Martínez
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
				resultado += (Integer.parseInt(codigo.substring(i, i + 1)) * (i + 1));
			}
		} catch (NumberFormatException e) {
			return null;
		}
		return codigo + (resultado % MODULO);
	}

    
    /* (non-Javadoc)
     * @see practica0.Codificacion#corregirDatos(java.lang.String)
     */
    @Override
    // Suponemos que solo corrige un fallo y da las posibles soluciones
    public String[] corregirDatos(String codigo) {
        //throw new UnsupportedOperationException("Not supported yet.");
		codigo = codigo.replaceAll("-", "");

        ArrayList<String> posibles;
        LinkedList<Par> pares;
        
        String tmp;
        posibles = new ArrayList<String>();
        int iesimo;
        
        if (verificar(codigo)){
        	//posibles.add(codigo);
        	// o vacío?
        }else{
        	// Copia la Cadena y la transforma en un Array de carcteres
        	// coge el ultimo digito, que es el de control y lo pasa a entero
        	int control = Integer.parseInt(generarCodigoControl(codigo).substring(codigo.length())); 
        	pares = errores(control);

        	for(Par par : pares){
        		iesimo = Integer.parseInt(codigo.substring(par.getPosicion()-1, par.getPosicion()));
        		if ((iesimo+par.getValor()<=9)&&(iesimo+par.getValor()>=0)){
        			tmp = codigo.substring(0, par.getPosicion()-1);
        			tmp += (iesimo+par.getValor());
        			tmp += codigo.substring(par.getPosicion());
        			posibles.add(tmp);
        		}
        	}
        }
        
        String res[];
        res= new String[posibles.size()];
        int i=0;
        for (String posible : posibles){
        	res[i]=new String(posible);
        	i++;
        }
        return res;
    }

    private LinkedList<Par> errores(int numero){
    	LinkedList<Par> res = new LinkedList<Par>();

    	switch(numero){
    	case 0:
    		break;
    	case 1:
    		res.add(new Par(1,-1)); // Sobra 1
    		res.add(new Par(10,1)); // faltan 10 No PUEDO SUMAR 10!!
    		break;
    	case 2:
    		// Sobran 2
    		res.add(new Par(2,-1));
    		res.add(new Par(1, -2));
    		// Faltan 9
    		res.add(new Par(9,1));
    		res.add(new Par(3,3));
    		res.add(new Par(1,9));
    		break;
    	case 3:
    		// Sobran 3
    		res.add(new Par(3,-1));
    		res.add(new Par(1,-3));
    		// Faltan 8
    		res.add(new Par(8,1));
    		res.add(new Par(1,8));
    		res.add(new Par(2,3));
    		break;
    	case 4:
    		// Sobran 4
    		res.add(new Par(4,-1));
    		res.add(new Par(2,-2));
    		res.add(new Par(1,-4));
    		// Faltan 7
    		res.add(new Par(7,1));
    		res.add(new Par(1,7));
    		break;
    	case 5:
    		// Sobran 5
    		res.add(new Par(5,-1));
    		res.add(new Par(1,-5));
    		// Faltan 6
    		res.add(new Par(6,1));
    		res.add(new Par(1,6));
    		break;
    	case 6:
    		// Sobran 6
    		res.add(new Par(6,-1));
    		res.add(new Par(1,-6));
    		// Faltan 5
    		res.add(new Par(5,1));
    		res.add(new Par(1,5));
    		break;
    	case 7:
    		// Sobran 7
    		res.add(new Par(7,-1));
    		res.add(new Par(1,-7));
    		// Faltan 4
    		res.add(new Par(4,1));
    		res.add(new Par(2,2));
    		res.add(new Par(1,4));
    		break;
    	case 8:
    		// Sobran 8
    		res.add(new Par(8,-1));
    		res.add(new Par(1,-8));
    		res.add(new Par(2,-3));
    		// Faltan 3
    		res.add(new Par(3,1));
    		res.add(new Par(1,3));
    		break;
    	case 9:
    		// Sobran 9
    		res.add(new Par(9,-1));
    		res.add(new Par(3,-3));
    		res.add(new Par(1,-9));
    		// Faltan 2
    		res.add(new Par(1,2));
    		res.add(new Par(2,1));
    	case 10:
    		// Sobran 10
    		res.add(new Par(10,-1));
    		// Faltan 1
    		res.add(new Par(10,1));
    		break;
    	default:
    		break;
    	}
    	return res;
    }
}
