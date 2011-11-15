/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package practica0;

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


    
    @Override
    public String[] corregirDatos(String codigo) {
    	
    	codigo = codigo.replaceAll("-", "");
    	String[] resultado = new String[50];
    	int indiceResultado = 0;    	
    	char[] tmp = new char[50];
    	
    	tmp = codigo.toCharArray();
    	
    	int moduloObtenido = Integer.parseInt(this.generarCodigoControl(codigo.substring(0, codigo.length()-1)).charAt(codigo.length()-1)+"");
    	System.out.println("Modulo obtenido: "+moduloObtenido);
    	
    	int moduloCorrecto = Integer.parseInt(""+codigo.charAt(codigo.length()-1));
    	System.out.println("Modulo correcto: "+moduloCorrecto);

    	//Si el módulo que obtenemos es X, quiere decir que o nos faltan 11-X o nos sobran X.
    	if (moduloObtenido !=0){
    		for (int i=0;i<codigo.length();i++){
    			//Posibilidad de que sobre moduloObtenido... y sus divisores.
    			if (i+1==1){ //Añadimos moduloObtenido a la cantidad de 1's
    				if (Integer.parseInt(""+tmp[i])-moduloObtenido>=0){
    					tmp = codigo.toCharArray();
    					tmp[i] = Character.forDigit(Integer.parseInt(""+tmp[i])-moduloObtenido, 10);
    					resultado[indiceResultado] = String.copyValueOf(tmp);
    					indiceResultado++;
    				}
    			}else if (moduloObtenido%(i+1)==0){
    				if (Integer.parseInt(""+tmp[i])-moduloObtenido/(i+1)>=0){
    					tmp = codigo.toCharArray();
    					tmp[i] = Character.forDigit(Integer.parseInt(""+tmp[i])-moduloObtenido/(i+1), 10);
    					resultado[indiceResultado] = String.copyValueOf(tmp);
    					indiceResultado++;
    				}
    			}
    			
    			//Posibilidad de que falte (11-moduloObtenido)... y sus divisores.
    			if (i+1==1){ //Añadimos moduloObtenido a la cantidad de 1's
    				if (Integer.parseInt(""+tmp[i])+(11-moduloObtenido)<0){
    					tmp = codigo.toCharArray();
    					tmp[i] = Character.forDigit(Integer.parseInt(""+tmp[i])+(11-moduloObtenido), 10);
    					resultado[indiceResultado] = String.copyValueOf(tmp);
    					indiceResultado++;
    				}
    			}else if ((11-moduloObtenido)%(i+1)==0){
    				if (Integer.parseInt(""+tmp[i])-(11 - moduloObtenido)/(i+1)>=0){
    					tmp = codigo.toCharArray();
    					tmp[i] = Character.forDigit(Integer.parseInt(""+tmp[i])+(11-moduloObtenido)/(i+1), 10);
    					resultado[indiceResultado] = String.copyValueOf(tmp);
    					indiceResultado++;
    				}
    			}
    		}
    	}
    	
		System.out.println("Los posibles códigos ISBN correctos son:");

    	for (int i=0;i<resultado.length;i++){
    		if (resultado[i]!=null) System.out.println(resultado[i]);
    	}
    	
    	return resultado;		
    }


}

