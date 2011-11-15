/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package practica1;

import java.util.Arrays;
import java.util.List;

/**
 *
 * @author alumno
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Double[] probabilidades = {0.25, 0.10, 0.15, 0.05, 0.20, 0.25};
        List probabilidadesLista = Arrays.asList(probabilidades);
        Fuente fuente = new Fuente(new AlfabetoSimbolos(),probabilidadesLista);
        fuente = new Fuente(new AlfabetoSimbolos());
        
        System.out.println("Alfabeto: {a, b, c, d, e, f}");
        System.out.println("Probabilidades: {0.25, 0.10, 0.15, 0.05, 0.20, 0.25}");
        System.out.println();
        
        System.out.println("Shannon (codificación binaria):");
        System.out.println(fuente.getCodificacion().toString());
        System.out.println("H(p): " + fuente.entropia());
        System.out.println("L: " + fuente.longitudMedia());
        System.out.println();
        
        
        System.out.println("Huffman (codificación binaria):");
        System.out.println(fuente.getOptimalCodificacion().toString());
        System.out.println("H(p): " + fuente.entropia());
        System.out.println("L: " + fuente.longitudMediaOptima());
        System.out.println();
        
        
        fuente.setAlfabetoCodificacion(new AlfabetoTernario());
        
        System.out.println("Shannon (codificación ternaria):");
        System.out.println(fuente.getCodificacion().toString());
        System.out.println("H(p): " + fuente.entropia());
        System.out.println("L: " + fuente.longitudMedia());
        System.out.println();

        System.out.println("Huffman (codificación ternaria):");
        System.out.println(fuente.getOptimalCodificacion().toString());
        System.out.println("H(p): " + fuente.entropia());
        System.out.println("L: " + fuente.longitudMediaOptima());

    }
}
