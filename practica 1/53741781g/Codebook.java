/**
 * @author Pedro Antonio Pardal Jimena
 */

package practica1;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Un Codebook representa un conjunto de s�mbolos de un alfabeto junto con su
 * codificaci�n en otro alfabeto.
 * 
 * s1 -> c(s1), s2 -> c(s2), ..., sn -> c(sn)
 */
public class Codebook
{
	/**
	 * Los elementos se almacenan ordenados lexicogr�ficamente
	 */
	private SortedMap<Character, String> _codebook;

	/**
	 * Crea un Codebook vac�o.
	 */
	public Codebook()
	{
		_codebook = new TreeMap<Character, String>();
	}

	/**
	 * A�ade un elemento al Codebook.
	 * 
	 * @param c
	 *            S�mbolo.
	 * @param s
	 *            S�mbolo codificado.
	 */
	public void add( char c, String s )
	{
		_codebook.put( c, s );
	}

	/**
	 * Elimina un elemento del Codebook.
	 * 
	 * @param c
	 *            S�mbolo a eliminar.
	 */
	public void erase( char c )
	{
		_codebook.remove( c );
	}

	/**
	 * Obtiene el c�digo de un s�mbolo.
	 * 
	 * @param c
	 *            S�mbolo.
	 * @return S�mbolo codificado.
	 */
	public String get( char c )
	{
		return _codebook.get( c );
	}

	/**
	 * @return Todos los s�mbolos del Codebook.
	 */
	public List<Character> simbols()
	{
		return new ArrayList<Character>( _codebook.keySet() );
	}

	/**
	 * @return Todos los c�digos del Codebook.
	 */
	public List<String> codes()
	{
		return new ArrayList<String>( _codebook.values() );
	}

	/**
	 * Tama�o del Codebook.
	 * 
	 * @return N�mero de elementos del Codebook.
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