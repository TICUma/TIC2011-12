/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package practica0;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author alumno
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
            for (int i = 0; i < codigo.length(); i++) {
                resultado += Integer.parseInt(codigo.substring(i, i+1))*(i+1);
            }
        } catch (NumberFormatException e) {
            return false;
        }
        return (resultado % MODULO) == 0;
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
        return codigo + ( resultado % MODULO); //(MODULO -(resultado% MODULO));
    }


    
    @Override
    public String [] corregirDatos(String codigo) {
        
        codigo = codigo.replaceAll("-", "");
        List<String> correcciones = new ArrayList<String>();
        
       
        
        int i=resultado (codigo);
        
        
        if(i==0) 
        {

            //El código es correcto
            correcciones.add(codigo);
        }
        else if(i>0 && i<11)
        {
            
        //1ºPosible solución: corregir digito de control
            correcciones.add(generarCodigoControl(codigo.substring(0,codigo.length()-1)));
        
        //2ºPosible solución: Asumimos dígito de control correcto
        //Cuando res=i -> restamos 1 a xi (así restamos i al resultado)
            correcciones.add(restar1Xi(i,codigo));
            
            
            //System.out.println(verificar(restar1Xi(i,codigo)));
        //3ºPosible solución: Asumimos dígito de control correcto
        //Si res=1-> intentamos sumar la cantidad necesaria en una de las posiciones posibles
            correcciones.addAll(correccionesSumaPosicion(i,codigo));
             
        }
        
        
        return correcciones.toArray(new String[0]);
    }
    
    private int resultado(String codigo)
    {
        
        int resultado = 0;
        try {
            for (int i = 0; i < codigo.length(); i++) {
                resultado += Integer.parseInt(codigo.substring(i, i+1))*(i+1);
            }
        } catch (NumberFormatException e) {
            return -1;
        }
        return resultado % MODULO;
    }
     
    private String restar1Xi(int i,String codigo)
    {
        String res="";
        int posi=Integer.parseInt(codigo.substring(i-1,i))-1;
        
        //Si i es igual a 10, no hacemos nada, ya que no queremos cambiar el digito de control
        if(i>0 && i<10)
        { 
            res=codigo.substring(0,i-1)+posi+codigo.substring(i,codigo.length());
        }
               
        
        
        return res;
    }
    
    private List<String> correccionesSumaPosicion(int i,String codigo)
    {
        List<String> resultados= new  ArrayList<String>();      
        List<Integer> div= divisores(11-i);
        int d;
        String res;
        int nuevo_valor;
        
        
        for ( int j = 0; j<div.size();j++ ) {

                   d= div.get(j);
                   if(d!=10)//la posición 10 no la queremos modificar, se supone correcta
                   {
                       nuevo_valor= Integer.parseInt(codigo.substring(d-1,d))+((11-i)/d);

                       res= codigo.substring(0,d-1)+ nuevo_valor+ codigo.substring(d,codigo.length());

                       resultados.add(res);
                   }
        }
        
        return resultados;
    
    }
    
    //Devuelve los divisores de n 
    private List<Integer> divisores (int n)
    {
        List<Integer> div = new ArrayList<Integer>();
        
       
        if(n>0 && n<11)
        {
            for(int i=1; i<=n;i++)
            {                
                 if(n%i==0) 
                 {    
                     div.add(i);
                 }

            }
        }      
        
        return div;
    }
}

