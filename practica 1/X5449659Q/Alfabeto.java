package practica1;

public abstract class Alfabeto  {
    
	public abstract int length(); 
	public abstract Object SimbolAt(int pos);
	public abstract Alfabeto getsubAlfabeto(int num);
}
