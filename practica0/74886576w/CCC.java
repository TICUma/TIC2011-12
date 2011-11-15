/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package practica0;

import java.util.ArrayList;

/**
 *
 * @author Nicolás
 */
public class CCC extends Codificacion {
    private static final int[] factores = {1,2,4,8,5,10,9,7,3,6};
    private static final int MODULO = 11;
    
    @Override
    public boolean verificar(String codigo) {
        int c1,c2;
              
        codigo = codigo.replaceAll("-", "");
        codigo = codigo.replaceAll(" ", "");
        
        if (codigo.length()!=20)
            return false;
                
        int control1 = Integer.parseInt(codigo.substring(8, 9));
        int control2 = Integer.parseInt(codigo.substring(9, 10));
        
        c1= generaControl("00"+codigo.substring(0, 8));
        c2= generaControl(codigo.substring(10, 20));

        return ((c1==control1) && (c2==control2));
        
    }

    @Override
    public String generarCodigoControl(String codigo) {
        int c1=0,c2=0;
        String cod1,cod2;
                
        codigo = codigo.replaceAll("-", "");
        codigo = codigo.replaceAll(" ", "");
        
        cod1=codigo.substring(0, 8);
        c1=generaControl("00"+cod1);
        
        cod2=codigo.substring(8, 18);
        c2=generaControl(cod2);
        
        return (cod1+c1+c2+cod2);
        
    }
    
    private int generaControl (String cod) {
        int c=0;

        for (int i=0;i<cod.length();i++){
            c=c+Integer.parseInt(cod.substring(i, i+1))*factores[i];
        }     
        c = MODULO - (c % MODULO);
        if (c==10)
            c=1;
        else if (c==11)
            c=0;
        
        return c;      
    }
    
    @Override
    public String corregirControl(String codigo){
        String cod;
        
        codigo = codigo.replaceAll("-", "");
        codigo = codigo.replaceAll(" ", "");
        cod=codigo.substring(0, 8)+codigo.substring(10);
        return generarCodigoControl(cod);
    }

    @Override
    public String[] corregirDatos(String codigo) {
        String [] res=new String [20];
        int difp,difn,cambio,temp,cont,c1,c2;
        int sumamod1=0,sumamod2=0;
        String prueba,parte1,parte2,cod1,cod2;
        ArrayList<String> lista = new ArrayList<String>();
        
        codigo = codigo.replaceAll("-", "");
        codigo = codigo.replaceAll(" ", "");
        
        cod1="00"+codigo.substring(0, 8);
        cod2=codigo.substring(10, 20);
        c1=Integer.parseInt(codigo.substring(8, 9));
        c2=Integer.parseInt(codigo.substring(9, 10));

        sumamod1= generaControl("00"+codigo.substring(0, 8));
        sumamod2= generaControl(codigo.substring(10, 20));
        
        if ((sumamod1!=c1)&&(sumamod2!=c2)){
            System.out.println("Hay más de un dígito erróneo en el código");
            return res;
        }
        else if (sumamod1!=c1){         // Esto es si el error está en la primera mitad (primer dígito de control)
            if (sumamod1-c1>0){
                difp=sumamod1-c1;
                difn=MODULO-difp;
            }
            else {
                difn=c1-sumamod1;
                difp=MODULO-difn;
            }

            for (int i=2;i<cod1.length();i++){
                parte1=cod1.substring(0, i);
                cambio=Integer.parseInt(cod1.substring(i, i+1));
                parte2=cod1.substring(i+1);
                
                                // Se comprueba en sentido ascendente
                cont=1;
                while ((cont*factores[i]%MODULO!=difp)&&(cont<11))
                    cont++;
                temp=((cambio+cont));
                if ((temp>0)&&(temp<10)&&(cont*factores[i]%MODULO==difp)){
                    prueba=parte1.substring(2)+temp+parte2;
                    lista.add(prueba+c1+c2+cod2);
                        //prueba=prueba+c1+c2+cod2;
                        //System.out.println("++ Añadido el código: "+prueba+" -> "+verificar(prueba)+" ++");
                }
                                // Y ahora en en sentido descendente
                cont=1;
                while ((cont*factores[i]%MODULO!=difn)&&(cont<11))
                    cont++;
                temp=((cambio-cont));
                if ((temp>=0)&&(temp<=9)&&(cont*factores[i]%MODULO==difn)){
                    prueba=parte1.substring(2)+temp+parte2;
                    lista.add(prueba+c1+c2+cod2);
                        //prueba=prueba+c1+c2+cod2;
                        //System.out.println("-- Añadido el código: "+prueba+" -> "+verificar(prueba)+" --");
                }
            }    
        }
        else if (sumamod2!=c2){       // Esto es si el error está en la segunda mitad (segundo dígito de control)
            if (sumamod2-c2>0){
                difp=sumamod2-c2;
                difn=MODULO-difp;
            }
            else {
                difn=c2-sumamod2;
                difp=MODULO-difn;
            }

            for (int i=0;i<cod2.length();i++){
                parte1=cod2.substring(0, i);
                cambio=Integer.parseInt(cod2.substring(i, i+1));
                parte2=cod2.substring(i+1);
                
                                    // Se comprueba en sentido ascendente
                cont=1;
                while ((cont*factores[i]%MODULO!=difp)&&(cont<11))
                    cont++;
                temp=((cambio+cont));
                if ((temp>0)&&(temp<10)&&(cont*factores[i]%MODULO==difp)){
                    prueba=parte1+temp+parte2;
                    lista.add(cod1.substring(2)+c1+c2+prueba);
                        //prueba=cod1.substring(2)+c1+c2+prueba;
                        //System.out.println("++ Añadido el código: "+prueba+" -> "+verificar(prueba)+" ++");
                }
                                    // Y ahora en en sentido descendente
                cont=1;
                while ((cont*factores[i]%MODULO!=difn)&&(cont<11))
                    cont++;
                temp=((cambio-cont));
                if ((temp>=0)&&(temp<=9)&&(cont*factores[i]%MODULO==difn)){
                    prueba=parte1+temp+parte2;
                    lista.add(cod1.substring(2)+c1+c2+prueba);
                        //prueba=cod1.substring(2)+c1+c2+prueba;
                        //System.out.println("-- Añadido el código: "+prueba+" -> "+verificar(prueba)+" --");
                }
            }   
        } 
        
        res=new String[lista.size()];
        lista.toArray(res);
        //System.out.println("Tamaño res = "+res.length+" <-> Tamaño super.res = "+(super.corregirDatos(codigo).length-1));
        return res; 
    }
   
}
