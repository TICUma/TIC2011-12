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
public class AlfabetoBinario extends Alfabeto{
    private static Character[] simbolos = {'0','1'};
    
    public AlfabetoBinario(){
        super(Arrays.asList(simbolos));
    }
}
