/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package practica1;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author alumno
 */
public abstract class Alfabeto {
    private ArrayList<Character> alfabeto;
    
    public Alfabeto(){
        alfabeto = new ArrayList<Character>();
    }
    
    public Alfabeto(Collection<Character> simbolos){
        this();
        alfabeto.addAll(simbolos);
    }
    
    public void add(char simbolo){
        alfabeto.add(simbolo);
    }
    
    public void add(Collection<Character> simbolos){
        alfabeto.addAll(simbolos);
    }
    
    public void remove(char simbolo){
        alfabeto.remove(simbolo);
    }
    
    public void remove(Collection<Character> simbolos){
        alfabeto.removeAll(simbolos);
    }
    
    public int size(){
        return alfabeto.size();
    }
    
    public Character[] toArray(){
        return alfabeto.toArray(new Character[0]);
    }
    
    public List<Character> toList(){
        return alfabeto;
    }
}
