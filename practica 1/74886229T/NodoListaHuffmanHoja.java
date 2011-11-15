package Practica1;

public class NodoListaHuffmanHoja extends NodoListaHuffman{

	public NodoListaHuffmanHoja(float probabilidad) {
		super(probabilidad);
		izquierda = null;
		derecha = null;
	}
	
	public NodoListaHuffmanHoja(float probabilidad,int indice){
		super(probabilidad);
		this.indice = indice;		
	}
	
	
}
