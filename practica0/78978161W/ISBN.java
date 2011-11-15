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
public class ISBN extends Codificacion {

    private static final int MODULO = 11;

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
            resultado = sumaPorPesos(codigo);
        } catch (NumberFormatException e) {
            return false;
        }
        return resultado % MODULO == 0;
    }

    private int sumaPorPesos(String codigo) throws NumberFormatException {
        int resultado = 0;
        for (int i = 0; i < codigo.length(); i++) {
            if (i == codigo.length() - 1 && (codigo.charAt(i) == 'X' || codigo.charAt(i) == 'x')) {
                resultado += 10 * (i + 1);
            } else {
                resultado += Integer.parseInt(codigo.substring(i, i + 1)) * (i + 1);
            }
        }
        return resultado;
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
                resultado += (Integer.parseInt(codigo.substring(i, i + 1)) * (i + 1));
            }
        } catch (NumberFormatException e) {
            return null;
        }

        int anadido = resultado % MODULO;

        return codigo + (anadido == 10 ? 'X' : anadido);
    }

    @Override
    public String[] corregirDatos(String codigo) {
        TreeSet<String> resultado = new TreeSet<String>();
        codigo = codigo.replaceAll("-", "");

        int resto;
        try {
            resto = sumaPorPesos(codigo) % MODULO;
            int multiplo, nuevoDigito;
            for (int i = 0; i <= 90; i += MODULO) {
                multiplo = i + resto;
                if (multiplo <= 90) {
                    for (int j = 1; j <= 10; j++) {
                        if (multiplo % j == 0) {
                            nuevoDigito = (codigo.charAt(j - 1) - '0') - (multiplo / j);
                            if (nuevoDigito >= 0) {
                                resultado.add(codigo.substring(0, j - 1) + nuevoDigito + codigo.substring(j));
                            }
                        }
                    }
                }
                multiplo = i - resto;
                if (multiplo >= 0) {
                    for (int j = 1; j <= 10; j++) {
                        if (multiplo % j == 0) {
                            nuevoDigito = (codigo.charAt(j - 1) - '0') + (multiplo / j);
                            if (nuevoDigito < 10) {
                                resultado.add(codigo.substring(0, j - 1) + nuevoDigito + codigo.substring(j));
                            }
                        }
                    }
                }
            }
        } catch (NumberFormatException e) {
        }
        return resultado.toArray(new String[0]);
    }
}
