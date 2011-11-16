/**
 * @author Pedro Antonio Pardal Jimena
 */

package practica1;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class FuenteShannon extends Fuente
{
	public FuenteShannon( Alfabeto origen )
	{
		super( origen );
	}

	public FuenteShannon( Alfabeto origen, Probabilidades probs )
	{
		super( origen, probs );
	}

	public FuenteShannon( Alfabeto origen, Probabilidades probs,
			Alfabeto destino )
	{
		super( origen, probs, destino );
	}

	@Override
	protected Codebook buildCodebook()
	{
		int[] y = calcularLongitudes();
		int[] n = calcularNiveles( y );

		// Util.printArray( y );
		// Util.printArray( n );

		return construirCodigo( n );
	}

	/**
	 * Calcula la sucesión de valores y_i óptima según la regla de Shannon-Fano.
	 */
	private int[] calcularLongitudes()
	{
		int[] y = new int[_simbolos.length];

		int b = _destino.size();

		for ( int i = 0; i < _simbolos.length; i++ )
		{
			double c = 1.0 / _simbolos[i].getFrecuencia();

			y[i] = 0;

			while ( Math.pow( b, y[i] ) < c )
				y[i]++;
		}

		return y;
	}

	/**
	 * Calcula los coeficientes n_i a partir de los y_i.
	 */
	private int[] calcularNiveles( int[] y )
	{
		int[] n = new int[Util.maximo( y )];

		Arrays.fill( n, 0 );

		for ( int i = 0; i < y.length; i++ )
		{
			n[y[i] - 1]++;
		}

		return n;
	}

	private Queue<Simbolo> getColaSimbolos()
	{
		LinkedList<Simbolo> list = new LinkedList<Simbolo>();

		list.addAll( Arrays.asList( _simbolos ) );

		return list;
	}

	/**
	 * Construye un Codebook a partir de la sucesión de parámetros n_i para esta
	 * fuente.
	 */
	private Codebook construirCodigo( int[] n )
	{
		Queue<Simbolo> simbolos = getColaSimbolos();

		Codebook codebook = new Codebook();

		rellenarCodebook( codebook, n, 0, simbolos, "" );

		return codebook;
	}

	private void rellenarCodebook( Codebook codebook, int[] n, int nivel,
			Queue<Simbolo> simbolos, String prefijo )
	{
		if ( nivel < n.length )
		{
			for ( int i = 0; i < _destino.size(); i++ )
			{
				String codigo = prefijo + _destino.get( i );

				if ( n[nivel] > 0 )
				{
					codebook.add( simbolos.poll().getSimbolo(), codigo );

					--n[nivel];
				}
				else if ( acabado( n ) )
				{
					return;
				}
				else
				{
					rellenarCodebook( codebook, n, nivel + 1, simbolos, codigo );
				}
			}
		}
	}

	private boolean acabado( int[] n )
	{
		return Util.allEqual( n, 0 );
	}
}
