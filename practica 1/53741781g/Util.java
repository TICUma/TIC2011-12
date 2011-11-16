package practica1;

public class Util
{
	public static int maximo( int[] a )
	{
		int max = a[0];

		for ( int i = 1; i < a.length; i++ )
		{
			if ( a[i] > max )
				max = a[i];
		}

		return max;
	}
	
	public static void printArray( int[] a )
	{
		System.out.print( "[ " );

		for ( int i = 0; i < a.length; i++ )
		{
			System.out.print( a[i] + " " );
		}

		System.out.println( "]" );
	}
	
	public static boolean allEqual( int[] a, int n )
	{
		for ( int i = 0; i < a.length; i++ )
		{
			if ( a[i] != n )
				return false;
		}
		
		return true;
	}
	
	public static double logaritmo( double base, double x )
	{
		return Math.log( x ) / Math.log( base );
	}
}
