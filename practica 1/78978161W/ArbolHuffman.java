/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package practica1;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.TreeMap;

/**
 *
 * @author alumno
 */
public class ArbolHuffman {

    private static class Nodo implements Comparable<Nodo>{

        private LinkedList<Nodo> listaNodos;
        private Character simbolo;
        private double probabilidad;

        public Nodo(LinkedList<Nodo> lista) {
            listaNodos = lista;
            simbolo = null;
            probabilidad = 0.0;
            
            for (Nodo nodo : lista) {
                probabilidad += nodo.probabilidad;
            }
        }
        
        public Nodo(char simbolo, double prob){
            listaNodos = null;
            this.simbolo = simbolo;
            probabilidad = prob;
        }
        
        public double getProbabilidad(){
            return probabilidad;
        }
        
        
        public TreeMap<Character,String> recorrido(Alfabeto alfabetoDestino){
            TreeMap<Character,String> resultado = new TreeMap<Character, String>();
            
            recorrer(resultado,"",alfabetoDestino.toList());
            
            return resultado;
        }
        
        private void recorrer(TreeMap<Character,String> recorrido, String acumulado, List<Character> alfabeto){
            if(simbolo != null){
                recorrido.put(simbolo, acumulado);
            }else{
                for(int i = 0; i < listaNodos.size(); i++){
                    listaNodos.get(i).recorrer(recorrido, acumulado + alfabeto.get(i), alfabeto);
                }
            }
        }

        @Override
        public int compareTo(Nodo t) {
            return Double.valueOf(probabilidad).compareTo(t.probabilidad);
        }
    }
    private LinkedList<Nodo> arbol;
    private CodeBook huffman;

    public ArbolHuffman(Alfabeto alfabetoOrigen, Alfabeto alfabetoDestino, List<Double> probabilidades) {
        Character[] alfabetoOrigenArray = alfabetoOrigen.toArray();
        LinkedList<Nodo> lista;
        int cont;
        
        arbol = new LinkedList<Nodo>();
        for (int i = 0; i < alfabetoOrigenArray.length; i++) {
            arbol.add(new Nodo(alfabetoOrigenArray[i], probabilidades.get(i)));
        }
        while(arbol.size() > 1){
            Collections.sort(arbol);
            lista = new LinkedList<Nodo>();
            cont = 0;
            while(cont < alfabetoDestino.size() && arbol.size() >= 1){
                lista.add(arbol.poll());
                cont++;
            }
            arbol.add(new Nodo(lista));
        }
        huffman = new CodeBook();
        for (Entry<Character,String> entrada : arbol.poll().recorrido(alfabetoDestino).entrySet()) {
            huffman.add(entrada.getKey().charValue(), entrada.getValue());
        }
    }
    
    public CodeBook getCodificacion(){
        return huffman;
    }
}
