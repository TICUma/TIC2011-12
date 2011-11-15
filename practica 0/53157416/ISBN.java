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
public class ISBN extends Codificacion {

    private static final int MODULO = 11;

/*************
 * 
 * @param codigo
 * @return
 */
    public boolean verificar(String codigo) {
        codigo = codigo.replaceAll("-", "");
        //Primero comprobamos si realmente tiene 10 digitos
        if (codigo.length() != 10) 
        //no tiene 10 digitos por lo tanto no es isbn correcto
        return false;
        
        else{
        //tiene 10 digitos comprobamos si es mod 11
            
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
        //Primero comprobamos su mod 11
        
    	  	
        codigo = codigo.replaceAll("-", "");
             
           
        int resultado = 0;
        try {
            for (int i = 0; i < codigo.length(); i++) {
                resultado += Integer.parseInt(codigo.substring(i, i+1))*(i+1);
            }
        } catch (NumberFormatException e) {
           // return false;
        }
        
       resultado = resultado % MODULO;
       
       if (resultado !=0){
       
       int aumento;
       LinkedList<String> list = new LinkedList<String>(); 
       int t1 = MODULO - resultado;
       int t2 = resultado;
       int actual;
       
       
       for (int i=0;i<codigo.length()-1;i++){
    	   actual=Integer.parseInt(String.valueOf(codigo.substring(i,i+1)));
    	   if (t1 % (i+1)==0){
    		   aumento = t1/(i+1);
    		   if(actual+aumento<10){
    		   list.add(codigo.substring(0,i)+Integer.toString(actual+aumento)
    		   +codigo.substring(i+1,codigo.length()));
    		   }
    	   }
       }
       
       for (int i=0;i<codigo.length()-1;i++){
    	   actual=Integer.parseInt(String.valueOf(codigo.substring(i,i+1)));
    	   if (t2% (i+1)==0){
    		   aumento = t2/(i+1);
    		   if(actual-aumento>0){
    		   list.add(codigo.substring(0,i)+Integer.toString(actual-aumento)
    		   +codigo.substring(i+1,codigo.length()));
    		   }
    	   }
       }
    
       return list.toArray(new String[0]);
    }else{
    	String[] s= new String[1];
    	s[0]=codigo;
       return s;
    }
    
    } 

}

