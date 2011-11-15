/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 * 
 * JORGE PEREZ RUIZ 
 * ATA 2011 Ingeniera Informática
 * 
 */

// http://www.taringa.net/posts/noticias/2523742/Pequena-maravilla-de-las-matematicas_-el-codigo-ISBN.html
// [6520681865, 8420681865, 8550681865, 8525681865, 8520641865, 8520687865, 8520681565, 8520681875]
// 85-206-8186-5

package practica0;

import java.util.ArrayList;

/**
 *
 * @author 
 */
public class ISBN extends Codificacion {

    private static final int MODULO = 11;
    private static final int TAMDATOS = 9;

    /*************
     * 
     * @param codigo
     * @return
     */
    @Override
    public boolean verificar(String codigo) {
        codigo = codigo.replaceAll("-", "");
        int resultado = 0;
        try {
            for (int i = 0; i < codigo.length(); i++) {
                // Si el modulo es 10. Entonces el codigo de control es X
                if (codigo.substring(i, i+1).equalsIgnoreCase("X"))
                    resultado += 10*(i+1);
                else
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
        String res;
        int resultado = 0;

        try {
            for (int i = 0; i < codigo.length(); i++) {
                resultado += (Integer.parseInt(codigo.substring(i, i+1))*(i+1));
            }
        } catch (NumberFormatException e) {
            return null;
        }
        resultado = resultado % MODULO;
        if (resultado > 9)
            res = codigo+"X";
        else
            res = codigo+(resultado% MODULO);
        
        return res;
    }

    /*
     * Se le pasa el codigo con el codigo de control correcto.
    */  
    @Override
    public String[] corregirDatos(String codigo) {
        
        ArrayList<String> resultado = new ArrayList<String>();
        codigo = codigo.replaceAll("-", "");
        String cad;
        
        /* Para cada numero vemos todos los posibles valores y comprobamos*/
        for (int i = 0; i < TAMDATOS;i++){
            
            for (int j = 0; j < 10; j++){
                /*Vemos los valores y si alguno nos da la letra del DNI, lo metemos*/
                cad = cambiarValorChar(codigo, i, j);
                if (verificar(cad)){
                    resultado.add(cad);
                }
            }
        }
        System.out.println(resultado);
        String[] res = new String[resultado.size()];
        for (int i = 0; i < resultado.size(); i++)
            res[i] = resultado.get(i);
        return res;
    }
    
    public static int codigoControl(String codigo, int tamDatos)
    {
        return Integer.parseInt(codigo.substring(TAMDATOS));
    }
    
    public static String cambiarValorChar(String codigo, int indice, int valor)
    {
        String pre = "", suf = "";
        if (indice >= codigo.length()){
            System.out.println("Error de indice "+ indice);
            return codigo;
        }
        if (indice > 0)
            pre = codigo.substring(0, indice);
        if (indice <= codigo.length()-1)
            suf = codigo.substring(indice+1);
        return pre + valor + suf;
    }
}

