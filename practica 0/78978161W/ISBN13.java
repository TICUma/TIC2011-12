/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package practica0;

import java.util.TreeSet;

/**
 *
 * @author monte
 */
public class ISBN13 extends Codificacion {

    private static final int MODULO = 10;

    @Override
    public boolean verificar(String codigo) {
        codigo = codigo.replaceAll("-", "");
        int resultado = 0;
        try {
            for (int i = 0; i < codigo.length(); i++) {
                if (i % 2 == 0) {
                    resultado += (Integer.parseInt(codigo.substring(i, i + 1)) * 1);
                } else {
                    resultado += (Integer.parseInt(codigo.substring(i, i + 1)) * 3);
                }
            }
        } catch (NumberFormatException e) {
            return false;
        }
        return resultado % MODULO == 0;
    }

    @Override
    public String generarCodigoControl(String codigo) {

        codigo = codigo.replaceAll("-", "");

        int resultado = 0;

        try {
            resultado = sumaPorPesos(codigo);
        } catch (NumberFormatException e) {
            return null;
        }

        resultado = resultado % MODULO;
        resultado = (MODULO - resultado) % MODULO;
        return codigo + resultado;
    }

    @Override
    public String[] corregirDatos(String codigo) {
        TreeSet<String> resultado = new TreeSet<String>();
        codigo = codigo.replaceAll("-", "");

        int resto, peso;
        try {
            resto = sumaPorPesos(codigo) % MODULO;
            int multiplo, nuevoDigito;
            for (int i = 0; i <= 27; i += MODULO) {
                multiplo = i + resto;
                if (multiplo <= 9) {


                    for (int j = 0; j < 13; j++) {
                        if (j % 2 == 0) {
                            peso = 1;
                        } else {
                            peso = 3;
                        }
                        if (multiplo % peso == 0) {
                            nuevoDigito = (codigo.charAt(j) - '0') - (multiplo / peso);
                            if (nuevoDigito >= 0) {
                                resultado.add(codigo.substring(0, j) + nuevoDigito + codigo.substring(j + 1));
                            }
                        }

                    }



                } else if (multiplo <= 27) {


                    for (int j = 1; j < 13; j += 2) {
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


                    for (int j = 0; j < 13; j++) {
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


                    for (int j = 1; j < 13; j += 2) {
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
                resultado += (Integer.parseInt(codigo.substring(i, i + 1)) * 1);
            } else {
                resultado += (Integer.parseInt(codigo.substring(i, i + 1)) * 3);
            }
        }
        return resultado;
    }
}
