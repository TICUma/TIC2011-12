package practica1;

import java.util.ArrayList;
import java.util.List;

public class Fuente {
	
	private Alfabeto alfabeto;
        private Alfabeto alfabetoCodificacion;
	private List<Double> probabilidades;
	
	public Fuente(Alfabeto alfabeto, List probabilidades) {
            this.alfabeto               = alfabeto;
            this.alfabetoCodificacion   = new AlfabetoBinario();
            this.probabilidades         = probabilidades;
	}
	
	public Fuente(Alfabeto alfabeto) {
            this.alfabeto               = alfabeto;
            this.alfabetoCodificacion   = new AlfabetoBinario();
            this.probabilidades         = new ArrayList<Double>();
            for (int i = 0; i < alfabeto.getSize(); i++){
                probabilidades.add(1.0/alfabeto.getSize());
            }
	}
		
	/**
	 * Metodo que genera la lista de símbolos
	 * del alfabeto segun sus probabilidades
	 * 
	 * @param numeroSimbolos
	 * @return
	 */
	public List getSimbolos(int numeroSimbolos){
            List<Character> simbolos = new ArrayList<Character>();
            
            // Random entre 0 y 1
            double random = Math.random();
            double suma = 0;
            
            if (probabilidades.isEmpty()){
                // Cada simbolo tiene la misma probabilidad
                // Se crea la lista con igual probabilidad
                for (int i = 0; i < alfabeto.getSize(); i++){
                    probabilidades.add(1.0/alfabeto.getSize());
                }
            }
            
            for (int i = 0; i < numeroSimbolos; i++){
                random = Math.random();
                suma = 0;
                // Todos los elementos tienen la misma probabilidad
                if (alfabeto.getSize() != probabilidades.size())
                    System.out.println("Incoherencia en longitud de alfabeto" +
                            alfabeto.getSize() +" y probabilidades." + probabilidades.size());
                else{
                    for(int j = 0; j < probabilidades.size(); j++){
                        suma += probabilidades.get(j);
                        if (random < suma){
                            simbolos.add(alfabeto.getSimbolo(j));
                            break;
                        }
                    }
                }
            }
            
            return simbolos;
	}	
	
	/**
	 * Metodo encargado de obtener un codebook binario
	 * segun Shanon 
	 * 
	 * @return
	 */
	public CodeBook getCodificacion(){
            
            
            List<Integer> shannon = FuncionesCodificacion.shannonFano
                    (this.alfabetoCodificacion.getBase(), probabilidades);
            CodeBook cb = new CodeBook();
            System.out.println("shannon "+ shannon);
            
            Arbol arb = new Arbol(2, shannon,alfabeto,alfabetoCodificacion);
            List<String> codigosAsignados = arb.asignaCodigos();
 //           NodoArbol raiz = (NodoArbol)arb.getTree().getModel().getRoot(); 
 //           System.out.println("Imprimimos nodos:");
 //           Arbol.imprimirNodos( raiz ); 
            
            for (int i = 0; i < shannon.size(); i++){
                cb.add(alfabeto.getSimbolo(i), codigosAsignados.get(i));
            }
            
            return cb;
	}
	
	/**
	 * Metodo encargado de obtener un codebook al
	 * alfabeto binario segun Huffman
	 * 
	 * @param alfabetoDestino
	 * @return
	 */
	public CodeBook getOptimalCodificacion(){
            List<Integer> huffman = FuncionesCodificacion.huffman(probabilidades);
            
            CodeBook cb = new CodeBook();
            
            Arbol arb = new Arbol(2, huffman,alfabeto,alfabetoCodificacion);
            List<String> codigosAsignados = arb.asignaCodigos();
            
            for (int i = 0; i < huffman.size(); i++){
                cb.add(alfabeto.getSimbolo(i), codigosAsignados.get(i));
            }
            
            return cb;

	}
	
	/**
	 * Metodo encargado de devolver una lista de 
	 * numSimbolos del alfabeto origen
	 * y sus correspondientes codificaciones binarias según Shannon-Fano.
	 * 
	 * @param numSimbolos
	 * @return
	 */
	public SimbolosCodificados getSimbolosCodificados(int numSimbolos){
            List<String>    simbolosCodificados = new ArrayList<String>();
            List<Character> simbolos = getSimbolos(numSimbolos);
            
            CodeBook cb = getCodificacion();
            for (Character s:simbolos)
                simbolosCodificados.add(cb.get(s));
            
            return new SimbolosCodificados(simbolos, simbolosCodificados);
	}
	
	/**
	 * Metodo encargado de devolver una lista de 
	 * numSimbolos del alfabeto origen
	 * y sus correspondientes codificaciones binarias en el
	 * alfabeto destino segun hufman
	 * 
	 * @param numSimbolos
	 * @return
	 */
	public SimbolosCodificados getSimbolosCodificadosOptimos(int numSimbolos){
            List<String>    simbolosCodificados = new ArrayList<String>();
            List<Character> simbolos = getSimbolos(numSimbolos);
            
            CodeBook cb = getOptimalCodificacion();
            for (Character s:simbolos)
                simbolosCodificados.add(cb.get(s));
            
            return new SimbolosCodificados(simbolos, simbolosCodificados);
	}
	
	
	//======================metodos auxiliares===================

        public String getStringCodificado(int pos, int tam){
            String res = "";
            
            for (int i = 0; i < tam; i++){
                
            }
                
            return res;
        }
	
	//====================get and set========================
	
	public Alfabeto getAlfabeto() {
		return alfabeto;
	}

	public void setAlfabeto(Alfabeto alfabeto) {
            this.alfabeto = alfabeto;
	}

	public void setAlfabetoCodificacion(Alfabeto alfabeto) {
            this.alfabetoCodificacion = alfabeto;
	}
	
	public List getProbabilidades() {
		return probabilidades;
	}


	public void setProbabilidades(List probabilidades) {
            this.probabilidades = probabilidades;
	}

    


}
