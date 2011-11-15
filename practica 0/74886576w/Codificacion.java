/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package practica0;

import java.util.ArrayList;

public abstract class Codificacion {

    /**
     * Comprueba si el código es válido.
     *
     * @param codigo    Código a comprobar.
     * @return          Verdadero si es código es válido, falso en otro caso.
     */
    public abstract boolean verificar(String codigo);

    /**
     * Devuelve el código válido a partir del código incorrecto que recibe como parámetro.
     *
     * @param codigo    Código incorrecto.
     * @return          Código corregido.
     */
    
    public String corregirControl(String codigo){
         if (verificar(codigo)) return codigo;
         else return generarCodigoControl(codigo.substring(0, codigo.length()-1));
        
    };

    /****************
     * Genera el código control a partir de los datos
     * @param codigo
     * @return
     */
public abstract String generarCodigoControl(String codigo);

    /************
     * Supuestamente el código de control está bien buscar donde es el error.
     * @param codigo
     * @return
     */
    public String[] corregirDatos(String codigo){
        String[] res;
        //String[] res = new String[20];
        String prueba;
        ArrayList<String> lista = new ArrayList<String>();
        int orig;
        
        codigo = codigo.replaceAll("-", "");
        codigo = codigo.replaceAll(" ", "");
        
        if (verificar(codigo)){
            System.out.println("El código a corregir es correcto");
            return new String[0];
        }
                  
        for (int i=0;i<codigo.length()-1;i++){
            orig = Integer.parseInt(codigo.substring(i, i+1));
            for (int j=0;j<10;j++){
                prueba=codigo;
                prueba=prueba.substring(0, i)+j+prueba.substring(i+1,codigo.length());
                if ((j!=orig)&&(verificar(prueba))){
                    lista.add(prueba);
                    //System.out.println("Añadido el codigo:"+prueba);
                }
            }   
        }
        
        res=new String[lista.size()];
        lista.toArray(res);
        //System.out.println("Longitud de res: "+res.length);
        return res;
        
    }
    
}
