package practica0;

import java.util.ArrayList;
import java.util.List;

public class CCC extends Codificacion {

	private static final int[] PESOS = { 1, 2, 4, 8, 5, 10, 9, 7, 3, 6 };
	private static final int MODULO = 11;

	@Override
	public boolean verificar(String codigo) {
		String eeee = codigo.substring(0, 4);
		String oooo = codigo.substring(4, 8);
		String dd = codigo.substring(8, 10);
		String cuenta = codigo.substring(10);
		return (eeee + oooo + dd + cuenta).equals(generarCodigoControl(eeee
				+ oooo + cuenta));
	}

	@Override
	public String generarCodigoControl(String codigo) {
		String codEntidad = codigo.substring(0, 4);
		String codOficina = codigo.substring(4, 8);
		String codCuenta = codigo.substring(8);

		String codComprobar = "00" + codEntidad + codOficina;
		int suma = 0;
		for (int i = 0; i < codComprobar.length(); i++) {
			suma += Integer.parseInt(String.valueOf(codComprobar.charAt(i)))
					* PESOS[i];
		}

		int mod = MODULO - (suma % MODULO);
		if (mod == 11)
			mod = 0;
		else if (mod == 10)
			mod = 1;

		String res = String.valueOf(mod);

		suma = 0;
		for (int i = 0; i < codCuenta.length(); i++) {
			suma += Integer.parseInt(String.valueOf(codCuenta.charAt(i)))
					* PESOS[i];
		}

		mod = MODULO - (suma % MODULO);
		if (mod == 11)
			mod = 0;
		else if (mod == 10)
			mod = 1;

		res = res + String.valueOf(mod);

		return codEntidad + codOficina + res + codCuenta;
	}

	@Override
	public String[] corregirDatos(String codigo) {
		String codEntidad = codigo.substring(0, 4);
		String codOficina = codigo.substring(4, 8);
		String control = codigo.substring(8, 10);
		String codCuenta = codigo.substring(10);

		String codComprobar = "00" + codEntidad + codOficina;
		int suma = 0;
		for (int i = 0; i < codComprobar.length(); i++) {
			suma += Integer.parseInt(String.valueOf(codComprobar.charAt(i)))
					* PESOS[i];
		}

		int mod = MODULO - (suma % MODULO);
		if (mod == 11)
			mod = 0;
		else if (mod == 10)
			mod = 1;

		String res = String.valueOf(mod);
		
		List<String> lista = new ArrayList<String>();
		if (res.charAt(0) != control.charAt(0)){ // corregir entidad y oficina
			// diferencia entre modulos
			
		}

		suma = 0;
		for (int i = 0; i < codCuenta.length(); i++) {
			suma += Integer.parseInt(String.valueOf(codCuenta.charAt(i)))
					* PESOS[i];
		}

		mod = MODULO - (suma % MODULO);
		if (mod == 11)
			mod = 0;
		else if (mod == 10)
			mod = 1;
		
		res = res + String.valueOf(mod);
		
		
		if (res.charAt(1) != control.charAt(1)){ // corregir cuenta
			
		}

		String[] array = new String[lista.size()];
		for (int i = 0; i < lista.size(); i++) {
			array[i] = lista.get(i);
		}
		return array;
	}
	
}
