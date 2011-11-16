/**
 * @author Pedro Antonio Pardal Jimena
 */

package practica1;

public class Alfabeto
{
	public static final Alfabeto BINARIO = new Alfabeto( '0', '1' );

	private char[] _simbolos;

	public Alfabeto( char... simbolos )
	{
		_simbolos = simbolos;
	}

	public char get( int i )
	{
		return _simbolos[i];
	}

	public int size()
	{
		return _simbolos.length;
	}
}
