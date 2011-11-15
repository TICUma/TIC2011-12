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
public class UPC extends Codificacion {

    private static final int MULTIPLO = 10;

/**********************
 * 
 * @param codigo
 * @return
 */
    public boolean verificar(String codigo) {
        int resultado = 0;
        int v;
        
        if (codigo.length()<12)
            return false;
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
    
       
    /*
    public String corregirControl(String codigo) {
        int i = 1;
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
    */

    /**************
     * 
     * @param codigo
     * @return
     */
    public String generarCodigoControl(String codigo) {
        int resultado=0;
        
        codigo = codigo.replaceAll("-", "");
        
        for (int i=0;i<codigo.length();i++){
            if (i%2==0)
                resultado+=3*Integer.parseInt(codigo.substring(i, i+1));
            else
                resultado+=Integer.parseInt(codigo.substring(i, i+1));
        }
        
        resultado = resultado % MULTIPLO;
        resultado = (MULTIPLO-resultado)%MULTIPLO;
        return codigo+resultado;
        
    }
  
   public String[] corregirDatos(String codigo) {
        String [] res;
        int difp=0,cambio,temp,cont;
        String prueba,parte1,parte2;
        ArrayList<String> lista=new ArrayList<String>();
        
        codigo = codigo.replaceAll("-", "");        
        
        for (int i = 0; i < codigo.length(); i++) {
               if (i%2==0)
                difp += (Integer.parseInt(codigo.substring(i, i+1))*3);
               else
                difp += (Integer.parseInt(codigo.substring(i, i+1))*1);
            }
        difp = difp % MULTIPLO;
        difp = (MULTIPLO - difp)%MULTIPLO;
        
        // 2) Se cuemprueba cómo hay que modificar cada dígito para corregir el error.     
        cont=1;
        while ((cont*3%MULTIPLO!=difp)&&(cont<11)) // Esto sirve para saber cuánto hay que sumar los dígitos multiplicados por 3.
            cont++;
        
        for (int i=0;i<codigo.length()-1;i++){
            parte1=codigo.substring(0, i);
            cambio=Integer.parseInt(codigo.substring(i, i+1));
            parte2=codigo.substring(i+1);
            
            if (i%2!=0){ //Si es uno de los dígitos multiplicados por 1, la comprobación es más sencilla
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
