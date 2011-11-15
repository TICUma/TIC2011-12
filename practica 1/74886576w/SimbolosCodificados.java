/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package practica1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Nicolás
 */
public class SimbolosCodificados {
    private List simbolos = new ArrayList();
    private List simbolosCodificados = new ArrayList();
    
    public SimbolosCodificados(List simbolos, List simbolosCodificados) {
        super();
        this.simbolos = simbolos;
        this.simbolosCodificados = simbolosCodificados;
    }
    
    
    
    
    public List getSimbolos() {
        return simbolos;
    }

    public void setSimbolos(List simbolos) {
        this.simbolos = simbolos;
    }

    public List getSimbolosCodificados() {
        return simbolosCodificados;
    }

    public void setSimbolosCodificados(List simbolosCodificados) {
        this.simbolosCodificados = simbolosCodificados;
    }
    
    public String toString(){
        return ""+simbolos+"\n"+simbolosCodificados;
    }
}
