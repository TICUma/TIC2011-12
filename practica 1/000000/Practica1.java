/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package practica1;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

/**
 *
 * @author alumno
 */
public class Practica1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
    	/*
    	 * El test de la fuente
    	 */
    	
    	System.out.println("* El test de la fuente:");

        Alfabeto a1 = new Alfabeto();
        a1.add('0'); a1.add('1'); a1.add('2');
        a1.add('3'); a1.add('4'); a1.add('5');
        Map<Character, Double> p1 = new HashMap<Character, Double>();
        p1.put('0', 0.4); p1.put('1', 0.1); p1.put('2', 0.1);
        p1.put('3', 0.1); p1.put('4', 0.1); p1.put('5', 0.2);
        
        testFuente(a1, p1, 1000000);
        
        Fuente fuente = new Fuente();
        
        /*
         * Ejercicio 8 & 10
         */
        
        System.out.println("* Ejercicio 8 & 10");
        
        Alfabeto a8 = new Alfabeto();
        a8.add('a'); a8.add('b'); a8.add('c');
        a8.add('d'); a8.add('e'); a8.add('f');
        Map<Character, Double> p8 = new HashMap<Character, Double>();
        p8.put('a', 0.25); p8.put('b', 0.1); p8.put('c', 0.15);
        p8.put('d', 0.05); p8.put('e', 0.2); p8.put('f', 0.25);
        Alfabeto aa8 = new Alfabeto();
        aa8.add('0'); aa8.add('1');
        
        fuente.setAlfabeto(a8);
        fuente.setProbabilidades(p8);
        fuente.setAlfabetoCodificacion(aa8);
        
        shannonFano(fuente);
        huffman(fuente);
        
        /*
         * Ejercicio 9
         */
        
        System.out.println("* Ejercicio 9");
        
        Alfabeto a9 = new Alfabeto();
        a9.add('a'); a9.add('b'); a9.add('c');
        Map<Character, Double> p9 = new HashMap<Character, Double>();
        p9.put('a', 0.5); p9.put('b', 0.3); p9.put('c', 0.2);
        Alfabeto aa9 = new Alfabeto();
        aa9.add('0'); aa9.add('1'); aa9.add('2');
        
        fuente.setAlfabeto(a9);
        fuente.setProbabilidades(p9);
        fuente.setAlfabetoCodificacion(aa9);
        
        shannonFano(fuente);
        huffman(fuente);
        
        /*
         * Ejercicio 11
         */
        
        System.out.println("* Ejercicio 11");
        
        Alfabeto a11 = new Alfabeto();
        a11.add('a'); a11.add('b'); a11.add('c'); a11.add('d');
        Map<Character, Double> p11 = new HashMap<Character, Double>();
        p11.put('a', 0.4); p11.put('b', 0.3); p11.put('c', 0.2); p11.put('d', 0.1);
        Alfabeto aa11 = new Alfabeto();
        aa11.add('0'); aa11.add('1');
        
        fuente.setAlfabeto(a11);
        fuente.setProbabilidades(p11);
        fuente.setAlfabetoCodificacion(aa11);
        
        shannonFano(fuente);
        huffman(fuente);
        
    }
    
    public static void shannonFano(Fuente fuente) {
    	Codebook shannonFano = fuente.getCodificacion();
    	System.out.println("+ Shannon-Fano:");
    	System.out.println(" + H(p) = " + fuente.entropia());
    	System.out.println(" + L = " + fuente.longitudMedia(shannonFano));
    	System.out.print(shannonFano);
    }
    
    public static void huffman(Fuente fuente) {
    	Codebook huffman = fuente.getOptimalCodificacion();
    	System.out.println("+ Huffman:");
    	System.out.println(" + H(p) = " + fuente.entropia());
    	System.out.println(" + L = " + fuente.longitudMedia(huffman));
    	System.out.print(huffman);
    }
    
    public static void testFuente(Alfabeto alfabeto, Map<Character, Double> probabilidades, int numeroSimbolos) {
        
        Fuente f1 = new Fuente(alfabeto, probabilidades);
        // simbolos generados
        List<Character> simbolos = f1.getSimbolos(numeroSimbolos);
        // contador
        Map<Character, Integer> freqs = new HashMap<Character, Integer>();
        // inicializacion del contador
        for (int i = 0; i < alfabeto.size(); i++) {
            freqs.put(alfabeto.get(i), 0);
        }
        // frecuencia de los simbolos generados
        for (Character simbolo : simbolos) {
            freqs.put(simbolo, freqs.get(simbolo) + 1);
        }
        // resultados
        for (Character simbolo : freqs.keySet()) {
            System.out.println(simbolo + ": " + freqs.get(simbolo));
        }
        
    }
    
}
