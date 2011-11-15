/**
* @author Ruiz Gonz�lez, Daniel
*/
package tree;
import java.util.*;

public interface ArbolGeneral<T> extends Iterable<T> {
	
	/* Un �rbol general se representa como una ra�z y sus sub�rboles
	 * que son una colecci�n de �rboles. Un �rbol general no puede ser
	 * vac�o, pero puede que no tenga sub�rboles. Esta interface define
	 * las operaciones b�sicas que debe tener toda clase que implemente
	 * un �rbol general.  */
	
	public void agrega (ArbolGeneral<T> a);
	// agrega el Arbol a a la lista de subarboles.
	
	public List<T> amplitud ();
	// devuelve una lista que contiene el recorrido en amplitud del �rbol.
	
	public boolean contiene (Object o);
	// devuelve cierto si el objeto o esta en el �rbol.
	
	public boolean contieneTodos (Collection<?> c);
	// devuelve cierto si todos los elementos de la colecci�n c est�n en el �rbol.
	
	public void	elimina(Object o);
	// elimina los sub�rboles que cuelgan del sub�rbol cuya ra�z es o.
	
	public int numElementos();
	// devuelve el n�mero de elementos del �rbol. 
	
	public int numSubarboles();
	// devuelve el n�mero de sub�rboles que cuelgan de la ra�z del �rbol.
	
	public List<T> postOrden();
	// devuelve una lista con el recorrido en post-orden del �rbol.
	
	public List<T> preOrden();
	// devuelve una lista con el recorrido en pre-orden del �rbol.
	
	public int profundidad();
	// devuelve la profundidad del �rbol.
	
	public T raiz();
	// devuelve la ra�z del �rbol.
	
	public void raiz(T elem);
	// cambia la ra�z del �rbol por elem.
	
	public ArbolGeneral<T> subarbolConRaiz(Object o);
	// devuelve un �rbol que tiene como ra�z o.
	
	public Collection<ArbolGeneral<T>> subarboles();
	// devuelve una colecci�n de �rboles con los sub�rboles que cuelgan de la ra�z.
	
	
}
