/**
 * @author Pedro Antonio Pardal Jimena
 */

package practica1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public abstract class Fuente
{
	protected Simbolo[] _simbolos;
	protected Alfabeto _destino;

	private Codebook _codebook;
	private Random _random;

	public Fuente( Alfabeto origen )
	{
		this( origen, Probabilidades.getDefault( origen ) );
	}

	public Fuente( Alfabeto origen, Probabilidades probs )
	{
		this( origen, probs, Alfabeto.BINARIO );
	}

	public Fuente( Alfabeto origen, Probabilidades probs, Alfabeto destino )
	{
		_simbolos = buildSimbolSet( origen, probs );
		_destino = destino;
		_codebook = buildCodebook();
		_random = new Random();
	}

	private Simbolo[] buildSimbolSet( Alfabeto a, Probabilidades probs )
	{
		if ( a.size() != probs.size() )
			throw new IllegalArgumentException(
					"Tamaño inadecuado de la distribución de probabilidad" );

		List<Simbolo> simbolos = new ArrayList<Simbolo>( a.size() );

		for ( int i = 0; i < a.size(); i++ )
		{
			simbolos.add( new Simbolo( a.get( i ), probs.get( i ) ) );
		}

		Collections.sort( simbolos );

		return simbolos.toArray( new Simbolo[] {} );
	}

	/**
	 * Construye un Codebook para esta fuente.
	 */
	protected abstract Codebook buildCodebook();

	/**
	 * Devuelve el Codebook usado por esta fuente.
	 */
	public Codebook getCodebook()
	{
		return _codebook;
	}

	/**
	 * Simula la emisión de UN sólo símbolo.
	 */
	private Character getSimbolo()
	{
		int n = _simbolos.length;

		double rnd = _random.nextDouble();
		double suma = 0.0;

		for ( int i = 0; i < n; i++ )
		{
			suma += _simbolos[i].getFrecuencia();

			if ( suma > rnd )
				return _simbolos[i].getSimbolo();
		}

		return null;
	}

	/**
	 * Simula la emisión por parte de la fuente de un numero dado de símbolos,
	 * acorde con la distribución de probabilidad de la misma.
	 */
	public List<Character> getSimbolos( int numeroSimbolos )
	{
		List<Character> simbolos = new ArrayList<Character>( numeroSimbolos );

		for ( int i = 0; i < numeroSimbolos; i++ )
		{
			simbolos.add( getSimbolo() );
		}

		return simbolos;
	}

	/**
	 * Devuelve una lista de numeroSimbolos del alfabeto origen y sus
	 * correspondientes codificaciones.
	 */
	public SimbolosCodificados getSimbolosCodificados( int numeroSimbolos )
	{
		List<Character> simbolos = getSimbolos( numeroSimbolos );

		List<String> simbolosCodificados = new ArrayList<String>(
				numeroSimbolos );

		for ( Character c : simbolos )
		{
			simbolosCodificados.add( _codebook.get( c ) );
		}

		return new SimbolosCodificados( simbolos, simbolosCodificados );
	}

	public double longitudMediaPalabras()
	{
		double suma = 0.0;

		for ( int i = 0; i < _simbolos.length; i++ )
		{
			double yi = _codebook.get( _simbolos[i].getSimbolo() ).length();
			double pi = _simbolos[i].getFrecuencia();

			suma += ( pi * yi );
		}

		return suma;
	}

	/**
	 * Calcula la entropía base b de la fuente, donde b es el tamaño del
	 * alfabeto de destino.
	 */
	public double entropia()
	{
		double suma = 0.0;

		int b = _destino.size();

		for ( int i = 0; i < _simbolos.length; i++ )
		{
			double pi = _simbolos[i].getFrecuencia();

			suma += pi * Util.logaritmo( b, 1.0 / pi );
		}

		return suma;
	}

	@Override
	public String toString()
	{
		StringBuilder sb = new StringBuilder();

		sb.append( "[ " );

		for ( Simbolo s : _simbolos )
		{
			sb.append( s.toString() );
			sb.append( " " );
		}

		sb.append( "]" );

		return sb.toString();
	}
}
