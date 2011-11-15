/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 * jose luis aguilar
 * http://www.chuidiang.com/java/JTree/PruebaJTree.java.html
 * 
 */
package practica1;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

/**
 *
 * @author akumayken
 */
public final class Arbol extends JTree{
    int base;
    int nivel;
    List<Integer> elementosPorNivel, longitudes;
    DefaultTreeModel modelo;
    JTree tree;
    List<Character> simbolosOrigen;
    List<Character> simbolosDestino;
    List<String> result;
    
    private Arbol() {
        super();
    }
    
    public Arbol(int base, List<Integer> longitudes, Alfabeto origen, Alfabeto destino){
        super();
        
        int pos, elem;
        this.base       = base;
        this.longitudes = longitudes;
        simbolosOrigen  = origen.getSimbolos();
        simbolosDestino = destino.getSimbolos();
        
        result = new ArrayList<String>();
        
        elementosPorNivel = new ArrayList<Integer>();
        for (int i = 0; i <= longitudes.size(); i++)
            elementosPorNivel.add(0);
        for (int i = 0; i < longitudes.size(); i++){
            // pos = longitud de la letra en la posicion i
            pos = longitudes.get(i);
            elem = elementosPorNivel.get(pos);
            elementosPorNivel.set(pos, elem+1);
        }

        NodoArbol nodoRaiz = new NodoArbol("");
        modelo = new DefaultTreeModel(nodoRaiz);
        
        tree = new JTree(modelo);
        
        creaHijos(nodoRaiz);
    }
    
    public void creaHijos(NodoArbol nodo) { 
//        System.out.println(/*nodo.texto + */ nodo.toString()); 
        if (!nodo.ocupado && elementosPorNivel.size() > nodo.getLevel()){
//            System.out.println("NIVEL:"+ nodo.getLevel());
//            System.out.println("ELEMENTOS:"+ elementosPorNivel);    
            int elementos = elementosPorNivel.get(nodo.getLevel());
            if (elementos > 0 ){
                //Se tiene que meter uno
                elementosPorNivel.set(nodo.getLevel(),elementos-1);
//                System.out.println("El nivel "+ nodo.getLevel() + " tiene "+ elementos);
 //               System.out.println("A ocupar para este nivel -> " + elementosPorNivel.get(nodo.getLevel()));
 //               System.out.println("Ocupamos uno del nivel "+ nodo.getLevel());
                nodo.setOcupado(true);
                result.add(nodo.toString());
                nodo.setTexto(Integer.toString(nodo.getLevel()));
            }    
            else if (nodo.getChildCount() <= 0){
                // Si no hay uno que meter se abre los n hijos posibles
                for (int i = 0; i < base; i++){
               //     modelo.insertNodeInto(new NodoArbol("-" + Integer.toString(nodo.getLevel()+1)),nodo,i);
                    String saux = nodo.toString() + Character.toString(simbolosDestino.get(i));
                    ;
                    modelo.insertNodeInto(new NodoArbol(saux),nodo,i);
                }
            }
            for (Enumeration e=nodo.children(); e.hasMoreElements(); ) { 

                NodoArbol n = (NodoArbol)e.nextElement(); 
                creaHijos(n); 
            } 

            
            /* Si el nodo no está ocupado y estamos en el nivel adecuado. Se apunta */
            
        }
    }
    
    public JTree getTree(){
        return tree;
    }
    
    public static void imprimirNodos(NodoArbol nodo) { 
       if (nodo.getOcupado())
            System.out.println(nodo.toString()); 
    
        if (nodo.getChildCount() >= 0) { 
            for (Enumeration e=nodo.children(); e.hasMoreElements(); ) { 
                NodoArbol n = (NodoArbol)e.nextElement(); 
                imprimirNodos(n); 
            } 
        } 
    }
    
    public List<String> asignaCodigos () {
        List<String> res = new ArrayList<String> ();
        int vueltas = result.size();
        int i = 0;
        for(int j = 0; j < longitudes.size(); j++){
            i = 0;
//            System.out.println("LONGITUDES: " +  longitudes+ " RESULT:" + result);
            while (longitudes.get(j) != result.get(i).length()){
                i++;
//                System.out.println("i="+Integer.toString(i-1)+"  " +longitudes.get(j) + " " + result.get(i).length());
            }
            res.add(result.get(i));
            result.remove(i);
 //           System.out.println(res);
        }
 //       System.out.println(res);
        return res;
    }
}
