/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package practica1;


import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alumno
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        
        
        
       
        System.out.println("//EJERCICIO 8");
        List<String> alf= Arrays.asList("a","b","c","d","e","f");
        List<Double> prob= Arrays.asList(0.25, 0.10, 0.15, 0.05, 0.20, 0.25);
        
        Fuente f = new Fuente(new AlfabetoNario(alf),prob);

        System.out.println("Entropia: "+f.entropia(f.getAlfabeto().getN_simbolos()));
        System.out.println("Entropia +1: "+(f.entropia(f.getAlfabeto().getN_simbolos())+1));
        
        CodeBook code= f.getCodificacion();
        System.out.println( "Codificación de Shannon:");
        for (int i=0; i<code.simbols().size();i++ )
        {
            System.out.println( "c( "+ code.simbols().get(i).toString() +" ) : " +code.codes().get(i) );
        }
        
        
            
        code= f.getOptimalCodificacion();
        System.out.println( "Codificación de Huffman:");
        for (int i=0; i<code.simbols().size();i++ )
        {
            System.out.println( "c( "+ code.simbols().get(i).toString() +" ) : " +code.codes().get(i) );
        }

         
        
            
        SimbolosCodificados sc= f.getSimbolosCodificados(10);
        
        System.out.println("Palabra original->"+sc.getSimbolos());
       
        
        System.out.println("Palabra codificada->"+ sc.getSimbolosCodificados());   
        
        sc= f.getSimbolosCodificadosOptimos(10);
        
        System.out.println("Palabra original->"+sc.getSimbolos());
       
        
        System.out.println("Palabra codificada->"+ sc.getSimbolosCodificados());
        
        
        
    
        
        
        
    }
}
