package practica1;

public class Alfabeto {
	private char[] alfabeto;
	
	public Alfabeto(char[] s){
		alfabeto = s;
		//s.toArray(alfabeto); // Es equivalente??
	}
	public char get(int i) {
		return this.alfabeto[i];
	}
	public int size(){
		return alfabeto.length;
	}
}
