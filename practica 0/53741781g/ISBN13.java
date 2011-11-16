/**
 * @author Pedro Antonio Pardal Jimena
 */

package practica0;

public class ISBN13 extends ISBN
{
	@Override
	public int getModulo()
	{
		return 10;
	}

	@Override
	public int getPeso( int posicion )
	{
		if ( ( posicion % 2 ) == 0 )
			return 3;
		else
			return 1;
	}

	@Override
	public String generarCodigoControl( String isbn )
	{
		isbn = isbn.replaceAll( "-", "" );

		int resultado = ( calcularSuma( isbn ) % getModulo() );

		return isbn + ( getModulo() - resultado ) % getModulo();
	}
}
