/**
 * @author Pedro Antonio Pardal Jimena
 */

package practica1;

public class Main
{
	public static void main( String[] args )
	{
		Fuente f;

		// f = prueba1_Shannon();
		// f = prueba1_Huffman();
		// f = ejercicio8_Shannon();
		f = ejercicio8_Huffman();
		// f = ejercicio9_Shannon();
		// f = ejercicio9_Huffman();

		System.out.println( "Entropia: " + f.entropia() );
		System.out.println( "Lmp: " + f.longitudMediaPalabras() );

		System.out.println( f.getCodebook() );

		SimbolosCodificados sc = f.getSimbolosCodificados( 10 );

		System.out.println( sc.getSimbolos() );
		System.out.println( sc.getSimbolosCodificados() );
	}

	@SuppressWarnings( "unused" )
	private static Fuente prueba1_Shannon()
	{
		return new FuenteShannon( new Alfabeto( 'a', 'b', 'c', 'd', 'e' ),
				new Probabilidades( 0.1, 0.2, 0.2, 0.4, 0.1 ) );
	}

	@SuppressWarnings( "unused" )
	private static Fuente prueba1_Huffman()
	{
		return new FuenteHuffman( new Alfabeto( 'a', 'b', 'c', 'd', 'e' ),
				new Probabilidades( 0.1, 0.2, 0.2, 0.4, 0.1 ) );
	}

	@SuppressWarnings( "unused" )
	private static Fuente ejercicio8_Shannon()
	{
		return new FuenteShannon( new Alfabeto( 'a', 'b', 'c', 'd', 'e', 'f' ),
				new Probabilidades( 0.25, 0.10, 0.15, 0.05, 0.20, 0.25 ) );
	}

	@SuppressWarnings( "unused" )
	private static Fuente ejercicio9_Shannon()
	{
		return new FuenteShannon( new Alfabeto( 'a', 'b', 'c' ),
				new Probabilidades( 0.5, 0.3, 0.2 ), new Alfabeto( '0', '1',
						'2' ) );
	}

	@SuppressWarnings( "unused" )
	private static Fuente ejercicio8_Huffman()
	{
		return new FuenteHuffman( new Alfabeto( 'a', 'b', 'c', 'd', 'e', 'f' ),
				new Probabilidades( 0.25, 0.10, 0.15, 0.05, 0.20, 0.25 ) );
	}

	@SuppressWarnings( "unused" )
	private static Fuente ejercicio9_Huffman()
	{
		return new FuenteHuffman( new Alfabeto( 'a', 'b', 'c' ),
				new Probabilidades( 0.5, 0.3, 0.2 ), new Alfabeto( '0', '1',
						'2' ) );
	}
}
