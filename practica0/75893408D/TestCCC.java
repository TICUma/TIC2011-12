package practica0;

public class TestCCC {
	
	public static void main(String args[]){
		String codigoCCC = "854195620010028945"; // "039400014416";

		System.out.println("Para el c�digo " + codigoCCC + " tenemos el siguiente digito de control:");
		Codificacion cod = new CCC();
		codigoCCC = cod.generarCodigoControl(codigoCCC);
		System.out.println(codigoCCC);
		System.out
				.println(cod.verificar(codigoCCC) ? "v�lido" : "No v�lido");
		
//		codigoCCC = "039404014416";
//		System.out.println("Para el c�digo " + codigoCCC + " que es err�neo tenemos las siguientes posibles correcciones");
//		for (String s : cod.corregirDatos(codigoCCC)){
//			System.out.println(s);
//			System.out
//			.println(cod.verificar(s) ? "v�lido" : "No v�lido");
//		}
		
		
	}

}
