package practica0;

public class TestISBN13 {
	
	public static void main(String args[]){
		String codigoISBN13 = "978-84-473-5602-";

		System.out.println("Para el código " + codigoISBN13 + " tenemos el siguiente digito de control:");
		Codificacion cod = new ISBN13();
		codigoISBN13 = cod.generarCodigoControl(codigoISBN13);
		System.out.println(codigoISBN13);
		System.out
				.println(cod.verificar(codigoISBN13) ? "válido" : "No válido");
		
		codigoISBN13 = "878-84-473-5602-7";
		System.out.println("Para el código " + codigoISBN13 + " que es erróneo tenemos las siguientes posibles correcciones");
		for (String s : cod.corregirDatos(codigoISBN13)){
			System.out.println(s);
			System.out
			.println(cod.verificar(s) ? "válido" : "No válido");
		}
		
	}

}
