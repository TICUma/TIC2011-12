package practica1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class Fuente {
	
	private Alfabeto alfabeto;
	private List<Double> probabilidades = new ArrayList<Double>();
	
	public Fuente(Alfabeto alfabeto, List<Double> probabilidades) {
		this.alfabeto=alfabeto;
		this.probabilidades=probabilidades;
		
	}
	
	public Fuente(Alfabeto alfabeto) {
		this.alfabeto=alfabeto;
		double probabilidad=1/alfabeto.tam();
		for (int i=0;i<alfabeto.tam();i++){
			probabilidades.add(probabilidad);
		}
	}
		
	/**
	 * Metodo que genera la lista de sinbolos
	 * del alfabeto segun sus probabilidades
	 * 
	 * @param numeroSimbolos
	 * @return
	 */
	public List getSimbolos(int numeroSimbolos){
		List s= new ArrayList(); 
		double t;
		double valor;
		int indice;
		Double aux5;
		
		for(int i=1;i<=numeroSimbolos;i++){
			t= Math.random();
			valor=(Double) probabilidades.get(0);
			indice=0;
			while (t<valor){
				indice++;
				valor=+(Double)probabilidades.get(indice);
			}
		s.add(alfabeto.getSimbol(indice));
		}
		
		return s;
	}	
	
	/**
	 * Metodo encargado de obtener un codebook binario
	 * segun Shanon 
	 * 
	 * @return
	 */
	public CodeBook getCodificacion(){
		ArrayList<Double> ap=new ArrayList(probabilidades.size());
		ArrayList<Character> alf=new ArrayList(alfabeto.tam());
		ArrayList<Character> alfa=new ArrayList();
		for(int i=0;i<alfabeto.tam();i++){
			alfa.add(alfabeto.getSimbol(i));
		}
		List<Double> ls=new ArrayList<Double>(probabilidades);
		Double aux=0.0;
		Double menor=1.0;
		int guia=0;
		int index=0;
		
		Iterator<Double> ip;
		for(int i=0;i<probabilidades.size();i++){
			menor=0.0;
			aux=0.0;
			guia=0;
			index=0;
		ip=ls.iterator();
		while(ip.hasNext()){
			aux=ip.next();
			if(aux>=menor){
				menor=aux;
				guia=index;
			}
			index++;
		}
		
		ap.add(menor);
		alf.add(alfa.get(guia));
		ls.remove(guia);
		alfa.remove(guia);
		}
		//System.out.println(ap.toString());
		//System.out.println(alf.toString());
	
		Iterator<Double> iter=ap.iterator();
		int indice=0;
		long fin;
		
		CodeBook cb= new CodeBook();
		
		
		
		double f;
		long ini;
		
		int y;
		Integer exp;
		double res;
		
		while (iter.hasNext()){
			//ini=System.nanoTime();
			exp=1;
			y=2;
			f=iter.next();
			res=1/f;
			while(y<res){
				y=2*y;
				exp++;
				
			}
			
			backtracking(exp,cb,alf.get(indice),"",false);
			
			indice++;
		}
		return cb;
	}
	private void backtracking(int nivel,CodeBook cb,char symbol,String cod,boolean stop)
	{
		if((!cb.containsValue(cod))&(!cb.containsKey(symbol))){
			
		if(nivel==1)
		{
			
			if((!cb.containsValue(cod+"0"))){
				cb.add(symbol,cod+"0");
			
				stop=true;}
			else{ 
				if((!cb.containsValue(cod+"1"))){
				
			
				cb.add(symbol,cod+"1");
			
				stop=true;}
			}
			
		}
		else{
			
			backtracking(nivel-1,cb,symbol,cod+"1",stop);
			
			if(!stop){
			backtracking(nivel-1,cb,symbol,cod+"0",stop);
			}
		}
		}
		
		
	}

	
	/**
	 * Metodo encargado de obtener un codebook al
	 * alfabeto binario segun Huffman
	 * 
	 * @param alfabetoDestino
	 * @return
	 */
	public CodeBook getOptimalCodificacion(){
		CodeBook cb=new CodeBook();
		double menor1;
		double menor2;
		int guia;
		Double aux5;
		double aux4;
		Integer in1;
		int in2;
		int indice;
		double aux;
		double aux2;
		List<Integer> l=new ArrayList<Integer>();
		for(int k=0;k<probabilidades.size();k++){
			l.add(0);
		}
		List<Double> dou=new ArrayList<Double>(probabilidades);
		
		Iterator iter;
		Iterator it;
		for(int j=0;j<probabilidades.size()-1;j++)
		{
			iter =dou.iterator();	
			menor1=1;
			menor2=1;
			guia=0;
			in1=0;
			in2=0;
		
		while(iter.hasNext())
		{
			aux=(Double)iter.next();
			if ((aux<menor1)&&(aux<1))
			{
				menor1=aux;
				in1=guia;
			}
			
			guia++;
		}
		iter =dou.iterator();	
		guia=0;
		while(iter.hasNext())
		{
			aux=(Double)iter.next();
			if ((aux<menor2)&&(aux<1)&&(guia!=in1))
			{
				menor2=aux;
				in2=guia;
			}
			
			guia++;
		}
		
		it= dou.iterator();
		indice=0;
		while(it.hasNext())
		{
			aux2=(Double)it.next();
			if(aux2==in1)
			{
			l.set(indice,l.get(indice)+1);	
			}
			if(aux2==in2)
			{
			l.set(indice,l.get(indice)+1);
			dou.set(indice,in1.doubleValue());
			}
			indice++;
		}
		
		//System.out.print(l.size());
		
		l.set(in1, l.get(in1)+1);
		l.set(in2,l.get(in2)+1);
		dou.set(in1, menor1+menor2);
		
		dou.set(in2,in1.doubleValue());
	}
		
		//Ordenamos...
		
		ArrayList<Integer> ap=new ArrayList(probabilidades.size());
		ArrayList<Character> alf=new ArrayList(alfabeto.tam());
		ArrayList<Character> alfa=new ArrayList();
		for(int i=0;i<alfabeto.tam();i++){
			alfa.add(alfabeto.getSimbol(i));
		}
		List<Integer> ls=new ArrayList<Integer>(l);
		Integer auxs=0;
		Integer menor=0;
		int guia2=0;
		int index=0;
		
		Iterator<Integer> ip;
		for(int i=0;i<probabilidades.size();i++){
			menor=probabilidades.size()+1;
			auxs=0;
			guia=0;
			index=0;
		ip=ls.iterator();
		while(ip.hasNext()){
			auxs=ip.next();
			if(auxs<menor){
				menor=auxs;
				guia2=index;
			}
			index++;
		}
		
		ap.add(menor);
		alf.add(alfa.get(guia2));
		ls.remove(guia2);
		alfa.remove(guia2);
		}
		
		///////////////////////////////////
		//System.out.println(alf.toString());
		//System.out.println(ap.toString());
		it=ap.iterator();
		
		indice=0;
		while(it.hasNext())
		{
			backtracking((Integer) it.next(),cb,alf.get(indice),"",false);
			indice++;
		}
		return cb;
	}
	
	/**
	 * Metodo encargado de devolver una lista de 
	 * numSimbolos del alfabeto origen
	 * y sus correspondientes codificaciones binarias según Shannon-Fano.
	 * 
	 * @param numSimbolos
	 * @return
	 */
	public SimbolosCodificados getSimbolosCodificados(int numSimbolos){
		CodeBook cb=this.getCodificacion();
		//cb.codes();
		//cb.simbols();
		
		
		
		return new SimbolosCodificados(cb.simbols().subList(0,numSimbolos),cb.codes().subList(0,numSimbolos));
	}
	public double longitud(CodeBook c)
	{
			String s;
			double d=0;
			Iterator it=c.codes().iterator();
			Iterator it2=probabilidades.iterator();
			while(it.hasNext()){
				s=(String)it.next();
				d=d+s.length()*(Double)it2.next();
				
				
			}
			return d;
		
	}
	/**
	 * Metodo encargado de devolver una lista de 
	 * numSimbolos del alfabeto origen
	 * y sus correspondientes codificaciones binarias en el
	 * alfabeto destino segun hufman
	 * 
	 * @param numSimbolos
	 * @return
	 */
	public SimbolosCodificados getSimbolosCodificadosOptimos(int numSimbolos){
		CodeBook cb=this.getOptimalCodificacion();
		return new SimbolosCodificados(cb.simbols().subList(0,numSimbolos),cb.codes().subList(0,numSimbolos));
	
	}
public double entropia(List<Double> p,int base) { 
	    double pro=0;
		double res = 0 ;
		Iterator<Double> it=p.iterator();
		while(it.hasNext()){
			pro=it.next();
			res += pro*log(1/pro,base);
		}
		return res;
		/*for(int i = 0 ; i < p.size();i++) {
			pro = p.get(i);
			res += pro*log(1/pro,base);
		}
		return res;*/
	}
	
	private double log(double d, int base) {
	// TODO Auto-generated method stub
	return Math.log10(d)/(Math.log10(base));
}

	//======================metodos auxiliares===================

	
	//====================get and set========================
	
	public Alfabeto getAlfabeto() {
		return alfabeto;
	}

	public void setAlfabeto(Alfabeto alfabeto) {
		this.alfabeto=alfabeto;
	}

	public void setAlfabetoCodificacion(Alfabeto alfabeto) {
	}
	
	public List<Double> getProbabilidades() {
		return probabilidades;
	}


	public void setProbabilidades(List<Double> probabilidades) {
		this.probabilidades=probabilidades;
	}


}
