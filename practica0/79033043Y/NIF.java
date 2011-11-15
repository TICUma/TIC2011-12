/*
* To change this template, choose Tools | Templates
* and open the template in the editor.
*/

package practica0;

import java.util.ArrayList;

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
    @Override
    public String generarCodigoControl(String codigo) {
       
       if (codigo!=null){
            /*Segundo parámetro es parseInt es la base numerica*/
            int dni = Integer.parseInt(codigo, 10);
            return codigo+ NIF_TABLA.charAt(dni % NIF_TABLA.length());
       }
       else return null;
    }
    
    private char generarCodigoControlChar(String codigo) {
       if (codigo!=null){
            /*Segundo parámetro es parseInt es la base numerica*/
            int dni = Integer.parseInt(codigo, 10);
            return NIF_TABLA.charAt(dni % NIF_TABLA.length());
       }
       else{
           System.out.println("Ha entrado con codigo null. ERROR.");
           return ' ';
       }
    }

    /*El codigo se le pasa sin el cod control*/
    @Override
    public String [] corregirDatos(String codigo) {
        ArrayList<String> resultado = new ArrayList<String>();
        int indice = 0;
        String aux;
//        int dni = Integer.parseInt(codigo, 10);
        String numeros = codigo.substring(0, codigo.length()-1);
        String codControl = codigo.substring(codigo.length()-1);
        char[] cod = numeros.toCharArray();
        char auxchar;
        
        System.out.println(numeros);
        System.out.println(codControl);
        System.out.println(cod);
        
//        System.out.println(cod[0]);
        
        /* Para cada numero vemos todos los posibles valores y comprobamos*/
        for (int i = 0; i < numeros.length();i++){
            auxchar = cod[i];
            for (int j = 0; j < 10; j++){
                /*Vemos los valores y si alguno nos da la letra del DNI, lo metemos*/
                cod[i] = Integer.toString(j).charAt(0);
                aux = this.generarCodigoControl(new String(cod));
//                System.out.println(aux);
                if (codControl.equalsIgnoreCase(aux.substring(cod.length))){
                    resultado.add(new String(cod));
                    indice++;
                }
            }
            cod[i] = auxchar;
        }
        System.out.println(resultado);
        String[] res = new String[resultado.size()];
        for (int i = 0; i < resultado.size(); i++)
            res[i] = resultado.get(i);
        return res;
    }
}
