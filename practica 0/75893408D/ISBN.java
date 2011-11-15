package practica0;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 
 * @author
 */
public class ISBN extends Codificacion {

	private static final int MODULO = 11;

	/*************
	 * 
	 * @param codigo
	 * @return
	 */
	public boolean verificar(String codigo) {
		codigo = codigo.replaceAll("-", "");
		int resultado = 0;
		try {
			for (int i = 0; i < codigo.length(); i++) {
				resultado += Integer.parseInt(codigo.substring(i, i + 1))
						* (i + 1);
			}
		} catch (NumberFormatException e) {
			return false;
		}
		return resultado % MODULO == 0;
	}

	/***************
	 * 
	 * @param codigo
	 * @return
	 */
	@Override
	public String generarCodigoControl(String codigo) {

		codigo = codigo.replaceAll("-", "");

		int resultado = 0;

		try {
			for (int i = 0; i < codigo.length(); i++) {
				resultado += (Integer.parseInt(codigo.substring(i, i + 1)) * (i + 1));
			}
		} catch (NumberFormatException e) {
			return null;
		}
		return codigo + (resultado % MODULO);
	}

	@Override
	public String[] corregirDatos(String codigo) {

		List<String> resultados = new ArrayList<String>();
		
		int resultado = 0;

		try {
			for (int i = 0; i < codigo.length(); i++) {
				resultado += (Integer.parseInt(codigo.substring(i, i + 1)) * (i + 1));
			}
		} catch (NumberFormatException e) {
			return null;
		}

		int mod = resultado % MODULO;
		if (mod == 0) {
			return new String[0];
		}

		int restoA11 = 11 - mod;
		int restoA0 = mod;
		
		// Posibilidades restoA11
		// Para 1
		
		List<Integer> factores = multiplos(restoA11);
		for (Integer f : factores){
			int valor = Integer.parseInt(String.valueOf(codigo.charAt(f-1)));
			if (valor+(restoA11/f)<=9 && f != 10)
				resultados.add(codigo.substring(0, f-1) + String.valueOf(valor+(restoA11/f)) + codigo.substring(f));
		}
		
		factores = multiplos(restoA0);
		for (Integer f : factores){
			int valor = Integer.parseInt(String.valueOf(codigo.charAt(f-1)));
			if (valor-(restoA0/f)<=0)
				resultados.add(codigo.substring(0, f-1) + String.valueOf(valor-(restoA0/f)) + codigo.substring(f));
		}
		
		String[] res = new String[resultados.size()];
		for (int i=0; i<resultados.size(); i++){
			res[i] = resultados.get(i);
		}
		return res;
	}

	private List<Integer> multiplos(int valor) {
		// Se empieza probando como posible multiplo el 2.
		int factor = 1;
		List<Integer> factores = new LinkedList<Integer>();

		// Ultimo factor que debemos probar.
		int factorLimite = valor;

		while (factor <= factorLimite) {
			if (valor % factor == 0) {
				factores.add(new Integer(factor));
			}

			factor++;
		}

		return factores;
	}

}
