/**
 * @author Pedro Antonio Pardal Jimena
 */

package practica0;

public class Main
{
	public static void main( String[] args )
	{
		Codificacion cod = new CCC();

		/*
		String codigoCCC = "1234 5678 06 1234567890";

		System.out.println( codigoCCC + " "
				+ ( cod.verificar( codigoCCC ) ? "valido" : "no valido" ) );

		codigoCCC = cod.generarCodigoControl( codigoCCC );
		System.out.println( codigoCCC );
		System.out.println( codigoCCC + " "
				+ ( cod.verificar( codigoCCC ) ? "valido" : "no valido" ) );
		*/

		for ( String s : cod.corregirDatos( "1234 5678 06 0234567890" ) )
		{
			System.out.print( s + " " );
			System.out.println( cod.verificar( s ) ? "valido" : "no valido" );
		}
	}
}
