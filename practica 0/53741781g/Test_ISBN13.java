/**
 * @author Pedro Antonio Pardal Jimena
 */

package practica0;

import junit.framework.Assert;

import org.junit.Test;

public class Test_ISBN13
{
	@Test
	public void Test_Verif()
	{
		Codificacion cod = new ISBN13();

		Assert.assertTrue( cod.verificar( "9788447356027" ) );
		Assert.assertTrue( !cod.verificar( "9788447356024" ) );
	}

	@Test
	public void Test_GenControl()
	{
		Codificacion cod = new ISBN13();

		Assert.assertEquals( cod.generarCodigoControl( "978844735602" ),
				"9788447356027" );
	}

	@Test
	public void Test_CorrDatos()
	{
		Codificacion cod = new ISBN13();

		for ( String s : cod.corregirDatos( "9788447356024" ) )
		{
			Assert.assertTrue( cod.verificar( s ) );
		}
	}
}
