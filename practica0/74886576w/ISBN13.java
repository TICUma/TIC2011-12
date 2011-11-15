/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package practica0;

import java.util.ArrayList;

/**
 *
 * @author monte
 */
public class ISBN13 extends Codificacion{

     private static final int MODULO = 10;

      public boolean verificar(String codigo) {
        codigo = codigo.replaceAll("-", "");
        int resultado = 0;
        try {
            for (int i = 0; i < codigo.length(); i++) {
               if (i%2==0)
                resultado += (Integer.parseInt(codigo.substring(i, i+1))*1);
               else
                resultado += (Integer.parseInt(codigo.substring(i, i+1))*3);
            }
        } catch (NumberFormatException e) {
            return false;
        }
        return resultado % MODULO == 0;
    }

    public String generarCodigoControl(String codigo) {
        int resultado = 0;
        
        codigo = codigo.replaceAll("-", "");
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
        resultado = resultado % MODULO;
        resultado = (MODULO -resultado)%MODULO;
        return codigo+resultado;
    }

    public String[] corregirDatos (String codigo) {
        String [] res;
        int difp=0,cambio,cont;
        String prueba,parte1,parte2;
        ArrayList<String> lista=new ArrayList<String>();
        
        codigo = codigo.replaceAll("-", "");
        
        // 1) Se calcula la diferencia que hay que corregir
        for (int i = 0; i < codigo.length(); i++) {
               if (i%2==0)
                difp += (Integer.parseInt(codigo.substring(i, i+1))*1);
               else
                difp += (Integer.parseInt(codigo.substring(i, i+1))*3);
            }
        
        difp = difp % MODULO;
        difp = (MODULO - difp)%MODULO;
        
        // 2) Se cuemprueba cómo hay que modificar cada dígito para corregir el error.     
        cont=1;
        while ((cont*3%MODULO!=difp)&&(cont<11)) // Esto sirve para saber cuánto hay que sumar los dígitos multiplicados por 3.
            cont++;
        
        for (int i=0;i<codigo.length()-1;i++){
            parte1=codigo.substring(0, i);
            cambio=Integer.parseInt(codigo.substring(i, i+1));
            parte2=codigo.substring(i+1);
            
            if (i%2==0){    // Si es par (el dígito se multiplica por 1) la comprobación es más sencilla.
                prueba=parte1+(cambio+difp)%10+parte2;
                lista.add(prueba);
                //System.out.println("|| Añadido el código: "+prueba+" -> "+verificar(prueba)+" ||");
            }
            else {      // Si se multiplica por 3, se suma el contador calculado anteriormente
                prueba=parte1+(cambio+cont)%10+parte2;
                lista.add(prueba);
                //System.out.println("++ Añadido el código: "+prueba+" -> "+verificar(prueba)+" ++");
            }      
        }
        res=new String[lista.size()];
        lista.toArray(res);
        //System.out.println("Tamaño res = "+res.length+" <-> Tamaño super.res = "+super.corregirDatos(codigo).length);
        return res;       
    }   
}
