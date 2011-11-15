package practica0;

public class TestISBN {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String codigoISBN = "044485193"; // "84-206-8186-5";
		
		System.out.println("Para el c�digo " + codigoISBN + " tenemos el siguiente digito de control:");
		Codificacion cod = new ISBN();
		codigoISBN = cod.generarCodigoControl(codigoISBN);
		System.out.println(codigoISBN);
		System.out
				.println(cod.verificar(codigoISBN) ? "v�lido" : "No v�lido");
		
		codigoISBN = "1444851933";
		System.out.println("Para el c�digo " + codigoISBN + " que es err�neo tenemos las siguientes posibles correcciones");
		for (String s : cod.corregirDatos(codigoISBN)){
			System.out.println(s);
			System.out
			.println(cod.verificar(s) ? "v�lido" : "No v�lido");
		}		
		
		System.out.println(cod.generarCodigoControl("144485193"));
		System.out
		.println(cod.verificar(cod.generarCodigoControl("144485193")) ? "v�lido" : "No v�lido");
		
		
	}

}
