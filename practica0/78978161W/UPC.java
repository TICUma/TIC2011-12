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
public class UPC extends Codificacion {

    private static final int MULTIPLO = 10;

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
                v = Integer.parseInt(codigo.substring(i, i + 1));
                resultado += i % 2 == 0 ? v * 3 : v;
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
                codigoCorregido = codigo.substring(0, i - 1) + digito + codigo.substring(i + 1, codigo.length());
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
    /*@Override
    public String generarCodigoControl(String codigo) {
    int resultado = 0;
    int v;
    try {
    for (int i = 0; i < codigo.length(); i++) {
    v = Integer.parseInt(codigo.substring(i, i+1));
    resultado += i%2 == 0 ? v*3 : v;
    }
    } catch (NumberFormatException e) {
    return null;
    }
    resultado = resultado % MULTIPLO;
    resultado = (MULTIPLO - resultado) % MULTIPLO;
    return codigo + resultado;
    }*/
    @Override
    public String generarCodigoControl(String codigo) {

        codigo = codigo.replaceAll("-", "");

        int resultado = 0;

        try {
            resultado = sumaPorPesos(codigo);
        } catch (NumberFormatException e) {
            return null;
        }

        resultado = resultado % MULTIPLO;
        resultado = (MULTIPLO - resultado) % MULTIPLO;
        return codigo + resultado;
    }

    @Override
    public String[] corregirDatos(String codigo) {
        TreeSet<String> resultado = new TreeSet<String>();
        codigo = codigo.replaceAll("-", "");

        int resto, peso;
        try {
            resto = sumaPorPesos(codigo) % MULTIPLO;
            int multiplo, nuevoDigito;
            for (int i = 0; i <= 27; i += MULTIPLO) {
                multiplo = i + resto;
                if (multiplo <= 9) {


                    for (int j = 0; j < 12; j++) {
                        if (j % 2 == 0) {
                            peso = 3;
                        } else {
                            peso = 1;
                        }
                        if (multiplo % peso == 0) {
                            nuevoDigito = (codigo.charAt(j) - '0') - (multiplo / peso);
                            if (nuevoDigito >= 0) {
                                resultado.add(codigo.substring(0, j) + nuevoDigito + codigo.substring(j + 1));
                            }
                        }

                    }



                } else if (multiplo <= 27) {


                    for (int j = 0; j < 12; j += 2) {
                        peso = 3;
                        if (multiplo % peso == 0) {
                            nuevoDigito = (codigo.charAt(j) - '0') - (multiplo / peso);
                            if (nuevoDigito >= 0) {
                                resultado.add(codigo.substring(0, j) + nuevoDigito + codigo.substring(j + 1));
                            }
                        }

                    }

                }

                multiplo = i - resto;
                if (multiplo >= 0 && multiplo <= 9) {


                    for (int j = 0; j < 12; j++) {
                        if (j % 2 == 0) {
                            peso = 1;
                        } else {
                            peso = 3;
                        }
                        if (multiplo % peso == 0) {
                            nuevoDigito = (codigo.charAt(j) - '0') + (multiplo / peso);
                            if (nuevoDigito < 10) {
                                resultado.add(codigo.substring(0, j) + nuevoDigito + codigo.substring(j + 1));
                            }
                        }

                    }



                } else if (multiplo >= 0 && multiplo <= 27) {


                    for (int j = 0; j < 12; j += 2) {
                        peso = 3;
                        if (multiplo % peso == 0) {
                            nuevoDigito = (codigo.charAt(j) - '0') + (multiplo / peso);
                            if (nuevoDigito < 10) {
                                resultado.add(codigo.substring(0, j) + nuevoDigito + codigo.substring(j + 1));
                            }
                        }

                    }

                }
            }
        } catch (NumberFormatException e) {
        }
        return resultado.toArray(new String[0]);
    }

    private int sumaPorPesos(String codigo) throws NumberFormatException {
        int resultado = 0;
        for (int i = 0; i < codigo.length(); i++) {
            if (i % 2 == 0) {
                resultado += (Integer.parseInt(codigo.substring(i, i + 1)) * 3);
            } else {
                resultado += (Integer.parseInt(codigo.substring(i, i + 1)) * 1);
            }
        }
        return resultado;
    }
}
