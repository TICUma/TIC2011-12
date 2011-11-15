/**
* @author Ruiz González, Daniel
*/
package tree;
import java.util.*;

public class ArbolLista<T> extends ArbolAbstracto<T> {
	
	/* Esta es una implementación de un árbol general de T, la cual usa
	 * una lista enlazada (LinkedList) para guardar la colección de hijos
	 * del árbol. Implementa los métodos de la interface ArbolGeneral
	 * que no son implementados en la clase ArbolAbstracto. */
	
	
	/* Variables de instancia, todo árbol tendrá una raíz y una colección 
	 * de hijos. */
	private T raiz;
	private Collection< ArbolGeneral<T> > hijos;
	
	/* Para crear un nuevo árbol dado un elemento asignamos ese elemento a la
	 * raíz del árbol e inicializamos los hijos a una lista vacía. */
	public ArbolLista(T elem){
		if(elem == null){
			throw new IllegalArgumentException();
		}else{
			raiz = elem;
			hijos = new LinkedList<ArbolGeneral<T>>();
		}
	}
	
	/* Si en cambio nos dan un elemento y una colección que contiene los hijos
	 * asignamos el elemento a la raíz del árbol y añadimos todos los elementos
	 * de la colección a la lista de hijos. */
	public ArbolLista(T elem, Collection < ArbolGeneral<T> > c){
		if (c == null){
			throw new ArbolException();
		}else if(elem == null){
			throw new IllegalArgumentException();
		}else{
			raiz = elem;
			hijos = new LinkedList<ArbolGeneral<T>>();
			hijos.addAll(c);
		}
	}
	
	
	/* Para agregar un nuevo árbol a los hijos basta con añadirlo a la
	 * lista de hijos. Si el árbol que nos pasan es nulo, lanzamos una
	 * excepción, ya que no puede haber árboles vacíos. */
	public void agrega (ArbolGeneral<T> a){
		if (a == null){
			throw new ArbolException();
		}
		hijos.add((ArbolLista<T>) a);
	}
	
	
	/* Para eliminar los subárboles propios, hago un método recursivo
	 * en el que vamos iterando en los subárboles y si la raíz de alguno
	 * coincide con 'o' eliminamos este subárbol. */
	public void elimina(Object o){
		if ( !hijos.isEmpty() ){
			Iterator<ArbolGeneral<T>> it = hijos.iterator();
			ArbolGeneral<T> aux = null;
			while (it.hasNext()){
				aux = it.next();
				if (aux.raiz().equals(o)){
					it.remove();
				}else if (aux.contiene(o)){
					aux.elimina(o);
				}
			}
		}
	}
	
	
	// Métodos de consulta de las variables de instancia. 
	public T raiz(){
		return raiz;
	}
	
	public Collection<ArbolGeneral<T>> subarboles(){
		return hijos;
	}
	
	//Para cambiar la raíz del árbol.
	public void raiz(T elem){
		if(elem == null){
			throw new IllegalArgumentException();
		}else{
			raiz = elem;
		}
	}
	

	
	

}
