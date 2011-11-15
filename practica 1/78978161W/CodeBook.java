package practica1;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * Un CodeBook representa un conjunto de símbolos de un alfabeto 
 * junto con su codificación en otro alfabeto.
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
	 * Crea un codebook vacío.
	 */
	public CodeBook() {
		this.codebook = new TreeMap<Character, String>();
	}
	
	/**
	 * Añade un elemento al codebook.
	 * 
	 * @param c	Símbolo.
	 * @param s	Símbolo codificado.
	 */
	public void add(char c, String s) {
		this.codebook.put(c, s);
	}
	
	/**
	 * Elimina un elemento del codebook.
	 * 
	 * @param c	Símbolo a eliminar.
	 */
	public void erase(char c) {
		this.codebook.remove(c);
	}
	
	/**
	 * Obtiene el código de un símbolo.
	 * 
	 * @param c	Símbolo.
	 * @return	Símbolo codificado.
	 */
	public String get(char c) {
		return this.codebook.get(c);
	}
	
	/**
	 * 
	 * @return	Todos los códigos del codebook.
	 */
	public List<String> codes() {
		return new ArrayList<String>(this.codebook.values());
	}
	
	/**
	 * 
	 * @return	Todos los símbolos del codebook.
	 */
	public List<Character> simbols() {
		return new ArrayList<Character>(this.codebook.keySet());
	}
	
	/**
	 * Tamaño del codebook.
	 * 
	 * @return	Número de elementos del codebook.
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
