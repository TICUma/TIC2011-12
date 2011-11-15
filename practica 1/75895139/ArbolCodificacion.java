package practica1;

public class ArbolCodificacion {

    public static String valorNodo(ArbolN arbol, int nivel, String sal, Alfabeto alfabetoCodificacion) {
        int i = 0;
        boolean libre = false;
        if (nivel == 1) { //Si el nivel que quiero ver es el 1
            if (arbol.getHijos() == null) { //Si no tiene hijos los inicializo poniendolos a -1 sus ra�ces
                arbol.inicializaHijos();
            }
            while (i < arbol.getN() && !libre) { //Si hay hueco en el nivel y no he recorrido todas las ramas
                ArbolN a = arbol.getHijos()[i];
                if ((a.getRaiz().compareTo("-1") == 0) && (a.getHijos() == null)) { //Si la raiz de la rama es -1 y no est� expandida
                    arbol.getHijos()[i].setRaiz(String.valueOf(i + 1));
                    sal += alfabetoCodificacion.getCharacter(i); //Concateno el valor de la salida con el caracter en la posici�n i del alfabeto de codificaci�n
                    libre = true;
                }
                i++;
            }
        } else { //Si el nivel que quiero no es el 1
            if (arbol.getHijos() == null) {
                arbol.inicializaHijos();
            }
            while (i < arbol.getN() && !libre) {
                ArbolN a = arbol.getHijos()[i];
                if ((a.getRaiz().compareTo("-1") == 0) && !completo(a)) { //Si la raiz es un -1 y adem�s el nivel no est� completo
                	//es decir, si no est� llenos todos los niveles
                    sal += alfabetoCodificacion.getCharacter(i); //Concateno el valor de salida con el caracter de la posici�n i del alfabeto de codificaci�n
                    libre = true;
                }
                i++;
            }
            //Llamamos de forma recursiva a la funci�n con el nivel anterior para que complete hasta que el nivel sea 1
            sal = valorNodo(arbol.getHijos()[i - 1], nivel - 1, sal, alfabetoCodificacion);
        }
        return sal;
    }

    private static boolean completo(ArbolN a) { 
        boolean res = true;
        if (a.getRaiz().compareTo("-1") != 0) {
            res = true;
        } else if (a.getRaiz().compareTo("-1") == 0) {
            if (a.getHijos() == null) {
                res = false;
            } else {
                for (int i = 0; i < a.getN(); i++) {
                    res = res && completo(a.getHijos()[i]);
                }
            }
        } else {
            res = false;
        }
        return res;
    }
}
