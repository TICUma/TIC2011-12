/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package practica1;

import java.util.TreeMap;
import java.util.TreeSet;

/**
 *
 * @author alumno
 */
class ArbolShannon {
    private TreeMap<Integer, TreeSet<String>> disponibles;
    private TreeSet<String> alfabetoString;

    public ArbolShannon(Alfabeto alfabeto) {
        alfabetoString = new TreeSet<String>();
        disponibles = new TreeMap<Integer, TreeSet<String>>();
        for (Character caracter : alfabeto.toList()) {
            alfabetoString.add(caracter.toString());
        }
        disponibles.put(Integer.valueOf(1), new TreeSet<String>(alfabetoString));
    }

    public String getLeafOfSize(int size) {
        String resultado;
        TreeSet<String> cadenas;
        if (!disponibles.containsKey(Integer.valueOf(size))) {
            expandToLevel(size);
        }
        cadenas = disponibles.get(Integer.valueOf(size));
        resultado = cadenas.pollFirst();
        if (cadenas.isEmpty()) {
            disponibles.remove(Integer.valueOf(size));
        }
        return resultado;
    }

    private void expandToLevel(int num) {
        int i = num - 1;
        String temp;
        TreeSet<String> cadenas;
        TreeSet<String> nuevaLista;
        while (!disponibles.containsKey(Integer.valueOf(i))) {
            i--;
        }
        for (int j = i; j < num; j++) {
            cadenas = disponibles.get(Integer.valueOf(j));
            temp = cadenas.pollFirst();
            if (cadenas.isEmpty()) {
                disponibles.remove(Integer.valueOf(j));
            }
            nuevaLista = new TreeSet<String>();
            for (String elem : alfabetoString) {
                nuevaLista.add(temp + elem);
            }
            disponibles.put(j + 1, nuevaLista);
        }
    }
    
}
