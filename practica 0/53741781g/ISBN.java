/**
 * @author Pedro Antonio Pardal Jimena
 */

package practica0;

import java.util.List;
import java.util.LinkedList;

public abstract class ISBN extends Codificacion
{
	public abstract int getModulo();

	public abstract int getPeso( int posicion );

	protected int calcularSuma( String isbn )
	{
		int sum = 0;

		try
		{
			for ( int i = 0; i < isbn.length(); i++ )
			{
				String digito = isbn.substring( i, i + 1 );
				
				int di;
				
				if ( digito == "X" )
					di = 10;
				else
					di = Integer.parseInt( digito );

				sum += getPeso( i + 1 ) * di;
			}
		}

		catch ( NumberFormatException e )
		{
			throw new RuntimeException( e );
		}

		return sum;
	}

	@Override
	public boolean verificar( String isbn )
	{
		isbn = isbn.replaceAll( "-", "" );

		return calcularSuma( isbn ) % getModulo() == 0;
	}

	@Override
	public String generarCodigoControl( String isbn )
	{
		isbn = isbn.replaceAll( "-", "" );

		int control = ( calcularSuma( isbn ) % getModulo() );

		return isbn + ( control == 10 ? "X" : String.valueOf( control ) );
	}

	private String corregir( String isbn, int i, int x )
	{
		String p1 = isbn.substring( 0, i );
		String p2 = isbn.substring( i + 1, isbn.length() );

		int xj = Integer.parseInt( isbn.substring( i, i + 1 ) );

		long cj = Modular.modulo(
				( xj - Modular.inversoModular( getPeso( i + 1 ), getModulo() )
						* x ), getModulo() );

		return p1 + String.valueOf( cj ) + p2;
	}

	@Override
	public String[] corregirDatos( String isbn )
	{
		isbn = isbn.replaceAll( "-", "" );

		List<String> corregidos = new LinkedList<String>();

		if ( verificar( isbn ) )
		{
			corregidos.add( isbn );
		}
		else
		{
			// Por cuestiones de eficiencia lo calculamos aquí.
			int x = calcularSuma( isbn );

			for ( int i = 0; i < isbn.length() - 1; i++ )
			{
				corregidos.add( corregir( isbn, i, x ) );
			}
		}

		return corregidos.toArray( new String[] {} );
	}
}
