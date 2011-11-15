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
public class ISBN extends Codificacion {

    private static final int MODULO = 11;

/*************
 * 
 * @param codigo
 * @return
 */
    public boolean verificar(String codigo) {
        codigo = codigo.replaceAll("-", "");
        if (codigo.length()<10)
            return false;
        int resultado = 0;
        try {
            for (int i = 0; i < codigo.length(); i++) {
                if ((i==codigo.length()-1)&&(codigo.charAt(i)=='X')){
                    resultado += 10*(i+1);
                }
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

     int resultado = 0;

        try {
            for (int i = 0; i < codigo.length(); i++) {
                resultado += (Integer.parseInt(codigo.substring(i, i+1))*(i+1));
            }
        } catch (NumberFormatException e) {
            return null;
        }
        if (resultado%MODULO!=10)
            return codigo+(resultado% MODULO);
        else
            return codigo+"X";
    }
        
    @Override
    public String[] corregirDatos(String codigo) {
        String [] res;
        int difp,difn,sumamod=0;
        ArrayList<String> lista;
        int[] factores = {1,2,3,4,5,6,7,8,9,10};
        
        codigo = codigo.replaceAll("-", "");
                       // Se calcula la diferencia a corregir
        for (int i = 0; i < codigo.length(); i++) {
                if ((i==codigo.length()-1)&&(codigo.charAt(i)=='X'))
                        sumamod += 10*(i+1);
                else
                    sumamod += (Integer.parseInt(codigo.substring(i, i+1))*(i+1));
            }
        sumamod=sumamod%MODULO;
        if (sumamod==0){
            System.out.println("El código es correcto");
            return new String[0];
        }
        
        difn=sumamod;
        difp=MODULO-sumamod;
        lista=corregir(codigo,difp,difn,factores,MODULO); // Se comprueba como hay que modificar cada digito
        res=new String[lista.size()];
        lista.toArray(res);
        //System.out.println("Tamaño res = "+res.length+" <-> Tamaño super.res = "+super.corregirDatos(codigo).length);
        return res;
    }
    
    public ArrayList<String> corregir(String codigo,int difp, int difn,int[] factores,int modulo){
        String parte1,parte2,prueba;
        int cambio,cont,temp;
        ArrayList<String> lista=new ArrayList<String>();
        
        for (int i=0;i<codigo.length()-1;i++){
            parte1=codigo.substring(0, i);
            cambio=Integer.parseInt(codigo.substring(i, i+1));
            parte2=codigo.substring(i+1);
            
                        // Se comprueba en sentido ascendente
            cont=1;
            while ((cont*factores[i]%modulo!=difp)&&(cont<11))
                cont++;
            //System.out.println("Comprobando digito "+(i+1)+" (pos) - cont: "+cont);
            temp=(cambio+cont);
            if ((temp>0)&&(temp<10)&&(cont*(i+1)%modulo==difp)){
                prueba=parte1+temp+parte2;
                lista.add(prueba);
                //System.out.println("++ Añadido el código: "+prueba+" -> "+verificar(prueba)+" ++");
            }
                        // Y ahora en sentido descendente
            cont=1;
            while ((cont*(i+1)%modulo!=difn)&&(cont<11))
                cont++;
            //System.out.println("Comprobando digito "+(i+1)+" (neg) - cont: "+cont);
            temp=(cambio-cont);
            if ((temp>=0)&&(temp<=9)&&(cont*(i+1)%modulo==difn)){
                prueba=parte1+temp+parte2;
                lista.add(prueba);
                //System.out.println("-- Añadido el código: "+prueba+" -> "+verificar(prueba)+" --");
            }   
        }      
        return lista; 
    }

}

