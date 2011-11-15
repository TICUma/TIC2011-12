/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package practica0;

import java.util.TreeSet;

/**
 *
 * @author alumno
 */
public class CCC extends Codificacion {

    private int unDigitoControl(String codigo) throws NumberFormatException {
        int resultado = 0;

        int[] pesos = {1, 2, 4, 8, 5, 10, 9, 7, 3, 6};
        for (int i = 0; i < pesos.length; i++) {
            resultado += Integer.parseInt(codigo.substring(i, i + 1)) * pesos[i];
        }
        resultado = 11 - (resultado % 11);
        if (resultado == 11) {
            resultado = 0;
        }
        if (resultado == 10) {
            resultado = 1;
        }
        return resultado;
    }

    @Override
    public boolean verificar(String codigo) {
        boolean resultado = false;

        codigo = codigo.replaceAll("-", "");
        try {
            if (codigo.length() == 20) {
                resultado = (Integer.parseInt(codigo.substring(8, 9)) == unDigitoControl("00" + codigo.substring(0, 8)))
                        && (Integer.parseInt(codigo.substring(9, 10)) == unDigitoControl(codigo.substring(10, 20)));
            }
        } catch (NumberFormatException e) {
            return false;
        }
        return resultado;
    }

    @Override
    public String generarCodigoControl(String codigo) {
        codigo = codigo.replaceAll("-", "");
            
        return generarCodigoControl(codigo.substring(0, 4),codigo.substring(4, 8),codigo.substring(8));
    }
    
    public String generarCodigoControl(String entidad, String oficina, String numeroCuenta) {
        try {
            return entidad+oficina+unDigitoControl("00"+entidad+oficina)+unDigitoControl(numeroCuenta)+numeroCuenta;
        } catch (NumberFormatException e) {
            return null;
        }
    }

    @Override
    public String[] corregirDatos(String codigo) {
        TreeSet<String> resultado = new TreeSet<String>();
        codigo = codigo.replaceAll("-", "");
        
        try {
            String parte1 = codigo.substring(0, 8);
            int digitoCalculado1 = unDigitoControl("00" + parte1);
            int digitoOriginal1 = Integer.parseInt(codigo.substring(8, 9));
            boolean error1 = digitoCalculado1 != digitoOriginal1;
            String parte2 = codigo.substring(10, 20);
            int digitoCalculado2 = unDigitoControl(parte2);
            int digitoOriginal2 = Integer.parseInt(codigo.substring(9, 10));
            boolean error2 = digitoCalculado2 != digitoOriginal2;
            
            boolean fin;
            int cont;
            String nuevoCodigo;
            if (error1 && !error2) {
                for (int i = 0; i < parte1.length(); i++){
                    fin = false;
                    cont = 0;
                    while (!fin && cont < 10) {
                        nuevoCodigo = parte1.substring(0, i) + cont + parte1.substring(i+1);
                        if(digitoOriginal1 == unDigitoControl("00"+nuevoCodigo)){
                            fin = true;
                            resultado.add(nuevoCodigo+digitoOriginal1+digitoOriginal2+parte2);
                        }else{
                            cont++;
                        }
                    }
                }
            }else if (error2 && !error1){
                for (int i = 0; i < parte2.length(); i++){
                    fin = false;
                    cont = 0;
                    while (!fin && cont < 10) {
                        nuevoCodigo = parte2.substring(0, i) + cont + parte2.substring(i+1);
                        if(digitoOriginal2 == unDigitoControl(nuevoCodigo)){
                            fin = true;
                            resultado.add(parte1+digitoOriginal1+digitoOriginal2+nuevoCodigo);
                        }else{
                            cont++;
                        }
                    }
                }
            }
        } catch (NumberFormatException e) {
        }
        
        return resultado.toArray(new String[0]);
    }
}
