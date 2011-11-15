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
public class Alfabeto {
    
    private List<Character> simbolos = new ArrayList<Character>();
    
    public Alfabeto() {
    }
    
    public Alfabeto(List<Character> simbolos) {
        this.simbolos = simbolos;
    }
    
    public void add(Character simbolo) {
        simbolos.add(simbolo);
    }
    
    public Character get(int index) {
        return simbolos.get(index);
    }
    
    public int size() {
        return simbolos.size();
    }
    
}
