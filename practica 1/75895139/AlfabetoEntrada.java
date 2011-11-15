package practica1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AlfabetoEntrada extends Alfabeto {

	List<Character> simbolos= new ArrayList<Character>();
	
	public AlfabetoEntrada(){
		this.simbolos.add('A');//A
		this.simbolos.add('B');//F
		this.simbolos.add('C');//E
		this.simbolos.add('D');//C
		this.simbolos.add('E');//B
		this.simbolos.add('F');//D
	}
	
	@Override
	public Character getCharacter(int index) {
		return simbolos.get(index);
	}

	@Override
	public int getSize() {
		return simbolos.size();
	}

	@Override
	public Iterator<Character> iterator() {
		return simbolos.iterator();
	}

	@Override
	public Integer getPosition(int index) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
    public String toString() {
        String res ="";
        int i = 0;
        for(Character s:simbolos){
        	if (i==0){
        		res += s.toString();
        		i++;
        	}else{
        		res += ", " + s.toString();
        	}
        }
        return res;
    }

	@Override
	public List<Character> add(Character a) {
		simbolos.add(a);
		return simbolos;
	}

	@Override
	public List<Character> erase(Character a) {
		int i = 0;
		boolean borrado = false;
		while (i < simbolos.size() && !borrado){
			if(simbolos.get(i) == a){
				simbolos.remove(i);
				borrado = true;
			}
			i++;
		}
		return simbolos;
	}

}
