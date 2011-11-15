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
public class NIF extends Codificacion {


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
//        throw new UnsupportedOperationException("Not supported yet.");
    	String res[];

    	LinkedList<String> posibles = new LinkedList<String>();
//    	char letraOrigen = codigo.charAt(codigo.length()-1);
//    	int indiceLetraOrigen = NIF_TABLA.indexOf(letraOrigen);
//    	int indiceLetraNueva = Integer.parseInt(codigo.substring(0,codigo.length()-1));
//    	char letraNueva = NIF_TABLA.charAt(indiceLetraNueva);
 
    	
    	if(!verificar(codigo)){
//    		int diferencia = indiceLetraNueva-indiceLetraOrigen;
    		String tmp;
    		// Fuerza bruta
    		for(int i=0; i<codigo.length()-1;i++){
    			for(int j=0; j<10;j++){
    				tmp=codigo.substring(0,i);
    				tmp=tmp+j;
    				tmp+=codigo.substring(i+1);
    				if(verificar(tmp)) posibles.add(tmp);    				
    			}
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
}
