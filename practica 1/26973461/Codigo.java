package practica1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Codigo implements Iterator<String>{

	private List<String> codigo;
	private Iterator<String> it;
	
	public Codigo(int longitud){
		this.codigo = new ArrayList<String>();
		String tmp="";
		ArrayList<String> nuevos;
		// Genera el codigo "00...000" Dependiendo de la longitud
		for(int i=0; i<longitud;i++) tmp+="0";
		codigo.add(tmp);
		
		for(int i=longitud-1; i>=0; i--){
			nuevos = new ArrayList<String>();
			for (String cod : codigo){
				nuevos.add(cod.substring(0, i)+"1"+cod.substring(i+1));
			}
			codigo.addAll(nuevos);
		}
		it = codigo.iterator();
	}
	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		return it.hasNext();
	}

	@Override
	public String next() {
		// TODO Auto-generated method stub
		return it.next();
	}

	@Override
	public void remove() {
		// TODO Auto-generated method stub
		
	}
	public String toString(){
		String res="";
		for(String cad : codigo){
			res +=cad+" ";
		}
		return res;
	}
	
}
