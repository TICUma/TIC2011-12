package Practica1;

public class main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Alfabeto alfabeto = new Alfabeto(new char[]{'a','b','c','d','e'},new float[]{0.4f,0.2f,0.2f,0.1f,0.1f});
		Alfabeto alfabeto = new Alfabeto(new char[]{'a','b','c','d','e','f'},new float[]{0.25f, 0.10f, 0.15f, 0.05f, 0.20f, 0.25f});
		//Alfabeto alfabeto = new Alfabeto(new char[]{'a','b','c','d'},new float[]{0.25f,0.25f,0.25f,0.25f});
		

		Fuente fuente = new Fuente(alfabeto);
		
		System.out.println("Pruebas ejercicio 8 con Shannon-Fano:");
		System.out.println("************************************");
		System.out.println(fuente.getCodificacion().toString());
		SimbolosCodificados resultado = fuente.getSimbolosCodificados(20);
		System.out.println("Texto plano: "+resultado.getSimbolos().toString());
		System.out.println("Texto codificado: "+resultado.getSimbolosCodificados().toString());
		
		System.out.println("Pruebas ejercicio 8 con Huffman:");
		System.out.println("************************************");
		System.out.println(fuente.getOptimalCodificacion().toString());
		resultado = fuente.getSimbolosCodificadosOptimos(20);
		System.out.println("Texto plano: "+resultado.getSimbolos().toString());
		System.out.println("Texto codificado: "+resultado.getSimbolosCodificados().toString());
		
	}

}
