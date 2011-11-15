/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package practica0;

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
        System.out.println("Codigo corregido: "+codigoCorregido);
        return codigoCorregido;
    }

    /**************
     * 
     * @param codigo
     * @return
     */
    public String generarCodigoControl(String codigo) {
    	int suma=0;
    	int modulo=0;
    	char[] codigoArray = codigo.toCharArray();
    	for (int i=0;i<codigo.length();i++){
    		if ((i%2)==0){    			
    			suma += Integer.parseInt(""+codigoArray[i])*3;
    		}else{    			
    			suma += Integer.parseInt(""+codigoArray[i]);
    		}
    	}
    	modulo=10-suma%10;    	
    	codigo=codigo+modulo;
    	return codigo;
    }

    
    public String[] corregirDatos(String codigo) {
    	int suma=0;
    	int modulo=0,moduloTemp;
    	
    	String[] resultado = new String[100];
    	int indiceResultado = 0;
    	
    	char[] codigoArray = codigo.toCharArray();
    	for (int i=0;i<codigo.length();i++){
    		if ((i%2)==0){    			
    			suma += Integer.parseInt(""+codigoArray[i])*3;
    		}else{    			
    			suma += Integer.parseInt(""+codigoArray[i]);
    		}
    	}
    	modulo=suma%10; //Esto es lo que nos falta o nos sobra.
    	
    	if (modulo==0) return null;
    	
    	System.out.println("Modulo obtenido: "+modulo);
    	
    	
    	for (int i=0;i<codigo.length()-1;i++){
    		//Primero, la posibilidad de que sobre modulo
    		//*******************************************
    		codigoArray = codigo.toCharArray();
    		if (modulo%3==0){ //El modulo que sobra es multiplo de 3
    			if (i%2==0 && Integer.parseInt(""+codigoArray[i]) - modulo/3 >=0){
    				//Si estamos en una posicion de peso 3 y nuestro modulo es multiplo de 3
    				//Restamos modulo/3 (si el resultado es >=0)    				
    				codigoArray[i] = Character.forDigit(Integer.parseInt(""+codigoArray[i]) - modulo/3,10);  
    				resultado[indiceResultado]=String.copyValueOf(codigoArray);
    				indiceResultado++;
    			}else if (i%2!=0 && Integer.parseInt(""+codigoArray[i]) - modulo>=0){ 
    				//Si es una posicion de peso 1 y el resultado es >=0
    				//Restamos modulo    				
    				codigoArray[i] = Character.forDigit(Integer.parseInt(""+codigoArray[i]) - modulo,10);
    				resultado[indiceResultado]=String.copyValueOf(codigoArray);
    				indiceResultado++;
    			}
    		}else{ //Si modulo no es multiplo de 3...
    			if (i%2!=0 && Integer.parseInt(""+codigoArray[i]) - modulo>=0){ //Si estamos en una posicion de peso 1...
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
    			if (i%2==0 && Integer.parseInt(""+codigoArray[i]) + moduloTemp/3 <10){
    				//Si estamos en una posicion de peso 3 y nuestro modulo es multiplo de 3
    				//Sumamos modulo/3 (si el resultado es <10)    				
    				codigoArray[i] = Character.forDigit(Integer.parseInt(""+codigoArray[i]) + moduloTemp/3,10);  
    				resultado[indiceResultado]=String.copyValueOf(codigoArray);
    				indiceResultado++;
    			}else if (i%2!=0 && Integer.parseInt(""+codigoArray[i]) + moduloTemp <10){ 
    				//Si es una posicion de peso 1 y el resultado es <10
    				//Sumamos modulo    				
    				codigoArray[i] = Character.forDigit(Integer.parseInt(""+codigoArray[i]) + moduloTemp,10);
    				resultado[indiceResultado]=String.copyValueOf(codigoArray);
    				indiceResultado++;
    			}
    		}else{ //Si modulo no es multiplo de 3...
    			if (i%2!=0 && Integer.parseInt(""+codigoArray[i]) + moduloTemp <10){ //Si estamos en una posicion de peso 1...
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
