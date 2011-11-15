/*
* To change this template, choose Tools | Templates
* and open the template in the editor.
*/

package practica0;

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

    //Carlos Albaladejo Pérez
    //17/10/2011
    
    public String [] corregirDatos(String codigo) {
    	
    	String[] posiblesValores = new String[90];
    	int indicePosiblesValores = 0;
    	
    	//codigo = "14886229T"; //Mi DNI incorrecto para probar. (74886229T)
    	String cadenaPrueba;
    	//Suponiendo que el código de control (la letra) está bien.
    	int moduloQueDeberia = NIF_TABLA.indexOf(codigo.charAt(codigo.length()-1));
    	int moduloQueTiene = Integer.parseInt(codigo.substring(0, codigo.length()-1)) % 23;
    	
    	System.out.println("Módulo correcto: "+moduloQueDeberia+" - Modulo actual: "+moduloQueTiene);
    	
    	if (moduloQueDeberia == moduloQueTiene){
        	System.out.println("El código es correcto.");
    	}else{
        	System.out.println("El código es incorrecto.Suponiendo UN solo caracter erróneo:");
        	
        	//Prueba todas las posibles combinaciones poniendo números del 0 al 9 en cada posición
        	//y comprobando si el módulo es correcto, es MUY ineficiente pero no se me ha ocurrido
        	//otra forma...
        	
        	for (int i=1;i<9;i++){
        		for (int j=0;j<10;j++){
        			cadenaPrueba = codigo.substring(0, i-1)+j+codigo.substring(i,codigo.length()-1);
        			moduloQueTiene = Integer.parseInt(cadenaPrueba) % 23;
        			if (moduloQueTiene == moduloQueDeberia){
        				posiblesValores[indicePosiblesValores] = cadenaPrueba + codigo.charAt(8);
        				indicePosiblesValores++;
        			}
        		}
        	}
        	System.out.println("Valores válidos para este DNI son:");

        	for (int i=0;i<indicePosiblesValores;i++){
        		System.out.println("Valor "+(i+1)+": "+posiblesValores[i]);
        	}
    	}
    	
		
    	
    	return posiblesValores;
   }
}
