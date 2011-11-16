/**
 * @author Pedro Antonio Pardal Jimena
 */

package practica1;

import java.util.Comparator;

public class HuffmanComparator implements Comparator<Arbol<Simbolo>>
{
	@Override
	public int compare( Arbol<Simbolo> s1, Arbol<Simbolo> s2 )
	{
		return -s1.getElemento().compareTo( s2.getElemento() );
	}
}
