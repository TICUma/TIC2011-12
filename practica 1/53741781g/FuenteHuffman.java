/**
 * @author Pedro Antonio Pardal Jimena
 */

package practica1;

import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class FuenteHuffman extends Fuente
{
	public FuenteHuffman( Alfabeto origen )
	{
		super( origen );
	}

	public FuenteHuffman( Alfabeto origen, Probabilidades probs )
	{
		super( origen, probs );
	}

	public FuenteHuffman( Alfabeto origen, Probabilidades probs,
			Alfabeto destino )
	{
		super( origen, probs, destino );
	}

	@Override
	protected Codebook buildCodebook()
	{
		Codebook codebook = new Codebook();

		rellenarCodebook( codebook, construirArbol(), _destino, "" );

		return codebook;
	}

	/**
	 * Rellena el codebook indicado recorriendo el arbol de Huffman
	 * y aplicando la regla H2.
	 */
	private void rellenarCodebook( Codebook codebook, Arbol<Simbolo> arbol,
			Alfabeto destino, String prefijo )
	{
		for ( int i = 0; i < arbol.getNumeroHijos(); i++ )
		{
			Arbol<Simbolo> hijo = arbol.getHijo( i );

			String codigo = prefijo + destino.get( i );

			if ( hijo.getElemento() != null )
			{
				codebook.add( hijo.getElemento().getSimbolo(), codigo );
			}
			else
			{
				rellenarCodebook( codebook, hijo, destino, codigo );
			}
		}
	}

	/**
	 * Construye la estructura de datos básica del algoritmo de Huffman a partir
	 * de los simbolos de la fuente.
	 */
	private Queue<Arbol<Simbolo>> getColaArboles()
	{
		Queue<Arbol<Simbolo>> queue = new PriorityQueue<Arbol<Simbolo>>(
				_simbolos.length, new HuffmanComparator() );

		for ( Simbolo s : _simbolos )
		{
			queue.add( new Arbol<Simbolo>( s ) );
		}

		return queue;
	}

	/**
	 * Construye el arbol de Huffman asociado a esta fuente
	 * utilizando la regla H1.
	 */
	private Arbol<Simbolo> construirArbol()
	{
		Queue<Arbol<Simbolo>> arboles = getColaArboles();

		while ( arboles.size() > 1 )
		{
			List<Arbol<Simbolo>> hijos = new LinkedList<Arbol<Simbolo>>();

			double p = 0.0;

			int n = _destino.size();

			while ( --n >= 0 && arboles.size() > 0 )
			{
				Arbol<Simbolo> sig = arboles.poll();

				p += sig.getElemento().getFrecuencia();

				hijos.add( sig );
			}

			arboles.add( new Arbol<Simbolo>( new Simbolo( '?', p ), hijos ) );
		}

		Arbol<Simbolo> arbol = arboles.poll();

		purgaArbol( arbol );

		return arbol;
	}

	/**
	 * Elimina del árbol los símbolos ficticios introducidos por el
	 * procedimiento construir arbol de Huffman.
	 */
	private void purgaArbol( Arbol<Simbolo> arbol )
	{
		if ( arbol != null )
		{
			int n = arbol.getNumeroHijos();

			if ( n != 0 )
			{
				arbol.setElemento( null );

				for ( int i = 0; i < n; i++ )
				{
					purgaArbol( arbol.getHijo( i ) );
				}
			}
		}
	}
}
