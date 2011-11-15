package practica0;

public class TestNIF {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		String codigoDNI = "55555555";
		
		Codificacion cod = new NIF();
		System.out.println(cod.generarCodigoControl(codigoDNI));
		System.out.println(cod.verificar(codigoDNI + "K") ? "v�lido"
				: "No v�lido");
		
		codigoDNI = "55755555K";
		System.out.println("Para el c�digo " + codigoDNI + " que es err�neo tenemos las siguientes posibles correcciones");
		for (String s : cod.corregirDatos(codigoDNI)){
			System.out.println(s);
			System.out
			.println(cod.verificar(s) ? "v�lido" : "No v�lido");
		}
		
	}

}
