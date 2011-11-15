/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package practica1;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Collections;
import java.util.Random;
	
/**
 *
 * @author alumno
 */
public class Fuente {
    
    private Random generator = new Random();
    private Alfabeto alfabeto;
    private Alfabeto alfabetoCodificacion;
    private Map<Character, Double> probabilidades = new HashMap<Character, Double>();
    
    public Fuente() {
    }
    
    public Fuente(Alfabeto alfabeto, Map<Character, Double> probabilidades) {
        this.alfabeto = alfabeto;
        this.probabilidades = probabilidades;
        setAlfabetoCodificacionBinario();
    }
    
    public Fuente(Alfabeto alfabeto) {
        this.alfabeto = alfabeto;
        // todos simbolos equiprobables
        for (int i = 0; i < alfabeto.size(); i++) {
            probabilidades.put(alfabeto.get(i), 1.0 / alfabeto.size());
        }
        setAlfabetoCodificacionBinario();
    }
    
    public List<Character> getSimbolos(int numeroSimbolos){
        List<Character> generados = new ArrayList<Character>();
    	for (int i = 0; i < numeroSimbolos; i++) {
            // distribución uniforme <0;1>
            double aleatorio = generator.nextDouble();
            double suma = 0.0;
            for (Character simbolo : probabilidades.keySet()) {
                suma += probabilidades.get(simbolo);
                if (suma >= aleatorio) {
                    generados.add(simbolo);
                    break;
                }
            }
        }
        return generados;
    }
    
    public Codebook getCodificacion() {

        // los parámetros de la codificación (n1, n2...)
        HashMap<Integer, Integer> niveles = new HashMap<Integer, Integer>();
        
        // la regla de Shannon-Fano
        for (Character simbolo : probabilidades.keySet()) {
        	// b ^ (y_i) >= 1.0 / p_i
            for (int y = 1; ; y++) {
            	if (Math.pow(alfabetoCodificacion.size(), y) >= 1.0 / probabilidades.get(simbolo)) {
            		niveles.put(y, niveles.containsKey(y) ? niveles.get(y) + 1 : 1);
            		break;
            	}
            }
        }
        
        // los simbolos codificados
        List<String> hojas = new ArrayList<String>();
        
        // los simbolos codificados
        makeArbol(1, niveles, "", hojas);
        
        // ordenar los simbolos según sus probabilidades
        List<Double> probabilidades = new ArrayList<Double>(this.probabilidades.values());
        Collections.sort(probabilidades, Collections.reverseOrder());
        List<Character> ordenados = new ArrayList<Character>();
        for (Double probabilidad : probabilidades) {
        	for (Character clave : this.probabilidades.keySet()) {
        		if (this.probabilidades.get(clave) == probabilidad) {
        			ordenados.add(clave);
        		}
        	}
        }
        
        // la codificación
        Codebook codebook = new Codebook();
        for (int i = 0; i < ordenados.size(); i++) {
        	codebook.add(ordenados.get(i), hojas.get(i));
        }
    	return codebook;
    	
    }
    
    /*
     * makeArbol es una función recursiva que genera
     * los códigos codificados en el cierto nivel
     */
    private void makeArbol(int nivel, HashMap<Integer, Integer> niveles, String rama, List<String> hojas) {
    	// nada que hacer
    	if (niveles.isEmpty()) return;
    	// el número de hojas en el nivel actual
    	int numeroHojas = niveles.containsKey(nivel) ? niveles.get(nivel) : 0;
    	// crear hojas & árboles
    	for (int i = 0; i < alfabetoCodificacion.size(); i++) {
    		Character simbolo = alfabetoCodificacion.get(i);
    		if (i < numeroHojas) {
    			// crear hoja
    			hojas.add(rama + simbolo);
    			niveles.put(nivel, niveles.get(nivel) - 1);
    	    	if (niveles.get(nivel) == 0) niveles.remove(nivel);
    		} else {
    			// crear árbol
    			makeArbol(nivel + 1, niveles, rama + simbolo, hojas);
    		}
    	}
    }
    
    public Codebook getOptimalCodificacion(){
    	
    	// copiar los carácteres al árbol
        List<Node> nodos = new ArrayList<Node>();
        for (Character character : probabilidades.keySet()) {
        	nodos.add(new Node(character, probabilidades.get(character)));
        }
        
        // construir el árbol
        Node raiz = makeArbolOptimal(nodos);
        
        // recorrer el árbol y crear la codificación
        Codebook codebook = new Codebook();
        assignArbolOptimal(raiz, "", codebook);
    	return codebook;
    	
    }
    
    /*
     * Node es la clase que representa el nodo en un árbol
     * de la construcción de la codificación de Huffman
     */
    private class Node implements Comparable<Node> {
    	private Character character;
    	private Double probabilidad;
    	private List<Node> descendientes = new ArrayList<Node>();
    	public Node() {
    		character = null;
    		probabilidad = 0.0;
    	}
    	public Node(Character character, Double probabilidad) {
    		this.character = character;
    		this.probabilidad = probabilidad;
    	}
    	public void add(Node node) {
    		descendientes.add(node);
    		probabilidad += node.probabilidad;
    	}
    	public Character getCharacter() {
    		return character;
    	}
    	@Override
    	public int compareTo(Node node) {
    		if (probabilidad > node.probabilidad) {
    			return 1;
    		} else if (probabilidad == node.probabilidad) {
    			return 0;
    		} else {
    			return -1;
    		}
    	}
    }
    
    /*
     * makeArbolOptimal es una función recursiva que construye
     * el árbol de la codficación de Huffman
     */
    private Node makeArbolOptimal(List<Node> nodos) {
    	
    	// la raíz del árbol
    	if (nodos.size() == 1) return nodos.get(0);
    	
    	Collections.sort(nodos);
    	
    	// sustituir las probabilidades menores por una probabilidad nueva
    	List<Node> nodosNuevos = new ArrayList<Node>(nodos);
    	Node nodoNuevo = new Node();
    	for (int i = 0; i < alfabetoCodificacion.size(); i++) {
    		if (!nodosNuevos.isEmpty()) {
    			nodoNuevo.add(nodosNuevos.remove(0));
    		}
    	}
    	nodosNuevos.add(nodoNuevo);
    	
    	return makeArbolOptimal(nodosNuevos);
    	
    }
    
    /*
     * assignArbolOptimal es una función recursiva que crea los códigos
     * según un árbol de la construcción de la codificación de Huffman
     */
    private void assignArbolOptimal(Node nodo, String rama, Codebook codebook) {
    	if (nodo.getCharacter() != null) {
    		// la hoja
    		codebook.add(nodo.getCharacter(), rama);
    	} else {
    		// las ramas
    		int i = 0;
    		for (Node descendiente : nodo.descendientes) {
    			assignArbolOptimal(descendiente, rama + alfabetoCodificacion.get(i), codebook);
    			i++;
    		}
    	}
    }
    
    public SimbolosCodificados getSimbolosCodificados(int numSimbolos){
    	List<Character> simbolos = new ArrayList<Character>(getSimbolos(numSimbolos));
    	Codebook codebook = this.getCodificacion();
    	List<String> simbolosCodificados = new ArrayList<String>();
    	for (Character simbolo : simbolos) {
    		simbolosCodificados.add(codebook.get(simbolo));
    	}
    	return new SimbolosCodificados(simbolos, simbolosCodificados);
    }
    
    public SimbolosCodificados getSimbolosCodificadosOptimos(int numSimbolos){
    	List<Character> simbolos = new ArrayList<Character>(getSimbolos(numSimbolos));
    	Codebook codebook = this.getOptimalCodificacion();
    	List<String> simbolosCodificados = new ArrayList<String>();
    	for (Character simbolo : simbolos) {
    		simbolosCodificados.add(codebook.get(simbolo));
    	}
    	return new SimbolosCodificados(simbolos, simbolosCodificados);
    }
    
    public Alfabeto getAlfabeto() {
        return alfabeto;
    }
    
    public void setAlfabeto(Alfabeto alfabeto) {
    	this.alfabeto = alfabeto;
    }
    
    public void setAlfabetoCodificacion(Alfabeto alfabeto) {
        alfabetoCodificacion = alfabeto;
    }
    
    private void setAlfabetoCodificacionBinario() {
        alfabetoCodificacion = new Alfabeto();
        alfabetoCodificacion.add('0');
        alfabetoCodificacion.add('1');
    }
    
    public Map<Character, Double> getProbabilidades() {
        return probabilidades;
    }
    
    public void setProbabilidades(Map<Character, Double> probabilidades) {
        this.probabilidades = probabilidades;
    }
    
    public double entropia() {
        double suma = 0.0;
        for (Double probabilidad : probabilidades.values()) {
            suma += probabilidad * Math.log(1.0 / probabilidad) / Math.log(alfabetoCodificacion.size());
        }
        return suma;
    }
    
    public double longitudMedia(Codebook codebook) {
    	double suma = 0.0;
    	for (Character character : codebook.simbolos()) {
    		suma += codebook.get(character).length() * probabilidades.get(character);
    	}
    	return suma;
    }
    
}
