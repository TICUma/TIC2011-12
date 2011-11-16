/**
 * @author Pedro Antonio Pardal Jimena
 */

package practica0;

import junit.framework.Assert;

import org.junit.Test;

public class Test_ISBN10
{
	@Test
	public void Test_Verif()
	{
		Codificacion cod = new ISBN10();

		Assert.assertTrue( cod.verificar( "0444851933" ) );
		Assert.assertTrue( !cod.verificar( "0444851938" ) );
	}

	@Test
	public void Test_GenControl()
	{
		Codificacion cod = new ISBN10();

		Assert.assertEquals( cod.generarCodigoControl( "0-444-85193" ),
				"0444851933" );
	}

	@Test
	public void Test_CorrDatos()
	{
		Codificacion cod = new ISBN10();

		for ( String s : cod.corregirDatos( "0-444-85193-8" ) )
		{
			Assert.assertTrue( cod.verificar( s ) );
		}
	}
}
