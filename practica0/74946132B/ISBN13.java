/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package practica0;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author alumno
 */
public class ISBN13 extends Codificacion{

     private static final int MODULO = 10;

    @Override
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


    @Override
    public String generarCodigoControl(String codigo) {

        
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
                return null;
            }

            int digito_control= MODULO-(resultado %MODULO);
            return codigo+ digito_control;
        
       
    }


    @Override
    public String[] corregirDatos(String codigo) {
        codigo = codigo.replaceAll("-", "");
        List<String> correcciones = new ArrayList<String>();
        
        int e=errorCodigo (codigo);        
        
        if(e==0) 
        {
            //El código es correcto
            correcciones.add(codigo);
        }
        else if(e>0 && e<10)
        {
            
        //1ºPosible solución: corregir digito de control
            correcciones.add(generarCodigoControl(codigo.substring(0,codigo.length()-1)));
        
        //2ºPosible solución: Asumimos dígito de control correcto
        //Sumamos (10-e) a las posiciones pares 
            correcciones.addAll(sumarParesN(10-e,codigo));
            
            
            //System.out.println(verificar(restar1Xi(i,codigo)));
        //3ºPosible solución: Asumimos dígito de control correcto
        //
            correcciones.addAll(sumaImparesN(10-e,codigo));             
        }
        
        return correcciones.toArray(new String[0]);        
    }

    private int errorCodigo(String codigo)
    {

        int resultado = 0;

        try {
            for (int i = 0; i < codigo.length(); i++) {
               if (i%2==0)
                resultado += (Integer.parseInt(codigo.substring(i, i+1))*1);
               else
                resultado += (Integer.parseInt(codigo.substring(i, i+1))*3);
            }
        } catch (NumberFormatException e) {
            return -1;
        }
        
        return resultado % MODULO;
    }
    
    

    private List<String> sumarParesN(int n, String codigo) {
       
        List<String> resultados= new ArrayList<String>();
        
        String res;
        int nuevo_valor;
        for (int i=0; i<codigo.length()-1;i++) //todas las posiciones pares excepto la ultima(digito de control)
        {
            if(i%2==0)
            {
                nuevo_valor= (Integer.parseInt(codigo.substring(i,i+1))+n) %MODULO ;
                if(i==0)
                {                   
                    res= nuevo_valor+codigo.substring(i+1, codigo.length());                
                }
                else
                {               
                    res= codigo.substring(0,i)+nuevo_valor+ codigo.substring(i+1, codigo.length());                
                }
                
                resultados.add(res);
            }
            
        }
        
        return resultados;
                
    }

    private List<String> sumaImparesN(int n, String codigo) {
         List<String> resultados= new ArrayList<String>();
        
        String res;
        int nuevo_valor;
        int valor_act;
        for (int i=0; i<12;i++) //posicion
        {
               valor_act= Integer.parseInt(codigo.substring(i,i+1));
            
                if(i%2!=0)
                {
                    for(int j=0; j<10;j++)//valores que podría tomar esa posicion
                    {
                    
                    
                    if(((j*3)%MODULO)== (valor_act*3 )+n)
                    {
                        nuevo_valor=j;
                        if(i==0)
                        {                   
                            res= nuevo_valor+codigo.substring(i+1, codigo.length());                
                        }
                        else
                        {               
                            res= codigo.substring(0,i)+nuevo_valor+ codigo.substring(i+1, codigo.length());                
                        }

                        resultados.add(res);
                    }
                }
            }
            
        }
        
        return resultados;
                
    }

    
}
