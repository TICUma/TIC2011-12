/**
 * @author Pedro Antonio Pardal Jimena
 */

package practica0;

import java.util.LinkedList;
import java.util.List;

public class CCC extends Codificacion
{
	private static final int MODULO = 11;

	private static final int[] PESOS_ENTIDAD = new int[] { 4, 8, 5, 10 };
	private static final int[] PESOS_SUCURSAL = new int[] { 9, 7, 3, 6 };
	private static final int[] PESOS_CUENTA = new int[] { 1, 2, 4, 8, 5, 10, 9,
			7, 3, 6 };

	private class CuentaCorriente
	{
		private String _entidad, _sucursal, _cuenta, _controlBanco,
				_controlCuenta;

		public CuentaCorriente( String numero )
		{
			numero = numero.replaceAll( " ", "" );

			if ( numero.length() != 20 )
				throw new RuntimeException( "Numero de cuenta invalido" );

			_entidad = numero.substring( 0, 4 );
			_sucursal = numero.substring( 4, 8 );
			_cuenta = numero.substring( 10, 20 );

			_controlBanco = numero.substring( 8, 9 );
			_controlCuenta = numero.substring( 9, 10 );
		}

		private CuentaCorriente( String entidad, String sucursal,
				String cuenta, String controlBanco, String controlCuenta )
		{
			_entidad = entidad;
			_sucursal = sucursal;
			_cuenta = cuenta;
			_controlBanco = controlBanco;
			_controlCuenta = controlCuenta;
		}

		private String corregirDato( String dato, int[] pesos, int j, int x )
		{
			String p1 = dato.substring( 0, j );
			String p2 = dato.substring( j + 1, dato.length() );

			int xj = Integer.parseInt( dato.substring( j, j + 1 ) );

			long cj = Modular.modulo(
					( xj - Modular.inversoModular( pesos[j], MODULO ) * x ),
					MODULO );

			if ( cj == 10 )
				return null;

			return p1 + String.valueOf( cj ) + p2;
		}

		public List<String> corregirDigitosEntidad()
		{
			List<String> corregidos = new LinkedList<String>();

			int x = suma( _entidad, PESOS_ENTIDAD )
					+ suma( _sucursal, PESOS_SUCURSAL )
					+ Integer.parseInt( _controlBanco );

			for ( int i = 0; i < 4; i++ )
			{
				String entidadCorregida = corregirDato( _entidad,
						PESOS_ENTIDAD, i, x );

				if ( entidadCorregida != null )
				{
					CuentaCorriente cuentaCorregida = new CuentaCorriente(
							entidadCorregida, _sucursal, _cuenta,
							_controlBanco, _controlCuenta );

					corregidos.add( cuentaCorregida.toString() );
				}
			}

			return corregidos;
		}

		public List<String> corregirDigitosSucursal()
		{
			List<String> corregidos = new LinkedList<String>();

			int x = suma( _entidad, PESOS_ENTIDAD )
					+ suma( _sucursal, PESOS_SUCURSAL )
					+ Integer.parseInt( _controlBanco );

			for ( int i = 0; i < 4; i++ )
			{
				String sucursalCorregida = corregirDato( _sucursal,
						PESOS_SUCURSAL, i, x );

				if ( sucursalCorregida != null )
				{
					CuentaCorriente cuentaCorregida = new CuentaCorriente(
							_entidad, sucursalCorregida, _cuenta,
							_controlBanco, _controlCuenta );

					corregidos.add( cuentaCorregida.toString() );
				}
			}

			return corregidos;
		}

		public List<String> corregirDigitosCuenta()
		{
			List<String> corregidos = new LinkedList<String>();

			int x = suma( _cuenta, PESOS_CUENTA )
					+ Integer.parseInt( _controlCuenta );

			for ( int i = 0; i < 10; i++ )
			{
				String numeroCuentaCorregido = corregirDato( _cuenta,
						PESOS_CUENTA, i, x );

				if ( numeroCuentaCorregido != null )
				{
					CuentaCorriente cuentaCorregida = new CuentaCorriente(
							_entidad, _sucursal, numeroCuentaCorregido,
							_controlBanco, _controlCuenta );

					corregidos.add( cuentaCorregida.toString() );
				}
			}

			return corregidos;
		}

		private int suma( String digitos, int[] pesos )
		{
			int resultado = 0;

			for ( int i = 0; i < digitos.length(); i++ )
			{
				resultado += pesos[i]
						* Integer.parseInt( digitos.substring( i, i + 1 ) );
			}

			return resultado;
		}

		public String generarDigitoControl( int suma )
		{
			int resultado = suma % MODULO;
			resultado = MODULO - resultado;

			if ( resultado == 10 )
				resultado = 1;
			if ( resultado == 11 )
				resultado = 0;

			return String.valueOf( resultado );
		}

		public boolean verificarControlBanco()
		{
			return generarControlBanco().equals( _controlBanco );
		}

		public boolean verificarControlCuenta()
		{
			return generarControlCuenta().equals( _controlCuenta );
		}

		public boolean veriicarControl()
		{
			return verificarControlBanco() && verificarControlCuenta();
		}

		public void corregirControl()
		{
			_controlBanco = generarControlBanco();
			_controlCuenta = generarControlCuenta();
		}

		public String generarControlBanco()
		{
			return generarDigitoControl( suma( _entidad, PESOS_ENTIDAD )
					+ suma( _sucursal, PESOS_SUCURSAL ) );
		}

		public String generarControlCuenta()
		{
			return generarDigitoControl( suma( _cuenta, PESOS_CUENTA ) );
		}

		public String toString()
		{
			return _entidad + " " + _sucursal + " " + _controlBanco
					+ _controlCuenta + " " + _cuenta;
		}
	}

	@Override
	public boolean verificar( String codigo )
	{
		CuentaCorriente cc = new CuentaCorriente( codigo );

		return cc.veriicarControl();
	}

	@Override
	public String generarCodigoControl( String codigo )
	{
		CuentaCorriente cc = new CuentaCorriente( codigo );

		cc.corregirControl();

		return cc.toString();
	}

	@Override
	public String[] corregirDatos( String codigo )
	{
		CuentaCorriente cc = new CuentaCorriente( codigo );

		List<String> corregidas = new LinkedList<String>();

		if ( !cc.verificarControlBanco() )
		{
			corregidas.addAll( cc.corregirDigitosEntidad() );
			corregidas.addAll( cc.corregirDigitosSucursal() );
		}

		if ( !cc.verificarControlCuenta() )
		{
			corregidas.addAll( cc.corregirDigitosCuenta() );
		}

		return corregidas.toArray( new String[] {} );
	}
}
