/**
* @author Ruiz González, Daniel
*/
package tree;

@SuppressWarnings("serial")
public class ArbolException extends RuntimeException {
	
	/* Clase para representar una excepción especial para árboles. */
	public ArbolException(){
		super();
	}
	public ArbolException(String e){
		super(e);
	}

}
