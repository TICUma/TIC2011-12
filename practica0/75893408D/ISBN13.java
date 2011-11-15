package practica0;

import java.util.List;
import java.util.ArrayList;

/**
 * 
 * @author monte
 */
public class ISBN13 extends Codificacion {

	private static final int MODULO = 10;

	public boolean verificar(String codigo) {
		codigo = codigo.replaceAll("-", "");
		int resultado = 0;
		try {
			for (int i = 0; i < codigo.length(); i++) {
				if (i % 2 == 0)
					resultado += (Integer.parseInt(codigo.substring(i, i + 1)) * 1);
				else
					resultado += (Integer.parseInt(codigo.substring(i, i + 1)) * 3);
			}
		} catch (NumberFormatException e) {
			return false;
		}
		return resultado % MODULO == 0;
	}

	public String generarCodigoControl(String codigo) {
		codigo = codigo.replaceAll("-", "");

		int resultado = 0;

		try {
			for (int i = 0; i < codigo.length(); i++) {
				if (i % 2 == 0)
					resultado += (Integer.parseInt(codigo.substring(i, i + 1)) * 1);
				else
					resultado += (Integer.parseInt(codigo.substring(i, i + 1)) * 3);
			}
		} catch (NumberFormatException e) {
			return null;
		}

		resultado = resultado % MODULO;
		resultado = (MODULO - resultado) % MODULO;
		return codigo + resultado;
	}

	public String[] corregirDatos(String codigo) {
		codigo = codigo.replaceAll("-", "");
		int resultado = 0;
		try {
			for (int i = 0; i < codigo.length(); i++) {
				if (i % 2 == 0)
					resultado += (Integer.parseInt(codigo.substring(i, i + 1)) * 1);
				else
					resultado += (Integer.parseInt(codigo.substring(i, i + 1)) * 3);
			}
		} catch (NumberFormatException e) {
			return new String[0];
		}
		int mod = resultado % MODULO;

		if (mod == 0)
			return new String[0];

		int restoA10 = 10 - mod;
		int restoA0 = mod;

		List<String> lista = new ArrayList<String>();

		for (int i = 0; i < codigo.length(); i++) {
			int valor = Integer.parseInt(codigo.substring(i, i + 1));
			if (i % 2 == 0) { // *1
				if (valor + restoA10 <= 9) {
					
					lista.add(codigo.substring(0, i)
							+ String.valueOf(valor + restoA10)
							+ codigo.substring(i+1));
				}
				
				if (valor - restoA0 >= 0) {
					lista.add(codigo.substring(0, i)
							+ String.valueOf(valor - restoA0)
							+ codigo.substring(i+1));

				}
			} else { // *3
				if (restoA10 % 3 == 0 && valor + (restoA10 / 3) <= 9) {
					lista.add(codigo.substring(0, i)
							+ String.valueOf(valor + (restoA10 / 3))
							+ codigo.substring(i+1));
				}

				if (restoA0 % 3 == 0 && valor - (restoA0 / 3) >= 0) {
					lista.add(codigo.substring(0, i)
							+ String.valueOf(valor - (restoA0 / 3))
							+ codigo.substring(i+1));
				}
			}
		}

		String[] array = new String[lista.size()];
		for (int i = 0; i < lista.size(); i++) {
			array[i] = lista.get(i);
		}
		return array;
	}

}
