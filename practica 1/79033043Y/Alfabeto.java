/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package practica1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author akumayken
 */
public abstract class Alfabeto {
    List<Character> simbolos;
    private int size;
    private int base; 
    
    public Alfabeto (){
        super();
        
    }
    
    
    
    public int getSize(){
        return size;
    }
    
    public int getBase(){
        return base;
    }
    
    public char getSimbolo(int pos){
        if (pos < size)
            return simbolos.get(pos);
        else{
            System.out.println("Error de tamaño");
            return '¡';
        }
    }
    
    public List<Character> getSimbolos(){
       return simbolos;
    }
    
    public void setSimbolos(List<Character> simbolos){
        this.simbolos = simbolos;
    }
    
    
}
