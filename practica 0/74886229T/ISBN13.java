/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package practica0;

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


    public String[] corregirDatos(String codigo) {
    	
        codigo = codigo.replaceAll("-", "");

        System.out.println(codigo);
        
    	int suma=0;
    	int modulo=0,moduloTemp;
    	
    	String[] resultado = new String[100];
    	int indiceResultado = 0;
    	
    	char[] codigoArray = codigo.toCharArray();
    	for (int i=0;i<codigo.length();i++){
    		if ((i%2)==0){    			
    			suma += Integer.parseInt(""+codigoArray[i]);
    		}else{    			
    			suma += Integer.parseInt(""+codigoArray[i]) * 3;
    		}
    	}
    	modulo=suma%10; //Esto es lo que nos falta o nos sobra.
    	
    	System.out.println("Suma obtenida: "+suma+" - Modulo obtenido: "+modulo);

    	
    	if (modulo==0) return null;
    	
    	
    	
    	for (int i=0;i<codigo.length()-1;i++){
    		//Primero, la posibilidad de que sobre modulo
    		//*******************************************
    		codigoArray = codigo.toCharArray();
    		if (modulo%3==0){ //El modulo que sobra es multiplo de 3
    			if (i%2!=0 && Integer.parseInt(""+codigoArray[i]) - modulo/3 >=0){
    				//Si estamos en una posicion de peso 3 y nuestro modulo es multiplo de 3
    				//Restamos modulo/3 (si el resultado es >=0)    				
    				codigoArray[i] = Character.forDigit(Integer.parseInt(""+codigoArray[i]) - modulo/3,10);  
    				resultado[indiceResultado]=String.copyValueOf(codigoArray);
    				indiceResultado++;
    			}else if (i%2==0 && Integer.parseInt(""+codigoArray[i]) - modulo>=0){ 
    				//Si es una posicion de peso 1 y el resultado es >=0
    				//Restamos modulo    				
    				codigoArray[i] = Character.forDigit(Integer.parseInt(""+codigoArray[i]) - modulo,10);
    				resultado[indiceResultado]=String.copyValueOf(codigoArray);
    				indiceResultado++;
    			}
    		}else{ //Si modulo no es multiplo de 3...
    			if (i%2==0 && Integer.parseInt(""+codigoArray[i]) - modulo>=0){ //Si estamos en una posicion de peso 1...
    				//Si es una posicion de peso 1 y el resultado es >=0
    				//Restamos modulo
    				codigoArray[i] = Character.forDigit(Integer.parseInt(""+codigoArray[i]) - modulo,10);
    				resultado[indiceResultado]=String.copyValueOf(codigoArray);
    				indiceResultado++;
    			}
    		}
    		//Ahora, la posibilidad de que falte 10-modulo.
    		moduloTemp = 10 - modulo;
    		codigoArray = codigo.toCharArray();
    		
    		if (moduloTemp%3==0){ //El modulo que falta es multiplo de 3
    			if (i%2!=0 && Integer.parseInt(""+codigoArray[i]) + moduloTemp/3 <10){
    				//Si estamos en una posicion de peso 3 y nuestro modulo es multiplo de 3
    				//Sumamos modulo/3 (si el resultado es <10)    				
    				codigoArray[i] = Character.forDigit(Integer.parseInt(""+codigoArray[i]) + moduloTemp/3,10);  
    				resultado[indiceResultado]=String.copyValueOf(codigoArray);
    				indiceResultado++;
    			}else if (i%2==0 && Integer.parseInt(""+codigoArray[i]) + moduloTemp <10){ 
    				//Si es una posicion de peso 1 y el resultado es <10
    				//Sumamos modulo    				
    				codigoArray[i] = Character.forDigit(Integer.parseInt(""+codigoArray[i]) + moduloTemp,10);
    				resultado[indiceResultado]=String.copyValueOf(codigoArray);
    				indiceResultado++;
    			}
    		}else{ //Si modulo no es multiplo de 3...
    			if (i%2==0 && Integer.parseInt(""+codigoArray[i]) + moduloTemp <10){ //Si estamos en una posicion de peso 1...
    				//Si es una posicion de peso 1 y el resultado es <10
    				//Sumamos modulo
    				codigoArray[i] = Character.forDigit(Integer.parseInt(""+codigoArray[i]) + moduloTemp,10);
    				resultado[indiceResultado]=String.copyValueOf(codigoArray);
    				indiceResultado++;
    			}
    		}
    		
    	}   	
    
    	System.out.println("Posibles codigos correctos:");
    	for (int i=0;i<resultado.length;i++){
    		if (resultado[i]!=null) System.out.println(i+"- "+resultado[i]);
    	}
    	return resultado;
    	
    }

}
