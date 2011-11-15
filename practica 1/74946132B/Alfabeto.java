/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package practica1;

import java.util.List;

/**
 *
 * @author alumno
 */
public abstract  class Alfabeto {
    
    private List simbolos;
    private int n_simbolos;
    
   public List getSimbolos() {
        return this.simbolos;
    }


    /**
     * @param simbolos the simbolos to set
     */
    public void setSimbolos(List simbolos) {
        this.simbolos = simbolos;
    }

    /**
     * @return the n_simbolos
     */
    public int getN_simbolos() {
        return n_simbolos;
    }

    /**
     * @param n_simbolos the n_simbolos to set
     */
    public void setN_simbolos(int n_simbolos) {
        this.n_simbolos = n_simbolos;
    }

}
