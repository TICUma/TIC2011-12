package practica0;

public class TestUPC {
	
	public static void main(String args[]){
		String codigoUPC = "03940001441"; // "039400014416";

		System.out.println("Para el código " + codigoUPC + " tenemos el siguiente digito de control:");
		Codificacion cod = new UPC();
		codigoUPC = cod.generarCodigoControl(codigoUPC);
		System.out.println(codigoUPC);
		System.out
				.println(cod.verificar(codigoUPC) ? "válido" : "No válido");
		
		codigoUPC = "039404014416";
		System.out.println("Para el código " + codigoUPC + " que es erróneo tenemos las siguientes posibles correcciones");
		for (String s : cod.corregirDatos(codigoUPC)){
			System.out.println(s);
			System.out
			.println(cod.verificar(s) ? "válido" : "No válido");
		}
		
		
	}

}
