package Practica1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Fuente {
	
	private Alfabeto alfabeto;
	
	public Fuente(Alfabeto alfabeto, List<?> probabilidades) {
		this.alfabeto = alfabeto;
		this.alfabeto.asignaProbabilidad(probabilidades);		
	}
	
	public Fuente(Alfabeto alfabeto) {
		this.alfabeto = alfabeto;
	}
		
	/**
	 * Metodo que genera la lista de sinbolos
	 * del alfabeto segun sus probabilidades
	 * 
	 * @param numeroSimbolos
	 * @return
	 */
	public List<Character> getSimbolos(int numeroSimbolos){
		List<Character> lista = new LinkedList<Character>();
		
		for (int i=0;i<numeroSimbolos;i++){
			lista.add(alfabeto.getSimbolo());
		}
		
		return lista;
	}	
	
	/**
	 * Metodo encargado de obtener un codebook binario
	 * segun Shanon 
	 * 
	 * @return
	 */
	public CodeBook getCodificacion(){
		LinkedList<NodoLista> simbolosLista;
		LinkedList<String> simbolosCodificadosAsignados = new LinkedList<String>();
		boolean[] comprobados = new boolean[alfabeto.getProbabilidades().size()];
		String nuevoSimbolo="";
		CodeBook codebook = new CodeBook();
		Iterator<Float> itpr;
		float tmp=0;		
		float maxProbabilidad = 0f;
		int j=0,maxIndex = 0,nivel;
		NodoLista nodo;
		
		//Inicializamos el array a falso
		for (int i=0;i<comprobados.length;i++){
			comprobados[i]=false;
		}	
		
		simbolosLista = getEnes(); //Aquí ya tenemos los números de símbolos por nivel.
		
		for (int i=0;i<alfabeto.getProbabilidades().size();i++){ //Tenemos que recorrer el array todas estas veces.
			for (itpr=alfabeto.getProbabilidades().iterator();itpr.hasNext();){ //Buscamos la mínima probabilidad no comprobada
				if (!comprobados[j]){
					tmp = itpr.next();
					if (tmp>maxProbabilidad){						
						maxProbabilidad = tmp;
						//System.out.println("Máxima probabilidad: "+maxProbabilidad);						
						maxIndex = j;
					}
				}else{
					tmp = itpr.next();
				}
				j++;
			}
			//Aquí ya tenemos el índice de máxima probabilidad.
			//System.out.println("Índice de máxima probabilidad: "+maxIndex);
			comprobados[maxIndex] = true; //Ya hemos comprobado este, lo marcamos.
			//cogemos el primer elemento de la lista.
			nodo = simbolosLista.getFirst();
			nivel = nodo.getNivel(); //Este será el número de caracteres que tendrá nuestro símbolo codificado.
			if (nodo.getNumero()==1){
				simbolosLista.removeFirst(); //Si solo hay un simbolo en este nivel eliminamos el nodo.
				//System.out.println("Eliminamos un nodo al estar vacio.");
			}else{
				simbolosLista.getFirst().setNumero(nodo.getNumero()-1); //Eliminamos uno.
				//System.out.println("Decrementamos nodo.");

			}
			nuevoSimbolo = getSimboloCodificado(nivel,simbolosCodificadosAsignados);
			//System.out.println("Símbolo obtenido :"+nuevoSimbolo);
			simbolosCodificadosAsignados.add(nuevoSimbolo);
			codebook.add(alfabeto.getSimbolo(maxIndex), nuevoSimbolo);
			j = 0;
			maxIndex = 0;
			maxProbabilidad = 0f;
		}		
		
		System.out.println("Longitud media codigo Shannon-Fano: "+longitudMedia(codebook));
		
		return codebook;
	}
	
	
	/**
	 * Metodo encargado de obtener un codebook al
	 * alfabeto binario segun Huffman
	 * 
	 * @param alfabetoDestino
	 * @return
	 */
	public CodeBook getOptimalCodificacion(){
		LinkedList<NodoListaHuffman> nodosHoja = new LinkedList<NodoListaHuffman>();
		CodeBook codeBook = new CodeBook();
		NodoListaHuffman nodo;
		int i = 0;
		
		//Contruimos los nodos hoja del árbol
		for (Iterator<Float> it = alfabeto.getProbabilidades().iterator();it.hasNext();){
			nodo = new NodoListaHuffmanHoja(it.next(), i);
			nodosHoja.add(nodo);			
			i++;
		}
		
		//Creamos el árbol
		nodo = construyeArbol(nodosHoja);
		construyeCodigo(nodo);
		
		i=0;
		for (Iterator<NodoListaHuffman> it = nodosHoja.iterator();it.hasNext(); ){
			codeBook.add(alfabeto.getSimbolo(i), (it.next()).getCodigo());
			i++;
		}	
		
		System.out.println("Longitud media codigo Huffman: "+longitudMedia(codeBook));
		
		return codeBook;
	}
	
	
	public float longitudMedia(CodeBook c){
		ArrayList<String> codigos = (ArrayList<String>) c.codes();
		ArrayList<Float> probabilidades = (ArrayList<Float>) alfabeto.getProbabilidades();
		float resultado=0f;
		
		int i = 0;
		for (Iterator<String> it = codigos.iterator();it.hasNext();){
			resultado = resultado + probabilidades.get(i)*((float)(it.next()).length());
			i++;
		}
		
		return resultado;
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
		List<Character> simbolosPlanos;
		List<String> simbolosCodificados = new LinkedList<String>();
		CodeBook codebook = this.getCodificacion();
		
		simbolosPlanos = this.getSimbolos(numSimbolos);
		
		for (Iterator<Character> it=simbolosPlanos.iterator();it.hasNext();){
			simbolosCodificados.add(codebook.get(it.next()));
		}
		
		return new SimbolosCodificados(simbolosPlanos,simbolosCodificados);
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
		List<Character> simbolosPlanos;
		List<String> simbolosCodificados = new LinkedList<String>();
		CodeBook codebook = this.getOptimalCodificacion();
		
		simbolosPlanos = this.getSimbolos(numSimbolos);
		
		for (Iterator<Character> it=simbolosPlanos.iterator();it.hasNext();){
			simbolosCodificados.add(codebook.get(it.next()));
		}
		
		return new SimbolosCodificados(simbolosPlanos,simbolosCodificados);
	}
	
	
	//======================metodos auxiliares===================

	
	//====================get and set========================
	
	public Alfabeto getAlfabeto() {
		return alfabeto;
	}

	public void setAlfabeto(Alfabeto alfabeto) {
		this.alfabeto = alfabeto;
	}

	public void setAlfabetoCodificacion(Alfabeto alfabeto) {
		
	}
	
	
	//MÉTODOS AUXILIARES DE HUFFMAN
	
	public void construyeCodigo(NodoListaHuffman nodo){
		if (!(nodo.getIzquierda() == null)){ //Es un nodo hoja.		
			if (nodo.getDerecha()==null){ //Si solo tiene un hijo propagamos hacia abajo
				nodo.getIzquierda().setCodigo(nodo.getCodigo());
				construyeCodigo(nodo.getIzquierda());
			}else{ //Si tiene dos, añadimos 0 a la izquierda y 1 a la derecha.
				nodo.getIzquierda().setCodigo(nodo.getCodigo()+"0");
				nodo.getDerecha().setCodigo(nodo.getCodigo()+"1");
				construyeCodigo(nodo.getIzquierda());
				construyeCodigo(nodo.getDerecha());
			}			
		}
	}
	
	public void imprimeArbol(NodoListaHuffman nodo){
		if (nodo!=null){
			imprimeArbol(nodo.getIzquierda());
			System.out.println(nodo.toString());
			imprimeArbol(nodo.getDerecha());
		}
		
	}
	
	public NodoListaHuffman construyeArbol(LinkedList<NodoListaHuffman> nodosHoja){
		NodoListaHuffman izquierdo = null,derecho = null;
		LinkedList<NodoListaHuffman> lineaAnterior =  nodosHoja;
		LinkedList<NodoListaHuffman> nuevaLineaArbol = new LinkedList<NodoListaHuffman>();
		Iterator<NodoListaHuffman> it;
		NodoListaHuffman tmpNodo;
		
		float menor1=1.1f, menor2=1.1f;
		int i1 = 0,i2 = 0,j;
		
		boolean[] comprobados;
		
		comprobados = new boolean[lineaAnterior.size()];
		for (int i=0;i<comprobados.length;i++){
			comprobados[i] = false;
		}
		
		//Recorremos la linea anterio del arbol, en el primer caso serán los nodos hoja.
		//tenemos que buscar los menores a pares e ir marcando los comprobados.
		while (lineaAnterior.size()>2){
				menor1=1.1f;
				menor2=1.1f;
				i1 = 0;i2 = 0;
				j=0;
				for (it = lineaAnterior.iterator();it.hasNext();){
					tmpNodo=it.next();
					if (!comprobados[j] && tmpNodo.getProbabilidad()<menor1){
						menor1 = tmpNodo.getProbabilidad();
						i1=j; 
					}
					j++;
				}
				comprobados[i1] = true;
				izquierdo = lineaAnterior.get(i1);
				//System.out.println("Probabilidad menor 1:"+izquierdo.getProbabilidad());
				
				j=0;
				for (it = lineaAnterior.iterator();it.hasNext();){
					tmpNodo=it.next();
					if (!comprobados[j] && tmpNodo.getProbabilidad()<menor2){
						menor2 = tmpNodo.getProbabilidad();
						i2=j; 
					}
					j++;
				}
				comprobados[i2] = true;
				derecho = lineaAnterior.get(i2);
				//System.out.println("Probabilidad menor 2:"+derecho.getProbabilidad());

				//Aquí ya tenemos el hijo derecho e izquierdo del nuevo nodo.
				nuevaLineaArbol.add(new NodoListaHuffmanNormal(izquierdo.getProbabilidad()+derecho.getProbabilidad(),izquierdo,derecho));
				//System.out.println("Nodo unificado: "+nuevaLineaArbol.getLast().getProbabilidad());
				//El resto de nodos no usados, los copiamos tal cual a la nueva linea del arbol, poniendo como padres a los anteriores.
				j = 0;
				
				
				for (it = lineaAnterior.iterator();it.hasNext();){
					tmpNodo=it.next();
					if (!comprobados[j]){
						nuevaLineaArbol.add(new NodoListaHuffmanNormal(tmpNodo.getProbabilidad(),tmpNodo,null));
						//System.out.println("Añadido nodo con probabilidad: "+tmpNodo.getProbabilidad());
					}
					j++;
				}
				lineaAnterior = nuevaLineaArbol;
				nuevaLineaArbol = new LinkedList<NodoListaHuffman>();
				
				comprobados = new boolean[lineaAnterior.size()];
				for (int i=0;i<comprobados.length;i++){
					comprobados[i] = false;
				}
		}		
		
		izquierdo = lineaAnterior.get(0);
		derecho = lineaAnterior.get(1);
		return new NodoListaHuffmanNormal(izquierdo.getProbabilidad()+derecho.getProbabilidad(),izquierdo,derecho);
	}
	
	
	//MÉTODOS AUXILIARES SHANNON-FANO
	
	//Esto me hace el cálculo que pide Shannon-Fano
	//que es:  2^numero >= 1/probabilidad.
	public int menorNumeroQue(float probabilidad, int base){
		float x = 1/probabilidad;
		int numero = 0;
		while (Math.pow(base, numero)<x){
			numero++;
		}
		//System.out.println("Probabilidad: "+probabilidad+" - Número obtenido: "+numero);
		return numero;		
	}
	
	//Este método me devuelve una cadena binaria valida para el código	
	private String getSimboloCodificado(int longitud,LinkedList<String> lista){
		String resultado = "";
		if (lista.isEmpty()){
			for (int i=0;i<longitud;i++){
				resultado+="0"; //Cadena de 0's
			}
		}else{
			int i=0;
			resultado = Integer.toBinaryString(i);
			i++;
			while (i<(Math.pow(2, longitud)) && algunaEsPrefijo(resultado,lista)){
				resultado = completaConCeros(Integer.toBinaryString(i),longitud);
				i++;				
			}
			
		}		
		
		return resultado;
	}
	
	//Este método comprueba si la cadena propuesta para el código tiene como prefijo otra
	//cadena ya asignada.
	public boolean algunaEsPrefijo(String cadena,LinkedList<String> lista){
		boolean resultado = false;
		boolean and = true;
		int indiceuno=0;
		int indicedos=0;
		String tmpcad;
		Iterator<String> it = lista.iterator();
				
		while (it.hasNext() && !resultado){
			and = true;
			tmpcad = it.next();
			//System.out.println("Cadenas a analizar: "+cadena+"->"+tmpcad);
			indiceuno=0;
			indicedos=0;
			while (indiceuno < tmpcad.length() && indicedos < cadena.length()){
				and = and && (tmpcad.toCharArray()[indiceuno] == cadena.toCharArray()[indicedos]);
				indiceuno++;
				indicedos++;
			}		
			if (and){				
				resultado = true;				
			}
		}	
		if (!it.hasNext() && !resultado){
			return false;
		}else{
			return true;
		}
	}
	
	//Esto me pone una cadena binaria con los bits necesarios.
	public String completaConCeros(String s,int longitud){
		//System.out.println("Cadena entrada: "+s+" longitud necesaria: "+longitud);
		int longitudFinal = longitud - s.length();
		for (int i=0;i<longitudFinal;i++){
			s="0"+s;
		}
		//System.out.println("Cadena resultados:" +s);
		return s;
	}
	
	//Método que me dice los símbolos por nivel.
	//Recorremos las probabilidades y calculamos b^yi >= 1/pi y lo
	//metemos en una lista de nodos que contienen el nivel y el número de símbolos en ese nivel.
	public LinkedList<NodoLista> getEnes(){
		boolean[] comprobados = new boolean[alfabeto.getProbabilidades().size()];
		
		for (boolean b : comprobados){
			b = false;
		}
		
		LinkedList<NodoLista> simbolosNivel = new LinkedList<NodoLista>();
		Iterator<NodoLista> it2;
		NodoLista tmp = null; 
		float tmpFloat=0f,maxFloat=0f;
		int numero,j,maxIndex = 0;
		
		for (int i=0;i<alfabeto.getProbabilidades().size();i++){		
			j = 0;
			maxFloat = 0.0f;
			tmpFloat = 0f;
			//System.out.println("Iteración "+i);
			for (Iterator<Float> it = alfabeto.getProbabilidades().iterator();it.hasNext();){
				tmpFloat = it.next();
				if (!comprobados[j]){
					if (tmpFloat > maxFloat){
						//System.out.println("Nuevo máximo: "+tmpFloat);
						maxFloat = tmpFloat;
						maxIndex = j;
						
						//System.out.println("Índice "+j+" no estaba comprobado, marcamos.");
					}					
				}else{
					//System.out.println("Índice "+j+" ya comprobado.");
				}
				j++;					
			}
			comprobados[maxIndex]=true;
			
			numero = this.menorNumeroQue(maxFloat, 2);			
			it2 = simbolosNivel.iterator();
			
			if (!it2.hasNext()){
				simbolosNivel.add(new NodoLista(numero,1));
			}else{
				tmp = it2.next();
				while (it2.hasNext() && tmp.getNivel()!= numero){
					tmp = it2.next();
				}
				
				if (tmp.getNivel()!= numero){
					simbolosNivel.add(new NodoLista(numero,1));
				}else{
					
					tmp.setNumero(tmp.getNumero()+1);
				}
			}		
		}
		
		for (Iterator<NodoLista> it3 = simbolosNivel.iterator();it3.hasNext();){
			tmp = it3.next();
			System.out.println("Nivel: "+tmp.getNivel()+" simbolos:"+tmp.getNumero());
		}
		return simbolosNivel;
		
	}

}
