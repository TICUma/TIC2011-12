package Practica1;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * Clase encargada de encapsular una 
 * lista de simbolos y sus codificaciones
 *
 */
public class SimbolosCodificados {
	
	private List<?> simbolos = new ArrayList<Object>();
	private List<?> simbolosCodificados = new ArrayList<Object>();

	
	public SimbolosCodificados(List<?> simbolos, List<?> simbolosCodificados) {
		super();
		this.simbolos = simbolos;
		this.simbolosCodificados = simbolosCodificados;
	}

	//=======================get and set==========================

	public List<?> getSimbolos() {
		return simbolos;
	}

	public void setSimbolos(List<?> simbolos) {
		this.simbolos = simbolos;
	}

	public List<?> getSimbolosCodificados() {
		return simbolosCodificados;
	}

	public void setSimbolosCodificados(List<?> simbolosCodificados) {
		this.simbolosCodificados = simbolosCodificados;
	}

	
}
