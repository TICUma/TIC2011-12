package practica1;

public  class Alfabeto {

	private char[] alf;
	public Alfabeto(char[] t){
		alf=t;
	}
	public int tam(){
		return alf.length;
	}
	
	public char getSimbol(int i){
		return alf[i];
	}
	public char[] getArray(){
		return alf;
	}
}
