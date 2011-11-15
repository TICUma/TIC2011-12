package practica1;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class Fuente {
	
	private Alfabeto alfabeto;
	private Alfabeto alfabetoCodificacion;
	private List<Float> probabilidades = new ArrayList<Float>();
	
	public Fuente(Alfabeto alfabeto, List<Float> probabilidades) {
		this.alfabeto = alfabeto;
		this.probabilidades = probabilidades;
	}
	
	public Fuente(Alfabeto alfabeto) {
		this.alfabeto = alfabeto;
		for (int i=0; i<alfabeto.getSize(); i++){
			probabilidades.add((float) (1/alfabeto.getSize()));
		}
	}
	
	
	/**
	 * Metodo que genera la lista de sinbolos
	 * del alfabeto segun sus probabilidades
	 * 
	 * @param numeroSimbolos
	 * @return
	 */
	public List<Character> getSimbolos(int numeroSimbolos){
		List<Character> res = new ArrayList<Character>();
		for (int i=0; i<numeroSimbolos; i++){
			Float p = new Random().nextFloat();
			Float suma = 0.0f;
			int posSimbolo;
			for (posSimbolo=0; posSimbolo<probabilidades.size(); posSimbolo++){
				suma += probabilidades.get(posSimbolo);
				if (suma > p){
					break;
				}
			}
			
			res.add(alfabeto.getCharacter(posSimbolo));
			
		}
		return res;
	}	
	
	/**
	 * Metodo encargado de obtener un codebook binario
	 * segun Shanon 
	 * 
	 * @return
	 */
	public CodeBook getCodificacion(){
		HashMap<Integer, Integer> valores = new HashMap<Integer, Integer>();
		
		int base= 2;
		//Ordenar las probabilidades de mayor a menor
		Collections.sort(probabilidades);
		Collections.reverse(probabilidades);
		
		for(Float p:probabilidades){
			int longitud_codificacion=1; //yi
			//Para sacar yi (el menor entero positivo tal que b^yi > 1/pi)
			while(((int)Math.pow(base,longitud_codificacion))<(1/p)){
				longitud_codificacion++;
			}
			if(valores.containsKey(longitud_codificacion)){
				valores.put(longitud_codificacion, valores.get(longitud_codificacion)+1);
			}
			else{
				valores.put(longitud_codificacion, 1);
			}
		}
		CodeBook c = new CodeBook(alfabeto,alfabetoCodificacion);
		
		List<String> l = new ArrayList<String>();
        ArbolN arbol = new ArbolN(alfabetoCodificacion.getSize());
        int i = 0;
        for(Integer k : valores.keySet()) {
        	int v = valores.get(k);
        	if(v>1){
        		while (v>=1){
        			l.add(ArbolCodificacion.valorNodo(arbol, k, "", alfabetoCodificacion));
    	            c.add(alfabeto.getCharacter(i), l.get(i));
    	            i++;
    	            v--;
        		}
        	}else{
	            l.add(ArbolCodificacion.valorNodo(arbol, k, "", alfabetoCodificacion));
	            c.add(alfabeto.getCharacter(i), l.get(i));
	            i++;
        	}
        }
		
		return c;
	}
	
	/**
	 * Metodo encargado de obtener un codebook al
	 * alfabeto binario segun Huffman
	 * 
	 * @param alfabetoDestino
	 * @return
	 */
	public CodeBook getOptimalCodificacion(){
		
		//Ordenar las probabilidades de mayor a menor
		Collections.sort(probabilidades);
		Collections.reverse(probabilidades);
		
		CodeBook cb = new CodeBook(alfabeto, alfabetoCodificacion);
		Float raiz = 1.0f;
		Character ch = null;
		
		//Creo un arbol
		ArbolHuffman a = new ArbolHuffman(raiz,ch,"");

		/*
		 *Asigno a las hojas del arbol los valores de las probabilidades
		 *y los simbolos 
		 */
		int i = 0;
		for(Float p: probabilidades){
			ArbolHuffman hijo = new ArbolHuffman(p, null, ""); 
			hijo.setChar(alfabeto.getCharacter(i));
			a.addHijos(hijo);
			i++;
		}
		
		/*
		 * Cojo los menores en probabilidad y actualizo el arbol
		 */
		
		while(a.getHijos().size() > 2){
			a = reglaHuffman(a);
		}
		
		codificacionHuffman(a,cb);
		return cb;
	}
	
	public void codificacionHuffman(ArbolHuffman arbol, CodeBook cb){
			
		if (!arbol.getHijos().isEmpty()){
			Iterator<ArbolHuffman> it = arbol.getHijos().iterator();
			ArbolHuffman primero = it.next();
			ArbolHuffman segundo = it.next();
			primero.setCodificacion(arbol.getCodificacion() + "1");
			segundo.setCodificacion(arbol.getCodificacion() + "0");
			codificacionHuffman(primero, cb);
			codificacionHuffman(segundo, cb);
		}else{
			cb.add(arbol.getChar(), arbol.getCodificacion());
		}
	}
	
	public ArbolHuffman reglaHuffman(ArbolHuffman arbol){
		
		if (arbol.getHijos().size() == 2) {
			return null;
		}

		//Sacamos los dos simbolos con menores probabilidades
		List<ArbolHuffman> arbMenorProb = new ArrayList<ArbolHuffman>();
		arbMenorProb = arbolesMenoresProb(arbol);
		
		Float suma= 0.0f;
		for(ArbolHuffman a: arbMenorProb){
			suma += a.getRaiz();
		}
		//Creo un nuevo arbol fruto de la suma de los menores
		ArbolHuffman nuevoA = new ArbolHuffman(suma, null,"");
		nuevoA.addHijos(arbMenorProb.get(0));
		nuevoA.addHijos(arbMenorProb.get(1));
		
		//Creo el arbol definitivo
		ArbolHuffman finalA = new ArbolHuffman(arbol.getRaiz(), arbol.getChar(),"");
		
		//Metemos en la lista de hijosFinales todos menos los dos de menor probabilidad
		for (ArbolHuffman h:arbol.getHijos()){
			if(arbMenorProb.get(0).getRaiz() == h.getRaiz()){
				
			}else if (arbMenorProb.get(1).getRaiz() == h.getRaiz()){
			}	
			else{
				finalA.addHijos(h);
			}
		}
		
		//Añado el arbol creado a una de las ramas
		finalA.addHijos(nuevoA);
		
		
		//Devuelvo dicho arbol
		return finalA;
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
		 CodeBook c = getCodificacion();
	     List<Character> listaSim = getSimbolos(numSimbolos);
	     List<String> listaSimCod = new ArrayList<String>();
	     
	     for(Character caracter : listaSim){
	    	 String codigo = c.get(caracter); 
	    	 listaSimCod.add(codigo);
	     }
		return (new SimbolosCodificados(listaSim, listaSimCod));
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
		CodeBook c = getOptimalCodificacion();
	     List<Character> listaSim = getSimbolos(numSimbolos);
	     List<String> listaSimCod = new ArrayList<String>();
	     
	     for(Character caracter : listaSim){
	    	 String codigo = c.get(caracter); 
	    	 listaSimCod.add(codigo);
	     }
		return (new SimbolosCodificados(listaSim, listaSimCod));
	}
	
	
	//======================metodos auxiliares===================
	public Float Entropia(){
		//Recorrer la lista de probabilidades y hacer el sumatorio de pi*log2(1/pi)
		float res = 0;
		for(Float p: probabilidades){
			res += p* (Math.log(1/p) / Math.log(2));
		}
		return res;
	}
	public Float LongitudMedia (CodeBook cb){
		float res = 0;
		
		//Para cada caracter del mensaje ver el code que le corresponde en el codebook y multiplicar por su longitud
		int i =0;
		for(Float p:probabilidades){
				res += cb.codes().get(i).length() * p;		
				i++;
		}
        return res;
	}
	public List<ArbolHuffman> arbolesMenoresProb(ArbolHuffman arbol){
		Float menor = 1f;
		ArbolHuffman a1 = new ArbolHuffman(1f, null, ""); //Menor
		ArbolHuffman a2 = new ArbolHuffman(1f, null, ""); //Segundo menor
		for(ArbolHuffman a:arbol.getHijos()){
			if(a.getRaiz()< menor){
				menor = a.getRaiz();
				a1.setRaiz(a.getRaiz());
				a1.setChar(a.getChar());
				a1.setHijos(a.getHijos());
				a1.setCodificacion(a.getCodificacion());
			}
		}
		Float menor2 = 1f;
		for(ArbolHuffman a:arbol.getHijos()){
			if(a.getRaiz()< menor2 && a.getRaiz()!= a1.getRaiz()){
				menor2 = a.getRaiz();
				a2.setRaiz(a.getRaiz());
				a2.setChar(a.getChar());
				a2.setHijos(a.getHijos());
				a2.setCodificacion(a.getCodificacion());
			}
		}
		
		List<ArbolHuffman> menores = new ArrayList<ArbolHuffman>();
		menores.add(a1);
		menores.add(a2);
        return menores;
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
	
	public List<Float> getProbabilidades() {
		return probabilidades;
	}


	public void setProbabilidades(List<Float> probabilidades) {
		this.probabilidades = probabilidades;
	}


}
