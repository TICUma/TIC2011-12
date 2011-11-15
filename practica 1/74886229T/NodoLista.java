package Practica1;

public class NodoLista {
	int nivel;
	int numero;
	
	public NodoLista(int nivel,int numero){
		this.nivel = nivel;
		this.numero = 1;
	}
	
	public int getNivel(){
		return nivel;
	}
	
	public int getNumero(){
		return numero;
	}
	
	public void setNumero(int n){
		numero = n;
	}

}
