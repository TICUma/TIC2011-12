package codification;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import tree.ArbolGeneral;
import tree.ArbolLista;

public class Source {

	private Alphabet alphabet;
	private List<Float> probability;

	public Source(Alphabet alphabet, List<Float> probability) {
		this.alphabet = alphabet;
		this.probability = probability;
	}

	public Source(Alphabet alphabet) {
		this.alphabet = alphabet;
		probability = new ArrayList<Float>();
		for (int i = 0; i < alphabet.size(); i++) {
			probability.add((float) (1 / alphabet.size()));
		}
	}

	/**
	 * Metodo que genera la lista de sinbolos del alfabeto segun sus
	 * probabilidades
	 * 
	 * @param msgLength
	 * @return
	 */
	public List<Character> getSymbols(int msgLength) {
		List<Character> res = new ArrayList<Character>();
		for (int i = 0; i < msgLength; i++) {
			float prob = new Random().nextFloat();
			float suma = 0.0f;
			int j;
			for (j = 0; j < alphabet.size(); j++) {
				suma += probability.get(j);
				if (suma > prob)
					break;
			}
			res.add(alphabet.getSymbols().get(j));
		}
		return res;
	}

	/**
	 * Metodo encargado de obtener un codebook binario segun Shanon
	 * 
	 * @return
	 */
	public CodeBook getCodificacion() {
		CodeBook cb = new CodeBook();
		List<String> nivel = new ArrayList<String>();
		nivel.add("0");
		nivel.add("1");
		Map<Integer, Integer> n = calcN(calcY(2));
		int symbolIndex = 0;
		for (Integer y : n.keySet()) {
			int i;
			// Agregamos los elementos del nivel
			for (i = 0; i < n.get(y); i++) {
				cb.add(alphabet.getSymbols().get(symbolIndex), nivel.get(0));
				nivel.remove(0);
				symbolIndex++;
			}
			// Generamos el nuevo nivel con los elementos no asignados
			List<String> nuevoNivel = new ArrayList<String>();
			for (String c : nivel) {
				nuevoNivel.add(c + "0");
				nuevoNivel.add(c + "1");
			}
			nivel = nuevoNivel;
		}

		return cb;
	}

	/**
	 * Metodo encargado de obtener un codebook al alfabeto binario segun Huffman
	 * 
	 * @param alfabetoDestino
	 * @return
	 */
	public CodeBook getOptimalCodificacion() {
		ArbolGeneral<NodoHuffman> arbol = arbolHuffman();
		CodeBook cb = new CodeBook();
		generarCodeBookArbolHuffman(arbol, cb);
		return cb;
	}

	/**
	 * Metodo encargado de devolver una lista de numSimbolos del alfabeto origen
	 * y sus correspondientes codificaciones binarias según Shannon-Fano.
	 * 
	 * @param numSimbolos
	 * @return
	 */
	public SimbolosCodificados getSimbolosCodificados(int numSimbolos) {
		CodeBook cb = getCodificacion();
		List<Character> msg = getSymbols(numSimbolos);
		List<String> cod = new ArrayList<String>();
		for (Character c : msg) {
			cod.add(cb.get(c));
		}

		return new SimbolosCodificados(msg, cod);
	}

	/**
	 * Metodo encargado de devolver una lista de numSimbolos del alfabeto origen
	 * y sus correspondientes codificaciones binarias en el alfabeto destino
	 * segun hufman
	 * 
	 * @param numSimbolos
	 * @return
	 */
	public SimbolosCodificados getSimbolosCodificadosOptimos(int numSimbolos) {
		return null;
	}

	// ======================metodos auxiliares===================

	public float entropy(int base) {
		float entropy = 0.0f;
		for (float p : probability) {
			entropy += p * Math.log10(1 / p) / Math.log10(base);
		}
		return entropy;
	}

	public List<Integer> calcY(int base) {
		List<Integer> y = new ArrayList<Integer>();
		for (Float p : probability) {
			y.add((int) Math.ceil(Math.log10(1 / p) / Math.log10(base)));
		}
		return y;
	}

	public Map<Integer, Integer> calcN(List<Integer> y) {
		Map<Integer, Integer> n = new HashMap<Integer, Integer>();
		for (Integer i : y) {
			if (!n.containsKey(i)) {
				n.put(i, 1);
			} else {
				n.put(i, n.get(i) + 1);
			}
		}
		return n;
	}

	public ArbolGeneral<NodoHuffman> arbolHuffman() {
		ArbolGeneral<NodoHuffman> arbol = new ArbolLista<NodoHuffman>(
				new NodoHuffman(null, 1.0f, ""));
		for (int i = 0; i < probability.size(); i++) {
			arbol.agrega(new ArbolLista<NodoHuffman>(new NodoHuffman(alphabet
					.getSymbols().get(i), probability.get(i), "")));
		}

		while (arbol.subarboles().size() != 2) {
			arbol = generarNuevoArbol(arbol);
		}

		return arbol;
	}
	
	public void generarCodeBookArbolHuffman(ArbolGeneral<NodoHuffman> arbol, CodeBook cb){
		if (!arbol.subarboles().isEmpty()){
			Iterator<ArbolGeneral<NodoHuffman>> it = arbol.subarboles().iterator();
			ArbolGeneral<NodoHuffman> primero = it.next();
			ArbolGeneral<NodoHuffman> segundo = it.next();
			primero.raiz().setCodification(arbol.raiz().getCodification() + "1");
			segundo.raiz().setCodification(arbol.raiz().getCodification() + "0");
			generarCodeBookArbolHuffman(primero, cb);
			generarCodeBookArbolHuffman(segundo, cb);
		}else{
			cb.add(arbol.raiz().getSymbol(), arbol.raiz().getCodification());
		}
	}

	public ArbolGeneral<NodoHuffman> generarNuevoArbol(
			ArbolGeneral<NodoHuffman> nivel) {
		if (nivel.subarboles().size() == 2) {
			return null;
		}
		Iterator<ArbolGeneral<NodoHuffman>> it = nivel.subarboles().iterator();
		// obtener los menores y el resto
		ArbolGeneral<NodoHuffman> primero = it.next();
		ArbolGeneral<NodoHuffman> segundo = it.next();

		ArbolGeneral<NodoHuffman> nuevoNivel = new ArbolLista<NodoHuffman>(
				new NodoHuffman(null, primero.raiz().getProbability()
						+ segundo.raiz().getProbability(), ""));
		nuevoNivel.agrega(primero);
		nuevoNivel.agrega(segundo);

		ArbolGeneral<NodoHuffman> nuevoArbol = new ArbolLista<NodoHuffman>(
				new NodoHuffman(nivel.raiz().getSymbol(), nivel.raiz()
						.getProbability(), nivel.raiz().getCodification()));
		nuevoArbol.agrega(nuevoNivel);
		while (it.hasNext()) {
			nuevoArbol.agrega(it.next());
		}

		return nuevoArbol;
	}

	// ====================get and set========================

	public Alphabet getAlfabeto() {
		return alphabet;
	}

	public void setAlfabeto(Alphabet alfabeto) {
	}

	public void setAlfabetoCodificacion(Alphabet alfabeto) {
	}

	public List<Float> getProbabilidades() {
		return probability;
	}

	public void setProbabilidades(List<Float> probabilidades) {
	}

}
