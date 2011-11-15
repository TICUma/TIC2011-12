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
public class AlfabetoBinario extends Alfabeto{
    List<Character> simbolos;
    private int size;
    private int base; 
    
    public AlfabetoBinario (){
        super();
        
        this.simbolos = Arrays.asList('0', '1'); //new char[] {'0', '1'};
        size = simbolos.size();
        base = 2;
    }
    
    public AlfabetoBinario (List<Character> simbolos, int base){
        super();
        
        this.simbolos = simbolos;
        this.base = base;
        size = simbolos.size();
    }
    
    @Override
    public int getSize(){
        return size;
    }
    
    public int getBase(){
        return base;
    }
    
    @Override
    public char getSimbolo(int pos){
        if (pos < size)
            return simbolos.get(pos);
        else{
            System.out.println("Error de tamaño");
            return '¡';
        }
    }
    
    @Override
    public List<Character> getSimbolos(){
       return simbolos;
    }
    
    @Override
    public void setSimbolos(List<Character> simbolos){
        this.simbolos = simbolos;
    }
    
    
}
