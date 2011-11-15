/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package practica1;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alumno
 */
public class AlfabetoBinario extends Alfabeto {
    
    public AlfabetoBinario ()
    {
        List<String> alf= new ArrayList<String>();
        alf.add("0");
        alf.add("1");               
        super.setSimbolos(alf);
        super.setN_simbolos(2);
    }
    
}
