package practica1;


import java.util.ArrayList;
import java.util.List;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		AlfabetoEntrada ent = new AlfabetoEntrada();
		System.out.println("Alfabeto de entrada: " + ent.toString());
		AlfabetoSalida sal = new AlfabetoSalida();
		System.out.println("Alfabeto de salida: " + sal.toString());
		
		List<Float> probs = new ArrayList<Float>();
		probs.add(0.25f); probs.add(0.10f); probs.add(0.15f);probs.add(0.05f);probs.add(0.20f);probs.add(0.25f);
		System.out.println("Distribución de probabilidad: " + probs);
		
		Fuente f = new Fuente(ent, probs);
		
		//Creo un codeBook para Shannon-Fanno
		f.setAlfabetoCodificacion(sal);
		CodeBook cb = f.getCodificacion();
		System.out.println("\nCodebook\n======== \n"+cb);
		
		
		System.out.println("Entropía: " + f.Entropia());
		System.out.println("Longitud media: " + f.LongitudMedia(cb));
			
		System.out.println("\n\nCodificación con Shannon-Fanno\n============================== \n");
		long milisecond1;
		milisecond1 = System.nanoTime();
	    SimbolosCodificados simbcod = f.getSimbolosCodificados(80);
	    long milisecond2;
		milisecond2 = System.nanoTime();
	    System.out.println("Cadena de símbolos generada por la fuente: ");
	    for(Character car : simbcod.getSimbolos()){
	        System.out.print(car);
	    }
	    System.out.println("\n\nCodificación: ");
	    for(String cod : simbcod.getSimbolosCodificados()){
	        System.out.print(cod);
	    }
	    long milisecondResult = milisecond2-milisecond1;
	    System.out.println("\n\nTiempo invertido en Shannon-Fano: " + milisecondResult);
	    
	    //Creo un codeBook para Huffman
	    f.setAlfabetoCodificacion(sal);
		CodeBook cbh = f.getOptimalCodificacion();
		System.out.println("\nCodebook\n======== \n"+cbh);
		System.out.println("Entropía: " + f.Entropia());
		System.out.println("Longitud media: " + f.LongitudMedia(cbh));
			
		System.out.println("\n\nCodificación con Huffman\n============================== \n");
		long milisecondh1;
		milisecondh1 = System.nanoTime();
	    SimbolosCodificados simbcodh = f.getSimbolosCodificadosOptimos(80);
	    long milisecondh2;
		milisecondh2 = System.nanoTime();
	    System.out.println("Cadena de símbolos generada por la fuente: ");
	    for(Character car : simbcodh.getSimbolos()){
	        System.out.print(car);
	    }
	    System.out.println("\n\nCodificación: ");
	    for(String cod : simbcodh.getSimbolosCodificados()){
	        System.out.print(cod);
	    }
	    long milisecondResulth = milisecondh2-milisecondh1;
	    System.out.println("\n\nTiempo invertido en Huffman: " + milisecondResulth);
	 }
}
