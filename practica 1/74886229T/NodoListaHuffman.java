package Practica1;

public class NodoListaHuffman {	
	
	protected float probabilidad; //La probabilidad asociada al nodo hoja.
	protected String codigo;
	protected NodoListaHuffman izquierda;
	protected NodoListaHuffman derecha;
	protected int indice;
	
	public NodoListaHuffman(float probabilidad){
		this.probabilidad = probabilidad;
		codigo="";
	}
	
	public float getProbabilidad(){
		return probabilidad;
	}
	
	public String getCodigo(){
		return codigo;
	}
	
	public void setCodigo(String s){
		this.codigo=s;
	}
	
	public void setIzquierda(NodoListaHuffman nodo){
		this.izquierda = nodo;
	}
	
	public void setDerecha(NodoListaHuffman nodo){
		this.derecha = nodo;
	}
	
	public NodoListaHuffman getIzquierda(){
		return izquierda;
	}
	
	public NodoListaHuffman getDerecha(){
		return derecha;
	}
	
	public String toString(){
		return "Índice asociado: "+indice+" - Probabilidad: "+probabilidad+" - Código: "+codigo;
	}

}
