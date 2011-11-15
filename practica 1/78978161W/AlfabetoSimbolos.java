/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package practica1;

import java.util.Arrays;

/**
 *
 * @author Redor
 */
public class AlfabetoSimbolos extends Alfabeto{
    private static Character[] simbolos = {'a','b','c','d','e','f'};
    
    public AlfabetoSimbolos(){
        super(Arrays.asList(simbolos));
    }
}