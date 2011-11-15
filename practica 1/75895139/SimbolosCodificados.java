package practica1;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * Clase encargada de encapsular una 
 * lista de simbolos y sus codificaciones
 *
 */
public class SimbolosCodificados {
	
	private List<Character> simbolos = new ArrayList<Character>();
	private List<String> simbolosCodificados = new ArrayList<String>();

	
	public SimbolosCodificados(List<Character> simbolos, List<String> simbolosCodificados) {
		super();
		this.simbolos = simbolos;
		this.simbolosCodificados = simbolosCodificados;
	}

	//=======================get and set==========================

	public List<Character> getSimbolos() {
		return simbolos;
	}

	public void setSimbolos(List<Character> simbolos) {
		this.simbolos = simbolos;
	}

	public List<String> getSimbolosCodificados() {
		return simbolosCodificados;
	}

	public void setSimbolosCodificados(List<String> simbolosCodificados) {
		this.simbolosCodificados = simbolosCodificados;
	}

	
}
