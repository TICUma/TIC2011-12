/**
 * @author Pedro Antonio Pardal Jimena
 */

package practica0;

import junit.framework.Assert;

import org.junit.Test;

public class Test_CCC
{
	@Test
	public void Test_Verif()
	{
		Codificacion cod = new CCC();

		Assert.assertTrue( cod.verificar( "1234 5678 06 1234567890" ) );
		Assert.assertTrue( !cod.verificar( "1234 5678 06 0234567890" ) );
		Assert.assertTrue( !cod.verificar( "1235 5678 06 0234567890" ) );
		Assert.assertTrue( !cod.verificar( "1234 6678 06 0234567890" ) );
	}

	@Test
	public void Test_GenControl()
	{
		Codificacion cod = new CCC();

		Assert.assertEquals( cod.generarCodigoControl( "1234 5678 99 1234567890" ),
				"1234 5678 06 1234567890" );
	}

	@Test
	public void Test_CorrDatos1()
	{
		// El error está en la cuenta bancaria
		
		Codificacion cod = new CCC();

		for ( String s : cod.corregirDatos( "1235 5678 06 1234567890" ) )
		{
			Assert.assertTrue( cod.verificar( s ) );
		}
	}
	
	@Test
	public void Test_CorrDatos2()
	{
		// El error está en el número de cuenta
		
		Codificacion cod = new CCC();

		for ( String s : cod.corregirDatos( "1234 5678 06 0234567890" ) )
		{
			Assert.assertTrue( cod.verificar( s ) );
		}
	}
}
