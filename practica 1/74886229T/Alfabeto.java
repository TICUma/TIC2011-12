package Practica1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Alfabeto {
	
	private List<Character> simbolos;
	private List<Float> probabilidades;
	
	public Alfabeto(char[] simbolos,float[] probabilidades){
		this.simbolos = new ArrayList<Character>();
		this.probabilidades = new ArrayList<Float>();
		
		for (int i=0;i<simbolos.length;i++){
			this.simbolos.add(simbolos[i]);
		}
		
		for (int i=0;i<probabilidades.length;i++){
			this.probabilidades.add(probabilidades[i]);
		}
	}
	
	public Alfabeto(List<Character> simbolos,List<Float> probabilidades){
		this.simbolos = (List<Character>) simbolos;
		this.probabilidades = (List<Float>) probabilidades;
	}
	
	public char getSimbolo(){
		double random = Math.random();
		float probabilidadAcumulada = 0f;
		int i=0;
		Iterator<Float> it = probabilidades.iterator();
		
		//System.out.println("Numero aleatorio: "+random);
		
		
		while (it.hasNext() && probabilidadAcumulada<=((float) random)){
			probabilidadAcumulada+=it.next();
			i++;
		}
		
		//System.out.println("Símbolo asociado: "+simbolos.get(i-1));
		
		return simbolos.get(i-1);
	}
	
	public List<Float> getProbabilidades(){
		return probabilidades;
	}
	
	public char getSimbolo(int i){
		return simbolos.get(i);
	}

	public void asignaProbabilidad(List<?> probabilidades2){
		this.probabilidades = (List<Float>) probabilidades2;
	}
}
