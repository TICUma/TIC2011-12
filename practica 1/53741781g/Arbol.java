/**
 * @author Pedro Antonio Pardal Jimena
 */

package practica1;

import java.util.LinkedList;
import java.util.List;

public class Arbol<T>
{
	private List<Arbol<T>> _hijos;
	private T _elemento;

	public int getNumeroHijos()
	{
		return _hijos.size();
	}

	public Arbol<T> getHijo( int i )
	{
		return _hijos.get( i );
	}

	public void addHijo( Arbol<T> arbol )
	{
		_hijos.add( arbol );
	}

	public T getElemento()
	{
		return _elemento;
	}
	
	public void setElemento( T elemento )
	{
		_elemento = elemento;
	}

	public Arbol()
	{
		this( null, new LinkedList<Arbol<T>>() );
	}

	public Arbol( List<Arbol<T>> hijos )
	{
		this( null, hijos );
	}

	public Arbol( T elemento )
	{
		this( elemento, new LinkedList<Arbol<T>>() );
	}

	public Arbol( T elemento, List<Arbol<T>> hijos )
	{
		_elemento = elemento;
		_hijos = hijos;
	}

	@Override
	public String toString()
	{
		StringBuilder sb = new StringBuilder();

		sb.append( "[ " );
		sb.append( _elemento != null ? _elemento : "()" );
		sb.append( " -> " );

		for ( Arbol<T> a : _hijos )
		{
			sb.append( a.toString() );
			sb.append( " " );
		}

		sb.append( "]" );

		return sb.toString();
	}
}
