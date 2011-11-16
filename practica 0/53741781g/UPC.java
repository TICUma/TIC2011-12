/**
 * @author Pedro Antonio Pardal Jimena
 */

package practica0;

import java.util.LinkedList;
import java.util.List;

public class UPC extends Codificacion
{
	private ISBN13 _isbn = new ISBN13();

	public boolean verificar( String upc )
	{
		return _isbn.verificar( "0" + upc );
	}

	public String generarCodigoControl( String upc )
	{
		String codigo = _isbn.generarCodigoControl( "0" + upc );

		return codigo.substring( 1, codigo.length() );
	}

	public String[] corregirDatos( String upc )
	{
		List<String> resultado = new LinkedList<String>();
		
		String[] corregidos = _isbn.corregirDatos( "0" + upc );

		for ( int i = 1; i < corregidos.length; i++ )
		{
			resultado.add( corregidos[i].substring( 1, corregidos[i].length() ) );
		}

		return resultado.toArray( new String[] {} );
	}
}
