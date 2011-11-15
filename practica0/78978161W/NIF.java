/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package practica0;

import java.util.TreeSet;

/**
 *
 * @author 
 */
public class NIF extends Codificacion {
    
    private static final String NIF_TABLA = "TRWAGMYFPDXBNJZSQVHLCKE";
    
    @Override
    public boolean verificar(String nif) {
        int dni = Integer.parseInt(nif.substring(0, nif.length() - 1));
        try {
            return NIF_TABLA.charAt(dni % NIF_TABLA.length()) == nif.charAt(nif.length() - 1);
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
        
        if (codigo != null) {
            int dni = Integer.parseInt(codigo, 10);
            return codigo + NIF_TABLA.charAt(dni % NIF_TABLA.length());
        } else {
            return null;
        }
    }
    
    @Override
    public String[] corregirDatos(String codigo) {
        TreeSet<String> resultado = new TreeSet<String>();
        codigo = codigo.replaceAll("-", "");
        String nuevoNIF;
        
        for (int i = 0; i < codigo.length() - 1; i++) {
            for (int j = 0; j < 10; j++) {
                nuevoNIF = codigo.substring(0, i) + j + codigo.substring(i + 1);
                if (verificar(nuevoNIF)) {
                    resultado.add(nuevoNIF);
                }
            }
        }
        
        return resultado.toArray(new String[0]);
    }
}
