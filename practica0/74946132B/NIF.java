/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package practica0;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alumno
 */
public class NIF extends Codificacion {


    private static final String NIF_TABLA = "TRWAGMYFPDXBNJZSQVHLCKE";


  

    @Override
    public boolean verificar(String nif) {
       int dni = Integer.parseInt(nif.substring(0, nif.length()-1));
       try {
           return NIF_TABLA.charAt(dni % NIF_TABLA.length()) == nif.charAt(nif.length()-1);
       } catch (NumberFormatException e) {
           return false;
       }
    }


    /*************
     * 
     * @param codigo
     * @return
     */
    @Override
    public String generarCodigoControl(String codigo) {
       
       if (codigo!=null){
        int dni = Integer.parseInt(codigo, 10);
        return codigo+ NIF_TABLA.charAt(dni % NIF_TABLA.length());
       }
       else return null;
    }

   

    @Override
    public String[] corregirDatos(String codigo) {
        
        List<String> correcciones = new ArrayList<String>();

        
        //1ºPosible solución: corregir digito de control
            correcciones.add(generarCodigoControl(codigo.substring(0,codigo.length()-1)));
        
        //2ºPosible solución: Asumimos dígito de control correcto
            correcciones.addAll(bucarValores(codigo));

        return correcciones.toArray(new String[0]);
    }

    private List<String> bucarValores(String codigo) {
        
        List<String> resultados= new  ArrayList<String>();   
        
        char letra= codigo.charAt(codigo.length()-1);
        String dni = codigo.substring(0,codigo.length()-1);       
     
        int ndni;
        for (int i=0; i<8; i++)//posicion
        {
               
            for(int j=0; j<10; j++)//posibles valores
            {
                
                if(i==0)
                {
                   
                    ndni=Integer.parseInt(String.valueOf(j)+dni.substring(1, dni.length()));
                    
                    if(letra==NIF_TABLA.charAt(ndni % NIF_TABLA.length()))
                    {
                        
                        resultados.add(j+dni.substring(1, dni.length()-1)+letra);
                        
                    }
                }
                else if (i==7)
                {
                    
                    ndni = Integer.parseInt(dni.substring(0, 7) + j);
                    
                    if (letra == NIF_TABLA.charAt(ndni % NIF_TABLA.length())) {

                        resultados.add(dni.substring(0, 7) + j+letra);
                        
                    
                    }
                }
                else
                {
                   
                    ndni=Integer.parseInt(dni.substring(0,i)+j+dni.substring(i+1,dni.length()));
                    
                    if(letra==NIF_TABLA.charAt(ndni % NIF_TABLA.length()))
                    {
                        
                        resultados.add(dni.substring(0,i)+j+dni.substring(i+1,dni.length())+letra);
                    }
                }
            }
            
        }
        
        
        return resultados;
    }
    
    
}

