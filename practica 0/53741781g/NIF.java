/**
 * @author Pedro Antonio Pardal Jimena
 */

package practica0;

import java.util.List;
import java.util.LinkedList;

public class NIF extends Codificacion
{
	private static final String NIF_TABLA = "TRWAGMYFPDXBNJZSQVHLCKE";

	@Override
	public boolean verificar( String nif )
	{
		int dni = Integer.parseInt( nif.substring( 0, nif.length() - 1 ) );

		try
		{
			return NIF_TABLA.charAt( dni % NIF_TABLA.length() ) == nif
					.charAt( nif.length() - 1 );
		}
		catch ( NumberFormatException e )
		{
			return false;
		}
	}

	@Override
	public String generarCodigoControl( String nif )
	{
		if ( nif != null )
		{
			int dni = Integer.parseInt( nif, 10 );

			return nif + NIF_TABLA.charAt( dni % NIF_TABLA.length() );
		}

		return null;
	}

	@Override
	public String[] corregirDatos( String nif )
	{
		List<String> corregidos = new LinkedList<String>();

		if ( verificar( nif ) )
		{
			corregidos.add( nif );
		}
		else
		{
			for ( int i = 0; i < nif.length() - 1; i++ )
			{
				String p1 = nif.substring( 0, i );
				String p2 = nif.substring( i + 1, nif.length() );

				for ( int j = 0; j < 10; j++ )
				{
					String nif2 = p1 + String.valueOf( j ) + p2;

					if ( verificar( nif2 ) )
					{
						corregidos.add( nif2 );
					}
				}
			}
		}

		return corregidos.toArray( new String[] {} );
	}
}
