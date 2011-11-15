/**
* @author Ruiz González, Daniel
*/
package tree;
import java.util.*;

public interface ArbolGeneral<T> extends Iterable<T> {
	
	/* Un árbol general se representa como una raíz y sus subárboles
	 * que son una colección de árboles. Un árbol general no puede ser
	 * vacío, pero puede que no tenga subárboles. Esta interface define
	 * las operaciones básicas que debe tener toda clase que implemente
	 * un árbol general.  */
	
	public void agrega (ArbolGeneral<T> a);
	// agrega el Arbol a a la lista de subarboles.
	
	public List<T> amplitud ();
	// devuelve una lista que contiene el recorrido en amplitud del árbol.
	
	public boolean contiene (Object o);
	// devuelve cierto si el objeto o esta en el árbol.
	
	public boolean contieneTodos (Collection<?> c);
	// devuelve cierto si todos los elementos de la colección c están en el árbol.
	
	public void	elimina(Object o);
	// elimina los subárboles que cuelgan del subárbol cuya raíz es o.
	
	public int numElementos();
	// devuelve el número de elementos del árbol. 
	
	public int numSubarboles();
	// devuelve el número de subárboles que cuelgan de la raíz del árbol.
	
	public List<T> postOrden();
	// devuelve una lista con el recorrido en post-orden del árbol.
	
	public List<T> preOrden();
	// devuelve una lista con el recorrido en pre-orden del árbol.
	
	public int profundidad();
	// devuelve la profundidad del árbol.
	
	public T raiz();
	// devuelve la raíz del árbol.
	
	public void raiz(T elem);
	// cambia la raíz del árbol por elem.
	
	public ArbolGeneral<T> subarbolConRaiz(Object o);
	// devuelve un árbol que tiene como raíz o.
	
	public Collection<ArbolGeneral<T>> subarboles();
	// devuelve una colección de árboles con los subárboles que cuelgan de la raíz.
	
	
}
