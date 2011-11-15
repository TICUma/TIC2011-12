package Practica1;

public class NodoListaHuffmanNormal extends NodoListaHuffman{

	public NodoListaHuffmanNormal(float probabilidad,NodoListaHuffman izquierdo,NodoListaHuffman derecho) {
		super(probabilidad);		
		this.izquierda = izquierdo;
		this.derecha = derecho;
		this.indice = -1;
	}	
}
