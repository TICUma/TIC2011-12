package practica1;

public class Simbolo implements Comparable<Simbolo>
{
	private char _simbolo;
	private double _frecuencia;

	public char getSimbolo()
	{
		return _simbolo;
	}

	public double getFrecuencia()
	{
		return _frecuencia;
	}

	public Simbolo( char simbolo, double frecuencia )
	{
		_simbolo = simbolo;
		_frecuencia = frecuencia;
	}

	@Override
	public boolean equals( Object o )
	{
		if ( !( o instanceof Simbolo ) )
			return false;

		Simbolo otro = (Simbolo) o;

		return _simbolo == otro.getSimbolo()
				&& _frecuencia == otro.getFrecuencia();
	}

	@Override
	public int compareTo( Simbolo otro )
	{
		return -(int) ( 1000 * ( _frecuencia - otro.getFrecuencia() ) + ( _simbolo - otro
				.getSimbolo() ) );
	}

	@Override
	public String toString()
	{
		return "(" + _simbolo + ", " + _frecuencia + ")";
	}
}
