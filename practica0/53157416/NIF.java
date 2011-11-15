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
        char letra= codigo.charAt(codigo.length()-1);
       // int sdni = Integer.parseInt(codigo.substring(0, codigo.length()-1))%23;
        boolean noencontrado=true;
        int indice=0;
        while(noencontrado){
           if (NIF_TABLA.charAt(indice) == letra) noencontrado =false;
           else indice++;
           
        }
        int letraReal=Integer.parseInt(codigo.substring(0,codigo.length()-1)) % NIF_TABLA.length();
        if (indice!=letraReal)
        {
        String valor="";
        LinkedList<String> ss = new LinkedList<String>();
        for(int i=0;i<8;i++){
        	for (int j=0;j<10;j++){
        	valor = codigo.substring(0,i)+String.valueOf(j)+codigo.substring(i+1,codigo.length()-1);
        	if(Integer.parseInt(valor)% NIF_TABLA.length() == indice){
        		ss.add(valor);
        	}
        	}
        }
        
        return ss.toArray(new String[0]);
        }
        else return new String[0];
        
    }
}
