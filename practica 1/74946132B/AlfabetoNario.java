
package practica1;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alumno
 */
class AlfabetoNario extends Alfabeto {
    
   
    public AlfabetoNario (List lista)
    {
        if(!lista.isEmpty())
        {
            super.setSimbolos(lista);
            super.setN_simbolos(lista.size());
        }
       
    }
    
   

}
