/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package practica1;

import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author alumno
 */
public class SimbolosCodificados {
    
    private List<Character> simbolos = new ArrayList<Character>();
    private List<String> simbolosCodificados = new ArrayList<String>();
    
    public SimbolosCodificados(List<Character> simbolos, List<String> simbolosCodificados) {
        super();
        this.simbolos = simbolos;
        this.simbolosCodificados = simbolosCodificados;
    }
    
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
