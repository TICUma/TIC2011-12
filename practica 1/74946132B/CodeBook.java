package practica1;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * Un CodeBook representa un conjunto de simbolos de un alfabeto 
 * junto con su codificacion en otro alfabeto.
 * 	s1 -> c(s1)
 * 	s2 -> c(s2)
 * 	...
 * 	sn -> c(sn)

 *
 */
public class CodeBook {

	/**
	 * Los elementos se almacenan ordenados lexicograficamente.
	 */
	private TreeMap<Character, String> codebook;
	
	/**
	 * Crea un codebook vacio.
	 */
	public CodeBook() {
		this.codebook = new TreeMap<Character, String>();
	}
	
	/**
	 * AÃ±ade un elemento al codebook.
	 * 
	 * @param c	SÃ­mbolo.
	 * @param s	SÃ­mbolo codificado.
	 */
	public void add(char c, String s) {
		this.codebook.put(c, s);
	}
	
	/**
	 * Elimina un elemento del codebook.
	 * 
	 * @param c	SÃ­mbolo a eliminar.
	 */
	public void erase(char c) {
		this.codebook.remove(c);
	}
	
	/**
	 * Obtiene el cÃ³digo de un sÃ­mbolo.
	 * 
	 * @param c	SÃ­mbolo.
	 * @return	SÃ­mbolo codificado.
	 */
	public String get(char c) {
		return this.codebook.get(c);
	}
	
	/**
	 * 
	 * @return	Todos los cÃ³digos del codebook.
	 */
	public List<String> codes() {
		return new ArrayList<String>(this.codebook.values());
	}
	
	/**
	 * 
	 * @return	Todos los sÃ­mbolos del codebook.
	 */
	public List<Character> simbols() {
		return new ArrayList<Character>(this.codebook.keySet());
	}
	
	/**
	 * TamaÃ±o del codebook.
	 * 
	 * @return	NÃºmero de elementos del codebook.
	 */
	public int size() {
		return codebook.size();
	}
	
	@Override
	public String toString() {
		String res = new String();
		for (char c : this.codebook.keySet()) {
			res = res.concat(c + " -> " + get(c) + '\n');
		}
		return res;
	}
}
