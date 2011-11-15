package practica0;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author
 */
public class NIF extends Codificacion {

	private static final String NIF_TABLA = "TRWAGMYFPDXBNJZSQVHLCKE";

	@Override
	public boolean verificar(String nif) {
		int dni = Integer.parseInt(nif.substring(0, nif.length() - 1));
		try {
			return NIF_TABLA.charAt(dni % NIF_TABLA.length()) == nif.charAt(nif
					.length() - 1);
		} catch (NumberFormatException e) {
			return false;
		}
	}

	/*************
	 * 
	 * @param codigo
	 * @return
	 */
	public String generarCodigoControl(String codigo) {
		if (codigo != null) {
			int dni = Integer.parseInt(codigo, 10);
			return codigo + NIF_TABLA.charAt(dni % NIF_TABLA.length());
		} else
			return null;
	}

	public String[] corregirDatos(String codigo) {
		int dni = Integer.parseInt(codigo.substring(0, codigo.length() - 1), 10);
		char letra = codigo.charAt(codigo.length() - 1);
		char letraVerdadera = NIF_TABLA.charAt(dni % NIF_TABLA.length());
		
		if (letra == letraVerdadera){ // es correcto
			return new String[0];
		}
		
		List<String> lista = new ArrayList<String>();
		for (int i = 0; i<codigo.length()-2; i++){
			for (int j = 0; j<9; j++){
				String dniCorregido = codigo.substring(0, i) + j + codigo.substring(i+1);
				if (verificar(dniCorregido)){
					lista.add(dniCorregido);
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
