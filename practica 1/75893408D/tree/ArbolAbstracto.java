/**
* @author Ruiz González, Daniel
*/
package tree;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.LinkedList;
import java.util.NoSuchElementException;

public abstract class ArbolAbstracto<T> implements Iterable<T>, ArbolGeneral<T> {

	/* Esta clase abstracta implementa aquellas operaciones sobre árboles 
	 * generales que no dependen de la implementación, también define una clase
	 * iteradora para devolver uno a uno los elementos del árbol. */
	
	
	/* La idea para este método es ir guardando los árboles en una cola
	 * y luego ir sacando el primer árbol de la cola guardando la raíz de
	 * este en la lista que luego se devolverá. */
	public List<T> amplitud() { 
		LinkedList< ArbolGeneral<T> > cola = new LinkedList<ArbolGeneral<T>>(); 
		List<T> lista = new LinkedList<T>();  
		cola.add(this); // añadimos este árbol a la cola.
		while ( !cola.isEmpty() ){ 
			if ( !cola.getFirst().subarboles().isEmpty() ){ // si tiene hijos los
				cola.addAll(cola.getFirst().subarboles());  // añadimos a la cola
			}
			lista.add(cola.removeFirst().raiz()); // añadimos la raíz a la lista a
		}										  // la vez que sacamos de la cola.
		return lista; 
	}
	
	
	/* Iremos iterando en los elementos del árbol y mirando si coinciden
	 * con el objeto dado, en el momento que uno coincida paramos la 
	 * iteración y delovemos 'true'. Si acabamos de iterar sin encontrarlo
	 * devolvemos 'false'. */
	public boolean contiene(Object o) {
		boolean esta = false;
		if (o == null){  // si el objeto es nulo devolvemos falso.
			return esta;
		}else{
			Iterator<T> it = this.iterator(); // declaramos un iterador sobre el árbol.
			while (!esta && it.hasNext()){
				if (it.next().equals(o)){
					esta = true;
				}
			}
			return esta;
		}
	}

	/* Aquí he optado por pasar los elementos del árbol a una lista enlazada
	 * y llamar al containsAll de esta lista con los elementos de la colección.
	 * quizás la eficiencia se resienta un poco, pero de todas formas si es cierto
	 * hay que recorrer por completo toda la colección, y si es false hay que
	 * recorrer por completo todo el árbol. */ 
	public boolean contieneTodos(Collection<?> c) {
		List<T> lista = new LinkedList<T>();
		Iterator<T> it = this.iterator();
		while (it.hasNext()){
			lista.add(it.next());
		}
		return lista.containsAll(c);
	}
	

	/* De nuevo se recorre por completo todo el árbol usando un contador que
	 * incrementa por cada iteración */ 
	public int numElementos() {
		Iterator<T> it = this.iterator();
		int res = 0;
		while (it.hasNext()){
			res++;
			it.next();
		}
		return res;
	}
	
	
	/* Aquí el número de subárboles viene dado por el tamaño que tenga la 
	 * colección que contiene a los subárboles. */
	public int numSubarboles() {
		return subarboles().size();
	}
	
	
	
	/* Debe ir en la lista primero la raíz, luego el subárbol izquierdo en 
	 * pre-orden por último los demás en pre-orden. Para ello usaremos un método
	 * recursivo, en el que primero se le añade la raíz a la lista que vamos a 
	 * devolver y luego si el árbol tiene subárboles vamos iterando en cada uno
	 * de ellos y para cada uno le añadimos a la lista el recorrido en pre-orden
	 * de este. */
	public List<T> preOrden() {
		List<T> lista = new LinkedList<T>();
		lista.add(raiz());
		if ( !subarboles().isEmpty() ) {
			Iterator<ArbolGeneral<T>> it = subarboles().iterator();// iterador sobre la
			while (it.hasNext()){								   // la colección de subárboles
				lista.addAll(it.next().preOrden());
			}
		}
		return lista;
	}
	
	
	/* La idea es la misma que en pre-orden solo que la raíz se añade al final */
	public List<T> postOrden() {
		List<T> lista = new LinkedList<T>();
		if ( !subarboles().isEmpty() ) {
			Iterator<ArbolGeneral<T>> it = subarboles().iterator();
			while (it.hasNext()){
				lista.addAll(it.next().postOrden());
			}
		}
		lista.add(raiz());
		return lista;
	}
	
	
	/* Para calcular la profundidad, si no tienes subárboles la profundidad es 1.
	 * en el caso de que tenga será 1 + la profundidad de sus subárboles. */
	public int profundidad(){
		if (subarboles().isEmpty()){
			return 1;
		}else{
			return 1 + profundidadSubarboles( subarboles() ); 
		}
	}
	
	/* Método privado para calcular la profundidad máxima de una colección de árboles.
	 * La idea de este método es ir guardando en un array la profundidad de cada subárbol
	 * y luego devolver el mayor */
	private int profundidadSubarboles(Collection<ArbolGeneral<T>> c){
		Iterator<ArbolGeneral<T>> it = c.iterator();
		int[] profundidades = new int[c.size()];
		int i = 0;
		while (it.hasNext()){
			profundidades[i] = it.next().profundidad();
			i++;
		}
		Arrays.sort(profundidades);
		return profundidades[profundidades.length-1];
	}
	
	
	
	/* Para este método usaré un iterador para buscar la raíz 'o' y cuando la
	 * encuentre delvolveré ese árbol. Si el elemento no está en el árbol lanza 
	 * una excepción. */
	public ArbolGeneral<T> subarbolConRaiz(Object o) {
		if (!contiene(o)){
			throw new ArbolException();
		}
		if(raiz().equals(o)){
			return this;
		}else if (subarboles().isEmpty()){
			return null;
		}
		boolean esta = false;
		Iterator<ArbolGeneral<T>> it = this.subarboles().iterator();
		ArbolGeneral<T> aux = null;
		ArbolGeneral<T> resultado = null;
		while ( !esta && it.hasNext() ){
			aux = it.next();
			if (aux.raiz().equals(o)){
				esta = true;
				resultado =  aux; 
			}else if( !aux.subarboles().isEmpty() && aux.contiene(o) ){
				esta = true;
				resultado = aux.subarbolConRaiz(o);
			}
		}
		return resultado;
	}
	
	

	/* Para representación del árbol devolveré simplemente el recorrido en pre-orden. */ 
	public String toString(){		
		return preOrden().toString();
	}
	
	
	
	/* Devuelve un nuevo iterador sobre el árbol actual. */
	public Iterator<T> iterator() {
		return new IteradorArbolGeneral();
		
	}

	
	
	
	
	// Clase iteradora sobre los elementos del árbol.
	protected class IteradorArbolGeneral implements Iterator<T> {
		
		
		/* La idea para este iterador es usar los iteradores de las estructuras
		 * que componen el árbol. Para ello tendremos un itActual que iterará
		 * sobre el árbol actual, y otro itHijos que iterará sobre los subárboles.
		 * Por úiltimo tenemos una variable primero que nos indica si ya se ha 
		 * devuelto la raíz del árbol actual.*/
		Iterator<T> itActual;
		Iterator<ArbolGeneral<T>> itHijos;
		boolean primero;
		
		/* Para crear un nuevo iterador debemos asignarle a primero false ya que 
		 * todavía no hemos dado la raíz del árbol sobre el que está definido este
		 * iterador. Luego en el caso de que el árbol tenga subárboles, inicializamos
		 * el itHijos creando un nuevo iterador sobre la estructura de los subárboles
		 * y por último inicializamos el itActual al primero de los subárboles. */
		public IteradorArbolGeneral(){
			primero = false;
			if (!subarboles().isEmpty()){
				itHijos = subarboles().iterator();
				itActual = itHijos.next().iterator();
			}
		}
		
		
		/* Para saber si aún nos quedan elementos por dar lo que tenemos que 
		 * comprobar es que primero sea false (no hemos devuelto la raíz, que
		 * itActual tenga siguiente y que itHijos tenga siguiente. Aparte 
		 * comprobamos que los iteradores no sean nulos por si no los hemos
		 * podido incializar. */
		public boolean hasNext() {
			if (!primero){
				return true;
			}else if (itActual != null && itActual.hasNext()){
				return true;
			}else if (itHijos != null && itHijos.hasNext()){
				return true;
			}else{
				return false;
			}
		}
		
		
		/* Para devolver el siguiente elemento debemos mirar si hemos devuelto
		 * ya la raíz, si no es así la devolvemos e indicamos que ya la hemos 
		 * devuelto poniendo primero  true, si ya la devolvimos pues devolvemos 
		 * el siguiente del itActual, en el caso de que este no tenga siguiente
		 * el itActual pasa a ser el siguiente de itHijos y devolvemos el siguiente
		 * de itActual. */
		public T next() {
			if (primero && !itActual.hasNext() && !itHijos.hasNext()){
				throw new NoSuchElementException();
			}
			
			T e = null;
			if (!primero){
				e = raiz();
				primero = true;
			}else if (itActual.hasNext()){
				e = itActual.next();
			}else if (itHijos.hasNext()){
				itActual = itHijos.next().iterator();
				e = itActual.next();
			}
			return e;
		}
		
		
		/* Método no implementado */
		public void remove() {
			throw new UnsupportedOperationException();
		}
	}

}
