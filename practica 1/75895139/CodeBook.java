package practica1;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * Un CodeBook representa un conjunto de s�mbolos de un alfabeto 
 * junto con su codificaci�n en otro alfabeto.
 * 	s1 -> c(s1)
 * 	s2 -> c(s2)
 * 	...
 * 	sn -> c(sn)

 *
 */
public class CodeBook {

	private Alfabeto ent;
	private Alfabeto sal;
	
	/**
	 * Los elementos se almacenan ordenados lexicograficamente.
	 */
	private TreeMap<Character, String> codebook;
	
	/**
	 * Crea un codebook vac�o.
	 */
	public CodeBook() {
		this.codebook = new TreeMap<Character, String>();
	}
	
	/*
	 * Crea un CodeBook dados un alfabeto de entrada y otro de salida
	 */
	public CodeBook(Alfabeto ent, Alfabeto sal){
		this.codebook = new TreeMap<Character, String>();
		this.ent = ent;
		this.sal = sal;
	}
	
	/**
	 * A�ade un elemento al codebook.
	 * 
	 * @param c	S�mbolo.
	 * @param s	S�mbolo codificado.
	 */
	public void add(char c, String s) {
		this.codebook.put(c, s);
	}
	
	/**
	 * Elimina un elemento del codebook.
	 * 
	 * @param c	S�mbolo a eliminar.
	 */
	public void erase(char c) {
		this.codebook.remove(c);
	}
	
	/**
	 * Obtiene el c�digo de un s�mbolo.
	 * 
	 * @param c	S�mbolo.
	 * @return	S�mbolo codificado.
	 */
	public String get(char c) {
		return this.codebook.get(c);
	}
	
	/**
	 * 
	 * @return	Todos los c�digos del codebook.
	 */
	public List<String> codes() {
		return new ArrayList<String>(this.codebook.values());
	}
	
	/**
	 * 
	 * @return	Todos los s�mbolos del codebook.
	 */
	public List<Character> simbols() {
		return new ArrayList<Character>(this.codebook.keySet());
	}
	
	/**
	 * Tama�o del codebook.
	 * 
	 * @return	N�mero de elementos del codebook.
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
