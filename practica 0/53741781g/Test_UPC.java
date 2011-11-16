/**
 * @author Pedro Antonio Pardal Jimena
 */

package practica0;

import junit.framework.Assert;

import org.junit.Test;

public class Test_UPC
{
	@Test
	public void Test_Verif()
	{
		Codificacion cod = new UPC();

		Assert.assertTrue( cod.verificar( "039400014416" ) );
		Assert.assertTrue( !cod.verificar( "039400014417" ) );
	}

	@Test
	public void Test_GenControl()
	{
		Codificacion cod = new UPC();

		Assert.assertEquals( cod.generarCodigoControl( "03940001441" ),
				"039400014416" );
	}

	@Test
	public void Test_CorrDatos()
	{
		Codificacion cod = new UPC();

		for ( String s : cod.corregirDatos( "039400014417" ) )
		{
			Assert.assertTrue( cod.verificar( s ) );
		}
	}
}
