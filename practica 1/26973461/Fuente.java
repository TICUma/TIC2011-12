package practica1;

import java.sql.Time;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;
import java.util.Map.Entry;


public class Fuente {
	
	private Alfabeto alfabeto;
	private Random random = new Random(System.currentTimeMillis());
	private static int BASE = 2;
	
	// Redefinido
	private List<Double> probabilidades;
	
	public Fuente(Alfabeto alfabeto, List<?> probabilidades) {
		this.alfabeto = alfabeto;
		this.probabilidades = (List<Double>) probabilidades;
		
	}
	
	public Fuente(Alfabeto alfabeto) {
		this.alfabeto = alfabeto;
	}
		
	/**
	 * Metodo que genera la lista de simbolosObject
	 * del alfabeto segun sus probabilidades
	 * 
	 * @param numeroSimbolos
	 * @return
	 */
	public List<?> getSimbolos(int numeroSimbolos){
		double prob = this.random.nextDouble();
		double acum;
		int j;
		List<Object> res = new ArrayList<Object>();
		for(int i=0;i<numeroSimbolos;i++){
			prob = this.random.nextDouble();

			j=0;
			acum=0;
			while(j<probabilidades.size() && acum<prob){
				acum+=probabilidades.get(j);
				j++;
			}
			res.add(alfabeto.get(j-1));
		}
		
		return res;
	}	
	
	/**
	 * Metodo encargado de obtener un codebook binario
	 * segun Shanon 
	 * 
	 * @return
	 */
	public CodeBook getCodificacion(){
		CodeBook res =new CodeBook();
		int longitud;
		Codigo generador;
		String codigoCandidato;
		for(int indice=0; indice<probabilidades.size(); indice++){
			double probabilidad = probabilidades.get(indice);
			longitud = minimaLongitud(probabilidad);
			boolean encontrado = false;
			while (!encontrado) {
				generador = new Codigo(longitud);
				while (!encontrado && generador.hasNext()) {
					codigoCandidato = generador.next();
					if (!tienePrefijos(codigoCandidato, res.codes())) {
						// Añade el codigo
						res.add(alfabeto.get(indice), codigoCandidato);
						// Sale del bucle
						encontrado = true;
					}
				}
				longitud++;
			}
		}		
		return res;
	}
	
	/**
	 * Metodo encargado de obtener un codebook al
	 * alfabeto binario segun Huffman
	 * 
	 * @param alfabetoDestino
	 * @return
	 */
	public CodeBook getOptimalCodificacion(){
		CodeBook res =new CodeBook();
		TreeMap<String, Double> arbol = new TreeMap<String, Double>();
		
		LinkedList<TreeMap<String, Double>> lista = new LinkedList<TreeMap<String, Double>>();
		Iterator<TreeMap<String, Double>> itArbol;
		
		// Creo un arbol inicial 
		for(int i=0;i<this.alfabeto.size();i++){
			String tmp="";
			tmp+=this.alfabeto.get(i);
			arbol.put(""+(this.alfabeto.get(i)),this.probabilidades.get(i));
		}
		
		
		TreeMap<String, Double> arbolNuevo;
		while(arbol.size()!=1){
			lista.addFirst(arbol);
			arbolNuevo = new TreeMap<String, Double>();

			arbolNuevo.putAll(arbol);
			Entry<String, Double> minimo1 = minimaProbabilidad(arbolNuevo);
			arbolNuevo.remove(minimo1.getKey());
			Entry<String, Double> minimo2 = minimaProbabilidad(arbolNuevo);
			arbolNuevo.remove(minimo2.getKey());			
			
			arbolNuevo.put(minimo1.getKey()+minimo2.getKey(),minimo1.getValue()+minimo2.getValue());
			arbol = arbolNuevo;
		}
		
		// Al llegar aquí ya tengo el arbol construido. Como el paso 1.
		// Ahora tengo que hacer el paso 2
		TreeMap<String, String> arbolCod = new TreeMap<String, String>();
		itArbol = lista.iterator();
		while(itArbol.hasNext()){
			arbolNuevo = itArbol.next();
			for(Entry<String, Double> entrada : arbol.entrySet()){
				int i=0;
				while(i<entrada.getKey().length() && !arbolNuevo.containsKey(entrada.getKey().substring(0, i))){
					i++;
				}
				
				if(arbolCod.containsKey(entrada.getKey())){
					// Es un nodo que estaba en el arbol
					Entry<String, String> entradaAntigua = arbolCod.ceilingEntry(entrada.getKey());
					// Agrega el nodo izquierdo. Si tiene longitud y no estaba antes
					if ((entradaAntigua.getKey().substring(0, i).length()>=1) && (!arbolCod.containsKey(entradaAntigua.getKey().substring(0, i)))){
						arbolCod.put(entradaAntigua.getKey().substring(0, i), entradaAntigua.getValue()+"0");
					}
					// Agrega el nodo derecho. Si tiene longitud y no estaba antes
					if ((entradaAntigua.getKey().substring(i).length()>=1) && (!arbolCod.containsKey(entradaAntigua.getKey().substring(i)))){
						arbolCod.put(entradaAntigua.getKey().substring(i), entradaAntigua.getValue()+"1");
					}
					// Borra el nodo antiguo si es diferente de longitud 1
					if (entradaAntigua.getKey().length()!=1){
						arbolCod.remove(entradaAntigua.getKey());
					}

				// Por aquí solo vendría la primera vez
				}else{
					// Agrega el nodo izquierdo
					arbolCod.put(entrada.getKey().substring(0, i), "0");
					// Agrega el nodo derecho
					arbolCod.put(entrada.getKey().substring(i), "1");
				}				
			}
			arbol=arbolNuevo;
		}
		for(Entry<String,String> c : arbolCod.entrySet()){
			if (c.getKey().length() == 1) res.add(c.getKey().charAt(0), c.getValue());
		}
		return res;
	}
	
	/**
	 * Metodo encargado de devolver una lista de 
	 * numSimbolos del alfabeto origen
	 * y sus correspondientes codificaciones binarias según Shannon-Fano.
	 * 
	 * @param numSimbolos
	 * @return
	 */
	public SimbolosCodificados getSimbolosCodificados(int numSimbolos){
		List<Character> cadenaACodificar = (List<Character>) this.getSimbolos(numSimbolos);
		CodeBook codeBook = this.getCodificacion();
		List<String> cadenaCodificada = new ArrayList<String>();
		for (Character c : cadenaACodificar){
			cadenaCodificada.add(codeBook.get(c));
		}
		SimbolosCodificados res = new SimbolosCodificados(cadenaACodificar,cadenaCodificada);
		
		return res;
	}
	
	/**
	 * Metodo encargado de devolver una lista de 
	 * numSimbolos del alfabeto origen
	 * y sus correspondientes codificaciones binarias en el
	 * alfabeto destino segun hufman
	 * 
	 * @param numSimbolos
	 * @return
	 */
	public SimbolosCodificados getSimbolosCodificadosOptimos(int numSimbolos){
		List<Character> cadenaACodificar = (List<Character>) this.getSimbolos(numSimbolos);
		CodeBook codeBook = this.getOptimalCodificacion();
		List<String> cadenaCodificada = new ArrayList<String>();
		for (Character c : cadenaACodificar){
			cadenaCodificada.add(codeBook.get(c));
		}
		SimbolosCodificados res = new SimbolosCodificados(cadenaACodificar,cadenaCodificada);
		
		return res;
	}
	
	
	//======================metodos auxiliares===================

	
	//====================get and set========================
	
	public Alfabeto getAlfabeto() {
		return alfabeto;
	}

	public void setAlfabeto(Alfabeto alfabeto) {
		this.alfabeto = alfabeto;
	}

	public void setAlfabetoCodificacion(Alfabeto alfabeto) {
	}
	
	public List<?> getProbabilidades() {
		return probabilidades;
	}


	public void setProbabilidades(List<?> probabilidades) {
	}
	
	/**
	 * @brief Devuelve la minima longitud que debe tener un codigo según una probabilidad, Se utiliza en Shanon Fano
	 * @param probabilidad la probabilidad
	 * @return El menor entero que cumple la reestricción
	 */
	private int minimaLongitud(double probabilidad){
		double res = (Math.log10(1/probabilidad))/(Math.log10(BASE));
		return ((int)Math.ceil(res));
	}
	/**
	 * @brief Devuelve cierto si alguna cadena del segundo parametro es prefijo de la primera
	 * @param codigo La cadena a la que se le buscan prefijos
	 * @param codigos Todos los codigos en los que se buscarán prefijos
	 * @return Cierto si existe algún prefijo, falso en caso contrario
	 */
	private boolean tienePrefijos(String codigo, List<String> codigos){
		boolean res = false;
		
		for(String cod : codigos){
			if (cod.length()<= codigo.length()){
				res = codigo.substring(0, cod.length()).compareTo(cod) == 0;
				if (res) break;
			}
		}
		return res;
	}
	private Entry<String,Double> minimaProbabilidad(TreeMap<String, Double> a){
		
		Entry<String, Double> menor = null;
		
		for(Entry<String, Double> entrada : a.entrySet()){
			if((menor == null)||(menor.getValue()>=entrada.getValue())){
				menor = a.ceilingEntry(entrada.getKey());
			}
		}
		return menor;
	}
}