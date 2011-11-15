package practica0;

public class TestISBN13 {
	
	public static void main(String args[]){
		String codigoISBN13 = "978-84-473-5602-";

		System.out.println("Para el c�digo " + codigoISBN13 + " tenemos el siguiente digito de control:");
		Codificacion cod = new ISBN13();
		codigoISBN13 = cod.generarCodigoControl(codigoISBN13);
		System.out.println(codigoISBN13);
		System.out
				.println(cod.verificar(codigoISBN13) ? "v�lido" : "No v�lido");
		
		codigoISBN13 = "878-84-473-5602-7";
		System.out.println("Para el c�digo " + codigoISBN13 + " que es err�neo tenemos las siguientes posibles correcciones");
		for (String s : cod.corregirDatos(codigoISBN13)){
			System.out.println(s);
			System.out
			.println(cod.verificar(s) ? "v�lido" : "No v�lido");
		}
		
	}

}
