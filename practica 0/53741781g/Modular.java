/**
 * @author Pedro Antonio Pardal Jimena
 */

package practica0;

public class Modular
{
	public static long mcd( long a, long b )
	{
		long r;

		while ( b != 0 )
		{
			r = a % b;
			a = b;
			b = r;
		}

		return a;
	}

	public static long[] euclidesExtendido( long a, long b )
	{
		long[] resp = new long[3];
		long x = 0, y = 0;

		if ( b == 0 )
		{
			resp[0] = a;
			resp[1] = 1;
			resp[2] = 0;
		}
		else
		{
			long x2 = 1, x1 = 0, y2 = 0, y1 = 1;
			long q = 0, r = 0;

			while ( b > 0 )
			{
				q = ( a / b );
				r = a - q * b;
				x = x2 - q * x1;
				y = y2 - q * y1;
				a = b;
				b = r;
				x2 = x1;
				x1 = x;
				y2 = y1;
				y1 = y;
			}

			resp[0] = a;
			resp[1] = x2;
			resp[2] = y2;
		}

		return resp;
	}

	public static long inversoModular( long a, long n )
	{
		long[] resp = new long[3];

		resp = euclidesExtendido( a, n );

		if ( resp[0] > 1 )
		{
			return -1;
		}
		else
		{
			if ( resp[1] < 0 )
				return resp[1] + n;
			else
				return resp[1];
		}
	}

	public static long modulo( long a, long m )
	{
		while ( a < 0 )
			a += m;

		while ( a >= m )
			a -= m;

		return a;
	}
}
