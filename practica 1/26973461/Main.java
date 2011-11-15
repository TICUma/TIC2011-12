package practica1;

import java.util.ArrayList;
import java.util.List;

public class Main {

	static char[] argu;
	static double[] prob;
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		argu = new char[]{'a','b','c','d'};
		List<Double> lp= new ArrayList<Double>();
		lp.add(0.4);
		lp.add( 0.25);
		lp.add(0.25);
		lp.add(0.1);
		Alfabeto a = new Alfabeto(argu);
		Fuente f = new Fuente(a,lp);
		System.out.println("Hola aqui empieza");
		
		 
		CodeBook b = f.getCodificacion();
		System.out.println(b.toString());
		
		SimbolosCodificados sc = f.getSimbolosCodificados(20);
		System.out.println(sc.getSimbolos().toString());
		System.out.println(sc.getSimbolosCodificados().toString());
	

		// Codificación optima
		
		b = f.getOptimalCodificacion();
		System.out.println(b.toString());
		sc = f.getSimbolosCodificadosOptimos(20);
		System.out.println(sc.getSimbolos().toString());
		System.out.println(sc.getSimbolosCodificados().toString());
	}

}
