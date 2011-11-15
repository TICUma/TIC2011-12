/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 * 
 * JORGE PEREZ RUIZ 
 * ATA 2011 Ingeniera Informática
 * 
 */

package practica0;

import java.util.ArrayList;

/**
 *
 * @author 
 */
public class UPC extends Codificacion {

    private static final int MULTIPLO = 10;
    private static final int TAMDATOS = 12;

/**********************
 * 
 * @param codigo
 * @return
 */
    @Override
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
    
    @Override
    public String corregirControl(String codigo) {
        int i = 0;
        boolean ok = false;
        String codigoCorregido = new String();

        while (i < codigo.length() && !ok) {
            int digito = 0;
            while (digito < 9 && !ok) {
                codigoCorregido = codigo.substring(0, i-1) + digito + codigo.substring(i+1, codigo.length());
                if (verificar(codigoCorregido)) {
                    ok = true;
                } else {
                    digito++;
                }
            }
        }
        return codigoCorregido;
    }

    /**************
     * 
     * @param codigo
     * @return
     */
    @Override
    public String generarCodigoControl(String codigo) {
        codigo = codigo.replaceAll("-", "");
        
        // Igual que en ISBN13
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

        resultado = resultado % MULTIPLO;
        resultado = (MULTIPLO -resultado)%MULTIPLO;
        return codigo+resultado;
    }

    
    @Override
    public String[] corregirDatos(String codigo) {
        ArrayList<String> resultado = new ArrayList<String>();
        codigo = codigo.replaceAll("-", "");
        String cad;
        
        /* Para cada numero vemos todos los posibles valores y comprobamos*/
        for (int i = 0; i < TAMDATOS;i++){
            for (int j = 0; j < 10; j++){
                /*Vemos los valores y si alguno nos da la letra del DNI, lo metemos*/
                cad = ISBN.cambiarValorChar(codigo, i, j);
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

}
