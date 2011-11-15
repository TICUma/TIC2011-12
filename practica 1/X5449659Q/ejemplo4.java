package practica1;
import java.util.*;
public class ejemplo4 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		List<Character> alfabeto = new ArrayList<Character>(6);
		alfabeto.add('a');
		alfabeto.add('b');
		alfabeto.add('c');
		alfabeto.add('d');
		alfabeto.add('e');
		alfabeto.add('f');
		List<Double> prob = new ArrayList<Double>(6);
		prob.add(0.25);prob.add(0.1);prob.add(0.15);prob.add(0.05);prob.add(0.2);prob.add(0.25);
		
        Fuente f = new Fuente(alfabeto, prob);
        System.out.println("entropia = "+f.entropia(f.getProbabilidades(), 2));
        double incio = System.nanoTime();
        CodeBook c = f.getCodificacion();
        double fin = System.nanoTime();
        System.out.println("shannon-fano time = "+(fin-incio)/1000);
        System.out.println(c.toString());
        
       // List<Character> l = f.getSimbolos(20);
        //SimbolosCodificados s = f.getSimbolosCodificados(20); 
        //System.out.println(s.getSimbolos().toString());
       // System.out.println(s.getSimbolosCodificados().toString());
       double i = System.nanoTime();
       CodeBook cb = f.getOptimalCodificacion();
       double fi = System.nanoTime();
       System.out.println("Huffman time = "+(fi-i)/1000);
       System.out.println(cb.toString());
	}

}
