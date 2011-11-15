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
public class UPC extends Codificacion {

    private static final int MULTIPLO = 10;

/**********************
 * 
 * @param codigo
 * @return
 */
    public boolean verificar(String codigo) {
        
        codigo = codigo.replaceAll("-", "");
        
        int resultado = 0;
        int v;
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
    
    public String corregirControl(String codigo) {
        int i = 0;
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

    /**************
     * 
     * @param codigo
     * @return
     */
    @Override
    public String generarCodigoControl(String codigo) {
        
        codigo = codigo.replaceAll("-", "");
        
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
        int dig_control=(MULTIPLO -(resultado % MULTIPLO));
        return codigo+ dig_control;
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
        //Sumamos (10-e) a las posiciones impares 
            correcciones.addAll(sumarImparesN(10-e,codigo));
            
            
            //System.out.println(verificar(restar1Xi(i,codigo)));
        //3ºPosible solución: Asumimos dígito de control correcto
        //
            correcciones.addAll(sumaParesN(10-e,codigo));             
        }
        
        return correcciones.toArray(new String[0]);        
    
    }
    
    private int errorCodigo(String codigo)
    {


       int resultado = 0;
        int v;
        try {
            for (int i = 0; i < codigo.length(); i++) {
                v = Integer.parseInt(codigo.substring(i, i+1));
                resultado += i%2 == 0 ? v*3 : v;
            }
        } catch (NumberFormatException e) {
            return -1;
        }
        
        
        return resultado % MULTIPLO;
    }

    private List<String> sumarImparesN(int n, String codigo) {
        List<String> resultados= new ArrayList<String>();
        
        String res;
        int nuevo_valor;
        for (int i=0; i<11;i++) //todas las posiciones pares excepto la ultima(digito de control)
        {
            if(i%2!=0)
            {
                nuevo_valor= (Integer.parseInt(codigo.substring(i,i+1))+n) %MULTIPLO ;
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

    private List<String> sumaParesN(int n, String codigo) {
         List<String> resultados= new ArrayList<String>();
        
        String res;
        int nuevo_valor;
        int valor_act;
        for (int i=0; i<10;i++) //posicion la 10º es la ultima impar
        {
            
            valor_act= Integer.parseInt(codigo.substring(i,i+1));
            
        
            if(i%2==0)
            {
                for(int j=0; j<10;j++)//valores que podría tomar esa posicion
                {
                    
                    if(((j*3)%MULTIPLO)== (valor_act*3 )+ n)
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
