package practica0;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author
 */
public class UPC extends Codificacion {

	private static final int MULTIPLO = 10;

	/**********************
	 * 
	 * @param codigo
	 * @return
	 */
	public boolean verificar(String codigo) {
		int resultado = 0;
		int v;
		try {
			for (int i = 0; i < codigo.length(); i++) {
				v = Integer.parseInt(codigo.substring(i, i + 1));
				resultado += i % 2 == 0 ? v * 3 : v;
			}
		} catch (NumberFormatException e) {
			return false;
		}
		return resultado % MULTIPLO == 0;
	}

	/*******************
	 * 
	 * @param codigo
	 * @return
	 */

	public String corregirControl(String codigo) {
		int i = 0;
		boolean ok = false;
		String codigoCorregido = new String();

		while (i < codigo.length() && !ok) {
			int digito = 0;
			while (digito < 9 && !ok) {
				codigoCorregido = codigo.substring(0, i - 1) + digito
						+ codigo.substring(i + 1, codigo.length());
				if (verificar(codigoCorregido)) {
					ok = true;
				} else {
					digito++;
				}
			}
		}
		return codigoCorregido;
	}

	/**************
	 * 
	 * @param codigo
	 * @return
	 */
	public String generarCodigoControl(String codigo) {
		int resultado = 0;

		for (int i = 0; i < codigo.length(); i++) {
			if (i % 2 == 0)
				resultado += (Integer.parseInt(codigo.substring(i, i + 1)) * 3);
			else
				resultado += (Integer.parseInt(codigo.substring(i, i + 1)) * 1);
		}
		
		resultado = resultado % MULTIPLO;
		resultado = (MULTIPLO - resultado) % MULTIPLO;
		return codigo + resultado;
	}

	public String[] corregirDatos(String codigo) {
		codigo = codigo.replaceAll("-", "");
		int resultado = 0;
		try {
			for (int i = 0; i < codigo.length(); i++) {
				if (i % 2 == 0)
					resultado += (Integer.parseInt(codigo.substring(i, i + 1)) * 3);
				else
					resultado += (Integer.parseInt(codigo.substring(i, i + 1)) * 1);
			}
		} catch (NumberFormatException e) {
			return new String[0];
		}
		int mod = resultado % MULTIPLO;

		if (mod == 0)
			return new String[0];

		int restoA10 = 10 - mod;
		int restoA0 = mod;

		List<String> lista = new ArrayList<String>();

		for (int i = 0; i < codigo.length(); i++) {
			int valor = Integer.parseInt(codigo.substring(i, i + 1));
			if (i % 2 != 0) { // *1
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
