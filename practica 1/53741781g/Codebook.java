/**
 * @author Pedro Antonio Pardal Jimena
 */

package practica1;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Un Codebook representa un conjunto de símbolos de un alfabeto junto con su
 * codificación en otro alfabeto.
 * 
 * s1 -> c(s1), s2 -> c(s2), ..., sn -> c(sn)
 */
public class Codebook
{
	/**
	 * Los elementos se almacenan ordenados lexicográficamente
	 */
	private SortedMap<Character, String> _codebook;

	/**
	 * Crea un Codebook vacío.
	 */
	public Codebook()
	{
		_codebook = new TreeMap<Character, String>();
	}

	/**
	 * Añade un elemento al Codebook.
	 * 
	 * @param c
	 *            Símbolo.
	 * @param s
	 *            Símbolo codificado.
	 */
	public void add( char c, String s )
	{
		_codebook.put( c, s );
	}

	/**
	 * Elimina un elemento del Codebook.
	 * 
	 * @param c
	 *            Símbolo a eliminar.
	 */
	public void erase( char c )
	{
		_codebook.remove( c );
	}

	/**
	 * Obtiene el código de un símbolo.
	 * 
	 * @param c
	 *            Símbolo.
	 * @return Símbolo codificado.
	 */
	public String get( char c )
	{
		return _codebook.get( c );
	}

	/**
	 * @return Todos los símbolos del Codebook.
	 */
	public List<Character> simbols()
	{
		return new ArrayList<Character>( _codebook.keySet() );
	}

	/**
	 * @return Todos los códigos del Codebook.
	 */
	public List<String> codes()
	{
		return new ArrayList<String>( _codebook.values() );
	}

	/**
	 * Tamaño del Codebook.
	 * 
	 * @return Número de elementos del Codebook.
	 */
	public int size()
	{
		return _codebook.size();
	}

	@Override
	public String toString()
	{
		StringBuilder sb = new StringBuilder();

		for ( char c : simbols() )
		{
			sb.append( c + " -> " + get( c ) + "\n" );
		}

		return sb.toString();
	}
}