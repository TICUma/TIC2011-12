/**
* @author Ruiz Gonz�lez, Daniel
*/
package tree;
import java.util.*;

public class ArbolLista<T> extends ArbolAbstracto<T> {
	
	/* Esta es una implementaci�n de un �rbol general de T, la cual usa
	 * una lista enlazada (LinkedList) para guardar la colecci�n de hijos
	 * del �rbol. Implementa los m�todos de la interface ArbolGeneral
	 * que no son implementados en la clase ArbolAbstracto. */
	
	
	/* Variables de instancia, todo �rbol tendr� una ra�z y una colecci�n 
	 * de hijos. */
	private T raiz;
	private Collection< ArbolGeneral<T> > hijos;
	
	/* Para crear un nuevo �rbol dado un elemento asignamos ese elemento a la
	 * ra�z del �rbol e inicializamos los hijos a una lista vac�a. */
	public ArbolLista(T elem){
		if(elem == null){
			throw new IllegalArgumentException();
		}else{
			raiz = elem;
			hijos = new LinkedList<ArbolGeneral<T>>();
		}
	}
	
	/* Si en cambio nos dan un elemento y una colecci�n que contiene los hijos
	 * asignamos el elemento a la ra�z del �rbol y a�adimos todos los elementos
	 * de la colecci�n a la lista de hijos. */
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
	
	
	/* Para agregar un nuevo �rbol a los hijos basta con a�adirlo a la
	 * lista de hijos. Si el �rbol que nos pasan es nulo, lanzamos una
	 * excepci�n, ya que no puede haber �rboles vac�os. */
	public void agrega (ArbolGeneral<T> a){
		if (a == null){
			throw new ArbolException();
		}
		hijos.add((ArbolLista<T>) a);
	}
	
	
	/* Para eliminar los sub�rboles propios, hago un m�todo recursivo
	 * en el que vamos iterando en los sub�rboles y si la ra�z de alguno
	 * coincide con 'o' eliminamos este sub�rbol. */
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
	
	
	// M�todos de consulta de las variables de instancia. 
	public T raiz(){
		return raiz;
	}
	
	public Collection<ArbolGeneral<T>> subarboles(){
		return hijos;
	}
	
	//Para cambiar la ra�z del �rbol.
	public void raiz(T elem){
		if(elem == null){
			throw new IllegalArgumentException();
		}else{
			raiz = elem;
		}
	}
	

	
	

}
