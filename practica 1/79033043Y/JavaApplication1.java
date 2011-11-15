/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package practica1;

import java.util.ArrayList;

/**
 *
 * @author akumayken
 */
public class JavaApplication1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        //List<Double> l = 
        ArrayList<Double> prob = new ArrayList<Double>();
        
//        prob.add(0.25); prob.add(0.10);prob.add(0.15);
 //       prob.add(0.05); prob.add(0.2);prob.add(0.25);
        
        prob.add(0.4); prob.add(0.2);prob.add(0.2);
        prob.add(0.1); prob.add(0.1);
        
        Fuente f = new Fuente(new AlfabetoABC());
        f.setProbabilidades(prob );
        
        
 //       System.out.println(f.getSimbolos(10));
 //       System.out.println(f.getCodificacion());
        
        
        System.out.println(f.getOptimalCodificacion());
        SimbolosCodificados noOpt = f.getSimbolosCodificados(10);
        SimbolosCodificados opt   = f.getSimbolosCodificadosOptimos(10);
        System.out.println("SIMBOLOS: " + noOpt.getSimbolos());
        System.out.println("CODIFICACION NO OPTIMA: " + noOpt.getSimbolosCodificados());
        System.out.println("SIMBOLOS: " + opt.getSimbolos());
        System.out.println("CODIFICACION SI OPTIMA: " + opt.getSimbolosCodificados());
        
    }

    
}
