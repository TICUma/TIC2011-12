/**
 * @author Pedro Antonio Pardal Jimena
 */

package practica1;

import java.util.Arrays;

public class Probabilidades
{
	private double[] _probs;

	private boolean verificar()
	{
		double suma = 0.0;

		for ( double d : _probs )
		{
			suma += d;
		}

		return suma == 1.0;
	}

	public Probabilidades( double... probs )
	{
		_probs = probs;

		if ( !verificar() )
			throw new IllegalArgumentException( "Las probabilidades no suman 1" );
	}

	public double get( int i )
	{
		return _probs[i];
	}

	public int size()
	{
		return _probs.length;
	}

	public static Probabilidades getDefault( Alfabeto alfabeto )
	{
		double[] probs = new double[alfabeto.size()];

		Arrays.fill( probs, 1.0 / alfabeto.size() );
			
		return new Probabilidades( probs );
	}
}
