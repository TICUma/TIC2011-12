package practica1;

import java.util.ArrayList;
import java.util.List;

public class ArbolHuffman{

	//Para crear árboles binarios, ternarios...de N ramas cada nivel
	
    private Float raiz = -1f;
    private Character ch = null;
    private String cod = "";
    private List<ArbolHuffman> hijos = null;

        
    public ArbolHuffman(Float raiz, Character c, String cod) {
        this.raiz = raiz;
        this.ch = c;
        this.cod = cod;
        hijos = new ArrayList<ArbolHuffman>();
    }

    public void addHijos(ArbolHuffman a) {
        hijos.add(a);
    }

    public List<ArbolHuffman> getHijos() {
        return hijos;
    }

    public void setHijos(List<ArbolHuffman> hijos) {
        this.hijos = hijos;
    }

    public Float getRaiz() {
        return raiz;
    }

    public void setRaiz(Float raiz) {
        this.raiz = raiz;
    }
    
    public Character getChar() {
        return ch;
    }

    public void setChar(Character ch) {
        this.ch = ch;
    }
    
    public String getCodificacion(){
    	return cod;
    }

    public void setCodificacion(String cod){
    	this.cod = cod;
    }


}
