package practica1;

import java.util.Iterator;
import java.util.List;

public abstract class Alfabeto implements Iterable<Character> {
	
	public abstract int getSize();
	
	public abstract Character getCharacter(int index);
	
	public abstract Integer getPosition(int index);

	public abstract Iterator<Character> iterator();
	
    public abstract String toString();
    
    public abstract List<Character> add(Character a);
    
    public abstract List<Character> erase(Character a);

}
