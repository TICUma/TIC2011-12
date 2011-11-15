package practica1;

import java.util.*;

public class Fuente {
	
	private List<Character> alfabeto = new ArrayList<Character>();
	private List<Double> probabilidades = new ArrayList<Double>();
	
	public Fuente(List<Character> alfabeto, List<Double> probabilidades) {
		 this.alfabeto = alfabeto ; 
		 this.probabilidades = probabilidades;
	}
	
	public Fuente(List<Character> alfabeto) {
		  int longitud = alfabeto.size();
		  double probabilidad = 1/longitud;
		  this.alfabeto = alfabeto ; 
		  List<Double> p = new ArrayList<Double>(longitud);
		  rellenarListaProbi(p,probabilidad);
		  this.probabilidades = p ;
		
	}
		
	/**
	 * Metodo que genera la lista de sinbolos
	 * del alfabeto segun sus probabilidades
	 * 
	 * @param numeroSimbolos
	 * @return
	 */
	public List<Character> getSimbolos(int numeroSimbolos){
		List<Character> cad = new ArrayList<Character>(numeroSimbolos);
		
		for(int i = 0 ; i < numeroSimbolos ; i++) { 
			double num = Math.random();
			int j = 0 ; 
			double rango = 0.0 ;
			boolean ok = false ;
			while(j < probabilidades.size() && !ok) { 
			    rango += probabilidades.get(j) ;
				if( num < rango ) { 
					cad.add(i,alfabeto.get(j)); 
					ok = true ; 
				}
				j++;
			}
			
		}
		return cad ;
	}	
	
	/**
	 * Metodo encargado de obtener un codebook binario
	 * segun Shanon 
	 * 
	 * @return
	 */
	
	public CodeBook getCodificacion(){
		
		/** 1. ordenar el alfabeta segun sus probabilidades en orden decreciente**/
		ordenarAlfabeto(); 
		/** 2.dividir en dos subconjunto de probabilidad casi iguales **/ 
		   int barrera = encontrarBarrera(this.probabilidades);
		   
		   List<Character> L0 = this.alfabeto.subList(0, barrera+1);
		   List<Character> L1 = this.alfabeto.subList(barrera+1, alfabeto.size());
		   List<String> subcon1 = codificar(L0,"0");
		   List<String> subcon2 = codificar(L1,"1");
		   CodeBook res = new CodeBook();
		   for(int i = 0 ; i <L0.size();i++) { 
			   res.add(L0.get(i), subcon1.get(i));
		   }
		   for(int i = 0 ; i <L1.size();i++) { 
			   res.add(L1.get(i), subcon2.get(i));
		   }	      
		   
		return res ;   
	}
	
	
		

	/**
	 * Metodo encargado de obtener un codebook al
	 * alfabeto binario segun Huffman
	 * 
	 * @param alfabetoDestino
	 * @return
	 */
	public CodeBook  getOptimalCodificacion(){
         
		/** 1. ordenar el alfabeto **7 
		 *
		 */
		CodeBook cb = new CodeBook();
		ordenarAlfabeto() ;
		TreeSet<ArbolBinario> arboles = new TreeSet<ArbolBinario>();
		/** creamos arboles a partir del alfabeto ; 
		 * 
		 */
		for(int i= 0 ; i <this.alfabeto.size();i++) { 
			ArbolBinario aux = new ArbolBinario(alfabeto.get(i).toString(),probabilidades.get(i));
			arboles.add(aux);
		}
		
		while(arboles.size() >1) { 
			ArbolBinario a1 = arboles.first() ;
			arboles.remove(a1);
			ArbolBinario a2 = arboles.first() ; 
			arboles.remove(a2) ; 
			// tenemos los dos menores ahora los juntamos 
			ArbolBinario mez = new ArbolBinario(a2, a1);
			arboles.add(mez);
		}
		if(arboles.size() == 1) { 
			ArbolBinario fin = arboles.first();
			ArbolBinario.printTree(fin,cb);
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
		
		List<Character> mensaje = getSimbolos(numSimbolos);
		CodeBook res = this.getCodificacion();
		
		List<String> mencod = new ArrayList<String>(mensaje.size());
		for (int i = 0 ; i < mensaje.size();i++) mencod.add(i, res.get(mensaje.get(i)));
		return new SimbolosCodificados(mensaje, mencod) ;
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
		List<Character> mensaje = getSimbolos(numSimbolos);
		CodeBook res = this.getOptimalCodificacion();
		
		List<String> mencod = new ArrayList<String>(mensaje.size());
		for (int i = 0 ; i < mensaje.size();i++) mencod.add(i, res.get(mensaje.get(i)));
		return new SimbolosCodificados(mensaje, mencod) ;
	}
	
	
	//======================metodos auxiliares===================
	/*private void rellenarNivPot(int[] a,int[] b){ 
		
		for(int i = 0 ; i<a.length;i++) a[i] = 0 ; 
		// rellenar con valores de los potencias 
		for(int i = 0 ; i < b.length ; i++) { 
			int ind = b[i] - 1 ;
			a[ind]++;
		}
	}
	private int  MaxArrayExponente(int[] vector) { 
		int i = 0 ; 
		int res = vector[i] ; 
		 
		while(i < vector.length) { 
			if(res < vector[i]) { 
				res = vector[i] ;
			}
			i++ ;
		}
		return res ; 
	}*/
	
	
	private void rellenarListaProbi(List<Double> p,double num) {
		
		for(int i = 0 ; i < p.size();i++) { 
			p.set(i,num);
		}
	}
	// logaritmo en base 2
	private double log(double num,int base) { 
		
		return Math.log10(num)/Math.log10(base);
	}
	
	public double entropia(List<Double> p,int base) { 
		
		double res = 0 ; 
		for(int i = 0 ; i < p.size();i++) {
			double pro = p.get(i);
			res += pro*log(1/pro,base);
		}
		return res;
	}
	
	public double longitudMedia() { 
		double res = 0 ;
		List<String> cad  = getCodificacion().codes() ;
		for(int i = 0 ; i < cad.size();i++) { 
			double longitud = cad.get(i).length() ;
			res += longitud* probabilidades.get(i);
		}
		return res ; 
	}
	
	private void ordenarAlfabeto() { 
		
		int pos = 0 ; 
		while(pos < probabilidades.size()) {
			int ind = IndiceMax(this.probabilidades,pos);
			intercambiar(this.probabilidades, pos, ind);
			char aux = alfabeto.get(pos);
			alfabeto.set(pos,alfabeto.get(ind));
			alfabeto.set(ind, aux);
			pos++;
		}
		
	}
	private int IndiceMax(List<Double> l,int pos) {
		double res = 0 ; 
		int i = pos ;
		int ind = 0;
		while(i<l.size()) { 
			if(l.get(i)>res) { 
				res = l.get(i);
				ind = i;
			}
			i++;
		}
	   return ind ;	
	}
	private void intercambiar(List<Double>l,int posx,int posy){
		
		 double aux = l.get(posx);
		 l.set(posx, l.get(posy));
		 l.set(posy,aux);
	}
    private int encontrarBarrera(List<Double> l) { 
    	double res = 0 ; 
    	int i = 0 ; 
    	res = l.get(i);
    	while((i<l.size())&&(res <= SumProb(l,i))){
    		i++;
    		res += l.get(i);
    	}
    	return i ;
    	
    }
    protected Double SumProb(List<Double>l,int pos) { 
    	double res = 0 ; 
    	for(int i = pos ; i <l.size();i++) { 
    		res += l.get(i);
    	}
    	return res ;
    }
    private List<String>  codificar(List<Character> l,String c){
        List<String> code = new  ArrayList<String>();   
    	if(l.size()==1){
    		code.add(""+c);
    	}else {
    		// es mayor que 1 
    		code.addAll(codificar(l.subList(0,1), c+"0"));
    		code.addAll(codificar(l.subList(1, l.size()), c+"1"));
    	}
    	return code;
    }
   /* private int exponente(int base ,double num) {
    	// busca el exponente tal que base^expo >= 1/num ;
    	
    	double aux = 1/num ; 
    	int j = 0 ; 
    	while (Math.pow(base,j)<aux) j++ ;
    	return j ;
    }
    private void rellenarPot(int[] a,int base,List<Double> prob) { 
    	
    	for(int i = 0 ; i <a.length ; i++) { 
    		a[i] = exponente(base,prob.get(i));
    	}
    }*/
    
    
	//====================get and set========================
	
	public List<Character> getAlfabeto() {
		return alfabeto;
	}

	public void setAlfabeto(List<Character> alfabeto) {
		this.alfabeto = alfabeto;
	}

	public void setAlfabetoCodificacion(Alfabeto alfabeto) {
		
		
		
	}
	
	public List<Double> getProbabilidades() {
		return probabilidades;
	}


	public void setProbabilidades(List<Double> probabilidades) {
		this.probabilidades = probabilidades;
	}


}