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
public class NIF extends Codificacion {


    private static final String NIF_TABLA = "TRWAGMYFPDXBNJZSQVHLCKE";
    private static final int MODULO = 23;


  

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
    public String generarCodigoControl(String codigo) {
       
       if (codigo!=null){
        int dni = Integer.parseInt(codigo, 10);
        return codigo+ NIF_TABLA.charAt(dni % NIF_TABLA.length());
       }
       else return null;
    }

    
    public String [] corregirDatos(String codigo) {        
        return super.corregirDatos(codigo);
        
        // Lo que sigue funciona mucho peor que lo anterior
        /*
        ArrayList<String> lista = new ArrayList<String>();
        String[] res;//=new String[8];
        String prueba;
        int n,orig,dif;
        char letra;
        
        orig=Integer.parseInt(codigo.substring(0, codigo.length()-1));
        letra=codigo.charAt(codigo.length()-1);
        dif=buscaTabla(letra);
        System.out.println(dif);
        n=dif;
        while (n<100000000){
            if (HammingMenorDos(n,orig)){
                prueba=rellena(Integer.toString(n),8);
                lista.add(prueba+letra);
                System.out.println("Se ha añadido el codigo "+prueba+letra);
            }
            n+=MODULO;
        }
        
        res=new String[lista.size()];
        lista.toArray(res);
        System.out.println("Tamaño res = "+res.length+" <-> Tamaño super.res = "+super.corregirDatos(codigo).length);
        return res;                
        */ 
    }
          
    private int buscaTabla(char c){
        int pos=0;
        
        while (NIF_TABLA.charAt(pos)!=c){
            pos++;
        }
        
        return pos;
                
    }
    
    
    // Todo lo que sigue no llega a usarse
    private String rellena (String cad, int l){
        while (cad.length()<l)
            cad="0"+cad;
        return cad;
    }
    
    
    private boolean HammingMenorDos(int a, int b){
        int maxl,i=0,cont=0;
        String c1=Integer.toString(a);
        String c2=Integer.toString(b);
        
        if (c1.length()>c2.length())
            maxl=c1.length();
        else
            maxl=c2.length();
        
        while (c1.length()<maxl)
            c1="0"+c1;
        while (c2.length()<maxl)
            c2="0"+c1;
        
        while ((i<maxl)&&(cont<2)){
            if (c1.charAt(i)!=c2.charAt(i))
                cont++;
            i++;
        }
        return (cont<2);
    }
    
}
