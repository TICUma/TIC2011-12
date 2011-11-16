/**
 * @author Pedro Antonio Pardal Jimena
 */

package practica0;

public class ISBN10 extends ISBN
{
	@Override
	public int getModulo()
	{
		return 11;
	}
	
	@Override
	public int getPeso( int posicion )
	{
		return posicion;
	}
}
