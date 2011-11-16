/**
 * @author Pedro Antonio Pardal Jimena
 */

package practica0;

import junit.framework.Assert;
import org.junit.Test;

public class Test_NIF
{
	@Test
	public void Test_Verif()
	{
		Codificacion cod = new NIF();

		Assert.assertTrue( cod.verificar( "55555555K" ) );
		Assert.assertTrue( !cod.verificar( "55555555L" ) );
	}

	@Test
	public void Test_GenControl()
	{
		Codificacion cod = new NIF();

		Assert.assertEquals( cod.generarCodigoControl( "53741781" ),
				"53741781G" );
	}
}
