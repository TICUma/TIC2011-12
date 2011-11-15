/**
* @author Ruiz Gonz�lez, Daniel
*/
package tree;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.LinkedList;
import java.util.NoSuchElementException;

public abstract class ArbolAbstracto<T> implements Iterable<T>, ArbolGeneral<T> {

	/* Esta clase abstracta implementa aquellas operaciones sobre �rboles 
	 * generales que no dependen de la implementaci�n, tambi�n define una clase
	 * iteradora para devolver uno a uno los elementos del �rbol. */
	
	
	/* La idea para este m�todo es ir guardando los �rboles en una cola
	 * y luego ir sacando el primer �rbol de la cola guardando la ra�z de
	 * este en la lista que luego se devolver�. */
	public List<T> amplitud() { 
		LinkedList< ArbolGeneral<T> > cola = new LinkedList<ArbolGeneral<T>>(); 
		List<T> lista = new LinkedList<T>();  
		cola.add(this); // a�adimos este �rbol a la cola.
		while ( !cola.isEmpty() ){ 
			if ( !cola.getFirst().subarboles().isEmpty() ){ // si tiene hijos los
				cola.addAll(cola.getFirst().subarboles());  // a�adimos a la cola
			}
			lista.add(cola.removeFirst().raiz()); // a�adimos la ra�z a la lista a
		}										  // la vez que sacamos de la cola.
		return lista; 
	}
	
	
	/* Iremos iterando en los elementos del �rbol y mirando si coinciden
	 * con el objeto dado, en el momento que uno coincida paramos la 
	 * iteraci�n y delovemos 'true'. Si acabamos de iterar sin encontrarlo
	 * devolvemos 'false'. */
	public boolean contiene(Object o) {
		boolean esta = false;
		if (o == null){  // si el objeto es nulo devolvemos falso.
			return esta;
		}else{
			Iterator<T> it = this.iterator(); // declaramos un iterador sobre el �rbol.
			while (!esta && it.hasNext()){
				if (it.next().equals(o)){
					esta = true;
				}
			}
			return esta;
		}
	}

	/* Aqu� he optado por pasar los elementos del �rbol a una lista enlazada
	 * y llamar al containsAll de esta lista con los elementos de la colecci�n.
	 * quiz�s la eficiencia se resienta un poco, pero de todas formas si es cierto
	 * hay que recorrer por completo toda la colecci�n, y si es false hay que
	 * recorrer por completo todo el �rbol. */ 
	public boolean contieneTodos(Collection<?> c) {
		List<T> lista = new LinkedList<T>();
		Iterator<T> it = this.iterator();
		while (it.hasNext()){
			lista.add(it.next());
		}
		return lista.containsAll(c);
	}
	

	/* De nuevo se recorre por completo todo el �rbol usando un contador que
	 * incrementa por cada iteraci�n */ 
	public int numElementos() {
		Iterator<T> it = this.iterator();
		int res = 0;
		while (it.hasNext()){
			res++;
			it.next();
		}
		return res;
	}
	
	
	/* Aqu� el n�mero de sub�rboles viene dado por el tama�o que tenga la 
	 * colecci�n que contiene a los sub�rboles. */
	public int numSubarboles() {
		return subarboles().size();
	}
	
	
	
	/* Debe ir en la lista primero la ra�z, luego el sub�rbol izquierdo en 
	 * pre-orden por �ltimo los dem�s en pre-orden. Para ello usaremos un m�todo
	 * recursivo, en el que primero se le a�ade la ra�z a la lista que vamos a 
	 * devolver y luego si el �rbol tiene sub�rboles vamos iterando en cada uno
	 * de ellos y para cada uno le a�adimos a la lista el recorrido en pre-orden
	 * de este. */
	public List<T> preOrden() {
		List<T> lista = new LinkedList<T>();
		lista.add(raiz());
		if ( !subarboles().isEmpty() ) {
			Iterator<ArbolGeneral<T>> it = subarboles().iterator();// iterador sobre la
			while (it.hasNext()){								   // la colecci�n de sub�rboles
				lista.addAll(it.next().preOrden());
			}
		}
		return lista;
	}
	
	
	/* La idea es la misma que en pre-orden solo que la ra�z se a�ade al final */
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
	
	
	/* Para calcular la profundidad, si no tienes sub�rboles la profundidad es 1.
	 * en el caso de que tenga ser� 1 + la profundidad de sus sub�rboles. */
	public int profundidad(){
		if (subarboles().isEmpty()){
			return 1;
		}else{
			return 1 + profundidadSubarboles( subarboles() ); 
		}
	}
	
	/* M�todo privado para calcular la profundidad m�xima de una colecci�n de �rboles.
	 * La idea de este m�todo es ir guardando en un array la profundidad de cada sub�rbol
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
	
	
	
	/* Para este m�todo usar� un iterador para buscar la ra�z 'o' y cuando la
	 * encuentre delvolver� ese �rbol. Si el elemento no est� en el �rbol lanza 
	 * una excepci�n. */
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
	
	

	/* Para representaci�n del �rbol devolver� simplemente el recorrido en pre-orden. */ 
	public String toString(){		
		return preOrden().toString();
	}
	
	
	
	/* Devuelve un nuevo iterador sobre el �rbol actual. */
	public Iterator<T> iterator() {
		return new IteradorArbolGeneral();
		
	}

	
	
	
	
	// Clase iteradora sobre los elementos del �rbol.
	protected class IteradorArbolGeneral implements Iterator<T> {
		
		
		/* La idea para este iterador es usar los iteradores de las estructuras
		 * que componen el �rbol. Para ello tendremos un itActual que iterar�
		 * sobre el �rbol actual, y otro itHijos que iterar� sobre los sub�rboles.
		 * Por �iltimo tenemos una variable primero que nos indica si ya se ha 
		 * devuelto la ra�z del �rbol actual.*/
		Iterator<T> itActual;
		Iterator<ArbolGeneral<T>> itHijos;
		boolean primero;
		
		/* Para crear un nuevo iterador debemos asignarle a primero false ya que 
		 * todav�a no hemos dado la ra�z del �rbol sobre el que est� definido este
		 * iterador. Luego en el caso de que el �rbol tenga sub�rboles, inicializamos
		 * el itHijos creando un nuevo iterador sobre la estructura de los sub�rboles
		 * y por �ltimo inicializamos el itActual al primero de los sub�rboles. */
		public IteradorArbolGeneral(){
			primero = false;
			if (!subarboles().isEmpty()){
				itHijos = subarboles().iterator();
				itActual = itHijos.next().iterator();
			}
		}
		
		
		/* Para saber si a�n nos quedan elementos por dar lo que tenemos que 
		 * comprobar es que primero sea false (no hemos devuelto la ra�z, que
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
		 * ya la ra�z, si no es as� la devolvemos e indicamos que ya la hemos 
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
		
		
		/* M�todo no implementado */
		public void remove() {
			throw new UnsupportedOperationException();
		}
	}

}
